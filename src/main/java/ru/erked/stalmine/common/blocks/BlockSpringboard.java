package ru.erked.stalmine.common.blocks;

import net.minecraft.block.BlockAir;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.particle.StalmineParticles;
import ru.erked.stalmine.client.sound.StalmineSounds;
import ru.erked.stalmine.common.tile_entities.TEFunnel;
import ru.erked.stalmine.common.tile_entities.TESpringboard;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockSpringboard extends BlockAir implements ITileEntityProvider {

    public BlockSpringboard() {
        setCreativeTab(StalmineMod.tabBlocks);
        setUnlocalizedName(StalmineMod.MODID + ".springboard");
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
        world.spawnParticle(
                StalmineParticles.SPRINGBOARD_BLAST,
                pos.getX() + .5f,
                pos.getY() + 1f,
                pos.getZ() + .5f,
                0d,
                0d,
                0d
        );
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
        float param = .15f;
        float rnd = rand.nextFloat();
        if (rnd > 1f - param) {
            worldIn.playSound(
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    StalmineSounds.pool.get("springboard_idle"),
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f - rand.nextFloat() * .2f,
                    true
            );
        }
        for (int i = 0; i < 4; i++) {
            float angle1 = (float) (((2f * Math.PI) / 4f) * i) + rand.nextFloat();
            for (int j = 0; j < 4; j++) {
                float angle2 = (float) (((2f * Math.PI) / 4f) * j) + rand.nextFloat();
                worldIn.spawnParticle(
                        StalmineParticles.SPRINGBOARD_DUST,
                        pos.getX() + .5f + Math.cos(angle1) * Math.sin(angle2) * 1.25d,
                        pos.getY() + .5f + Math.sin(angle1) * Math.sin(angle2) * 1.25d,
                        pos.getZ() + .5f + Math.cos(angle2) * 1.25d,
                        Math.cos(angle1) * Math.sin(angle2) / 24f,
                        Math.sin(angle1) * Math.sin(angle2) / 24f,
                        Math.cos(angle2) / 24f
                );
            }
        }
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

    public TESpringboard getTE(World world, BlockPos pos) {
        return (TESpringboard) world.getTileEntity(pos);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TESpringboard();
    }
}

