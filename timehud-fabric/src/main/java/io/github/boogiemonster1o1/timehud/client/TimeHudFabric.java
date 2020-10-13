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
