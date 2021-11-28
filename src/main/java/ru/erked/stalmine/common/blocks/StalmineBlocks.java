package ru.erked.stalmine.common.blocks;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StalmineBlocks {

    @GameRegistry.ObjectHolder("stalmine:fireplace")
    public static BlockFireplace blockFireplace;
    @GameRegistry.ObjectHolder("stalmine:fireplace_lit")
    public static BlockFireplace blockFireplaceLit;
    @GameRegistry.ObjectHolder("stalmine:bonfire")
    public static BlockBonfire blockBonfire;
    @GameRegistry.ObjectHolder("stalmine:bonfire_lit")
    public static BlockBonfire blockBonfireLit;
    @GameRegistry.ObjectHolder("stalmine:bag")
    public static BlockBag bag;
    @GameRegistry.ObjectHolder("stalmine:bag_1")
    public static BlockBag bag1;
    @GameRegistry.ObjectHolder("stalmine:safe_1")
    public static BlockSafe safe1;
    @GameRegistry.ObjectHolder("stalmine:safe_2")
    public static BlockSafe safe2;
    @GameRegistry.ObjectHolder("stalmine:safe_3")
    public static BlockSafe safe3;
    @GameRegistry.ObjectHolder("stalmine:safe_4")
    public static BlockSafe safe4;
    @GameRegistry.ObjectHolder("stalmine:roadblock")
    public static BlockRoadblock roadblock;
    @GameRegistry.ObjectHolder("stalmine:electrical_panel")
    public static BlockElectricalPanel electricalPanel;
    @GameRegistry.ObjectHolder("stalmine:sigarets")
    public static BlockSigarets sigarets;
    @GameRegistry.ObjectHolder("stalmine:lamp")
    public static BlockLamp lamp;
    @GameRegistry.ObjectHolder("stalmine:lamp_2")
    public static BlockLamp2 lamp2;
    @GameRegistry.ObjectHolder("stalmine:lamp_3")
    public static BlockLamp3 lamp3;
    @GameRegistry.ObjectHolder("stalmine:radio")
    public static BlockRadio radio;
    @GameRegistry.ObjectHolder("stalmine:barrel_1")
    public static BlockBarrel barrel1;
    @GameRegistry.ObjectHolder("stalmine:barrel_2")
    public static BlockBarrel barrel2;
    @GameRegistry.ObjectHolder("stalmine:barrel_3")
    public static BlockBarrel barrel3;
    @GameRegistry.ObjectHolder("stalmine:barrel_4")
    public static BlockBarrel barrel4;
    @GameRegistry.ObjectHolder("stalmine:barrel_5")
    public static BlockBarrel barrel5;
    @GameRegistry.ObjectHolder("stalmine:hedg")
    public static BlockHedg hedg;
    @GameRegistry.ObjectHolder("stalmine:ballon_1")
    public static BlockBallon ballon1;
    @GameRegistry.ObjectHolder("stalmine:ballon_2")
    public static BlockBallon ballon2;
    @GameRegistry.ObjectHolder("stalmine:ballon_3")
    public static BlockBallon ballon3;
    @GameRegistry.ObjectHolder("stalmine:rad_sign")
    public static BlockRadSign radSign;
    @GameRegistry.ObjectHolder("stalmine:wpn_upgrade_table")
    public static BlockWeaponUpgradeTable wpnUpgradeTable;

    @GameRegistry.ObjectHolder("stalmine:electra")
    public static BlockElectra electra;
    @GameRegistry.ObjectHolder("stalmine:funnel")
    public static BlockFunnel funnel;
    @GameRegistry.ObjectHolder("stalmine:springboard")
    public static BlockSpringboard springboard;
    @GameRegistry.ObjectHolder("stalmine:carousel")
    public static BlockCarousel carousel;
    @GameRegistry.ObjectHolder("stalmine:deepfry")
    public static BlockDeepfry deepfry;
    @GameRegistry.ObjectHolder("stalmine:steam")
    public static BlockSteam steam;
    @GameRegistry.ObjectHolder("stalmine:soda")
    public static BlockSoda soda;
    @GameRegistry.ObjectHolder("stalmine:kissel")
    public static BlockKissel kissel;
    @GameRegistry.ObjectHolder("stalmine:teleport")
    public static BlockTeleport teleport;

    @GameRegistry.ObjectHolder("stalmine:radiation_0")
    public static BlockRadiation rad0;
    @GameRegistry.ObjectHolder("stalmine:radiation_1")
    public static BlockRadiation rad1;
    @GameRegistry.ObjectHolder("stalmine:radiation_2")
    public static BlockRadiation rad2;
    @GameRegistry.ObjectHolder("stalmine:radiation_3")
    public static BlockRadiation rad3;

    @GameRegistry.ObjectHolder("stalmine:psy_0")
    public static BlockPsy psy0;
    @GameRegistry.ObjectHolder("stalmine:psy_1")
    public static BlockPsy psy1;
    @GameRegistry.ObjectHolder("stalmine:psy_2")
    public static BlockPsy psy2;
    @GameRegistry.ObjectHolder("stalmine:psy_3")
    public static BlockPsy psy3;

    @GameRegistry.ObjectHolder("stalmine:chem_0")
    public static BlockChem chem0;
    @GameRegistry.ObjectHolder("stalmine:chem_1")
    public static BlockChem chem1;
    @GameRegistry.ObjectHolder("stalmine:chem_2")
    public static BlockChem chem2;
    @GameRegistry.ObjectHolder("stalmine:chem_3")
    public static BlockChem chem3;

    @GameRegistry.ObjectHolder("stalmine:term_0")
    public static BlockTerm term0;
    @GameRegistry.ObjectHolder("stalmine:term_1")
    public static BlockTerm term1;
    @GameRegistry.ObjectHolder("stalmine:term_2")
    public static BlockTerm term2;
    @GameRegistry.ObjectHolder("stalmine:term_3")
    public static BlockTerm term3;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        blockFireplace.initModel();
        blockFireplaceLit.initModel();
        blockBonfire.initModel();
        blockBonfireLit.initModel();
        bag.initModel();
        bag1.initModel();
        safe1.initModel();
        safe2.initModel();
        safe3.initModel();
        safe4.initModel();
        roadblock.initModel();
        electricalPanel.initModel();
        sigarets.initModel();
        lamp.initModel();
        lamp2.initModel();
        lamp3.initModel();
        radio.initModel();
        barrel1.initModel();
        barrel2.initModel();
        barrel3.initModel();
        barrel4.initModel();
        barrel5.initModel();
        hedg.initModel();
        ballon1.initModel();
        ballon2.initModel();
        ballon3.initModel();
        radSign.initModel();
        wpnUpgradeTable.initModel();

        electra.initModel();
        funnel.initModel();
        springboard.initModel();
        carousel.initModel();
        deepfry.initModel();
        steam.initModel();
        soda.initModel();
        kissel.initModel();
        teleport.initModel();

        rad0.initModel();
        rad1.initModel();
        rad2.initModel();
        rad3.initModel();
        psy0.initModel();
        psy1.initModel();
        psy2.initModel();
        psy3.initModel();
        chem0.initModel();
        chem1.initModel();
        chem2.initModel();
        chem3.initModel();
        term0.initModel();
        term1.initModel();
        term2.initModel();
        term3.initModel();
    }

}
