/*
 * Copyright 2020 BoogieMonster1O1
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.boogiemonster1o1.timehud.command;

import io.github.boogiemonster1o1.timehud.TimeHudSponge;
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
                .build();
        manager.register(plugin, spec, "timehud");
    }

    private static CommandResult execute(CommandSource source, CommandContext ctx) throws CommandException {
        if (!(source instanceof Player)) {
            throw new CommandException(Text.of("Can only be executed by a player!"));
        }
        TimeHudManager.getTimeHudManager().handle(source.getName());
        TimeHudManager.save(TimeHudSponge.instance.getConfigDir().resolve("timehud.json"));
        return CommandResult.success();
    }
}
