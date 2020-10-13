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

public class TimeFormatter {
    public static String format(long time, boolean mcTime) {
        time = mcTime ? toSeconds(time) : time;
        long hours = time / 60;
        long minutes = hours % 60;
        hours /= 60;
        StringBuilder minutesString = new StringBuilder().append(minutes);
        return getString(hours) + ":" + getString(minutes);
    }

    public static String format(long time) {
        return format(time, true);
    }

    public static long toSeconds(long time) {
        time %= 24000;
        time = (86400 * time) / 24000;
        return time;
    }

    static String getString(long value) {
        String s = Long.toString(value);
        if (s.length() == 1) {
            s = "0" + s;
        }
        return s;
    }
}
