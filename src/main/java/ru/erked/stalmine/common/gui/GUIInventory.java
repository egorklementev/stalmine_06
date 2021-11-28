package ru.erked.stalmine.common.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.StalmineConfig;
import ru.erked.stalmine.common.armor.Armor;
import ru.erked.stalmine.common.effects.*;
import ru.erked.stalmine.common.items.ItemArtifact;

public class GUIInventory extends GuiInventory {

    ResourceLocation hudIcons = new ResourceLocation(StalmineMod.MODID, "textures/gui/hud_inventory.png");

    public GUIInventory(EntityPlayer player) {
        super(player);
    }

    @Override
    protected void updateActivePotionEffects() {}

    @Override
    public void initGui() {
        super.initGui();
        //this.buttonList.clear();
        this.hasActivePotionEffects = false;
        updateActivePotionEffects();
        //this.guiTop -= 20;
    }

    public void drawStats() {
        Minecraft mc = this.mc;
        EntityPlayer player = mc.player;

        GlStateManager.pushMatrix();
        mc.renderEngine.bindTexture(hudIcons);

        float totalAntirad = 0f;
        float totalAntipsy = 0f;
        float totalAntichem = 0f;
        float totalAntiterm = 0f;
        float totalAntielectra = 0f;
        float totalArmor = 0f;

        // Effects
        for (PotionEffect pe : player.getActivePotionEffects()) {
            if (pe.getPotion() instanceof PotionAntiRad) {
                totalAntirad += pe.getAmplifier() / 10f;
            }
            if (pe.getPotion() instanceof PotionAntiPsy) {
                totalAntipsy += pe.getAmplifier() / 10f;
            }
            if (pe.getPotion() instanceof PotionAntiChem) {
                totalAntichem += pe.getAmplifier() / 10f;
            }
            if (pe.getPotion() instanceof PotionAntiTerm) {
                totalAntiterm += pe.getAmplifier() / 10f;
            }
        }
        /*// Artifacts
        for (int i = 0; i < 9; i++) {
            ItemStack is = player.inventory.getStackInSlot(i);
            if (!is.isEmpty() && is.getItem() instanceof ItemArtifact) {
                totalAntirad += ((ItemArtifact) is.getItem()).model.getAntirad();
                totalAntirad -= ((ItemArtifact) is.getItem()).model.getRad();
                totalAntipsy += ((ItemArtifact) is.getItem()).model.getAntipsy();
                totalAntipsy -= ((ItemArtifact) is.getItem()).model.getPsy();
                totalAntichem += ((ItemArtifact) is.getItem()).model.getAntichem();
                totalAntichem -= ((ItemArtifact) is.getItem()).model.getChem();
                totalAntiterm += ((ItemArtifact) is.getItem()).model.getAntiterm();
                totalAntiterm -= ((ItemArtifact) is.getItem()).model.getTerm();
                totalAntielectra += ((ItemArtifact) is.getItem()).model.getAntielectra();
            }
        }*/
        // Armor
        for (int i = 0; i < 4; i++) {
            ItemStack is = player.inventory.armorInventory.get(i);
            if (!is.isEmpty() && is.getItem() instanceof Armor) {
                totalAntirad += ((Armor) is.getItem()).model.getAntirad();
                totalAntipsy += ((Armor) is.getItem()).model.getAntipsy();
                totalAntichem += ((Armor) is.getItem()).model.getAntichem();
                totalAntiterm += ((Armor) is.getItem()).model.getAntiterm();
                totalAntielectra += ((Armor) is.getItem()).model.getAntielectra();
                totalArmor += 1f * ((Armor) is.getItem()).damageReduceAmount / StalmineConfig.MAX_ARMOR;
            }
        }

        if (totalAntirad < 0f) totalAntirad = 0f;
        if (totalAntirad > 1f) totalAntirad = 1f;
        if (totalAntipsy < 0f) totalAntipsy = 0f;
        if (totalAntipsy > 1f) totalAntipsy = 1f;
        if (totalAntichem < 0f) totalAntichem = 0f;
        if (totalAntichem > 1f) totalAntichem = 1f;
        if (totalAntiterm < 0f) totalAntiterm = 0f;
        if (totalAntiterm > 1f) totalAntiterm = 1f;
        if (totalAntielectra < 0f) totalAntielectra = 0f;
        if (totalAntielectra > 1f) totalAntielectra = 1f;

        for (int i = 0; i < 6; i++) {
            drawModalRectWithCustomSizedTexture(
                    this.guiLeft + (i > 2 ? this.xSize - 9 : 0),
                    this.guiTop + this.ySize + 5 + (i % 3) * 11,
                    9f * i,
                    0f,
                    18 / 2,
                    18 / 2,
                    128 / 2f,
                    128 / 2f
            );
        }
        for (int i = 0; i < 6; i++) {
            drawModalRectWithCustomSizedTexture(
                    this.guiLeft + (i > 2 ? this.xSize - 11 - 64 : 11),
                    this.guiTop + this.ySize + 5 + (i % 3) * 11,
                    0f,
                    9f,
                    128 / 2,
                    18 / 2,
                    128 / 2f,
                    128 / 2f
            );
        }

        float[] params = new float[] { totalAntirad, totalAntipsy, totalAntichem, totalAntiterm, totalAntielectra, totalArmor };
        for (int i = 0; i < 6; i++) {
            drawModalRectWithCustomSizedTexture(
                    this.guiLeft + (i > 2 ? this.xSize - 11 - (int)(params[i] * 128) / 2 : 11),
                    this.guiTop + this.ySize + 5 + (i % 3) * 11,
                    i > 2 ? (int)(params[i] * 128f / 2f) : 0f,
                    9f * 2f,
                    (int)(params[i] * 128) / 2,
                    18 / 2,
                    128 / 2f,
                    128 / 2f
            );
        }
        GlStateManager.popMatrix();
    }
}
