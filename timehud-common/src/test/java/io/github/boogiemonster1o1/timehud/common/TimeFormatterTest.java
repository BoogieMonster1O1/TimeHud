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
