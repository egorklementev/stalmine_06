package ru.erked.stalmine.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.particle.StalmineParticles;
import ru.erked.stalmine.client.sound.StalmineSounds;
import ru.erked.stalmine.common.armor.Armor;
import ru.erked.stalmine.common.effects.PotionAntiChem;
import ru.erked.stalmine.common.items.ItemArtifact;
import ru.erked.stalmine.common.tile_entities.TEKissel;
import ru.erked.stalmine.common.tile_entities.TESoda;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockKissel extends Block implements ITileEntityProvider {

    private static final AxisAlignedBB CARPET_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);

    public BlockKissel() {
        super(Material.CARPET);
        setCreativeTab(StalmineMod.tabBlocks);
        setUnlocalizedName(StalmineMod.MODID + ".kissel");
        setLightLevel(.5f);
        setTickRandomly(true);
    }

    @Override
    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
        return false;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return CARPET_AABB;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        if (side == EnumFacing.UP)
        {
            return true;
        }
        else
        {
            return blockAccess.getBlockState(pos.offset(side)).getBlock() == this || super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        }
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        boolean hit = false;
        if (!worldIn.isRemote) {
            hit = updateState(worldIn, pos, entityIn);
        }
        if (hit) {
            showBlast(Minecraft.getMinecraft().world, pos);
        }
    }

    public void showBlast(World world, BlockPos pos) {
        world.playSound(
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                StalmineSounds.pool.get("soda_blast"),
                SoundCategory.BLOCKS,
                1.0f,
                1.0f - world.rand.nextFloat() * .1f,
                true
        );
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);
        float param = .015f;
        float rnd = rand.nextFloat();
        if (rnd > 1f - param) {
            worldIn.playSound(
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    StalmineSounds.pool.get("soda_idle"),
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f - rand.nextFloat() * .1f,
                    true
            );
        }
        worldIn.spawnParticle(
                StalmineParticles.KISSEL,
                pos.getX() + .5f + (worldIn.rand.nextFloat() - .5f),
                pos.getY() + (worldIn.rand.nextFloat() * .1f),
                pos.getZ() + .5f + (worldIn.rand.nextFloat() - .5f),
                0d,
                0D,
                0D
        );
    }

    public boolean updateState(World w, BlockPos bp, Entity e) {
        TEKissel te = getTE(w, bp);
        if (te.hasTimerElapsed()) {
            if (e != null) {
                if (e instanceof EntityLivingBase) {
                    float dmg = 1f;
                    if (e instanceof EntityPlayer) {
                        EntityPlayer player = (EntityPlayer) e;
                        float totalAntichem = 0f;
                        // Effects
                        for (PotionEffect pe : player.getActivePotionEffects()) {
                            if (pe.getPotion() instanceof PotionAntiChem) {
                                totalAntichem += pe.getAmplifier() / 10f;
                            }
                        }
                        // Artifacts
                        for (int i = 0; i < 9; i++) {
                            ItemStack is = player.inventory.getStackInSlot(i);
                            if (!is.isEmpty() && is.getItem() instanceof ItemArtifact) {
                                totalAntichem += ((ItemArtifact) is.getItem()).model.getAntichem();
                                totalAntichem -= ((ItemArtifact) is.getItem()).model.getChem();
                            }
                        }
                        // Armor
                        for (int i = 0; i < 4; i++) {
                            ItemStack is = player.inventory.armorInventory.get(i);
                            if (!is.isEmpty() && is.getItem() instanceof Armor) {
                                totalAntichem += ((Armor) is.getItem()).model.getAntichem();
                            }
                        }

                        if (totalAntichem < 0f) totalAntichem = 0f;
                        if (totalAntichem > 1f) totalAntichem = 1f;
                        dmg = 1f - totalAntichem;
                    }
                    e.attackEntityFrom(StalmineMod.anomalyDS, ((EntityLivingBase) e).getMaxHealth() * .25f * dmg);
                    te.resetTimer();
                    return true;
                } else if ((e instanceof EntityItem && !(((EntityItem)e).getItem().getItem() instanceof ItemArtifact)) || e instanceof IProjectile) {
                    w.removeEntity(e);
                    te.resetTimer();
                    return true;
                }
            }
        }
        return false;
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

    public TEKissel getTE(World world, BlockPos pos) {
        return (TEKissel) world.getTileEntity(pos);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TEKissel();
    }
}

