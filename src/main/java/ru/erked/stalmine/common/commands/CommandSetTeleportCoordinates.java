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
import ru.erked.stalmine.client.StalmineConfig;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class CommandSetTeleportCoordinates extends CommandBase {

    public CommandSetTeleportCoordinates(){
        aliases = Lists.newArrayList("SETTELE", "settele");
    }

    private final List<String> aliases;

    @Override
    @Nonnull
    public String getName() {
        return "settele";
    }

    @Override
    @Nonnull
    public String getUsage(@Nonnull ICommandSender sender) {
        return "settele <x> <y> <z>";
    }

    @Override
    @Nonnull
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
        if (sender instanceof EntityPlayer) {
            try {
                StalmineConfig.teleX = CommandBase.parseCoordinate(((EntityPlayer) sender).posX, args[0], true);
                StalmineConfig.teleY = CommandBase.parseCoordinate(((EntityPlayer) sender).posY, args[1], -4096, 4096, false);
                StalmineConfig.teleZ = CommandBase.parseCoordinate(((EntityPlayer) sender).posZ, args[2], true);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            sender.sendMessage(
                    new TextComponentString(
                            TextFormatting.WHITE +
                                    "[INFO] Teleport destination coordinates are set"
                    )
            );
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
