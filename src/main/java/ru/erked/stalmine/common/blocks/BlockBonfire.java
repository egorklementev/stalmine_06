package ru.erked.stalmine.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.particle.StalmineParticles;
import ru.erked.stalmine.client.sound.StalmineSounds;
import ru.erked.stalmine.common.items.StalmineItems;
import ru.erked.stalmine.common.tile_entities.TEFireplace;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockBonfire extends Block implements ITileEntityProvider {

    private final boolean isBurning;
    private static final PropertyDirection FACING = PropertyDirection.create("facing");

    public BlockBonfire(boolean isBurning) {
        super(Material.IRON);
        this.isBurning = isBurning;
        setUnlocalizedName(StalmineMod.MODID + ".fireplace");
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        if (isBurning) {
            setLightLevel(1f);
        } else {
            setCreativeTab(StalmineMod.tabBlocks);
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

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
                                    EnumFacing side, float hitX, float hitY, float hitZ) {
        boolean playExtinguish = false;
        boolean playFireloop = false;

        if (!world.isRemote) {
            TEFireplace te = getTE(world, pos);
            if (isBurning &&
                    !player.inventory.getCurrentItem().isItemEqualIgnoreDurability(StalmineItems.itemLighter.getDefaultInstance()) &&
                    !player.inventory.getCurrentItem().isItemEqualIgnoreDurability(StalmineItems.itemErkedStick.getDefaultInstance()) &&
                            !te.isInfinite()
            ) {
                te.nullifyTimer();
                playExtinguish = true;
            } else if (player.inventory.getCurrentItem().isItemEqualIgnoreDurability(StalmineItems.itemLighter.getDefaultInstance())) {
                te.resetTimer();
                player.inventory.getCurrentItem().damageItem(1, player);
                playFireloop = true;
            } else if (player.inventory.getCurrentItem().isItemEqualIgnoreDurability(StalmineItems.itemErkedStick.getDefaultInstance())) {
                player.sendMessage(new TextComponentString(
                        TextFormatting.WHITE + "timer: " + (te.isInfinite() ? "inf " : "" ) + te.getTimer() + " sec " + (isBurning ? "[LIT]" : "[EXT]")));
            }
        }

        if (playExtinguish) {
            Minecraft.getMinecraft().world.playSound(
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    SoundEvents.BLOCK_FIRE_EXTINGUISH,
                    SoundCategory.BLOCKS,
                    0.4f + 0.3f * world.rand.nextFloat(),
                    0.8f + 0.2f * world.rand.nextFloat(),
                    false
            );
        }
        if (playFireloop) {
            Minecraft.getMinecraft().world.playSound(
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    StalmineSounds.pool.get("fireplace"),
                    SoundCategory.BLOCKS,
                    0.4f + 0.3f * world.rand.nextFloat(),
                    0.8f + 0.2f * world.rand.nextFloat(),
                    false
            );
        }
        return true;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (isBurning)
            entityIn.setFire(10);
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);
        TEFireplace te = getTE(worldIn, pos);
        if (this.isBurning) {
            if (te.hasTimerElapsed()) {
                worldIn.playSound(
                        pos.getX(),
                        pos.getY(),
                        pos.getZ(),
                        SoundEvents.BLOCK_FIRE_EXTINGUISH,
                        SoundCategory.BLOCKS,
                        0.4f + 0.3f * worldIn.rand.nextFloat(),
                        0.8f + 0.2f * worldIn.rand.nextFloat(),
                        false
                );
                worldIn.setBlockState(
                        pos,
                        StalmineBlocks.blockBonfire.getDefaultState().withProperty(FACING, stateIn.getValue(FACING)),
                        2
                );
            } else {
                te.updateTimer();
                if (rand.nextFloat() > 0.9f) {
                    Minecraft.getMinecraft().world.playSound(
                            pos.getX(),
                            pos.getY(),
                            pos.getZ(),
                            StalmineSounds.pool.get("fireplace"),
                            SoundCategory.BLOCKS,
                            0.4f + 0.3f * rand.nextFloat(),
                            0.8f + 0.2f * rand.nextFloat(),
                            false
                    );
                }
                for (int i = 0; i < 3; i++) {
                    worldIn.spawnParticle(
                            StalmineParticles.FIREPLACE_SMOKE,
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5,
                            0,
                            .075f,
                            0
                    );
                    worldIn.spawnParticle(
                            StalmineParticles.FIREPLACE_FIRE,
                            pos.getX() + 0.5,
                            pos.getY() + 0.15,
                            pos.getZ() + 0.5,
                            0,
                            .025f,
                            0
                    );
                }
            }
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.125, 0, 0.125, 0.875, 0.625, 0.875);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }

    public static EnumFacing getFacingFromEntity(BlockPos clickedBlock, EntityLivingBase entity) {
        return EnumFacing.getFacingFromVector(
                (float) (entity.posX - clickedBlock.getX()),
                (float) (entity.posY - clickedBlock.getY()),
                (float) (entity.posZ - clickedBlock.getZ()));
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(pos, placer)), 2);
        state.getBlock().setLightLevel(0f);

    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
                .withProperty(FACING, EnumFacing.getFront(meta & 7));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    private TEFireplace getTE(World world, BlockPos pos) {
        return (TEFireplace) world.getTileEntity(pos);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TEFireplace();
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

}
