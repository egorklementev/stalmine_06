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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import ru.erked.stalmine.common.armor.Armor;
import ru.erked.stalmine.common.items.ItemArtifact;
import ru.erked.stalmine.common.tile_entities.TEElectra;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockElectra extends BlockAir implements ITileEntityProvider {

    public BlockElectra() {
        setCreativeTab(StalmineMod.tabBlocks);
        setUnlocalizedName(StalmineMod.MODID + ".electra");
        setLightLevel(.5f);
        setTickRandomly(true);
    }

    @Override
    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
        return true;
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
                StalmineSounds.pool.get("electra_blast"),
                SoundCategory.BLOCKS,
                1.0f,
                1.0f - world.rand.nextFloat() * .2f,
                true
        );
        for (int i = 0; i < 24; i++) {
            float angle = (float) (world.rand.nextFloat() * 360f * Math.PI / 180f);
            for (int j = 0; j < 12 + world.rand.nextInt(5); j++) {
                world.spawnParticle(
                        StalmineParticles.ELECTRA_BLAST,
                        pos.getX() + .5f + (Math.sin(angle) * ((j * 1.25f + 1) / (1f + world.rand.nextFloat()))),
                        pos.getY() + 1.1f - (.05f * (j + 1)),
                        pos.getZ() + .5f + (Math.cos(angle) * ((j * 1.25f + 1) / (1f + world.rand.nextFloat()))),
                        j / 4.0,
                        0D,
                        0D
                );
            }
        }
        for (int i = 0; i < 12; i++) {
            float angle = (float) (world.rand.nextFloat() * 360f * Math.PI / 180f);
            for (int j = 0; j < 3 + world.rand.nextInt(3); j++) {
                world.spawnParticle(
                        StalmineParticles.ELECTRA_BLAST,
                        pos.getX() + .5f + (Math.sin(angle) * ((j * 1.25f + 1) / (1f + world.rand.nextFloat()))),
                        pos.getY() + .5f + (.5f * (j + 1)),
                        pos.getZ() + .5f + (Math.cos(angle) * ((j * 1.25f + 1) / (1f + world.rand.nextFloat()))),
                        j / 4.0,
                        0D,
                        0D
                );
            }
        }
        world.spawnParticle(
                StalmineParticles.ELECTRA_BLAST_CENTER,
                pos.getX() + .5f,
                pos.getY() + .5f,
                pos.getZ() + .5f,
                0D,
                0D,
                0D
        );
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);
        float param = .05f;
        float rnd = rand.nextFloat();
        if (rnd > 1f - param) {
            worldIn.playSound(
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    StalmineSounds.pool.get("electra_idle"),
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f - rand.nextFloat() * .2f,
                    true
            );
        }
        if (rnd > 1f - param * 1.5f) {
            worldIn.playSound(
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    StalmineSounds.pool.get("electra_hit"),
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f - rand.nextFloat() * .2f,
                    true
            );
        }
        for (int i = 0; i < 5; i++) {
            if (worldIn.rand.nextFloat() > .9f) {
                worldIn.spawnParticle(
                        StalmineParticles.ELECTRA_CENTER,
                        pos.getX() + .5f + (worldIn.rand.nextDouble() * 4D - 2D),
                        pos.getY() + .5f + (worldIn.rand.nextDouble() - .25D),
                        pos.getZ() + .5f + (worldIn.rand.nextDouble() * 4D - 2D),
                        0D,
                        0D,
                        0D
                );
            }
        }
    }

    public boolean updateState(World w, BlockPos bp, Entity e) {
        TEElectra te = getTE(w, bp);
        if (te.hasTimerElapsed()) {
            if (e != null) {
                if (e instanceof EntityLivingBase) {
                    float dmg = 1f;
                    if (e instanceof EntityPlayer) {
                        EntityPlayer p = (EntityPlayer) e;
                        float totalAntielectra = 0f;
                        // Artifacts
                        for (int i = 0; i < 9; i++) {
                            ItemStack is = p.inventory.getStackInSlot(i);
                            if (!is.isEmpty() && is.getItem() instanceof ItemArtifact) {
                                totalAntielectra += ((ItemArtifact) is.getItem()).model.getAntielectra();
                            }
                        }
                        // Armor
                        for (int i = 0; i < 4; i++) {
                            ItemStack is = p.inventory.armorInventory.get(i);
                            if (!is.isEmpty() && is.getItem() instanceof Armor) {
                                totalAntielectra += ((Armor) is.getItem()).model.getAntielectra();
                            }
                        }

                        if (totalAntielectra < 0f) totalAntielectra = 0f;
                        if (totalAntielectra > 1f) totalAntielectra = 1f;
                        dmg = 1f - totalAntielectra;
                    }
                    e.attackEntityFrom(StalmineMod.noArmorDS, (((EntityLivingBase) e).getMaxHealth() / 2f) * dmg);
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

    public TEElectra getTE(World world, BlockPos pos) {
        return (TEElectra) world.getTileEntity(pos);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TEElectra();
    }
}

