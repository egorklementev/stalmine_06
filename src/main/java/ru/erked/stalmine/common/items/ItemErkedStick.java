package ru.erked.stalmine.common.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.StalmineMod;

public class ItemErkedStick extends Item {

    public ItemErkedStick() {
        setMaxStackSize(1);
        setUnlocalizedName(StalmineMod.MODID + ".erked_stick");
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
