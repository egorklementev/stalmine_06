package ru.erked.stalmine.common.tile_entities;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.util.Constants;
import ru.erked.stalmine.client.StalmineConfig;
import ru.erked.stalmine.common.items.ItemArtifact;
import ru.erked.stalmine.common.items.StalmineItems;

import java.util.ArrayList;

public class TEPsy extends TileEntity  implements ITickable {

    private long artTimer;

    public TEPsy() {
        artTimer = StalmineConfig.psyArtTimer;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        artTimer = compound.getLong("art_timer");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound nbt = super.writeToNBT(compound);
        nbt.setLong("art_timer", artTimer);
        return nbt;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.getPos(), -1, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.handleUpdateTag(packet.getNbtCompound());
    }

    @Override
    public void update() {
        artTimer--;
        if (!world.isRemote) {
            if (artTimer <= 0) {
                artTimer = StalmineConfig.psyArtTimer + world.rand.nextInt(StalmineConfig.psyArtTimer / 2);
                double chance = world.rand.nextDouble();
                double x = getPos().getX() + world.rand.nextDouble() * 6d - 3d;
                double y = getPos().getY() + world.rand.nextDouble() * 2d;
                double z = getPos().getZ() + world.rand.nextDouble() * 6d - 3d;
                if (chance > StalmineConfig.art_legendary) {
                    ArrayList<ItemArtifact> arts = new ArrayList<ItemArtifact>() {
                        {
                            add(StalmineItems.itemMoonlight);
                        }
                    };
                    world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(arts.get(world.rand.nextInt(arts.size())))));
                }
                if (chance > StalmineConfig.art_epic) {
                    ArrayList<ItemArtifact> arts = new ArrayList<ItemArtifact>() {
                        {
                            add(StalmineItems.itemStoneflower);
                        }
                    };
                    world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(arts.get(world.rand.nextInt(arts.size())))));
                }
            }
        }
        markDirty();
        if (!getWorld().isRemote) {
            getWorld().notifyBlockUpdate(
                    getPos(),
                    getWorld().getBlockState(getPos()),
                    getWorld().getBlockState(getPos()),
                    Constants.BlockFlags.DEFAULT);
        }
    }
}
