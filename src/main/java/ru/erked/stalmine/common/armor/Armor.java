package ru.erked.stalmine.common.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.StalmineConfig;
import ru.erked.stalmine.client.models.StalmineModels;

import javax.annotation.Nullable;
import java.util.List;

public class Armor extends ItemArmor {

    public int id;
    public String name;
    public ArmorDataModel model;

    public Armor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, int id) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        model = StalmineConfig.arm_models.get(name);
        this.setRegistryName(name);
        this.setNoRepair();
        this.setUnlocalizedName(StalmineMod.MODID + "." + name);
        this.setCreativeTab(StalmineMod.tabArmor);
        this.name = name;
        this.id = id;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(
                this,
                0,
                new ModelResourceLocation(getRegistryName(), "inventory")
        );
    }

    @Nullable
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        return StalmineModels.pool.get(name);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if (model.getAntirad() > 0f)
            tooltip.add(I18n.format("armor.stalmine.antirad.info") + " " + (model.getAntirad() * 100f) + "%");
        if (model.getAntipsy() > 0f)
            tooltip.add(I18n.format("armor.stalmine.antipsy.info") + " " + (model.getAntipsy() * 100f) + "%");
        if (model.getAntichem() > 0f)
            tooltip.add(I18n.format("armor.stalmine.antichem.info") + " " + (model.getAntichem() * 100f) + "%");
        if (model.getAntiterm() > 0f)
            tooltip.add(I18n.format("armor.stalmine.antiterm.info") + " " + (model.getAntiterm() * 100f) + "%");
        if (model.getAntielectra() > 0f)
            tooltip.add(I18n.format("armor.stalmine.antielectra.info") + " " + (model.getAntielectra() * 100f) + "%");
        if (model.getNightVisionDevice() > 0)
            tooltip.add(I18n.format("armor.stalmine.nvd.info", model.getNightVisionDevice()));
        if (stack != null) {
            tooltip.add(I18n.format("weapon.stalmine.durability.info") + " " +
                    (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage());
        }
    }
}
