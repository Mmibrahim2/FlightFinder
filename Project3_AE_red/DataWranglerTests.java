import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.LinkedList;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
public class DataWranglerTests {
    /**
     * This method is tests the Flight class. It checks if the values are being loaded and if getOriginCityName()
     * getter is returning the correct value
     */
    @Test
    public void DataWranglerTest1() {
        try {
            //loads data into Flight
            Flight flight1 = new Flight("Miami", "New York", 1090);
            Flight flight2= new Flight("New York", "Chicago", 740);
            //checks if the getter method is working correctly
            assertEquals(flight1.getOriginCityName(), "Miami");
            assertEquals(flight2.getOriginCityName(), "New York");
        }
        catch(Exception e) {
            fail("Exception thrown");
        }
    }

    /**
     * This method is tests the Flight class. It checks if the values are being loaded and if getDestinationCity()
     * getter is returning the correct value
     */
    @Test
    public void DataWranglerTest2() {
        try {
            //loads data into Flight
            Flight flight1 = new Flight("Miami", "New York", 1090);
            Flight flight2= new Flight("New York", "Chicago", 740);
            //checks if the getter method is working correctly
            assertEquals(flight1.getDestinationCityName(), "New York");
            assertEquals(flight2.getDestinationCityName(), "Chicago");

        }
        catch(Exception e) {
            fail("Exception thrown");
        }
    }

    /**
     * This method is tests the Flight class. It checks if the values are being loaded and if getDistance()
     * getter is returning the correct value
     */
    @Test
    public void DataWranglerTest3() {
        try {
            //loads data into Flight
            Flight flight1 = new Flight("Miami", "New York", 1090);
            Flight flight2= new Flight("New York", "Chicago", 740);
            //checks if the getter method is working correctly
            assertEquals(flight1.getDistance(), 1090);
            assertEquals(flight2.getDistance(), 740);
        }
        catch(Exception e) {
            fail("Exception thrown");
        }
    }

    /**
     * This method tests the functionality of loadFlights() in FlightLoader.java
     */
    @Test
    public void DataWranglerTest4() {
        try {
            FlightLoader load = new FlightLoader();
            //sets the file path to our dot file
            String filePath = "flights.dot";
            List<IFlight> list = new ArrayList<IFlight>();
            list = load.loadFlights(filePath);
            //checks if the file was loaded correctly and gives the correct values
            assertEquals(list.get(8).getOriginCityName(),"Los Angeles");
            assertEquals(list.get(5).getDestinationCityName(),"Dallas");
            assertEquals(list.get(1).getDistance(),2724);
        }
        catch (Exception e) {
            fail("Exception thrown");
        }
    }

    /**
     * This method tests flightLoader() in FlightLoader.java when file is invalid (not found)
     */
    @Test
    public void DataWranglerTest5() {
        IFlightLoader flightLoader = new FlightLoader();
        List<IFlight> list = new ArrayList<IFlight>();
        try {
            //passing incorrect file
            list = flightLoader.loadFlights("random.dot");
            fail("Test 5 failed");
        } catch (FileNotFoundException e) {
            //exception is caught and ensures nothing was added to the list
            assertEquals(list.size(), 0);
        }
    }

     /**
     * This method is tests the Flight class and its methods. Could not use backend's code for integration test since
     * they are still working on the code so I had to test using my classes only.
     */
    @Test
    public void DataWranglerIntegration1(){
        try {
            //loads data into Flight
            Flight flight1 = new Flight("Dallas", "Los Angeles", 1235);
            Flight flight2= new Flight("Seattle", "Chicago", 1721);
            //checks if the getter method is working correctly
            assertEquals(flight1.getOriginCityName(), "Dallas");
            assertEquals(flight2.getDestinationCityName(), "Chicago");
            assertEquals(flight1.getDistance(), 1235);
        }
        catch(Exception e) {
            fail("Exception thrown");
        }
    }

    /**
     * This method tests flightLoader() in FlightLoader.java when file is invalid (not found).
     */
    @Test
    public void DataWranglerIntegration2() {
        IFlightLoader flightLoader = new FlightLoader();
        List<IFlight> list = new ArrayList<IFlight>();
        try {
            //passing incorrect file
            list = flightLoader.loadFlights("nikita.txt");
            fail("Test 2 failed");
        } catch (FileNotFoundException e) {
            //exception is caught and ensures nothing was added to the list
            assertEquals(list.size(), 0);
        }
    }

    /**
     * This method tests insertVertex(), containsVertex(), containsEdge() and insertEdge() in CS400Graph.java that was
     * coded by the Algorithm Engineer
     */
    @Test
    public void CodeReviewOfAlgorithmEngineer1(){
        CS400Graph<String, Integer> graph = new CS400Graph<>();
        //inserts vertices in the graph
        graph.insertVertex("Miami");
        graph.insertVertex("Atlanta");
        graph.insertVertex("Chicago");
        graph.insertVertex("New York");
        //checks if the graph contains the vertices
        assertEquals(graph.containsVertex("Miami"), true);
        assertEquals(graph.containsVertex("Atlanta"), true);
        //inserts edges in the graph
        graph.insertEdge("Atlanta", "Chicago", 606);
        graph.insertEdge("Atlanta", "Miami", 595);
        //checks if the graph contains edges
        assertEquals(graph.containsEdge("Miami", "New York"), false);
        assertEquals(graph.containsEdge("Atlanta", "Miami"), true);
    }

    /**
     * This method tests getMST() in CS400Graph.java that was coded by the Algorithm Engineer
     */
    @Test
    public void CodeReviewOfAlgorithmEngineer2() {
        //creates a graph and adds vertexes, edges
        CS400Graph<String, Integer> graph = new CS400Graph<>();
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
        List<String> atlantaList = graph.getMST("Atlanta");
        assertEquals(atlantaList.size(), 6);
        List<String> atlantaMST = new LinkedList<String>();
        //checks MST of Atlanta
        atlantaMST.add("Atlanta -> Miami");
        atlantaMST.add("Atlanta -> Chicago");
        atlantaMST.add("Chicago -> Dallas");
        atlantaMST.add("Miami -> New York");
        atlantaMST.add("Dallas -> Los Angeles");
        atlantaMST.add("Los Angeles -> Seattle");
        for (int i = 0; i < atlantaList.size(); i++) {
            assertEquals(atlantaList.get(i), atlantaMST.get(i));
        }
    }

}
