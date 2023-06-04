import java.io.FileNotFoundException;
import java.util.List;

/**
 * Instances of this interface can be used to load flight data from a DOT file
 */
public interface IFlightLoader {
    /**
     * This method loads the list of flights from a DOT file.
     * @param filepathToDot path to the DOT file relative to the executable
     * @return a list of flight objects
     * @throws FileNotFoundException
     */
    List<IFlight> loadFlights(String filepathToDot) throws FileNotFoundException;
}
