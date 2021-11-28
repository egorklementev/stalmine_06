package ru.erked.stalmine.common.weapons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.StalmineConfig;
import ru.erked.stalmine.common.items.ItemAmmo;

import javax.annotation.Nullable;
import java.util.List;

public class Weapon extends Item {

    public WeaponDataModel model;

    public Weapon(String name) {
        model = StalmineConfig.w_models.get(name);
        setMaxDamage(model.getMaxDurability());
        setMaxStackSize(1);
        setCreativeTab(StalmineMod.tabWeapons);
        setFull3D();
        setNoRepair();
        setRegistryName(name);
        setUnlocalizedName(StalmineMod.MODID + "." + name);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(
                this,
                0,
                new ModelResourceLocation(getRegistryName(), "inventory")
        );
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.onCreated(stack, worldIn, playerIn);
        stack.setTagCompound(resetNBT());
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!worldIn.isRemote) {
            if (!stack.hasTagCompound()) {
                stack.setTagCompound(resetNBT());
            } else {
                float fr_timer = stack.getTagCompound().getFloat("fr_timer");
                if (fr_timer > 0f) {
                    stack.getTagCompound().setFloat("fr_timer", fr_timer -
                            (1f / (1f + Minecraft.getMinecraft().getTickLength())));
                }
                if (!Minecraft.getMinecraft().gameSettings.keyBindAttack.isKeyDown()) {
                    stack.getTagCompound().setBoolean("has_shot", false);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(I18n.format("weapon.stalmine.damage.info") + " " + model.getDamage());
        tooltip.add(I18n.format("weapon.stalmine.fire_rate.info") + " " + model.getFireRate());
        tooltip.add(I18n.format("weapon.stalmine.accuracy.info") + " " + model.getAccuracy());
        tooltip.add(I18n.format("weapon.stalmine.aim.info") + " " + model.getAim());
        tooltip.add(I18n.format("weapon.stalmine.bullet_vel.info") + " " + model.getBulletVelocity());
        tooltip.add(I18n.format("weapon.stalmine.recoil.info") + " " + model.getRecoil());
        if (model.getType() == WeaponDataModel.WType.SHOTGUN) {
            tooltip.add(I18n.format("weapon.stalmine.reload.info") + " " + model.getReloadTimeSG() *
                    model.getMaxClipSize());
        }
        else {
            tooltip.add(I18n.format("weapon.stalmine.reload.info") + " " + model.getReloadTime());
        }
        TextFormatting tf1 = TextFormatting.DARK_GRAY;
        TextFormatting tf2 = TextFormatting.DARK_GRAY;
        TextFormatting tf3 = TextFormatting.DARK_GRAY;
        if (stack != null && stack.hasTagCompound()) {
            switch (stack.getTagCompound().getInteger("ammo_type")) {
                case 0: {
                    tf1 = TextFormatting.WHITE;
                    break;
                }
                case 1: {
                    tf2 = TextFormatting.WHITE;
                    break;
                }
                case 2: {
                    tf3 = TextFormatting.WHITE;
                    break;
                }
                default: {
                    break;
                }
            }
        }

        tooltip.add("\n");
        tooltip.add(tf1 + Item.getByNameOrId(StalmineMod.MODID + ":" +
                model.getAmmo()).getDefaultInstance().getDisplayName());
        if (!model.getSecondaryAmmo().equals(model.getAmmo())) {
            tooltip.add(tf2 + Item.getByNameOrId(StalmineMod.MODID + ":" +
                    model.getSecondaryAmmo()).getDefaultInstance().getDisplayName());
        }
        if (model.getType() == WeaponDataModel.WType.AUTO_RIFLE_GRENADE ||
                model.getType() == WeaponDataModel.WType.SNIPER_GRENADE_AUTO_RIFLE) {
            tooltip.add(tf3 + Item.getByNameOrId(StalmineMod.MODID + ":" +
                    model.getGrenadeAmmo()).getDefaultInstance().getDisplayName());
        }
        tooltip.add("\n");
        if (stack != null) {
            if (stack.getTagCompound() != null) {
                tooltip.add(I18n.format("weapon.stalmine.clip_size.info") + " " +
                        stack.getTagCompound().getInteger("clip") + "/" + model.getMaxClipSize());
            }
            tooltip.add(I18n.format("weapon.stalmine.durability.info") + " " +
                    (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage());
        }
    }

    private NBTTagCompound resetNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setFloat("fr_timer", 0f);
        nbt.setBoolean("has_shot", false);
        nbt.setInteger("clip", model.getMaxClipSize());
        nbt.setInteger("grenade_clip", 0);
        nbt.setInteger("ammo_type", 0); // 0 - regular ammo, 1 - higher penetration, 2 - grenades
        nbt.setInteger("prev_ammo_type", 0);
        return nbt;
    }

    public ItemStack findAmmo(EntityPlayer player, int ammoType)
    {
        if (this.isAmmo(player.getHeldItem(EnumHand.OFF_HAND), ammoType))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (this.isAmmo(player.getHeldItem(EnumHand.MAIN_HAND), ammoType))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.isAmmo(itemstack, ammoType))
                {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }

    @Override
    public boolean isFull3D() {
       return true;
    }

    public boolean isAmmo(ItemStack stack, int ammoType) {
        return stack.getItem() instanceof ItemAmmo &&
                stack.getItemDamage() < stack.getMaxDamage() &&
                ((ItemAmmo)stack.getItem()).name.equals(
                        ammoType == 0 ?
                                model.getAmmo() : ammoType == 1 ?
                                model.getSecondaryAmmo() : model.getGrenadeAmmo()
                );
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        return true;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return EnumActionResult.SUCCESS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        return new ActionResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.GHAST_TEAR;
    }
}

