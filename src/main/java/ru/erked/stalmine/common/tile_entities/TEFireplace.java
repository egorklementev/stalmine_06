package ru.erked.stalmine.common.tile_entities;

import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import ru.erked.stalmine.client.StalmineConfig;
import ru.erked.stalmine.common.blocks.StalmineBlocks;

public class TEFireplace extends TileEntity implements ITickable {

    private boolean isInfinite;
    private long timer;
    private static final PropertyDirection FACING = PropertyDirection.create("facing");

    public TEFireplace() {
        timer = 0L;
        isInfinite = StalmineConfig.placeInifiniteFireplaces;
    }

    public void resetTimer() {
        timer = StalmineConfig.fireplaceBurnDuration;
        //isInfinite = StalmineConfig.placeInifiniteFireplaces;
        markDirty();
        if (world != null) {
            IBlockState state = world.getBlockState(getPos());
            Block bLit;
            if (getBlockType() == StalmineBlocks.blockFireplace ||
                    getBlockType() == StalmineBlocks.blockFireplaceLit) {
                bLit = StalmineBlocks.blockFireplaceLit;
            } else {
                bLit = StalmineBlocks.blockBonfireLit;
            }
            world.setBlockState(
                    getPos(),
                    bLit.getDefaultState().withProperty(FACING, state.getValue(FACING)),
                    2
            );
            state = world.getBlockState(getPos());
            world.notifyBlockUpdate(getPos(), state, state, 3);
        }
    }

    public void nullifyTimer() {
        timer = 0L;
        markDirty();
        if (world != null) {
            IBlockState state = world.getBlockState(getPos());
            Block bExt;
            if (getBlockType() == StalmineBlocks.blockFireplace ||
                    getBlockType() == StalmineBlocks.blockFireplaceLit) {
                bExt = StalmineBlocks.blockFireplace;
            } else {
                bExt = StalmineBlocks.blockBonfire;
            }
            world.setBlockState(
                    getPos(),
                    bExt.getDefaultState().withProperty(FACING, state.getValue(FACING)),
                    2
            );
            state = world.getBlockState(getPos());
            world.notifyBlockUpdate(getPos(), state, state, 3);
        }
    }

    public void updateTimer() {
        if (timer > 0) {
            timer--;
            if (timer == 0) {
                nullifyTimer();
            } else {
                markDirty();
                if (world != null) {
                    IBlockState state = world.getBlockState(getPos());
                    world.notifyBlockUpdate(getPos(), state, state, 3);
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        timer = compound.getLong("timer");
        isInfinite = compound.getBoolean("is_infinite");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setLong("timer", timer);
        compound.setBoolean("is_infinite", isInfinite);
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

    public long getTimer() { return timer; }

    public boolean hasTimerElapsed() {
        return timer <= 0;
    }

    public boolean isInfinite() {
        return isInfinite;
    }

    @Override
    public void update() {
        if (!getWorld().isRemote) {
            if (!isInfinite && getWorld().getTotalWorldTime() % 20 == 0) {
                updateTimer();
            }
        }
    }
}
