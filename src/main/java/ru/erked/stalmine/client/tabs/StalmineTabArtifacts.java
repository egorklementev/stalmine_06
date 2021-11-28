package ru.erked.stalmine.client.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import ru.erked.stalmine.common.armor.StalmineArmor;
import ru.erked.stalmine.common.items.StalmineItems;

public class StalmineTabArtifacts extends CreativeTabs {

    public StalmineTabArtifacts(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(StalmineItems.itemMedusa);
    }
}
