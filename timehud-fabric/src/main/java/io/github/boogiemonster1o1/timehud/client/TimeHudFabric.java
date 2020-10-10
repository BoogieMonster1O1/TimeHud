package io.github.boogiemonster1o1.timehud.client;

import java.nio.file.Path;

import io.github.boogiemonster1o1.timehud.common.TimeHudManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.loader.api.FabricLoader;

@Environment(EnvType.SERVER)
public class TimeHudFabric implements DedicatedServerModInitializer {
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("timehud.json5");
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitializeServer() {
        LOGGER.info("Loading TimeHud");
        TimeHudManager.load(CONFIG_PATH);
        CommandRegistrationCallback.EVENT.register(EventHandler.INSTANCE);
        ServerTickEvents.END_SERVER_TICK.register(EventHandler.INSTANCE);
    }
}
