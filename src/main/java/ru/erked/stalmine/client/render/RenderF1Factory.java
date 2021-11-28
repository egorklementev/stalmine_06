package ru.erked.stalmine.client.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderF1Factory<T extends Entity> implements IRenderFactory<T> {

    public RenderF1Factory() {
    }

    @Override
    public Render<? super T> createRenderFor(RenderManager manager) {
        return new RenderF1(manager);
    }
}
