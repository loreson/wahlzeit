package org.wahlzeit.model;

abstract class AbstractCoordinate implements Coordinate
{
    @Override
    public double getCartesianDistance(Coordinate other) {
        return this.asCartesianCoordinate().getCartesianDistance(other);
    }
    @Override
    public double getCentralAngle(Coordinate other){
        return this.asSphericCoordinate().getCentralAngle(other);
    }

    @Override
    public boolean isEqual(Coordinate other)
    {
        return this.asCartesianCoordinate().isEqual(other);
    }

    @Override
    public int hashCode()
    {
        return this.asCartesianCoordinate().hashCode();
    }

    @Override
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
        if (other instanceof AbstractCoordinate)
        {
            return isEqual((Coordinate) other);
        }
        return false;
    }
}