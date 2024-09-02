package net.Byebye007x.firstprotomod.util;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class NBTExtract {
    private static int execute (CommandContext<CommandSourceStack> commandContext) {
        if (commandContext.getSource().getEntity() instanceof Player player) {
            player.sendSystemMessage(Component.literal("Hello World"));
        }
        return Command.SINGLE_SUCCESS;
    }
}
