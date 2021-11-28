package ru.erked.stalmine.client.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.erked.stalmine.common.blocks.StalmineBlocks;

public class StalmineTabBlocks extends CreativeTabs {

    public StalmineTabBlocks(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Item.getItemFromBlock(StalmineBlocks.blockFireplace));
    }
}
