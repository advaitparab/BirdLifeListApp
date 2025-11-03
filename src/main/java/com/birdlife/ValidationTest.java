package com.birdlife;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


import org.junit.Test;


public class ValidationTest {
    @Test
    public void requireNonBlank_allows_good_value() {
        String out = Validation.requireNonBlank("Cardinal", "commonName");
        assertEquals("Cardinal", out);
    }

    @Test
    public void requireNonBlank_throws_on_blank() {
        assertThrows(IllegalArgumentException.class,
                () -> Validation.requireNonBlank("  ", "commonName"));
    }
}
