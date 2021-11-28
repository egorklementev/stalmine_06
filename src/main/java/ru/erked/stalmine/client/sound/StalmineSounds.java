package ru.erked.stalmine.client.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import ru.erked.stalmine.StalmineMod;

import java.util.ArrayList;
import java.util.HashMap;

@Mod.EventBusSubscriber(Side.CLIENT)
public class StalmineSounds {

    private static ArrayList<String> soundNames = new ArrayList<String>()
    {{
        add("fireplace");
        add("lighter");
        add("bag_open");
        add("bag_close");
        add("w_empty");
        add("w_psm");
        add("w_aks74u");
        add("w_toz_bm");
        add("w_toz_bm_reload_1");
        add("w_toz_bm_reload_2");
        add("w_p90");
        add("w_ak74m");
        add("w_an94");
        add("w_mp5");
        add("w_g36");
        add("w_vss");
        add("w_gauss");
        add("w_p99");
        add("w_m1911");
        add("w_bulldog");
        add("w_groza");
        add("w_l85");
        add("w_beretta");
        add("w_spas12");
        add("w_toz34");
        add("w_pb");
        add("w_pm");
        add("w_sig220");
        add("w_fort12");
        add("w_deagle");
        add("w_usp45");
        add("w_hpsa");
        add("w_pkm");
        add("w_pkm_reload");
        add("w_lr300");
        add("w_sig550");
        add("w_val");
        add("w_svd");
        add("w_svu");
        add("w_fn2000");
        add("w_fn57");
        add("w_stcpw");
        add("w_ar57");
        add("w_rpg7");
        add("w_rpg7_reload");
        add("w_bulldog_reload_1");
        add("w_bulldog_reload_2");
        add("w_bulldog_reload_3");
        add("w_bulldog_reload_4");
        add("w_bulldog_reload_5");
        add("w_bulldog_reload_6");
        add("w_sg_reload_1");
        add("w_sg_reload_2");
        add("w_sg_reload_3");
        add("w_sg_reload_4");
        add("w_sg_reload_5");
        add("w_sg_reload_6");
        add("w_sg_reload_7");
        add("w_sg_reload_8");
        add("w_sg_reload_9");
        add("w_sg_reload_10");
        add("w_sg_reload_11");
        add("w_sg_reload_12");
        add("pistol_reload");
        add("rifle_reload");
        add("w_grenload");
        add("w_grenshoot");
        add("w_grenchange");
        add("nvd_off");
        add("nvd_start");
        add("nvd_loop");
        add("geiger");
        add("psy");
        add("term");
        add("electra_idle");
        add("electra_hit");
        add("electra_blast");
        add("funnel_idle");
        add("funnel_hit");
        add("funnel_blast");
        add("springboard_idle");
        add("springboard_blast");
        add("carousel_blast");
        add("carousel_idle");
        add("deepfry_blast");
        add("steam_idle");
        add("steam_blast");
        add("soda_idle");
        add("soda_blast");
        add("tele_idle");
        add("tele_work");
        add("safe_open_1");
        add("safe_open_2");
        add("safe_open_3");
        add("safe_open_4");
        add("drink");
        add("food");
        add("vodka");
        add("medkit");
        add("pills");
        add("pda_news");
        add("pda_tip");
        add("pda_objective");
        add("radio");
        add("guitar");
        add("computer_on");
        add("bandage");
    }};

    public static HashMap<String, SoundEvent> pool = new HashMap<>();

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        for (String sound : soundNames) {
            registerSound(sound, event);
        }
    }

    private static void registerSound(String soundName, RegistryEvent.Register<SoundEvent> event) {
        ResourceLocation location = new ResourceLocation(StalmineMod.MODID, soundName);
        pool.put(soundName, new SoundEvent(location).setRegistryName(location));
        event.getRegistry().register(pool.get(soundName));
    }

}
