package ru.erked.stalmine.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.erked.stalmine.client.sound.StalmineSounds;
import ru.erked.stalmine.common.weapons.Weapon;
import ru.erked.stalmine.common.weapons.WeaponDataModel;

public class PacketShowShootMainhand implements IMessage {

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public PacketShowShootMainhand() {
    }

    public static class Handler implements IMessageHandler<PacketShowShootMainhand, IMessage> {
        @Override
        public IMessage onMessage(PacketShowShootMainhand message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketShowShootMainhand message, MessageContext ctx) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP p = mc.player;
            ItemStack wis = p.getHeldItemMainhand();
            WeaponDataModel wdm = ((Weapon)wis.getItem()).model;
            p.playSound(
                    StalmineSounds.pool.get(wis.getTagCompound().getInteger("ammo_type") == 2 ?
                            "w_grenshoot" : wdm.getShootSound()),
                    1f, (float) (1.0 - 0.1 * p.world.rand.nextGaussian() -
                            (wis.getTagCompound().getInteger("ammo_type") == 1 ? 0.2 : 0))
            );
            if (!p.capabilities.isCreativeMode) {
                float modifiers = 1f;
                if (!p.onGround) modifiers += .25f;
                if (p.isSprinting()) modifiers += .25f;
                if (p.isSneaking()) modifiers -= .25f;
                if (mc.gameSettings.keyBindUseItem.isKeyDown()) modifiers -= .25f;
                float recoil = wdm.getRecoil() * modifiers;
                p.rotationYaw += ((float)p.world.rand.nextGaussian()) * recoil / 10f;
                p.rotationPitch -= ((float)p.world.rand.nextGaussian() + 1f) / 2f + recoil / 5f;
            }
        }
    }
}
