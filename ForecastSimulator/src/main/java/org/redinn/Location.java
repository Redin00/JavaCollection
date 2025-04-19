package org.redinn;

public class Location {

    // Class used to create a location with a name and coordinates chosen by the user

    private String name;

    // Array for the coordinates (which will be latitude and longitude respectively)
    private double[] coordinates;

    public Location(String name, double[] coordinate) {
        this.name = name;
        this.coordinates = coordinate;
    }

    // Getter methods

    public String getName(){
        return name;
    }

    public String getCoordinates(){
        return ColorText.BLUE + coordinates[0] + ", " + coordinates[1];
    }

    // Setter methods

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(double[] coordinates){
        this.coordinates = coordinates;
    }


}