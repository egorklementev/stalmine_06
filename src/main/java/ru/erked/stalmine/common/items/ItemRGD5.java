package ru.erked.stalmine.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import ru.erked.stalmine.common.entities.EntityRGD5;

public class ItemRGD5 extends ItemGrenade {

    public ItemRGD5()
    {
        super("w_rgd5");
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if (!worldIn.isRemote)
        {
            EntityRGD5 ent = new EntityRGD5(worldIn, playerIn);
            ent.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(ent);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
