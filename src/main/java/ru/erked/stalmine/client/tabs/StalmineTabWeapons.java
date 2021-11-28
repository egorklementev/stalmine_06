package ru.erked.stalmine.client.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import ru.erked.stalmine.common.items.StalmineItems;
import ru.erked.stalmine.common.weapons.StalmineWeapons;

public class StalmineTabWeapons extends CreativeTabs {

    public StalmineTabWeapons(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(StalmineWeapons.psm);
    }
}
