package org.wahlzeit.model;

import org.junit.Test;
import java.lang.Math;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CoordinateTest
{
    @Test
    public void TestDefaultConstructor()
    {
        CartesianCoordinate coord = new CartesianCoordinate();
        assertEquals(0, coord.getX(), 0.0);
        assertEquals(0, coord.getY(), 0.0);
        assertEquals(0, coord.getZ(), 0.0);
    }

    @Test
    public void TestSingleArgumentConstructor()
    {
        CartesianCoordinate coord = new CartesianCoordinate(1.0);
        assertEquals(1.0, coord.getX(), 0.0);
        assertEquals(1.0, coord.getY(), 0.0);
        assertEquals(1.0, coord.getZ(), 0.0);
    }

    @Test
    public void TestThreeArgumentConstructor()
    {
        CartesianCoordinate coord = new CartesianCoordinate(1.0, 2.0, 3.0);
        assertEquals(1.0, coord.getX(), 0.0);
        assertEquals(2.0, coord.getY(), 0.0);
        assertEquals(3.0, coord.getZ(), 0.0);
    }

    @Test
    public void equalsTest()
    {
        Coordinate coord1 = new CartesianCoordinate( 1.0, 2.0, 3.0);
        Coordinate coord2 = new CartesianCoordinate( 1.0, 2.0, 2.0);
        Coordinate coord3 = new CartesianCoordinate(1.0, 3.0, 3.0);
        Coordinate coord4 = new CartesianCoordinate(2.0, 2.0, 3.0);
        Coordinate coord5 = new CartesianCoordinate( 1.0, 2.0, 3.0);
        Coordinate coord6 = new CartesianCoordinate(1.05, 2.0, 3.0);

        Coordinate coord7 = new SphericCoordinate(0, 0, 10);
        Coordinate coord8 = new SphericCoordinate(Math.PI, 0, 10);
        Coordinate coord9 = new CartesianCoordinate(0, 0, 10);
        assertEquals(coord1, coord1);
        assertEquals(coord1, coord5);
        assertNotEquals(coord1, coord2);
        assertNotEquals(coord1, coord3);
        assertNotEquals(coord1, coord4);
        assertNotEquals(coord1, new Object());
        assertEquals(coord1, coord6);

        assertEquals(coord7, coord8);
        assertEquals(coord8, coord9);
        assertEquals(coord7, coord9);
    }

    @Test
    public void hashTest()
    {
        Coordinate coord1 = new CartesianCoordinate( 1.0, 2.0, 3.0);
        Coordinate coord2 = new CartesianCoordinate( 1.0, 2.0, 3.0);
        Coordinate coord3 = new CartesianCoordinate( 1.2, 2.0, 3.0);
        Coordinate coord4 = new CartesianCoordinate( 1.0, 2.2, 3.0);
        Coordinate coord5 = new CartesianCoordinate( 1.0, 2.0, 3.2);
        assertEquals(coord1.hashCode(), coord2.hashCode());
        assertNotEquals(coord1.hashCode(), coord3.hashCode());
        assertNotEquals(coord1.hashCode(), coord4.hashCode());
        assertNotEquals(coord1.hashCode(), coord5.hashCode());
    }

    @Test
    public void asCartesianCoordinate()
    {
        CartesianCoordinate cart = new CartesianCoordinate(10, 20, 30);
        CartesianCoordinate cart2 = cart.asCartesianCoordinate();
        assertEquals(cart2.getX(), 10.0, 1e-14);
        assertEquals(cart2.getY(), 20.0, 1e-14);
        assertEquals(cart2.getZ(), 30.0, 1e-14);
        SphericCoordinate sphere = new SphericCoordinate(Math.PI, 0, 10);
        cart = sphere.asCartesianCoordinate();
        assertEquals(0, cart.getX(), 1e-14);
        assertEquals(0, cart.getY(), 1e-14);
        assertEquals(10, cart.getZ(),1e-14);
        sphere = new SphericCoordinate(0, Math.PI/2, 10);
        cart = sphere.asCartesianCoordinate();
        assertEquals(10, cart.getX(), 1e-14);
        assertEquals(0, cart.getY(), 1e-14);
        assertEquals(0, cart.getZ(), 1e-14);
    }

    @Test
    public void centralAngleTest()
    {
        SphericCoordinate coord0 = new SphericCoordinate(0,0,10);
        SphericCoordinate coord1 = new SphericCoordinate(Math.PI, 0, 10);
        SphericCoordinate coord2 = new SphericCoordinate(Math.PI, 0, 5);
        SphericCoordinate coord3 = new SphericCoordinate(0, Math.PI/2, 10);
        CartesianCoordinate coord4 = new CartesianCoordinate(0, 10, 0);
        assertEquals(Math.PI, coord0.getCentralAngle(coord1), 0.01);
        assertEquals(0, coord1.getCentralAngle(coord2), 0.01);
        assertEquals(0, coord3.getCentralAngle(coord4), 0.01);
        assertEquals(0, coord4.getCentralAngle(coord3), 0.01);

    }
}
