package ru.erked.stalmine.client.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.loaders.AdvancedModelLoader;
import ru.erked.stalmine.client.loaders.IModelCustom;
import ru.erked.stalmine.common.armor.Armor;

public class ModelBulat extends ModelBiped {

    IModelCustom chest = AdvancedModelLoader.loadModel(new ResourceLocation(
            StalmineMod.MODID, "models/armor/arm_bulat_chest.obj"));
    IModelCustom arm = AdvancedModelLoader.loadModel(new ResourceLocation(
            StalmineMod.MODID, "models/armor/arm_bulat_arm.obj"));
    IModelCustom leg = AdvancedModelLoader.loadModel(new ResourceLocation(
            StalmineMod.MODID, "models/armor/arm_bulat_leg.obj"));
    IModelCustom head = AdvancedModelLoader.loadModel(new ResourceLocation(
            StalmineMod.MODID, "models/armor/arm_bulat_head.obj"));
    IModelCustom boot = AdvancedModelLoader.loadModel(new ResourceLocation(
            StalmineMod.MODID, "models/armor/arm_bulat_boot.obj"));

    ResourceLocation texture;
    private int id;

    public ModelBulat(String texture, int id) {
        this.texture = new ResourceLocation(StalmineMod.MODID, "textures/armor/" + texture + ".png");
        this.id = id;
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        ItemStack bootsIS = ((NonNullList<ItemStack>)entity.getArmorInventoryList()).get(0);
        ItemStack legsIS = ((NonNullList<ItemStack>)entity.getArmorInventoryList()).get(1);
        ItemStack chestIS = ((NonNullList<ItemStack>)entity.getArmorInventoryList()).get(2);
        ItemStack headIS = ((NonNullList<ItemStack>)entity.getArmorInventoryList()).get(3);
        boolean showBoots = !bootsIS.isEmpty() && bootsIS.getItem() instanceof Armor &&
                ((Armor)bootsIS.getItem()).id == id;
        boolean showLegs = !legsIS.isEmpty() && legsIS.getItem() instanceof Armor &&
                ((Armor)legsIS.getItem()).id == id;
        boolean showChest = !chestIS.isEmpty() && chestIS.getItem() instanceof Armor &&
                ((Armor)chestIS.getItem()).id == id;
        boolean showHead = !headIS.isEmpty() && headIS.getItem() instanceof Armor &&
                ((Armor)headIS.getItem()).id == id;
        if (entity instanceof EntityArmorStand) {
            ageInTicks = 0f;
            netHeadYaw = 0f;
        }
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);

        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

        if (this.isChild)
        {
            GlStateManager.scale(0.75F, 0.75F, 0.75F);
            GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
            if (showHead)
                render(head, this.bipedHead, scale, StalmineModels.ArmType.HEAD);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
        }
        else
        {
            if (entity.isSneaking())
            {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }

            if (showHead)
                render(head, this.bipedHead, scale, StalmineModels.ArmType.HEAD);
        }

        if (showChest) {
            render(chest, this.bipedBody, scale, StalmineModels.ArmType.CHEST);
            render(arm, this.bipedLeftArm, scale, StalmineModels.ArmType.ARML);
            render(arm, this.bipedRightArm, scale, StalmineModels.ArmType.ARMR);
        }
        if (showLegs) {
            render(leg, this.bipedLeftLeg, scale, StalmineModels.ArmType.LEGL);
            render(leg, this.bipedRightLeg, scale, StalmineModels.ArmType.LEGR);
        }
        if (showBoots) {
            render(boot, this.bipedLeftLeg, scale, StalmineModels.ArmType.BOOT);
            render(boot, this.bipedRightLeg, scale, StalmineModels.ArmType.BOOT);
        }
        //this.bipedHeadwear.render(scale);

        GlStateManager.popMatrix();
    }

    private void renderChest(IModelCustom model) {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(180f, 1f, 0f, 0f);
        GlStateManager.scale(.325f, .31f, .35f);
        GlStateManager.scale(.9f, .9f, .8f);
        GlStateManager.translate(0f, -.9f, .0f);
        model.renderAll();
        GlStateManager.popMatrix();
    }

    private void renderArm(IModelCustom model, float p) {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(180f, 1f, 0f, 0f);
        GlStateManager.scale(.3f, .25f, .35f);
        GlStateManager.scale(-p, 1f, 1f);
        GlStateManager.translate(-.2f, -.75f, 0f);
        model.renderAll();
        GlStateManager.popMatrix();
    }

    private void renderLeg(IModelCustom model, float p) {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(180f, 1f, 0f, 0f);
        GlStateManager.scale(.3f, .25f, .3f);
        GlStateManager.scale(-p, 1f, 1f);
        GlStateManager.translate(0f, -1.25f, 0f);
        model.renderAll();
        GlStateManager.popMatrix();
    }

    private void renderBoot(IModelCustom model) {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(180f, 1f, 0f, 0f);
        GlStateManager.rotate(180f, 0f, 1f, 0f);
        GlStateManager.scale(.275f, .2f, .225f);
        GlStateManager.translate(0f, -3.25f, 0f);
        model.renderAll();
        GlStateManager.popMatrix();
    }

    private void renderHead(IModelCustom model) {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(180f, 1f, 0f, 0f);
        GlStateManager.scale(.35f, .3f, .35f);
        GlStateManager.translate(0f, .75f, 0f);
        model.renderAll();
        GlStateManager.popMatrix();
    }

    private void render(IModelCustom model, ModelRenderer mr, float scale, StalmineModels.ArmType type) {
        GlStateManager.translate(mr.offsetX, mr.offsetY, mr.offsetZ);
        if (mr.rotateAngleX == 0.0F && mr.rotateAngleY == 0.0F && mr.rotateAngleZ == 0.0F)
        {
            if (mr.rotationPointX == 0.0F && mr.rotationPointY == 0.0F && mr.rotationPointZ == 0.0F)
            {
                selectAndRender(model, type);
            }
            else
            {
                GlStateManager.translate(mr.rotationPointX * scale, mr.rotationPointY * scale, mr.rotationPointZ * scale);
                selectAndRender(model, type);
                GlStateManager.translate(-mr.rotationPointX * scale, -mr.rotationPointY * scale, -mr.rotationPointZ * scale);
            }
        }
        else
        {
            GlStateManager.pushMatrix();
            GlStateManager.translate(mr.rotationPointX * scale, mr.rotationPointY * scale, mr.rotationPointZ * scale);

            if (mr.rotateAngleZ != 0.0F)
            {
                GlStateManager.rotate(mr.rotateAngleZ * (180F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
            }

            if (mr.rotateAngleY != 0.0F)
            {
                GlStateManager.rotate(mr.rotateAngleY * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
            }

            if (mr.rotateAngleX != 0.0F)
            {
                GlStateManager.rotate(mr.rotateAngleX * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
            }

            selectAndRender(model, type);

            GlStateManager.popMatrix();
        }

        GlStateManager.translate(-mr.offsetX, -mr.offsetY, -mr.offsetZ);
    }

    private void selectAndRender(IModelCustom model, StalmineModels.ArmType type) {
        switch (type) {
            case HEAD:
                renderHead(model);
                break;
            case CHEST:
                renderChest(model);
                break;
            case ARML:
                renderArm(model, 1f);
                break;
            case ARMR:
                renderArm(model, -1f);
                break;
            case LEGL:
                renderLeg(model, 1f);
                break;
            case LEGR:
                renderLeg(model, -1f);
                break;
            case BOOT:
                renderBoot(model);
                break;
            default:
                break;
        }
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        boolean flag = entityIn instanceof EntityLivingBase && ((EntityLivingBase)entityIn).getTicksElytraFlying() > 4;
        this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;

        if (flag)
        {
            this.bipedHead.rotateAngleX = -((float)Math.PI / 4F);
        }
        else
        {
            this.bipedHead.rotateAngleX = headPitch * 0.017453292F;
        }

        this.bipedBody.rotateAngleY = 0.0F;
        this.bipedRightArm.rotationPointZ = 0.0F;
        this.bipedRightArm.rotationPointX = -5.0F;
        this.bipedLeftArm.rotationPointZ = 0.0F;
        this.bipedLeftArm.rotationPointX = 5.0F;
        float f = 1.0F;

        if (flag)
        {
            f = (float)(entityIn.motionX * entityIn.motionX + entityIn.motionY * entityIn.motionY + entityIn.motionZ * entityIn.motionZ);
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F)
        {
            f = 1.0F;
        }

        this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;

        if (entityIn instanceof EntityZombie) {
            flag = ((EntityZombie)entityIn).isArmsRaised();
            float f2 = -(float) Math.PI / (flag ? 1.5F : 2.25F);
            this.bipedRightArm.rotateAngleX = f2;
            this.bipedLeftArm.rotateAngleX = f2;
        }

        this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;
        this.bipedRightLeg.rotateAngleZ = 0.0F;
        this.bipedLeftLeg.rotateAngleZ = 0.0F;

        if (this.isRiding)
        {
            this.bipedRightArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedLeftArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedRightLeg.rotateAngleX = -1.4137167F;
            this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.bipedRightLeg.rotateAngleZ = 0.07853982F;
            this.bipedLeftLeg.rotateAngleX = -1.4137167F;
            this.bipedLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleZ = -0.07853982F;
        }

        this.bipedRightArm.rotateAngleY = 0.0F;
        this.bipedRightArm.rotateAngleZ = 0.0F;

        switch (this.leftArmPose)
        {
            case EMPTY:
                this.bipedLeftArm.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - 0.9424779F;
                this.bipedLeftArm.rotateAngleY = 0.5235988F;
                break;
            case ITEM:
                this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
                this.bipedLeftArm.rotateAngleY = 0.0F;
        }

        switch (this.rightArmPose)
        {
            case EMPTY:
                this.bipedRightArm.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - 0.9424779F;
                this.bipedRightArm.rotateAngleY = -0.5235988F;
                break;
            case ITEM:
                this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
                this.bipedRightArm.rotateAngleY = 0.0F;
        }

        if (this.swingProgress > 0.0F)
        {
            EnumHandSide enumhandside = this.getMainHand(entityIn);
            ModelRenderer modelrenderer = this.getArmForSide(enumhandside);
            float f1 = this.swingProgress;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float)Math.PI * 2F)) * 0.2F;

            if (enumhandside == EnumHandSide.LEFT)
            {
                this.bipedBody.rotateAngleY *= -1.0F;
            }

            this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            //this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
            f1 = 1.0F - this.swingProgress;
            f1 = f1 * f1;
            f1 = f1 * f1;
            f1 = 1.0F - f1;
            float f2 = MathHelper.sin(f1 * (float)Math.PI);
            float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            modelrenderer.rotateAngleX = (float)((double)modelrenderer.rotateAngleX - ((double)f2 * 1.2D + (double)f3));
            modelrenderer.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
        }

        if (this.isSneak)
        {
            this.bipedBody.rotateAngleX = 0.5F;
            this.bipedRightArm.rotateAngleX += 0.4F;
            this.bipedLeftArm.rotateAngleX += 0.4F;
            this.bipedRightLeg.rotationPointZ = 4.0F;
            this.bipedLeftLeg.rotationPointZ = 4.0F;
            this.bipedRightLeg.rotationPointY = 9.0F;
            this.bipedLeftLeg.rotationPointY = 9.0F;
            this.bipedHead.rotationPointY = 1.0F;
        }
        else
        {
            this.bipedBody.rotateAngleX = 0.0F;
            this.bipedRightLeg.rotationPointZ = 0.1F;
            this.bipedLeftLeg.rotationPointZ = 0.1F;
            this.bipedRightLeg.rotationPointY = 12.0F;
            this.bipedLeftLeg.rotationPointY = 12.0F;
            this.bipedHead.rotationPointY = 0.0F;
        }

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

        if (this.rightArmPose == ArmPose.BOW_AND_ARROW)
        {
            this.bipedRightArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY;
            this.bipedLeftArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY + 0.4F;
            this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
        }
        else if (this.leftArmPose == ArmPose.BOW_AND_ARROW)
        {
            this.bipedRightArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY - 0.4F;
            this.bipedLeftArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY;
            this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
        }

        if (entityIn instanceof EntityZombie) {
            this.bipedRightArm.rotateAngleY = -(float)Math.PI / 32F;
            this.bipedLeftArm.rotateAngleY = (float)Math.PI / 32F;
        }

        copyModelAngles(this.bipedHead, this.bipedHeadwear);
    }

}
