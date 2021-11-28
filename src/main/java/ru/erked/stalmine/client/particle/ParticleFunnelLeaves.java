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
public class ParticleFunnelLeaves extends Particle {

    float angle;

    public ParticleFunnelLeaves(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0, 0, 0);
        angle = (float) xSpeedIn;
        this.particleRed = 1.0F;
        this.particleGreen = 1.0F;
        this.particleBlue = 1.0F;
        this.particleAlpha = .01f;
        this.motionX = 0d;
        this.motionY = 0d;
        this.motionZ = 0d;
        this.particleGravity = 0f;
        this.particleScale = rand.nextFloat() + .25f;
        this.particleMaxAge = 40 + rand.nextInt(20);
        this.setParticleTextureIndex((rand.nextFloat() > .5f ? 226 : 242) + rand.nextInt(2) - (rand.nextFloat() > 7f ? 2 : 0));
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

        //this.particleScale *= 1.1f;
        this.move(this.motionX, this.motionY, this.motionZ);
        this.motionX = Math.cos(particleAge / 5f) / 5f;
        this.motionY = Math.sin(particleAge / 10f) / 20f;
        this.motionZ = Math.sin(particleAge / 5f) / 5f;

        if (1f * this.particleAge / this.particleMaxAge < .15f) {
            this.particleAlpha *= 3D;
            if (this.particleAlpha > 1f)
                this.particleAlpha = 1f;
        } else if (1f * this.particleAge / this.particleMaxAge > .75f) {
            this.particleAlpha *= 0.9D;
        }
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
            return new ParticleFunnelLeaves(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        }
    }
}
