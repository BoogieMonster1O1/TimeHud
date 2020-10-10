package io.github.boogiemonster1o1.timehud.common;

import java.nio.file.Path;
import java.util.List;

import com.google.common.collect.Lists;

public class TimeHudManager {
    private static final TimeHudManager INSTANCE = new TimeHudManager();

    private List<String> usernames = Lists.newArrayList();

    public static TimeHudManager getTimeHudManager() {
        return INSTANCE;
    }

    public boolean handle(String username) {
        if (!this.usernames.remove(username)) {
            this.usernames.add(username);
            return false;
        }
        return true;
    }

    public static void load(Path path) {

    }

    public void save(Path path) {

    }
}
