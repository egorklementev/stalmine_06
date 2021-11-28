package ru.erked.stalmine.common.tile_entities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.util.Constants;
import ru.erked.stalmine.client.StalmineConfig;
import ru.erked.stalmine.client.sound.StalmineSounds;
import ru.erked.stalmine.common.blocks.BlockDeepfry;
import ru.erked.stalmine.common.items.ItemArtifact;
import ru.erked.stalmine.common.items.StalmineItems;

import java.util.ArrayList;

public class TEDeepfry extends TileEntity  implements ITickable {

    private long timer;
    private long artTimer;

    public TEDeepfry() {
        timer = 0L;
        artTimer = StalmineConfig.deepfryArtTimer;
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
        timer = StalmineConfig.deepfryTimer;
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
                artTimer = StalmineConfig.deepfryArtTimer + world.rand.nextInt(StalmineConfig.deepfryArtTimer / 2);
                double chance = world.rand.nextDouble();
                double x = getPos().getX() + world.rand.nextDouble() * 1d - .5d;
                double y = getPos().getY() + world.rand.nextDouble() * 2d + 1d;
                double z = getPos().getZ() + world.rand.nextDouble() * 1d - .5d;
                if (chance > StalmineConfig.art_legendary) {
                    ArrayList<ItemArtifact> arts = new ArrayList<ItemArtifact>() {
                        {
                            add(StalmineItems.itemFlame);
                        }
                    };
                    world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(arts.get(world.rand.nextInt(arts.size())))));
                }
                else if (chance > StalmineConfig.art_epic) {
                    ArrayList<ItemArtifact> arts = new ArrayList<ItemArtifact>() {
                        {
                            add(StalmineItems.itemEye);
                            add(StalmineItems.itemFireball);
                        }
                    };
                    world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(arts.get(world.rand.nextInt(arts.size())))));
                }
                else if (chance > StalmineConfig.art_rare) {
                    ArrayList<ItemArtifact> arts = new ArrayList<ItemArtifact>() {
                        {
                            add(StalmineItems.itemCrystal);
                        }
                    };
                    world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(arts.get(world.rand.nextInt(arts.size())))));
                }
                else if (chance > StalmineConfig.art_common) {
                    ArrayList<ItemArtifact> arts = new ArrayList<ItemArtifact>() {
                        {
                            add(StalmineItems.itemMombeads);
                            add(StalmineItems.itemDrops);
                        }
                    };
                    world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(arts.get(world.rand.nextInt(arts.size())))));
                }
            }
        }
        if (timer >= 0) {
            if (timer == StalmineConfig.deepfryTimer) {
                Minecraft.getMinecraft().world.playSound(
                        pos.getX(),
                        pos.getY(),
                        pos.getZ(),
                        StalmineSounds.pool.get("deepfry_blast"),
                        SoundCategory.BLOCKS,
                        1.0f,
                        1.0f,
                        true
                );
            }
            timer--;
            ((BlockDeepfry)getWorld().getBlockState(getPos()).getBlock()).showBlast(getWorld(), getPos());
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
