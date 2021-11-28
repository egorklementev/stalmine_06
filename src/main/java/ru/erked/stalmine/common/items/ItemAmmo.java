package ru.erked.stalmine.common.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.common.weapons.WeaponDataModel;

import javax.annotation.Nullable;
import java.util.List;

public class ItemAmmo extends Item {

    public String name;
    public float dmgModifier;

    public ItemAmmo(String name, int size, float dmgModifier) {
        this.name = name;
        this.dmgModifier = dmgModifier;
        setRegistryName(name);
        setUnlocalizedName(StalmineMod.MODID + "." + name);
        setMaxStackSize(1);
        setMaxDamage(size);
        setCreativeTab(StalmineMod.tabWeapons);
        //setNoRepair();
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(
                this,
                0,
                new ModelResourceLocation(getRegistryName(), "inventory")
        );
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if (stack != null) {
            tooltip.add(I18n.format("weapon.stalmine.ammo.info") + " " + (stack.getMaxDamage() - stack.getItemDamage()) +
                    "/" + stack.getMaxDamage());
        }
        if (dmgModifier > 1f) {
            tooltip.add("+" + ((dmgModifier - 1f) * 100f) + "% " + I18n.format("weapon.stalmine.bp_ammo.info"));
        }
    }

}
