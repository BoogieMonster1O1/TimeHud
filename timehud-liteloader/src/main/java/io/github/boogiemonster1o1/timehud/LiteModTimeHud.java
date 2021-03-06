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

package io.github.boogiemonster1o1.timehud;

import java.io.File;
import java.nio.file.Path;

import io.github.boogiemonster1o1.timehud.common.TimeFormatter;
import io.github.boogiemonster1o1.timehud.common.TimeHudManager;
import io.github.boogiemonster1o1.timehud.config.TimeHudConfigPanel;
import com.mumfrey.liteloader.Configurable;
import com.mumfrey.liteloader.HUDRenderListener;
import com.mumfrey.liteloader.LiteMod;
import com.mumfrey.liteloader.OutboundChatFilter;
import com.mumfrey.liteloader.modconfig.ConfigPanel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class LiteModTimeHud implements LiteMod, HUDRenderListener, OutboundChatFilter, Configurable {
    public static Path configPath;

    @Override
    public String getVersion() {
        return "@VERSION";
    }

    @Override
    public void init(File ignored) {
        configPath = Minecraft.getMinecraft().gameDir.toPath().resolve("timehud.json");
        TimeHudManager.load(configPath);
    }

    @Override
    public void upgradeSettings(String version, File configPath, File oldConfigPath) {
    }

    @Override
    public String getName() {
        return "TimeHud";
    }

    @Override
    public void onPreRenderHUD(int screenWidth, int screenHeight) {
    }

    @Override
    public void onPostRenderHUD(int screenWidth, int screenHeight) {
        if (TimeHudManager.getTimeHudManager().shouldSend(Minecraft.getMinecraft().player.getGameProfile().getName())) {
            long time = Minecraft.getMinecraft().world.getWorldTime();
            String formattedTime = TimeFormatter.format(time);
            Minecraft.getMinecraft().ingameGUI.setOverlayMessage(formattedTime, false);
        }
    }

    @Override
    public boolean onSendChatMessage(String message) {
        if (message.equals("/timehud")) {
            boolean removed = TimeHudManager.getTimeHudManager().handle(Minecraft.getMinecraft().player.getGameProfile().getName());
            if (removed) {
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString(TextFormatting.RED + I18n.format("timehud.disabled")));
            } else {
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString(TextFormatting.GREEN + I18n.format("timehud.enabled")));
            }
            TimeHudManager.save(configPath);
            return false;
        }
        return true;
    }

    @Override
    public Class<? extends ConfigPanel> getConfigPanelClass() {
        return TimeHudConfigPanel.class;
    }
}
