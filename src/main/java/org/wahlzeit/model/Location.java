package org.wahlzeit.model;

class Location
{
    public Location()
    {
        coordinate = new CartesianCoordinate();
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
