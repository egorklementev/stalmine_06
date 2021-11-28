package ru.erked.stalmine.common.gui;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.common.containters.ContainerWeaponUpgradeTable;
import ru.erked.stalmine.common.tile_entities.TEWeaponUpgradeTable;

public class GUIWeaponUpgradeTable extends GuiContainer {

    public static final int WIDTH = 180;
    public static final int HEIGHT = 200;

    private static float angleX = 0f;
    private static float angleY = 0f;
    private static float angleZ = 0f;
    private static int prevMouseX = -1;
    private static int prevMouseY = -1;

    private static final ResourceLocation background =
            new ResourceLocation(StalmineMod.MODID, "textures/gui/wpn_upgrade_table.png");

    public GUIWeaponUpgradeTable(TEWeaponUpgradeTable tileEntity, ContainerWeaponUpgradeTable container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;

        angleX = 0f;
        angleY = 0f;
        angleZ = 0f;
        prevMouseX = -1;
        prevMouseY = -1;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);

        // Draw gun
        ScaledResolution scaled = new ScaledResolution(mc);
        int w = scaled.getScaledWidth();
        int h = scaled.getScaledHeight();
        if (inventorySlots.getSlot(3).getHasStack()) {
            ItemStack stack = inventorySlots.getSlot(3).getStack();
            double scale = 3.5;
            int xPos = (int) ((getGuiLeft() + getXSize() * .5f) / scale);
            int yPos = (int) ((getGuiTop() + getYSize() * .16f) / scale);
            GlStateManager.pushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            GlStateManager.scale(scale, scale, scale);
            //itemRender.renderItemAndEffectIntoGUI(mc.player, stack, xPos, yPos);
            itemRender.zLevel += 50.0f;
            {
                IBakedModel bakedmodel = itemRender.getItemModelWithOverrides(stack, (World)null, mc.player);
                GlStateManager.pushMatrix();
                mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                mc.renderEngine.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
                GlStateManager.enableRescaleNormal();
                GlStateManager.enableAlpha();
                GlStateManager.alphaFunc(516, 0.1F);
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                //this.setupGuiTransform(x, y, bakedmodel.isGui3d());
                {
                    GlStateManager.translate((float)xPos, (float)yPos, 100.0F + itemRender.zLevel);
                    GlStateManager.translate(8.0F, 8.0F, 0.0F);
                    GlStateManager.scale(1.0F, -1.0F, 1.0F);
                    GlStateManager.scale(16.0F, 16.0F, 16.0F);
                    GlStateManager.rotate(45f, 0f, 0f, -1f);

                    GlStateManager.rotate(angleX, -1f, 1f, 0f);
                    GlStateManager.rotate(angleY, -1f, -1f, 0f);
                    //GlStateManager.rotate(angleX + angleY, 0f, 0f, 0.1f);

                    if (bakedmodel.isGui3d())
                    {
                        GlStateManager.enableLighting();
                    }
                    else
                    {
                        GlStateManager.disableLighting();
                    }
                }
                bakedmodel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(bakedmodel,
                        ItemCameraTransforms.TransformType.GUI, false);
                itemRender.renderItem(stack, bakedmodel);
                GlStateManager.disableAlpha();
                GlStateManager.disableRescaleNormal();
                GlStateManager.disableLighting();
                GlStateManager.popMatrix();
                mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                mc.renderEngine.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
            }
            itemRender.zLevel -= 50.0f;
            GlStateManager.popMatrix();
        }

        this.renderHoveredToolTip(mouseX, mouseY);

        // Update wpn rotation angles
        if (prevMouseX != -1) {
            //if (mc.gameSettings.keyBindUseItem.isPressed()) {
/*
                int dx = mouseX - prevMouseX;
                int dy = mouseY - prevMouseY;
                angleX += (float)dx * 1f;
                angleY += (float)dy * 1f;
*/
            //}
        }

        angleX = ((float)mouseX / w) * 360f - 180f;
        angleY = ((float)mouseY / h) * 360f - 180f;

        prevMouseX = mouseX;
        prevMouseY = mouseY;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        this.fontRenderer.drawString(I18n.format("tile.stalmine.wpn_upgrade_table.name"), 8, 8, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0f, 0f, xSize, ySize, xSize, ySize);
    }

}
