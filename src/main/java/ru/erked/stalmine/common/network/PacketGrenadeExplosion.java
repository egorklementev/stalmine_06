package ru.erked.stalmine.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketGrenadeExplosion implements IMessage {

    private BlockPos position;
    private float strength;

    @Override
    public void fromBytes(ByteBuf buf) {
        position = BlockPos.fromLong(buf.readLong());
        strength = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(position.toLong());
        buf.writeFloat(strength);
    }

    public PacketGrenadeExplosion() {}

    public PacketGrenadeExplosion(BlockPos blockPos, float strength) {
        this.position = blockPos;
        this.strength = strength;
    }

    public static class Handler implements IMessageHandler<PacketGrenadeExplosion, IMessage> {
        @Override
        public IMessage onMessage(PacketGrenadeExplosion message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketGrenadeExplosion message, MessageContext ctx) {
            EntityPlayerMP p = ctx.getServerHandler().player;
            World world = p.world;
            if (!world.isRemote) {
                world.createExplosion(p, message.position.getX(), message.position.getY(), message.position.getZ(), message.strength, true);
            }
        }
    }
}
