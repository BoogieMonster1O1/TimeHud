package io.github.boogiemonster1o1.timehud.config;

import io.github.boogiemonster1o1.timehud.LiteModTimeHud;
import io.github.boogiemonster1o1.timehud.common.TimeHudManager;
import com.mumfrey.liteloader.client.gui.GuiCheckbox;
import com.mumfrey.liteloader.modconfig.AbstractConfigPanel;
import com.mumfrey.liteloader.modconfig.ConfigPanelHost;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class TimeHudConfigPanel extends AbstractConfigPanel {
    private GuiCheckbox enabled;

    @Override
    public String getPanelTitle() {
        return I18n.format("timehud.config.screen.title");
    }

    @Override
    public int getContentHeight() {
        return 100;
    }

    @Override
    public void onPanelHidden() {
        TimeHudManager.save(LiteModTimeHud.configPath);
    }

    @Override
    protected void addOptions(ConfigPanelHost host) {
        String username = Minecraft.getMinecraft().player.getGameProfile().getName();
        this.addControl(this.enabled = new GuiCheckbox(11, 24, 32, I18n.format("timehud.config.enable")), (option) -> {
            if (!option.checked) {
                TimeHudManager.getTimeHudManager().usernames.remove(username);
            } else {
                if (!TimeHudManager.getTimeHudManager().usernames.contains(username)) {
                    TimeHudManager.getTimeHudManager().usernames.add(username);
                }
            }
        });
    }
}
