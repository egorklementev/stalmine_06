package ru.erked.stalmine.client.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBulletGrenadeFactory<T extends Entity> implements IRenderFactory<T> {

    @Override
    public Render<? super T> createRenderFor(RenderManager manager) {
        return new RenderBulletGrenade(manager);
    }
}
