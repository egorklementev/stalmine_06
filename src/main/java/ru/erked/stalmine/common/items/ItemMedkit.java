package ru.erked.stalmine.common.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import ru.erked.stalmine.common.effects.StalminePotions;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMedkit extends ItemMedicine {

    private final int type;

    public ItemMedkit(String name, String sound, int type) {
        super(name, sound);
        this.type = type;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.addPotionEffect(
                new PotionEffect(
                        Potion.getPotionById(6),
                        1,
                        type / 2
                )
        );
        playerIn.addPotionEffect(
                new PotionEffect(
                        Potion.getPotionById(10),
                        100 * (type - 1),
                        (type - 1) / 2
                )
        );
        playerIn.addPotionEffect(
                new PotionEffect(
                        StalminePotions.ANTIRAD,
                        400 * ((type - 1) / 2),
                        type - 1
                )
        );
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("item.stalmine.healing.info") + " " + ((type / 2 + 1) * 10)  + "%");
        if (type > 1) {
            tooltip.add(I18n.format("item.stalmine.regeneration.info") + " " + (5 * (type - 1)) + " " + I18n.format("general.stalmine.seconds.info"));
            if (type > 2) {
                tooltip.add(I18n.format("item.stalmine.radiation_protection.info") + " " + (type * 10f) + "%, " + (20 * ((type - 1) / 2)) + " " + I18n.format("general.stalmine.seconds.info"));
            }
        }
    }
}
