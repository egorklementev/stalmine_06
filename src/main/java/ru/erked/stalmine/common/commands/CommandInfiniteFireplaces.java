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
import ru.erked.stalmine.client.StalmineConfig;
import ru.erked.stalmine.common.weapons.Weapon;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class CommandInfiniteFireplaces extends CommandBase {

    public CommandInfiniteFireplaces(){
        aliases = Lists.newArrayList("INFFIRE", "inffire");
    }

    private final List<String> aliases;

    @Override
    @Nonnull
    public String getName() {
        return "inffire";
    }

    @Override
    @Nonnull
    public String getUsage(@Nonnull ICommandSender sender) {
        return "inffire";
    }

    @Override
    @Nonnull
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
        if (sender instanceof EntityPlayer) {
            sender.sendMessage(
                    new TextComponentString(
                            TextFormatting.WHITE +
                                    "[INFO] Infinite fireplaces " +
                                    (StalmineConfig.placeInifiniteFireplaces ?
                                            (TextFormatting.RED + "OFF") :
                                            (TextFormatting.GREEN + "ON"))
                    )
            );
            StalmineConfig.placeInifiniteFireplaces = !StalmineConfig.placeInifiniteFireplaces;
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
