package org.wahlzeit.model;

import org.junit.Test;
import java.lang.Math;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CoordinateTest
{
    @Test
    public void TestDefaultConstructor()
    {
        Coordinate coord = new Coordinate();
        assertEquals(0, coord.getX(), 0.0);
        assertEquals(0, coord.getY(), 0.0);
        assertEquals(0, coord.getZ(), 0.0);
    }

    @Test
    public void TestSingleArgumentConstructor()
    {
        Coordinate coord = new Coordinate(1.0);
        assertEquals(1.0, coord.getX(), 0.0);
        assertEquals(1.0, coord.getY(), 0.0);
        assertEquals(1.0, coord.getZ(), 0.0);
    }

    @Test
    public void TestThreeArgumentConstructor()
    {
        Coordinate coord = new Coordinate(1.0, 2.0, 3.0);
        assertEquals(1.0, coord.getX(), 0.0);
        assertEquals(2.0, coord.getY(), 0.0);
        assertEquals(3.0, coord.getZ(), 0.0);
    }

    @Test
    public void equalsTest()
    {
        Coordinate coord1 = new Coordinate( 1.0, 2.0, 3.0);
        Coordinate coord2 = new Coordinate( 1.0, 2.0, 2.0);
        Coordinate coord3 = new Coordinate(1.0, 3.0, 3.0);
        Coordinate coord4 = new Coordinate(2.0, 2.0, 3.0);
        Coordinate coord5 = new Coordinate( 1.0, 2.0, 3.0);
        assertEquals(coord1, coord1);
        assertEquals(coord1, coord5);
        assertNotEquals(coord1, coord2);
        assertNotEquals(coord1, coord3);
        assertNotEquals(coord1, coord4);
        assertNotEquals(coord1, new Object());
    }

    @Test
    public void hashTest()
    {
        Coordinate coord1 = new Coordinate( 1.0, 2.0, 3.0);
        Coordinate coord2 = new Coordinate( 1.0, 2.0, 3.0);
        Coordinate coord3 = new Coordinate( 1.01, 2.0, 3.0);
        Coordinate coord4 = new Coordinate( 1.0, 2.01, 3.0);
        Coordinate coord5 = new Coordinate( 1.0, 2.0, 3.01);
        assertEquals(coord1.hashCode(), coord2.hashCode());
        assertNotEquals(coord1.hashCode(), coord3.hashCode());
        assertNotEquals(coord1.hashCode(), coord4.hashCode());
        assertNotEquals(coord1.hashCode(), coord5.hashCode());
    }
}