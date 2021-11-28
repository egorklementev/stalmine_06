package ru.erked.stalmine.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.common.entities.EntityBullet;
import ru.erked.stalmine.common.entities.EntityBulletGrenade;
import ru.erked.stalmine.common.entities.EntityBulletGrenadeM430;
import ru.erked.stalmine.common.entities.EntityBulletGrenadeOG7V;
import ru.erked.stalmine.common.items.ItemAmmo;
import ru.erked.stalmine.common.weapons.Weapon;
import ru.erked.stalmine.common.weapons.WeaponDataModel;

public class PacketShootMainhand implements IMessage {

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public PacketShootMainhand() { }

    public static class Handler implements IMessageHandler<PacketShootMainhand, IMessage> {
        @Override
        public IMessage onMessage(PacketShootMainhand message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketShootMainhand message, MessageContext ctx) {
            EntityPlayerMP p = ctx.getServerHandler().player;
            World world = p.getEntityWorld();
            ItemStack wis = p.getHeldItemMainhand();
            WeaponDataModel wdm = ((Weapon)wis.getItem()).model;

            int ammoType = wis.getTagCompound().getInteger("ammo_type");
            int clip = wis.getTagCompound().getInteger("clip");
            int grenadeClip = wis.getTagCompound().getInteger("grenade_clip");
            float fr_timer = wis.getTagCompound().getFloat("fr_timer");
            boolean has_shot = wis.getTagCompound().getBoolean("has_shot");

            if (ammoType == 2) {
                boolean performShooting = (grenadeClip > 0 && !has_shot) || p.capabilities.isCreativeMode;
                if (performShooting) {
                    if (fr_timer <= 0f) {
                        StalminePacketHandler.INSTANCE.sendTo(new PacketShowShootMainhand(), p);

                        float modifiers = 1f;
                        if (!p.onGround) modifiers -= .25f;
                        if (p.isSprinting()) modifiers -= .25f;
                        if (p.isSneaking()) modifiers += .25f;
                        if (Minecraft.getMinecraft().gameSettings.keyBindUseItem.isKeyDown()) modifiers += .25f;
                        modifiers *= .2f + .8f * (1f - 1f * wis.getItemDamage() / wis.getMaxDamage());

                        if (wdm.getGrenadeAmmo().equals("am_vog25")) {
                            EntityBulletGrenade be = new EntityBulletGrenade(
                                    world,
                                    p,
                                    1f
                            );
                            be.setStrength(4f);
                            float dmgModifier = ((ItemAmmo) Item.getByNameOrId(
                                    StalmineMod.MODID + ":am_vog25"
                            )).dmgModifier;
                            be.setDamage(wdm.getDamage() * dmgModifier);
                            be.shoot(
                                    p,
                                    p.rotationPitch,
                                    p.rotationYaw,
                                    0f,
                                    wdm.getBulletVelocity() / 3f,
                                    10f / (wdm.getAccuracy() * modifiers)
                            );
                            world.spawnEntity(be);
                        }
                        else {
                            EntityBulletGrenadeM430 be = new EntityBulletGrenadeM430(
                                    world,
                                    p,
                                    1f
                            );
                            be.setStrength(5f);
                            float dmgModifier = ((ItemAmmo) Item.getByNameOrId(
                                    StalmineMod.MODID + ":am_m430"
                            )).dmgModifier;
                            be.setDamage(wdm.getDamage() * dmgModifier);
                            be.shoot(
                                    p,
                                    p.rotationPitch,
                                    p.rotationYaw,
                                    0f,
                                    wdm.getBulletVelocity() / 2.4f,
                                    10f / (wdm.getAccuracy() * modifiers)
                            );
                            world.spawnEntity(be);
                        }

                        wis.getTagCompound().setFloat("fr_timer", 1200f / wdm.getFireRate() + fr_timer);
                        wis.getTagCompound().setBoolean("has_shot", true);
                        if (!p.capabilities.isCreativeMode) {
                            wis.getTagCompound().setInteger("grenade_clip", grenadeClip - 1);
                            wis.damageItem(1, p);
                        }
                    }
                } else if (grenadeClip <= 0) {
                    int max_ammo = 1;
                    int cur_ammo = wis.getTagCompound().getInteger("grenade_clip");
                    float timer = wis.getTagCompound().getFloat("fr_timer");
                    int added = 0;
                    ItemStack ammo = ((Weapon) wis.getItem()).findAmmo(p, ammoType);
                    while (!ammo.isEmpty() && max_ammo > (cur_ammo + added) && timer <= 0f) {
                        int ammo_size = ammo.getMaxDamage() - ammo.getItemDamage();
                        int toAdd = Math.min(ammo_size, max_ammo - (cur_ammo + added));
                        added += toAdd;
                        ammo.damageItem(toAdd, p);
                        if (ammo.getItemDamage() == ammo.getMaxDamage()) ammo.damageItem(1, p);
                        ammo = ((Weapon) wis.getItem()).findAmmo(p, ammoType);
                    }
                    if (added > 0) {
                        wis.getTagCompound().setInteger("grenade_clip", cur_ammo + added);
                        wis.getTagCompound().setFloat("fr_timer", wdm.getReloadTime() * 15f);
                        wis.getTagCompound().setBoolean("has_shot", false);
                        StalminePacketHandler.INSTANCE.sendTo(new PacketShowReload(true), p);
                    } else {
                        StalminePacketHandler.INSTANCE.sendTo(new PacketShowEmpty(), p);
                    }
                }
            }
            else {
                boolean performShooting = (clip > 0 && !has_shot) || p.capabilities.isCreativeMode;
                if (performShooting) {
                    if (fr_timer <= 0f) {
                        StalminePacketHandler.INSTANCE.sendTo(new PacketShowShootMainhand(), p);

                        float modifiers = 1f;
                        if (!p.onGround) modifiers -= .25f;
                        if (p.isSprinting()) modifiers -= .25f;
                        if (p.isSneaking()) modifiers += .25f;
                        if (Minecraft.getMinecraft().gameSettings.keyBindUseItem.isKeyDown()) modifiers += .25f;
                        modifiers *= .2f + .8f * (1f - 1f * wis.getItemDamage() / wis.getMaxDamage());

                        if (wdm.getName().equals("w_bulldog")) {
                            EntityBulletGrenade be = new EntityBulletGrenade(
                                    world,
                                    p,
                                    1f
                            );
                            be.setStrength(4f);
                            float dmgModifier = ((ItemAmmo) Item.getByNameOrId(
                                    StalmineMod.MODID + ":" + (ammoType == 0 ? wdm.getAmmo() : wdm.getSecondaryAmmo())
                            )).dmgModifier;
                            be.setDamage(wdm.getDamage() * dmgModifier);
                            be.shoot(
                                    p,
                                    p.rotationPitch,
                                    p.rotationYaw,
                                    0f,
                                    wdm.getBulletVelocity(),
                                    10f / (wdm.getAccuracy() * modifiers)
                            );
                            world.spawnEntity(be);
                        }
                        else if (wdm.getName().equals("w_rpg7")) {
                            EntityBulletGrenadeOG7V be = new EntityBulletGrenadeOG7V(
                                    world,
                                    p,
                                    1f
                            );
                            be.setStrength(8f);
                            float dmgModifier = ((ItemAmmo) Item.getByNameOrId(
                                    StalmineMod.MODID + ":" + (ammoType == 0 ? wdm.getAmmo() : wdm.getSecondaryAmmo())
                            )).dmgModifier;
                            be.setDamage(wdm.getDamage() * dmgModifier);
                            be.shoot(
                                    p,
                                    p.rotationPitch,
                                    p.rotationYaw,
                                    0f,
                                    wdm.getBulletVelocity(),
                                    10f / (wdm.getAccuracy() * modifiers)
                            );
                            world.spawnEntity(be);
                        }
                        else {
                            for (int i = 0; i < (wdm.getType() == WeaponDataModel.WType.SHOTGUN ? 16 : 1); i++) {
                                EntityBullet be = new EntityBullet(
                                        world,
                                        p,
                                        1f
                                );
                                float dmgModifier = ((ItemAmmo) Item.getByNameOrId(
                                        StalmineMod.MODID + ":" + (ammoType == 0 ? wdm.getAmmo() : wdm.getSecondaryAmmo())
                                )).dmgModifier;
                                be.setDamage(wdm.getDamage() * dmgModifier);
                                be.shoot(
                                        p,
                                        p.rotationPitch,
                                        p.rotationYaw,
                                        0f,
                                        wdm.getBulletVelocity(),
                                        10f / (wdm.getAccuracy() * modifiers)
                                );
                                world.spawnEntity(be);
                            }
                        }
                        wis.getTagCompound().setFloat("fr_timer", 1200f / wdm.getFireRate() + fr_timer);
                        if (wdm.getType() != WeaponDataModel.WType.AUTO_RIFLE
                                && wdm.getType() != WeaponDataModel.WType.AUTO_RIFLE_GRENADE
                                && wdm.getType() != WeaponDataModel.WType.SNIPER_AUTO_RIFLE
                                && wdm.getType() != WeaponDataModel.WType.SNIPER_GRENADE_AUTO_RIFLE
                        ) {
                            wis.getTagCompound().setBoolean("has_shot", true);
                        }
                        if (!p.capabilities.isCreativeMode) {
                            wis.getTagCompound().setInteger("clip", clip - 1);
                            wis.damageItem(1, p);
                        }
                    }
                } else if (clip <= 0) {
                    int max_ammo = wdm.getMaxClipSize();
                    int cur_ammo = wis.getTagCompound().getInteger("clip");
                    float timer = wis.getTagCompound().getFloat("fr_timer");
                    int added = 0;
                    ItemStack ammo = ((Weapon) wis.getItem()).findAmmo(p, ammoType);
                    while (!ammo.isEmpty() && max_ammo > (cur_ammo + added) && timer <= 0f) {
                        int ammo_size = ammo.getMaxDamage() - ammo.getItemDamage();
                        int toAdd = Math.min(ammo_size, max_ammo - (cur_ammo + added));
                        added += toAdd;
                        ammo.damageItem(toAdd, p);
                        if (ammo.getItemDamage() == ammo.getMaxDamage()) ammo.damageItem(1, p);
                        ammo = ((Weapon) wis.getItem()).findAmmo(p, ammoType);
                    }
                    if (added > 0) {
                        wis.getTagCompound().setInteger("clip", cur_ammo + added);
                        if (wdm.getType() == WeaponDataModel.WType.SHOTGUN) {
                            String newReloadSound = wdm.getReloadSound()
                                    .substring(0, wdm.getReloadSound().lastIndexOf('_') + 1) + added;
                            wdm.setReloadSound(newReloadSound);
                            wdm.setReloadTime(wdm.getReloadTimeSG() * added);
                        }
                        wis.getTagCompound().setFloat("fr_timer", wdm.getReloadTime() * 20f);
                        wis.getTagCompound().setBoolean("has_shot", false);
                        StalminePacketHandler.INSTANCE.sendTo(new PacketShowReload(false), p);
                    } else {
                        StalminePacketHandler.INSTANCE.sendTo(new PacketShowEmpty(), p);
                    }
                }
            }
        }
    }
}
