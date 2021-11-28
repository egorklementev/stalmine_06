package ru.erked.stalmine.client;

import net.minecraft.command.CommandBase;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.common.armor.ArmorDataModel;
import ru.erked.stalmine.common.armor.StalmineArmor;
import ru.erked.stalmine.common.items.ArtifactDataModel;
import ru.erked.stalmine.common.items.StalmineItems;
import ru.erked.stalmine.common.weapons.StalmineWeapons;
import ru.erked.stalmine.common.weapons.WeaponDataModel;
import ru.erked.stalmine.proxy.CommonProxy;

import java.util.HashMap;

public class StalmineConfig {

    private static final String CATEGORY_GENERAL = "general";
    private static final String CATEGORY_ITEMS = "items";
    private static final String CATEGORY_BLOCKS = "blocks";
    private static final String CATEGORY_WEAPONS = "weapons";
    private static final String CATEGORY_ARMOR = "armor";
    private static final String CATEGORY_ARTIFACTS = "artifacts";

    public static final int MAX_ARMOR = 30;
    public static boolean placeInifiniteFireplaces = false;
    public static boolean isNVDActive = false;
    public static CommandBase.CoordinateArg teleX;
    public static CommandBase.CoordinateArg teleY;
    public static CommandBase.CoordinateArg teleZ;

    public static int lighterDurability = 50;
    public static int fireplaceBurnDuration = 66; // Around 15 minutes

    public static int psyArtTimer = 100;
    public static int electraTimer = 5;
    public static int electraArtTimer = 100;
    public static int funnelTimer = 5;
    public static int funnelArtTimer = 100;
    public static int springboardTimer = 5;
    public static int springboardArtTimer = 100;
    public static int carouselTimer = 5;
    public static int carouselArtTimer = 100;
    public static int deepfryTimer = 5;
    public static int deepfryArtTimer = 100;
    public static int steamTimer = 5;
    public static int steamArtTimer = 100;
    public static int sodaTimer = 5;
    public static int sodaArtTimer = 100;
    public static int kisselTimer = 5;
    public static int kisselArtTimer = 100;

    public static double art_common = .5d;
    public static double art_rare = .75d;
    public static double art_epic = .9d;
    public static double art_legendary = .99d;

    public static HashMap<String, WeaponDataModel> w_models = new HashMap<>();
    public static HashMap<String, ArmorDataModel> arm_models = new HashMap<>();
    public static HashMap<String, ArtifactDataModel> art_models = new HashMap<>();

    public static final float teleTime = 120f;

    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
        } catch (Exception e1) {
            StalmineMod.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }

        for (int i = 0; i < StalmineWeapons.w_list.size(); i++) {
            Configuration wpn_cfg = CommonProxy.wpnConfigs.get(i);
            try {
                wpn_cfg.load();
                initWeaponConfig(wpn_cfg, StalmineWeapons.w_list.get(i));
            } catch (Exception e1) {
                StalmineMod.logger.log(Level.ERROR, "Problem loading config file!", e1);
            } finally {
                if (wpn_cfg.hasChanged()) {
                    wpn_cfg.save();
                }
            }
        }

        for (int i = 0; i < StalmineArmor.arm_names.size(); i++) {
            Configuration arm_cfg = CommonProxy.armConfigs.get(i);
            try {
                arm_cfg.load();
                initArmorConfig(arm_cfg, StalmineArmor.arm_names.get(i));
            } catch (Exception e1) {
                StalmineMod.logger.log(Level.ERROR, "Problem loading config file!", e1);
            } finally {
                if (arm_cfg.hasChanged()) {
                    arm_cfg.save();
                }
            }
        }

        for (int i = 0; i < StalmineItems.art_names.size(); i++) {
            Configuration art_cfg = CommonProxy.artConfigs.get(i);
            try {
                art_cfg.load();
                initArtifactConfig(art_cfg, StalmineItems.art_names.get(i));
            } catch (Exception e1) {
                StalmineMod.logger.log(Level.ERROR, "Problem loading config file!", e1);
            } finally {
                if (art_cfg.hasChanged()) {
                    art_cfg.save();
                }
            }
        }
    }

    private static void initArtifactConfig(Configuration cfg, String art_name) {
        art_models.put(
                art_name,
                new ArtifactDataModel().setAntirad(
                        cfg.getFloat(
                                art_name + "_antirad", CATEGORY_ARTIFACTS,
                                0f, 0f, 1f, art_name + " antirad"
                        )
                ).setAntipsy(
                        cfg.getFloat(
                                art_name + "_antipsy", CATEGORY_ARTIFACTS,
                                0f, 0f, 1f, art_name + " antipsy"
                        )
                ).setAntichem(
                        cfg.getFloat(
                                art_name + "_antichem", CATEGORY_ARTIFACTS,
                                0f, 0f, 1f, art_name + " antichem"
                        )
                ).setAntiterm(
                        cfg.getFloat(
                                art_name + "_antiterm", CATEGORY_ARTIFACTS,
                                0f, 0f, 1f, art_name + " antiterm"
                        )
                ).setAntielectra(
                        cfg.getFloat(
                                art_name + "_antielectra", CATEGORY_ARTIFACTS,
                                0f, 0f, 1f, art_name + " antielectra"
                        )
                ).setRad(
                        cfg.getFloat(
                                art_name + "_rad", CATEGORY_ARTIFACTS,
                                0f, 0f, 1f, art_name + " rad"
                        )
                ).setPsy(
                        cfg.getFloat(
                                art_name + "_psy", CATEGORY_ARTIFACTS,
                                0f, 0f, 1f, art_name + " psy"
                        )
                ).setChem(
                        cfg.getFloat(
                                art_name + "_chem", CATEGORY_ARTIFACTS,
                                0f, 0f, 1f, art_name + " chem"
                        )
                ).setTerm(
                        cfg.getFloat(
                                art_name + "_term", CATEGORY_ARTIFACTS,
                                0f, 0f, 1f, art_name + " term"
                        )
                ).setRegen(
                        cfg.getFloat(
                                art_name + "_regen", CATEGORY_ARTIFACTS,
                                0f, 0f, 1f, art_name + " regen"
                        )
                ).setSpeed(
                        cfg.getFloat(
                                art_name + "_speed", CATEGORY_ARTIFACTS,
                                0f, 0f, 1f, art_name + " speed"
                        )
                ).setResistance(
                        cfg.getFloat(
                                art_name + "_armor", CATEGORY_ARTIFACTS,
                                0f, 0f, 1f, art_name + " armor"
                        )
                ).setPoison(
                        cfg.getFloat(
                                art_name + "_poison", CATEGORY_ARTIFACTS,
                                0f, 0f, 1f, art_name + " poison"
                        )
                ).setSlowness(
                        cfg.getFloat(
                                art_name + "_slowness", CATEGORY_ARTIFACTS,
                                0f, 0f, 1f, art_name + " slowness"
                        )
                ).setWeakness(
                        cfg.getFloat(
                                art_name + "_weakness", CATEGORY_ARTIFACTS,
                                0f, 0f, 1f, art_name + " weakness"
                        )
                ).setTimer(
                        cfg.getFloat(
                                art_name + "_timer", CATEGORY_ARTIFACTS,
                                20f, 1f, 1000f, art_name + " timer"
                        )
                ).setR(
                        cfg.getFloat(
                                art_name + "_r", CATEGORY_ARTIFACTS,
                                .9f, 0f, 1f, art_name + " r"
                        )
                ).setG(
                        cfg.getFloat(
                                art_name + "_g", CATEGORY_ARTIFACTS,
                                .9f, 0f, 1f, art_name + " g"
                        )
                ).setB(
                        cfg.getFloat(
                                art_name + "_b", CATEGORY_ARTIFACTS,
                                .9f, 0f, 1f, art_name + " b"
                        )
                )
        );
    }

    private static void initArmorConfig(Configuration cfg, String arm_name) {
        arm_models.put(
                arm_name,
                new ArmorDataModel().setAntirad(
                        cfg.getFloat(
                                arm_name + "_antirad", CATEGORY_ARMOR,
                                0f, 0f, 1f, arm_name + " antirad")
                ).setAntipsy(
                        cfg.getFloat(
                                arm_name + "_antipsy", CATEGORY_ARMOR,
                                0f, 0f, 1f, arm_name + " antipsy")
                ).setAntichem(
                        cfg.getFloat(
                                arm_name + "_antichem", CATEGORY_ARMOR,
                                0f, 0f, 1f, arm_name + " antichem")
                ).setAntiterm(
                        cfg.getFloat(
                                arm_name + "_antiterm", CATEGORY_ARMOR,
                                0f, 0f, 1f, arm_name + " antiterm")
                ).setAntielectra(
                        cfg.getFloat(
                                arm_name + "_antielectra", CATEGORY_ARMOR,
                                0f, 0f, 1f, arm_name + " antielectra")
                ).setNightVisionDevice(
                        cfg.getInt(
                                arm_name + "_night_vision_device", CATEGORY_ARMOR,
                                0, 0, 2, arm_name + " night vision device")
                )
        );
    }

    private static void initWeaponConfig(Configuration cfg, String w_name) {
        w_models.put(
                w_name,
                new WeaponDataModel()
                        .setName(
                                cfg.getString(
                                        w_name + "_name", CATEGORY_WEAPONS, w_name, w_name + " name")
                        )
                        .setDamage(
                                cfg.getFloat(
                                        w_name + "_dmg", CATEGORY_WEAPONS,
                                        1f, 0f, 100f, w_name + " damage")
                        )
                        .setMaxClipSize(
                                cfg.getInt(
                                        w_name + "_max_clip", CATEGORY_WEAPONS,
                                        5, 1, 1000, w_name + " max clip size")
                        )
                        .setFireRate(
                                cfg.getFloat(
                                        w_name + "_fire_rate", CATEGORY_WEAPONS,
                                        100f, 1f, 1200f, w_name + " fire rate (RPS)")
                        )
                        .setMaxDurability(
                                cfg.getInt(
                                        w_name + "_max_durability", CATEGORY_WEAPONS,
                                        100, 1, 100000, w_name + " max durability")
                        )
                        .setCrosshair(
                                cfg.getInt(
                                        w_name + "_crosshair", CATEGORY_WEAPONS,
                                        0, 0, 6, w_name + " crosshair")
                        )
                        .setCrosshairNvd(
                                cfg.getInt(
                                        w_name + "_crosshair_nvd", CATEGORY_WEAPONS,
                                        0, 0, 6, w_name + " crosshair nvd")
                        )
                        .setAccuracy(
                                cfg.getFloat(
                                        w_name + "_accuracy", CATEGORY_WEAPONS,
                                        2.5f, .5f, 250f, w_name + " accuracy")
                        )
                        .setBulletVelocity(
                                cfg.getFloat(
                                        w_name + "_bullet_velocity", CATEGORY_WEAPONS,
                                        3f, .01f, 15f, w_name + " bullet velocity")
                        )
                        .setReloadTime(
                                cfg.getFloat(
                                        w_name + "_reload_time", CATEGORY_WEAPONS,
                                        5f, 1f, 200f, w_name + " reload time")
                        )
                        .setReloadTimeSG(
                                cfg.getFloat(
                                        w_name + "_reload_time_sg", CATEGORY_WEAPONS,
                                        2f, 1f, 200f, w_name + " reload time (shotgun)")
                        )
                        .setRecoil(
                                cfg.getFloat(
                                        w_name + "_recoil", CATEGORY_WEAPONS,
                                        5f, 0f, 100f, w_name + " recoil")
                        )
                        .setAim(
                                cfg.getFloat(
                                        w_name + "_aim", CATEGORY_WEAPONS,
                                        0f, 0f, 10f, w_name + " aim")
                        )
                        .setAmmo(
                                cfg.getString(
                                        w_name + "_ammo", CATEGORY_WEAPONS, "am_545x18", w_name + " ammo")
                        )
                        .setSecondaryAmmo(
                                cfg.getString(
                                        w_name + "_secondary_ammo", CATEGORY_WEAPONS, "am_9x19", w_name + " secondary ammo")
                        )
                        .setGrenadeAmmo(
                                cfg.getString(
                                        w_name + "_grenade_ammo", CATEGORY_WEAPONS, "am_vog25", w_name + " grenade ammo")
                        )
                        .setShootSound(
                                cfg.getString(
                                        w_name + "_shoot_sound", CATEGORY_WEAPONS, w_name, w_name + " shooting sound")
                        )
                        .setReloadSound(
                                cfg.getString(
                                        w_name + "_reload_sound", CATEGORY_WEAPONS, "pistol_reload",
                                        w_name + " reloading sound")
                        )
                        .setHasCrosshairAttached(
                                cfg.getBoolean(
                                        w_name + "_has_crosshair", CATEGORY_WEAPONS,
                                        false, w_name + " has crosshair")
                        )
                        .setHasGrenadeLauncherAttached(
                                cfg.getBoolean(
                                        w_name + "_has_grenade_launcher", CATEGORY_WEAPONS,
                                        false, w_name + " has grenade launcher")
                        )
        );
    }

    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        cfg.addCustomCategoryComment(CATEGORY_ITEMS, "Item configuration");
        cfg.addCustomCategoryComment(CATEGORY_BLOCKS, "Block configuration");
        cfg.addCustomCategoryComment(CATEGORY_WEAPONS, "Weapon configuration");
        cfg.addCustomCategoryComment(CATEGORY_ARMOR, "Armor configuration");
        cfg.addCustomCategoryComment(CATEGORY_ARTIFACTS, "Artifact configuration");

        lighterDurability = cfg.getInt("lighter_durability", CATEGORY_ITEMS, lighterDurability, 1, 1024, "Sets the durability of a lighter.");
        fireplaceBurnDuration = cfg.getInt("fireplace_burn_duration", CATEGORY_BLOCKS, fireplaceBurnDuration, 1, 1024, "Sets how long a fireplace is lit up.");

        psyArtTimer = cfg.getInt("psy_art_timer", CATEGORY_BLOCKS, psyArtTimer, 100, 20 * 60 * 60, "Timer of psy field");
        electraTimer = cfg.getInt("electra_timer", CATEGORY_BLOCKS, electraTimer, 1, 100, "Timer of electra anomaly");
        electraArtTimer = cfg.getInt("electra_art_timer", CATEGORY_BLOCKS, electraArtTimer, 100, 20 * 60 * 60, "Artifact timer of electra anomaly");
        funnelTimer = cfg.getInt("funnel_timer", CATEGORY_BLOCKS, funnelTimer, 1, 100, "Timer of funnel anomaly");
        funnelArtTimer = cfg.getInt("funnel_art_timer", CATEGORY_BLOCKS, funnelArtTimer, 100, 20 * 60 * 60, "Artifact timer of funnel anomaly");
        springboardTimer = cfg.getInt("springboard_timer", CATEGORY_BLOCKS, springboardTimer, 1, 100, "Timer of springboard anomaly");
        springboardArtTimer = cfg.getInt("springboard_art_timer", CATEGORY_BLOCKS, springboardArtTimer, 100, 20 * 60 * 60, "Artifact timer of springboard anomaly");
        carouselTimer = cfg.getInt("carousel_timer", CATEGORY_BLOCKS, carouselTimer, 1, 200, "Timer of carousel anomaly");
        carouselArtTimer = cfg.getInt("carousel_art_timer", CATEGORY_BLOCKS, carouselArtTimer, 100, 20 * 60 * 60, "Artifact timer of carousel anomaly");
        deepfryTimer = cfg.getInt("deepfry_timer", CATEGORY_BLOCKS, deepfryTimer, 1, 500, "Timer of deepfry anomaly");
        deepfryArtTimer = cfg.getInt("deepfry_art_timer", CATEGORY_BLOCKS, deepfryArtTimer, 100, 20 * 60 * 60, "Artifact timer of deepfry anomaly");
        steamTimer = cfg.getInt("steam_timer", CATEGORY_BLOCKS, steamTimer, 1, 500, "Timer of steam anomaly");
        steamArtTimer = cfg.getInt("steam_art_timer", CATEGORY_BLOCKS, steamArtTimer, 100, 20 * 60 * 60, "Artifact timer of steam anomaly");
        sodaTimer = cfg.getInt("soda_timer", CATEGORY_BLOCKS, sodaTimer, 1, 200, "Timer of soda anomaly");
        sodaArtTimer = cfg.getInt("soda_art_timer", CATEGORY_BLOCKS, sodaArtTimer, 100, 20 * 60 * 60, "Artifact timer of soda anomaly");
        kisselTimer = cfg.getInt("kissel_timer", CATEGORY_BLOCKS, kisselTimer, 1, 200, "Timer of kissel anomaly");
        kisselArtTimer = cfg.getInt("kissel_art_timer", CATEGORY_BLOCKS, kisselArtTimer, 100, 20 * 60 * 60, "Artifact timer of kissel anomaly");

        art_common = cfg.getFloat("art_common", CATEGORY_GENERAL, (float)art_common, 0f, 1f, "Chance of getting common artifact");
        art_rare = cfg.getFloat("art_rare", CATEGORY_GENERAL, (float)art_rare, 0f, 1f, "Chance of getting rare artifact");
        art_epic = cfg.getFloat("art_epic", CATEGORY_GENERAL, (float)art_epic, 0f, 1f, "Chance of getting epic artifact");
        art_legendary = cfg.getFloat("art_legendary", CATEGORY_GENERAL, (float)art_legendary, 0f, 1f, "Chance of getting legendary artifact");

        try {
            teleX = CommandBase.parseCoordinate(0f, "~+1", true);
            teleY = CommandBase.parseCoordinate(0f, "~", false);
            teleZ = CommandBase.parseCoordinate(0f, "~+1", true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
