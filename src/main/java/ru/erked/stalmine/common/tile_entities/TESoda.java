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

public class TESoda extends TileEntity  implements ITickable {

    private long timer;
    private long artTimer;

    public TESoda() {
        timer = 0L;
        artTimer = StalmineConfig.sodaArtTimer;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        timer = compound.getLong("timer");
        artTimer = compound.getLong("art_timer");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound nbt = super.writeToNBT(compound);
        nbt.setLong("timer", timer);
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

    public long getTimer() { return timer; }

    public boolean hasTimerElapsed() {
        return timer <= 0;
    }

    public void resetTimer() {
        timer = StalmineConfig.sodaTimer;
        markDirty();
        if (!getWorld().isRemote) {
            getWorld().notifyBlockUpdate(
                    getPos(),
                    getWorld().getBlockState(getPos()),
                    getWorld().getBlockState(getPos()),
                    Constants.BlockFlags.DEFAULT);
        }
    }

    @Override
    public void update() {
        artTimer--;
        if (!world.isRemote) {
            if (artTimer <= 0) {
                artTimer = StalmineConfig.sodaArtTimer + world.rand.nextInt(StalmineConfig.sodaArtTimer / 2);
                double chance = world.rand.nextDouble();
                double x = getPos().getX() + world.rand.nextDouble() * 1d - .5d;
                double y = getPos().getY() + world.rand.nextDouble() * 2d + 1d;
                double z = getPos().getZ() + world.rand.nextDouble() * 1d - .5d;
                if (chance > StalmineConfig.art_legendary) {
                    ArrayList<ItemArtifact> arts = new ArrayList<ItemArtifact>() {
                        {
                            add(StalmineItems.itemBubble);
                            add(StalmineItems.itemKolobok);
                        }
                    };
                    world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(arts.get(world.rand.nextInt(arts.size())))));
                }
                else if (chance > StalmineConfig.art_epic) {
                    ArrayList<ItemArtifact> arts = new ArrayList<ItemArtifact>() {
                        {
                            add(StalmineItems.itemSoul);
                            add(StalmineItems.itemMembrane);
                            add(StalmineItems.itemSeaUrchin);
                        }
                    };
                    world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(arts.get(world.rand.nextInt(arts.size())))));
                }
                else if (chance > StalmineConfig.art_rare) {
                    ArrayList<ItemArtifact> arts = new ArrayList<ItemArtifact>() {
                        {
                            add(StalmineItems.itemMeatpiece);
                        }
                    };
                    world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(arts.get(world.rand.nextInt(arts.size())))));
                }
                else if (chance > StalmineConfig.art_common) {
                    ArrayList<ItemArtifact> arts = new ArrayList<ItemArtifact>() {
                        {
                            add(StalmineItems.itemStoneblood);
                        }
                    };
                    world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(arts.get(world.rand.nextInt(arts.size())))));
                }
            }
        }
        if (timer >= 0) {
            timer--;
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
