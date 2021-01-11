package org.wahlzeit.model;

class Location
{
    public Location()
    {
        coordinate = AbstractCoordinate.getCoordinateFromCart(0, 0, 0);
    }
    public Location(Coordinate coordinate)
    {
        this.setCoordinate(coordinate);
    }
    public Coordinate getCoordinate()
    {
        return this.coordinate;
    }

    public void setCoordinate(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }
    private Coordinate coordinate;

}
