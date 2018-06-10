package com.tetris.enums;

import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {


    @Test
    public void testUPX() {
        assertEquals(Direction.UP.getX(),0);
    }

    @Test
    public void testUPY() {
        assertEquals(Direction.UP.getY(),1);
    }

    @Test
    public void testDOWNX() {
        assertEquals(Direction.DOWN.getX(),0);
    }

    @Test
    public void testDOWNY() {
        assertEquals(Direction.DOWN.getY(),-1);
    }

    @Test
    public void testLEFTX() {
        assertEquals(Direction.LEFT.getX(),-1);
    }

    @Test
    public void testLEFTY() {
        assertEquals(Direction.LEFT.getY(),0);
    }

    @Test
    public void testRIGHTX() {
        assertEquals(Direction.RIGHT.getX(),1);
    }

    @Test
    public void testRIGHTY() {
        assertEquals(Direction.RIGHT.getY(),0);
    }

}