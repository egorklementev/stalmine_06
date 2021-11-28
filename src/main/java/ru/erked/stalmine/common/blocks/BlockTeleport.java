package ru.erked.stalmine.common.blocks;

import net.minecraft.block.BlockAir;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.command.CommandBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.StalmineConfig;
import ru.erked.stalmine.client.loaders.obj.Vec3;
import ru.erked.stalmine.client.particle.StalmineParticles;
import ru.erked.stalmine.client.sound.StalmineSounds;
import ru.erked.stalmine.common.armor.Armor;
import ru.erked.stalmine.common.gui.GUIInGame;
import ru.erked.stalmine.common.items.ItemArtifact;
import ru.erked.stalmine.common.tile_entities.TEElectra;
import ru.erked.stalmine.common.tile_entities.TETeleport;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

public class BlockTeleport extends BlockAir implements ITileEntityProvider {

    public BlockTeleport() {
        setCreativeTab(StalmineMod.tabBlocks);
        setUnlocalizedName(StalmineMod.MODID + ".teleport");
        setLightLevel(.75f);
        //setTickRandomly(true);
    }

    @Override
    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
        return false;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        boolean hit = false;
        if (!worldIn.isRemote) {
            hit = updateState(worldIn, pos, entityIn);
            //getTE(worldIn, pos).saveCoordinates();
            getTE(worldIn, pos).printData();
        }
        if (hit) {
            showBlast(Minecraft.getMinecraft().world, pos, getTE(worldIn, pos).getTPCoords(), entityIn instanceof EntityPlayer);
        }
    }

    public void showBlast(World world, BlockPos pos, Vec3d tp, boolean isPlayer) {
        if (world != null) {
            world.playSound(
                    tp.x,
                    tp.y,
                    tp.z,
                    StalmineSounds.pool.get("tele_work"),
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f - world.rand.nextFloat() * .2f,
                    true
            );
            float yaw = (Minecraft.getMinecraft().player.rotationYawHead + 90f) % 360f;
            for (int i = 0; i < 8; i++) {
                world.spawnParticle(
                        StalmineParticles.TELEPORT,
                        tp.x + .5d + (world.rand.nextGaussian() / 16d),
                        tp.y + 1.25d + (world.rand.nextGaussian() / 16d + .0625d),
                        tp.z + .5d + (world.rand.nextGaussian() / 16d),
                        Math.cos(yaw / 180d * Math.PI) / 2,
                        i * 1.5d,
                        Math.sin(yaw / 180d * Math.PI) / 2
                );
            }
            Minecraft.getMinecraft().player.playSound(
                    StalmineSounds.pool.get("tele_work"),
                    1.0f,
                    1.0f - world.rand.nextFloat() * .2f
            );
            if (isPlayer) {
                GUIInGame.teleAlpha = StalmineConfig.teleTime;
            }
        }
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);
        float param = .075f;
        float rnd = rand.nextFloat();
        if (rnd > 1f - param) {
            worldIn.playSound(
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    StalmineSounds.pool.get("tele_idle"),
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f - rand.nextFloat() * .2f,
                    true
            );
        }
        float yaw = (Minecraft.getMinecraft().player.rotationYawHead + 90f) % 360f;
        worldIn.spawnParticle(
                StalmineParticles.TELEPORT,
                pos.getX() + .5d + (rand.nextGaussian() / 16d),
                pos.getY() + 1.25d + (rand.nextGaussian() / 16d + .0625d),
                pos.getZ() + .5d + (rand.nextGaussian() / 16d),
                Math.cos(yaw / 180d * Math.PI) / 2,
                0d,
                Math.sin(yaw / 180d * Math.PI) / 2
        );
    }

    public boolean updateState(World w, BlockPos bp, Entity e) {
        if (e != null && e.world != null) {
            teleportEntityToCoordinates(w, bp, e);
            return true;
        }
        return false;
    }

    private void teleportEntityToCoordinates(World w, BlockPos bp, Entity teleportingEntity)
    {
        if (teleportingEntity instanceof EntityPlayerMP)
        {
            Set<SPacketPlayerPosLook.EnumFlags> set = EnumSet.<SPacketPlayerPosLook.EnumFlags>noneOf(SPacketPlayerPosLook.EnumFlags.class);

/*
            if (StalmineConfig.teleX.isRelative())
            {
                set.add(SPacketPlayerPosLook.EnumFlags.X);
            }

            if (StalmineConfig.teleY.isRelative())
            {
                set.add(SPacketPlayerPosLook.EnumFlags.Y);
            }

            if (StalmineConfig.teleZ.isRelative())
            {
                set.add(SPacketPlayerPosLook.EnumFlags.Z);
            }
*/

            float f = teleportingEntity.rotationYaw;
            float f1 = teleportingEntity.rotationPitch;

            Vec3d tp = getTE(w, bp).getTPCoords();
            teleportingEntity.dismountRidingEntity();
            teleportingEntity.onGround = false;
            teleportingEntity.velocityChanged = true;
            ((EntityPlayerMP)teleportingEntity).connection.player.posX = tp.x;
            ((EntityPlayerMP)teleportingEntity).connection.player.posY = tp.y;
            ((EntityPlayerMP)teleportingEntity).connection.player.posZ = tp.z;
            ((EntityPlayerMP)teleportingEntity).connection.processPlayer(
                    new CPacketPlayer.Position(tp.x, tp.y, tp.z, teleportingEntity.onGround)
            );
            ((EntityPlayerMP)teleportingEntity).connection.setPlayerLocation(
                    tp.x,
                    tp.y,
                    tp.z,
                    f, f1, set);
            teleportingEntity.setRotationYawHead(f);
        }
        else
        {
            float f2 = teleportingEntity.rotationYaw;
            float f3 = teleportingEntity.rotationPitch;
            f3 = MathHelper.clamp(f3, -90.0F, 90.0F);
            Vec3d tp = getTE(w, bp).getTPCoords();
            teleportingEntity.setLocationAndAngles(
                    tp.x,
                    tp.y,
                    tp.z,
                    f2, f3);
            teleportingEntity.setRotationYawHead(f2);
        }

        if (!(teleportingEntity instanceof EntityLivingBase) || !((EntityLivingBase)teleportingEntity).isElytraFlying())
        {
            teleportingEntity.motionY = 0.0D;
            teleportingEntity.onGround = true;
        }
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        //getTE(world, pos).saveCoordinates();
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(
                Item.getItemFromBlock(this),
                0,
                new ModelResourceLocation(getRegistryName(), "inventory")
        );
    }

    public TETeleport getTE(World world, BlockPos pos) {
        return (TETeleport) world.getTileEntity(pos);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TETeleport();
    }
}

