package io.github.boogiemonster1o1.timehud;

import java.nio.file.Path;

import io.github.boogiemonster1o1.timehud.command.TimeHudCommand;
import io.github.boogiemonster1o1.timehud.common.TimeHudManager;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "timehud-sponge")
public class TimeHudSponge {
    @Inject
    private Logger logger;

    @Inject
    @ConfigDir(sharedRoot = true)
    private Path configDir;

    public static TimeHudSponge instance;

    @Listener
    public void onInitalize(GameInitializationEvent event) {
        instance = this;
        this.logger.info("Starting TimeHud");
        CommandManager manager = Sponge.getCommandManager();
        TimeHudManager.load(this.getConfigDir().resolve("timehud.json"));
        TimeHudManager.save(this.getConfigDir().resolve("timehud.json"));
        TimeHudCommand.register(manager, this);
    }

    public Path getConfigDir() {
        return this.configDir;
    }
}
