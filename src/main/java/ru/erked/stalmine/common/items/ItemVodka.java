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

public class ItemVodka extends ItemMedicine {

    public ItemVodka(String name, String sound) {
        super(name, sound);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.addPotionEffect(
                new PotionEffect(
                        StalminePotions.ANTIRAD,
                        20 * 20,
                        0
                )
        );
        playerIn.addPotionEffect(
                new PotionEffect(
                        Potion.getPotionById(9),
                        60 * 20,
                        0
                )
        );
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("item.stalmine.radiation_protection.info") + " 10%, 20 " + I18n.format("general.stalmine.seconds.info"));
        tooltip.add(I18n.format("item.stalmine.nausea.info") + " 60 " + I18n.format("general.stalmine.seconds.info"));
    }
}
