package org.wahlzeit.model;
import java.lang.Math;
class SphericCoordinate implements Coordinate
{
    private double phi;
    private double theta;
    private double radius;


    SphericCoordinate(double phi, double theta, double radius)
    {
        setPhi(phi);
        setTheta(theta);
        setRadius(radius);
    }
    public double getPhi()
    {
        return phi;
    }

    public void setPhi(double phi)
    {
        this.phi = phi;
    }
    public double getTheta()
    {
        return theta;
    }

    public void setTheta(double theta)
    {
        this.theta = theta;
    }
    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    public SphericCoordinate asSphericCoordinate()
    {
        return this;
    }
    
    public CartesianCoordinate asCartesianCoordinate()
    {
        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);
        return new CartesianCoordinate(x, y, z);
    }
    public double getCartesianDistance(Coordinate other)
    {
        return this.asCartesianCoordinate().getCartesianDistance(other);
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
        //return Math.acos(Math.sin(phi) * Math.sin(o.phi) + Math.cos(phi) * Math.cos(o.phi) *math.cos())
    }

    public boolean isEqual(Coordinate other)
    {
        SphericCoordinate o = other.asSphericCoordinate();
        return getCentralAngle(o) * radius < 0.1 && Math.abs(radius - o.radius) < 0.1;
    }

}