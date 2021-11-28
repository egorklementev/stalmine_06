package ru.erked.stalmine.common.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class StalminePacketHandler {

    private static int packetId = 0;
    public static SimpleNetworkWrapper INSTANCE = null;

    public StalminePacketHandler() {}

    public static int nextID() {
        return packetId++;
    }

    public static void registerMessages(String channelName) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
        registerMessages();
    }

    private static void registerMessages() {
        INSTANCE.registerMessage(PacketReload.Handler.class, PacketReload.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(PacketShootMainhand.Handler.class, PacketShootMainhand.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(PacketShowShootMainhand.Handler.class, PacketShowShootMainhand.class, nextID(), Side.CLIENT);
        INSTANCE.registerMessage(PacketShowReload.Handler.class, PacketShowReload.class, nextID(), Side.CLIENT);
        INSTANCE.registerMessage(PacketShowEmpty.Handler.class, PacketShowEmpty.class, nextID(), Side.CLIENT);
        INSTANCE.registerMessage(PacketGrenadeExplosion.Handler.class, PacketGrenadeExplosion.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(PacketChangeSecondaryAmmo.Handler.class, PacketChangeSecondaryAmmo.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(PacketChangeGrenadeAmmo.Handler.class, PacketChangeGrenadeAmmo.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(PacketShowGrenadeSwitch.Handler.class, PacketShowGrenadeSwitch.class, nextID(), Side.CLIENT);
        INSTANCE.registerMessage(PacketShowNoAmmoChange.Handler.class, PacketShowNoAmmoChange.class, nextID(), Side.CLIENT);
        INSTANCE.registerMessage(PacketPickupItem.Handler.class, PacketPickupItem.class, nextID(), Side.SERVER);
    }
}
