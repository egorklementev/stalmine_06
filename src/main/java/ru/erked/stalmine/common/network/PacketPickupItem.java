package ru.erked.stalmine.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.erked.stalmine.proxy.CommonProxy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PacketPickupItem implements IMessage {

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public PacketPickupItem() { }

    public static class Handler implements IMessageHandler<PacketPickupItem, IMessage> {
        @Override
        public IMessage onMessage(PacketPickupItem message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketPickupItem message, MessageContext ctx) {
            EntityPlayerMP p = ctx.getServerHandler().player;
            World world = p.getEntityWorld();

            List<Entity> closestItems = world.loadedEntityList.stream()
                    .filter(ent -> ent instanceof EntityItem)
                    .filter(ent -> distanceToPlayer(ent, p) < 1.75)
                    .sorted(Comparator.comparingDouble(o -> distanceToPlayer(o, p)))
                    .collect(Collectors.toList());

            if(!closestItems.isEmpty()) {
                CommonProxy.allowIntemPickup = true;
                closestItems.get(0).onCollideWithPlayer(p);
            }
        }

        private double distanceToPlayer(Entity e, EntityPlayer p) {
            double radius = 0.3;
            float yaw = -p.rotationYawHead;
            float pitch = p.rotationPitch + 90;
            double yawRadians = (yaw / 180.0) * Math.PI;
            double pitchRadians = (pitch / 180.0) * Math.PI;
            BlockPos pPos = p.getPosition();
            double xAdd = radius * Math.sin(yawRadians) * Math.sin(pitchRadians);
            double yAdd = radius * Math.cos(pitchRadians) + p.getEyeHeight();
            double zAdd = radius * Math.cos(yawRadians) * Math.sin(pitchRadians);
            pPos = pPos.add(xAdd, yAdd, zAdd);
            return Math.sqrt(pPos.distanceSq(e.getPosition()));
        }
    }
}
