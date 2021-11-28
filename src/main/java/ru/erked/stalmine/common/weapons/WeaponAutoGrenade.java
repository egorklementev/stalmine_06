package ru.erked.stalmine.common.weapons;

public class WeaponAutoGrenade extends Weapon {
    public WeaponAutoGrenade(String name) {
        super(name);
        model.setType(WeaponDataModel.WType.AUTO_RIFLE_GRENADE);
    }
}
