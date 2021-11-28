package ru.erked.stalmine.client.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.erked.stalmine.common.armor.StalmineArmor;
import ru.erked.stalmine.common.blocks.StalmineBlocks;

public class StalmineTabArmor extends CreativeTabs {

    public StalmineTabArmor(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(StalmineArmor.jacketChest);
    }
}
