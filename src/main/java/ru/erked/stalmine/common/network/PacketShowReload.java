package ru.erked.stalmine.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.erked.stalmine.client.sound.StalmineSounds;
import ru.erked.stalmine.common.weapons.Weapon;
import ru.erked.stalmine.common.weapons.WeaponDataModel;

public class PacketShowReload implements IMessage {

    private boolean isGrenade;

    @Override
    public void fromBytes(ByteBuf buf) {
        isGrenade = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(isGrenade);
    }

    public PacketShowReload() {}

    public PacketShowReload(boolean isGrenade) {
        this.isGrenade = isGrenade;
    }

    public static class Handler implements IMessageHandler<PacketShowReload, IMessage> {
        @Override
        public IMessage onMessage(PacketShowReload message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketShowReload message, MessageContext ctx) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP p = mc.player;
            if (!p.getHeldItemMainhand().isEmpty() && p.getHeldItemMainhand().getItem() instanceof Weapon) {
                ItemStack w = p.getHeldItemMainhand();
                WeaponDataModel wdm = ((Weapon)w.getItem()).model;
                p.playSound(
                        message.isGrenade ?
                                StalmineSounds.pool.get("w_grenload") :
                                StalmineSounds.pool.get(wdm.getReloadSound()),
                        1f,
                        1f - 0.1f * p.world.rand.nextFloat()
                );
                p.getCooldownTracker().setCooldown(w.getItem(), (int) (wdm.getReloadTime() * (message.isGrenade ? 15f : 20f)));
            }
        }
    }
}
