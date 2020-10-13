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
