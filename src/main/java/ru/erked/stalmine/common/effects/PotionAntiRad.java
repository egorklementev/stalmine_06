package ru.erked.stalmine.common.effects;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionAntiRad extends Potion {

    public PotionAntiRad() {
        super(false, 3484199);
        setPotionName("antirad");
    }

    @Override
    public boolean shouldRender(PotionEffect effect) {
        return true;
    }

    @Override
    public boolean shouldRenderHUD(PotionEffect effect) {
        return false;
    }

    @Override
    public boolean shouldRenderInvText(PotionEffect effect) {
        return true;
    }
}
