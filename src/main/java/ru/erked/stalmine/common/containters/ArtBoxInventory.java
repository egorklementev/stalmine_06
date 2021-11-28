package ru.erked.stalmine.common.containters;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.Constants;
import ru.erked.stalmine.common.items.ItemArtifact;

import java.util.Arrays;

public class ArtBoxInventory implements IInventory {

    private final int type;
    private String name = "Art box ";
    private final ItemStack invItem;
    private final ItemStack[] inventory;

    public ArtBoxInventory(ItemStack is, int type) {
        inventory = new ItemStack[type * 2];
        Arrays.fill(inventory, ItemStack.EMPTY);
        this.type = type;
        name += type;
        invItem = is;

        if (!is.hasTagCompound()) {
            is.setTagCompound(new NBTTagCompound());
        }
        readFromNBT(is.getTagCompound());
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack i : inventory) {
            if (i == null || !i.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = getStackInSlot(index);
        if(stack != null)
        {
            if(stack.getCount() > count)
            {
                stack = stack.splitStack(count);
                markDirty();
            }
            else
            {
                setInventorySlotContents(index, ItemStack.EMPTY);
            }
        }
        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack is = getStackInSlot(index);
        setInventorySlotContents(index, ItemStack.EMPTY);
        return is;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        inventory[index] = stack;
        if (stack != null && stack.getCount() > getInventoryStackLimit()) {
            stack.setCount(getInventoryStackLimit());
        }
        markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {
        for (int i = 0; i < getSizeInventory(); ++i)
        {
            if (getStackInSlot(i) != null && getStackInSlot(i).getCount() == 0) {
                inventory[i] = ItemStack.EMPTY;
            }
        }

        writeToNBT(invItem.getTagCompound());
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) { }

    @Override
    public void closeInventory(EntityPlayer player) { }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return stack.getItem() instanceof ItemArtifact;
    }

    @Override
    public int getField(int id) { return 0; }

    @Override
    public void setField(int id, int value) { }

    @Override
    public int getFieldCount() { return 0; }

    @Override
    public void clear() {
        for (int i = 0; i < type * 2; i++) {
            setInventorySlotContents(i, ItemStack.EMPTY);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasCustomName() {
        return name.length() > 0;
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    public void readFromNBT(NBTTagCompound compound) {
        NBTTagList items = compound.getTagList("ItemInventory", Constants.NBT.TAG_COMPOUND);

        for (int i = 0; i < items.tagCount(); ++i)
        {
            NBTTagCompound item = items.getCompoundTagAt(i);
            int slot = item.getInteger("Slot");

            if (slot >= 0 && slot < getSizeInventory()) {
                inventory[slot] = new ItemStack(item);
            }
        }
    }

    public void writeToNBT(NBTTagCompound tagcompound) {
        NBTTagList items = new NBTTagList();

        for (int i = 0; i < getSizeInventory(); ++i)
        {
            if (getStackInSlot(i) != null)
            {
                NBTTagCompound item = new NBTTagCompound();
                item.setInteger("Slot", i);
                getStackInSlot(i).writeToNBT(item);

                items.appendTag(item);
            }
        }
        tagcompound.setTag("ItemInventory", items);
    }
}
