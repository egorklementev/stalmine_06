package ru.erked.stalmine.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import ru.erked.stalmine.common.containters.ArtBoxInventory;
import ru.erked.stalmine.common.containters.ContainerArtBox;
import ru.erked.stalmine.common.containters.ContainerBag;
import ru.erked.stalmine.common.containters.ContainerWeaponUpgradeTable;
import ru.erked.stalmine.common.gui.GUIArtBox;
import ru.erked.stalmine.common.gui.GUIBag;
import ru.erked.stalmine.common.gui.GUIWeaponUpgradeTable;
import ru.erked.stalmine.common.items.ItemArtBox;
import ru.erked.stalmine.common.tile_entities.TEBag;
import ru.erked.stalmine.common.tile_entities.TEWeaponUpgradeTable;

public class GUIProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TEBag) {
            return new ContainerBag(player.inventory, (TEBag) te);
        }
        if (te instanceof TEWeaponUpgradeTable) {
            return new ContainerWeaponUpgradeTable(player.inventory, (TEWeaponUpgradeTable) te);
        }
        if (ID == ItemArtBox.GUI_ID) {
            return new ContainerArtBox(player, player.inventory, new ArtBoxInventory(player.getHeldItem(EnumHand.MAIN_HAND), 1));
        }
        if (ID == ItemArtBox.GUI_ID + 1) {
            return new ContainerArtBox(player, player.inventory, new ArtBoxInventory(player.getHeldItem(EnumHand.MAIN_HAND), 2));
        }
        if (ID == ItemArtBox.GUI_ID + 2) {
            return new ContainerArtBox(player, player.inventory, new ArtBoxInventory(player.getHeldItem(EnumHand.MAIN_HAND), 3));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TEBag) {
            TEBag containerTileEntity = (TEBag) te;
            return new GUIBag(containerTileEntity, new ContainerBag(player.inventory, containerTileEntity));
        }
        if (te instanceof TEWeaponUpgradeTable) {
            TEWeaponUpgradeTable containerTileEntity = (TEWeaponUpgradeTable) te;
            return new GUIWeaponUpgradeTable(containerTileEntity, new ContainerWeaponUpgradeTable(player.inventory, containerTileEntity));
        }
        if (ID == ItemArtBox.GUI_ID) {
            return new GUIArtBox(
                    new ContainerArtBox(player, player.inventory, new ArtBoxInventory(player.getHeldItem(EnumHand.MAIN_HAND), 1)),
                    1
            );
        }
        if (ID == ItemArtBox.GUI_ID + 1) {
            return new GUIArtBox(
                    new ContainerArtBox(player, player.inventory, new ArtBoxInventory(player.getHeldItem(EnumHand.MAIN_HAND), 2)),
                    2
            );
        }
        if (ID == ItemArtBox.GUI_ID + 2) {
            return new GUIArtBox(
                    new ContainerArtBox(player, player.inventory, new ArtBoxInventory(player.getHeldItem(EnumHand.MAIN_HAND), 3)),
                    3
            );
        }
        return null;
    }

}
