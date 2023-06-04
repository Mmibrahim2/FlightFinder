import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class main method to run IFlight mapper app
 * 
 */

public class FlightMapper {
  private static ArrayList<String> cities = new ArrayList<String>();

  public static void main(String[] args) {
    try {
      FlightLoader fi = new FlightLoader();
      String file = "flights.dot";
      List<IFlight> list = fi.loadFlights(file);
      for (int i = 0; i < list.size(); i++) {
        IFlight currFlight = list.get(i);
        boolean inCities = false;
        for (int j = 0; j < cities.size(); j++) {
          if (currFlight.getOriginCityName().equals(cities.get(j))) {
            inCities = true;
          }
        }
        if (!inCities) {
          cities.add(currFlight.getOriginCityName());
        }
      }
      IFlightBackend backend = new FlightBackend();
      for (int i = 0; i < cities.size(); i++) {
        backend.addVertex(cities.get(i));
      }
      for (int i = 0; i < list.size(); i++) {
        backend.addEdge(list.get(i).getOriginCityName(), list.get(i).getDestinationCityName(),
            list.get(i).getDistance());
      }
      Scanner userInputScanner = new Scanner(System.in);
      IFlightFrontend frontend = new FlightFrontend(userInputScanner, backend);
      frontend.runCommandLoop();
    } catch (Exception e) {
      System.out.println("Error in FlightMapper when starting program");
    }
  }
}
