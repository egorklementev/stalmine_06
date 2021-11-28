package ru.erked.stalmine.client.models;

import net.minecraft.client.model.ModelBiped;

import java.util.HashMap;

public class StalmineModels {

    private static int armId = 1;
    public static final ModelStalminePlayer playerModel = new ModelStalminePlayer(0f);

    private static final ModelJacket m_jacket = new ModelJacket("arm_jacket", armId++);
    private static final ModelJacket m_black_jacket = new ModelJacket("arm_black_jacket", armId++);

    private static final ModelCape m_cape = new ModelCape("arm_cape", armId++);
    private static final ModelCape m_black_cape = new ModelCape("arm_black_cape", armId++);

    private static final ModelKombez m_kombez_bandit = new ModelKombez("arm_bandit_kombez", armId++);
    private static final ModelKombez m_kombez_merc_1 = new ModelKombez("arm_merc_kombez_1", armId++);
    private static final ModelKombez m_kombez_merc_2 = new ModelKombez("arm_merc_kombez_2", armId++);
    private static final ModelKombez m_kombez_unknown_1 = new ModelKombez("arm_unknown_kombez_1", armId++);
    private static final ModelKombez m_kombez_unknown_2 = new ModelKombez("arm_unknown_kombez_2", armId++);
    private static final ModelKombez m_kombez_us_1 = new ModelKombez("arm_us_kombez_1", armId++);
    private static final ModelKombez m_kombez_us_2 = new ModelKombez("arm_us_kombez_2", armId++);

    private static final ModelHeavy m_heavy_duty = new ModelHeavy("arm_heavy_duty", armId++);
    private static final ModelHeavy m_heavy_freedom = new ModelHeavy("arm_heavy_freedom", armId++);
    private static final ModelHeavy m_heavy_merc_1 = new ModelHeavy("arm_heavy_merc_1", armId++);
    private static final ModelHeavy m_heavy_merc_2 = new ModelHeavy("arm_heavy_merc_2", armId++);
    private static final ModelHeavy m_heavy_monolith_1 = new ModelHeavy("arm_heavy_monolith_1", armId++);
    private static final ModelHeavy m_heavy_monolith_2 = new ModelHeavy("arm_heavy_monolith_2", armId++);
    private static final ModelHeavy m_heavy_stalker = new ModelHeavy("arm_heavy_stalker", armId++);
    private static final ModelHeavy m_heavy_unknown = new ModelHeavy("arm_heavy_unknown", armId++);

    private static final ModelExo m_exo_duty = new ModelExo("arm_heavy_duty", armId++);
    private static final ModelExo m_exo_freedom = new ModelExo("arm_heavy_freedom", armId++);
    private static final ModelExo m_exo_merc_1 = new ModelExo("arm_heavy_merc_1", armId++);
    private static final ModelExo m_exo_merc_2 = new ModelExo("arm_heavy_merc_2", armId++);
    private static final ModelExo m_exo_monolith_1 = new ModelExo("arm_heavy_monolith_1", armId++);
    private static final ModelExo m_exo_monolith_2 = new ModelExo("arm_heavy_monolith_2", armId++);
    private static final ModelExo m_exo_stalker = new ModelExo("arm_heavy_stalker", armId++);
    private static final ModelExo m_exo_unknown = new ModelExo("arm_heavy_unknown", armId++);

    private static final ModelSeva m_seva_duty = new ModelSeva("arm_seva_duty", armId++);
    private static final ModelSeva m_seva_freedom = new ModelSeva("arm_seva_freedom", armId++);
    private static final ModelSeva m_seva_merc = new ModelSeva("arm_seva_merc", armId++);
    private static final ModelSeva m_seva_monolith = new ModelSeva("arm_seva_monolith", armId++);
    private static final ModelSeva m_seva_stalker = new ModelSeva("arm_seva_stalker", armId++);

    private static final ModelScientist m_scientist_1 = new ModelScientist("arm_scientist_1", armId++);
    private static final ModelScientist m_scientist_2 = new ModelScientist("arm_scientist_2", armId++);
    private static final ModelScientist m_scientist_3 = new ModelScientist("arm_scientist_3", armId++);
    private static final ModelScientist m_scientist_4 = new ModelScientist("arm_scientist_4", armId++);

    private static final ModelBerill m_berill_dead = new ModelBerill("arm_berill_dead", armId++);
    private static final ModelBerill m_berill_freedom = new ModelBerill("arm_berill_freedom", armId++);
    private static final ModelBerill m_berill_military = new ModelBerill("arm_berill_military", armId++);

    private static final ModelBulat m_bulat_dead = new ModelBulat("arm_bulat_dead", armId++);
    private static final ModelBulat m_bulat_duty = new ModelBulat("arm_bulat_duty", armId++);
    private static final ModelBulat m_bulat_freedom = new ModelBulat("arm_bulat_freedom", armId++);
    private static final ModelBulat m_bulat_merc = new ModelBulat("arm_bulat_merc", armId++);
    private static final ModelBulat m_bulat_military = new ModelBulat("arm_bulat_military", armId++);

    private static final ModelZarya m_zarya_dead_1 = new ModelZarya("arm_zarya_dead_1", armId++);
    private static final ModelZarya m_zarya_dead_2 = new ModelZarya("arm_zarya_dead_2", armId++);
    private static final ModelZarya m_zarya_duty = new ModelZarya("arm_zarya_duty", armId++);
    private static final ModelZarya m_zarya_freedom_1 = new ModelZarya("arm_zarya_freedom_1", armId++);
    private static final ModelZarya m_zarya_freedom_2 = new ModelZarya("arm_zarya_freedom_2", armId++);
    private static final ModelZarya m_zarya_freedom_3 = new ModelZarya("arm_zarya_freedom_3", armId++);
    private static final ModelZarya m_zarya_monolith = new ModelZarya("arm_zarya_monolith", armId++);
    private static final ModelZarya m_zarya_stalker = new ModelZarya("arm_zarya_stalker", armId++);

    public static HashMap<String, ModelBiped> pool = new HashMap<String, ModelBiped>() {
        {
            put("arm_jacket_chest", m_jacket);
            put("arm_jacket_head", m_jacket);
            put("arm_jacket_legs", m_jacket);
            put("arm_jacket_boots", m_jacket);
            put("arm_jacket_black_chest", m_black_jacket);
            put("arm_jacket_black_head", m_black_jacket);
            put("arm_jacket_black_legs", m_black_jacket);
            put("arm_jacket_black_boots", m_black_jacket);
            put("arm_cape_chest", m_cape);
            put("arm_cape_head", m_cape);
            put("arm_cape_legs", m_cape);
            put("arm_cape_boots", m_cape);
            put("arm_cape_black_chest", m_black_cape);
            put("arm_cape_black_head", m_black_cape);
            put("arm_cape_black_legs", m_black_cape);
            put("arm_cape_black_boots", m_black_cape);
            put("arm_kombez_merc_chest_1", m_kombez_merc_1);
            put("arm_kombez_merc_head_1", m_kombez_merc_1);
            put("arm_kombez_merc_legs_1", m_kombez_merc_1);
            put("arm_kombez_merc_boots_1", m_kombez_merc_1);
            put("arm_kombez_merc_chest_2", m_kombez_merc_2);
            put("arm_kombez_merc_head_2", m_kombez_merc_2);
            put("arm_kombez_merc_legs_2", m_kombez_merc_2);
            put("arm_kombez_merc_boots_2", m_kombez_merc_2);
            put("arm_kombez_bandit_chest", m_kombez_bandit);
            put("arm_kombez_bandit_head", m_kombez_bandit);
            put("arm_kombez_bandit_legs", m_kombez_bandit);
            put("arm_kombez_bandit_boots", m_kombez_bandit);
            put("arm_kombez_unknown_chest_1", m_kombez_unknown_1);
            put("arm_kombez_unknown_head_1", m_kombez_unknown_1);
            put("arm_kombez_unknown_legs_1", m_kombez_unknown_1);
            put("arm_kombez_unknown_boots_1", m_kombez_unknown_1);
            put("arm_kombez_unknown_chest_2", m_kombez_unknown_2);
            put("arm_kombez_unknown_head_2", m_kombez_unknown_2);
            put("arm_kombez_unknown_legs_2", m_kombez_unknown_2);
            put("arm_kombez_unknown_boots_2", m_kombez_unknown_2);
            put("arm_kombez_us_chest_1", m_kombez_us_1);
            put("arm_kombez_us_head_1", m_kombez_us_1);
            put("arm_kombez_us_legs_1", m_kombez_us_1);
            put("arm_kombez_us_boots_1", m_kombez_us_1);
            put("arm_kombez_us_chest_2", m_kombez_us_2);
            put("arm_kombez_us_head_2", m_kombez_us_2);
            put("arm_kombez_us_legs_2", m_kombez_us_2);
            put("arm_kombez_us_boots_2", m_kombez_us_2);
            put("arm_heavy_duty_chest", m_heavy_duty);
            put("arm_heavy_duty_head", m_heavy_duty);
            put("arm_heavy_duty_legs", m_heavy_duty);
            put("arm_heavy_duty_boots", m_heavy_duty);
            put("arm_heavy_freedom_chest", m_heavy_freedom);
            put("arm_heavy_freedom_head", m_heavy_freedom);
            put("arm_heavy_freedom_legs", m_heavy_freedom);
            put("arm_heavy_freedom_boots", m_heavy_freedom);
            put("arm_heavy_merc_chest_1", m_heavy_merc_1);
            put("arm_heavy_merc_head_1", m_heavy_merc_1);
            put("arm_heavy_merc_legs_1", m_heavy_merc_1);
            put("arm_heavy_merc_boots_1", m_heavy_merc_1);
            put("arm_heavy_merc_chest_2", m_heavy_merc_2);
            put("arm_heavy_merc_head_2", m_heavy_merc_2);
            put("arm_heavy_merc_legs_2", m_heavy_merc_2);
            put("arm_heavy_merc_boots_2", m_heavy_merc_2);
            put("arm_heavy_monolith_chest_1", m_heavy_monolith_1);
            put("arm_heavy_monolith_head_1", m_heavy_monolith_1);
            put("arm_heavy_monolith_legs_1", m_heavy_monolith_1);
            put("arm_heavy_monolith_boots_1", m_heavy_monolith_1);
            put("arm_heavy_monolith_chest_2", m_heavy_monolith_2);
            put("arm_heavy_monolith_head_2", m_heavy_monolith_2);
            put("arm_heavy_monolith_legs_2", m_heavy_monolith_2);
            put("arm_heavy_monolith_boots_2", m_heavy_monolith_2);
            put("arm_heavy_stalker_chest", m_heavy_stalker);
            put("arm_heavy_stalker_head", m_heavy_stalker);
            put("arm_heavy_stalker_legs", m_heavy_stalker);
            put("arm_heavy_stalker_boots", m_heavy_stalker);
            put("arm_heavy_unknown_chest", m_heavy_unknown);
            put("arm_heavy_unknown_head", m_heavy_unknown);
            put("arm_heavy_unknown_legs", m_heavy_unknown);
            put("arm_heavy_unknown_boots", m_heavy_unknown);
            put("arm_exo_duty_chest", m_exo_duty);
            put("arm_exo_duty_head", m_exo_duty);
            put("arm_exo_duty_legs", m_exo_duty);
            put("arm_exo_duty_boots", m_exo_duty);
            put("arm_exo_freedom_chest", m_exo_freedom);
            put("arm_exo_freedom_head", m_exo_freedom);
            put("arm_exo_freedom_legs", m_exo_freedom);
            put("arm_exo_freedom_boots", m_exo_freedom);
            put("arm_exo_merc_chest_1", m_exo_merc_1);
            put("arm_exo_merc_head_1", m_exo_merc_1);
            put("arm_exo_merc_legs_1", m_exo_merc_1);
            put("arm_exo_merc_boots_1", m_exo_merc_1);
            put("arm_exo_merc_chest_2", m_exo_merc_2);
            put("arm_exo_merc_head_2", m_exo_merc_2);
            put("arm_exo_merc_legs_2", m_exo_merc_2);
            put("arm_exo_merc_boots_2", m_exo_merc_2);
            put("arm_exo_monolith_chest_1", m_exo_monolith_1);
            put("arm_exo_monolith_head_1", m_exo_monolith_1);
            put("arm_exo_monolith_legs_1", m_exo_monolith_1);
            put("arm_exo_monolith_boots_1", m_exo_monolith_1);
            put("arm_exo_monolith_chest_2", m_exo_monolith_2);
            put("arm_exo_monolith_head_2", m_exo_monolith_2);
            put("arm_exo_monolith_legs_2", m_exo_monolith_2);
            put("arm_exo_monolith_boots_2", m_exo_monolith_2);
            put("arm_exo_stalker_chest", m_exo_stalker);
            put("arm_exo_stalker_head", m_exo_stalker);
            put("arm_exo_stalker_legs", m_exo_stalker);
            put("arm_exo_stalker_boots", m_exo_stalker);
            put("arm_exo_unknown_chest", m_exo_unknown);
            put("arm_exo_unknown_head", m_exo_unknown);
            put("arm_exo_unknown_legs", m_exo_unknown);
            put("arm_exo_unknown_boots", m_exo_unknown);
            put("arm_seva_duty_chest", m_seva_duty);
            put("arm_seva_duty_head", m_seva_duty);
            put("arm_seva_duty_legs", m_seva_duty);
            put("arm_seva_duty_boots", m_seva_duty);
            put("arm_seva_duty_chest", m_seva_duty);
            put("arm_seva_duty_head", m_seva_duty);
            put("arm_seva_duty_legs", m_seva_duty);
            put("arm_seva_duty_boots", m_seva_duty);
            put("arm_seva_freedom_chest", m_seva_freedom);
            put("arm_seva_freedom_head", m_seva_freedom);
            put("arm_seva_freedom_legs", m_seva_freedom);
            put("arm_seva_freedom_boots", m_seva_freedom);
            put("arm_seva_merc_chest", m_seva_merc);
            put("arm_seva_merc_head", m_seva_merc);
            put("arm_seva_merc_legs", m_seva_merc);
            put("arm_seva_merc_boots", m_seva_merc);
            put("arm_seva_monolith_chest", m_seva_monolith);
            put("arm_seva_monolith_head", m_seva_monolith);
            put("arm_seva_monolith_legs", m_seva_monolith);
            put("arm_seva_monolith_boots", m_seva_monolith);
            put("arm_seva_stalker_chest", m_seva_stalker);
            put("arm_seva_stalker_head", m_seva_stalker);
            put("arm_seva_stalker_legs", m_seva_stalker);
            put("arm_seva_stalker_boots", m_seva_stalker);
            put("arm_scientist_chest_1", m_scientist_1);
            put("arm_scientist_head_1", m_scientist_1);
            put("arm_scientist_legs_1", m_scientist_1);
            put("arm_scientist_boots_1", m_scientist_1);
            put("arm_scientist_chest_2", m_scientist_2);
            put("arm_scientist_head_2", m_scientist_2);
            put("arm_scientist_legs_2", m_scientist_2);
            put("arm_scientist_boots_2", m_scientist_2);
            put("arm_scientist_chest_3", m_scientist_3);
            put("arm_scientist_head_3", m_scientist_3);
            put("arm_scientist_legs_3", m_scientist_3);
            put("arm_scientist_boots_3", m_scientist_3);
            put("arm_scientist_chest_4", m_scientist_4);
            put("arm_scientist_head_4", m_scientist_4);
            put("arm_scientist_legs_4", m_scientist_4);
            put("arm_scientist_boots_4", m_scientist_4);
            put("arm_berill_dead_chest", m_berill_dead);
            put("arm_berill_dead_head", m_berill_dead);
            put("arm_berill_dead_legs", m_berill_dead);
            put("arm_berill_dead_boots", m_berill_dead);
            put("arm_berill_freedom_chest", m_berill_freedom);
            put("arm_berill_freedom_head", m_berill_freedom);
            put("arm_berill_freedom_legs", m_berill_freedom);
            put("arm_berill_freedom_boots", m_berill_freedom);
            put("arm_berill_military_chest", m_berill_military);
            put("arm_berill_military_head", m_berill_military);
            put("arm_berill_military_legs", m_berill_military);
            put("arm_berill_military_boots", m_berill_military);
            put("arm_bulat_dead_chest", m_bulat_dead);
            put("arm_bulat_dead_head", m_bulat_dead);
            put("arm_bulat_dead_legs", m_bulat_dead);
            put("arm_bulat_dead_boots", m_bulat_dead);
            put("arm_bulat_duty_chest", m_bulat_duty);
            put("arm_bulat_duty_head", m_bulat_duty);
            put("arm_bulat_duty_legs", m_bulat_duty);
            put("arm_bulat_duty_boots", m_bulat_duty);
            put("arm_bulat_freedom_chest", m_bulat_freedom);
            put("arm_bulat_freedom_head", m_bulat_freedom);
            put("arm_bulat_freedom_legs", m_bulat_freedom);
            put("arm_bulat_freedom_boots", m_bulat_freedom);
            put("arm_bulat_merc_chest", m_bulat_merc);
            put("arm_bulat_merc_head", m_bulat_merc);
            put("arm_bulat_merc_legs", m_bulat_merc);
            put("arm_bulat_merc_boots", m_bulat_merc);
            put("arm_bulat_military_chest", m_bulat_military);
            put("arm_bulat_military_head", m_bulat_military);
            put("arm_bulat_military_legs", m_bulat_military);
            put("arm_bulat_military_boots", m_bulat_military);
            put("arm_zarya_dead_chest_1", m_zarya_dead_1);
            put("arm_zarya_dead_head_1", m_zarya_dead_1);
            put("arm_zarya_dead_legs_1", m_zarya_dead_1);
            put("arm_zarya_dead_boots_1", m_zarya_dead_1);
            put("arm_zarya_dead_chest_2", m_zarya_dead_2);
            put("arm_zarya_dead_head_2", m_zarya_dead_2);
            put("arm_zarya_dead_legs_2", m_zarya_dead_2);
            put("arm_zarya_dead_boots_2", m_zarya_dead_2);
            put("arm_zarya_duty_chest", m_zarya_duty);
            put("arm_zarya_duty_head", m_zarya_duty);
            put("arm_zarya_duty_legs", m_zarya_duty);
            put("arm_zarya_duty_boots", m_zarya_duty);
            put("arm_zarya_freedom_chest_1", m_zarya_freedom_1);
            put("arm_zarya_freedom_head_1", m_zarya_freedom_1);
            put("arm_zarya_freedom_legs_1", m_zarya_freedom_1);
            put("arm_zarya_freedom_boots_1", m_zarya_freedom_1);
            put("arm_zarya_freedom_chest_2", m_zarya_freedom_2);
            put("arm_zarya_freedom_head_2", m_zarya_freedom_2);
            put("arm_zarya_freedom_legs_2", m_zarya_freedom_2);
            put("arm_zarya_freedom_boots_2", m_zarya_freedom_2);
            put("arm_zarya_freedom_chest_3", m_zarya_freedom_3);
            put("arm_zarya_freedom_head_3", m_zarya_freedom_3);
            put("arm_zarya_freedom_legs_3", m_zarya_freedom_3);
            put("arm_zarya_freedom_boots_3", m_zarya_freedom_3);
            put("arm_zarya_monolith_chest", m_zarya_monolith);
            put("arm_zarya_monolith_head", m_zarya_monolith);
            put("arm_zarya_monolith_legs", m_zarya_monolith);
            put("arm_zarya_monolith_boots", m_zarya_monolith);
            put("arm_zarya_stalker_chest", m_zarya_stalker);
            put("arm_zarya_stalker_head", m_zarya_stalker);
            put("arm_zarya_stalker_legs", m_zarya_stalker);
            put("arm_zarya_stalker_boots", m_zarya_stalker);
        }
    };

    public enum ArmType {
        HEAD,
        CHEST,
        ARML,
        ARMR,
        LEGL,
        LEGR,
        BOOT
    }

}
