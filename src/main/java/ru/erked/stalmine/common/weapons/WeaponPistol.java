package ru.erked.stalmine.common.weapons;

public class WeaponPistol extends Weapon {

    public WeaponPistol(String name) {
        super(name);
        model.setType(WeaponDataModel.WType.PISTOL);

    }
}
