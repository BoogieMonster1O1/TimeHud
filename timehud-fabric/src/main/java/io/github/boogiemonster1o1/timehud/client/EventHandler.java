package io.github.boogiemonster1o1.timehud.client;

import io.github.boogiemonster1o1.timehud.client.command.TimeHudCommand;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.EndTick;

public enum EventHandler implements EndTick, CommandRegistrationCallback {
    INSTANCE;

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        TimeHudCommand.register(dispatcher);
    }

    @Override
    public void onEndTick(MinecraftServer server) {

    }

}
