package ru.erked.stalmine.common.weapons;

public class WeaponSniperAutoRifle extends Weapon {

    public WeaponSniperAutoRifle(String name) {
        super(name);
        model.setType(WeaponDataModel.WType.SNIPER_AUTO_RIFLE);

    }
}
