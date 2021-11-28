package ru.erked.stalmine.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.erked.stalmine.client.sound.StalmineSounds;
import ru.erked.stalmine.common.weapons.Weapon;
import ru.erked.stalmine.common.weapons.WeaponDataModel;

public class PacketShowEmpty implements IMessage {

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public PacketShowEmpty() {
    }

    public static class Handler implements IMessageHandler<PacketShowEmpty, IMessage> {
        @Override
        public IMessage onMessage(PacketShowEmpty message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketShowEmpty message, MessageContext ctx) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP p = mc.player;
            if (!p.getHeldItemMainhand().isEmpty() && p.getHeldItemMainhand().getItem() instanceof Weapon) {
                ItemStack wis = p.getHeldItemMainhand();
                if (!wis.getTagCompound().getBoolean("has_shot") &&
                        !mc.gameSettings.keyBindAttack.isKeyDown()) {
                    p.playSound(
                            StalmineSounds.pool.get("w_empty"),
                            1f,
                            1f - 0.1f * p.world.rand.nextFloat()
                    );
                }
            }
        }
    }
}
