import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static org.junit.Assert.fail;
import java.io.FileNotFoundException;

class AlgorithmEngineerTests {

  private CS400Graph<String, Integer> graph;
  private List<String> expectedMiamiMST;
  private List<String> expectedDallasMST;
  @BeforeEach
  public void createGraph() {
    // Sets up the graph that the methods will be working on
    graph = new CS400Graph<>();
    graph.insertVertex("Miami");
    graph.insertVertex("Atlanta");
    graph.insertVertex("Chicago");
    graph.insertVertex("New York");
    graph.insertVertex("Los Angeles");
    graph.insertVertex("Dallas");
    graph.insertVertex("Seattle");
    
    graph.insertEdge("Miami", "New York", 1090);
    graph.insertEdge("Miami", "Seattle", 2724);
    graph.insertEdge("Atlanta", "Chicago", 606);
    graph.insertEdge("Atlanta", "Miami", 595);
    graph.insertEdge("Chicago", "Los Angeles", 1745);
    graph.insertEdge("Chicago", "Dallas", 802);
    graph.insertEdge("New York", "Chicago", 740);
    graph.insertEdge("New York", "Atlanta", 760);
    graph.insertEdge("Los Angeles", "Seattle", 954);
    graph.insertEdge("Los Angeles", "New York", 2475);
    graph.insertEdge("Dallas", "Los Angeles", 1235);
    graph.insertEdge("Seattle", "Chicago", 1724);

    // Sets up an expected MST to be used by tester methods that starts in Miami
    expectedMiamiMST = new LinkedList<String>();
    expectedMiamiMST.add("Miami -> New York");
    expectedMiamiMST.add("New York -> Chicago");
    expectedMiamiMST.add("New York -> Atlanta");
    expectedMiamiMST.add("Chicago -> Dallas");
    expectedMiamiMST.add("Dallas -> Los Angeles");
    expectedMiamiMST.add("Los Angeles -> Seattle");
    
 // Sets up an expected MST to be used by tester methods that starts in Dallas
    expectedDallasMST = new LinkedList<String>();
    expectedDallasMST.add("Dallas -> Los Angeles");
    expectedDallasMST.add("Los Angeles -> Seattle");
    expectedDallasMST.add("Seattle -> Chicago");
    expectedDallasMST.add("Los Angeles -> New York");
    expectedDallasMST.add("New York -> Atlanta");
    expectedDallasMST.add("Atlanta -> Miami");
  }
  
  /**
   * Gets the MST from the graph class starting in cities Miami and Dallas and makes sure
   * the returning MST has the correct size and string elements
   */
  @Test
  public void testExpectedMST() {
    // Checks that getting the MST starting at Miami has correct size and elements
    List<String> miamiList = graph.getMST("Miami");
    assertTrue(miamiList.size() == 6);
    for (int i = 0; i < miamiList.size(); i++) {
      assertTrue(miamiList.get(i).equals(expectedMiamiMST.get(i)));
    }
    
    // Checks that getting the MST starting at Dallas has correct size and elements
    List<String> dallasList = graph.getMST("Dallas");
    assertTrue(dallasList.size() == 6);
    for (int i = 0; i < dallasList.size(); i++) {
      assertTrue(dallasList.get(i).equals(expectedDallasMST.get(i)));
    }
  }
  
  /**
   * Tests that NoSuchElementException is thrown when trying to get the MST with an invalid
   * starting city and checks that no exception is thrown when starting city is valid
   */
  @Test
  public void testInputCity() {
    List<String> testList;
    // Checks for NoSuchElementException to be thrown with invalid city parameters, Cleveland
    // and Orlando
    try {
      testList = graph.getMST("Cleveland");
      fail("Exception not thrown for vertex not in graph, input: Cleveland");
    } catch (NoSuchElementException nsee) { }
    try {
      testList = graph.getMST("Orlando");
      fail("Exception not thrown for vertex not in graph, input: Orlando");
    } catch (NoSuchElementException nsee) { }
    
    // Checks that no exception is thrown for valid city input
    try {
      testList = graph.getMST("Los Angeles");
    } catch (Exception e) {
      fail("Unexpected exception thrown for valid graph input");
    }
  }
  
  /**
   * Tests adding a new city and edge to the graph and making sure that the correct spanning
   * tree is returned
   */
  @Test
  public void testNewCity() {
    // Tests that when new city is added, getMST works but returns a list with no elements
    graph.insertVertex("Cleveland");
    List<String> testList = graph.getMST("Cleveland");
    assertTrue(testList.size() == 0);
    
    // Checks that adding Cleveland to Miami edge results in the correct spanning tree
    // Reusing the expectedMiamiMST since that will be the same tree except for the starting
    // Cleveland -> Miami edge.
    graph.insertEdge("Cleveland", "Miami", 1);
    testList = graph.getMST("Cleveland");
    assertTrue(testList.get(0).equals("Cleveland -> Miami"));
    for (int i = 1; i < testList.size(); i++) {
      assertTrue(testList.get(i).equals(expectedMiamiMST.get(i - 1)));
    }
  }
  
  /**
   * Tests that the correct new MST is returned after changing the edges in the graph, such
   * as adding new ones to create shorter flights ebtween cities
   */
  @Test
  public void changingEdges() {
    // Adds an edge between Miami and Seattle and checks that new MST is what is expected
    graph.insertEdge("Miami", "Seattle", 1);
    expectedMiamiMST.add(0, "Miami -> Seattle");
    expectedMiamiMST.remove(6);
    List<String> testList = graph.getMST("Miami");
    for (int i = 0; i < testList.size(); i++) {
      assertTrue(testList.get(i).equals(expectedMiamiMST.get(i)));
    }
    
    // Tests the resulting MST after adding another edge and the above edge
    graph.insertEdge("Dallas", "Miami", 1);
    testList = graph.getMST("Dallas");
    assertTrue(testList.get(0).equals("Dallas -> Miami"));
    assertTrue(testList.get(1).equals("Miami -> Seattle"));
    assertTrue(testList.get(2).equals("Miami -> New York"));
    assertTrue(testList.get(3).equals("New York -> Chicago"));
    assertTrue(testList.get(4).equals("New York -> Atlanta"));
    assertTrue(testList.get(5).equals("Dallas -> Los Angeles"));
  }
  
  /**
   * Removes cities from the graph and tests that the MST method still works and returns a
   * minimum spanning tree with all the vertices and the correct edges linking them
   */
  @Test
  public void removingCities() {
    // Removes Los Angeles from the graph and tests the MST resulting after the removal
    graph.removeVertex("Los Angeles");
    List<String> testList = graph.getMST("Miami");
    assertTrue(testList.size() == 5);
    assertTrue(testList.get(0).equals("Miami -> New York"));
    assertTrue(testList.get(1).equals("New York -> Chicago"));
    assertTrue(testList.get(2).equals("New York -> Atlanta"));
    assertTrue(testList.get(3).equals("Chicago -> Dallas"));
    assertTrue(testList.get(4).equals("Miami -> Seattle"));
    
    // Removes Dallas and Los Angeles from the graph and tests the MST resulting after the removal
    graph.removeVertex("Dallas");
    testList = graph.getMST("Miami");
    assertTrue(testList.size() == 4);
    assertTrue(testList.get(0).equals("Miami -> New York"));
    assertTrue(testList.get(1).equals("New York -> Chicago"));
    assertTrue(testList.get(2).equals("New York -> Atlanta"));
    assertTrue(testList.get(3).equals("Miami -> Seattle"));
  }
  
  /**
   * Tests that the shortest path returned by the program is correct
   */
  @Test
  public void integrationTest1() {
   
 // Simulates running the program to be used for integration/partner tests
    ArrayList<String> cities = new ArrayList<String>();
    FlightLoader loader = new FlightLoader();
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
      // Tests Shortest Paths between cities
      assertTrue(backend.getShortestPath("Miami", "New York").equals("[Miami, New York]"));
      assertTrue(backend.getShortestPath("Miami", "Atlanta").equals("[Miami, New York, Atlanta]"));
      assertTrue(backend.getShortestPath("Dallas", "Los Angeles").equals("[Dallas, Los Angeles]"));
assertTrue(backend.getShortestPath("Los Angeles", "Dallas").equals("[Los Angeles, Seattle, Chicago, Dallas]"));    
} catch (Exception e) {
      System.out.println("Error in FlightMapper when starting program");
    }
  }
  
  /**
   * Tests that the MST from Prim's algorithm is correct after integration
   */
  @Test
  public void integrationTest2() {
    ArrayList<String> cities = new ArrayList<String>();
    FlightLoader loader = new FlightLoader();
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
      // Tests Shortest Paths between cities
      List<String> retList = backend.getMST("Miami");
      assertTrue(retList.size() == 6);
      for (int i = 0; i < retList.size(); i++) {
        assertTrue(retList.get(i).equals(expectedMiamiMST.get(i)));
      }
retList = backend.getMST("Dallas");
      assertTrue(retList.size() == 6);
      for (int i = 0; i < retList.size(); i++) {
        assertTrue(retList.get(i).equals(expectedDallasMST.get(i)));
      }
    } catch (Exception e) {
      System.out.println("Error in FlightMapper when starting program");
    }
  }

  /**
   * Tests that two correct flights from the flights.dot are returned from flight loader
   * in the list
   */
  @Test
  public void CodeReviewOfDataWrangler1() {
    FlightLoader loader = new FlightLoader();
    try {
      List<IFlight> list = loader.loadFlights("flights.dot");
      // Tests that the 12th flight in the list is what's expected
      assertTrue(list.get(11).getOriginCityName().equals("Seattle"));
      assertTrue(list.get(11).getDestinationCityName().equals("Chicago"));
      assertTrue(list.get(11).getDistance() == 1721);
      
      // Tests that the 11th flight in the list is what's expected
      assertTrue(list.get(10).getOriginCityName().equals("Dallas"));
      assertTrue(list.get(10).getDestinationCityName().equals("Los Angeles"));
      assertTrue(list.get(10).getDistance() == 1235);

    } catch (Exception e) {
      System.out.println("Unexpected Exception in CodeReviewOfDataWrangler1");
    }
  }

  /**
   * Tests the input file for flight loader to make sure it throws File not found error
   * with incorrect file
   */
  @Test
  public void CodeReviewOfDataWrangler2() {
    FlightLoader loader = new FlightLoader();
    try {
      List<IFlight> list = loader.loadFlights("incorrect.dot");
      fail("flightLoader didn't throw expected error");
    }
    catch (FileNotFoundException e) { }
    try {
      List<IFlight> list = loader.loadFlights("flights.DOt");
      fail("flightLoader didn't throw expected error");
    } catch (FileNotFoundException e) { }
    try {
      List<IFlight> list = loader.loadFlights("flights.txt");
      fail("flightLoader didn't throw expected error");
    } catch (FileNotFoundException e) { }
  }

  }
