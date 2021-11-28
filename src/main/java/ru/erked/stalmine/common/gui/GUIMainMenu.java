package ru.erked.stalmine.common.gui;

import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.Display;
import ru.erked.stalmine.StalmineMod;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Random;

public class GUIMainMenu extends GuiScreen {

    private static final ResourceLocation logo
            = new ResourceLocation(StalmineMod.MODID, "textures/gui/logo.png");
    private static final ResourceLocation background
            = new ResourceLocation(StalmineMod.MODID, "textures/gui/menu_background.png");

    private Random rand = new Random();
    private float backAlpha = 1f;
    private float alphaTarget = 0.5f;
    private float eps = 1e-1f;

    @Override
    public void initGui() {
        Display.setTitle("S.T.A.L.M.I.N.E. v0.6  [build " + StalmineMod.VERSION + "]");

        // Buttons
        addButton(
                new GuiButton(
                        0,
                        this.width / 2 - 49,
                        this.height / 2,
                        98,
                        20,
                        I18n.format("menu.singleplayer")
                )
        );
        addButton(
                new GuiButton(
                        1,
                        this.width / 2 - 49,
                        this.height / 2 + 25,
                        98,
                        20,
                        I18n.format("menu.options")
                )
        );
        addButton(
                new GuiButton(
                        2,
                        this.width / 2 - 49,
                        this.height / 2 + 50,
                        98,
                        20,
                        I18n.format("menu.quit")
                )
        );
        addButton(
                new GuiButtonLanguage(
                        3,
                        this.width / 2 - 10,
                        this.height / 2 + 75
                )
        );
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        int w = this.width;
        int h = this.height;

        GlStateManager.color(0.0F, 0.0F, 0.0F, 1.0f);
        drawRect(0, 0, w, h, 0);

        // Logo
        int logoH = 128;
        logoH /= 2;
        int logoW = logoH * 6;
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0f);
        this.mc.getTextureManager().bindTexture(logo);
        double x = Math.sin(System.currentTimeMillis() / 2e3);
        double offset = 15.0;
        draw(
                w / 2.0 - logoW / 2.0 + (x * offset / 2.0),
                20.0 - x * 10.0,
                0,
                0,
                logoW - x * offset,
                logoH - x * offset / 6.0,
                (float) (logoW - x * offset),
                (float) (logoH - x * offset / 6.0)
        );

        // Version string
        this.drawString(
                this.fontRenderer,
                "Build version: " + StalmineMod.VERSION,
                5,
                this.height - 10,
                -1
        );

        super.drawScreen(mouseX, mouseY, partialTicks);


        // Background alpha
        float step = partialTicks * 5e-3f;
        if (step > eps) step = eps;
        if (Math.abs(backAlpha - alphaTarget) < eps) {
            alphaTarget = rand.nextFloat() * .8f + .2f;
        }
        else {
            backAlpha = backAlpha > alphaTarget ? backAlpha - step : backAlpha + step;
        }

        // Background
        float wth = 1024f / 768f;
        float htw = 768f / 1024;
        int backH = 0;
        int backW = (int)(h * wth);
        if (w > backW) {
            backW = w;
            backH = (int)(backW * htw);
        }
        else {
            backH = h;
        }
        GlStateManager.color(1.0F, 1.0F, 1.0F, backAlpha);
        this.mc.getTextureManager().bindTexture(background);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA_SATURATE, GlStateManager.DestFactor.DST_ALPHA);
        drawModalRectWithCustomSizedTexture(
                w / 2 - backW / 2,
                h / 2 - backH / 2,
                0,
                0,
                backW,
                backH,
                backW,
                backH
        );
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0: {
                this.mc.displayGuiScreen(new GuiWorldSelection(this));
                break;
            }
            case 1: {
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            }
            case 2: {
                this.mc.shutdown();
                break;
            }
            case 3: {
                this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
                break;
            }
            default:
                break;
        }
    }


    private void draw(double x, double y, float u, float v, double width, double height, float textureWidth, float textureHeight)
    {
        float f = 1.0F / textureWidth;
        float f1 = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x, y + height, 0.0D).tex(u * f, (v + (float)height) * f1).endVertex();
        bufferbuilder.pos(x + width, y + height, 0.0D).tex((u + (float)width) * f, (v + (float)height) * f1).endVertex();
        bufferbuilder.pos(x + width, y, 0.0D).tex((u + (float)width) * f, v * f1).endVertex();
        bufferbuilder.pos(x, y, 0.0D).tex(u * f, v * f1).endVertex();
        tessellator.draw();
    }


    private ByteBuffer readImageToBuffer(InputStream imageStream) throws IOException
    {
        BufferedImage bufferedimage = ImageIO.read(imageStream);
        int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), (int[])null, 0, bufferedimage.getWidth());
        ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);

        for (int i : aint)
        {
            bytebuffer.putInt(i << 8 | i >> 24 & 255);
        }

        bytebuffer.flip();
        return bytebuffer;
    }
}
