package org.wahlzeit.model;
import java.lang.Math;
class Coordinate
{
    public Coordinate()
    {
        x = 0;
        y = 0;
        z = 0;
    }
    public Coordinate(double xyz)
    {
        x = xyz;
        y = xyz;
        z = xyz;
    }
    public Coordinate(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coordinate(Coordinate other)
    {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    private double x;
    private double y;
    private double z;
    
    /**
     * Returns the coordinates as an array of the form
     * {x,y,z}
     * @methodtype conversion method
     * @methodproperties primitive
     */
    public double[] asArray()
    {
        double[] values = new double[3];
        values[0] = x;
        values[1] = y;
        values[2] = z;
        return values;
    }

    /**
     * Sets the coordinates equal to the ones of other.
     * @methodtype set method
     * @methodproperties composed
     */
    public void setCoordinate(Coordinate other)
    {
        this.setX(other.getX());
        this.setY(other.getY());
        this.setZ(other.getZ());
    }

    /**
     * Sets the coordinates to values of an array.
     * @methodtype set method
     * @methodproperties composed
     */
    public void setCoordinate(double[] values)
    {
        this.setX(values[0]);
        this.setY(values[1]);
        this.setZ(values[2]);
    }

    /**
     * Sets the X coordinate.
     * @methodtype set method
     * @methodproperties primitive
     */
    public void setX(double x)
    {
        this.x = x;
    }

    /**
     * Returns  the X coordinate.
     * @methodtype get method
     * @methodproperties primitive
     */
    public double getX()
    {
        return x;
    }

    /**
     * Sets the Y coordinate.
     * @methodtype set method
     * @methodproperties primitive
     */
    public void setY(double y)
    {
        this.y = y;
    }
    
    /**
     * Returns  the Y coordinate.
     * @methodtype get method
     * @methodproperties primitive
     */
    public double getY()
    {
        return y;
    }

    /**
     * Sets the Z coordinate.
     * @methodtype set method
     * @methodproperties primitive
     */
    public void setZ(double z)
    {
        this.z = z;
    }

    /**
     * Returns  the Z coordinate.
     * @methodtype get method
     * @methodproperties primitive
     */
    public double getZ()
    {
        return z;
    }


    public double getDistance(Coordinate other)
    {
        double dx = x - other.x;
        double dy = y - other.y;
        double dz = z - other.z;
        double square_dist = dx*dx + dy+dy +dz*dz;
        return Math.sqrt(square_dist);
    }

    public boolean isEqual(Coordinate other)
    {
        return x == other.x && y == other.y && z == other.z;
    }
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (other == null)
        {
            return false;
        }
        if(this.getClass() == other.getClass())
        {
            return this.isEqual((Coordinate)other);
        }
        return false;
    }
}