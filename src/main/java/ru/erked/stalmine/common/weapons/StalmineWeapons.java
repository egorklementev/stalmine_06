package ru.erked.stalmine.common.weapons;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.common.items.ItemF1;
import ru.erked.stalmine.common.items.ItemKnife;
import ru.erked.stalmine.common.items.ItemRGD5;

import java.util.ArrayList;
import java.util.HashMap;

public class StalmineWeapons {

    public static final int pistolNum = 20;
    public static final int autoRifleNum = 18;
    public static final int autoGrenadeRifleNum = 10;
    public static final int shotgunNum = 7;
    public static final int sniperNum = 4;
    public static final int sniperAutoNum = 46;
    public static final int sniperAutoGrenadeNum = 38;

    public static ArrayList<String> w_list = new ArrayList<String>()
    {
        {
            add("w_psm"); // 1
            add("w_p99"); // 2
            add("w_p99_54518");
            add("w_m1911"); // 4
            add("w_m1911_919");
            add("w_beretta");
            add("w_beretta_54518");
            add("w_pb"); // 8
            add("w_pm");
            add("w_sig220");
            add("w_sig220_919");
            add("w_deagle");
            add("w_deagle_919");
            add("w_usp45");
            add("w_usp45_919");
            add("w_fort12"); // 16
            add("w_hpsa");
            add("w_hpsa_54518"); // 18
            add("w_fn57");
            add("w_fn57_919"); // 20

            add("w_aks74u"); // 1
            add("w_p90"); // 2
            add("w_p90_919");
            add("w_ak74m"); // 4
            add("w_an94");
            add("w_mp5");
            add("w_mp5_54518");
            add("w_pkm"); // 8
            add("w_val");
            add("w_val_54539");
            add("w_sig550");
            add("w_sig550_54539");
            add("w_lr300");
            add("w_lr300_54539"); // 14
            add("w_stcpw");
            add("w_stcpw_919");
            add("w_ar57");
            add("w_ar57_919"); // 18

            add("w_ak74m_gp"); // 1
            add("w_an94_gp");
            add("w_groza");
            add("w_groza_54539"); // 4
            add("w_sig550_gl");
            add("w_sig550_54539_gl");
            add("w_lr300_gl");
            add("w_lr300_54539_gl"); // 8
            add("w_ar57_gl");
            add("w_ar57_919_gl"); // 10

            add("w_bulldog"); // 1
            add("w_toz_bm");
            add("w_obrez_bm");
            add("w_protecta"); // 4
            add("w_spas12");
            add("w_winchester1300");
            add("w_toz34"); // 7

            add("w_vss");
            add("w_gauss");
            add("w_svd");
            add("w_svu");

            add("w_g36"); // 1
            add("w_g36_54539");
            add("w_l85");
            add("w_l85_54539"); // 4
            add("w_ak74m_pso");
            add("w_ak74m_psu");
            add("w_aks74u_pso");
            add("w_aks74u_psu"); // 8
            add("w_an94_pso");
            add("w_an94_psu");
            add("w_val_pso");
            add("w_val_psu");
            add("w_val_54539_pso");
            add("w_val_54539_psu");
            add("w_sig550_susat");
            add("w_sig550_susat16"); // 16
            add("w_sig550_susat16nvd");
            add("w_sig550_susat4nvd");
            add("w_sig550_54539_susat");
            add("w_sig550_54539_susat16");
            add("w_sig550_54539_susat16nvd");
            add("w_sig550_54539_susat4nvd"); // 22
            add("w_lr300_susat");
            add("w_lr300_susat16");
            add("w_lr300_susat16nvd");
            add("w_lr300_susat4nvd");
            add("w_lr300_54539_susat");
            add("w_lr300_54539_susat16");
            add("w_lr300_54539_susat16nvd");
            add("w_lr300_54539_susat4nvd"); // 30
            add("w_stcpw_susat");
            add("w_stcpw_susat16");
            add("w_stcpw_susat16nvd");
            add("w_stcpw_susat4nvd");
            add("w_stcpw_919_susat");
            add("w_stcpw_919_susat16");
            add("w_stcpw_919_susat16nvd");
            add("w_stcpw_919_susat4nvd");
            add("w_ar57_susat");
            add("w_ar57_susat16");
            add("w_ar57_susat16nvd");
            add("w_ar57_susat4nvd");
            add("w_ar57_919_susat");
            add("w_ar57_919_susat16");
            add("w_ar57_919_susat16nvd");
            add("w_ar57_919_susat4nvd"); // 46

            add("w_ak74m_pso_gp"); // 1
            add("w_ak74m_psu_gp");
            add("w_an94_pso_gp");
            add("w_an94_psu_gp"); // 4
            add("w_fn2000");
            add("w_fn2000_54539");
            add("w_sig550_susat_gl");
            add("w_sig550_susat16_gl"); // 8
            add("w_sig550_susat16nvd_gl");
            add("w_sig550_susat4nvd_gl");
            add("w_sig550_54539_susat_gl");
            add("w_sig550_54539_susat16_gl");
            add("w_sig550_54539_susat16nvd_gl");
            add("w_sig550_54539_susat4nvd_gl"); // 14
            add("w_lr300_susat_gl");
            add("w_lr300_susat16_gl");
            add("w_lr300_susat16nvd_gl");
            add("w_lr300_susat4nvd_gl");
            add("w_lr300_54539_susat_gl");
            add("w_lr300_54539_susat16_gl");
            add("w_lr300_54539_susat16nvd_gl");
            add("w_lr300_54539_susat4nvd_gl"); // 22
            add("w_l85_gl");
            add("w_l85_54539_gl"); // 24
            add("w_groza_pso");
            add("w_groza_psu");
            add("w_groza_54539_pso");
            add("w_groza_54539_psu"); // 28
            add("w_g36_gl");
            add("w_g36_54539_gl"); // 30
            add("w_ar57_susat_gl");
            add("w_ar57_susat16_gl");
            add("w_ar57_susat16nvd_gl");
            add("w_ar57_susat4nvd_gl");
            add("w_ar57_919_susat_gl");
            add("w_ar57_919_susat16_gl");
            add("w_ar57_919_susat16nvd_gl");
            add("w_ar57_919_susat4nvd_gl"); // 38

            add("w_rpg7");
        }
    };

    @GameRegistry.ObjectHolder("stalmine:w_psm")
    public static WeaponPistol psm;

    @GameRegistry.ObjectHolder("stalmine:w_p99")
    public static WeaponPistol p99;

    @GameRegistry.ObjectHolder("stalmine:w_p99_54518")
    public static WeaponPistol p99_54518;

    @GameRegistry.ObjectHolder("stalmine:w_m1911")
    public static WeaponPistol m1911;

    @GameRegistry.ObjectHolder("stalmine:w_m1911_919")
    public static WeaponPistol m1911_919;

    @GameRegistry.ObjectHolder("stalmine:w_beretta")
    public static WeaponPistol beretta;

    @GameRegistry.ObjectHolder("stalmine:w_beretta_54518")
    public static WeaponPistol beretta_54518;

    @GameRegistry.ObjectHolder("stalmine:w_pb")
    public static WeaponPistol pb;

    @GameRegistry.ObjectHolder("stalmine:w_pm")
    public static WeaponPistol pm;

    @GameRegistry.ObjectHolder("stalmine:w_sig220")
    public static WeaponPistol sig220;

    @GameRegistry.ObjectHolder("stalmine:w_sig220_919")
    public static WeaponPistol sig220_919;

    @GameRegistry.ObjectHolder("stalmine:w_usp45")
    public static WeaponPistol usp45;

    @GameRegistry.ObjectHolder("stalmine:w_usp45_919")
    public static WeaponPistol usp45_919;

    @GameRegistry.ObjectHolder("stalmine:w_deagle")
    public static WeaponPistol deagle;

    @GameRegistry.ObjectHolder("stalmine:w_deagle_919")
    public static WeaponPistol deagle_919;

    @GameRegistry.ObjectHolder("stalmine:w_fort12")
    public static WeaponPistol fort12;

    @GameRegistry.ObjectHolder("stalmine:w_hpsa")
    public static WeaponPistol hpsa;

    @GameRegistry.ObjectHolder("stalmine:w_hpsa_54518")
    public static WeaponPistol hpsa_54518;

    @GameRegistry.ObjectHolder("stalmine:w_aks74u")
    public static WeaponAuto aks74u;

    @GameRegistry.ObjectHolder("stalmine:w_aks74u_pso")
    public static WeaponSniperAutoRifle aks74u_pso;

    @GameRegistry.ObjectHolder("stalmine:w_aks74u_psu")
    public static WeaponSniperAutoRifle aks74u_psu;

    @GameRegistry.ObjectHolder("stalmine:w_mp5")
    public static WeaponAuto mp5;

    @GameRegistry.ObjectHolder("stalmine:w_mp5_54518")
    public static WeaponAuto mp5_54518;

    @GameRegistry.ObjectHolder("stalmine:w_ak74m")
    public static WeaponAuto ak74m;

    @GameRegistry.ObjectHolder("stalmine:w_ak74m_gp")
    public static WeaponAutoGrenade ak74m_gp;

    @GameRegistry.ObjectHolder("stalmine:w_ak74m_pso")
    public static WeaponSniperAutoRifle ak74m_pso;

    @GameRegistry.ObjectHolder("stalmine:w_ak74m_pso_gp")
    public static WeaponSniperGrenadeAutoRifle ak74m_pso_gp;

    @GameRegistry.ObjectHolder("stalmine:w_ak74m_psu")
    public static WeaponSniperAutoRifle ak74m_psu;

    @GameRegistry.ObjectHolder("stalmine:w_ak74m_psu_gp")
    public static WeaponSniperGrenadeAutoRifle ak74m_psu_gp;

    @GameRegistry.ObjectHolder("stalmine:w_p90")
    public static WeaponAuto p90;

    @GameRegistry.ObjectHolder("stalmine:w_p90_919")
    public static WeaponAuto p90_919;

    @GameRegistry.ObjectHolder("stalmine:w_an94")
    public static WeaponAuto an94;

    @GameRegistry.ObjectHolder("stalmine:w_an94_gp")
    public static WeaponAutoGrenade an94_gp;

    @GameRegistry.ObjectHolder("stalmine:w_an94_pso")
    public static WeaponSniperAutoRifle an94_pso;

    @GameRegistry.ObjectHolder("stalmine:w_an94_psu")
    public static WeaponSniperAutoRifle an94_psu;

    @GameRegistry.ObjectHolder("stalmine:w_an94_pso_gp")
    public static WeaponSniperGrenadeAutoRifle an94_pso_gp;

    @GameRegistry.ObjectHolder("stalmine:w_an94_psu_gp")
    public static WeaponSniperGrenadeAutoRifle an94_psu_gp;

    @GameRegistry.ObjectHolder("stalmine:w_groza")
    public static WeaponAutoGrenade groza;

    @GameRegistry.ObjectHolder("stalmine:w_groza_54539")
    public static WeaponAutoGrenade groza_54539;

    @GameRegistry.ObjectHolder("stalmine:w_groza_pso")
    public static WeaponSniperGrenadeAutoRifle groza_pso;

    @GameRegistry.ObjectHolder("stalmine:w_groza_psu")
    public static WeaponSniperGrenadeAutoRifle groza_psu;

    @GameRegistry.ObjectHolder("stalmine:w_groza_54539_pso")
    public static WeaponSniperGrenadeAutoRifle groza_54539_pso;

    @GameRegistry.ObjectHolder("stalmine:w_groza_54539_psu")
    public static WeaponSniperGrenadeAutoRifle groza_54539_psu;

    @GameRegistry.ObjectHolder("stalmine:w_pkm")
    public static WeaponAuto pkm;

    @GameRegistry.ObjectHolder("stalmine:w_val")
    public static WeaponAuto val;

    @GameRegistry.ObjectHolder("stalmine:w_val_54539")
    public static WeaponAuto val_54539;

    @GameRegistry.ObjectHolder("stalmine:w_val_pso")
    public static WeaponSniperAutoRifle val_pso;

    @GameRegistry.ObjectHolder("stalmine:w_val_54539_pso")
    public static WeaponSniperAutoRifle val_54539_pso;

    @GameRegistry.ObjectHolder("stalmine:w_val_psu")
    public static WeaponSniperAutoRifle val_psu;

    @GameRegistry.ObjectHolder("stalmine:w_val_54539_psu")
    public static WeaponSniperAutoRifle val_54539_psu;

    @GameRegistry.ObjectHolder("stalmine:w_sig550")
    public static WeaponAuto sig550;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_gl")
    public static WeaponAutoGrenade sig550_gl;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_susat")
    public static WeaponSniperAutoRifle sig550_susat;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_susat16")
    public static WeaponSniperAutoRifle sig550_susat16;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_susat16nvd")
    public static WeaponSniperAutoRifle sig550_susat16nvd;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_susat4nvd")
    public static WeaponSniperAutoRifle sig550_susat4nvd;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_susat_gl")
    public static WeaponSniperGrenadeAutoRifle sig550_susat_gl;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_susat16_gl")
    public static WeaponSniperGrenadeAutoRifle sig550_susat16_gl;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_susat16nvd_gl")
    public static WeaponSniperGrenadeAutoRifle sig550_susat16nvd_gl;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_susat4nvd_gl")
    public static WeaponSniperGrenadeAutoRifle sig550_susat4nvd_gl;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_54539")
    public static WeaponAuto sig550_54539;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_54539_gl")
    public static WeaponAutoGrenade sig550_54539_gl;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_54539_susat")
    public static WeaponSniperAutoRifle sig550_54539_susat;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_54539_susat16")
    public static WeaponSniperAutoRifle sig550_54539_susat16;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_54539_susat16nvd")
    public static WeaponSniperAutoRifle sig550_54539_susat16nvd;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_54539_susat4nvd")
    public static WeaponSniperAutoRifle sig550_54539_susat4nvd;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_54539_susat_gl")
    public static WeaponSniperGrenadeAutoRifle sig550_54539_susat_gl;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_54539_susat16_gl")
    public static WeaponSniperGrenadeAutoRifle sig550_54539_susat16_gl;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_54539_susat16nvd_gl")
    public static WeaponSniperGrenadeAutoRifle sig550_54539_susat16nvd_gl;

    @GameRegistry.ObjectHolder("stalmine:w_sig550_54539_susat4nvd_gl")
    public static WeaponSniperGrenadeAutoRifle sig550_54539_susat4nvd_gl;

    @GameRegistry.ObjectHolder("stalmine:w_lr300")
    public static WeaponAuto lr300;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_gl")
    public static WeaponAutoGrenade lr300_gl;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_susat")
    public static WeaponSniperAutoRifle lr300_susat;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_susat16")
    public static WeaponSniperAutoRifle lr300_susat16;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_susat16nvd")
    public static WeaponSniperAutoRifle lr300_susat16nvd;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_susat4nvd")
    public static WeaponSniperAutoRifle lr300_susat4nvd;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_susat_gl")
    public static WeaponSniperGrenadeAutoRifle lr300_susat_gl;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_susat16_gl")
    public static WeaponSniperGrenadeAutoRifle lr300_susat16_gl;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_susat16nvd_gl")
    public static WeaponSniperGrenadeAutoRifle lr300_susat16nvd_gl;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_susat4nvd_gl")
    public static WeaponSniperGrenadeAutoRifle lr300_susat4nvd_gl;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_54539")
    public static WeaponAuto lr300_54539;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_54539_gl")
    public static WeaponAutoGrenade lr300_54539_gl;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_54539_susat")
    public static WeaponSniperAutoRifle lr300_54539_susat;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_54539_susat16")
    public static WeaponSniperAutoRifle lr300_54539_susat16;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_54539_susat16nvd")
    public static WeaponSniperAutoRifle lr300_54539_susat16nvd;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_54539_susat4nvd")
    public static WeaponSniperAutoRifle lr300_54539_susat4nvd;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_54539_susat_gl")
    public static WeaponSniperGrenadeAutoRifle lr300_54539_susat_gl;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_54539_susat16_gl")
    public static WeaponSniperGrenadeAutoRifle lr300_54539_susat16_gl;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_54539_susat16nvd_gl")
    public static WeaponSniperGrenadeAutoRifle lr300_54539_susat16nvd_gl;

    @GameRegistry.ObjectHolder("stalmine:w_lr300_54539_susat4nvd_gl")
    public static WeaponSniperGrenadeAutoRifle lr300_54539_susat4nvd_gl;

    @GameRegistry.ObjectHolder("stalmine:w_toz_bm")
    public static WeaponShotgun tozbm;

    @GameRegistry.ObjectHolder("stalmine:w_obrez_bm")
    public static WeaponShotgun obrezbm;

    @GameRegistry.ObjectHolder("stalmine:w_protecta")
    public static WeaponShotgun protecta;

    @GameRegistry.ObjectHolder("stalmine:w_spas12")
    public static WeaponShotgun spas12;

    @GameRegistry.ObjectHolder("stalmine:w_winchester1300")
    public static WeaponShotgun winchester1300;

    @GameRegistry.ObjectHolder("stalmine:w_toz34")
    public static WeaponShotgun toz34;

    @GameRegistry.ObjectHolder("stalmine:w_vss")
    public static WeaponSniperRifle vss;

    @GameRegistry.ObjectHolder("stalmine:w_gauss")
    public static WeaponSniperRifle gauss;

    @GameRegistry.ObjectHolder("stalmine:w_svd")
    public static WeaponSniperRifle svd;

    @GameRegistry.ObjectHolder("stalmine:w_svu")
    public static WeaponSniperRifle svu;

    @GameRegistry.ObjectHolder("stalmine:w_g36")
    public static WeaponSniperAutoRifle g36;

    @GameRegistry.ObjectHolder("stalmine:w_g36_gl")
    public static WeaponSniperGrenadeAutoRifle g36_gl;

    @GameRegistry.ObjectHolder("stalmine:w_g36_54539")
    public static WeaponSniperAutoRifle g36_54539;

    @GameRegistry.ObjectHolder("stalmine:w_g36_54539_gl")
    public static WeaponSniperGrenadeAutoRifle g36_54539_gl;

    @GameRegistry.ObjectHolder("stalmine:w_l85")
    public static WeaponSniperAutoRifle l85;

    @GameRegistry.ObjectHolder("stalmine:w_l85_gl")
    public static WeaponSniperGrenadeAutoRifle l85_gl;

    @GameRegistry.ObjectHolder("stalmine:w_l85_54539")
    public static WeaponSniperAutoRifle l85_54539;

    @GameRegistry.ObjectHolder("stalmine:w_l85_54539_gl")
    public static WeaponSniperGrenadeAutoRifle l85_54539_gl;

    @GameRegistry.ObjectHolder("stalmine:w_fn2000")
    public static WeaponSniperGrenadeAutoRifle fn2000;

    @GameRegistry.ObjectHolder("stalmine:w_fn2000_54539")
    public static WeaponSniperGrenadeAutoRifle fn2000_54539;

    @GameRegistry.ObjectHolder("stalmine:w_fn57")
    public static WeaponPistol fn57;

    @GameRegistry.ObjectHolder("stalmine:w_fn57_919")
    public static WeaponPistol fn57_919;

    @GameRegistry.ObjectHolder("stalmine:w_stcpw")
    public static WeaponAuto stcpw;

    @GameRegistry.ObjectHolder("stalmine:w_stcpw_919")
    public static WeaponAuto stcpw_919;

    @GameRegistry.ObjectHolder("stalmine:w_ar57")
    public static WeaponAuto ar57;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_919")
    public static WeaponAuto ar57_919;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_gl")
    public static WeaponAutoGrenade ar57_gl;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_919_gl")
    public static WeaponAutoGrenade ar57_919_gl;

    @GameRegistry.ObjectHolder("stalmine:w_stcpw_susat")
    public static WeaponSniperAutoRifle stcpw_susat;

    @GameRegistry.ObjectHolder("stalmine:w_stcpw_susat16")
    public static WeaponSniperAutoRifle stcpw_susat16;

    @GameRegistry.ObjectHolder("stalmine:w_stcpw_susat16nvd")
    public static WeaponSniperAutoRifle stcpw_susat16nvd;

    @GameRegistry.ObjectHolder("stalmine:w_stcpw_susat4nvd")
    public static WeaponSniperAutoRifle stcpw_susat4nvd;

    @GameRegistry.ObjectHolder("stalmine:w_stcpw_919_susat")
    public static WeaponSniperAutoRifle stcpw_919_susat;

    @GameRegistry.ObjectHolder("stalmine:w_stcpw_919_susat16")
    public static WeaponSniperAutoRifle stcpw_919_susat16;

    @GameRegistry.ObjectHolder("stalmine:w_stcpw_919_susat16nvd")
    public static WeaponSniperAutoRifle stcpw_919_susat16nvd;

    @GameRegistry.ObjectHolder("stalmine:w_stcpw_919_susat4nvd")
    public static WeaponSniperAutoRifle stcpw_919_susat4nvd;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_susat")
    public static WeaponSniperAutoRifle ar57_susat;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_susat16")
    public static WeaponSniperAutoRifle ar57_susat16;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_susat16nvd")
    public static WeaponSniperAutoRifle ar57_susat16nvd;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_susat4nvd")
    public static WeaponSniperAutoRifle ar57_susat4nvd;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_919_susat")
    public static WeaponSniperAutoRifle ar57_919_susat;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_919_susat16")
    public static WeaponSniperAutoRifle ar57_919_susat16;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_919_susat16nvd")
    public static WeaponSniperAutoRifle ar57_919_susat16nvd;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_919_susat4nvd")
    public static WeaponSniperAutoRifle ar57_919_susat4nvd;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_susat_gl")
    public static WeaponSniperGrenadeAutoRifle ar57_susat_gl;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_susat16_gl")
    public static WeaponSniperGrenadeAutoRifle ar57_susat16_gl;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_susat16nvd_gl")
    public static WeaponSniperGrenadeAutoRifle ar57_susat16nvd_gl;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_susat4nvd_gl")
    public static WeaponSniperGrenadeAutoRifle ar57_susat4nvd_gl;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_919_susat_gl")
    public static WeaponSniperGrenadeAutoRifle ar57_919_susat_gl;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_919_susat16_gl")
    public static WeaponSniperGrenadeAutoRifle ar57_919_susat16_gl;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_919_susat16nvd_gl")
    public static WeaponSniperGrenadeAutoRifle ar57_919_susat16nvd_gl;

    @GameRegistry.ObjectHolder("stalmine:w_ar57_919_susat4nvd_gl")
    public static WeaponSniperGrenadeAutoRifle ar57_919_susat4nvd_gl;

    @GameRegistry.ObjectHolder("stalmine:w_bulldog")
    public static WeaponShotgun bulldog;

    @GameRegistry.ObjectHolder("stalmine:w_rpg7")
    public static WeaponRocket rpg7;

    @GameRegistry.ObjectHolder("stalmine:w_rgd5")
    public static ItemRGD5 rgd5;

    @GameRegistry.ObjectHolder("stalmine:w_f1")
    public static ItemF1 f1;

    @GameRegistry.ObjectHolder("stalmine:w_knife")
    public static ItemKnife knife;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        psm.initModel();
        p99.initModel();
        p99_54518.initModel();
        m1911.initModel();
        m1911_919.initModel();
        beretta.initModel();
        beretta_54518.initModel();
        pb.initModel();
        pm.initModel();
        sig220.initModel();
        sig220_919.initModel();
        deagle.initModel();
        deagle_919.initModel();
        fort12.initModel();
        usp45.initModel();
        usp45_919.initModel();
        hpsa.initModel();
        hpsa_54518.initModel();
        fn57.initModel();
        fn57_919.initModel();

        aks74u.initModel();
        aks74u_pso.initModel();
        aks74u_psu.initModel();
        p90.initModel();
        p90_919.initModel();
        ak74m.initModel();
        ak74m_gp.initModel();
        ak74m_pso.initModel();
        ak74m_pso_gp.initModel();
        ak74m_psu.initModel();
        ak74m_psu_gp.initModel();
        an94.initModel();
        an94_gp.initModel();
        an94_pso.initModel();
        an94_psu.initModel();
        an94_pso_gp.initModel();
        an94_psu_gp.initModel();
        mp5.initModel();
        mp5_54518.initModel();
        groza.initModel();
        groza_pso.initModel();
        groza_psu.initModel();
        groza_54539.initModel();
        groza_54539_pso.initModel();
        groza_54539_psu.initModel();
        pkm.initModel();
        val.initModel();
        val_pso.initModel();
        val_psu.initModel();
        val_54539.initModel();
        val_54539_pso.initModel();
        val_54539_psu.initModel();
        sig550.initModel();
        sig550_gl.initModel();
        sig550_susat.initModel();
        sig550_susat16.initModel();
        sig550_susat16nvd.initModel();
        sig550_susat4nvd.initModel();
        sig550_susat_gl.initModel();
        sig550_susat16_gl.initModel();
        sig550_susat16nvd_gl.initModel();
        sig550_susat4nvd_gl.initModel();
        sig550_54539.initModel();
        sig550_54539_gl.initModel();
        sig550_54539_susat.initModel();
        sig550_54539_susat16.initModel();
        sig550_54539_susat16nvd.initModel();
        sig550_54539_susat4nvd.initModel();
        sig550_54539_susat_gl.initModel();
        sig550_54539_susat16_gl.initModel();
        sig550_54539_susat16nvd_gl.initModel();
        sig550_54539_susat4nvd_gl.initModel();
        lr300.initModel();
        lr300_gl.initModel();
        lr300_susat.initModel();
        lr300_susat16.initModel();
        lr300_susat16nvd.initModel();
        lr300_susat4nvd.initModel();
        lr300_susat_gl.initModel();
        lr300_susat16_gl.initModel();
        lr300_susat16nvd_gl.initModel();
        lr300_susat4nvd_gl.initModel();
        lr300_54539.initModel();
        lr300_54539_gl.initModel();
        lr300_54539_susat.initModel();
        lr300_54539_susat16.initModel();
        lr300_54539_susat16nvd.initModel();
        lr300_54539_susat4nvd.initModel();
        lr300_54539_susat_gl.initModel();
        lr300_54539_susat16_gl.initModel();
        lr300_54539_susat16nvd_gl.initModel();
        lr300_54539_susat4nvd_gl.initModel();
        stcpw.initModel();
        stcpw_919.initModel();
        stcpw_susat.initModel();
        stcpw_susat16.initModel();
        stcpw_susat16nvd.initModel();
        stcpw_susat4nvd.initModel();
        stcpw_919_susat.initModel();
        stcpw_919_susat16.initModel();
        stcpw_919_susat16nvd.initModel();
        stcpw_919_susat4nvd.initModel();
        ar57.initModel();
        ar57_919.initModel();
        ar57_gl.initModel();
        ar57_919_gl.initModel();
        ar57_susat.initModel();
        ar57_susat16.initModel();
        ar57_susat16nvd.initModel();
        ar57_susat4nvd.initModel();
        ar57_susat_gl.initModel();
        ar57_susat16_gl.initModel();
        ar57_susat16nvd_gl.initModel();
        ar57_susat4nvd_gl.initModel();
        ar57_919_susat.initModel();
        ar57_919_susat16.initModel();
        ar57_919_susat16nvd.initModel();
        ar57_919_susat4nvd.initModel();
        ar57_919_susat_gl.initModel();
        ar57_919_susat16_gl.initModel();
        ar57_919_susat16nvd_gl.initModel();
        ar57_919_susat4nvd_gl.initModel();

        bulldog.initModel();
        tozbm.initModel();
        obrezbm.initModel();
        protecta.initModel();
        spas12.initModel();
        winchester1300.initModel();
        toz34.initModel();

        vss.initModel();
        gauss.initModel();
        svd.initModel();
        svu.initModel();

        g36.initModel();
        g36_gl.initModel();
        g36_54539.initModel();
        g36_54539_gl.initModel();
        l85.initModel();
        l85_gl.initModel();
        l85_54539.initModel();
        l85_54539_gl.initModel();
        fn2000.initModel();
        fn2000_54539.initModel();

        rgd5.initModel();
        f1.initModel();
        knife.initModel();

        rpg7.initModel();
    }
}
