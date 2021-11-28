package ru.erked.stalmine.client.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.loaders.AdvancedModelLoader;
import ru.erked.stalmine.client.loaders.IModelCustom;
import ru.erked.stalmine.common.entities.EntityF1;
import ru.erked.stalmine.common.entities.EntityRGD5;

import javax.annotation.Nullable;

public class RenderF1<T extends EntityF1> extends Render<T> {

    private IModelCustom model;

    public RenderF1(RenderManager renderManager) {
        super(renderManager);
        this.model = AdvancedModelLoader.loadModel(new ResourceLocation(
                StalmineMod.MODID, "models/item/w_f1.obj"));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        return new ResourceLocation(StalmineMod.MODID, "textures/items/w_rgd5.png");
    }

    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        this.bindEntityTexture(entity);
        GlStateManager.translate(x, y + .125f, z);
        GlStateManager.scale(.05f, .05f, .05f);
        this.model.renderAll();
        GlStateManager.popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
