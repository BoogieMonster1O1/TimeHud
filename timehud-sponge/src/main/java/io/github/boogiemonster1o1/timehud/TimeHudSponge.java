package io.github.boogiemonster1o1.timehud;

import java.nio.file.Path;

import io.github.boogiemonster1o1.timehud.command.TimeHudCommand;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "timehud-sponge")
public class TimeHudSponge {
    @Inject
    private Logger logger;

    @Inject
    @ConfigDir(sharedRoot = true)
    private Path configDir;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
    }

    @Listener
    public void onInitalize(GameInitializationEvent event) {
        CommandManager manager = Sponge.getCommandManager();
        TimeHudCommand.register(manager, this);
        System.out.println(this.configDir.toString());
    }
}
