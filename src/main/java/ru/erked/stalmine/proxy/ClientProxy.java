package ru.erked.stalmine.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.StalmineConfig;
import ru.erked.stalmine.client.particle.*;
import ru.erked.stalmine.client.render.RenderStalminePlayer;
import ru.erked.stalmine.client.sound.StalmineSounds;
import ru.erked.stalmine.client.tabs.*;
import ru.erked.stalmine.common.armor.Armor;
import ru.erked.stalmine.common.armor.ArmorDataModel;
import ru.erked.stalmine.common.armor.StalmineArmor;
import ru.erked.stalmine.common.blocks.StalmineBlocks;
import ru.erked.stalmine.common.gui.GUIInGame;
import ru.erked.stalmine.common.gui.GUIInventory;
import ru.erked.stalmine.common.gui.GUIMainMenu;
import ru.erked.stalmine.common.items.StalmineItems;
import ru.erked.stalmine.common.network.*;
import ru.erked.stalmine.common.weapons.*;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    public static RenderStalminePlayer playerRender;
    public static KeyBinding[] keyBindings;

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        OBJLoader.INSTANCE.addDomain(StalmineMod.MODID);

        StalmineMod.tabBlocks = new StalmineTabBlocks(CreativeTabs.getNextID(), "tab_blocks");
        StalmineMod.tabItems = new StalmineTabItems(CreativeTabs.getNextID(), "tab_items");
        StalmineMod.tabWeapons = new StalmineTabWeapons(CreativeTabs.getNextID(), "tab_weapons");
        StalmineMod.tabArmor = new StalmineTabArmor(CreativeTabs.getNextID(), "tab_armor");
        StalmineMod.tabArtifacts = new StalmineTabArtifacts(CreativeTabs.getNextID(), "tab_artifacts");

    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);

        keyBindings[0] = new KeyBinding("key.stalmine.reload.desc", Keyboard.KEY_R, "key.stalmine.category");
        keyBindings[1] = new KeyBinding("key.stalmine.nvd.desc", Keyboard.KEY_N, "key.stalmine.category");
        keyBindings[2] = new KeyBinding("key.stalmine.secondary_ammo.desc", Keyboard.KEY_Y, "key.stalmine.category");
        keyBindings[3] = new KeyBinding("key.stalmine.grenade_ammo.desc", Keyboard.KEY_V, "key.stalmine.category");
        keyBindings[4] = new KeyBinding("key.stalmine.item_pickup.desc", Keyboard.KEY_F, "key.stalmine.category");
        for (KeyBinding keyBinding : keyBindings) {
            ClientRegistry.registerKeyBinding(keyBinding);
        }
    }

    @Override
    public void registerParticles() {
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.FIREPLACE_SMOKE.getParticleID(), new ParticleFireplaceSmoke.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.FIREPLACE_FIRE.getParticleID(), new ParticleFireplaceFire.Factory());

        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.RAD_0.getParticleID(), new ParticleField.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.RAD_1.getParticleID(), new ParticleField.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.RAD_2.getParticleID(), new ParticleField.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.RAD_3.getParticleID(), new ParticleField.Factory());

        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.PSY_0.getParticleID(), new ParticleField.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.PSY_1.getParticleID(), new ParticleField.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.PSY_2.getParticleID(), new ParticleField.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.PSY_3.getParticleID(), new ParticleField.Factory());

        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.CHEM_0.getParticleID(), new ParticleField.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.CHEM_1.getParticleID(), new ParticleField.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.CHEM_2.getParticleID(), new ParticleField.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.CHEM_3.getParticleID(), new ParticleField.Factory());

        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.TERM_0.getParticleID(), new ParticleField.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.TERM_1.getParticleID(), new ParticleField.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.TERM_2.getParticleID(), new ParticleField.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.TERM_3.getParticleID(), new ParticleField.Factory());

        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.ARTIFACT.getParticleID(), new ParticleArtifact.Factory());

        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.ELECTRA_FAR.getParticleID(), new ParticleElectraFar.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.ELECTRA_CENTER.getParticleID(), new ParticleElectraCenter.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.ELECTRA_BLAST.getParticleID(), new ParticleElectraBlast.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.ELECTRA_BLAST_CENTER.getParticleID(), new ParticleElectraBlastCenter.Factory());

        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.FUNNEL_DUST.getParticleID(), new ParticleFunnelDust.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.FUNNEL_LEAVES.getParticleID(), new ParticleFunnelLeaves.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.FUNNEL_FLESH.getParticleID(), new ParticleFunnelFlesh.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.FUNNEL_BLAST.getParticleID(), new ParticleFunnelBlast.Factory());

        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.SPRINGBOARD_DUST.getParticleID(), new ParticleSpringboardDust.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.SPRINGBOARD_BLAST.getParticleID(), new ParticleSpringboardBlast.Factory());

        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.CAROUSEL_BLAST.getParticleID(), new ParticleCarouselBlast.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.CAROUSEL_CENTER.getParticleID(), new ParticleCarouselCenter.Factory());

        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.DEEPFRY_BLAST.getParticleID(), new ParticleDeepfryBlast.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.STEAM_BLAST.getParticleID(), new ParticleSteamBlast.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.SODA_BLAST.getParticleID(), new ParticleSodaBlast.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.KISSEL.getParticleID(), new ParticleKissel.Factory());

        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.LAMP_FIRE.getParticleID(), new ParticleLampFire.Factory());

        Minecraft.getMinecraft().effectRenderer.registerParticle(
                StalmineParticles.TELEPORT.getParticleID(), new ParticleTeleport.Factory());
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        StalmineBlocks.initModels();
        StalmineItems.initModels();
        StalmineWeapons.initModels();
        StalmineArmor.initModels();
    }

    @SubscribeEvent
    public static void changeGui(GuiOpenEvent event) {
        if (event.getGui() instanceof GuiMainMenu) {
            event.setGui(new GUIMainMenu());
        }
        if (event.getGui() instanceof GuiInventory) {
            event.setGui(new GUIInventory(Minecraft.getMinecraft().player));
        }
    }

    @SubscribeEvent
    public static void drawPlayerStats(GuiScreenEvent.BackgroundDrawnEvent event) {
        if (event.getGui() instanceof GUIInventory) {
            ((GUIInventory)event.getGui()).drawStats();
        }
    }

    @SubscribeEvent
    public static void changeInGameGui(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;
        new GUIInGame(Minecraft.getMinecraft());
    }

    @SubscribeEvent
    public static void onPrePlayerRender(RenderPlayerEvent.Pre event) {
        event.setCanceled(true);
        if (event.getEntityPlayer() != null) {
            playerRender.doRender((AbstractClientPlayer) event.getEntityPlayer(), event.getX(), event.getY(), event.getZ(), event.getEntity().rotationYaw, event.getPartialRenderTick());
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority= EventPriority.HIGH, receiveCanceled=true)
    public static void onEvent(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player.getHeldItemMainhand().getItem() instanceof Weapon) {
            if (keyBindings[0].isPressed()) {
                StalminePacketHandler.INSTANCE.sendToServer(new PacketReload());
            }
            if (keyBindings[2].isPressed()) {
                StalminePacketHandler.INSTANCE.sendToServer(new PacketChangeSecondaryAmmo());
            }
            if (keyBindings[3].isPressed() &&
                    (
                            mc.player.getHeldItemMainhand().getItem() instanceof WeaponAutoGrenade ||
                                    mc.player.getHeldItemMainhand().getItem() instanceof WeaponSniperGrenadeAutoRifle
                    )
            ) {
                StalminePacketHandler.INSTANCE.sendToServer(new PacketChangeGrenadeAmmo());
            }
        }
        if (keyBindings[1].isPressed()) {
            ItemStack helmet = ((NonNullList<ItemStack>)mc.player.getArmorInventoryList()).get(3);
            if (!helmet.isEmpty()) {
                if (helmet.getItem() instanceof Armor) {
                    ArmorDataModel adm = ((Armor)helmet.getItem()).model;
                    if (adm.getNightVisionDevice() > 0) {
                        StalmineConfig.isNVDActive = !StalmineConfig.isNVDActive;
                        mc.player.playSound(
                                StalmineSounds.pool.get(StalmineConfig.isNVDActive ? "nvd_start" : "nvd_off"),
                                1f,
                                1f
                        );
                    }
                }
            }
        }
        if (keyBindings[4].isPressed()) {
            StalminePacketHandler.INSTANCE.sendToServer(new PacketPickupItem());
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGH, receiveCanceled = true)
    public static void onClientTick(TickEvent.ClientTickEvent e) {
        Minecraft mc = Minecraft.getMinecraft();
        if (!mc.isGamePaused() && mc.world != null) {
            EntityPlayerSP p = mc.player;
            if (p.getHeldItemMainhand().getItem() instanceof Weapon) {
                if (mc.gameSettings.keyBindAttack.isKeyDown()) {
                    StalminePacketHandler.INSTANCE.sendToServer(new PacketShootMainhand());
                }
            }
        }
    }

    @SubscribeEvent
    public static void onZoom(FOVUpdateEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (!mc.isGamePaused() && mc.world != null) {
            EntityPlayer p = mc.player;
            if (p.getHeldItemMainhand().getItem() instanceof Weapon) {
                if (mc.gameSettings.keyBindUseItem.isKeyDown()) {
                    event.setNewfov(1f - ((Weapon) p.getHeldItemMainhand().getItem()).model.getAim() / 10f);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onHandDraw(RenderHandEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (!mc.isGamePaused() && mc.world != null) {
            EntityPlayer p = mc.player;
            if (p.getHeldItemMainhand().getItem() instanceof WeaponSniperRifle
                    || p.getHeldItemMainhand().getItem() instanceof WeaponSniperAutoRifle
                    || p.getHeldItemMainhand().getItem() instanceof WeaponSniperGrenadeAutoRifle
                    || p.getHeldItemMainhand().getItem() instanceof WeaponRocket) {
                if (mc.gameSettings.keyBindUseItem.isKeyDown()) {
                    event.setCanceled(true);
                }
            }
        }
    }
}

