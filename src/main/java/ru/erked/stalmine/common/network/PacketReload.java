package ru.erked.stalmine.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.erked.stalmine.client.sound.StalmineSounds;
import ru.erked.stalmine.common.weapons.Weapon;
import ru.erked.stalmine.common.weapons.WeaponDataModel;

public class PacketReload implements IMessage {

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public PacketReload() {
    }

    public static class Handler implements IMessageHandler<PacketReload, IMessage> {
        @Override
        public IMessage onMessage(PacketReload message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketReload message, MessageContext ctx) {
            EntityPlayerMP p = ctx.getServerHandler().player;
            if (!p.getHeldItemMainhand().isEmpty() && p.getHeldItemMainhand().getItem() instanceof Weapon) {
                ItemStack wis = p.getHeldItemMainhand();
                WeaponDataModel wdm = ((Weapon)wis.getItem()).model;
                if (wis.hasTagCompound()) {
                    int ammoType = wis.getTagCompound().getInteger("ammo_type");
                    if (ammoType == 2) {
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
                    else {
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
}
