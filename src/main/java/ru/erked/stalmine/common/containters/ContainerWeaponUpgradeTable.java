package ru.erked.stalmine.common.containters;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import ru.erked.stalmine.common.tile_entities.TEWeaponUpgradeTable;

public class ContainerWeaponUpgradeTable extends Container {

    private TEWeaponUpgradeTable te;
    private SlotItemHandler[] wpnTableSlots;

    public ContainerWeaponUpgradeTable(IInventory playerInventory, TEWeaponUpgradeTable te) {
        this.te = te;
        addOwnSlots();
        addPlayerSlots(playerInventory);
    }

    private void addPlayerSlots(IInventory playerInventory) {
        // Slots for the main inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = 9 + col * 18;
                int y = row * 18 + 118;
                this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 9, x, y));
            }
        }

        // Slots for the hotbar
        for (int row = 0; row < 9; ++row) {
            int x = 9 + row * 18;
            int y = 58 + 118;
            this.addSlotToContainer(new Slot(playerInventory, row, x, y));
        }
    }

    private void addOwnSlots() {
        IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        int x = 19;
        int y = 37;

        // Add our own slots
        wpnTableSlots = new SlotItemHandler[4];
        int slotIndex = 0;
       // Crosshair
        wpnTableSlots[slotIndex] = new SlotItemHandler(itemHandler, slotIndex, x, y);
        addSlotToContainer(wpnTableSlots[slotIndex]);
        slotIndex++;
        y += 18;
        // Weapon
        wpnTableSlots[slotIndex] = new SlotItemHandler(itemHandler, slotIndex, x, y) {
            @Override
            public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
                ContainerWeaponUpgradeTable.this.wpnTableSlots[3].putStack(ItemStack.EMPTY);
                return super.onTake(thePlayer, stack);
            }
        };
        addSlotToContainer(wpnTableSlots[slotIndex]);
        slotIndex++;
        y += 18;
        // Grenade launcher
        wpnTableSlots[slotIndex] = new SlotItemHandler(itemHandler, slotIndex, x, y);
        addSlotToContainer(wpnTableSlots[slotIndex]);
        slotIndex++;

        x += 26;
        y -= 18;
        // Resulting slot
        wpnTableSlots[slotIndex] = new SlotItemHandler(itemHandler, slotIndex, x, y){
            @Override
            public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
                ContainerWeaponUpgradeTable.this.wpnTableSlots[0].putStack(ItemStack.EMPTY);
                ContainerWeaponUpgradeTable.this.wpnTableSlots[1].putStack(ItemStack.EMPTY);
                ContainerWeaponUpgradeTable.this.wpnTableSlots[2].putStack(ItemStack.EMPTY);
                ContainerWeaponUpgradeTable.this.wpnTableSlots[3].putStack(ItemStack.EMPTY);
                return super.onTake(thePlayer, stack);
            }
        };
        addSlotToContainer(wpnTableSlots[slotIndex]);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        /*
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < TEWeaponUpgradeTable.SIZE) {
                if (!this.mergeItemStack(itemstack1, TEWeaponUpgradeTable.SIZE, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, TEWeaponUpgradeTable.SIZE, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        */
        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return te.canInteractWith(playerIn);
    }

}
