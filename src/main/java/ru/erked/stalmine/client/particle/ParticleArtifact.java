package ru.erked.stalmine.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleArtifact extends Particle {

    public ParticleArtifact(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 1, 1, 1);
        this.particleRed = (float) xSpeedIn;
        this.particleGreen = (float) ySpeedIn;
        this.particleBlue = (float) zSpeedIn;
        this.motionX = .075 * (worldIn.rand.nextDouble() * 2. - 1.);
        this.motionY = .075* (worldIn.rand.nextDouble() * 2. - 1.);
        this.motionZ = .075 * (worldIn.rand.nextDouble() * 2. - 1.);
        this.particleGravity = 0.0F;
        this.particleScale = this.rand.nextFloat() * this.rand.nextFloat() * 2.0F + .25f;
        this.particleMaxAge = 5 + worldIn.rand.nextInt(15);
        this.setParticleTextureIndex(34);
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setExpired();
        }

        this.move(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.7599999761581421D;
        this.motionY *= 0.7599999761581421D;
        this.motionZ *= 0.7599999761581421D;
        this.particleAlpha *= 0.9D;
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation( "stalmine:textures/particles/particles.png"));
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory
    {
        public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_)
        {
            return new ParticleArtifact(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        }
    }
}
