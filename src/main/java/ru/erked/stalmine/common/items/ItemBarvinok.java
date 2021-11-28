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

public class ItemBarvinok extends ItemMedicine {

    public ItemBarvinok(String name, String sound) {
        super(name, sound);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.addPotionEffect(
                new PotionEffect(
                        Potion.getPotionById(10),
                        400
                )
        );
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("item.stalmine.regeneration.info") + " 20 " + I18n.format("general.stalmine.seconds.info"));
    }
}
