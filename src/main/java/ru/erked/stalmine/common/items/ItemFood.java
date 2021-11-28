package ru.erked.stalmine.common.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.sound.StalmineSounds;

public class ItemFood extends Item {

    private final int foodLevel;
    private final float foodSatMod;
    private final String sound;

    public ItemFood(String name, String sound, int foodLevel, float foodSatMod) {
        this.sound = sound;
        this.foodLevel = foodLevel;
        this.foodSatMod = foodSatMod;
        setRegistryName(name);
        setUnlocalizedName(StalmineMod.MODID + "." + name);
        setCreativeTab(StalmineMod.tabItems);
        setMaxStackSize(16);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(
                this,
                0,
                new ModelResourceLocation(getRegistryName(), "inventory")
        );
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack is = playerIn.getHeldItem(handIn);
        if (!playerIn.isCreative()) {
            is.shrink(1);
        }
        playerIn.playSound(
                StalmineSounds.pool.get(sound),
                1f,
                1f - worldIn.rand.nextFloat() * .2f
        );
        playerIn.getFoodStats().addStats(foodLevel, foodSatMod);
        playerIn.getCooldownTracker().setCooldown(this, 100);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
