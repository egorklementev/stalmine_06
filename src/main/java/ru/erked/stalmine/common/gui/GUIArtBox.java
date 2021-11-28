package ru.erked.stalmine.common.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.common.containters.ArtBoxInventory;
import ru.erked.stalmine.common.containters.ContainerArtBox;

public class GUIArtBox extends GuiContainer {

    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background1 =
            new ResourceLocation(StalmineMod.MODID, "textures/gui/art_box_1.png");
    private static final ResourceLocation background2 =
            new ResourceLocation(StalmineMod.MODID, "textures/gui/art_box_2.png");
    private static final ResourceLocation background3 =
            new ResourceLocation(StalmineMod.MODID, "textures/gui/art_box_3.png");

    private final int type;

    private final ArtBoxInventory inventory;

    public GUIArtBox(ContainerArtBox container, int type) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;

        this.inventory = container.inventory;
        this.type = type;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        switch (type) {
            case 1: {
                mc.getTextureManager().bindTexture(background1);
                break;
            }
            case 2: {
                mc.getTextureManager().bindTexture(background2);
                break;
            }
            case 3: {
                mc.getTextureManager().bindTexture(background3);
                break;
            }
        }
        drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0f, 0f, xSize, ySize, xSize, ySize);
    }

}
