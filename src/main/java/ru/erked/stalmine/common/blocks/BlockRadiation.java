package ru.erked.stalmine.common.blocks;

import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.sound.StalmineSounds;
import ru.erked.stalmine.common.effects.StalminePotions;

import java.util.Random;

public class BlockRadiation extends BlockAir {

    private int type;

    public BlockRadiation(int type) {
        this.type = type;
        setCreativeTab(StalmineMod.tabBlocks);
        setUnlocalizedName(StalmineMod.MODID + ".radiation_" + type);
    }

    @Override
    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
        return false;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (entityIn instanceof EntityLivingBase) {
            if (!((EntityLivingBase) entityIn).isPotionActive(StalminePotions.RADIATION) ||
                    ((EntityLivingBase) entityIn).getActivePotionEffect(StalminePotions.RADIATION).getDuration() < 210 ||
                    ((EntityLivingBase) entityIn).getActivePotionEffect(StalminePotions.RADIATION).getAmplifier() < type
            ) {
                ((EntityLivingBase) entityIn).addPotionEffect(
                        new PotionEffect(
                                StalminePotions.RADIATION,
                                300,
                                type,
                                false,
                                true
                        )
                );
            }
        }
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);
        float param = (type * type + 1f) * .001f;
        if (rand.nextFloat() > 1f - param) {
            worldIn.playSound(
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    StalmineSounds.pool.get("geiger"),
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
}

