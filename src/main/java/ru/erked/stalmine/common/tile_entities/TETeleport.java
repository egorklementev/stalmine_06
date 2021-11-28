package ru.erked.stalmine.common.tile_entities;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.Vec3d;
import ru.erked.stalmine.client.StalmineConfig;

public class TETeleport extends TileEntity implements ITickable{

    private double tpX;
    private double tpY;
    private double tpZ;

    public TETeleport() {
        tpX = StalmineConfig.teleX.getAmount();
        tpY = StalmineConfig.teleY.getAmount();
        tpZ = StalmineConfig.teleZ.getAmount();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        tpX = compound.getDouble("tp_x");
        tpY = compound.getDouble("tp_y");
        tpZ = compound.getDouble("tp_z");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setDouble("tp_x", tpX);
        compound.setDouble("tp_y", tpY);
        compound.setDouble("tp_z", tpZ);
        return compound;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
    }

    public Vec3d getTPCoords() {
        return new Vec3d(tpX, tpY, tpZ);
    }

    public void printData() {
        System.out.println("Player has been tp'ed to " + getTPCoords().toString());
    }

    public void saveCoordinates() {
        markDirty();
        if (world != null) {
            IBlockState state = world.getBlockState(getPos());
            world.notifyBlockUpdate(getPos(), state, state, 3);
        }
    }

    @Override
    public void update() {
        if (!getWorld().isRemote) {
            if (getWorld().getTotalWorldTime() % 100 == 0) {
                saveCoordinates();
            }
        }
    }
}
