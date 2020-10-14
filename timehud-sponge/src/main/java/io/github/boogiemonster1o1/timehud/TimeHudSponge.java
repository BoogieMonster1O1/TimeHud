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

package io.github.boogiemonster1o1.timehud;

import java.nio.file.Path;

import io.github.boogiemonster1o1.timehud.command.TimeHudCommand;
import io.github.boogiemonster1o1.timehud.common.TimeFormatter;
import io.github.boogiemonster1o1.timehud.common.TimeHudManager;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.chat.ChatTypes;

@Plugin(id = "timehud-sponge")
public class TimeHudSponge {
    private static final TimeHudManager TIME_HUD_MANAGER = TimeHudManager.getTimeHudManager();

    @Inject
    private Logger logger;

    private Task task;

    @Inject
    @ConfigDir(sharedRoot = true)
    private Path configDir;

    public static TimeHudSponge instance;

    @Listener
    public void onInitalize(GameInitializationEvent event) {
        instance = this;
        this.logger.info("Loading TimeHud");
        CommandManager manager = Sponge.getCommandManager();
        TimeHudManager.load(this.getConfigDir().resolve("timehud.json"));
        TimeHudCommand.register(manager, this);
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        this.task = Task.builder().intervalTicks(1L).name("TimeHud Message Loop").execute(this::sendMessage).submit(this);
    }

    @Listener
    public void onServerStopping(GameStoppingServerEvent event) {
        this.task.cancel();
    }

    private void sendMessage() {
        Sponge.getGame()
                .getServer()
                .getOnlinePlayers()
                .stream()
                .filter(player -> TIME_HUD_MANAGER.shouldSend(player.getName()))
                .forEach(player -> {
                    String time = TimeFormatter.format(player.getWorld().getProperties().getWorldTime());
                    player.sendMessage(ChatTypes.ACTION_BAR, Text.of(time));
                });
    }

    public Path getConfigDir() {
        return this.configDir;
    }
}
