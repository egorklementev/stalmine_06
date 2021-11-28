package ru.erked.stalmine.common.containters;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import ru.erked.stalmine.common.items.ItemArtifact;
import ru.erked.stalmine.common.tile_entities.TEBag;

public class ContainerArtBox extends Container {

    public final ArtBoxInventory inventory;

    private int INV_START;
    private int INV_END;
    private int HOTBAR_START;
    private int HOTBAR_END;

    public ContainerArtBox(EntityPlayer par1Player, InventoryPlayer inventoryPlayer, ArtBoxInventory inventoryItem) {
        this.inventory = inventoryItem;
        INV_START = inventoryItem.getSizeInventory();
        INV_END = INV_START + 26;
        HOTBAR_START = INV_END + 1;
        HOTBAR_END = HOTBAR_START + 1;

        // Art box slots
        for (int i = 0; i < INV_START; ++i) {
            this.addSlotToContainer(new Slot(this.inventory, i, 36 + i * 18, 30){
                @Override
                public boolean isItemValid(ItemStack stack) {
                    return stack.getItem() instanceof ItemArtifact;
                }
            });
        }

        // PLAYER INVENTORY - uses default locations for standard inventory texture file
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 9 + j * 18, 70 + i * 18));
            }
        }

        // PLAYER ACTION BAR - uses default locations for standard action bar texture file
        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 9 + i * 18, 128));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return inventory.isUsableByPlayer(entityplayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < INV_START)
            {
                if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END+1, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else {
				if (itemstack1.getItem() instanceof ItemArtifact) {
					if (!this.mergeItemStack(itemstack1, 0, INV_START, false)) {
						return ItemStack.EMPTY;
					}
				}
                if (index >= INV_START)
                {
                    if (!this.mergeItemStack(itemstack1, 0, INV_START, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (itemstack1.getCount() == 0)
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }

    @Override
    public ItemStack slotClick(int slot, int button, ClickType flag, EntityPlayer player) {
        // this will prevent the player from interacting with the item that opened the inventory:
        if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem(EnumHand.MAIN_HAND)) {
            return ItemStack.EMPTY;
        }
        return super.slotClick(slot, button, flag, player);
    }
}
