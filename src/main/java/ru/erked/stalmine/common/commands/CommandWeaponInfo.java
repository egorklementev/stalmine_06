package ru.erked.stalmine.common.commands;

import com.google.common.collect.Lists;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import ru.erked.stalmine.StalmineMod;
import ru.erked.stalmine.common.weapons.Weapon;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class CommandWeaponInfo extends CommandBase {

    public CommandWeaponInfo(){
        aliases = Lists.newArrayList("WINFO", "winfo");
    }

    private final List<String> aliases;

    @Override
    @Nonnull
    public String getName() {
        return "winfo";
    }

    @Override
    @Nonnull
    public String getUsage(@Nonnull ICommandSender sender) {
        return "winfo";
    }

    @Override
    @Nonnull
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
        if (sender instanceof EntityPlayer) {
            if (!((EntityPlayer)sender).inventory.getCurrentItem().isEmpty() &&
                    ((EntityPlayer)sender).inventory.getCurrentItem().getItem() instanceof Weapon
            ) {
                Weapon w = (Weapon) ((EntityPlayer)sender).inventory.getCurrentItem().getItem();
                sender.sendMessage(
                        new TextComponentString(
                                TextFormatting.WHITE +
                                        "[INFO] w_damage: " + w.model.getDamage() + "\n" +
                                        "[INFO] w_max_clip_size: " + w.model.getMaxClipSize() + "\n" +
                                        "[INFO] w_type: " + w.model.getType().name()
                        )
                );
            }
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    @Nonnull
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return Collections.emptyList();
    }

}
