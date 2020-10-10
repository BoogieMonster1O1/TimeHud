package io.github.boogiemonster1o1.timehud.client.command;

import io.github.boogiemonster1o1.timehud.common.TimeHudManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

public final class TimeHudCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("timehud").executes(TimeHudCommand::execute));
    }

    private static int execute(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        ServerPlayerEntity player = ctx.getSource().getPlayer();
        boolean removed = TimeHudManager.getTimeHudManager().handle(player.getEntityName());
        if (removed) {
            player.sendMessage(new LiteralText("Disabled TimeHud").formatted(Formatting.RED), false);
        } else {
            player.sendMessage(new LiteralText("Enabled TimeHud").formatted(Formatting.GREEN), false);
        }
        return 1;
    }
}
