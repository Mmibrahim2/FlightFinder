// --== CS400 Fall 2022 File Header Information ==--
// Name: Mohammud Ibrahim
// Email: Mmibrahim2@wisc.edu
// Team: AE
// TA: Yuye
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;


public class FlightBackend<NodeType, EdgeType extends Number> extends CS400Graph<NodeType, EdgeType>
    implements IFlightBackend {

  private String[] cities =
      new String[] {"New York", "Chicago", "Miami", "Atlanta", "Dallas", "Seattle", "Los Angeles"};

  private IPrimMST<String, Integer> graph = new CS400Graph<>();



  /**
   * This is a method to display all Flights
   * 
   * @return List of all cities in graph
   */
  @Override
  public String[] DisplayallFlights() {
    return cities;
  }



  /**
   * This method finds all outgoing edges for a given node
   * 
   * @param city name of city were looking for
   * @return List of IFlight containing all the flights that come from a cityv
   */
  @Override
  public List<String> Flightto(String city) {
    // Check if the city exists in the graph
      // Iterate over the edges leaving each vertex
          // If the origin city of the flight matches the given city, add the destination city to
          // the list of flights
	  List<String> flights = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            if (graph.containsEdge(cities[i],city)) {
                flights.add(cities[i]);
            }
        }
     return flights;
    }

 


  /**
   * For old testers
   */
 
  public List<String> Flightto1(String city) {
        // Check if the city exists in the graph
        List<String> flights = new ArrayList<>();
        // Iterate over the vertices in the graph
        for (Vertex v : vertices.values()) {
            // Iterate over the edges leaving each vertex
            if (!v.edgesLeaving.isEmpty()) {
                for (Edge e : v.edgesLeaving) {
                    // If the origin city of the flight matches the given city, add the destination city to
                    // the list of flights
                    String s = e.target.data.toString().split(", ")[1];
                    if (s.equals(city)) {
                        String g = e.target.data.toString().split(", ")[0];
                        flights.add(g);
                    }
                }
            }
        }
        for (int i = 0; i < flights.size(); i++) {
            flights.set(i, flights.get(i).substring(1));
        }
        return flights;
    }

  /**
   * Finds flights from a given city
   * 
   * @param city user input
   * @return a list of flights
   */
  @Override
  public List Flightfrom(String city) {
  // Check if the city exists in the graph    
  // Iterate over the vertices in the graph
    List<String> flights = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            if (graph.containsEdge(city,cities[i])) {
                flights.add( cities[i]);
            }
        }
     return flights;
    }


  /**
     * Finds flights from a given city
     * testers
     * @param city user input
     * @return a list of flights
     */
    public List Flightfrom1(String city) {
        // Check if the city exists in the graph
        List<String> flights = new ArrayList<>();
        // Iterate over the vertices in the graph
        for (Vertex v : vertices.values()) {
            // Iterate over the edges leaving each vertex
            String f = v.data.toString().split("\\[")[1];
            String s = f.split(", ")[0];
                    if (s.equals(city)) {
                        String g = v.data.toString().split(", ")[1];
                        System.out.println(g);
                        flights.add(g);
                    }
                }
        return flights;
    }



  /**
   *
   * @param starting
   * @param ending
   * @return
   */
  @Override
  public String getShortestPath(String starting, String ending) {
    return graph.shortestPath(starting, ending).toString();
  }

  /**
   *
   * @param starting
   * @return
   */
  @Override
  public List getMST(String starting) {
    return graph.getMST(starting);
  }
  @Override
  public void addVertex(String vertex) {
    graph.insertVertex(vertex);
  }
  @Override
  public void addEdge(String starting, String ending, int distance) {
    graph.insertEdge(starting, ending, distance);
  }

}
