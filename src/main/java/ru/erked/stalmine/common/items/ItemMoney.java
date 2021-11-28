package ru.erked.stalmine.common.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.StalmineMod;

public class ItemMoney extends Item {

    public ItemMoney(String name, int type) {
        setRegistryName(name);
        setUnlocalizedName(StalmineMod.MODID + ".money_" + type);
        setCreativeTab(StalmineMod.tabItems);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(
                this,
                0,
                new ModelResourceLocation(getRegistryName(), "inventory")
        );
    }

}
