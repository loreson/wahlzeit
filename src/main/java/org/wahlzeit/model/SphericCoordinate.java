package org.wahlzeit.model;
import java.lang.Math;
import java.lang.IllegalStateException;
class SphericCoordinate extends AbstractCoordinate
{
    private final double phi;
    private final double theta;
    private final double radius;


    protected SphericCoordinate(double phi, double theta, double radius)
    {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
        assertClassInvariants();
    }
    public double getPhi()
    {
        assertClassInvariants();
        return phi;
    }

    public SphericCoordinate setPhi(double phi)
    {
        assertClassInvariants();
        assertArgumentFinite(phi);
        return new SphericCoordinate(phi, this.theta, this.radius);
        
    }
    public double getTheta()
    {
        assertClassInvariants();
        return theta;
    }

    public SphericCoordinate setTheta(double theta)
    {
        assertClassInvariants();
        assertArgumentFinite(theta);
        return new SphericCoordinate(this.phi, theta, this.radius);
    }
    public double getRadius()
    {
        assertClassInvariants();
        return radius;
    }

    public SphericCoordinate setRadius(double radius)
    {
        assertClassInvariants();
        assertArgumentFinite(radius);
        return new SphericCoordinate(this.phi, this.theta, radius);
    }

    @Override
    protected SphericCoordinate toSpheric()
    {
        return this;
    }
    @Override
    protected CartesianCoordinate toCartesian()
    {
        assertClassInvariants();
        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);
        CartesianCoordinate coord = new CartesianCoordinate(x, y, z);
        assert coord != null :"Constructor of CartesianCoordinate failed";
        return coord;
    }


    private double hav(double x)
    {
        return  Math.sin(x/2) * Math.sin(x/2);
    }

    private double ahav(double x)
    {
        return 2 * Math.asin(Math.sqrt(x));
    }
    public double getCentralAngle(Coordinate other)
    {
        SphericCoordinate o = other.asSphericCoordinate();
        double dTheta = Math.abs(theta - o.theta);
        double dPhi = Math.abs(phi - o.phi);
        return ahav(hav(dPhi) + Math.cos(phi) * Math.cos(o.phi) * hav(dTheta));
    }
    @Override
    protected void assertClassInvariants()
    {
        if(! Double.isFinite(radius) || !Double.isFinite(phi)|| !Double.isFinite(theta))
        {
            throw  new IllegalStateException("Non-Finite coordinate");
        }
        if( radius < 0)
        {
            throw new IllegalStateException("negative Radius");
        }
        if(phi < 0 || phi > 2*Math.PI)
        {
            throw new IllegalStateException("Phi out of bounds");
        }
        if(theta <0 || theta > Math.PI)
        {
            throw new IllegalStateException("Theta out of bounds");
        }
        return;
    }

}