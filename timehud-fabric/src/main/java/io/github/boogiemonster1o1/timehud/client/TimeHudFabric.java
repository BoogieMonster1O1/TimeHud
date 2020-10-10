package io.github.boogiemonster1o1.timehud.client;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

@Environment(EnvType.SERVER)
public class TimeHudFabric implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        CommandRegistrationCallback.EVENT.register(EventHandler.INSTANCE);
        ServerTickEvents.END_SERVER_TICK.register(server -> {

        });
    }
}
