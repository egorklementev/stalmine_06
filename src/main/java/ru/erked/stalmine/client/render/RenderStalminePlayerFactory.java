package ru.erked.stalmine.client.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.proxy.ClientProxy;

@SideOnly(Side.CLIENT)
public class RenderStalminePlayerFactory<T extends Entity> implements IRenderFactory<T> {

    @Override
    public Render<? super T> createRenderFor(RenderManager manager) {
        return (Render<? super T>) ClientProxy.playerRender;
    }
}
