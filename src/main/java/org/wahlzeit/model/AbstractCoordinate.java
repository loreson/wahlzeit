package org.wahlzeit.model;

abstract class AbstractCoordinate implements Coordinate
{
    @Override
    public double getCartesianDistance(Coordinate other) {
        assertArgumentNotNull(other);
        assertClassInvariants();
        double d = this.asCartesianCoordinate().getCartesianDistance(other);
        assert d >=0 : "Distance has to be positive";
        assertClassInvariants();
        return d;
    }
    @Override
    public double getCentralAngle(Coordinate other){
        assertArgumentNotNull(other);
        assertClassInvariants();
        double a = this.asSphericCoordinate().getCentralAngle(other);
        assert Double.isFinite(a) : "Central Angle has to be finite";
        assertClassInvariants();
        return a;
    }

    @Override
    public boolean isEqual(Coordinate other)
    {   
        assertArgumentNotNull(other);
        boolean e = this.asCartesianCoordinate().isEqual(other);
        assertClassInvariants();
        return e;
    }

    @Override
    public int hashCode()
    {
        assertClassInvariants();
        int h = this.asCartesianCoordinate().hashCode();
        assertClassInvariants();
        return h;
    }

    @Override
    public boolean equals(Object other)
    {
        assertClassInvariants();
        boolean b;
        if (this == other)
        {
            b = true;
        }
        else if (other == null)
        {
            b = false;
        }
        else if (other instanceof AbstractCoordinate)
        {
            b = isEqual((Coordinate) other);
        }
        else
        {
            b = false;
        }
        assertClassInvariants();
        return b;
    }
    protected abstract void assertClassInvariants();

    protected void assertArgumentFinite(double arg)
    {
        if(!Double.isFinite(arg))
        {
            throw new IllegalArgumentException("argument is not finite");
        }
        else
        {
            return;
        }
    }
    protected void assertArgumentNotNull(Coordinate other)
    {
        if(other == null)
        {
            throw new IllegalArgumentException("Method called with null parameter");
        }
        
    }
    
}

    