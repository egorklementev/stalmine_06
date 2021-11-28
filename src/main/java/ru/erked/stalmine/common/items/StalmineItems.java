package ru.erked.stalmine.common.items;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

public class StalmineItems {

    public static ArrayList<String> art_names = new ArrayList<String>() {
        {
            add("art_medusa");
            add("art_twist");
            add("art_stoneflower");
            add("art_nightstar");
            add("art_goldfish");
            add("art_gravi");
            add("art_battery");
            add("art_sparkler");
            add("art_flash");
            add("art_eye");
            add("art_soul");
            add("art_kolobok");
            add("art_compass");
            add("art_bubble");
            add("art_crystal");
            add("art_dummy");
            add("art_fireball");
            add("art_flame");
            add("art_meatpiece");
            add("art_mombeads");
            add("art_moonlight");
            add("art_snowflake");
            add("art_stoneblood");
            add("art_seaurchin");
            add("art_thorn");
            add("art_crystal_thorn");
            add("art_spring");
            add("art_slug");
            add("art_slime");
            add("art_mica");
            add("art_membrane");
            add("art_bitum");
            add("art_drops");
        }
    };

    @GameRegistry.ObjectHolder("stalmine:lighter")
    public static ItemLighter itemLighter;
    @GameRegistry.ObjectHolder("stalmine:erked_stick")
    public static ItemErkedStick itemErkedStick;
    @GameRegistry.ObjectHolder("stalmine:container_1")
    public static ItemContainer itemContainer1;
    @GameRegistry.ObjectHolder("stalmine:container_2")
    public static ItemContainer itemContainer2;
    @GameRegistry.ObjectHolder("stalmine:document_1")
    public static ItemDocument itemDocument1;
    @GameRegistry.ObjectHolder("stalmine:document_2")
    public static ItemDocument itemDocument2;
    @GameRegistry.ObjectHolder("stalmine:document_3")
    public static ItemDocument itemDocument3;
    @GameRegistry.ObjectHolder("stalmine:document_4")
    public static ItemDocument itemDocument4;
    @GameRegistry.ObjectHolder("stalmine:guitar")
    public static ItemGuitar itemGuitar;

    @GameRegistry.ObjectHolder("stalmine:money_1")
    public static ItemMoney money1;
    @GameRegistry.ObjectHolder("stalmine:money_2")
    public static ItemMoney money2;
    @GameRegistry.ObjectHolder("stalmine:money_5")
    public static ItemMoney money5;
    @GameRegistry.ObjectHolder("stalmine:money_10")
    public static ItemMoney money10;
    @GameRegistry.ObjectHolder("stalmine:money_20")
    public static ItemMoney money20;
    @GameRegistry.ObjectHolder("stalmine:money_50")
    public static ItemMoney money50;
    @GameRegistry.ObjectHolder("stalmine:money_100")
    public static ItemMoney money100;
    @GameRegistry.ObjectHolder("stalmine:money_200")
    public static ItemMoney money200;
    @GameRegistry.ObjectHolder("stalmine:money_500")
    public static ItemMoney money500;
    @GameRegistry.ObjectHolder("stalmine:money_1000")
    public static ItemMoney money1000;
    @GameRegistry.ObjectHolder("stalmine:money_2000")
    public static ItemMoney money2000;
    @GameRegistry.ObjectHolder("stalmine:money_5000")
    public static ItemMoney money5000;
    @GameRegistry.ObjectHolder("stalmine:money_10000")
    public static ItemMoney money10000;

    @GameRegistry.ObjectHolder("stalmine:am_545x18")
    public static ItemAmmo itemAmmo545x18;
    @GameRegistry.ObjectHolder("stalmine:am_545x39")
    public static ItemAmmo itemAmmo545x39;
    @GameRegistry.ObjectHolder("stalmine:am_545x39bp")
    public static ItemAmmo itemAmmo545x39bp;
    @GameRegistry.ObjectHolder("stalmine:am_556x45")
    public static ItemAmmo itemAmmo556x45;
    @GameRegistry.ObjectHolder("stalmine:am_556x45m855")
    public static ItemAmmo itemAmmo556x45m855;
    @GameRegistry.ObjectHolder("stalmine:am_16x70")
    public static ItemAmmo itemAmmo16x70;
    @GameRegistry.ObjectHolder("stalmine:am_57x28")
    public static ItemAmmo itemAmmo57x28;
    @GameRegistry.ObjectHolder("stalmine:am_762x54")
    public static ItemAmmo itemAmmo762x54;
    @GameRegistry.ObjectHolder("stalmine:am_762x54bp")
    public static ItemAmmo itemAmmo762x54bp;
    @GameRegistry.ObjectHolder("stalmine:am_762x54bs")
    public static ItemAmmo itemAmmo762x54bs;
    @GameRegistry.ObjectHolder("stalmine:am_762x54pp")
    public static ItemAmmo itemAmmo762x54pp;
    @GameRegistry.ObjectHolder("stalmine:am_9x39")
    public static ItemAmmo itemAmmo9x39;
    @GameRegistry.ObjectHolder("stalmine:am_9x39bp")
    public static ItemAmmo itemAmmo9x39bp;
    @GameRegistry.ObjectHolder("stalmine:am_9x19")
    public static ItemAmmo itemAmmo9x19;
    @GameRegistry.ObjectHolder("stalmine:am_9x19pbp")
    public static ItemAmmo itemAmmo9x19pbp;
    @GameRegistry.ObjectHolder("stalmine:am_dot45acp")
    public static ItemAmmo itemAmmoDot45acp;
    @GameRegistry.ObjectHolder("stalmine:am_dot45acppp")
    public static ItemAmmo itemAmmoDot45acppp;
    @GameRegistry.ObjectHolder("stalmine:am_accum")
    public static ItemAmmo itemAmmoAccum;
    @GameRegistry.ObjectHolder("stalmine:am_accum30")
    public static ItemAmmo itemAmmoAccum30;
    @GameRegistry.ObjectHolder("stalmine:am_vog25")
    public static ItemAmmo itemAmmoVog25;
    @GameRegistry.ObjectHolder("stalmine:am_m430")
    public static ItemAmmo itemAmmoM430;
    @GameRegistry.ObjectHolder("stalmine:am_rpg7")
    public static ItemAmmo itemAmmoRPG7;

    @GameRegistry.ObjectHolder("stalmine:w_gp25")
    public static ItemGrenadeLauncher itemGP25;
    @GameRegistry.ObjectHolder("stalmine:w_gl5040")
    public static ItemGrenadeLauncher itemGL5040;
    @GameRegistry.ObjectHolder("stalmine:w_pso1")
    public static ItemCrosshair itemPSO1;
    @GameRegistry.ObjectHolder("stalmine:w_psu1")
    public static ItemCrosshair itemPSU1;
    @GameRegistry.ObjectHolder("stalmine:w_susat")
    public static ItemCrosshair itemSUSAT;
    @GameRegistry.ObjectHolder("stalmine:w_susat16")
    public static ItemCrosshair itemSUSAT16;
    @GameRegistry.ObjectHolder("stalmine:w_susat16nvd")
    public static ItemCrosshair itemSUSAT16NVD;
    @GameRegistry.ObjectHolder("stalmine:w_susat4nvd")
    public static ItemCrosshair itemSUSAT4NVD;

    @GameRegistry.ObjectHolder("stalmine:art_medusa")
    public static ItemArtifact itemMedusa;
    @GameRegistry.ObjectHolder("stalmine:art_twist")
    public static ItemArtifact itemTwist;
    @GameRegistry.ObjectHolder("stalmine:art_stoneflower")
    public static ItemArtifact itemStoneflower;
    @GameRegistry.ObjectHolder("stalmine:art_nightstar")
    public static ItemArtifact itemNightstar;
    @GameRegistry.ObjectHolder("stalmine:art_goldfish")
    public static ItemArtifact itemGoldfish;
    @GameRegistry.ObjectHolder("stalmine:art_gravi")
    public static ItemArtifact itemGravi;
    @GameRegistry.ObjectHolder("stalmine:art_battery")
    public static ItemArtifact itemBattery;
    @GameRegistry.ObjectHolder("stalmine:art_sparkler")
    public static ItemArtifact itemSparkler;
    @GameRegistry.ObjectHolder("stalmine:art_flash")
    public static ItemArtifact itemFlash;
    @GameRegistry.ObjectHolder("stalmine:art_eye")
    public static ItemArtifact itemEye;
    @GameRegistry.ObjectHolder("stalmine:art_soul")
    public static ItemArtifact itemSoul;
    @GameRegistry.ObjectHolder("stalmine:art_kolobok")
    public static ItemArtifact itemKolobok;
    @GameRegistry.ObjectHolder("stalmine:art_compass")
    public static ItemArtifact itemCompass;
    @GameRegistry.ObjectHolder("stalmine:art_bubble")
    public static ItemArtifact itemBubble;
    @GameRegistry.ObjectHolder("stalmine:art_crystal")
    public static ItemArtifact itemCrystal;
    @GameRegistry.ObjectHolder("stalmine:art_dummy")
    public static ItemArtifact itemDummy;
    @GameRegistry.ObjectHolder("stalmine:art_fireball")
    public static ItemArtifact itemFireball;
    @GameRegistry.ObjectHolder("stalmine:art_flame")
    public static ItemArtifact itemFlame;
    @GameRegistry.ObjectHolder("stalmine:art_meatpiece")
    public static ItemArtifact itemMeatpiece;
    @GameRegistry.ObjectHolder("stalmine:art_mombeads")
    public static ItemArtifact itemMombeads;
    @GameRegistry.ObjectHolder("stalmine:art_moonlight")
    public static ItemArtifact itemMoonlight;
    @GameRegistry.ObjectHolder("stalmine:art_snowflake")
    public static ItemArtifact itemSnowflake;
    @GameRegistry.ObjectHolder("stalmine:art_stoneblood")
    public static ItemArtifact itemStoneblood;
    @GameRegistry.ObjectHolder("stalmine:art_seaurchin")
    public static ItemArtifact itemSeaUrchin;
    @GameRegistry.ObjectHolder("stalmine:art_thorn")
    public static ItemArtifact itemThorn;
    @GameRegistry.ObjectHolder("stalmine:art_crystal_thorn")
    public static ItemArtifact itemCrystalThorn;
    @GameRegistry.ObjectHolder("stalmine:art_spring")
    public static ItemArtifact itemSpring;
    @GameRegistry.ObjectHolder("stalmine:art_slug")
    public static ItemArtifact itemSlug;
    @GameRegistry.ObjectHolder("stalmine:art_slime")
    public static ItemArtifact itemSlime;
    @GameRegistry.ObjectHolder("stalmine:art_mica")
    public static ItemArtifact itemMica;
    @GameRegistry.ObjectHolder("stalmine:art_membrane")
    public static ItemArtifact itemMembrane;
    @GameRegistry.ObjectHolder("stalmine:art_bitum")
    public static ItemArtifact itemBitum;
    @GameRegistry.ObjectHolder("stalmine:art_drops")
    public static ItemArtifact itemDrops;

    @GameRegistry.ObjectHolder("stalmine:sausage")
    public static ItemFood itemSausage;
    @GameRegistry.ObjectHolder("stalmine:loaf")
    public static ItemFood itemLoaf;
    @GameRegistry.ObjectHolder("stalmine:canned")
    public static ItemFood itemCanned;
    @GameRegistry.ObjectHolder("stalmine:sardines")
    public static ItemFood itemSardines;
    @GameRegistry.ObjectHolder("stalmine:energy_drink")
    public static ItemEnergyDrink itemEnergyDrink;
    @GameRegistry.ObjectHolder("stalmine:vodka")
    public static ItemVodka itemVodka;

    @GameRegistry.ObjectHolder("stalmine:medkit_1")
    public static ItemMedkit itemMedkit1;
    @GameRegistry.ObjectHolder("stalmine:medkit_2")
    public static ItemMedkit itemMedkit2;
    @GameRegistry.ObjectHolder("stalmine:medkit_3")
    public static ItemMedkit itemMedkit3;
    @GameRegistry.ObjectHolder("stalmine:medkit_4")
    public static ItemMedkit itemMedkit4;

    @GameRegistry.ObjectHolder("stalmine:bandage")
    public static ItemBandage itemBandage;
    @GameRegistry.ObjectHolder("stalmine:barvinok")
    public static ItemBarvinok itemBarvinok;
    @GameRegistry.ObjectHolder("stalmine:psy_blockade")
    public static ItemPsyBlockade itemPsyBlockade;
    @GameRegistry.ObjectHolder("stalmine:radioprotector")
    public static ItemRadioprotector itemRadioprotector;
    @GameRegistry.ObjectHolder("stalmine:anabiotik")
    public static ItemAnabiotik itemAnabiotik;

    @GameRegistry.ObjectHolder("stalmine:art_box_1")
    public static ItemArtBox itemArtBox1;
    @GameRegistry.ObjectHolder("stalmine:art_box_2")
    public static ItemArtBox itemArtBox2;
    @GameRegistry.ObjectHolder("stalmine:art_box_3")
    public static ItemArtBox itemArtBox3;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        itemLighter.initModel();
        itemErkedStick.initModel();
        itemAmmo545x18.initModel();
        itemAmmo545x39.initModel();
        itemAmmo545x39bp.initModel();
        itemAmmo556x45.initModel();
        itemAmmo556x45m855.initModel();
        itemAmmo16x70.initModel();
        itemAmmo57x28.initModel();
        itemAmmo762x54.initModel();
        itemAmmo762x54bp.initModel();
        itemAmmo762x54bs.initModel();
        itemAmmo762x54pp.initModel();
        itemAmmo9x39.initModel();
        itemAmmo9x39bp.initModel();
        itemAmmo9x19.initModel();
        itemAmmo9x19pbp.initModel();
        itemAmmoDot45acp.initModel();
        itemAmmoDot45acppp.initModel();
        itemAmmoAccum.initModel();
        itemAmmoAccum30.initModel();
        itemAmmoVog25.initModel();
        itemAmmoM430.initModel();
        itemAmmoRPG7.initModel();
        itemMedusa.initModel();
        itemTwist.initModel();
        itemStoneflower.initModel();
        itemNightstar.initModel();
        itemGoldfish.initModel();
        itemGravi.initModel();
        itemBattery.initModel();
        itemSparkler.initModel();
        itemFlash.initModel();
        itemEye.initModel();
        itemSoul.initModel();
        itemKolobok.initModel();
        itemCompass.initModel();
        itemBubble.initModel();
        itemCrystal.initModel();
        itemDummy.initModel();
        itemFireball.initModel();
        itemFlame.initModel();
        itemMeatpiece.initModel();
        itemMombeads.initModel();
        itemMoonlight.initModel();
        itemSnowflake.initModel();
        itemStoneblood.initModel();
        itemSeaUrchin.initModel();
        itemThorn.initModel();
        itemCrystalThorn.initModel();
        itemSpring.initModel();
        itemSlug.initModel();
        itemSlime.initModel();
        itemMembrane.initModel();
        itemMica.initModel();
        itemBitum.initModel();
        itemDrops.initModel();
        itemSausage.initModel();
        itemLoaf.initModel();
        itemCanned.initModel();
        itemSardines.initModel();
        itemEnergyDrink.initModel();
        itemVodka.initModel();
        itemMedkit1.initModel();
        itemMedkit2.initModel();
        itemMedkit3.initModel();
        itemMedkit4.initModel();
        itemBarvinok.initModel();
        itemPsyBlockade.initModel();
        itemRadioprotector.initModel();
        itemAnabiotik.initModel();
        itemBandage.initModel();
        itemContainer1.initModel();
        itemContainer2.initModel();
        itemDocument1.initModel();
        itemDocument2.initModel();
        itemDocument3.initModel();
        itemDocument4.initModel();
        itemGuitar.initModel();
        itemArtBox1.initModel();
        itemArtBox2.initModel();
        itemArtBox3.initModel();
        money1.initModel();
        money2.initModel();
        money5.initModel();
        money10.initModel();
        money20.initModel();
        money50.initModel();
        money100.initModel();
        money200.initModel();
        money500.initModel();
        money1000.initModel();
        money2000.initModel();
        money5000.initModel();
        money10000.initModel();
        itemGP25.initModel();
        itemGL5040.initModel();
        itemPSO1.initModel();
        itemPSU1.initModel();
        itemSUSAT.initModel();
        itemSUSAT16.initModel();
        itemSUSAT16NVD.initModel();
        itemSUSAT4NVD.initModel();
    }

}
