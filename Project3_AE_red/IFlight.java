/**
 * This interface defines some getter methods for each flights data attributes and is implemented by classes that
 * represent a flight and its associated data.
 */
public interface IFlight {
    /**
     * Returns the distance of the flight in miles
     * @return the distance of the flight
     */
    int getDistance();

    /**
     * Returns the name of the origin city
     * @return the name of the origin city
     */
    String getOriginCityName();

    /**
     * Returns the name of the destination city
     * @return the name of the destination city
     */
    String getDestinationCityName();
}
