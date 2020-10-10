package io.github.boogiemonster1o1.timehud.command;

import java.nio.file.Path;

import io.github.boogiemonster1o1.timehud.common.TimeHudManager;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class TimeHudCommand {

    public static void register(CommandManager manager, Object plugin) {
        CommandSpec spec = CommandSpec.builder()
                .description(Text.of("/timehud"))
                .executor(TimeHudCommand::execute)
                .permission("timehud.command.timehud.permission").build();
        manager.register(plugin, spec, "timehud");
    }

    private static CommandResult execute(CommandSource source, CommandContext ctx) throws CommandException {
        if (!(source instanceof Player)) {
            throw new CommandException(Text.of("Can only be executed by a player!"));
        }
        TimeHudManager.getTimeHudManager().handle(source.getName());
        return CommandResult.success();
    }
}
