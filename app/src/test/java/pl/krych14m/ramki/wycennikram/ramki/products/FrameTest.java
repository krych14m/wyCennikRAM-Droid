package pl.krych14m.ramki.wycennikram.ramki.products;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FrameTest {

    @Test
    public void frame_string_simple_numbers() {
        assertEquals(
                new Frame("10", 10, 15, ColorType.RAW, false, false, false, false),
                new Frame("P10 10x15"));
    }

    @Test
    public void frame_string_decimal_places_comma() {
        assertEquals(
                new Frame("10", 10.5, 15.5, ColorType.RAW, false, false, false, false),
                new Frame("P10 10,5x15,5"));
    }

    @Test
    public void frame_string_decimal_places_dot() {
        assertEquals(
                new Frame("10", 10.5, 15.5, ColorType.RAW, false, false, false, false),
                new Frame("P10 10.5x15.5"));
    }

    @Test
    public void frame_string_stain() {
        assertEquals(
                new Frame("10", 10, 15, ColorType.STAIN, false, false, false, false),
                new Frame("P10 10x15 B"));
    }

    @Test
    public void frame_string_opaque() {
        assertEquals(
                new Frame("10", 10, 15, ColorType.OPAQUE, false, false, false, false),
                new Frame("P10 10x15 F"));
    }

    @Test
    public void frame_string_worn_out() {
        assertEquals(
                new Frame("10", 10, 15, ColorType.WORN_OUT, false, false, false, false),
                new Frame("P10 10x15 P"));
    }

    @Test
    public void frame_string_glass() {
        assertEquals(
                new Frame("10", 10, 15, ColorType.RAW, true, false, false, false),
                new Frame("P10 10x15 S"));
    }

    @Test
    public void frame_string_back() {
        assertEquals(
                new Frame("10", 10, 15, ColorType.RAW, false, true, false, false),
                new Frame("P10 10x15 T"));
    }

    @Test
    public void frame_string_hook() {
        assertEquals(
                new Frame("10", 10, 15, ColorType.RAW, false, false, true, false),
                new Frame("P10 10x15 Z"));
    }

    @Test
    public void frame_string_badges() {
        assertEquals(
                new Frame("10", 10, 15, ColorType.RAW, false, false, false, true),
                new Frame("P10 10x15 f"));
    }

    @Test
    public void frame_string_all_stuff() {
        assertEquals(
                new Frame("10", 10, 15, ColorType.WORN_OUT, true, true, true, true),
                new Frame("P10 10x15 P STZf"));
    }
}
