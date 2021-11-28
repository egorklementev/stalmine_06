package ru.erked.stalmine.common.weapons;

public class WeaponSniperRifle extends Weapon {

    public WeaponSniperRifle(String name) {
        super(name);
        model.setType(WeaponDataModel.WType.SNIPER_RIFLE);

    }
}
