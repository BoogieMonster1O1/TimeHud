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

package io.github.boogiemonster1o1.timehud.client;

import io.github.boogiemonster1o1.timehud.client.command.TimeHudCommand;
import io.github.boogiemonster1o1.timehud.common.TimeFormatter;
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
        server.getPlayerManager()
                .getPlayerList()
                .stream()
                .filter(player -> TimeHudManager.getTimeHudManager().shouldSend(player.getGameProfile().getName()))
                .forEach(player -> {
                    String time = TimeFormatter.format(player.world.getTimeOfDay());
                    player.sendMessage(new LiteralText(time), true);
                });
    }
}
