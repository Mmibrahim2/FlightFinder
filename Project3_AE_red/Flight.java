public class Flight implements IFlight {
    private String originCity;
    private String destinationCity;
    private int distance;

    /**
     * Initializes name of origin city, destination city and the distance between them (in miles)
     */
    public Flight(String originCity, String destinationCity, int distance){
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.distance = distance;
    }
    /**
     *
     * Returns the distance of the flight in miles
     * @return the distance of the flight
     */
    @Override
    public int getDistance() {
        return distance;
    }

    /**
     * Returns the name of the origin city
     * @return the name of the origin city
     */
    @Override
    public String getOriginCityName() {
        return originCity;
    }

    /**
     * Returns the name of the destination city
     * @return the name of the destination city
     */
    @Override
    public String getDestinationCityName() {
        return destinationCity;
    }
}
