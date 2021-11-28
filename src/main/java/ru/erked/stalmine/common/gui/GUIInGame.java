package ru.erked.stalmine.common.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.GuiIngameForge;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.StalmineConfig;
import ru.erked.stalmine.common.armor.Armor;
import ru.erked.stalmine.common.armor.ArmorDataModel;
import ru.erked.stalmine.common.effects.StalminePotions;
import ru.erked.stalmine.common.items.ItemAmmo;
import ru.erked.stalmine.common.items.ItemArtifact;
import ru.erked.stalmine.common.items.StalmineItems;
import ru.erked.stalmine.common.weapons.*;
import ru.erked.stalmine.proxy.ClientProxy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GUIInGame extends GuiIngameForge {

    public static float teleAlpha = 0f;

    private static final ResourceLocation fieldOverlay = new ResourceLocation(StalmineMod.MODID, "textures/gui/field.png");
    private static final ResourceLocation hudBack = new ResourceLocation(StalmineMod.MODID, "textures/gui/hud_back.png");
    private static final ResourceLocation hudHealth = new ResourceLocation(StalmineMod.MODID, "textures/gui/hud_health.png");
    private static final ResourceLocation hudArmor = new ResourceLocation(StalmineMod.MODID, "textures/gui/hud_armor.png");
    private static final ResourceLocation hudIcons = new ResourceLocation(StalmineMod.MODID, "textures/gui/hud_icons.png");
    private static final ResourceLocation artIcons = new ResourceLocation(StalmineMod.MODID, "textures/gui/art_icons.png");
    private static final ResourceLocation hudCrosshair = new ResourceLocation(StalmineMod.MODID, "textures/gui/wpn_crosshair.png");
    private static final ResourceLocation hudCrosshairGauss = new ResourceLocation(StalmineMod.MODID, "textures/gui/wpn_crosshair_gauss.png");
    private static final ResourceLocation hudCrosshairG36 = new ResourceLocation(StalmineMod.MODID, "textures/gui/wpn_crosshair_g36.png");
    private static final ResourceLocation hudCrosshairRPG7 = new ResourceLocation(StalmineMod.MODID, "textures/gui/wpn_crosshair_rpg.png");

    private static float timer = 0f;
    private static float itemDescAlpha = 0f;
    private static final Potion nvdPotion = Potion.getPotionById(16);
    private static final ResourceLocation[] nvdTextures =
            new ResourceLocation[] {
                    new ResourceLocation(StalmineMod.MODID, "textures/gui/radiation_0.png"),
                    new ResourceLocation(StalmineMod.MODID, "textures/gui/radiation_1.png"),
                    new ResourceLocation(StalmineMod.MODID, "textures/gui/radiation_2.png"),
                    new ResourceLocation(StalmineMod.MODID, "textures/gui/radiation_3.png"),
                    new ResourceLocation(StalmineMod.MODID, "textures/gui/radiation_4.png"),
                    new ResourceLocation(StalmineMod.MODID, "textures/gui/radiation_5.png"),
                    new ResourceLocation(StalmineMod.MODID, "textures/gui/radiation_6.png"),
                    new ResourceLocation(StalmineMod.MODID, "textures/gui/radiation_7.png"),
                    new ResourceLocation(StalmineMod.MODID, "textures/gui/radiation_8.png"),
                    new ResourceLocation(StalmineMod.MODID, "textures/gui/radiation_9.png")
            } ;
    private static final ArrayList<int[]> indices = new ArrayList<int[]>(){{
        add(new int[] { 1, 3 } );
        add(new int[] { 1, 1 } );
        add(new int[] { 1, 4 } );
        add(new int[] { 1, 2 } );
        add(new int[] { 1, 11 } );
        add(new int[] { 0, 11 } );
        add(new int[] { 2, 11 } );
        add(new int[] { 3, 11 } );
    }};
    private static final ArrayList<Integer> icons = new ArrayList<>();
    private static final ArrayList<Potion> potions = new ArrayList<>();
    private static final ArrayList<Boolean> offsets = new ArrayList<>();
    private static final ArrayList<Integer> arts = new ArrayList<>();

    public GUIInGame(Minecraft mc) {
        super(mc);

        renderHotbar = false;
        renderHealth = false;
        renderArmor = false;
        renderCrosshairs = false;
        renderHealthMount = false;
        renderFood = false;
        renderExperiance = false;

        ScaledResolution scaled = new ScaledResolution(mc);
        int w = scaled.getScaledWidth();
        int h = scaled.getScaledHeight();


        // Night vision device
        boolean isNvdDrawing = false;
        ItemStack helmetArm = mc.player.inventory.armorInventory.get(3);
        if (!helmetArm.isEmpty() && helmetArm.getItem() instanceof Armor) {
            if (StalmineConfig.isNVDActive) {
                ArmorDataModel adm = ((Armor)helmetArm.getItem()).model;
                if (adm.getNightVisionDevice() > 0) {
                    isNvdDrawing = true;
                    GlStateManager.pushMatrix();
                    GlStateManager.enableAlpha();
                    GlStateManager.enableBlend();
                    GlStateManager.color(
                            .7f,
                            1f - .3f * (adm.getNightVisionDevice() - 1),
                            .7f + .3f * (adm.getNightVisionDevice() - 1),
                            .5f - .19f * adm.getNightVisionDevice());
                    mc.renderEngine.bindTexture(nvdTextures[(int)timer]);
                    drawModalRectWithCustomSizedTexture( 0, 0, 0, 0, w, h, w, h );
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    if (!mc.player.isPotionActive(nvdPotion) ||
                            mc.player.getActivePotionEffect(nvdPotion).getDuration() < 210) {
                        mc.player.addPotionEffect(new PotionEffect(
                                nvdPotion,
                                300
                        ));
                    }
                } else {
                    if (mc.player.isPotionActive(nvdPotion)) {
                        mc.player.removePotionEffect(nvdPotion);
                    }
                }
            } else {
                if (mc.player.isPotionActive(nvdPotion)) {
                    mc.player.removePotionEffect(nvdPotion);
                }
            }
        }
        timer += Minecraft.getMinecraft().getRenderPartialTicks();
        if (timer >= 10f)
            timer = 0f;

        int hudSize = (int)(222 / 2.25f);
        int hudLeftMargin = (int)(27 / 2.25f);
        int hudRightMargin = (int)(57 / 2.25f);
        int yOffset = (int)(47 / 2.25f);
        mc.renderEngine.bindTexture(hudBack);
        drawModalRectWithCustomSizedTexture(
                w - hudSize - 5, h - hudSize + yOffset - 5,
                0f, 0f, hudSize, hudSize, hudSize, hudSize
        );
        mc.renderEngine.bindTexture(hudHealth);
        drawModalRectWithCustomSizedTexture(
                w - hudSize - 5,
                h - hudSize + yOffset - 5,
                0f, 0f,
                hudLeftMargin +
                        (int) ((float)(hudSize - hudLeftMargin - hudRightMargin) *
                                (mc.player.getHealth() / mc.player.getMaxHealth())),
                hudSize, hudSize, hudSize
        );
        mc.renderEngine.bindTexture(hudArmor);
        drawModalRectWithCustomSizedTexture(
                w - hudSize - 5,
                h - hudSize + yOffset - 5,
                0f, 0f,
                hudLeftMargin +
                        (int) ((float)(hudSize - hudLeftMargin - hudRightMargin) *
                                (1f * mc.player.getTotalArmorValue() / StalmineConfig.MAX_ARMOR)),
                hudSize, hudSize, hudSize
        );

        // Food
        int food = mc.player.getFoodStats().getFoodLevel();
        if (food < 4) {
            icons.add(4);
            icons.add(0);
        } else if (food < 8) {
            icons.add(3);
            icons.add(0);
        } else if (food < 12) {
            icons.add(2);
            icons.add(0);
        } else if (food < 16) {
            icons.add(1);
            icons.add(0);
        }

        // Potions
        potions.add(StalminePotions.RADIATION);
        potions.add(StalminePotions.PSY);
        potions.add(StalminePotions.CHEM);
        potions.add(StalminePotions.TERM);
        potions.add(StalminePotions.ANTIRAD);
        potions.add(StalminePotions.ANTIPSY);
        potions.add(StalminePotions.ANTICHEM);
        potions.add(StalminePotions.ANTITERM);


        for (int i = 0; i < 4; i++) offsets.add(true);
        for (int i = 0; i < 4; i++) offsets.add(false);

        for (int i = 0; i < potions.size(); i++) {
            if (mc.player.isPotionActive(potions.get(i))) {
                PotionEffect ef = mc.player.getActivePotionEffect(potions.get(i));
                if (ef.getDuration() <= 10) {
                    mc.player.removePotionEffect(ef.getPotion());
                } else {
                    icons.add(indices.get(i)[0] + (offsets.get(i) ? ef.getAmplifier() : 0));
                    icons.add(indices.get(i)[1]);
                }
            }
        }
        potions.clear();
        offsets.clear();

        // Armor
        for (int i = 0; i < 4; i++) {
            if (!mc.player.inventory.armorInventory.get(i).isEmpty()) {
                ItemStack armor = mc.player.inventory.armorInventory.get(i);
                int damage = (int) (100f * armor.getItemDamage() / armor.getMaxDamage());
                if (damage >= 20) {
                    damage -= 20;
                    icons.add((i > 1 ? 1 : 5) + (damage / 20));
                    icons.add(9 - (i % 2));
                }
            }
        }
        timer += Minecraft.getMinecraft().getRenderPartialTicks();
        if (timer >= 10f)
            timer = 0f;


        // Weapons
        boolean isAiming = false;
        if (mc.player.getHeldItemMainhand().getItem() instanceof Weapon) {
            ItemStack weapon = mc.player.getHeldItemMainhand();
            int damage = (int) (100f * weapon.getItemDamage() / weapon.getMaxDamage());
            if (damage >= 20) {
                damage -= 20;
                icons.add(1 + (damage / 20));
                icons.add(7);
            }
            if (mc.player.getHeldItemMainhand().getItem() instanceof WeaponSniperRifle
                    || mc.player.getHeldItemMainhand().getItem() instanceof WeaponSniperAutoRifle
                    || mc.player.getHeldItemMainhand().getItem() instanceof WeaponSniperGrenadeAutoRifle
                    || mc.player.getHeldItemMainhand().getItem() instanceof WeaponRocket) {
                if (mc.gameSettings.keyBindUseItem.isKeyDown()) {
                    // Crosshair NVD (0 - none, 1 - bad, 2 - good)
                    if (!isNvdDrawing) {
                        int wpnNvdType = ((Weapon) mc.player.getHeldItemMainhand().getItem()).model.getCrosshairNvd();
                        if (wpnNvdType > 0) {
                            GlStateManager.pushMatrix();
                            GlStateManager.enableAlpha();
                            GlStateManager.enableBlend();
                            GlStateManager.color(
                                    .7f,
                                    1f - .3f * (wpnNvdType - 1),
                                    .7f + .3f * (wpnNvdType - 1),
                                    .5f - .19f * wpnNvdType);
                            mc.renderEngine.bindTexture(nvdTextures[(int)timer]);
                            drawModalRectWithCustomSizedTexture( 0, 0, 0, 0, w, h, w, h );
                            GlStateManager.disableBlend();
                            GlStateManager.popMatrix();
                            if (!mc.player.isPotionActive(nvdPotion) ||
                                    mc.player.getActivePotionEffect(nvdPotion).getDuration() < 210) {
                                mc.player.addPotionEffect(new PotionEffect(
                                        nvdPotion,
                                        300
                                ));
                            }
                        } else {
                            if (mc.player.isPotionActive(nvdPotion)) {
                                mc.player.removePotionEffect(nvdPotion);
                            }
                        }
                    }

                    isAiming = true;
                    GlStateManager.pushMatrix();
                    GlStateManager.color(1f, 1f, 1f, 1f);
                    GlStateManager.enableBlend();
                    switch (((Weapon) mc.player.getHeldItemMainhand().getItem()).model.getCrosshair()) {
                        case 1: {
                            mc.renderEngine.bindTexture(hudCrosshairGauss);
                            break;
                        }
                        case 2: {
                            mc.renderEngine.bindTexture(hudCrosshairG36);
                            break;
                        }
                        case 3: {
                            mc.renderEngine.bindTexture(hudCrosshairRPG7);
                            break;
                        }
                        default: {
                            mc.renderEngine.bindTexture(hudCrosshair);
                            break;
                        }
                    }
                    drawModalRectWithCustomSizedTexture( 0, 0, 0, 0, w, h, w, h );
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                } else {
                    if (!isNvdDrawing) {
                        if (mc.player.isPotionActive(nvdPotion)) {
                            mc.player.removePotionEffect(nvdPotion);
                        }
                    }
                }
            }
        }


        if (!isAiming) {
            // Artifacts
            mc.renderEngine.bindTexture(artIcons);
            for (int i = 0; i < 9; i++) {
                ItemStack is = mc.player.inventory.getStackInSlot(i);
                if (!is.isEmpty() && is.getItem() instanceof ItemArtifact) {
                    int index = StalmineItems.art_names.indexOf(((ItemArtifact) is.getItem()).name);
                    arts.add(index % 8);
                    arts.add(index / 8);

                }
            }
            int artX = 8;
            for (int i = 0; i < arts.size(); i += 2) {
                drawModalRectWithCustomSizedTexture(
                        artX + i * 8,
                        h - 16 - 10,
                        16 * arts.get(i),
                        16 * arts.get(i + 1),
                        16,
                        16,
                        512 / 4f,
                        512 / 4f
                );
            }
            arts.clear();

            int textureSize = 1024;
            int iconSize = 84;
            int iconScale = 6;
            textureSize = textureSize / iconScale;
            iconSize = iconSize / iconScale;
            int iconX = 8;
            int iconY = h / 2 + 35;
            int padding = 1;
            int mxHeight = 4 * 2;

            mc.renderEngine.bindTexture(hudIcons);
            for (int i = 0; i < icons.size(); i += 2) {
                drawModalRectWithCustomSizedTexture(
                        w - iconX - (i / mxHeight) * (iconSize + padding) - iconSize,
                        iconY - ((i % mxHeight) / 2) * (iconSize + padding),
                        iconSize * icons.get(i),
                        iconSize * icons.get(i + 1),
                        iconSize,
                        iconSize,
                        textureSize,
                        textureSize
                );
            }

            int crossTex = 512 / 2;
            int crossSize = 42 / 2;
            int crossX = 5;
            int crossY;
            if (mc.player.isSprinting() || !mc.player.onGround) {
                crossY = 7;
            } else if (mc.player.isSneaking()) {
                crossY = 5;
            } else {
                crossY = 6;
            }
            drawModalRectWithCustomSizedTexture(
                    w / 2 - crossSize / 2,
                    h / 2 - crossSize / 2,
                    crossSize * crossX,
                    crossSize * crossY,
                    crossSize,
                    crossSize,
                    crossTex,
                    crossTex
            );

            ItemStack stack = mc.player.getHeldItemMainhand();
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof Weapon) {
                    NBTTagCompound nbt;
                    if (stack.hasTagCompound())
                        nbt = stack.getTagCompound();
                    else
                        nbt = new NBTTagCompound();

                    if (!nbt.hasKey("clip"))
                        nbt.setInteger("clip", ((Weapon) stack.getItem()).model.getMaxClipSize());
                    stack.setTagCompound(nbt);

                    float scale = 1f;
                    int xPos = (int) ((w - 90) / scale);
                    int yPos = (int) ((h - 36) / scale);
                    GlStateManager.pushMatrix();
                    RenderHelper.enableGUIStandardItemLighting();
                    GlStateManager.scale(scale, scale, scale);
                    ItemStack ammo = new ItemStack(Item.getByNameOrId(StalmineMod.MODID + ":" +
                                                    (stack.getTagCompound().getInteger("ammo_type") == 0 ?
                                                            ((Weapon) stack.getItem()).model.getAmmo() :
                                                                    stack.getTagCompound().getInteger("ammo_type") == 1 ?
                                                                            ((Weapon) stack.getItem()).model.getSecondaryAmmo() :
                                                                            ((Weapon) stack.getItem()).model.getGrenadeAmmo())));
                    itemRenderer.renderItemAndEffectIntoGUI(
                            mc.player,
                            ammo,
                            xPos,
                            yPos
                    );
                    itemRenderer.renderItemOverlays(mc.fontRenderer, ammo, xPos, yPos);
                    GlStateManager.popMatrix();

                    if (stack.hasTagCompound() && stack.getTagCompound().hasKey("ammo_type")) {
                        GlStateManager.pushMatrix();
                        GlStateManager.scale(.7f, .7f, .7f);
                        GlStateManager.translate(.355f * w, .39f * h, 0);
                        if (stack.getTagCompound().getInteger("ammo_type") == 2) {
                            String label = stack.getTagCompound().getInteger("grenade_clip") + "/1";
                            drawCenteredString(
                                    mc.fontRenderer,
                                    label,
                                    w - 82,
                                    h - 19,
                                    Integer.parseInt("ffffff", 16)
                            );
                            drawCenteredString(
                                    mc.fontRenderer,
                                    "" + getAllAmmoCount(mc.player, ((Weapon)stack.getItem()), 2),
                                    w - 82,
                                    h - 10,
                                    Integer.parseInt("aaaaaa", 16)
                            );
                        } else {
                            String label = stack.getTagCompound().getInteger("clip") + "/" +
                                    ((Weapon) stack.getItem()).model.getMaxClipSize();
                            drawCenteredString(
                                    mc.fontRenderer,
                                    label,
                                    w - 82,
                                    h - 19,
                                    Integer.parseInt("ffffff", 16)
                            );

                            drawCenteredString(
                                    mc.fontRenderer,
                                    "" + getAllAmmoCount(
                                            mc.player,
                                            ((Weapon)stack.getItem()),
                                            stack.getTagCompound().getInteger("ammo_type")
                                    ),
                                    w - 82,
                                    h - 10,
                                    Integer.parseInt("aaaaaa", 16)
                            );
                            //String frTimer = String.valueOf(stack.getTagCompound().getFloat("fr_timer"));
                            //drawCenteredString( mc.fontRenderer, frTimer, (int) (w * .4f), h - 15, Integer.parseInt("ffffff", 16) );
                        }
                        GlStateManager.popMatrix();
                    }
                } else if (stack.getItem() instanceof ItemAmmo) {
                    String label = (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage();
                    drawCenteredString(
                            mc.fontRenderer,
                            label,
                            w - 82,
                            h - 25,
                            Integer.parseInt("ffffff", 16)
                    );
                }

                float scale = 1.5f;
                int xPos = (int) ((w - 58) / scale);
                int yPos = (int) ((h - 37) / scale);
                GlStateManager.pushMatrix();
                RenderHelper.enableGUIStandardItemLighting();
                GlStateManager.scale(scale, scale, scale);
                itemRenderer.renderItemAndEffectIntoGUI(mc.player, stack, xPos, yPos);
                itemRenderer.renderItemOverlays(mc.fontRenderer, stack, xPos, yPos);
                GlStateManager.popMatrix();

            }

            // Dropped items GUI
            List<Entity> closestItems = mc.world.loadedEntityList.stream()
                    .filter(ent -> ent instanceof EntityItem)
                    .filter(ent -> distanceToPlayer(ent, mc.player) < 1.75)
                    .sorted(Comparator.comparingDouble(o -> distanceToPlayer(o, mc.player)))
                    .collect(Collectors.toList());
            if (!closestItems.isEmpty()) {
                stack = ((EntityItem)closestItems.get(0)).getItem();
                float scale = 1.5f;
                int xPos = (int) ((w / 2f - 10) / scale);
                int yPos = (int) ((h / 2f + 15) / scale);
                GlStateManager.pushMatrix();
                RenderHelper.enableGUIStandardItemLighting();
                GlStateManager.scale(scale, scale, scale);
                itemRenderer.renderItemAndEffectIntoGUI(mc.player, stack, xPos, yPos);
                itemRenderer.renderItemOverlays(mc.fontRenderer, stack, xPos, yPos);
                GlStateManager.popMatrix();
                drawCenteredString(mc.fontRenderer,
                        ClientProxy.keyBindings[4].getDisplayName(),
                        (int) (w * .5f),
                        (int) (h * .5f + 42),
                        Integer.parseInt("ffffff", 16));

                int k = itemDescAlpha < 0f ? -1 : (int)(itemDescAlpha * 255f);
                if (k > 0) {
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    drawCenteredString(mc.fontRenderer,
                            stack.getDisplayName(),
                            (int) (w * .5f),
                            (int) (h * .5f + 57),
                            16777215 + (k << 24));
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                }
                itemDescAlpha = Math.min(itemDescAlpha + mc.getRenderPartialTicks() / 75f, 1f);
            } else {
                itemDescAlpha = -4f;
            }

            drawCenteredString(
                    mc.fontRenderer,
                    (mc.player.inventory.currentItem + 1) + "",
                    w - 22,
                    h - 22,
                    Integer.parseInt("ffffff", 16)
            );

            GlStateManager.pushMatrix();
            float scale = .75f;
            GlStateManager.scale(scale, scale, scale);
            drawCenteredString(
                    mc.fontRenderer,
                    (mc.player.experienceLevel) + "",
                    (int)((w - 22) / scale) + 1,
                    (int)((h - 35) / scale) - 1,
                    Integer.parseInt("aaaaaa", 16)
            );
            GlStateManager.color(1f, 1f, 1f, 1f);
            GlStateManager.popMatrix();

            // Teleport flash
            if (teleAlpha > 0f) {
                GlStateManager.pushMatrix();
                mc.renderEngine.bindTexture(fieldOverlay);
                GlStateManager.scale((float) w / h, 1f, 1f);
                GlStateManager.color(1f, 1f, 1f, teleAlpha / StalmineConfig.teleTime);
                drawModalRectWithCustomSizedTexture( 0, 0, 0, 0, w, h, w, h );
                GlStateManager.popMatrix();
                teleAlpha -= mc.getRenderPartialTicks();
            }
        }
        icons.clear();
    }

    private double distanceToPlayer(Entity e, EntityPlayerSP p) {
        double radius = 0.3;
        float yaw = -p.rotationYawHead;
        float pitch = p.rotationPitch + 90;
        double yawRadians = (yaw / 180.0) * Math.PI;
        double pitchRadians = (pitch / 180.0) * Math.PI;
        BlockPos pPos = p.getPosition();
        double xAdd = radius * Math.sin(yawRadians) * Math.sin(pitchRadians);
        double yAdd = radius * Math.cos(pitchRadians) + p.getEyeHeight();
        double zAdd = radius * Math.cos(yawRadians) * Math.sin(pitchRadians);
        pPos = pPos.add(xAdd, yAdd, zAdd);
        return Math.sqrt(pPos.distanceSq(e.getPosition()));
    }

    private int getAllAmmoCount(EntityPlayer p, Weapon wpn, int ammoType) {
        int count = 0;
        for (ItemStack is : p.inventory.mainInventory) {
            count += getAmmoCount(is, wpn, ammoType);
        }
        for (ItemStack is : p.inventory.offHandInventory) {
            count += getAmmoCount(is, wpn, ammoType);
        }
        return count;
    }

    private int getAmmoCount(ItemStack is, Weapon wpn, int ammoType) {
        if (is.getItem() instanceof ItemAmmo && wpn.isAmmo(is, ammoType)) {
            return is.getCount() * (is.getMaxDamage() - is.getItemDamage());
        } else {
            return 0;
        }
    }

    @Override
    protected void renderExperience(int width, int height) {

    }

    @Override
    protected void renderPotionEffects(ScaledResolution resolution) {
    }

    @Override
    protected void renderPotionIcons(ScaledResolution resolution) {
    }
}
