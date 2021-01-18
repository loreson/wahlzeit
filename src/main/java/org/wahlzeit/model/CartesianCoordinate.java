package org.wahlzeit.model;
import java.lang.Math;
import java.util.ArrayList;
import org.wahlzeit.utils.PatternInstance;

class CartesianCoordinate extends AbstractCoordinate{
    @PatternInstance(name = "Object pool", type = "Creational pattern", participants = {"CartesianCoordinate"})
    private static ArrayList<CartesianCoordinate> allCoordinates = new ArrayList<CartesianCoordinate>();

    public static CartesianCoordinate getCartesian(double x, double y, double z)
    {
        CartesianCoordinate coord = new CartesianCoordinate(x, y, z);
        for(CartesianCoordinate existing: allCoordinates)
        {
            if(coord.equals(existing)){
                coord = null;
                return(existing);
            }
        }
        allCoordinates.add(coord);
        return coord;
    }
    protected CartesianCoordinate(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        assertClassInvariants();
    }

    private final double x;
    private final double y;
    private final double z;

    /**
     * Returns the coordinates as an array of the form
     * {x,y,z}
     * @methodtype conversion method
     * @methodproperties primitive
     */
    public double[] asArray()
    {
        assertClassInvariants();
        double[] values = new double[3];
        values[0] = x;
        values[1] = y;
        values[2] = z;
        assertClassInvariants();
        return values;
    }

  
    /**
     * Sets the X coordinate.
     * @methodtype set method
     * @methodproperties primitive
     */
    public CartesianCoordinate setX(double x)
    {
        assertClassInvariants();
        assertArgumentFinite(x);
        return getCartesian(x, this.y, this.z);
    }

    /**
     * Returns  the X coordinate.
     * @methodtype get method
     * @methodproperties primitive
     */
    public double getX()
    {
        assertClassInvariants();
        return x;
    }

    /**
     * Sets the Y coordinate.
     * @methodtype set method
     * @methodproperties primitive
     */
    public CartesianCoordinate setY(double y)
    {
        assertClassInvariants();
        assertArgumentFinite(y);

        return getCartesian(this.x, y, this.z);
    }

    /**
     * Returns  the Y coordinate.
     * @methodtype get method
     * @methodproperties primitive
     */
    public double getY()
    {
        assertClassInvariants();
        return y;
    }

    /**
     * Sets the Z coordinate.
     * @methodtype set method
     * @methodproperties primitive
     */
    public CartesianCoordinate setZ(double z)
    {
        assertClassInvariants();
        assertArgumentFinite(z);
        return getCartesian(this.x, this.y, z);

    }

    /**
     * Returns  the Z coordinate.
     * @methodtype get method
     * @methodproperties primitive
     */
    public double getZ()
    {
        assertClassInvariants();
        return z;
    }

    public CartesianCoordinate asCartesianCoordinate()
    {
        assertClassInvariants();
        return this;
    }

    public SphericCoordinate asSphericCoordinate()
    {
        assertClassInvariants();
        double radius = Math.sqrt(x*x + y*y + z*z);
        double theta = Math.acos(z/radius);
        double phi = Math.atan2(x, y);
        SphericCoordinate coord = SphericCoordinate.getSpheric(phi, theta, radius);
        assert coord != null :"getSpheric failed";
        return coord;
    }
    public double getCartesianDistance(Coordinate other)
    {
        return getDistance(other.asCartesianCoordinate());
    }
    public double getDistance(CartesianCoordinate other)
    {
        assertClassInvariants();
        assertArgumentNotNull(other);
        double dx = x - other.x;
        double dy = y - other.y;
        double dz = z - other.z;
        double square_dist = dx*dx + dy+dy +dz*dz;
        double dist = Math.sqrt(square_dist);
        assert dist >= 0:"Distance must be non-negative";
        assertClassInvariants();
        return dist;
    }

    public double getCentralAngle(Coordinate other)
    {
        assertArgumentNotNull(other);
        assertClassInvariants();
        double a = asSphericCoordinate().getCentralAngle(other);
        assert Double.isFinite(a): "Central angle must be finite";
        assertClassInvariants();
        return a;
    }
    public boolean isEqual(Coordinate other)
    {
        assertClassInvariants();
        assertArgumentNotNull(other);
        CartesianCoordinate o = other.asCartesianCoordinate();
        double dx = Math.abs(x - o.x);
        double dy = Math.abs(y - o.y);
        double dz = Math.abs(z - o.z);
        boolean e =  dx + dy + dz < 0.1;
        assertClassInvariants();
        return e;
    }
    @Override
    public int hashCode()
    {
        assertClassInvariants();
        int hashX = Double.valueOf(this.x).hashCode();
        int hashY = Double.valueOf(this.y).hashCode();
        int hashZ = Double.valueOf(this.z).hashCode();
        return hashX ^ hashY ^ hashZ;
    }
    @Override
    protected void assertClassInvariants()
    {
       
        if( !Double.isFinite(x) || !Double.isFinite(y) || !Double.isFinite(z))
        {
            throw new IllegalStateException("Nonfinite Coordinate");
        }
        
        return;
    }
}
