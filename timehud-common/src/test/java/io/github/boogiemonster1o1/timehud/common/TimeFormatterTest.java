package io.github.boogiemonster1o1.timehud.common;

import org.junit.Test;

import static io.github.boogiemonster1o1.timehud.common.TimeFormatter.format;
import static io.github.boogiemonster1o1.timehud.common.TimeFormatter.getString;
import static io.github.boogiemonster1o1.timehud.common.TimeFormatter.toSeconds;
import static org.junit.Assert.assertEquals;

public class TimeFormatterTest {
    @Test
    public void testFormat() {
        assertEquals(43200L, toSeconds(12000L));
        assertEquals(0L, toSeconds(24000L));
        assertEquals("23:58", format(86300L, false));
        assertEquals("00", getString(0));
        assertEquals("12:00", format(12000L, true));
        assertEquals("08:20", format(8340L, true));
    }
}
