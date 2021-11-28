package ru.erked.stalmine.common.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.common.containters.ContainerBag;
import ru.erked.stalmine.common.tile_entities.TEBag;

public class GUIBag extends GuiContainer {

    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background =
            new ResourceLocation(StalmineMod.MODID, "textures/gui/bag.png");

    public GUIBag(TEBag tileEntity, ContainerBag container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0f, 0f, xSize, ySize, xSize, ySize);
    }

}
