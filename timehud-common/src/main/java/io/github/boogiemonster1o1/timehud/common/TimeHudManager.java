package io.github.boogiemonster1o1.timehud.common;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class TimeHudManager {
    private static TimeHudManager INSTANCE = new TimeHudManager();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().setLenient().create();
    @SerializedName("enabled_usernames")
    public List<String> usernames = Lists.newArrayList();

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
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                save(path);
                return;
            }
            INSTANCE = GSON.fromJson(new InputStreamReader(Files.newInputStream(path)), TimeHudManager.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(Path path) {
        String json = GSON.toJson(INSTANCE);
        try {
            Files.write(path, json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
