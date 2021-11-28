package ru.erked.stalmine.common.weapons;

public class WeaponShotgun extends Weapon {

    public WeaponShotgun(String name) {
        super(name);
        model.setType(WeaponDataModel.WType.SHOTGUN);
    }
}
