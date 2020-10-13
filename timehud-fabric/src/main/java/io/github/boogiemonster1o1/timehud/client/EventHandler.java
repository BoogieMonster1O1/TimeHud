package io.github.boogiemonster1o1.timehud.client;

import io.github.boogiemonster1o1.timehud.client.command.TimeHudCommand;
import io.github.boogiemonster1o1.timehud.common.TimeHudManager;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

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
        server.getPlayerManager().getPlayerList().stream().filter(player -> TimeHudManager.getTimeHudManager().shouldSend(player.getGameProfile().getName())).forEach(player -> {
            String time = String.valueOf(player.world.getTimeOfDay());
            player.sendMessage(new LiteralText(time), true);
        });
    }
}
