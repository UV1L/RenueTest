package net.noname.testapp.model;

import java.util.Arrays;
import java.util.List;

public class AirportModel {

    private final List<String> airportArray;

    public AirportModel(List<String> airportArray) {
        this.airportArray = airportArray;
    }

    @Override
    public String toString() {
        return Arrays.toString(airportArray.toArray())
                .replaceAll("\\[", "")
                .replaceAll("]", "");
    }
}
