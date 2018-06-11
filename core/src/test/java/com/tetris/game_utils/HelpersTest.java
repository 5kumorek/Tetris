package com.tetris.game_utils;

import org.junit.Test;

import static com.tetris.game_utils.Helpers.limit;
import static org.junit.Assert.*;

public class HelpersTest {

    @Test
    public void shouldReturnUpperBound() {
        assertEquals(limit(4,2,4),4);
    }

    @Test
    public void shouldReturnUpperBound2() {
        assertEquals(limit(5,2,4),4);
    }

    @Test
    public void shouldReturnLowerBound() {
        assertEquals(limit(2,2,4),2);
    }

    @Test
    public void shouldReturnLowerBound2() {
        assertEquals(limit(-2,-1,4),-1);
    }

    @Test
    public void shouldReturnNotLimitedNumber() {
        assertEquals(limit(2,-1,4),2);
    }

    @Test
    public void shouldReturnNotLimitedNumber2() {
        assertEquals(limit(-20,-100,400),-20);
    }



    @Test(expected = IllegalArgumentException.class)
    public void shouldThrownIllegalArgumentExceptionOnWrongParameters() {
        int temp = limit(4,4,3);
    }
}
