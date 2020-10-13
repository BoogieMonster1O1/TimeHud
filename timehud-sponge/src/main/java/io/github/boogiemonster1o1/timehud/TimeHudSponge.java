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
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.chat.ChatTypes;

@Plugin(id = "timehud-sponge")
public class TimeHudSponge {
    private static final TimeHudManager TIME_HUD_MANAGER = TimeHudManager.getTimeHudManager();

    @Inject
    private Logger logger;

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
        Task.builder().intervalTicks(1L).execute(this::sendMessage).submit(this);
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
