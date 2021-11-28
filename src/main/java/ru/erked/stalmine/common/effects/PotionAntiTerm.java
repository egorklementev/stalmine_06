package ru.erked.stalmine.common.effects;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionAntiTerm extends Potion {

    public PotionAntiTerm() {
        super(false, 3484199);
        setPotionName("antiterm");
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
