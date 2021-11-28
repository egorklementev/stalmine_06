package ru.erked.stalmine.common.containters;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.common.items.ItemCrosshair;
import ru.erked.stalmine.common.items.ItemGrenadeLauncher;
import ru.erked.stalmine.common.weapons.Weapon;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class WpnTableISHandler extends ItemStackHandler {

    private static boolean isAssembling = false;

    public WpnTableISHandler(int size) {
        super(size);
    }

    private static HashMap<String, String> addonsMap = new HashMap<String, String>(){
        {
            put("gp", "stalmine:w_gp25");
            put("gl", "stalmine:w_gl5040");
            put("pso", "stalmine:w_pso1");
            put("psu", "stalmine:w_psu1");
            put("susat", "stalmine:w_susat");
            put("susat16", "stalmine:w_susat16");
            put("susat16nvd", "stalmine:w_susat16nvd");
            put("susat4nvd", "stalmine:w_susat4nvd");
        }
    };

    private static HashMap<String, String> deAddonsMap = new HashMap<String, String>(){
        {
            put("w_gp25", "gp");
            put("w_gl5040", "gl");
            put("w_pso1", "pso");
            put("w_psu1", "psu");
            put("w_susat", "susat");
            put("w_susat16", "susat16");
            put("w_susat16nvd", "susat16nvd");
            put("w_susat4nvd", "susat4nvd");
        }
    };

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        switch (slot) {
            case 0: {
                return stack.getItem() instanceof ItemCrosshair && !getStackInSlot(1).isEmpty();
            }
            case 1: {
                return stack.getItem() instanceof Weapon && !(((Weapon)stack.getItem()).model.hasCrosshairAttached() ||
                        ((Weapon)stack.getItem()).model.hasGrenadeLauncherAttached());
            }
            case 2: {
                return stack.getItem() instanceof ItemGrenadeLauncher && !getStackInSlot(1).isEmpty();
            }
            case 3: {
                return stack.getItem() instanceof Weapon && (((Weapon)stack.getItem()).model.hasCrosshairAttached() ||
                        ((Weapon)stack.getItem()).model.hasGrenadeLauncherAttached()) &&
                        getStackInSlot(0).isEmpty() && getStackInSlot(1).isEmpty() && getStackInSlot(2).isEmpty();
            }
            default: {
                return false;
            }
        }
    }

    @Override
    protected void onContentsChanged(int slot) {
        switch (slot) {
            case 0: { // Crosshair
                if (!getStackInSlot(1).isEmpty()) {
                    isAssembling = true;
                    assembleWpn();
                    isAssembling = false;
                    //System.out.println("ASSEMBLE: CROSS");
                }
                break;
            }
            case 1: { // A weapon itself
                if (!getStackInSlot(1).isEmpty()) {
                    isAssembling = true;
                    assembleWpn();
                    isAssembling = false;
                    //System.out.println("ASSEMBLE: WPN");
                }
                break;
            }
            case 2: { // Grenade launcher
                if (!getStackInSlot(1).isEmpty()) {
                    isAssembling = true;
                    assembleWpn();
                    isAssembling = false;
                    //System.out.println("ASSEMBLE: GREN");
                }
                break;
            }
            case 3: {
                if (!getStackInSlot(3).isEmpty() && !isAssembling) {
                    disassembleWpn();
                    //System.out.println("DISASSEMBLE");
                }
                break;
            }
            default: {
                break;
            }
        }
    }

    private void disassembleWpn() {
        String wpnFullName = getStackInSlot(3).getUnlocalizedName();
        ItemStack moddedWpn = getStackInSlot(3);

        String prefix = "item.stalmine.";
        String crosshairName = "";
        String wpnName = "";
        String gLauncherName = "";
        if (((Weapon)moddedWpn.getItem()).model.hasCrosshairAttached() &&
                ((Weapon)moddedWpn.getItem()).model.hasGrenadeLauncherAttached()) {
            gLauncherName = wpnFullName.substring(
                    wpnFullName.lastIndexOf("_") + 1,
                    wpnFullName.length()
            );
            crosshairName = wpnFullName.substring(
                    0,
                    wpnFullName.lastIndexOf("_")
            );
            crosshairName = crosshairName.substring(
                    crosshairName.lastIndexOf("_") + 1,
                    crosshairName.length()
            );
            wpnName = wpnFullName.substring(
                    0,
                    wpnFullName.lastIndexOf("_")
            );
            wpnName = wpnName.substring(
                    0,
                    wpnName.lastIndexOf("_")
            );
            wpnName = wpnName.substring(
                    prefix.length(),
                    wpnName.length()
            );
        }
        else if (((Weapon)moddedWpn.getItem()).model.hasCrosshairAttached()) {
            crosshairName = wpnFullName.substring(
                    wpnFullName.lastIndexOf("_") + 1,
                    wpnFullName.length()
            );
            wpnName = wpnFullName.substring(
                    0,
                    wpnFullName.lastIndexOf("_")
            );
            wpnName = wpnName.substring(
                    prefix.length(),
                    wpnName.length()
            );
        }
        else if (((Weapon)moddedWpn.getItem()).model.hasGrenadeLauncherAttached()) {
            gLauncherName = wpnFullName.substring(
                    wpnFullName.lastIndexOf("_") + 1,
                    wpnFullName.length()
            );
            wpnName = wpnFullName.substring(
                    0,
                    wpnFullName.lastIndexOf("_")
            );
            wpnName = wpnName.substring(
                    prefix.length(),
                    wpnName.length()
            );
        }

        crosshairName = addonsMap.get(crosshairName);
        gLauncherName = addonsMap.get(gLauncherName);
        wpnName = StalmineMod.MODID + ":" + wpnName;

        //System.out.println("candidate cross: " + crosshairName);
        //System.out.println("candidate wpn: " + wpnName);
        //System.out.println("candidate gLauncher: " + gLauncherName);

        Item wpnItem = Item.getByNameOrId(wpnName);
        if (wpnItem != null) {
            ItemStack wpn = new ItemStack(wpnItem);
            wpn.setTagCompound(moddedWpn.getTagCompound());
            wpn.setItemDamage(moddedWpn.getItemDamage());
            setStackInSlot(1, wpn);
        }

        if (crosshairName != null) {
            Item crosshairItem = Item.getByNameOrId(crosshairName);
            if (crosshairItem != null) {
                setStackInSlot(0, new ItemStack(crosshairItem));
            }
        }
        if (gLauncherName != null) {
            Item gLauncherItem = Item.getByNameOrId(gLauncherName);
            if (gLauncherItem != null) {
                setStackInSlot(2, new ItemStack(gLauncherItem));
            }
        }
    }

    private void assembleWpn() {
        String prefix = "item.stalmine.";
        String crosshairPostfix = "";
        String gLauncherPostfix = "";
        ItemStack crosshair = getStackInSlot(0);
        ItemStack weapon = getStackInSlot(1);
        ItemStack gLauncher = getStackInSlot(2);
        if (!crosshair.isEmpty()) {
            crosshairPostfix = crosshair.getUnlocalizedName();
            crosshairPostfix = crosshairPostfix.substring(prefix.length(), crosshairPostfix.length());
            crosshairPostfix = "_" + deAddonsMap.get(crosshairPostfix);
        }
        if (!gLauncher.isEmpty()) {
            gLauncherPostfix = gLauncher.getUnlocalizedName();
            gLauncherPostfix = gLauncherPostfix.substring(prefix.length(), gLauncherPostfix.length());
            gLauncherPostfix = "_" + deAddonsMap.get(gLauncherPostfix);
        }
        String candidateWpn = weapon.getUnlocalizedName() + crosshairPostfix + gLauncherPostfix;
        candidateWpn = candidateWpn.substring(prefix.length(), candidateWpn.length());
        candidateWpn = StalmineMod.MODID + ":" + candidateWpn;
        System.out.println("candidateWpn: " + candidateWpn);

        Item moddedWpnItem = Item.getByNameOrId(candidateWpn);
        if (moddedWpnItem != null) {
            ItemStack moddedWpn = new ItemStack(moddedWpnItem);
            moddedWpn.setTagCompound(weapon.getTagCompound());
            moddedWpn.setItemDamage(weapon.getItemDamage());
            setStackInSlot(3, moddedWpn);
        } else {
            setStackInSlot(3, ItemStack.EMPTY);
        }
    }
}
