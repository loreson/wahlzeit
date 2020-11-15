package org.wahlzeit.model;

class Location
{
    public Location()
    {
        coordinate = new Coordinate();
    }
    public Location(Coordinate coordinate)
    {
        this.setCoordinate(coordinate);
    }
    public Coordinate getCoordinate()
    {
        return new Coordinate(this.coordinate);
    }

    public void setCoordinate(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }
    private Coordinate coordinate;

}
