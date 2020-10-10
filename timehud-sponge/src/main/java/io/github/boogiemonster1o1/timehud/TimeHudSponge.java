package io.github.boogiemonster1o1.timehud;

import io.github.boogiemonster1o1.timehud.command.TimeHudCommand;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "timehud-sponge")
public class TimeHudSponge {
    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
    }

    @Listener
    public void onInitalize(GameInitializationEvent event) {
        CommandManager manager = Sponge.getCommandManager();
        TimeHudCommand.register(manager, this);
    }
}
