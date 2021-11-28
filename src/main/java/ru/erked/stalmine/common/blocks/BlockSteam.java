package ru.erked.stalmine.common.blocks;

import net.minecraft.block.BlockAir;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import ru.erked.stalmine.common.armor.Armor;
import ru.erked.stalmine.common.items.ItemArtifact;
import ru.erked.stalmine.common.tile_entities.TEDeepfry;
import ru.erked.stalmine.common.tile_entities.TESpringboard;
import ru.erked.stalmine.common.tile_entities.TESteam;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockSteam extends BlockAir implements ITileEntityProvider {

    public BlockSteam() {
        setCreativeTab(StalmineMod.tabBlocks);
        setUnlocalizedName(StalmineMod.MODID + ".steam");
        setTickRandomly(true);
    }

    @Override
    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
        return true;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (!worldIn.isRemote) {
            updateState(worldIn, pos, entityIn);
        }
    }

    public void showBlast(World world, BlockPos pos) {
        for (int i = 0; i < 12; i++) {
            world.spawnParticle(
                    StalmineParticles.STEAM_BLAST,
                    pos.getX() + .5f + (world.rand.nextFloat() * .5f - .25f),
                    pos.getY(),
                    pos.getZ() + .5f + (world.rand.nextFloat() * .5f - .25f),
                    0d,
                    0D,
                    0D
            );
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
                    StalmineSounds.pool.get("steam_idle"),
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f - rand.nextFloat() * .2f,
                    true
            );
        }
        rnd = rand.nextFloat();
        if (rnd > 1f - param) {
            worldIn.spawnParticle(
                    StalmineParticles.STEAM_BLAST,
                    pos.getX() + .5f + (worldIn.rand.nextFloat() * 3f - 1.5f),
                    pos.getY(),
                    pos.getZ() + .5f + (worldIn.rand.nextFloat() * 3f - 1.5f),
                    0d,
                    0D,
                    0D
            );
        }
    }

    public boolean updateState(World w, BlockPos bp, Entity e) {
        TESteam te = getTE(w, bp);
        if (e != null) {
            if (e instanceof EntityLivingBase) {
                float dmg = 1f;
                if (e instanceof EntityPlayer) {
                    EntityPlayer p = (EntityPlayer) e;
                    float totalAntiterm = 0f;
                    // Artifacts
                    for (int i = 0; i < 9; i++) {
                        ItemStack is = p.inventory.getStackInSlot(i);
                        if (!is.isEmpty() && is.getItem() instanceof ItemArtifact) {
                            totalAntiterm += ((ItemArtifact) is.getItem()).model.getAntiterm();
                            totalAntiterm -= ((ItemArtifact) is.getItem()).model.getTerm();
                        }
                    }
                    // Armor
                    for (int i = 0; i < 4; i++) {
                        ItemStack is = p.inventory.armorInventory.get(i);
                        if (!is.isEmpty() && is.getItem() instanceof Armor) {
                            totalAntiterm += ((Armor) is.getItem()).model.getAntiterm();
                        }
                    }

                    if (totalAntiterm < 0f) totalAntiterm = 0f;
                    if (totalAntiterm > 1f) totalAntiterm = 1f;
                    dmg -= totalAntiterm;
                }
                e.attackEntityFrom(StalmineMod.anomalyDS, dmg * ((EntityLivingBase) e).getMaxHealth() * .25f);
                if (te.hasTimerElapsed())
                    te.resetTimer();
                return true;
            } else if ((e instanceof EntityItem && !(((EntityItem)e).getItem().getItem() instanceof ItemArtifact)) || e instanceof IProjectile) {
                w.removeEntity(e);
                if (te.hasTimerElapsed())
                    te.resetTimer();
                return true;
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

    public TESteam getTE(World world, BlockPos pos) {
        return (TESteam) world.getTileEntity(pos);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TESteam();
    }
}

