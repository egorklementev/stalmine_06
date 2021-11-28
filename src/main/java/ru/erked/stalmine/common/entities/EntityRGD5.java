package ru.erked.stalmine.common.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import ru.erked.stalmine.common.network.PacketGrenadeExplosion;
import ru.erked.stalmine.common.network.StalminePacketHandler;

public class EntityRGD5 extends EntityThrowable
{
    private int timer = 60;
    private final float strength = 2;
    private float energy = .35f;

    public EntityRGD5(World worldIn) {
        super(worldIn);
    }

    public EntityRGD5(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityRGD5(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    protected void onImpact(RayTraceResult result)
    {
        if (this.isEntityAlive()) {
            if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
                Vec3d normal;
                switch (result.sideHit) {
                    case DOWN:
                        normal = new Vec3d(0d, -1d, 0d);
                        break;
                    case UP:
                        normal = new Vec3d(0d, 1d, 0d);
                        break;
                    case NORTH:
                        normal = new Vec3d(0d, 0d, 1d);
                        break;
                    case SOUTH:
                        normal = new Vec3d(0d, 0d, -1d);
                        break;
                    case WEST:
                        normal = new Vec3d(-1d, 0d, 0d);
                        break;
                    case EAST:
                        normal = new Vec3d(1d, 0d, 0d);
                        break;
                    default:
                        normal = new Vec3d(0d, 0d, 0d);
                        break;
                }
                Vec3d motion = new Vec3d(motionX, motionY, motionZ);
                Vec3d refl = motion.subtract(
                        normal.x * 2d * motion.dotProduct(normal),
                        normal.y * 2d * motion.dotProduct(normal),
                        normal.z * 2d * motion.dotProduct(normal)
                );
                motionX = refl.x * energy;
                motionY = refl.y * energy * energy;
                motionZ = refl.z * energy;
                energy *= .98f;
            }
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        timer--;
        if (!world.isRemote && timer <= 0) {
            StalminePacketHandler.INSTANCE.sendToServer(new PacketGrenadeExplosion(getPosition(), strength));
            this.setDead();
        }
    }
}
