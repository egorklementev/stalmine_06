package ru.erked.stalmine.common.blocks;

import net.minecraft.block.BlockAir;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.particle.StalmineParticles;
import ru.erked.stalmine.client.sound.StalmineSounds;
import ru.erked.stalmine.common.tile_entities.TEElectra;
import ru.erked.stalmine.common.tile_entities.TEFunnel;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockFunnel extends BlockAir implements ITileEntityProvider {

    public BlockFunnel() {
        setCreativeTab(StalmineMod.tabBlocks);
        setUnlocalizedName(StalmineMod.MODID + ".funnel");
        setTickRandomly(true);
    }

    @Override
    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
        return true;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
    }

    public void showBlast(World world, BlockPos pos) {
        if (world.isRemote) {
            world.spawnParticle(
                    StalmineParticles.FUNNEL_BLAST,
                    pos.getX() + .5f,
                    pos.getY() + 1f,
                    pos.getZ() + .5f,
                    0d,
                    0d,
                    0d
            );
            for (int i = 0; i < 16; i++) {
                float angle = (float) (world.rand.nextFloat() * 360f * Math.PI / 180f);
                world.spawnParticle(
                        StalmineParticles.FUNNEL_FLESH,
                        pos.getX() + .5f + Math.cos(angle) * .25f,
                        pos.getY() + 1f,
                        pos.getZ() + .5f + Math.sin(angle) * .25f,
                        Math.cos(angle) / (1f + 1f * world.rand.nextFloat()),
                        .1D + .1d * world.rand.nextFloat(),
                        Math.sin(angle) / (1f + 1f * world.rand.nextFloat())
                );
            }
        }
    }

    public void showHit(World worldIn, BlockPos pos) {
        if (worldIn.isRemote) {
            worldIn.playSound(
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    StalmineSounds.pool.get("funnel_hit"),
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f - worldIn.rand.nextFloat() * .2f,
                    true
            );
            for (int i = 0; i < 8; i++) {
                float angle1 = (float) (((2f * Math.PI) / 8f) * i) + worldIn.rand.nextFloat();
                for (int j = 0; j < 8; j++) {
                    float angle2 = (float) (((2f * Math.PI) / 8f) * j) + worldIn.rand.nextFloat();
                    worldIn.spawnParticle(
                            StalmineParticles.FUNNEL_DUST,
                            pos.getX() + .5f + Math.cos(angle1) * Math.sin(angle2) * 1.25d,
                            pos.getY() + .5f + Math.sin(angle1) * Math.sin(angle2) * 1.25d,
                            pos.getZ() + .5f + Math.cos(angle2) * 1.25d,
                            Math.cos(angle1) * Math.sin(angle2) / 8f,
                            Math.sin(angle1) * Math.sin(angle2) / 8f,
                            Math.cos(angle2) / 8f
                    );
                }
            }
        }
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);
        float param = .1f;
        float rnd = rand.nextFloat();
        if (rnd > 1f - param) {
            worldIn.playSound(
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    StalmineSounds.pool.get("funnel_idle"),
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f - rand.nextFloat() * .2f,
                    true
            );
        }
        for (int i = 0; i < 6; i++) {
            float angle = (float) (rand.nextFloat() * 2f * Math.PI);
            worldIn.spawnParticle(
                    StalmineParticles.FUNNEL_LEAVES,
                    pos.getX() + Math.cos(angle) * 2d + .5f,
                    pos.getY() + .25f,
                    pos.getZ() - Math.sin(angle) * 2d - .5f,
                    angle * 1d,
                    0D,
                    0D
            );
        }
        rnd = rand.nextFloat();
        if (rnd > 1f - param) {
            worldIn.playSound(
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    StalmineSounds.pool.get("funnel_hit"),
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f - rand.nextFloat() * .2f,
                    true
            );
            for (int i = 0; i < 8; i++) {
                float angle1 = (float) (((2f * Math.PI) / 8f) * i) + rand.nextFloat();
                for (int j = 0; j < 8; j++) {
                    float angle2 = (float) (((2f * Math.PI) / 8f) * j) + rand.nextFloat();
                    worldIn.spawnParticle(
                            StalmineParticles.FUNNEL_DUST,
                            pos.getX() + .5f + Math.cos(angle1) * Math.sin(angle2) * 1.25d,
                            pos.getY() + .5f + Math.sin(angle1) * Math.sin(angle2) * 1.25d,
                            pos.getZ() + .5f + Math.cos(angle2) * 1.25d,
                            Math.cos(angle1) * Math.sin(angle2) / 8f,
                            Math.sin(angle1) * Math.sin(angle2) / 8f,
                            Math.cos(angle2) / 8f
                    );
                }
            }
        }
    }

    public int updateState(World w, BlockPos bp, Entity e) {
        return 0;
    }

    @Override
    public int tickRate(World worldIn) {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(
                Item.getItemFromBlock(this),
                0,
                new ModelResourceLocation(getRegistryName(), "inventory")
        );
    }

    public TEFunnel getTE(World world, BlockPos pos) {
        return (TEFunnel) world.getTileEntity(pos);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TEFunnel();
    }
}

