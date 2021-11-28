package ru.erked.stalmine.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleFunnelBlast extends Particle {

    public ParticleFunnelBlast(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0, 0, 0);
        this.particleRed = 1.0F;
        this.particleGreen = 1.0F;
        this.particleBlue = 1.0F;
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.particleGravity = 0.0F;
        this.particleScale = this.world.rand.nextFloat() + 6f;
        this.particleMaxAge = 10;
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        this.setParticleTextureIndex((int)(8f * this.particleAge / this.particleMaxAge) + 88);

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setExpired();
        }

        this.particleScale *= 1.1f;
        //this.move(this.motionX, this.motionY, this.motionZ);
        //this.motionX *= 0.7599999761581421D;
        //this.motionY *= 0.7599999761581421D;
        //this.motionZ *= 0.7599999761581421D;
        if (1f * this.particleAge / this.particleMaxAge > .5f) {
            this.particleAlpha *= 0.8D;
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
            return new ParticleFunnelBlast(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        }
    }
}
