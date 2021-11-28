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

public class PotionChem extends Potion {

    private static int curAnti = 0;

    private final ResourceLocation overlayTexture =
                    new ResourceLocation(StalmineMod.MODID, "textures/gui/field.png");

    public PotionChem() {
        super(true, 3484199);
        setPotionName("chem");
    }

    @Override
    public void renderHUDEffect(PotionEffect effect, Gui gui, int x, int y, float z, float alpha) {
        //Minecraft.getMinecraft().player.timeInPortal = effect.getAmplifier() * .1f + .1f;
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (!player.isCreative()) {
            int w = Minecraft.getMinecraft().displayWidth;
            int h = Minecraft.getMinecraft().displayHeight;
            float a = effect.getAmplifier() == 0 ? .1f : effect.getAmplifier() / 5f;

            float anti = 1f;
            for (ItemStack is : player.getArmorInventoryList()) {
                if (!is.isEmpty() && is.getItem() instanceof Armor) {
                    anti -= ((Armor)is.getItem()).model.getAntichem();
                }
            }
            if (player.isPotionActive(StalminePotions.ANTICHEM)) {
                anti -= player.getActivePotionEffect(StalminePotions.ANTICHEM).getAmplifier() / 10f;
            }
            if (anti < 0f) {
                anti = 0f;
            }
            a *= anti;

            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.color(
                    1f - effect.getAmplifier() * .1f,
                    1f,
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
        if (ent.isPotionActive(StalminePotions.ANTICHEM)) {
            anti = .9f - ent.getActivePotionEffect(StalminePotions.ANTICHEM).getAmplifier() / 10f;
            curAnti = ent.getActivePotionEffect(StalminePotions.ANTICHEM).getAmplifier();
        }
        if (ent instanceof EntityPlayer) {
            float armorBonus = 0f;
            for (int i = 0; i < 4; i++) {
                armorBonus += ((EntityPlayer) ent).inventory.armorInventory.get(i).isEmpty()
                        ? 0f
                        : ((EntityPlayer) ent).inventory.armorInventory.get(i).getItem() instanceof Armor
                        ? ((Armor) ((EntityPlayer) ent).inventory.armorInventory.get(i).getItem()).model.getAntichem()
                        : 0f;
                curAnti += (int)(armorBonus * 5f);
            }
            anti -= armorBonus;
            /*for (int i = 0; i < 9; i++) {
                ItemStack is = ((EntityPlayer) ent).inventory.getStackInSlot(i);
                if (!is.isEmpty() && is.getItem() instanceof ItemArtifact) {
                    anti -= ((ItemArtifact) is.getItem()).model.getAntichem();
                    anti += ((ItemArtifact) is.getItem()).model.getChem();
                    curAnti += (int)(((ItemArtifact) is.getItem()).model.getAntichem() * 15f);
                    curAnti -= (int)(((ItemArtifact) is.getItem()).model.getChem()) * 15f;
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
        return true;
    }

    @Override
    public boolean shouldRenderInvText(PotionEffect effect) {
        return true;
    }
}
