package ru.erked.stalmine.common.weapons;

public class WeaponDataModel {

    private String name;
    private float damage;
    private int maxClipSize;
    private float fireRate;
    private int maxDurability;
    private float accuracy;
    private float bulletVelocity;
    private float reloadTime;
    private float reloadTimeSG;
    private float recoil;
    private float aim;
    private String ammo;
    private String secondaryAmmo;
    private String grenadeAmmo;
    private String shootSound;
    private String reloadSound;
    private int crosshair;
    private int crosshairNvd;
    private boolean hasCrosshairAttached;
    private boolean hasGrenadeLauncherAttached;
    private WType type;

    public WeaponDataModel() {
        damage = 0;
        maxClipSize = 1;
        type = WType.PISTOL;
        maxDurability = 10;
    }

    public float getDamage() {
        return damage;
    }

    public WeaponDataModel setDamage(float damage) {
        this.damage = damage;
        return this;
    }

    public int getMaxClipSize() {
        return maxClipSize;
    }

    public WeaponDataModel setMaxClipSize(int maxClipSize) {
        this.maxClipSize = maxClipSize;
        return this;
    }

    public WType getType() {
        return type;
    }

    public WeaponDataModel setType(WType type) {
        this.type = type;
        return this;
    }

    public float getFireRate() {
        return fireRate;
    }

    public WeaponDataModel setFireRate(float fireRate) {
        this.fireRate = fireRate;
        return this;
    }

    public int getMaxDurability() {
        return maxDurability;
    }

    public WeaponDataModel setMaxDurability(int maxDurability) {
        this.maxDurability = maxDurability;
        return this;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public WeaponDataModel setAccuracy(float accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public String getName() {
        return name;
    }

    public WeaponDataModel setName(String name) {
        this.name = name;
        return this;
    }

    public float getBulletVelocity() {
        return bulletVelocity;
    }

    public WeaponDataModel setBulletVelocity(float bulletVelocity) {
        this.bulletVelocity = bulletVelocity;
        return this;
    }

    public float getReloadTime() {
        return reloadTime;
    }

    public WeaponDataModel setReloadTime(float reloadTime) {
        this.reloadTime = reloadTime;
        return this;
    }

    public String getAmmo() {
        return ammo;
    }

    public WeaponDataModel setAmmo(String ammo) {
        this.ammo = ammo;
        return this;
    }

    public String getSecondaryAmmo() {
        return secondaryAmmo;
    }

    public WeaponDataModel setSecondaryAmmo(String secondaryAmmo) {
        this.secondaryAmmo = secondaryAmmo;
        return this;
    }

    public String getGrenadeAmmo() {
        return grenadeAmmo;
    }

    public WeaponDataModel setGrenadeAmmo(String grenadeAmmo) {
        this.grenadeAmmo = grenadeAmmo;
        return this;
    }

    public float getRecoil() {
        return recoil;
    }

    public WeaponDataModel setRecoil(float recoil) {
        this.recoil = recoil;
        return this;
    }

    public float getAim() {
        return aim;
    }

    public WeaponDataModel setAim(float aim) {
        this.aim = aim;
        return this;
    }

    public String getShootSound() {
        return shootSound;
    }

    public WeaponDataModel setShootSound(String shootSound) {
        this.shootSound = shootSound;
        return this;
    }

    public String getReloadSound() {
        return reloadSound;
    }

    public WeaponDataModel setReloadSound(String reloadSound) {
        this.reloadSound = reloadSound;
        return this;
    }

    public int getCrosshair() {
        return crosshair;
    }

    public WeaponDataModel setCrosshair(int crosshair) {
        this.crosshair = crosshair;
        return this;
    }

    public int getCrosshairNvd() {
        return crosshairNvd;
    }

    public WeaponDataModel setCrosshairNvd(int crosshairNvd) {
        this.crosshairNvd = crosshairNvd;
        return this;
    }

    public float getReloadTimeSG() {
        return reloadTimeSG;
    }

    public WeaponDataModel  setReloadTimeSG(float reloadTimeSG) {
        this.reloadTimeSG = reloadTimeSG;
        return this;
    }

    public boolean hasCrosshairAttached() {
        return hasCrosshairAttached;
    }

    public WeaponDataModel setHasCrosshairAttached(boolean hasCrosshairAttached) {
        this.hasCrosshairAttached = hasCrosshairAttached;
        return this;
    }

    public boolean hasGrenadeLauncherAttached() {
        return hasGrenadeLauncherAttached;
    }

    public WeaponDataModel setHasGrenadeLauncherAttached(boolean hasGrenadeLauncherAttached) {
        this.hasGrenadeLauncherAttached = hasGrenadeLauncherAttached;
        return this;
    }

    public enum WType {
        PISTOL,
        SHOTGUN,
        SNIPER_RIFLE,
        SNIPER_AUTO_RIFLE,
        SNIPER_GRENADE_AUTO_RIFLE,
        AUTO_RIFLE,
        AUTO_RIFLE_GRENADE,
        ROCKET
    }
}
