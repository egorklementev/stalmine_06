package ru.erked.stalmine.common.weapons;

public class WeaponRocket extends Weapon {

    public WeaponRocket(String name) {
        super(name);
        model.setType(WeaponDataModel.WType.ROCKET);

    }
}
