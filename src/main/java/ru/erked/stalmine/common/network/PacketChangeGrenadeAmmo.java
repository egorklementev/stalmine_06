package ru.erked.stalmine.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.erked.stalmine.common.weapons.Weapon;
import ru.erked.stalmine.common.weapons.WeaponDataModel;

public class PacketChangeGrenadeAmmo implements IMessage {

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public PacketChangeGrenadeAmmo() {
    }

    public static class Handler implements IMessageHandler<PacketChangeGrenadeAmmo, IMessage> {
        @Override
        public IMessage onMessage(PacketChangeGrenadeAmmo message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketChangeGrenadeAmmo message, MessageContext ctx) {
            EntityPlayerMP p = ctx.getServerHandler().player;
            if (!p.getHeldItemMainhand().isEmpty() && p.getHeldItemMainhand().getItem() instanceof Weapon) {
                ItemStack wis = p.getHeldItemMainhand();
                WeaponDataModel wdm = ((Weapon)wis.getItem()).model;
                if (wis.hasTagCompound()) {
                    int curClip = wis.getTagCompound().getInteger("clip");
                    int curType = wis.getTagCompound().getInteger("ammo_type");
                    float fr_timer = wis.getTagCompound().getFloat("fr_timer");
                    boolean has_shot = wis.getTagCompound().getBoolean("has_shot");
                    boolean performChange = !has_shot || p.capabilities.isCreativeMode;
                    if (performChange) {
                        if (fr_timer <= 0f) {
                            if (wdm.getName().equals("w_bulldog") || wdm.getType() == WeaponDataModel.WType.ROCKET) {
                                StalminePacketHandler.INSTANCE.sendTo(new PacketShowNoAmmoChange(), p);
                            } else {
                                switch (curType) {
                                    case 0: {
                                        wis.getTagCompound().setInteger("ammo_type", 2);
                                        wis.getTagCompound().setInteger("prev_ammo_type", 0);
                                        StalminePacketHandler.INSTANCE.sendTo(new PacketShowGrenadeSwitch(), p);
                                        break;
                                    }
                                    case 1: {
                                        wis.getTagCompound().setInteger("ammo_type", 2);
                                        wis.getTagCompound().setInteger("prev_ammo_type", 1);
                                        StalminePacketHandler.INSTANCE.sendTo(new PacketShowGrenadeSwitch(), p);
                                        break;
                                    }
                                    case 2: {
                                        wis.getTagCompound().setInteger("ammo_type", wis.getTagCompound().getInteger("prev_ammo_type"));
                                        StalminePacketHandler.INSTANCE.sendTo(new PacketShowGrenadeSwitch(), p);
                                        break;
                                    }
                                    default: {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
