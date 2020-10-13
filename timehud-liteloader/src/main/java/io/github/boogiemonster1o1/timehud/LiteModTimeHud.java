package io.github.boogiemonster1o1.timehud;

import java.io.File;

import io.github.boogiemonster1o1.timehud.common.TimeFormatter;
import io.github.boogiemonster1o1.timehud.common.TimeHudManager;
import com.mumfrey.liteloader.HUDRenderListener;
import com.mumfrey.liteloader.LiteMod;
import com.mumfrey.liteloader.OutboundChatFilter;

import net.minecraft.client.Minecraft;

public class LiteModTimeHud implements LiteMod, HUDRenderListener, OutboundChatFilter {
    @Override
    public String getVersion() {
        return "@VERSION";
    }

    @Override
    public void init(File configPath) {
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
            TimeHudManager.getTimeHudManager().handle(Minecraft.getMinecraft().player.getGameProfile().getName());
            return false;
        }
        return true;
    }
}