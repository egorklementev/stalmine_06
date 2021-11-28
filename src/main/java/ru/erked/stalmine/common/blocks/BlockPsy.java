package ru.erked.stalmine.common.blocks;

import net.minecraft.block.BlockAir;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.sound.StalmineSounds;
import ru.erked.stalmine.common.effects.StalminePotions;
import ru.erked.stalmine.common.tile_entities.TEPsy;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockPsy extends BlockAir /*implements ITileEntityProvider*/ {

    private int type;

    public BlockPsy(int type) {
        this.type = type;
        setCreativeTab(StalmineMod.tabBlocks);
        setUnlocalizedName(StalmineMod.MODID + ".psy_" + type);
    }

    @Override
    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
        return false;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (entityIn instanceof EntityLivingBase) {
            if (!((EntityLivingBase) entityIn).isPotionActive(StalminePotions.PSY) ||
                    ((EntityLivingBase) entityIn).getActivePotionEffect(StalminePotions.PSY).getDuration() < 210 ||
                    ((EntityLivingBase) entityIn).getActivePotionEffect(StalminePotions.PSY).getAmplifier() < type
            ) {
                ((EntityLivingBase) entityIn).addPotionEffect(
                        new PotionEffect(
                                StalminePotions.PSY,
                                300,
                                type,
                                false,
                                true
                        )
                );
            }
            if (!((EntityLivingBase) entityIn).isPotionActive(Potion.getPotionById(9)) ||
                    ((EntityLivingBase) entityIn).getActivePotionEffect(Potion.getPotionById(9)).getDuration() < 210 ||
                    ((EntityLivingBase) entityIn).getActivePotionEffect(Potion.getPotionById(9)).getAmplifier() < type
            ) {
                if (worldIn.getTotalWorldTime() % 100 == 0 && worldIn.rand.nextFloat() > .95f - type * .05f) {
                    ((EntityLivingBase) entityIn).addPotionEffect(
                            new PotionEffect(
                                    Potion.getPotionById(9),
                                    75 + type * 25,
                                    type,
                                    false,
                                    true
                            )
                    );
                }
            }
        }
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);
        float param = (type + 1f) * .00005f;
        float rnd = rand.nextFloat();
        if (rnd > 1f - param) {
            worldIn.playSound(
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    StalmineSounds.pool.get("psy"),
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f,
                    true
            );
        }
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(
                Item.getItemFromBlock(this),
                0,
                new ModelResourceLocation(getRegistryName(), "inventory")
        );
    }

    /*@Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TEPsy();
    }*/
}

