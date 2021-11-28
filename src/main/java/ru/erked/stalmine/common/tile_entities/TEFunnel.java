package ru.erked.stalmine.common.tile_entities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.StalmineConfig;
import ru.erked.stalmine.client.sound.StalmineSounds;
import ru.erked.stalmine.common.blocks.BlockFunnel;
import ru.erked.stalmine.common.items.ItemArtifact;
import ru.erked.stalmine.common.items.StalmineItems;

import java.util.ArrayList;
import java.util.List;

public class TEFunnel extends TileEntity  implements ITickable {

    private boolean soundStarted;
    private long timer;
    private long artTimer;

    public TEFunnel() {
        artTimer = StalmineConfig.funnelArtTimer;
        timer = StalmineConfig.funnelTimer;
        soundStarted = false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        timer = compound.getLong("timer");
        artTimer = compound.getLong("art_timer");
        soundStarted = compound.getBoolean("sound_started");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound nbt = super.writeToNBT(compound);
        nbt.setLong("timer", timer);
        nbt.setLong("art_timer", artTimer);
        nbt.setBoolean("sound_started", soundStarted);
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

    public boolean hasSoundStarted() {
        return soundStarted;
    }

    public boolean hasTimerElapsed() {
        return timer <= 0;
    }

    public void resetTimer() {
        timer = StalmineConfig.funnelTimer;
        soundStarted = false;
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
        BlockPos b1 = new BlockPos(getPos());
        b1 = b1.add(-2, -3, -2);
        BlockPos b2 = new BlockPos(getPos());
        b2 = b2.add(3, 3, 3);
        List<EntityLivingBase> entList = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(b1, b2));
        if (entList.isEmpty() && timer != StalmineConfig.funnelTimer) {
            resetTimer();
        }
        for (EntityLivingBase ent : entList) {
            if (ent != null && !(ent instanceof EntityPlayer && ((EntityPlayer) ent).isCreative())) {
                boolean hit = false;
                if (!world.isRemote && hasTimerElapsed()) {
                    ent.attackEntityFrom(StalmineMod.anomalyDS, 1000f);
                    hit = true;
                    resetTimer();
                }
                if (hit) {
                    ((BlockFunnel)world.getBlockState(getPos()).getBlock()).showBlast(Minecraft.getMinecraft().world, getPos());
                }
                ent.attackEntityFrom(StalmineMod.anomalyDS, 1f);
                double dx = (getPos().getX() + .5f - ent.posX);
                double dy = (getPos().getY() + .1f - ent.posY);
                double dz = (getPos().getZ() + .5f - ent.posZ);
                dx /= Math.abs(dx);
                //dy /= Math.abs(dy);
                dz /= Math.abs(dz);
                double vel = .05d;
                ent.motionX += vel * dx;
                ent.motionY += (vel * dy) * 2d;
                ent.motionZ += vel * dz;
                if (!entList.isEmpty() && !hasSoundStarted()) {
                    soundStarted = true;
                    if (world.isRemote) {
                        Minecraft.getMinecraft().world.playSound(
                                getPos().getX(),
                                getPos().getY(),
                                getPos().getZ(),
                                StalmineSounds.pool.get("funnel_blast"),
                                SoundCategory.BLOCKS,
                                1f,
                                1f,
                                true
                        );
                    }
                }
            }
        }
        if (!entList.isEmpty()) {
            timer--;
        }
        artTimer--;
        if (!world.isRemote) {
            if (artTimer <= 0) {
                artTimer = StalmineConfig.funnelArtTimer + world.rand.nextInt(StalmineConfig.funnelArtTimer / 2);
                double chance = world.rand.nextDouble();
                double x = getPos().getX() + world.rand.nextDouble() * 1d - .5d;
                double y = getPos().getY() + world.rand.nextDouble() * 2d + 1d;
                double z = getPos().getZ() + world.rand.nextDouble() * 1d - .5d;
                if (chance > StalmineConfig.art_legendary) {
                    ArrayList<ItemArtifact> arts = new ArrayList<ItemArtifact>() {
                        {
                            add(StalmineItems.itemMica);
                        }
                    };
                    world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(arts.get(world.rand.nextInt(arts.size())))));
                }
                else if (chance > StalmineConfig.art_epic) {
                    ArrayList<ItemArtifact> arts = new ArrayList<ItemArtifact>() {
                        {
                            add(StalmineItems.itemMembrane);
                        }
                    };
                    world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(arts.get(world.rand.nextInt(arts.size())))));
                }
                else if (chance > StalmineConfig.art_rare) {
                    ArrayList<ItemArtifact> arts = new ArrayList<ItemArtifact>() {
                        {
                            add(StalmineItems.itemGravi);
                            add(StalmineItems.itemThorn);
                        }
                    };
                    world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(arts.get(world.rand.nextInt(arts.size())))));
                }
                else if (chance > StalmineConfig.art_common) {
                    ArrayList<ItemArtifact> arts = new ArrayList<ItemArtifact>() {
                        {
                            add(StalmineItems.itemStoneflower);
                            add(StalmineItems.itemNightstar);
                            add(StalmineItems.itemMedusa);
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
