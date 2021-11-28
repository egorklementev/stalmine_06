package ru.erked.stalmine.common.effects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.common.armor.Armor;
import ru.erked.stalmine.common.items.ItemArtifact;

public class PotionTerm extends Potion {

    private static int curAnti = 0;

    private final ResourceLocation overlayTexture =
                    new ResourceLocation(StalmineMod.MODID, "textures/gui/field.png");

    public PotionTerm() {
        super(true, 3484199);
        setPotionName("term");
    }

    @Override
    public void renderHUDEffect(PotionEffect effect, Gui gui, int x, int y, float z, float alpha) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (!Minecraft.getMinecraft().player.isCreative()) {
            int w = Minecraft.getMinecraft().displayWidth;
            int h = Minecraft.getMinecraft().displayHeight;
            float a = effect.getAmplifier() == 0 ? .1f : effect.getAmplifier() / 6f;

            float anti = 1f;
            for (ItemStack is : player.getArmorInventoryList()) {
                if (!is.isEmpty() && is.getItem() instanceof Armor) {
                    anti -= ((Armor)is.getItem()).model.getAntiterm();
                }
            }
            if (player.isPotionActive(StalminePotions.ANTITERM)) {
                anti -= player.getActivePotionEffect(StalminePotions.ANTITERM).getAmplifier() / 10f;
            }
            if (anti < 0f) {
                anti = 0f;
            }
            a *= anti;

            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.color(
                    1f,
                    1f - effect.getAmplifier() * .1f,
                    .5f - effect.getAmplifier() * .1f,
                    (float) (a * (Math.sqrt(alpha)))
            );
            Minecraft.getMinecraft().renderEngine.bindTexture(overlayTexture);
            GlStateManager.scale((float) w / h, 1f, 1f);
            gui.drawTexturedModalRect(0, 0, 0, 0, w, h);
            GlStateManager.popMatrix();
        }
    }

    @Override
    public void performEffect(EntityLivingBase ent, int amplifier) {
        float anti = 1f;
        curAnti = 0;
        if (ent.isPotionActive(StalminePotions.ANTITERM)) {
            anti = .9f - ent.getActivePotionEffect(StalminePotions.ANTITERM).getAmplifier() / 10f;
            curAnti = ent.getActivePotionEffect(StalminePotions.ANTITERM).getAmplifier();
        }
        if (ent instanceof EntityPlayer) {
            float armorBonus = 0f;
            for (int i = 0; i < 4; i++) {
                armorBonus += ((EntityPlayer) ent).inventory.armorInventory.get(i).isEmpty()
                        ? 0f
                        : ((EntityPlayer) ent).inventory.armorInventory.get(i).getItem() instanceof Armor
                        ? ((Armor) ((EntityPlayer) ent).inventory.armorInventory.get(i).getItem()).model.getAntiterm()
                        : 0f;
                curAnti += (int)(armorBonus * 5f);
            }
            anti -= armorBonus;
            /*for (int i = 0; i < 9; i++) {
                ItemStack is = ((EntityPlayer) ent).inventory.getStackInSlot(i);
                if (!is.isEmpty() && is.getItem() instanceof ItemArtifact) {
                    anti -= ((ItemArtifact) is.getItem()).model.getAntiterm();
                    anti += ((ItemArtifact) is.getItem()).model.getTerm();
                    curAnti += (int)(((ItemArtifact) is.getItem()).model.getAntiterm() * 15f);
                    curAnti -= (int)(((ItemArtifact) is.getItem()).model.getTerm()) * 15f;
                }
            }*/
        }
        if (anti < 0f)
            anti = 0f;
        if (!(ent instanceof EntityZombie)) {
            ent.attackEntityFrom(
                    amplifier > 1 ? StalmineMod.anomalyDS : StalmineMod.noArmorDS,
                    (amplifier == 0 ? 1 : amplifier + 1) * anti
            );
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        int j = (100 >> amplifier) + curAnti * 10;

        if (j > 0)
        {
            return duration % j == 0;
        }
        else
        {
            return true;
        }
    }

    @Override
    public boolean shouldRender(PotionEffect effect) {
        return false;
    }

    @Override
    public boolean shouldRenderInvText(PotionEffect effect) {
        return true;
    }
}
