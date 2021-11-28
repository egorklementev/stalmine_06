package ru.erked.stalmine.client.particle;

import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.util.EnumHelper;
import ru.erked.stalmine.StalmineMod;

public class StalmineParticles {

    public static EnumParticleTypes FIREPLACE_SMOKE;
    public static EnumParticleTypes FIREPLACE_FIRE;

    public static EnumParticleTypes LAMP_FIRE;

    public static EnumParticleTypes RAD_0;
    public static EnumParticleTypes RAD_1;
    public static EnumParticleTypes RAD_2;
    public static EnumParticleTypes RAD_3;

    public static EnumParticleTypes PSY_0;
    public static EnumParticleTypes PSY_1;
    public static EnumParticleTypes PSY_2;
    public static EnumParticleTypes PSY_3;

    public static EnumParticleTypes CHEM_0;
    public static EnumParticleTypes CHEM_1;
    public static EnumParticleTypes CHEM_2;
    public static EnumParticleTypes CHEM_3;

    public static EnumParticleTypes TERM_0;
    public static EnumParticleTypes TERM_1;
    public static EnumParticleTypes TERM_2;
    public static EnumParticleTypes TERM_3;

    public static EnumParticleTypes ARTIFACT;

    public static EnumParticleTypes ELECTRA_FAR;
    public static EnumParticleTypes ELECTRA_CENTER;
    public static EnumParticleTypes ELECTRA_BLAST;
    public static EnumParticleTypes ELECTRA_BLAST_CENTER;

    public static EnumParticleTypes FUNNEL_DUST;
    public static EnumParticleTypes FUNNEL_LEAVES;
    public static EnumParticleTypes FUNNEL_FLESH;
    public static EnumParticleTypes FUNNEL_BLAST;

    public static EnumParticleTypes SPRINGBOARD_DUST;
    public static EnumParticleTypes SPRINGBOARD_BLAST;

    public static EnumParticleTypes CAROUSEL_BLAST;
    public static EnumParticleTypes CAROUSEL_CENTER;

    public static EnumParticleTypes DEEPFRY_BLAST;
    public static EnumParticleTypes STEAM_BLAST;
    public static EnumParticleTypes SODA_BLAST;
    public static EnumParticleTypes KISSEL;
    public static EnumParticleTypes TELEPORT;

    public static void registration() {
        Class<?>[] particlesParams = {
                String.class, int.class, boolean.class
        };

        int id = 1456278124;
        FIREPLACE_SMOKE = EnumHelper.addEnum(EnumParticleTypes.class, "FIREPLACE_SMOKE", particlesParams,
                "fireplace_smoke", id++, false);
        FIREPLACE_FIRE = EnumHelper.addEnum(EnumParticleTypes.class, "FIREPLACE_FIRE", particlesParams,
                "fireplace_fire", id++, false);

        RAD_0 = EnumHelper.addEnum(EnumParticleTypes.class, "RAD_0", particlesParams,
                "rad_0", id++, false);
        RAD_1 = EnumHelper.addEnum(EnumParticleTypes.class, "RAD_1", particlesParams,
                "rad_1", id++, false);
        RAD_2 = EnumHelper.addEnum(EnumParticleTypes.class, "RAD_2", particlesParams,
                "rad_2", id++, false);
        RAD_3 = EnumHelper.addEnum(EnumParticleTypes.class, "RAD_3", particlesParams,
                "rad_3", id++, false);

        PSY_0 = EnumHelper.addEnum(EnumParticleTypes.class, "PSY_0", particlesParams,
                "psy_0", id++, false);
        PSY_1 = EnumHelper.addEnum(EnumParticleTypes.class, "PSY_1", particlesParams,
                "psy_1", id++, false);
        PSY_2 = EnumHelper.addEnum(EnumParticleTypes.class, "PSY_2", particlesParams,
                "psy_2", id++, false);
        PSY_3 = EnumHelper.addEnum(EnumParticleTypes.class, "PSY_3", particlesParams,
                "psy_3", id++, false);

        CHEM_0 = EnumHelper.addEnum(EnumParticleTypes.class, "CHEM_0", particlesParams,
                "chem_0", id++, false);
        CHEM_1 = EnumHelper.addEnum(EnumParticleTypes.class, "CHEM_1", particlesParams,
                "chem_1", id++, false);
        CHEM_2 = EnumHelper.addEnum(EnumParticleTypes.class, "CHEM_2", particlesParams,
                "chem_2", id++, false);
        CHEM_3 = EnumHelper.addEnum(EnumParticleTypes.class, "CHEM_3", particlesParams,
                "chem_3", id++, false);

        TERM_0 = EnumHelper.addEnum(EnumParticleTypes.class, "TERM_0", particlesParams,
                "term_0", id++, false);
        TERM_1 = EnumHelper.addEnum(EnumParticleTypes.class, "TERM_1", particlesParams,
                "term_1", id++, false);
        TERM_2 = EnumHelper.addEnum(EnumParticleTypes.class, "TERM_2", particlesParams,
                "term_2", id++, false);
        TERM_3 = EnumHelper.addEnum(EnumParticleTypes.class, "TERM_3", particlesParams,
                "term_3", id++, false);

        ARTIFACT = EnumHelper.addEnum(EnumParticleTypes.class, "artifact", particlesParams,
                "artifact", id++, false);

        ELECTRA_FAR = EnumHelper.addEnum(EnumParticleTypes.class, "electra_far", particlesParams,
                "electra_far", id++, false);
        ELECTRA_CENTER = EnumHelper.addEnum(EnumParticleTypes.class, "electra_center", particlesParams,
                "electra_center", id++, false);
        ELECTRA_BLAST = EnumHelper.addEnum(EnumParticleTypes.class, "electra_blast", particlesParams,
                "electra_blast", id++, false);
        ELECTRA_BLAST_CENTER = EnumHelper.addEnum(EnumParticleTypes.class, "electra_blast_center", particlesParams,
                "electra_blast_center", id++, false);


        FUNNEL_DUST = EnumHelper.addEnum(EnumParticleTypes.class, "funnel_dust", particlesParams,
                "funnel_dust", id++, false);
        FUNNEL_FLESH = EnumHelper.addEnum(EnumParticleTypes.class, "funnel_flesh", particlesParams,
                "funnel_flesh", id++, false);
        FUNNEL_LEAVES = EnumHelper.addEnum(EnumParticleTypes.class, "funnel_leaves", particlesParams,
                "funnel_leaves", id++, false);
        FUNNEL_BLAST = EnumHelper.addEnum(EnumParticleTypes.class, "funnel_blast", particlesParams,
                "funnel_blast", id++, false);

        SPRINGBOARD_DUST = EnumHelper.addEnum(EnumParticleTypes.class, "springboard_dust", particlesParams,
                "springboard_dust", id++, false);
        SPRINGBOARD_BLAST = EnumHelper.addEnum(EnumParticleTypes.class, "springboard_blast", particlesParams,
                "springboard_blast", id++, false);

        CAROUSEL_BLAST = EnumHelper.addEnum(EnumParticleTypes.class, "carousel_blast", particlesParams,
                "carousel_blast", id++, false);
        CAROUSEL_CENTER = EnumHelper.addEnum(EnumParticleTypes.class, "carousel_center", particlesParams,
                "carousel_center", id++, false);

        DEEPFRY_BLAST = EnumHelper.addEnum(EnumParticleTypes.class, "deepfry_blast", particlesParams,
                "deepfry_blast", id++, false);
        STEAM_BLAST = EnumHelper.addEnum(EnumParticleTypes.class, "steam_blast", particlesParams,
                "steam_blast", id++, false);
        SODA_BLAST = EnumHelper.addEnum(EnumParticleTypes.class, "soda_blast", particlesParams,
                "soda_blast", id++, false);
        KISSEL = EnumHelper.addEnum(EnumParticleTypes.class, "kissel", particlesParams,
                "kissel", id++, false);

        LAMP_FIRE = EnumHelper.addEnum(EnumParticleTypes.class, "LAMP_FIRE", particlesParams,
                "lamp_fire", id++, false);

        TELEPORT = EnumHelper.addEnum(EnumParticleTypes.class, "teleport", particlesParams,
                "teleport", id++, false);

        EnumParticleTypes.PARTICLES.put(FIREPLACE_SMOKE.getParticleID(), FIREPLACE_SMOKE);
        EnumParticleTypes.BY_NAME.put(FIREPLACE_SMOKE.getParticleName(), FIREPLACE_SMOKE);

        EnumParticleTypes.PARTICLES.put(FIREPLACE_FIRE.getParticleID(), FIREPLACE_FIRE);
        EnumParticleTypes.BY_NAME.put(FIREPLACE_FIRE.getParticleName(), FIREPLACE_FIRE);

        EnumParticleTypes.PARTICLES.put(RAD_0.getParticleID(), RAD_0);
        EnumParticleTypes.BY_NAME.put(RAD_0.getParticleName(), RAD_0);
        EnumParticleTypes.PARTICLES.put(RAD_1.getParticleID(), RAD_1);
        EnumParticleTypes.BY_NAME.put(RAD_1.getParticleName(), RAD_1);
        EnumParticleTypes.PARTICLES.put(RAD_2.getParticleID(), RAD_2);
        EnumParticleTypes.BY_NAME.put(RAD_2.getParticleName(), RAD_2);
        EnumParticleTypes.PARTICLES.put(RAD_3.getParticleID(), RAD_3);
        EnumParticleTypes.BY_NAME.put(RAD_3.getParticleName(), RAD_3);

        EnumParticleTypes.PARTICLES.put(PSY_0.getParticleID(), PSY_0);
        EnumParticleTypes.BY_NAME.put(PSY_0.getParticleName(), PSY_0);
        EnumParticleTypes.PARTICLES.put(PSY_1.getParticleID(), PSY_1);
        EnumParticleTypes.BY_NAME.put(PSY_1.getParticleName(), PSY_1);
        EnumParticleTypes.PARTICLES.put(PSY_2.getParticleID(), PSY_2);
        EnumParticleTypes.BY_NAME.put(PSY_2.getParticleName(), PSY_2);
        EnumParticleTypes.PARTICLES.put(PSY_3.getParticleID(), PSY_3);
        EnumParticleTypes.BY_NAME.put(PSY_3.getParticleName(), PSY_3);

        EnumParticleTypes.PARTICLES.put(CHEM_0.getParticleID(), CHEM_0);
        EnumParticleTypes.BY_NAME.put(CHEM_0.getParticleName(), CHEM_0);
        EnumParticleTypes.PARTICLES.put(CHEM_1.getParticleID(), CHEM_1);
        EnumParticleTypes.BY_NAME.put(CHEM_1.getParticleName(), CHEM_1);
        EnumParticleTypes.PARTICLES.put(CHEM_2.getParticleID(), CHEM_2);
        EnumParticleTypes.BY_NAME.put(CHEM_2.getParticleName(), CHEM_2);
        EnumParticleTypes.PARTICLES.put(CHEM_3.getParticleID(), CHEM_3);
        EnumParticleTypes.BY_NAME.put(CHEM_3.getParticleName(), CHEM_3);

        EnumParticleTypes.PARTICLES.put(TERM_0.getParticleID(), TERM_0);
        EnumParticleTypes.BY_NAME.put(TERM_0.getParticleName(), TERM_0);
        EnumParticleTypes.PARTICLES.put(TERM_1.getParticleID(), TERM_1);
        EnumParticleTypes.BY_NAME.put(TERM_1.getParticleName(), TERM_1);
        EnumParticleTypes.PARTICLES.put(TERM_2.getParticleID(), TERM_2);
        EnumParticleTypes.BY_NAME.put(TERM_2.getParticleName(), TERM_2);
        EnumParticleTypes.PARTICLES.put(TERM_3.getParticleID(), TERM_3);
        EnumParticleTypes.BY_NAME.put(TERM_3.getParticleName(), TERM_3);

        EnumParticleTypes.PARTICLES.put(ARTIFACT.getParticleID(), ARTIFACT);
        EnumParticleTypes.BY_NAME.put(ARTIFACT.getParticleName(), ARTIFACT);

        EnumParticleTypes.PARTICLES.put(ELECTRA_FAR.getParticleID(), ELECTRA_FAR);
        EnumParticleTypes.BY_NAME.put(ELECTRA_FAR.getParticleName(), ELECTRA_FAR);
        EnumParticleTypes.PARTICLES.put(ELECTRA_CENTER.getParticleID(), ELECTRA_CENTER);
        EnumParticleTypes.BY_NAME.put(ELECTRA_CENTER.getParticleName(), ELECTRA_CENTER);
        EnumParticleTypes.PARTICLES.put(ELECTRA_BLAST.getParticleID(), ELECTRA_BLAST);
        EnumParticleTypes.BY_NAME.put(ELECTRA_BLAST.getParticleName(), ELECTRA_BLAST);
        EnumParticleTypes.PARTICLES.put(ELECTRA_BLAST_CENTER.getParticleID(), ELECTRA_BLAST_CENTER);
        EnumParticleTypes.BY_NAME.put(ELECTRA_BLAST_CENTER.getParticleName(), ELECTRA_BLAST_CENTER);

        EnumParticleTypes.PARTICLES.put(FUNNEL_DUST.getParticleID(), FUNNEL_DUST);
        EnumParticleTypes.BY_NAME.put(FUNNEL_DUST.getParticleName(), FUNNEL_DUST);
        EnumParticleTypes.PARTICLES.put(FUNNEL_LEAVES.getParticleID(), FUNNEL_LEAVES);
        EnumParticleTypes.BY_NAME.put(FUNNEL_LEAVES.getParticleName(), FUNNEL_LEAVES);
        EnumParticleTypes.PARTICLES.put(FUNNEL_FLESH.getParticleID(), FUNNEL_FLESH);
        EnumParticleTypes.BY_NAME.put(FUNNEL_FLESH.getParticleName(), FUNNEL_FLESH);
        EnumParticleTypes.PARTICLES.put(FUNNEL_BLAST.getParticleID(), FUNNEL_BLAST);
        EnumParticleTypes.BY_NAME.put(FUNNEL_BLAST.getParticleName(), FUNNEL_BLAST);

        EnumParticleTypes.PARTICLES.put(SPRINGBOARD_DUST.getParticleID(), SPRINGBOARD_DUST);
        EnumParticleTypes.BY_NAME.put(SPRINGBOARD_DUST.getParticleName(), SPRINGBOARD_DUST);
        EnumParticleTypes.PARTICLES.put(SPRINGBOARD_BLAST.getParticleID(), SPRINGBOARD_BLAST);
        EnumParticleTypes.BY_NAME.put(SPRINGBOARD_BLAST.getParticleName(), SPRINGBOARD_BLAST);

        EnumParticleTypes.PARTICLES.put(CAROUSEL_BLAST.getParticleID(), CAROUSEL_BLAST);
        EnumParticleTypes.BY_NAME.put(CAROUSEL_BLAST.getParticleName(), CAROUSEL_BLAST);
        EnumParticleTypes.PARTICLES.put(CAROUSEL_CENTER.getParticleID(), CAROUSEL_CENTER);
        EnumParticleTypes.BY_NAME.put(CAROUSEL_CENTER.getParticleName(), CAROUSEL_CENTER);

        EnumParticleTypes.PARTICLES.put(DEEPFRY_BLAST.getParticleID(), DEEPFRY_BLAST);
        EnumParticleTypes.BY_NAME.put(DEEPFRY_BLAST.getParticleName(), DEEPFRY_BLAST);
        EnumParticleTypes.PARTICLES.put(STEAM_BLAST.getParticleID(), STEAM_BLAST);
        EnumParticleTypes.BY_NAME.put(STEAM_BLAST.getParticleName(), STEAM_BLAST);
        EnumParticleTypes.PARTICLES.put(SODA_BLAST.getParticleID(), SODA_BLAST);
        EnumParticleTypes.BY_NAME.put(SODA_BLAST.getParticleName(), SODA_BLAST);
        EnumParticleTypes.PARTICLES.put(KISSEL.getParticleID(), KISSEL);
        EnumParticleTypes.BY_NAME.put(KISSEL.getParticleName(), KISSEL);

        EnumParticleTypes.PARTICLES.put(LAMP_FIRE.getParticleID(), LAMP_FIRE);
        EnumParticleTypes.BY_NAME.put(LAMP_FIRE.getParticleName(), LAMP_FIRE);

        EnumParticleTypes.PARTICLES.put(TELEPORT.getParticleID(), TELEPORT);
        EnumParticleTypes.BY_NAME.put(TELEPORT.getParticleName(), TELEPORT);

        StalmineMod.proxy.registerParticles();
    }
}
