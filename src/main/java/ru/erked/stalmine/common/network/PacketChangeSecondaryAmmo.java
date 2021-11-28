package ru.erked.stalmine.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.common.weapons.Weapon;
import ru.erked.stalmine.common.weapons.WeaponDataModel;

public class PacketChangeSecondaryAmmo implements IMessage {

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public PacketChangeSecondaryAmmo() {
    }

    public static class Handler implements IMessageHandler<PacketChangeSecondaryAmmo, IMessage> {
        @Override
        public IMessage onMessage(PacketChangeSecondaryAmmo message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketChangeSecondaryAmmo message, MessageContext ctx) {
            EntityPlayerMP p = ctx.getServerHandler().player;
            if (!p.getHeldItemMainhand().isEmpty() && p.getHeldItemMainhand().getItem() instanceof Weapon) {
                ItemStack wis = p.getHeldItemMainhand();
                WeaponDataModel wdm = ((Weapon)wis.getItem()).model;
                if (wis.hasTagCompound()) {
                    int curClip = wis.getTagCompound().getInteger("clip");
                    int curType = wis.getTagCompound().getInteger("ammo_type");
                    float fr_timer = wis.getTagCompound().getFloat("fr_timer");
                    boolean has_shot = wis.getTagCompound().getBoolean("has_shot");
                    boolean performChange = (!has_shot || p.capabilities.isCreativeMode) &&
                            !(wdm.getAmmo().equals(wdm.getSecondaryAmmo()));
                    if (performChange) {
                        if (fr_timer <= 0f) {
                            if (wdm.getName().equals("w_bulldog") || wdm.getType() == WeaponDataModel.WType.ROCKET) {
                                StalminePacketHandler.INSTANCE.sendTo(new PacketShowNoAmmoChange(), p);
                            } else {
                                //System.out.println("WHY THE FUCK IS THIS CALLED GOD DAMN IT!!!");
                                wis.getTagCompound().setInteger("ammo_type", curType > 0 ? 0 : 1);
                                curType = curType > 0 ? 0 : 1;
                                int ammoType = curType;
                                int max_ammo = wdm.getMaxClipSize();
                                int cur_ammo = wis.getTagCompound().getInteger("clip");
                                if (cur_ammo > 0) {
                                    ItemStack loadedAmmo = new ItemStack(Item.getByNameOrId(
                                            StalmineMod.MODID + ":" + (curType == 0 ? wdm.getSecondaryAmmo() : wdm.getAmmo())
                                    ));
                                    int fullIses = cur_ammo / loadedAmmo.getMaxDamage();
                                    int remIs = cur_ammo % loadedAmmo.getMaxDamage();
                                    if (fullIses > 0) {
                                        for (int i = 0; i < fullIses; i++) {
                                            if (!p.addItemStackToInventory(loadedAmmo.copy())) {
                                                p.world.spawnEntity(new EntityItem(p.world, p.posX, p.posY, p.posZ, loadedAmmo.copy()));
                                            }
                                        }
                                    }
                                    if (remIs > 0) {
                                        loadedAmmo.setItemDamage(loadedAmmo.getMaxDamage() - remIs);
                                        if (!p.addItemStackToInventory(loadedAmmo)) {
                                            p.world.spawnEntity(new EntityItem(p.world, p.posX, p.posY, p.posZ, loadedAmmo));
                                        }
                                    }
                                    cur_ammo = 0;
                                    wis.getTagCompound().setInteger("clip", cur_ammo);
                                }
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
    }
}
