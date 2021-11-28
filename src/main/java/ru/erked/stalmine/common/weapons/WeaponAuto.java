package ru.erked.stalmine.common.weapons;

public class WeaponAuto extends Weapon {
    public WeaponAuto(String name) {
        super(name);
        model.setType(WeaponDataModel.WType.AUTO_RIFLE);
    }
}
