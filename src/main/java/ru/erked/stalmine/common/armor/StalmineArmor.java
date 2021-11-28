package ru.erked.stalmine.common.armor;

import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.StalmineMod;

import java.util.ArrayList;

public class StalmineArmor {

    public static final ArrayList<String> arm_names = new ArrayList<String>() {
        {
            add("arm_jacket_chest");
            add("arm_jacket_head");
            add("arm_jacket_legs");
            add("arm_jacket_boots");
            add("arm_jacket_black_chest");
            add("arm_jacket_black_head");
            add("arm_jacket_black_legs");
            add("arm_jacket_black_boots");
            add("arm_cape_chest");
            add("arm_cape_head");
            add("arm_cape_legs");
            add("arm_cape_boots");
            add("arm_cape_black_chest");
            add("arm_cape_black_head");
            add("arm_cape_black_legs");
            add("arm_cape_black_boots");
            add("arm_kombez_bandit_chest");
            add("arm_kombez_bandit_head");
            add("arm_kombez_bandit_legs");
            add("arm_kombez_bandit_boots");
            add("arm_kombez_merc_chest_1");
            add("arm_kombez_merc_head_1");
            add("arm_kombez_merc_legs_1");
            add("arm_kombez_merc_boots_1");
            add("arm_kombez_merc_chest_2");
            add("arm_kombez_merc_head_2");
            add("arm_kombez_merc_legs_2");
            add("arm_kombez_merc_boots_2");
            add("arm_kombez_unknown_chest_1");
            add("arm_kombez_unknown_head_1");
            add("arm_kombez_unknown_legs_1");
            add("arm_kombez_unknown_boots_1");
            add("arm_kombez_unknown_chest_2");
            add("arm_kombez_unknown_head_2");
            add("arm_kombez_unknown_legs_2");
            add("arm_kombez_unknown_boots_2");
            add("arm_kombez_us_chest_1");
            add("arm_kombez_us_head_1");
            add("arm_kombez_us_legs_1");
            add("arm_kombez_us_boots_1");
            add("arm_kombez_us_chest_2");
            add("arm_kombez_us_head_2");
            add("arm_kombez_us_legs_2");
            add("arm_kombez_us_boots_2");
            add("arm_heavy_duty_chest");
            add("arm_heavy_duty_head");
            add("arm_heavy_duty_legs");
            add("arm_heavy_duty_boots");
            add("arm_heavy_freedom_chest");
            add("arm_heavy_freedom_head");
            add("arm_heavy_freedom_legs");
            add("arm_heavy_freedom_boots");
            add("arm_heavy_merc_chest_1");
            add("arm_heavy_merc_head_1");
            add("arm_heavy_merc_legs_1");
            add("arm_heavy_merc_boots_1");
            add("arm_heavy_merc_chest_2");
            add("arm_heavy_merc_head_2");
            add("arm_heavy_merc_legs_2");
            add("arm_heavy_merc_boots_2");
            add("arm_heavy_monolith_chest_1");
            add("arm_heavy_monolith_head_1");
            add("arm_heavy_monolith_legs_1");
            add("arm_heavy_monolith_boots_1");
            add("arm_heavy_monolith_chest_2");
            add("arm_heavy_monolith_head_2");
            add("arm_heavy_monolith_legs_2");
            add("arm_heavy_monolith_boots_2");
            add("arm_heavy_stalker_chest");
            add("arm_heavy_stalker_head");
            add("arm_heavy_stalker_legs");
            add("arm_heavy_stalker_boots");
            add("arm_heavy_unknown_chest");
            add("arm_heavy_unknown_head");
            add("arm_heavy_unknown_legs");
            add("arm_heavy_unknown_boots");
            add("arm_exo_duty_chest");
            add("arm_exo_duty_head");
            add("arm_exo_duty_legs");
            add("arm_exo_duty_boots");
            add("arm_exo_freedom_chest");
            add("arm_exo_freedom_head");
            add("arm_exo_freedom_legs");
            add("arm_exo_freedom_boots");
            add("arm_exo_merc_chest_1");
            add("arm_exo_merc_head_1");
            add("arm_exo_merc_legs_1");
            add("arm_exo_merc_boots_1");
            add("arm_exo_merc_chest_2");
            add("arm_exo_merc_head_2");
            add("arm_exo_merc_legs_2");
            add("arm_exo_merc_boots_2");
            add("arm_exo_monolith_chest_1");
            add("arm_exo_monolith_head_1");
            add("arm_exo_monolith_legs_1");
            add("arm_exo_monolith_boots_1");
            add("arm_exo_monolith_chest_2");
            add("arm_exo_monolith_head_2");
            add("arm_exo_monolith_legs_2");
            add("arm_exo_monolith_boots_2");
            add("arm_exo_stalker_chest");
            add("arm_exo_stalker_head");
            add("arm_exo_stalker_legs");
            add("arm_exo_stalker_boots");
            add("arm_exo_unknown_chest");
            add("arm_exo_unknown_head");
            add("arm_exo_unknown_legs");
            add("arm_exo_unknown_boots");
            add("arm_seva_duty_chest");
            add("arm_seva_duty_head");
            add("arm_seva_duty_legs");
            add("arm_seva_duty_boots");
            add("arm_seva_freedom_chest");
            add("arm_seva_freedom_head");
            add("arm_seva_freedom_legs");
            add("arm_seva_freedom_boots");
            add("arm_seva_merc_chest");
            add("arm_seva_merc_head");
            add("arm_seva_merc_legs");
            add("arm_seva_merc_boots");
            add("arm_seva_monolith_chest");
            add("arm_seva_monolith_head");
            add("arm_seva_monolith_legs");
            add("arm_seva_monolith_boots");
            add("arm_seva_stalker_chest");
            add("arm_seva_stalker_head");
            add("arm_seva_stalker_legs");
            add("arm_seva_stalker_boots");
            add("arm_scientist_chest_1");
            add("arm_scientist_head_1");
            add("arm_scientist_legs_1");
            add("arm_scientist_boots_1");
            add("arm_scientist_chest_2");
            add("arm_scientist_head_2");
            add("arm_scientist_legs_2");
            add("arm_scientist_boots_2");
            add("arm_scientist_chest_3");
            add("arm_scientist_head_3");
            add("arm_scientist_legs_3");
            add("arm_scientist_boots_3");
            add("arm_scientist_chest_4");
            add("arm_scientist_head_4");
            add("arm_scientist_legs_4");
            add("arm_scientist_boots_4");
            add("arm_berill_dead_chest");
            add("arm_berill_dead_head");
            add("arm_berill_dead_legs");
            add("arm_berill_dead_boots");
            add("arm_berill_freedom_chest");
            add("arm_berill_freedom_head");
            add("arm_berill_freedom_legs");
            add("arm_berill_freedom_boots");
            add("arm_berill_military_chest");
            add("arm_berill_military_head");
            add("arm_berill_military_legs");
            add("arm_berill_military_boots");
            add("arm_bulat_dead_chest");
            add("arm_bulat_dead_head");
            add("arm_bulat_dead_legs");
            add("arm_bulat_dead_boots");
            add("arm_bulat_duty_chest");
            add("arm_bulat_duty_head");
            add("arm_bulat_duty_legs");
            add("arm_bulat_duty_boots");
            add("arm_bulat_freedom_chest");
            add("arm_bulat_freedom_head");
            add("arm_bulat_freedom_legs");
            add("arm_bulat_freedom_boots");
            add("arm_bulat_merc_chest");
            add("arm_bulat_merc_head");
            add("arm_bulat_merc_legs");
            add("arm_bulat_merc_boots");
            add("arm_bulat_military_chest");
            add("arm_bulat_military_head");
            add("arm_bulat_military_legs");
            add("arm_bulat_military_boots");
            add("arm_zarya_dead_chest_1");
            add("arm_zarya_dead_head_1");
            add("arm_zarya_dead_legs_1");
            add("arm_zarya_dead_boots_1");
            add("arm_zarya_dead_chest_2");
            add("arm_zarya_dead_head_2");
            add("arm_zarya_dead_legs_2");
            add("arm_zarya_dead_boots_2");
            add("arm_zarya_duty_chest");
            add("arm_zarya_duty_head");
            add("arm_zarya_duty_legs");
            add("arm_zarya_duty_boots");
            add("arm_zarya_freedom_chest_1");
            add("arm_zarya_freedom_head_1");
            add("arm_zarya_freedom_legs_1");
            add("arm_zarya_freedom_boots_1");
            add("arm_zarya_freedom_chest_2");
            add("arm_zarya_freedom_head_2");
            add("arm_zarya_freedom_legs_2");
            add("arm_zarya_freedom_boots_2");
            add("arm_zarya_freedom_chest_3");
            add("arm_zarya_freedom_head_3");
            add("arm_zarya_freedom_legs_3");
            add("arm_zarya_freedom_boots_3");
            add("arm_zarya_monolith_chest");
            add("arm_zarya_monolith_head");
            add("arm_zarya_monolith_legs");
            add("arm_zarya_monolith_boots");
            add("arm_zarya_stalker_chest");
            add("arm_zarya_stalker_head");
            add("arm_zarya_stalker_legs");
            add("arm_zarya_stalker_boots");
        }
    };

    public static ArrayList<Armor> allArmor;

    public static final Armor.ArmorMaterial MATERIAL_JACKET =
            EnumHelper.addArmorMaterial(
                    StalmineMod.MODID + ":mat_arm_jacket",
                    StalmineMod.MODID + ":mat_arm_jacket",
                    5,
                    new int[]{0, 1, 1, 0}, // 2
                    7,
                    SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
                    0.0F
            ).setRepairItem(new ItemStack(Items.LEATHER));
    public static final Armor.ArmorMaterial MATERIAL_CAPE =
            EnumHelper.addArmorMaterial(
                    StalmineMod.MODID + ":mat_arm_cape",
                    StalmineMod.MODID + ":mat_arm_cape",
                    8,
                    new int[]{0, 1, 2, 0}, // 3
                    7,
                    SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
                    0.0F
            ).setRepairItem(new ItemStack(Items.LEATHER));
    public static final Armor.ArmorMaterial MATERIAL_KOMBEZ =
            EnumHelper.addArmorMaterial(
                    StalmineMod.MODID + ":mat_arm_kombez",
                    StalmineMod.MODID + ":mat_arm_kombez",
                    12,
                    new int[]{1, 2, 5, 2}, // 10
                    7,
                    SoundEvents.ITEM_ARMOR_EQUIP_CHAIN,
                    0.0F
            ).setRepairItem(new ItemStack(Items.IRON_INGOT));
    public static final Armor.ArmorMaterial MATERIAL_HEAVY =
            EnumHelper.addArmorMaterial(
                    StalmineMod.MODID + ":mat_arm_heavy",
                    StalmineMod.MODID + ":mat_arm_heavy",
                    40,
                    new int[]{3, 7, 10, 6}, // 26
                    7,
                    SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                    1.0F
            ).setRepairItem(new ItemStack(Items.IRON_INGOT));
    public static final Armor.ArmorMaterial MATERIAL_EXO =
            EnumHelper.addArmorMaterial(
                    StalmineMod.MODID + ":mat_arm_exo",
                    StalmineMod.MODID + ":mat_arm_exo",
                    50,
                    new int[]{3, 9, 12, 6}, // 30
                    7,
                    SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                    2.0F
            ).setRepairItem(new ItemStack(Items.IRON_INGOT));
    public static final Armor.ArmorMaterial MATERIAL_SEVA =
            EnumHelper.addArmorMaterial(
                    StalmineMod.MODID + ":mat_arm_seva",
                    StalmineMod.MODID + ":mat_arm_seva",
                    16,
                    new int[]{1, 3, 5, 2}, // 11
                    7,
                    SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                    0.0F
            ).setRepairItem(new ItemStack(Items.IRON_INGOT));
    public static final Armor.ArmorMaterial MATERIAL_SCIENTIST =
            EnumHelper.addArmorMaterial(
                    StalmineMod.MODID + ":mat_arm_scientist",
                    StalmineMod.MODID + ":mat_arm_scientist",
                    10,
                    new int[]{0, 1, 2, 1}, // 4
                    7,
                    SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
                    0.0F
            ).setRepairItem(new ItemStack(Items.LEATHER));
    public static final Armor.ArmorMaterial MATERIAL_BERILL =
            EnumHelper.addArmorMaterial(
                    StalmineMod.MODID + ":mat_arm_berill",
                    StalmineMod.MODID + ":mat_arm_berill",
                    22,
                    new int[]{1, 3, 8, 4}, // 16
                    7,
                    SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                    0.0F
            ).setRepairItem(new ItemStack(Items.IRON_INGOT));
    public static final Armor.ArmorMaterial MATERIAL_BULAT =
            EnumHelper.addArmorMaterial(
                    StalmineMod.MODID + ":mat_arm_bulat",
                    StalmineMod.MODID + ":mat_arm_bulat",
                    35,
                    new int[]{2, 7, 11, 5}, // 25
                    7,
                    SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                    1.0F
            ).setRepairItem(new ItemStack(Items.IRON_INGOT));
    public static final Armor.ArmorMaterial MATERIAL_ZARYA =
            EnumHelper.addArmorMaterial(
                    StalmineMod.MODID + ":mat_arm_zarya",
                    StalmineMod.MODID + ":mat_arm_zarya",
                    15,
                    new int[]{0, 2, 3, 1}, // 6
                    7,
                    SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                    0.0F
            ).setRepairItem(new ItemStack(Items.IRON_INGOT));

    @GameRegistry.ObjectHolder("stalmine:arm_jacket_chest")
    public static Armor jacketChest;
    @GameRegistry.ObjectHolder("stalmine:arm_jacket_head")
    public static Armor jacketHead;
    @GameRegistry.ObjectHolder("stalmine:arm_jacket_legs")
    public static Armor jacketLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_jacket_boots")
    public static Armor jacketBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_jacket_black_chest")
    public static Armor blackJacketChest;
    @GameRegistry.ObjectHolder("stalmine:arm_jacket_black_head")
    public static Armor blackJacketHead;
    @GameRegistry.ObjectHolder("stalmine:arm_jacket_black_legs")
    public static Armor blackJacketLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_jacket_black_boots")
    public static Armor blackJacketBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_cape_chest")
    public static Armor capeChest;
    @GameRegistry.ObjectHolder("stalmine:arm_cape_head")
    public static Armor capeHead;
    @GameRegistry.ObjectHolder("stalmine:arm_cape_legs")
    public static Armor capeLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_cape_boots")
    public static Armor capeBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_cape_black_chest")
    public static Armor blackCapeChest;
    @GameRegistry.ObjectHolder("stalmine:arm_cape_black_head")
    public static Armor blackCapeHead;
    @GameRegistry.ObjectHolder("stalmine:arm_cape_black_legs")
    public static Armor blackCapeLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_cape_black_boots")
    public static Armor blackCapeBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_kombez_bandit_chest")
    public static Armor banditKombezChest;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_bandit_head")
    public static Armor banditKombezHead;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_bandit_legs")
    public static Armor banditKombezLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_bandit_boots")
    public static Armor banditKombezBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_kombez_merc_chest_1")
    public static Armor mercKombezChest1;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_merc_head_1")
    public static Armor mercKombezHead1;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_merc_legs_1")
    public static Armor mercKombezLegs1;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_merc_boots_1")
    public static Armor mercKombezBoots1;

    @GameRegistry.ObjectHolder("stalmine:arm_kombez_merc_chest_2")
    public static Armor mercKombezChest2;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_merc_head_2")
    public static Armor mercKombezHead2;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_merc_legs_2")
    public static Armor mercKombezLegs2;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_merc_boots_2")
    public static Armor mercKombezBoots2;

    @GameRegistry.ObjectHolder("stalmine:arm_kombez_unknown_chest_1")
    public static Armor unknownKombezChest1;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_unknown_head_1")
    public static Armor unknownKombezHead1;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_unknown_legs_1")
    public static Armor unknownKombezLegs1;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_unknown_boots_1")
    public static Armor unknownKombezBoots1;

    @GameRegistry.ObjectHolder("stalmine:arm_kombez_unknown_chest_2")
    public static Armor unknownKombezChest2;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_unknown_head_2")
    public static Armor unknownKombezHead2;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_unknown_legs_2")
    public static Armor unknownKombezLegs2;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_unknown_boots_2")
    public static Armor unknownKombezBoots2;

    @GameRegistry.ObjectHolder("stalmine:arm_kombez_us_chest_1")
    public static Armor usKombezChest1;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_us_head_1")
    public static Armor usKombezHead1;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_us_legs_1")
    public static Armor usKombezLegs1;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_us_boots_1")
    public static Armor usKombezBoots1;

    @GameRegistry.ObjectHolder("stalmine:arm_kombez_us_chest_2")
    public static Armor usKombezChest2;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_us_head_2")
    public static Armor usKombezHead2;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_us_legs_2")
    public static Armor usKombezLegs2;
    @GameRegistry.ObjectHolder("stalmine:arm_kombez_us_boots_2")
    public static Armor usKombezBoots2;

    @GameRegistry.ObjectHolder("stalmine:arm_heavy_duty_chest")
    public static Armor dutyHeavyChest;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_duty_head")
    public static Armor dutyHeavyHead;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_duty_legs")
    public static Armor dutyHeavyLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_duty_boots")
    public static Armor dutyHeavyBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_heavy_freedom_chest")
    public static Armor freedomHeavyChest;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_freedom_head")
    public static Armor freedomHeavyHead;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_freedom_legs")
    public static Armor freedomHeavyLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_freedom_boots")
    public static Armor freedomHeavyBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_heavy_merc_chest_1")
    public static Armor mercHeavyChest1;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_merc_head_1")
    public static Armor mercHeavyHead1;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_merc_legs_1")
    public static Armor mercHeavyLegs1;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_merc_boots_1")
    public static Armor mercHeavyBoots1;

    @GameRegistry.ObjectHolder("stalmine:arm_heavy_merc_chest_2")
    public static Armor mercHeavyChest2;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_merc_head_2")
    public static Armor mercHeavyHead2;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_merc_legs_2")
    public static Armor mercHeavyLegs2;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_merc_boots_2")
    public static Armor mercHeavyBoots2;

    @GameRegistry.ObjectHolder("stalmine:arm_heavy_monolith_chest_1")
    public static Armor monolithHeavyChest1;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_monolith_head_1")
    public static Armor monolithHeavyHead1;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_monolith_legs_1")
    public static Armor monolithHeavyLegs1;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_monolith_boots_1")
    public static Armor monolithHeavyBoots1;

    @GameRegistry.ObjectHolder("stalmine:arm_heavy_monolith_chest_2")
    public static Armor monolithHeavyChest2;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_monolith_head_2")
    public static Armor monolithHeavyHead2;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_monolith_legs_2")
    public static Armor monolithHeavyLegs2;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_monolith_boots_2")
    public static Armor monolithHeavyBoots2;

    @GameRegistry.ObjectHolder("stalmine:arm_heavy_stalker_chest")
    public static Armor stalkerHeavyChest;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_stalker_head")
    public static Armor stalkerHeavyHead;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_stalker_legs")
    public static Armor stalkerHeavyLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_stalker_boots")
    public static Armor stalkerHeavyBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_heavy_unknown_chest")
    public static Armor unknownHeavyChest;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_unknown_head")
    public static Armor unknownHeavyHead;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_unknown_legs")
    public static Armor unknownHeavyLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_heavy_unknown_boots")
    public static Armor unknownHeavyBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_exo_duty_chest")
    public static Armor dutyExoChest;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_duty_head")
    public static Armor dutyExoHead;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_duty_legs")
    public static Armor dutyExoLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_duty_boots")
    public static Armor dutyExoBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_exo_freedom_chest")
    public static Armor freedomExoChest;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_freedom_head")
    public static Armor freedomExoHead;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_freedom_legs")
    public static Armor freedomExoLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_freedom_boots")
    public static Armor freedomExoBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_exo_merc_chest_1")
    public static Armor mercExoChest1;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_merc_head_1")
    public static Armor mercExoHead1;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_merc_legs_1")
    public static Armor mercExoLegs1;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_merc_boots_1")
    public static Armor mercExoBoots1;

    @GameRegistry.ObjectHolder("stalmine:arm_exo_merc_chest_2")
    public static Armor mercExoChest2;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_merc_head_2")
    public static Armor mercExoHead2;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_merc_legs_2")
    public static Armor mercExoLegs2;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_merc_boots_2")
    public static Armor mercExoBoots2;

    @GameRegistry.ObjectHolder("stalmine:arm_exo_monolith_chest_1")
    public static Armor monolithExoChest1;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_monolith_head_1")
    public static Armor monolithExoHead1;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_monolith_legs_1")
    public static Armor monolithExoLegs1;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_monolith_boots_1")
    public static Armor monolithExoBoots1;

    @GameRegistry.ObjectHolder("stalmine:arm_exo_monolith_chest_2")
    public static Armor monolithExoChest2;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_monolith_head_2")
    public static Armor monolithExoHead2;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_monolith_legs_2")
    public static Armor monolithExoLegs2;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_monolith_boots_2")
    public static Armor monolithExoBoots2;

    @GameRegistry.ObjectHolder("stalmine:arm_exo_stalker_chest")
    public static Armor stalkerExoChest;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_stalker_head")
    public static Armor stalkerExoHead;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_stalker_legs")
    public static Armor stalkerExoLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_stalker_boots")
    public static Armor stalkerExoBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_exo_unknown_chest")
    public static Armor unknownExoChest;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_unknown_head")
    public static Armor unknownExoHead;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_unknown_legs")
    public static Armor unknownExoLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_exo_unknown_boots")
    public static Armor unknownExoBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_seva_duty_chest")
    public static Armor dutySevaChest;
    @GameRegistry.ObjectHolder("stalmine:arm_seva_duty_head")
    public static Armor dutySevaHead;
    @GameRegistry.ObjectHolder("stalmine:arm_seva_duty_legs")
    public static Armor dutySevaLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_seva_duty_boots")
    public static Armor dutySevaBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_seva_freedom_chest")
    public static Armor freedomSevaChest;
    @GameRegistry.ObjectHolder("stalmine:arm_seva_freedom_head")
    public static Armor freedomSevaHead;
    @GameRegistry.ObjectHolder("stalmine:arm_seva_freedom_legs")
    public static Armor freedomSevaLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_seva_freedom_boots")
    public static Armor freedomSevaBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_seva_merc_chest")
    public static Armor mercSevaChest;
    @GameRegistry.ObjectHolder("stalmine:arm_seva_merc_head")
    public static Armor mercSevaHead;
    @GameRegistry.ObjectHolder("stalmine:arm_seva_merc_legs")
    public static Armor mercSevaLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_seva_merc_boots")
    public static Armor mercSevaBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_seva_monolith_chest")
    public static Armor monolithSevaChest;
    @GameRegistry.ObjectHolder("stalmine:arm_seva_monolith_head")
    public static Armor monolithSevaHead;
    @GameRegistry.ObjectHolder("stalmine:arm_seva_monolith_legs")
    public static Armor monolithSevaLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_seva_monolith_boots")
    public static Armor monolithSevaBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_seva_stalker_chest")
    public static Armor stalkerSevaChest;
    @GameRegistry.ObjectHolder("stalmine:arm_seva_stalker_head")
    public static Armor stalkerSevaHead;
    @GameRegistry.ObjectHolder("stalmine:arm_seva_stalker_legs")
    public static Armor stalkerSevaLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_seva_stalker_boots")
    public static Armor stalkerSevaBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_scientist_chest_1")
    public static Armor scientist1Chest;
    @GameRegistry.ObjectHolder("stalmine:arm_scientist_head_1")
    public static Armor scientist1Head;
    @GameRegistry.ObjectHolder("stalmine:arm_scientist_legs_1")
    public static Armor scientist1Legs;
    @GameRegistry.ObjectHolder("stalmine:arm_scientist_boots_1")
    public static Armor scientist1Boots;

    @GameRegistry.ObjectHolder("stalmine:arm_scientist_chest_2")
    public static Armor scientist2Chest;
    @GameRegistry.ObjectHolder("stalmine:arm_scientist_head_2")
    public static Armor scientist2Head;
    @GameRegistry.ObjectHolder("stalmine:arm_scientist_legs_2")
    public static Armor scientist2Legs;
    @GameRegistry.ObjectHolder("stalmine:arm_scientist_boots_2")
    public static Armor scientist2Boots;

    @GameRegistry.ObjectHolder("stalmine:arm_scientist_chest_3")
    public static Armor scientist3Chest;
    @GameRegistry.ObjectHolder("stalmine:arm_scientist_head_3")
    public static Armor scientist3Head;
    @GameRegistry.ObjectHolder("stalmine:arm_scientist_legs_3")
    public static Armor scientist3Legs;
    @GameRegistry.ObjectHolder("stalmine:arm_scientist_boots_3")
    public static Armor scientist3Boots;

    @GameRegistry.ObjectHolder("stalmine:arm_scientist_chest_4")
    public static Armor scientist4Chest;
    @GameRegistry.ObjectHolder("stalmine:arm_scientist_head_4")
    public static Armor scientist4Head;
    @GameRegistry.ObjectHolder("stalmine:arm_scientist_legs_4")
    public static Armor scientist4Legs;
    @GameRegistry.ObjectHolder("stalmine:arm_scientist_boots_4")
    public static Armor scientist4Boots;

    @GameRegistry.ObjectHolder("stalmine:arm_berill_dead_chest")
    public static Armor deadBerillChest;
    @GameRegistry.ObjectHolder("stalmine:arm_berill_dead_head")
    public static Armor deadBerillHead;
    @GameRegistry.ObjectHolder("stalmine:arm_berill_dead_legs")
    public static Armor deadBerillLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_berill_dead_boots")
    public static Armor deadBerillBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_berill_freedom_chest")
    public static Armor freedomBerillChest;
    @GameRegistry.ObjectHolder("stalmine:arm_berill_freedom_head")
    public static Armor freedomBerillHead;
    @GameRegistry.ObjectHolder("stalmine:arm_berill_freedom_legs")
    public static Armor freedomBerillLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_berill_freedom_boots")
    public static Armor freedomBerillBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_berill_military_chest")
    public static Armor militaryBerillChest;
    @GameRegistry.ObjectHolder("stalmine:arm_berill_military_head")
    public static Armor militaryBerillHead;
    @GameRegistry.ObjectHolder("stalmine:arm_berill_military_legs")
    public static Armor militaryBerillLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_berill_military_boots")
    public static Armor militaryBerillBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_bulat_dead_chest")
    public static Armor deadBulatChest;
    @GameRegistry.ObjectHolder("stalmine:arm_bulat_dead_head")
    public static Armor deadBulatHead;
    @GameRegistry.ObjectHolder("stalmine:arm_bulat_dead_legs")
    public static Armor deadBulatLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_bulat_dead_boots")
    public static Armor deadBulatBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_bulat_duty_chest")
    public static Armor dutyBulatChest;
    @GameRegistry.ObjectHolder("stalmine:arm_bulat_duty_head")
    public static Armor dutyBulatHead;
    @GameRegistry.ObjectHolder("stalmine:arm_bulat_duty_legs")
    public static Armor dutyBulatLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_bulat_duty_boots")
    public static Armor dutyBulatBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_bulat_freedom_chest")
    public static Armor freedomBulatChest;
    @GameRegistry.ObjectHolder("stalmine:arm_bulat_freedom_head")
    public static Armor freedomBulatHead;
    @GameRegistry.ObjectHolder("stalmine:arm_bulat_freedom_legs")
    public static Armor freedomBulatLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_bulat_freedom_boots")
    public static Armor freedomBulatBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_bulat_merc_chest")
    public static Armor mercBulatChest;
    @GameRegistry.ObjectHolder("stalmine:arm_bulat_merc_head")
    public static Armor mercBulatHead;
    @GameRegistry.ObjectHolder("stalmine:arm_bulat_merc_legs")
    public static Armor mercBulatLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_bulat_merc_boots")
    public static Armor mercBulatBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_bulat_military_chest")
    public static Armor militaryBulatChest;
    @GameRegistry.ObjectHolder("stalmine:arm_bulat_military_head")
    public static Armor militaryBulatHead;
    @GameRegistry.ObjectHolder("stalmine:arm_bulat_military_legs")
    public static Armor militaryBulatLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_bulat_military_boots")
    public static Armor militaryBulatBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_zarya_dead_chest_1")
    public static Armor deadZaryaChest1;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_dead_head_1")
    public static Armor deadZaryaHead1;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_dead_legs_1")
    public static Armor deadZaryaLegs1;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_dead_boots_1")
    public static Armor deadZaryaBoots1;

    @GameRegistry.ObjectHolder("stalmine:arm_zarya_dead_chest_2")
    public static Armor deadZaryaChest2;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_dead_head_2")
    public static Armor deadZaryaHead2;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_dead_legs_2")
    public static Armor deadZaryaLegs2;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_dead_boots_2")
    public static Armor deadZaryaBoots2;

    @GameRegistry.ObjectHolder("stalmine:arm_zarya_duty_chest")
    public static Armor dutyZaryaChest;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_duty_head")
    public static Armor dutyZaryaHead;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_duty_legs")
    public static Armor dutyZaryaLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_duty_boots")
    public static Armor dutyZaryaBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_zarya_freedom_chest_1")
    public static Armor freedomZaryaChest1;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_freedom_head_1")
    public static Armor freedomZaryaHead1;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_freedom_legs_1")
    public static Armor freedomZaryaLegs1;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_freedom_boots_1")
    public static Armor freedomZaryaBoots1;

    @GameRegistry.ObjectHolder("stalmine:arm_zarya_freedom_chest_2")
    public static Armor freedomZaryaChest2;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_freedom_head_2")
    public static Armor freedomZaryaHead2;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_freedom_legs_2")
    public static Armor freedomZaryaLegs2;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_freedom_boots_2")
    public static Armor freedomZaryaBoots2;

    @GameRegistry.ObjectHolder("stalmine:arm_zarya_freedom_chest_3")
    public static Armor freedomZaryaChest3;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_freedom_head_3")
    public static Armor freedomZaryaHead3;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_freedom_legs_3")
    public static Armor freedomZaryaLegs3;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_freedom_boots_3")
    public static Armor freedomZaryaBoots3;

    @GameRegistry.ObjectHolder("stalmine:arm_zarya_monolith_chest")
    public static Armor monolithZaryaChest;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_monolith_head")
    public static Armor monolithZaryaHead;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_monolith_legs")
    public static Armor monolithZaryaLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_monolith_boots")
    public static Armor monolithZaryaBoots;

    @GameRegistry.ObjectHolder("stalmine:arm_zarya_stalker_chest")
    public static Armor stalkerZaryaChest;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_stalker_head")
    public static Armor stalkerZaryaHead;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_stalker_legs")
    public static Armor stalkerZaryaLegs;
    @GameRegistry.ObjectHolder("stalmine:arm_zarya_stalker_boots")
    public static Armor stalkerZaryaBoots;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        allArmor = new ArrayList<>();

        jacketChest.initModel();
        jacketHead.initModel();
        jacketLegs.initModel();
        jacketBoots.initModel();
        allArmor.add(jacketChest);
        allArmor.add(jacketHead);
        allArmor.add(jacketLegs);
        allArmor.add(jacketBoots);

        blackJacketChest.initModel();
        blackJacketHead.initModel();
        blackJacketLegs.initModel();
        blackJacketBoots.initModel();
        allArmor.add(blackJacketChest);
        allArmor.add(blackJacketHead);
        allArmor.add(blackJacketLegs);
        allArmor.add(blackJacketBoots);

        capeChest.initModel();
        capeHead.initModel();
        capeLegs.initModel();
        capeBoots.initModel();
        allArmor.add(capeChest);
        allArmor.add(capeHead);
        allArmor.add(capeLegs);
        allArmor.add(capeBoots);

        blackCapeChest.initModel();
        blackCapeHead.initModel();
        blackCapeLegs.initModel();
        blackCapeBoots.initModel();
        allArmor.add(blackCapeChest);
        allArmor.add(blackCapeHead);
        allArmor.add(blackCapeLegs);
        allArmor.add(blackCapeBoots);

        banditKombezChest.initModel();
        banditKombezHead.initModel();
        banditKombezLegs.initModel();
        banditKombezBoots.initModel();
        allArmor.add(banditKombezChest);
        allArmor.add(banditKombezHead);
        allArmor.add(banditKombezLegs);
        allArmor.add(banditKombezBoots);

        mercKombezChest1.initModel();
        mercKombezHead1.initModel();
        mercKombezLegs1.initModel();
        mercKombezBoots1.initModel();
        allArmor.add(mercKombezChest1);
        allArmor.add(mercKombezHead1);
        allArmor.add(mercKombezLegs1);
        allArmor.add(mercKombezBoots1);

        mercKombezChest2.initModel();
        mercKombezHead2.initModel();
        mercKombezLegs2.initModel();
        mercKombezBoots2.initModel();
        allArmor.add(mercKombezChest2);
        allArmor.add(mercKombezHead2);
        allArmor.add(mercKombezLegs2);
        allArmor.add(mercKombezBoots2);

        unknownKombezChest1.initModel();
        unknownKombezHead1.initModel();
        unknownKombezLegs1.initModel();
        unknownKombezBoots1.initModel();
        allArmor.add(unknownKombezChest1);
        allArmor.add(unknownKombezHead1);
        allArmor.add(unknownKombezLegs1);
        allArmor.add(unknownKombezBoots1);

        unknownKombezChest2.initModel();
        unknownKombezHead2.initModel();
        unknownKombezLegs2.initModel();
        unknownKombezBoots2.initModel();
        allArmor.add(unknownKombezChest2);
        allArmor.add(unknownKombezHead2);
        allArmor.add(unknownKombezLegs2);
        allArmor.add(unknownKombezBoots2);

        usKombezChest1.initModel();
        usKombezHead1.initModel();
        usKombezLegs1.initModel();
        usKombezBoots1.initModel();
        allArmor.add(usKombezChest1);
        allArmor.add(usKombezHead1);
        allArmor.add(usKombezLegs1);
        allArmor.add(usKombezBoots1);

        usKombezChest2.initModel();
        usKombezHead2.initModel();
        usKombezLegs2.initModel();
        usKombezBoots2.initModel();
        allArmor.add(usKombezChest2);
        allArmor.add(usKombezHead2);
        allArmor.add(usKombezLegs2);
        allArmor.add(usKombezBoots2);

        dutyHeavyChest.initModel();
        dutyHeavyHead.initModel();
        dutyHeavyLegs.initModel();
        dutyHeavyBoots.initModel();
        allArmor.add(dutyHeavyChest);
        allArmor.add(dutyHeavyHead);
        allArmor.add(dutyHeavyLegs);
        allArmor.add(dutyHeavyBoots);

        freedomHeavyChest.initModel();
        freedomHeavyHead.initModel();
        freedomHeavyLegs.initModel();
        freedomHeavyBoots.initModel();
        allArmor.add(freedomHeavyChest);
        allArmor.add(freedomHeavyHead);
        allArmor.add(freedomHeavyLegs);
        allArmor.add(freedomHeavyBoots);

        mercHeavyChest1.initModel();
        mercHeavyHead1.initModel();
        mercHeavyLegs1.initModel();
        mercHeavyBoots1.initModel();
        allArmor.add(mercHeavyChest1);
        allArmor.add(mercHeavyHead1);
        allArmor.add(mercHeavyLegs1);
        allArmor.add(mercHeavyBoots1);

        mercHeavyChest2.initModel();
        mercHeavyHead2.initModel();
        mercHeavyLegs2.initModel();
        mercHeavyBoots2.initModel();
        allArmor.add(mercHeavyChest2);
        allArmor.add(mercHeavyHead2);
        allArmor.add(mercHeavyLegs2);
        allArmor.add(mercHeavyBoots2);

        monolithHeavyChest1.initModel();
        monolithHeavyHead1.initModel();
        monolithHeavyLegs1.initModel();
        monolithHeavyBoots1.initModel();
        allArmor.add(monolithHeavyChest1);
        allArmor.add(monolithHeavyHead1);
        allArmor.add(monolithHeavyLegs1);
        allArmor.add(monolithHeavyBoots1);

        monolithHeavyChest2.initModel();
        monolithHeavyHead2.initModel();
        monolithHeavyLegs2.initModel();
        monolithHeavyBoots2.initModel();
        allArmor.add(monolithHeavyChest2);
        allArmor.add(monolithHeavyHead2);
        allArmor.add(monolithHeavyLegs2);
        allArmor.add(monolithHeavyBoots2);

        stalkerHeavyChest.initModel();
        stalkerHeavyHead.initModel();
        stalkerHeavyLegs.initModel();
        stalkerHeavyBoots.initModel();
        allArmor.add(stalkerHeavyChest);
        allArmor.add(stalkerHeavyHead);
        allArmor.add(stalkerHeavyLegs);
        allArmor.add(stalkerHeavyBoots);

        unknownHeavyChest.initModel();
        unknownHeavyHead.initModel();
        unknownHeavyLegs.initModel();
        unknownHeavyBoots.initModel();
        allArmor.add(unknownHeavyChest);
        allArmor.add(unknownHeavyHead);
        allArmor.add(unknownHeavyLegs);
        allArmor.add(unknownHeavyBoots);

        dutyExoChest.initModel();
        dutyExoHead.initModel();
        dutyExoLegs.initModel();
        dutyExoBoots.initModel();
        allArmor.add(dutyExoChest);
        allArmor.add(dutyExoHead);
        allArmor.add(dutyExoLegs);
        allArmor.add(dutyExoBoots);

        freedomExoChest.initModel();
        freedomExoHead.initModel();
        freedomExoLegs.initModel();
        freedomExoBoots.initModel();
        allArmor.add(freedomExoChest);
        allArmor.add(freedomExoHead);
        allArmor.add(freedomExoLegs);
        allArmor.add(freedomExoBoots);

        mercExoChest1.initModel();
        mercExoHead1.initModel();
        mercExoLegs1.initModel();
        mercExoBoots1.initModel();
        allArmor.add(mercExoChest1);
        allArmor.add(mercExoHead1);
        allArmor.add(mercExoLegs1);
        allArmor.add(mercExoBoots1);

        mercExoChest2.initModel();
        mercExoHead2.initModel();
        mercExoLegs2.initModel();
        mercExoBoots2.initModel();
        allArmor.add(mercExoChest2);
        allArmor.add(mercExoHead2);
        allArmor.add(mercExoLegs2);
        allArmor.add(mercExoBoots2);

        monolithExoChest1.initModel();
        monolithExoHead1.initModel();
        monolithExoLegs1.initModel();
        monolithExoBoots1.initModel();
        allArmor.add(monolithExoChest1);
        allArmor.add(monolithExoHead1);
        allArmor.add(monolithExoLegs1);
        allArmor.add(monolithExoBoots1);

        monolithExoChest2.initModel();
        monolithExoHead2.initModel();
        monolithExoLegs2.initModel();
        monolithExoBoots2.initModel();
        allArmor.add(monolithExoChest2);
        allArmor.add(monolithExoHead2);
        allArmor.add(monolithExoLegs2);
        allArmor.add(monolithExoBoots2);

        stalkerExoChest.initModel();
        stalkerExoHead.initModel();
        stalkerExoLegs.initModel();
        stalkerExoBoots.initModel();
        allArmor.add(stalkerExoChest);
        allArmor.add(stalkerExoHead);
        allArmor.add(stalkerExoLegs);
        allArmor.add(stalkerExoBoots);

        unknownExoChest.initModel();
        unknownExoHead.initModel();
        unknownExoLegs.initModel();
        unknownExoBoots.initModel();
        allArmor.add(unknownExoChest);
        allArmor.add(unknownExoHead);
        allArmor.add(unknownExoLegs);
        allArmor.add(unknownExoBoots);

        dutySevaChest.initModel();
        dutySevaHead.initModel();
        dutySevaLegs.initModel();
        dutySevaBoots.initModel();
        allArmor.add(dutySevaChest);
        allArmor.add(dutySevaHead);
        allArmor.add(dutySevaLegs);
        allArmor.add(dutySevaBoots);

        freedomSevaChest.initModel();
        freedomSevaHead.initModel();
        freedomSevaLegs.initModel();
        freedomSevaBoots.initModel();
        allArmor.add(freedomSevaChest);
        allArmor.add(freedomSevaHead);
        allArmor.add(freedomSevaLegs);
        allArmor.add(freedomSevaBoots);

        mercSevaChest.initModel();
        mercSevaHead.initModel();
        mercSevaLegs.initModel();
        mercSevaBoots.initModel();
        allArmor.add(mercSevaChest);
        allArmor.add(mercSevaHead);
        allArmor.add(mercSevaLegs);
        allArmor.add(mercSevaBoots);

        monolithSevaChest.initModel();
        monolithSevaHead.initModel();
        monolithSevaLegs.initModel();
        monolithSevaBoots.initModel();
        allArmor.add(monolithSevaChest);
        allArmor.add(monolithSevaHead);
        allArmor.add(monolithSevaLegs);
        allArmor.add(monolithSevaBoots);

        stalkerSevaChest.initModel();
        stalkerSevaHead.initModel();
        stalkerSevaLegs.initModel();
        stalkerSevaBoots.initModel();
        allArmor.add(stalkerSevaChest);
        allArmor.add(stalkerSevaHead);
        allArmor.add(stalkerSevaLegs);
        allArmor.add(stalkerSevaBoots);

        scientist1Chest.initModel();
        scientist1Head.initModel();
        scientist1Legs.initModel();
        scientist1Boots.initModel();
        allArmor.add(scientist1Chest);
        allArmor.add(scientist1Head);
        allArmor.add(scientist1Legs);
        allArmor.add(scientist1Boots);

        scientist2Chest.initModel();
        scientist2Head.initModel();
        scientist2Legs.initModel();
        scientist2Boots.initModel();
        allArmor.add(scientist2Chest);
        allArmor.add(scientist2Head);
        allArmor.add(scientist2Legs);
        allArmor.add(scientist2Boots);

        scientist3Chest.initModel();
        scientist3Head.initModel();
        scientist3Legs.initModel();
        scientist3Boots.initModel();
        allArmor.add(scientist3Chest);
        allArmor.add(scientist3Head);
        allArmor.add(scientist3Legs);
        allArmor.add(scientist3Boots);

        scientist4Chest.initModel();
        scientist4Head.initModel();
        scientist4Legs.initModel();
        scientist4Boots.initModel();
        allArmor.add(scientist4Chest);
        allArmor.add(scientist4Head);
        allArmor.add(scientist4Legs);
        allArmor.add(scientist4Boots);

        deadBerillChest.initModel();
        deadBerillHead.initModel();
        deadBerillLegs.initModel();
        deadBerillBoots.initModel();
        allArmor.add(deadBerillChest);
        allArmor.add(deadBerillHead);
        allArmor.add(deadBerillLegs);
        allArmor.add(deadBerillBoots);

        freedomBerillChest.initModel();
        freedomBerillHead.initModel();
        freedomBerillLegs.initModel();
        freedomBerillBoots.initModel();
        allArmor.add(freedomBerillChest);
        allArmor.add(freedomBerillHead);
        allArmor.add(freedomBerillLegs);
        allArmor.add(freedomBerillBoots);

        militaryBerillChest.initModel();
        militaryBerillHead.initModel();
        militaryBerillLegs.initModel();
        militaryBerillBoots.initModel();
        allArmor.add(militaryBerillChest);
        allArmor.add(militaryBerillHead);
        allArmor.add(militaryBerillLegs);
        allArmor.add(militaryBerillBoots);

        deadBulatChest.initModel();
        deadBulatHead.initModel();
        deadBulatLegs.initModel();
        deadBulatBoots.initModel();
        allArmor.add(deadBulatChest);
        allArmor.add(deadBulatHead);
        allArmor.add(deadBulatLegs);
        allArmor.add(deadBulatBoots);

        dutyBulatChest.initModel();
        dutyBulatHead.initModel();
        dutyBulatLegs.initModel();
        dutyBulatBoots.initModel();
        allArmor.add(dutyBulatChest);
        allArmor.add(dutyBulatHead);
        allArmor.add(dutyBulatLegs);
        allArmor.add(dutyBulatBoots);

        freedomBulatChest.initModel();
        freedomBulatHead.initModel();
        freedomBulatLegs.initModel();
        freedomBulatBoots.initModel();
        allArmor.add(freedomBulatChest);
        allArmor.add(freedomBulatHead);
        allArmor.add(freedomBulatLegs);
        allArmor.add(freedomBulatBoots);

        mercBulatChest.initModel();
        mercBulatHead.initModel();
        mercBulatLegs.initModel();
        mercBulatBoots.initModel();
        allArmor.add(mercBulatChest);
        allArmor.add(mercBulatHead);
        allArmor.add(mercBulatLegs);
        allArmor.add(mercBulatBoots);

        militaryBulatChest.initModel();
        militaryBulatHead.initModel();
        militaryBulatLegs.initModel();
        militaryBulatBoots.initModel();
        allArmor.add(militaryBulatChest);
        allArmor.add(militaryBulatHead);
        allArmor.add(militaryBulatLegs);
        allArmor.add(militaryBulatBoots);

        deadZaryaChest1.initModel();
        deadZaryaHead1.initModel();
        deadZaryaLegs1.initModel();
        deadZaryaBoots1.initModel();
        allArmor.add(deadZaryaChest1);
        allArmor.add(deadZaryaHead1);
        allArmor.add(deadZaryaLegs1);
        allArmor.add(deadZaryaBoots1);

        deadZaryaChest2.initModel();
        deadZaryaHead2.initModel();
        deadZaryaLegs2.initModel();
        deadZaryaBoots2.initModel();
        allArmor.add(deadZaryaChest2);
        allArmor.add(deadZaryaHead2);
        allArmor.add(deadZaryaLegs2);
        allArmor.add(deadZaryaBoots2);

        dutyZaryaChest.initModel();
        dutyZaryaHead.initModel();
        dutyZaryaLegs.initModel();
        dutyZaryaBoots.initModel();
        allArmor.add(dutyZaryaChest);
        allArmor.add(dutyZaryaHead);
        allArmor.add(dutyZaryaLegs);
        allArmor.add(dutyZaryaBoots);

        freedomZaryaChest1.initModel();
        freedomZaryaHead1.initModel();
        freedomZaryaLegs1.initModel();
        freedomZaryaBoots1.initModel();
        allArmor.add(freedomZaryaChest1);
        allArmor.add(freedomZaryaHead1);
        allArmor.add(freedomZaryaLegs1);
        allArmor.add(freedomZaryaBoots1);

        freedomZaryaChest2.initModel();
        freedomZaryaHead2.initModel();
        freedomZaryaLegs2.initModel();
        freedomZaryaBoots2.initModel();
        allArmor.add(freedomZaryaChest2);
        allArmor.add(freedomZaryaHead2);
        allArmor.add(freedomZaryaLegs2);
        allArmor.add(freedomZaryaBoots2);

        freedomZaryaChest3.initModel();
        freedomZaryaHead3.initModel();
        freedomZaryaLegs3.initModel();
        freedomZaryaBoots3.initModel();
        allArmor.add(freedomZaryaChest3);
        allArmor.add(freedomZaryaHead3);
        allArmor.add(freedomZaryaLegs3);
        allArmor.add(freedomZaryaBoots3);

        monolithZaryaChest.initModel();
        monolithZaryaHead.initModel();
        monolithZaryaLegs.initModel();
        monolithZaryaBoots.initModel();
        allArmor.add(monolithZaryaChest);
        allArmor.add(monolithZaryaHead);
        allArmor.add(monolithZaryaLegs);
        allArmor.add(monolithZaryaBoots);

        stalkerZaryaChest.initModel();
        stalkerZaryaHead.initModel();
        stalkerZaryaLegs.initModel();
        stalkerZaryaBoots.initModel();
        allArmor.add(stalkerZaryaChest);
        allArmor.add(stalkerZaryaHead);
        allArmor.add(stalkerZaryaLegs);
        allArmor.add(stalkerZaryaBoots);

    }
}
