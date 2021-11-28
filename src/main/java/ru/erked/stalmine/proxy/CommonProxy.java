package ru.erked.stalmine.proxy;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySkeletonHorse;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.StalmineConfig;
import ru.erked.stalmine.client.particle.StalmineParticles;
import ru.erked.stalmine.client.render.*;
import ru.erked.stalmine.client.sound.StalmineSounds;
import ru.erked.stalmine.common.armor.Armor;
import ru.erked.stalmine.common.armor.StalmineArmor;
import ru.erked.stalmine.common.blocks.*;
import ru.erked.stalmine.common.effects.StalminePotions;
import ru.erked.stalmine.common.entities.*;
import ru.erked.stalmine.common.items.*;
import ru.erked.stalmine.common.network.StalminePacketHandler;
import ru.erked.stalmine.common.tile_entities.*;
import ru.erked.stalmine.common.weapons.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

@Mod.EventBusSubscriber
public class CommonProxy {

    public static boolean allowIntemPickup = false;
    public static Configuration config;
    public static ArrayList<Configuration> wpnConfigs;
    public static ArrayList<Configuration> armConfigs;
    public static ArrayList<Configuration> artConfigs;

    public void preInit(FMLPreInitializationEvent e) {
        wpnConfigs = new ArrayList<>();
        armConfigs = new ArrayList<>();
        artConfigs = new ArrayList<>();

        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "stalmine.cfg"));

        for (int i = 0; i < StalmineWeapons.w_list.size(); i++) {
            wpnConfigs.add(
                    new Configuration(
                            new File(
                                    directory.getPath() + "/stalmine_wpn",
                                    StalmineWeapons.w_list.get(i) + ".cfg"
                            )
                    )
            );
        }

        for (int i = 0; i < StalmineArmor.arm_names.size(); i++) {
            armConfigs.add(
                    new Configuration(
                            new File(
                                    directory.getPath() + "/stalmine_arm",
                                    StalmineArmor.arm_names.get(i) + ".cfg"
                            )
                    )
            );
        }

        for (int i = 0; i < StalmineItems.art_names.size(); i++) {
            artConfigs.add(
                    new Configuration(
                            new File(
                                    directory.getPath() + "/stalmine_art",
                                    StalmineItems.art_names.get(i) + ".cfg"
                            )
                    )
            );
        }

        StalmineConfig.readConfig();

        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderBulletFactory<>());
        RenderingRegistry.registerEntityRenderingHandler(EntityBulletGrenade.class, new RenderBulletGrenadeFactory<>());
        RenderingRegistry.registerEntityRenderingHandler(EntityBulletGrenadeOG7V.class, new RenderBulletGrenadeOG7VFactory<>());
        RenderingRegistry.registerEntityRenderingHandler(EntityBulletGrenadeM430.class, new RenderBulletGrenadeM430Factory<>());
        RenderingRegistry.registerEntityRenderingHandler(EntityRGD5.class, new RenderRGD5Factory<>());
        RenderingRegistry.registerEntityRenderingHandler(EntityF1.class, new RenderF1Factory<>());
        RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderStalminePlayerFactory<>());

        StalminePacketHandler.registerMessages("stalmine");

        ForgeModContainer.zombieBabyChance = 0f;
    }

    public void init(FMLInitializationEvent e) {
        StalmineParticles.registration();
        NetworkRegistry.INSTANCE.registerGuiHandler(StalmineMod.instance, new GUIProxy());
        ClientProxy.playerRender = new RenderStalminePlayer(Minecraft.getMinecraft().getRenderManager());
        ClientProxy.keyBindings = new KeyBinding[5];
    }

    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged()) {
            config.save();
        }
        fieldBlocks = new ArrayList<Block>() {
            {
                add(StalmineBlocks.rad0);
                add(StalmineBlocks.rad1);
                add(StalmineBlocks.rad2);
                add(StalmineBlocks.rad3);
                add(StalmineBlocks.psy0);
                add(StalmineBlocks.psy1);
                add(StalmineBlocks.psy2);
                add(StalmineBlocks.psy3);
                add(StalmineBlocks.chem0);
                add(StalmineBlocks.chem1);
                add(StalmineBlocks.chem2);
                add(StalmineBlocks.chem3);
                add(StalmineBlocks.term0);
                add(StalmineBlocks.term1);
                add(StalmineBlocks.term2);
                add(StalmineBlocks.term3);
                add(StalmineBlocks.electra);
                add(StalmineBlocks.funnel);
                add(StalmineBlocks.springboard);
                add(StalmineBlocks.carousel);
                add(StalmineBlocks.steam);
                add(StalmineBlocks.deepfry);
                add(StalmineBlocks.soda);
            }
        };
        particleTypes = new ArrayList<EnumParticleTypes>() {
            {
                add(StalmineParticles.RAD_0);
                add(StalmineParticles.RAD_1);
                add(StalmineParticles.RAD_2);
                add(StalmineParticles.RAD_3);
                add(StalmineParticles.PSY_0);
                add(StalmineParticles.PSY_1);
                add(StalmineParticles.PSY_2);
                add(StalmineParticles.PSY_3);
                add(StalmineParticles.CHEM_0);
                add(StalmineParticles.CHEM_1);
                add(StalmineParticles.CHEM_2);
                add(StalmineParticles.CHEM_3);
                add(StalmineParticles.TERM_0);
                add(StalmineParticles.TERM_1);
                add(StalmineParticles.TERM_2);
                add(StalmineParticles.TERM_3);
                add(StalmineParticles.PSY_3);
                add(StalmineParticles.PSY_3);
                add(StalmineParticles.PSY_3);
                add(StalmineParticles.PSY_3);
                add(StalmineParticles.PSY_3);
                add(StalmineParticles.PSY_3);
                add(StalmineParticles.PSY_3);
            }
        };
        fieldBlockItems = new ArrayList<Item>() {
            {
                add(Item.getItemFromBlock(StalmineBlocks.rad0));
                add(Item.getItemFromBlock(StalmineBlocks.rad1));
                add(Item.getItemFromBlock(StalmineBlocks.rad2));
                add(Item.getItemFromBlock(StalmineBlocks.rad3));
                add(Item.getItemFromBlock(StalmineBlocks.psy0));
                add(Item.getItemFromBlock(StalmineBlocks.psy1));
                add(Item.getItemFromBlock(StalmineBlocks.psy2));
                add(Item.getItemFromBlock(StalmineBlocks.psy3));
                add(Item.getItemFromBlock(StalmineBlocks.chem0));
                add(Item.getItemFromBlock(StalmineBlocks.chem1));
                add(Item.getItemFromBlock(StalmineBlocks.chem2));
                add(Item.getItemFromBlock(StalmineBlocks.chem3));
                add(Item.getItemFromBlock(StalmineBlocks.term0));
                add(Item.getItemFromBlock(StalmineBlocks.term1));
                add(Item.getItemFromBlock(StalmineBlocks.term2));
                add(Item.getItemFromBlock(StalmineBlocks.term3));
            }
        };
        anomalyBlockItems = new ArrayList<Item>() {
            {
                add(Item.getItemFromBlock(StalmineBlocks.electra));
                add(Item.getItemFromBlock(StalmineBlocks.funnel));
                add(Item.getItemFromBlock(StalmineBlocks.springboard));
                add(Item.getItemFromBlock(StalmineBlocks.carousel));
                add(Item.getItemFromBlock(StalmineBlocks.steam));
                add(Item.getItemFromBlock(StalmineBlocks.deepfry));
                add(Item.getItemFromBlock(StalmineBlocks.soda));
            }
        };
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new BlockFireplace(false).setRegistryName("fireplace"));
        event.getRegistry().register(new BlockFireplace(true).setRegistryName("fireplace_lit"));
        event.getRegistry().register(new BlockBonfire(false).setRegistryName("bonfire"));
        event.getRegistry().register(new BlockBonfire(true).setRegistryName("bonfire_lit"));
        event.getRegistry().register(new BlockBag().setRegistryName("bag"));
        event.getRegistry().register(new BlockBag().setRegistryName("bag_1"));
        event.getRegistry().register(new BlockSafe(1,
                new AxisAlignedBB(.1f, 0f, .1f, .9f, 1f, .9f)).setRegistryName("safe_1"));
        event.getRegistry().register(new BlockSafe(2,
                new AxisAlignedBB(0.1f, 0f, .35f, .9f, .35f, .65f)).setRegistryName("safe_2"));
        event.getRegistry().register(new BlockSafe(3,
                new AxisAlignedBB(0.15f, 0f, .35f, .85f, .3f, .65f)).setRegistryName("safe_3"));
        event.getRegistry().register(new BlockSafe(4,
                new AxisAlignedBB(0f, 0f, 0f, 1f, 1f, 1f)).setRegistryName("safe_4"));
        event.getRegistry().register(new BlockRoadblock().setRegistryName("roadblock"));
        event.getRegistry().register(new BlockElectricalPanel(
                new AxisAlignedBB(.3f, .15f, .75f, .7f, 1f, 1f)).setRegistryName("electrical_panel"));
        event.getRegistry().register(new BlockSigarets().setRegistryName("sigarets"));
        event.getRegistry().register(new BlockLamp(
                new AxisAlignedBB(0f, .9f, .35f, 1f, 1f, .65f), .875f).setRegistryName("lamp"));
        event.getRegistry().register(new BlockLamp2(
                new AxisAlignedBB(.4f, 0f, .4f, .6f, .2f, .6f), .625f).setRegistryName("lamp_2"));
        event.getRegistry().register(new BlockLamp3(
                new AxisAlignedBB(0f, .9f, .35f, 1f, 1f, .65f), .9375f).setRegistryName("lamp_3"));
        event.getRegistry().register(new BlockRadio().setRegistryName("radio"));
        event.getRegistry().register(new BlockBarrel().setRegistryName("barrel_1"));
        event.getRegistry().register(new BlockBarrel().setRegistryName("barrel_2"));
        event.getRegistry().register(new BlockBarrel().setRegistryName("barrel_3"));
        event.getRegistry().register(new BlockBarrel().setRegistryName("barrel_4"));
        event.getRegistry().register(new BlockBarrel().setRegistryName("barrel_5"));
        event.getRegistry().register(new BlockHedg().setRegistryName("hedg"));
        event.getRegistry().register(new BlockBallon().setRegistryName("ballon_1"));
        event.getRegistry().register(new BlockBallon().setRegistryName("ballon_2"));
        event.getRegistry().register(new BlockBallon().setRegistryName("ballon_3"));
        event.getRegistry().register(new BlockRadSign().setRegistryName("rad_sign"));
        event.getRegistry().register(new BlockWeaponUpgradeTable().setRegistryName("wpn_upgrade_table"));

        event.getRegistry().register(new BlockElectra().setRegistryName("electra"));
        event.getRegistry().register(new BlockFunnel().setRegistryName("funnel"));
        event.getRegistry().register(new BlockSpringboard().setRegistryName("springboard"));
        event.getRegistry().register(new BlockCarousel().setRegistryName("carousel"));
        event.getRegistry().register(new BlockDeepfry().setRegistryName("deepfry"));
        event.getRegistry().register(new BlockSteam().setRegistryName("steam"));
        event.getRegistry().register(new BlockSoda().setRegistryName("soda"));
        event.getRegistry().register(new BlockKissel().setRegistryName("kissel"));
        event.getRegistry().register(new BlockTeleport().setRegistryName("teleport"));

        event.getRegistry().register(new BlockRadiation(0).setRegistryName("radiation_0"));
        event.getRegistry().register(new BlockRadiation(1).setRegistryName("radiation_1"));
        event.getRegistry().register(new BlockRadiation(2).setRegistryName("radiation_2"));
        event.getRegistry().register(new BlockRadiation(3).setRegistryName("radiation_3"));

        event.getRegistry().register(new BlockPsy(0).setRegistryName("psy_0"));
        event.getRegistry().register(new BlockPsy(1).setRegistryName("psy_1"));
        event.getRegistry().register(new BlockPsy(2).setRegistryName("psy_2"));
        event.getRegistry().register(new BlockPsy(3).setRegistryName("psy_3"));

        event.getRegistry().register(new BlockChem(0).setRegistryName("chem_0"));
        event.getRegistry().register(new BlockChem(1).setRegistryName("chem_1"));
        event.getRegistry().register(new BlockChem(2).setRegistryName("chem_2"));
        event.getRegistry().register(new BlockChem(3).setRegistryName("chem_3"));

        event.getRegistry().register(new BlockTerm(0).setRegistryName("term_0"));
        event.getRegistry().register(new BlockTerm(1).setRegistryName("term_1"));
        event.getRegistry().register(new BlockTerm(2).setRegistryName("term_2"));
        event.getRegistry().register(new BlockTerm(3).setRegistryName("term_3"));

        GameRegistry.registerTileEntity(TEFireplace.class, StalmineMod.MODID + ":" + "fireplace");
        GameRegistry.registerTileEntity(TEBag.class, StalmineMod.MODID + ":" + "bag");
        GameRegistry.registerTileEntity(TEWeaponUpgradeTable.class, StalmineMod.MODID + ":" + "wpn_upgrade_table");
        GameRegistry.registerTileEntity(TEElectra.class, StalmineMod.MODID + ":" + "electra");
        GameRegistry.registerTileEntity(TEFunnel.class, StalmineMod.MODID + ":" + "funnel");
        GameRegistry.registerTileEntity(TESpringboard.class, StalmineMod.MODID + ":" + "springboard");
        GameRegistry.registerTileEntity(TECarousel.class, StalmineMod.MODID + ":" + "carousel");
        GameRegistry.registerTileEntity(TEDeepfry.class, StalmineMod.MODID + ":" + "deepfry");
        GameRegistry.registerTileEntity(TESteam.class, StalmineMod.MODID + ":" + "steam");
        GameRegistry.registerTileEntity(TESoda.class, StalmineMod.MODID + ":" + "soda");
        GameRegistry.registerTileEntity(TEKissel.class, StalmineMod.MODID + ":" + "kissel");
        GameRegistry.registerTileEntity(TETeleport.class, StalmineMod.MODID + ":" + "teleport");
        //GameRegistry.registerTileEntity(TEPsy.class, StalmineMod.MODID + ":" + "psy");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(StalmineBlocks.blockFireplace)
                .setRegistryName(StalmineBlocks.blockFireplace.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.blockFireplaceLit)
                .setRegistryName(StalmineBlocks.blockFireplaceLit.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.blockBonfire)
                .setRegistryName(StalmineBlocks.blockBonfire.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.blockBonfireLit)
                .setRegistryName(StalmineBlocks.blockBonfireLit.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.bag)
                .setRegistryName(StalmineBlocks.bag.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.bag1)
                .setRegistryName(StalmineBlocks.bag1.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.safe1)
                .setRegistryName(StalmineBlocks.safe1.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.safe2)
                .setRegistryName(StalmineBlocks.safe2.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.safe3)
                .setRegistryName(StalmineBlocks.safe3.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.safe4)
                .setRegistryName(StalmineBlocks.safe4.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.roadblock)
                .setRegistryName(StalmineBlocks.roadblock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.electricalPanel)
                .setRegistryName(StalmineBlocks.electricalPanel.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.sigarets)
                .setRegistryName(StalmineBlocks.sigarets.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.lamp)
                .setRegistryName(StalmineBlocks.lamp.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.lamp2)
                .setRegistryName(StalmineBlocks.lamp2.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.lamp3)
                .setRegistryName(StalmineBlocks.lamp3.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.radio)
                .setRegistryName(StalmineBlocks.radio.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.barrel1)
                .setRegistryName(StalmineBlocks.barrel1.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.barrel2)
                .setRegistryName(StalmineBlocks.barrel2.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.barrel3)
                .setRegistryName(StalmineBlocks.barrel3.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.barrel4)
                .setRegistryName(StalmineBlocks.barrel4.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.barrel5)
                .setRegistryName(StalmineBlocks.barrel5.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.hedg)
                .setRegistryName(StalmineBlocks.hedg.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.ballon1)
                .setRegistryName(StalmineBlocks.ballon1.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.ballon2)
                .setRegistryName(StalmineBlocks.ballon2.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.ballon3)
                .setRegistryName(StalmineBlocks.ballon3.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.radSign)
                .setRegistryName(StalmineBlocks.radSign.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.wpnUpgradeTable)
                .setRegistryName(StalmineBlocks.wpnUpgradeTable.getRegistryName()));

        event.getRegistry().register(new ItemBlock(StalmineBlocks.electra)
                .setRegistryName(StalmineBlocks.electra.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.funnel)
                .setRegistryName(StalmineBlocks.funnel.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.springboard)
                .setRegistryName(StalmineBlocks.springboard.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.carousel)
                .setRegistryName(StalmineBlocks.carousel.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.deepfry)
                .setRegistryName(StalmineBlocks.deepfry.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.steam)
                .setRegistryName(StalmineBlocks.steam.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.soda)
                .setRegistryName(StalmineBlocks.soda.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.kissel)
                .setRegistryName(StalmineBlocks.kissel.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.teleport)
                .setRegistryName(StalmineBlocks.teleport.getRegistryName()));

        event.getRegistry().register(new ItemBlock(StalmineBlocks.rad0)
                .setRegistryName(StalmineBlocks.rad0.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.rad1)
                .setRegistryName(StalmineBlocks.rad1.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.rad2)
                .setRegistryName(StalmineBlocks.rad2.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.rad3)
                .setRegistryName(StalmineBlocks.rad3.getRegistryName()));

        event.getRegistry().register(new ItemBlock(StalmineBlocks.psy0)
                .setRegistryName(StalmineBlocks.psy0.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.psy1)
                .setRegistryName(StalmineBlocks.psy1.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.psy2)
                .setRegistryName(StalmineBlocks.psy2.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.psy3)
                .setRegistryName(StalmineBlocks.psy3.getRegistryName()));

        event.getRegistry().register(new ItemBlock(StalmineBlocks.chem0)
                .setRegistryName(StalmineBlocks.chem0.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.chem1)
                .setRegistryName(StalmineBlocks.chem1.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.chem2)
                .setRegistryName(StalmineBlocks.chem2.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.chem3)
                .setRegistryName(StalmineBlocks.chem3.getRegistryName()));

        event.getRegistry().register(new ItemBlock(StalmineBlocks.term0)
                .setRegistryName(StalmineBlocks.term0.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.term1)
                .setRegistryName(StalmineBlocks.term1.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.term2)
                .setRegistryName(StalmineBlocks.term2.getRegistryName()));
        event.getRegistry().register(new ItemBlock(StalmineBlocks.term3)
                .setRegistryName(StalmineBlocks.term3.getRegistryName()));

        event.getRegistry().register(new ItemLighter().setRegistryName("lighter"));
        event.getRegistry().register(new ItemErkedStick().setRegistryName("erked_stick"));
        event.getRegistry().register(new ItemContainer("container_1"));
        event.getRegistry().register(new ItemContainer("container_2"));
        event.getRegistry().register(new ItemDocument("document_1"));
        event.getRegistry().register(new ItemDocument("document_2"));
        event.getRegistry().register(new ItemDocument("document_3"));
        event.getRegistry().register(new ItemDocument("document_4"));
        event.getRegistry().register(new ItemGuitar("guitar"));
        event.getRegistry().register(new ItemArtBox("art_box_1", 1));
        event.getRegistry().register(new ItemArtBox("art_box_2", 2));
        event.getRegistry().register(new ItemArtBox("art_box_3", 3));

        event.getRegistry().register(new ItemMoney("money_1", 1));
        event.getRegistry().register(new ItemMoney("money_2", 2));
        event.getRegistry().register(new ItemMoney("money_5", 5));
        event.getRegistry().register(new ItemMoney("money_10", 10));
        event.getRegistry().register(new ItemMoney("money_20", 20));
        event.getRegistry().register(new ItemMoney("money_50", 50));
        event.getRegistry().register(new ItemMoney("money_100", 100));
        event.getRegistry().register(new ItemMoney("money_200", 200));
        event.getRegistry().register(new ItemMoney("money_500", 500));
        event.getRegistry().register(new ItemMoney("money_1000", 1000));
        event.getRegistry().register(new ItemMoney("money_2000", 2000));
        event.getRegistry().register(new ItemMoney("money_5000", 5000));
        event.getRegistry().register(new ItemMoney("money_10000", 10000));

        event.getRegistry().register(new ItemArtifact("art_medusa", 1));
        event.getRegistry().register(new ItemArtifact("art_twist", 2));
        event.getRegistry().register(new ItemArtifact("art_stoneflower",1));
        event.getRegistry().register(new ItemArtifact("art_nightstar",1));
        event.getRegistry().register(new ItemArtifact("art_goldfish", 4));
        event.getRegistry().register(new ItemArtifact("art_gravi", 2));
        event.getRegistry().register(new ItemArtifact("art_battery", 1));
        event.getRegistry().register(new ItemArtifact("art_sparkler", 1));
        event.getRegistry().register(new ItemArtifact("art_flash", 3));
        event.getRegistry().register(new ItemArtifact("art_eye", 4));
        event.getRegistry().register(new ItemArtifact("art_soul",2));
        event.getRegistry().register(new ItemArtifact("art_kolobok", 4));
        event.getRegistry().register(new ItemArtifact("art_compass", 10));
        event.getRegistry().register(new ItemArtifact("art_bubble", 4));
        event.getRegistry().register(new ItemArtifact("art_crystal", 2));
        event.getRegistry().register(new ItemArtifact("art_dummy", 2));
        event.getRegistry().register(new ItemArtifact("art_fireball", 3));
        event.getRegistry().register(new ItemArtifact("art_flame", 4));
        event.getRegistry().register(new ItemArtifact("art_meatpiece", 2));
        event.getRegistry().register(new ItemArtifact("art_mombeads", 1));
        event.getRegistry().register(new ItemArtifact("art_moonlight",3));
        event.getRegistry().register(new ItemArtifact("art_snowflake", 4));
        event.getRegistry().register(new ItemArtifact("art_stoneblood", 1));
        event.getRegistry().register(new ItemArtifact("art_seaurchin", 3));
        event.getRegistry().register(new ItemArtifact("art_thorn", 2));
        event.getRegistry().register(new ItemArtifact("art_crystal_thorn", 4));
        event.getRegistry().register(new ItemArtifact("art_spring", 3));
        event.getRegistry().register(new ItemArtifact("art_slug", 3));
        event.getRegistry().register(new ItemArtifact("art_slime", 2));
        event.getRegistry().register(new ItemArtifact("art_mica", 4));
        event.getRegistry().register(new ItemArtifact("art_membrane", 3));
        event.getRegistry().register(new ItemArtifact("art_bitum", 4));
        event.getRegistry().register(new ItemArtifact("art_drops", 1));

        event.getRegistry().register(new ItemFood("sausage", "food", 4, 0.5f));
        event.getRegistry().register(new ItemFood("loaf", "food", 3, 0.2f));
        event.getRegistry().register(new ItemFood("canned", "food", 8, 0.4f));
        event.getRegistry().register(new ItemFood("sardines", "food", 6, 0.6f));
        event.getRegistry().register(new ItemEnergyDrink("energy_drink", "drink"));
        event.getRegistry().register(new ItemVodka("vodka", "vodka"));

        event.getRegistry().register(new ItemGrenadeLauncher("w_gp25"));
        event.getRegistry().register(new ItemGrenadeLauncher("w_gl5040"));
        event.getRegistry().register(new ItemCrosshair("w_pso1"));
        event.getRegistry().register(new ItemCrosshair("w_psu1"));
        event.getRegistry().register(new ItemCrosshair("w_susat"));
        event.getRegistry().register(new ItemCrosshair("w_susat16"));
        event.getRegistry().register(new ItemCrosshair("w_susat16nvd"));
        event.getRegistry().register(new ItemCrosshair("w_susat4nvd"));

        event.getRegistry().register(new ItemMedkit("medkit_1", "medkit", 1));
        event.getRegistry().register(new ItemMedkit("medkit_2", "medkit", 2));
        event.getRegistry().register(new ItemMedkit("medkit_3", "medkit", 3));
        event.getRegistry().register(new ItemMedkit("medkit_4", "medkit", 4));

        event.getRegistry().register(new ItemBandage("bandage", "bandage"));
        event.getRegistry().register(new ItemBarvinok("barvinok", "pills"));
        event.getRegistry().register(new ItemPsyBlockade("psy_blockade", "pills"));
        event.getRegistry().register(new ItemRadioprotector("radioprotector", "pills"));
        event.getRegistry().register(new ItemAnabiotik("anabiotik", "pills"));

        event.getRegistry().register(new ItemAmmo("am_545x18", 36, 1f));
        event.getRegistry().register(new ItemAmmo("am_545x39", 30, 1f));
        event.getRegistry().register(new ItemAmmo("am_545x39bp", 30, 1.25f));
        event.getRegistry().register(new ItemAmmo("am_556x45", 30, 1f));
        event.getRegistry().register(new ItemAmmo("am_556x45m855", 30, 1.25f));
        event.getRegistry().register(new ItemAmmo("am_16x70", 10, 1f));
        event.getRegistry().register(new ItemAmmo("am_57x28", 50, 1f));
        event.getRegistry().register(new ItemAmmo("am_762x54", 20, 1f));
        event.getRegistry().register(new ItemAmmo("am_762x54bp", 15, 1.25f));
        event.getRegistry().register(new ItemAmmo("am_762x54bs", 10, 1.5f));
        event.getRegistry().register(new ItemAmmo("am_762x54pp", 50, 1.75f));
        event.getRegistry().register(new ItemAmmo("am_9x39", 20, 1f));
        event.getRegistry().register(new ItemAmmo("am_9x39bp", 15, 1.5f));
        event.getRegistry().register(new ItemAmmo("am_9x19", 50, 1f));
        event.getRegistry().register(new ItemAmmo("am_9x19pbp", 30, 1.25f));
        event.getRegistry().register(new ItemAmmo("am_dot45acp", 24, 1f));
        event.getRegistry().register(new ItemAmmo("am_dot45acppp", 16, 1.5f));
        event.getRegistry().register(new ItemAmmo("am_accum", 10, 1f));
        event.getRegistry().register(new ItemAmmo("am_accum30", 10, 2f));
        event.getRegistry().register(new ItemAmmo("am_vog25", 1, 1f).setMaxStackSize(4));
        event.getRegistry().register(new ItemAmmo("am_m430", 1, 1.25f).setMaxStackSize(4));
        event.getRegistry().register(new ItemAmmo("am_rpg7", 1, 1f));

        event.getRegistry().register(new ItemRGD5());
        event.getRegistry().register(new ItemF1());

        event.getRegistry().register(new ItemKnife());

        int accum = 0;

        for (int i = accum; i < accum + StalmineWeapons.pistolNum; i++) {
            event.getRegistry().register(new WeaponPistol(StalmineWeapons.w_list.get(i)));
        }
        accum += StalmineWeapons.pistolNum;

        for (int i = accum; i < accum + StalmineWeapons.autoRifleNum; i++) {
            event.getRegistry().register(new WeaponAuto(StalmineWeapons.w_list.get(i)));
        }
        accum += StalmineWeapons.autoRifleNum;

        for (int i = accum; i < accum + StalmineWeapons.autoGrenadeRifleNum; i++) {
            event.getRegistry().register(new WeaponAutoGrenade(StalmineWeapons.w_list.get(i)));
        }
        accum += StalmineWeapons.autoGrenadeRifleNum;

        for (int i = accum; i < accum + StalmineWeapons.shotgunNum; i++) {
            event.getRegistry().register(new WeaponShotgun(StalmineWeapons.w_list.get(i)));
        }
        accum += StalmineWeapons.shotgunNum;

        for (int i = accum; i < accum + StalmineWeapons.sniperNum; i++) {
            event.getRegistry().register(new WeaponSniperRifle(StalmineWeapons.w_list.get(i)));
        }
        accum += StalmineWeapons.sniperNum;

        for (int i = accum; i < accum + StalmineWeapons.sniperAutoNum; i++) {
            event.getRegistry().register(new WeaponSniperAutoRifle(StalmineWeapons.w_list.get(i)));
        }
        accum += StalmineWeapons.sniperAutoNum;

        for (int i = accum; i < accum + StalmineWeapons.sniperAutoGrenadeNum; i++) {
            event.getRegistry().register(new WeaponSniperGrenadeAutoRifle(StalmineWeapons.w_list.get(i)));
        }
        accum += StalmineWeapons.sniperAutoGrenadeNum;

        event.getRegistry().register(new WeaponRocket(StalmineWeapons.w_list.get(accum++))); // RPG 7

        {
            int arm_id = 1;
            int arm_i = 0;
            for (int i = 0; i < 2; i++) {
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_JACKET, 0, EntityEquipmentSlot.CHEST, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_JACKET, 0, EntityEquipmentSlot.HEAD, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_JACKET, 0, EntityEquipmentSlot.LEGS, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_JACKET, 0, EntityEquipmentSlot.FEET, arm_id));
                arm_id++;
            }

            for (int i = 0; i < 2; i++) {
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_CAPE, 1, EntityEquipmentSlot.CHEST, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_CAPE, 1, EntityEquipmentSlot.HEAD, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_CAPE, 1, EntityEquipmentSlot.LEGS, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_CAPE, 1, EntityEquipmentSlot.FEET, arm_id));
                arm_id++;
            }

            for (int i = 0; i < 7; i++) {
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_KOMBEZ, 1, EntityEquipmentSlot.CHEST, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_KOMBEZ, 1, EntityEquipmentSlot.HEAD, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_KOMBEZ, 1, EntityEquipmentSlot.LEGS, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_KOMBEZ, 1, EntityEquipmentSlot.FEET, arm_id));
                arm_id++;
            }

            for (int i = 0; i < 8; i++) {
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_HEAVY, 1, EntityEquipmentSlot.CHEST, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_HEAVY, 1, EntityEquipmentSlot.HEAD, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_HEAVY, 1, EntityEquipmentSlot.LEGS, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_HEAVY, 1, EntityEquipmentSlot.FEET, arm_id));
                arm_id++;
            }

            for (int i = 0; i < 8; i++) {
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_EXO, 1, EntityEquipmentSlot.CHEST, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_EXO, 1, EntityEquipmentSlot.HEAD, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_EXO, 1, EntityEquipmentSlot.LEGS, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_EXO, 1, EntityEquipmentSlot.FEET, arm_id));
                arm_id++;
            }

            for (int i = 0; i < 5; i++) {
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_SEVA, 1, EntityEquipmentSlot.CHEST, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_SEVA, 1, EntityEquipmentSlot.HEAD, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_SEVA, 1, EntityEquipmentSlot.LEGS, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_SEVA, 1, EntityEquipmentSlot.FEET, arm_id));
                arm_id++;
            }

            for (int i = 0; i < 4; i++) {
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_SCIENTIST, 1, EntityEquipmentSlot.CHEST, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_SCIENTIST, 1, EntityEquipmentSlot.HEAD, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_SCIENTIST, 1, EntityEquipmentSlot.LEGS, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_SCIENTIST, 1, EntityEquipmentSlot.FEET, arm_id));
                arm_id++;
            }

            for (int i = 0; i < 3; i++) {
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_BERILL, 1, EntityEquipmentSlot.CHEST, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_BERILL, 1, EntityEquipmentSlot.HEAD, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_BERILL, 1, EntityEquipmentSlot.LEGS, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_BERILL, 1, EntityEquipmentSlot.FEET, arm_id));
                arm_id++;
            }

            for (int i = 0; i < 5; i++) {
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_BULAT, 1, EntityEquipmentSlot.CHEST, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_BULAT, 1, EntityEquipmentSlot.HEAD, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_BULAT, 1, EntityEquipmentSlot.LEGS, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_BULAT, 1, EntityEquipmentSlot.FEET, arm_id));
                arm_id++;
            }

            for (int i = 0; i < 8; i++) {
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_ZARYA, 1, EntityEquipmentSlot.CHEST, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_ZARYA, 1, EntityEquipmentSlot.HEAD, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_ZARYA, 1, EntityEquipmentSlot.LEGS, arm_id));
                event.getRegistry().register(new Armor(
                        StalmineArmor.arm_names.get(arm_i++), StalmineArmor.MATERIAL_ZARYA, 1, EntityEquipmentSlot.FEET, arm_id));
                arm_id++;
            }
        }
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        int id = 0;
        event.getRegistry().register(
                EntityEntryBuilder
                        .create()
                        .entity(EntityBullet.class)
                        .tracker(256, 1000, true)
                        .id(new ResourceLocation(StalmineMod.MODID, "stalmine_bullet"), id++)
                        .name("Stalmine Bullet")
                        .build()
        );
        event.getRegistry().register(
                EntityEntryBuilder
                        .create()
                        .entity(EntityRGD5.class)
                        .tracker(128, 1000, true)
                        .id(new ResourceLocation(StalmineMod.MODID, "stalmine_rgd5"), id++)
                        .name("Stalmine RGD5")
                        .build()
        );
        event.getRegistry().register(
                EntityEntryBuilder
                        .create()
                        .entity(EntityF1.class)
                        .tracker(128, 1000, true)
                        .id(new ResourceLocation(StalmineMod.MODID, "stalmine_f1"), id++)
                        .name("Stalmine F1")
                        .build()
        );
        event.getRegistry().register(
                EntityEntryBuilder
                        .create()
                        .entity(EntityBulletGrenade.class)
                        .tracker(256, 1000, true)
                        .id(new ResourceLocation(StalmineMod.MODID, "stalmine_bullet_grenade"), id++)
                        .name("Stalmine Bullet Grenade")
                        .build()
        );
        event.getRegistry().register(
                EntityEntryBuilder
                        .create()
                        .entity(EntityBulletGrenadeOG7V.class)
                        .tracker(256, 1000, true)
                        .id(new ResourceLocation(StalmineMod.MODID, "stalmine_bullet_grenade_og7v"), id++)
                        .name("Stalmine Bullet Grenade OG7V")
                        .build()
        );
        event.getRegistry().register(
                EntityEntryBuilder
                        .create()
                        .entity(EntityBulletGrenadeM430.class)
                        .tracker(256, 1000, true)
                        .id(new ResourceLocation(StalmineMod.MODID, "stalmine_bullet_grenade_m430"), id++)
                        .name("Stalmine Bullet Grenade M430")
                        .build()
        );
    }

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event) {
        event.getRegistry().register(StalminePotions.RADIATION.setRegistryName("radiation"));
        event.getRegistry().register(StalminePotions.PSY.setRegistryName("psy"));
        event.getRegistry().register(StalminePotions.CHEM.setRegistryName("chem"));
        event.getRegistry().register(StalminePotions.TERM.setRegistryName("term"));
        event.getRegistry().register(StalminePotions.ANTIRAD.setRegistryName("antirad"));
        event.getRegistry().register(StalminePotions.ANTIPSY.setRegistryName("antipsy"));
        event.getRegistry().register(StalminePotions.ANTICHEM.setRegistryName("antichem"));
        event.getRegistry().register(StalminePotions.ANTITERM.setRegistryName("antiterm"));
    }

    @SubscribeEvent
    public static void onSnowballHit(ProjectileImpactEvent.Throwable event) {
        if (event.getThrowable() instanceof EntitySnowball) {
            if (event.getRayTraceResult().typeOfHit == RayTraceResult.Type.BLOCK) {
                World w = event.getThrowable().world;
                IBlockState state = w.getBlockState(event.getRayTraceResult().getBlockPos());
                BlockPos pos = event.getRayTraceResult().getBlockPos();
                Block b = state.getBlock();
                if (b instanceof BlockElectra) {
                    BlockElectra el = (BlockElectra) b;
                    boolean hit = false;
                    if (!w.isRemote) {
                        hit = el.updateState(w, pos, event.getThrowable());
                    }
                    if (hit) {
                        el.showBlast(Minecraft.getMinecraft().world, pos);
                    }
                } else if (b instanceof BlockDeepfry) {
                    BlockDeepfry df = (BlockDeepfry) b;
                    boolean hit = false;
                    if (!w.isRemote) {
                        hit = df.updateState(w, pos, event.getThrowable());
                    }
                    if (hit) {
                        df.showBlast(Minecraft.getMinecraft().world, pos);
                    }
                } else if (b instanceof BlockSteam) {
                    BlockSteam st = (BlockSteam) b;
                    boolean hit = false;
                    if (!w.isRemote) {
                        hit = st.updateState(w, pos, event.getThrowable());
                    }
                    if (hit) {
                        st.showBlast(Minecraft.getMinecraft().world, pos);
                    }
                } else if (w.isRemote && (b instanceof BlockFunnel)) {
                    BlockFunnel anom = (BlockFunnel) b;
                    anom.showHit(Minecraft.getMinecraft().world, pos);
                }  else if (w.isRemote && (b instanceof BlockSpringboard)) {
                    BlockSpringboard anom = (BlockSpringboard) b;
                    anom.showHit(Minecraft.getMinecraft().world, pos);
                }  else if (w.isRemote && (b instanceof BlockCarousel)) {
                    BlockCarousel anom = (BlockCarousel) b;
                    anom.showHit(Minecraft.getMinecraft().world, pos);
                } else if (w.isRemote && b instanceof BlockSoda) {
                    Minecraft.getMinecraft().world.playSound(
                            pos.getX(),
                            pos.getY(),
                            pos.getZ(),
                            StalmineSounds.pool.get("soda_blast"),
                            SoundCategory.BLOCKS,
                            1.0f,
                            1.0f - w.rand.nextFloat() * .2f,
                            true
                    );
                }
            }
        }
    }

    public void registerParticles() {}

    @SubscribeEvent
    public static void onMobSpawn(LivingSpawnEvent.CheckSpawn event) {
        EntityLiving el = (EntityLiving) event.getEntity();
        if (el instanceof EntityCreeper ||
                el instanceof EntitySkeleton ||
                el instanceof EntityHusk ||
                el instanceof EntityZombieVillager ||
                el instanceof EntityEnderman ||
                el instanceof EntitySpider ||
                el instanceof EntityWitch ||
                el instanceof EntityAnimal ||
                el instanceof EntitySlime ||
                el instanceof EntityWaterMob ||
                el instanceof EntityAmbientCreature ||
                el instanceof EntityGuardian ||
                el instanceof EntitySkeletonHorse) {
            event.setResult(Event.Result.DENY);
            return;
        }
        if (el instanceof EntityZombie) {
            if (event.getWorld().rand.nextFloat() > .9f && !el.isChild()) {
                for (ItemStack is : el.getEquipmentAndArmor()) {
                    is = ItemStack.EMPTY;
                }
                int[] armNum = new int[] { 2, 2, 7, 8, 8, 5, 4, 3, 5, 8 };
                ArrayList<Float> probs = new ArrayList<>();
                probs.add(.28f); // Jacket
                probs.add(.2f); // Cape
                probs.add(.1f); // Kombez
                probs.add(.015f); // Heavy
                probs.add(.005f); // Exo
                probs.add(.05f); // Seva
                probs.add(.05f); // Scientist
                probs.add(.1f); // Berill
                probs.add(.02f); // Bulat
                probs.add(.18f); // Zarya
                float probe = event.getWorld().rand.nextFloat();
                float accum = 0f;
                int armAccum = 0;
                for (int i = 0; i < 10; i++) {
                    accum += probs.get(i);
                    if (probe <= accum) {
                        int finalArmor = event.getWorld().rand.nextInt(armNum[i]);
                        int boots = armAccum + finalArmor * 4 + 3;
                        int legs = armAccum + finalArmor * 4 + 2;
                        int chest = armAccum + finalArmor * 4;
                        ArrayList<ItemStack> zombieArmor = new ArrayList<ItemStack>() {{
                            add(new ItemStack(StalmineArmor.allArmor.get(boots)));
                            add(new ItemStack(StalmineArmor.allArmor.get(legs)));
                            add(new ItemStack(StalmineArmor.allArmor.get(chest)));
                            add(ItemStack.EMPTY);
                        }};
                        for (int j = 0; j < 4; j++) {
                            ((NonNullList<ItemStack>) el.getArmorInventoryList()).set(j, zombieArmor.get(j));
                        }
                        break;
                    }
                    armAccum += 4 * armNum[i];
                }
            } else {
                event.setResult(Event.Result.DENY);
                return;
            }
        }
    }

    @SubscribeEvent
    public static void onMobDrop(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityZombie) {
            event.getDrops().clear();
            Random rand = event.getEntityLiving().world.rand;
            if (rand.nextFloat() < .1f) {
                ItemStack is = new ItemStack(Item.getByNameOrId("snowball"), 1);
                event.getDrops().add(new EntityItem(
                        event.getEntityLiving().world,
                        event.getEntityLiving().posX,
                        event.getEntityLiving().posY,
                        event.getEntityLiving().posZ,
                        is
                ));
            }
            if (rand.nextFloat() < .05f) {
                ItemStack is = new ItemStack(StalmineItems.itemLoaf, 1);
                event.getDrops().add(new EntityItem(
                        event.getEntityLiving().world,
                        event.getEntityLiving().posX,
                        event.getEntityLiving().posY,
                        event.getEntityLiving().posZ,
                        is
                ));
            }
            if (rand.nextFloat() < .05f) {
                ItemStack is = new ItemStack(StalmineItems.itemBandage, 1);
                event.getDrops().add(new EntityItem(
                        event.getEntityLiving().world,
                        event.getEntityLiving().posX,
                        event.getEntityLiving().posY,
                        event.getEntityLiving().posZ,
                        is
                ));
            }
        }
    }

    @SubscribeEvent
    public static void onZombieBurn(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntity() instanceof EntityZombie) {
            if (event.getEntity().isBurning()) {
                event.getEntity().extinguish();
            }
        }
    }

    @SubscribeEvent
    public static void onExplosion(ExplosionEvent.Detonate e) {
        e.getAffectedBlocks().clear();
    }

    @SubscribeEvent
    public static void onExpDrop(LivingExperienceDropEvent e) {
        e.setCanceled(true);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (!mc.isGamePaused() && mc.world != null) {
            // Artifacts effects
            ArrayList<Integer> curDurs = new ArrayList<Integer>() {
                {
                    add(event.player.isPotionActive(StalminePotions.RADIATION) ?
                            event.player.getActivePotionEffect(StalminePotions.RADIATION).getDuration() : -1);
                    add(event.player.isPotionActive(StalminePotions.PSY) ?
                            event.player.getActivePotionEffect(StalminePotions.PSY).getDuration() : -1);
                    add(event.player.isPotionActive(StalminePotions.CHEM) ?
                            event.player.getActivePotionEffect(StalminePotions.CHEM).getDuration() : -1);
                    add(event.player.isPotionActive(StalminePotions.TERM) ?
                            event.player.getActivePotionEffect(StalminePotions.TERM).getDuration() : -1);
                    add(event.player.isPotionActive(Potion.getPotionById(19)) ?
                            event.player.getActivePotionEffect(Potion.getPotionById(19)).getDuration() : -1);
                    add(event.player.isPotionActive(Potion.getPotionById(2)) ?
                            event.player.getActivePotionEffect(Potion.getPotionById(2)).getDuration() : -1);
                    add(event.player.isPotionActive(Potion.getPotionById(18)) ?
                            event.player.getActivePotionEffect(Potion.getPotionById(18)).getDuration() : -1);
                    add(event.player.isPotionActive(StalminePotions.ANTIRAD) ?
                            event.player.getActivePotionEffect(StalminePotions.ANTIRAD).getDuration() : -1);
                    add(event.player.isPotionActive(StalminePotions.ANTIPSY) ?
                            event.player.getActivePotionEffect(StalminePotions.ANTIPSY).getDuration() : -1);
                    add(event.player.isPotionActive(StalminePotions.ANTICHEM) ?
                            event.player.getActivePotionEffect(StalminePotions.ANTICHEM).getDuration() : -1);
                    add(event.player.isPotionActive(StalminePotions.ANTITERM) ?
                            event.player.getActivePotionEffect(StalminePotions.ANTITERM).getDuration() : -1);
                    add(event.player.isPotionActive(Potion.getPotionById(10)) ?
                            event.player.getActivePotionEffect(Potion.getPotionById(10)).getDuration() : -1);
                    add(event.player.isPotionActive(Potion.getPotionById(1)) ?
                            event.player.getActivePotionEffect(Potion.getPotionById(1)).getDuration() : -1);
                    add(event.player.isPotionActive(Potion.getPotionById(11)) ?
                            event.player.getActivePotionEffect(Potion.getPotionById(11)).getDuration() : -1);
                }
            };
            ArrayList<Integer> curAmpls = new ArrayList<Integer>() {
                {
                    add(event.player.isPotionActive(StalminePotions.RADIATION) ?
                            event.player.getActivePotionEffect(StalminePotions.RADIATION).getAmplifier() : -1);
                    add(event.player.isPotionActive(StalminePotions.PSY) ?
                            event.player.getActivePotionEffect(StalminePotions.PSY).getAmplifier() : -1);
                    add(event.player.isPotionActive(StalminePotions.CHEM) ?
                            event.player.getActivePotionEffect(StalminePotions.CHEM).getAmplifier() : -1);
                    add(event.player.isPotionActive(StalminePotions.TERM) ?
                            event.player.getActivePotionEffect(StalminePotions.TERM).getAmplifier() : -1);
                    add(event.player.isPotionActive(Potion.getPotionById(19)) ?
                            event.player.getActivePotionEffect(Potion.getPotionById(19)).getAmplifier() : -1);
                    add(event.player.isPotionActive(Potion.getPotionById(2)) ?
                            event.player.getActivePotionEffect(Potion.getPotionById(2)).getAmplifier() : -1);
                    add(event.player.isPotionActive(Potion.getPotionById(18)) ?
                            event.player.getActivePotionEffect(Potion.getPotionById(18)).getAmplifier() : -1);
                    add(event.player.isPotionActive(StalminePotions.ANTIRAD) ?
                            event.player.getActivePotionEffect(StalminePotions.ANTIRAD).getAmplifier() : -1);
                    add(event.player.isPotionActive(StalminePotions.ANTIPSY) ?
                            event.player.getActivePotionEffect(StalminePotions.ANTIPSY).getAmplifier() : -1);
                    add(event.player.isPotionActive(StalminePotions.ANTICHEM) ?
                            event.player.getActivePotionEffect(StalminePotions.ANTICHEM).getAmplifier() : -1);
                    add(event.player.isPotionActive(StalminePotions.ANTITERM) ?
                            event.player.getActivePotionEffect(StalminePotions.ANTITERM).getAmplifier() : -1);
                    add(event.player.isPotionActive(Potion.getPotionById(10)) ?
                            event.player.getActivePotionEffect(Potion.getPotionById(10)).getAmplifier() : -1);
                    add(event.player.isPotionActive(Potion.getPotionById(1)) ?
                            event.player.getActivePotionEffect(Potion.getPotionById(1)).getAmplifier() : -1);
                    add(event.player.isPotionActive(Potion.getPotionById(11)) ?
                            event.player.getActivePotionEffect(Potion.getPotionById(11)).getAmplifier() : -1);
                }
            };
            ArrayList<Float> effects = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                effects.add(0f);
            }
            for (int i = 0; i < 9; i++) {
                ItemStack is = event.player.inventory.getStackInSlot(i);
                if (!is.isEmpty() && is.getItem() instanceof ItemArtifact) {
                    ItemArtifact ia = (ItemArtifact) is.getItem();
                    float artEffects[] = new float[14];
                    artEffects[0] = ia.model.getRad();
                    artEffects[1] = ia.model.getPsy();
                    artEffects[2] = ia.model.getChem();
                    artEffects[3] = ia.model.getTerm();
                    artEffects[4] = ia.model.getPoison();
                    artEffects[5] = ia.model.getSlowness();
                    artEffects[6] = ia.model.getWeakness();
                    artEffects[7] = ia.model.getAntirad();
                    artEffects[8] = ia.model.getAntipsy();
                    artEffects[9] = ia.model.getAntichem();
                    artEffects[10] = ia.model.getAntiterm();
                    artEffects[11] = ia.model.getRegen();
                    artEffects[12] = ia.model.getSpeed();
                    artEffects[13] = ia.model.getResistance();
                    for (int j = 0; j < 7; j++) {
                        effects.set(j, effects.get(j) + (artEffects[j] - artEffects[j + 7]));
                    }
                }
            }
            for (int i = 0; i < 7; i++) {
                if (effects.get(i) > 0f) {
                    effects.set(i, Math.min(.999f, effects.get(i)));
                } else {
                    effects.set(i, Math.max(-.999f, effects.get(i)));
                }
                if (effects.get(i) != 0f) {
                    int newAmplSpecial = (int) Math.floor(Math.abs(effects.get(i) * 10f));
                    int newAmpl = (int) Math.floor(Math.abs(effects.get(i) * 4f));
                    int newDur = 300;
                    if ((effects.get(i) > 0f && curDurs.get(i) < 210)
                            || (effects.get(i) < 0f && curDurs.get(i + 7) < 210)
                            || (curAmpls.get(i) < newAmpl && effects.get(i) > 0f)
                            || (curAmpls.get(i + 7) < (i + 7 < 11 ? newAmplSpecial : newAmpl) && effects.get(i) < 0f)) {
                        if (effects.get(i) > 0f) {
                            switch (i) {
                                case 0: {
                                    event.player.removePotionEffect(StalminePotions.RADIATION);
                                    event.player.addPotionEffect(
                                            new PotionEffect(
                                                    StalminePotions.RADIATION,
                                                    Math.max(newDur, curDurs.get(i)),
                                                    Math.max(newAmpl, curAmpls.get(i)),
                                                    false,
                                                    true
                                            )
                                    );
                                    break;
                                }
                                case 1: {
                                    event.player.removePotionEffect(StalminePotions.PSY);
                                    event.player.addPotionEffect(
                                            new PotionEffect(
                                                    StalminePotions.PSY,
                                                    Math.max(newDur, curDurs.get(i)),
                                                    Math.max(newAmpl, curAmpls.get(i)),
                                                    false,
                                                    true
                                            )
                                    );
                                    break;
                                }
                                case 2: {
                                    event.player.removePotionEffect(StalminePotions.CHEM);
                                    event.player.addPotionEffect(
                                            new PotionEffect(
                                                    StalminePotions.CHEM,
                                                    Math.max(newDur, curDurs.get(i)),
                                                    Math.max(newAmpl, curAmpls.get(i)),
                                                    false,
                                                    true
                                            )
                                    );
                                    break;
                                }
                                case 3: {
                                    event.player.removePotionEffect(StalminePotions.TERM);
                                    event.player.addPotionEffect(
                                            new PotionEffect(
                                                    StalminePotions.TERM,
                                                    Math.max(newDur, curDurs.get(i)),
                                                    Math.max(newAmpl, curAmpls.get(i)),
                                                    false,
                                                    true
                                            )
                                    );
                                    break;
                                }
                                case 4: {
                                    event.player.removePotionEffect(Potion.getPotionById(19));
                                    event.player.addPotionEffect(
                                            new PotionEffect(
                                                    Potion.getPotionById(19),
                                                    Math.max(newDur, curDurs.get(i)),
                                                    Math.max(newAmpl, curAmpls.get(i)),
                                                    false,
                                                    true
                                            )
                                    );
                                    break;
                                }
                                case 5: {
                                    event.player.removePotionEffect(Potion.getPotionById(2));
                                    event.player.addPotionEffect(
                                            new PotionEffect(
                                                    Potion.getPotionById(2),
                                                    Math.max(newDur, curDurs.get(i)),
                                                    Math.max(newAmpl, curAmpls.get(i)),
                                                    false,
                                                    true
                                            )
                                    );
                                    break;
                                }
                                case 6: {
                                    event.player.removePotionEffect(Potion.getPotionById(18));
                                    event.player.addPotionEffect(
                                            new PotionEffect(
                                                    Potion.getPotionById(18),
                                                    Math.max(newDur, curDurs.get(i)),
                                                    Math.max(newAmpl, curAmpls.get(i)),
                                                    false,
                                                    true
                                            )
                                    );
                                    break;
                                }
                                default:
                                    break;
                            }
                        } else {
                            switch (i) {
                                case 0: {
                                    event.player.removePotionEffect(StalminePotions.ANTIRAD);
                                    event.player.addPotionEffect(
                                            new PotionEffect(
                                                    StalminePotions.ANTIRAD,
                                                    Math.max(newDur, curDurs.get(i + 7)),
                                                    Math.max(newAmplSpecial, curAmpls.get(i + 7)),
                                                    false,
                                                    true
                                            )
                                    );
                                    break;
                                }
                                case 1: {
                                    event.player.removePotionEffect(StalminePotions.ANTIPSY);
                                    event.player.addPotionEffect(
                                            new PotionEffect(
                                                    StalminePotions.ANTIPSY,
                                                    Math.max(newDur, curDurs.get(i + 7)),
                                                    Math.max(newAmplSpecial, curAmpls.get(i + 7)),
                                                    false,
                                                    true
                                            )
                                    );
                                    break;
                                }
                                case 2: {
                                    event.player.removePotionEffect(StalminePotions.ANTICHEM);
                                    event.player.addPotionEffect(
                                            new PotionEffect(
                                                    StalminePotions.ANTICHEM,
                                                    Math.max(newDur, curDurs.get(i + 7)),
                                                    Math.max(newAmplSpecial, curAmpls.get(i + 7)),
                                                    false,
                                                    true
                                            )
                                    );
                                    break;
                                }
                                case 3: {
                                    event.player.removePotionEffect(StalminePotions.ANTITERM);
                                    event.player.addPotionEffect(
                                            new PotionEffect(
                                                    StalminePotions.ANTITERM,
                                                    Math.max(newDur, curDurs.get(i + 7)),
                                                    Math.max(newAmplSpecial, curAmpls.get(i + 7)),
                                                    false,
                                                    true
                                            )
                                    );
                                    break;
                                }
                                case 4: {
                                    event.player.removePotionEffect(Potion.getPotionById(10));
                                    event.player.addPotionEffect(
                                            new PotionEffect(
                                                    Potion.getPotionById(10),
                                                    Math.max(newDur, curDurs.get(i + 7)),
                                                    Math.max(newAmpl, curAmpls.get(i + 7)),
                                                    false,
                                                    true
                                            )
                                    );
                                    break;
                                }
                                case 5: {
                                    event.player.removePotionEffect(Potion.getPotionById(1));
                                    event.player.addPotionEffect(
                                            new PotionEffect(
                                                    Potion.getPotionById(1),
                                                    Math.max(newDur, curDurs.get(i + 7)),
                                                    Math.max(newAmpl, curAmpls.get(i + 7)),
                                                    false,
                                                    true
                                            )
                                    );
                                    break;
                                }
                                case 6: {
                                    event.player.removePotionEffect(Potion.getPotionById(11));
                                    event.player.addPotionEffect(
                                            new PotionEffect(
                                                    Potion.getPotionById(11),
                                                    Math.max(newDur, curDurs.get(i + 7)),
                                                    Math.max(newAmpl, curAmpls.get(i + 7)),
                                                    false,
                                                    true
                                            )
                                    );
                                    break;
                                }
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onTick(TickEvent.RenderTickEvent event) {
        if (event.side.isClient()) {
            Minecraft mc = Minecraft.getMinecraft();
            if (!mc.isGamePaused() && mc.world != null) {
                drawFieldParticles(
                        mc,
                        MathHelper.floor(mc.player.posX),
                        MathHelper.floor(mc.player.posY),
                        MathHelper.floor(mc.player.posZ)
                );

            }
        }
    }

    private static ArrayList<Item> fieldBlockItems;
    private static ArrayList<Item> anomalyBlockItems;
    public static void drawFieldParticles(Minecraft mc, int posX, int posY, int posZ) {
        Random random = new Random();
        ItemStack itemstack = mc.player.getHeldItemMainhand();
        boolean flag = mc.playerController.getCurrentGameType() == GameType.CREATIVE && !itemstack.isEmpty();
        int showType = 0;
        Item it = itemstack.getItem();
        if (fieldBlockItems.contains(it)) showType = 1;
        if (anomalyBlockItems.contains(it)) showType = 2;
        if (it == StalmineItems.itemErkedStick) showType = 3;
        flag = showType == 0 ? false : flag;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        for (int j = 0; j < 16; ++j)
        {
            showFieldParticles(mc, posX, posY, posZ, 8, random, flag, showType, blockpos$mutableblockpos);
        }
    }

    private static ArrayList<Block> fieldBlocks;
    private static ArrayList<EnumParticleTypes> particleTypes;
    public static void showFieldParticles(Minecraft mc, int x, int y, int z, int offset, Random random, boolean holdingField, int showType, BlockPos.MutableBlockPos pos) {

        int i = x + random.nextInt(offset) - random.nextInt(offset);
        int j = y + random.nextInt(offset) - random.nextInt(offset);
        int k = z + random.nextInt(offset) - random.nextInt(offset);
        pos.setPos(i, j, k);
        IBlockState iblockstate = mc.world.getBlockState(pos);

        boolean toDraw = false;
         if (holdingField) {
             EnumParticleTypes p_type = StalmineParticles.RAD_0;
             int fieldType = 0;
             int fieldAmpl = 0;

             for (int it = 0; it < fieldBlocks.size(); it++) {
                if (iblockstate.getBlock() == fieldBlocks.get(it)) {
                    p_type = particleTypes.get(it);
                    fieldType = it > 15 ? 1 : it / 4;
                    fieldAmpl = it > 15 ? 3 : it % 4;
                    switch (showType) {
                        case 1: {
                            toDraw = it < 16;
                            break;
                        }
                        case 2: {
                            toDraw = it >= 16;
                            break;
                        }
                        case 3: {
                            toDraw = true;
                            break;
                        }
                        default: {
                            toDraw = false;
                            break;
                        }
                    }
                }
            }

            if (toDraw) {
                mc.world.spawnParticle(
                        p_type,
                        (float) i + 0.5F,
                        (float) j + 0.5F,
                        (float) k + 0.5F,
                        0.0D,
                        0.0D,
                        0.0D,
                        new int[] { fieldType, fieldAmpl }
                );
            }
        }
    }

    @SubscribeEvent
    public static void onAnvilRepair(AnvilRepairEvent event) {
        event.setBreakChance(0f); // Do not want to break anvils
    }

    @SubscribeEvent
    public static void onEntityItemPickup(EntityItemPickupEvent event) {
        if (allowIntemPickup) {
            //event.setResult(Event.Result.ALLOW);
            allowIntemPickup = false;
            return;
        }
        event.setCanceled(true);
    }

}
