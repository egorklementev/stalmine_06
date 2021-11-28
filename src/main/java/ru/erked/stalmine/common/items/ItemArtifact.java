package ru.erked.stalmine.common.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.client.StalmineConfig;
import ru.erked.stalmine.client.particle.StalmineParticles;
import ru.erked.stalmine.common.effects.StalminePotions;

import javax.annotation.Nullable;
import java.util.List;

public class ItemArtifact extends Item {

    private final int rarity;
    public String name;
    public ArtifactDataModel model;

    public ItemArtifact(String name, int rarity) {
        setRegistryName(name);
        this.name = name;
        this.rarity = rarity;
        model = StalmineConfig.art_models.get(name);
        setUnlocalizedName(StalmineMod.MODID + "." + name);
        setMaxStackSize(1);
        setFull3D();
        setNoRepair();
        setCreativeTab(StalmineMod.tabArtifacts);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        if (!entityItem.world.isRemote) {
            if (entityItem.onGround && entityItem.world.rand.nextFloat() > .9f) {
                entityItem.motionX = (entityItem.world.rand.nextGaussian() + .1f) / 15f * (entityItem.world.rand.nextInt(2) == 0 ? 1f : -1f);
                entityItem.motionY = .35f;
                entityItem.motionZ = (entityItem.world.rand.nextGaussian() + .1f) / 15f * (entityItem.world.rand.nextInt(2) == 0 ? 1f : -1f);
            }
        }
        if (entityItem.world.rand.nextFloat() > .5f) {
            entityItem.world.spawnParticle(
                    StalmineParticles.ARTIFACT,
                    entityItem.posX,
                    entityItem.posY + .25,
                    entityItem.posZ,
                    model.getR(),
                    model.getG(),
                    model.getB()
            );
        }
        return super.onEntityItemUpdate(entityItem);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if (model.getAntirad() > 0f)
            tooltip.add(TextFormatting.GREEN + I18n.format("artifact.stalmine.antirad.info") + " " + (model.getAntirad() * 100f) + "%");
        if (model.getAntipsy() > 0f)
            tooltip.add(TextFormatting.GREEN + I18n.format("artifact.stalmine.antipsy.info") + " " + (model.getAntipsy() * 100f) + "%");
        if (model.getAntichem() > 0f)
            tooltip.add(TextFormatting.GREEN + I18n.format("artifact.stalmine.antichem.info") + " " + (model.getAntichem() * 100f) + "%");
        if (model.getAntiterm() > 0f)
            tooltip.add(TextFormatting.GREEN + I18n.format("artifact.stalmine.antiterm.info") + " " + (model.getAntiterm() * 100f) + "%");
        if (model.getAntielectra() > 0f)
            tooltip.add(TextFormatting.GREEN + I18n.format("artifact.stalmine.antielectra.info") + " " + (model.getAntielectra() * 100f) + "%");
        if (model.getRegen() > 0f)
            tooltip.add(TextFormatting.GREEN + I18n.format("artifact.stalmine.regen.info") + " " + (model.getRegen() * 100f) + "%");
        if (model.getSpeed() > 0f)
            tooltip.add(TextFormatting.GREEN + I18n.format("artifact.stalmine.speed.info") + " " + (model.getSpeed() * 100f) + "%");
        if (model.getResistance() > 0f)
            tooltip.add(TextFormatting.GREEN + I18n.format("artifact.stalmine.resistance.info") + " " + (model.getResistance() * 100f) + "%");
        if (model.getRad() > 0f)
            tooltip.add(TextFormatting.RED + I18n.format("artifact.stalmine.rad.info") + " " + (model.getRad() * 100f) + "%");
        if (model.getPsy() > 0f)
            tooltip.add(TextFormatting.RED + I18n.format("artifact.stalmine.psy.info") + " " + (model.getPsy() * 100f) + "%");
        if (model.getChem() > 0f)
            tooltip.add(TextFormatting.RED + I18n.format("artifact.stalmine.chem.info") + " " + (model.getChem() * 100f) + "%");
        if (model.getTerm() > 0f)
            tooltip.add(TextFormatting.RED + I18n.format("artifact.stalmine.term.info") + " " + (model.getTerm() * 100f) + "%");
        if (model.getPoison() > 0f)
            tooltip.add(TextFormatting.RED + I18n.format("artifact.stalmine.poison.info") + " " + (model.getPoison() * 100f) + "%");
        if (model.getSlowness() > 0f)
            tooltip.add(TextFormatting.RED + I18n.format("artifact.stalmine.slowness.info") + " " + (model.getSlowness() * 100f) + "%");
        if (model.getWeakness() > 0f)
            tooltip.add(TextFormatting.RED + I18n.format("artifact.stalmine.weakness.info") + " " + (model.getWeakness() * 100f) + "%");
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
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.onCreated(stack, worldIn, playerIn);
        stack.setTagCompound(resetNBT());
    }

    private NBTTagCompound resetNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setFloat("timer", 0f);
        return nbt;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return EnumActionResult.FAIL;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        return true;
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, World world) {
        return 3000 + rarity * 1000;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
    {
        return false;
    }
}
