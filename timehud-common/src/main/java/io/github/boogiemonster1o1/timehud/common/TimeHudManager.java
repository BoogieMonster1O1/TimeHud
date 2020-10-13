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

    public boolean shouldSend(String username) {
        return this.usernames.contains(username);
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
        } catch (RuntimeException e) {
            save(path);
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
