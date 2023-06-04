// --== CS400 Fall 2022 File Header Information ==--
// Name: Mohammud Ibrahim
// Email: Mmibrahim2@wisc.edu
// Team: AE
// TA: Yuye
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.List;

import javax.swing.plaf.basic.BasicLabelUI;
import java.util.Scanner;

/**
 *
 */
public class BackendDeveloperTests {

    private BDCS400Graph<IFlight,Integer> graph;
    FlightBackend<IFlight,Integer> backend = new FlightBackend<>();
    IFlight flight1 = new BDFlight("NYC","Madison",90);
    IFlight flight2 = new BDFlight("Madison","MPLS",45);
    IFlight flight3 = new BDFlight("Chicago","Madison",10);
    IFlight flight4 = new BDFlight("Chicago","MPLS",20);
    IFlight flight5 = new BDFlight("Dallas","Miami",30);
    IFlight flight6 = new BDFlight("Atlanta","MPLS",15);
    IFlight flight7 = new BDFlight("Seattle","MPLS",2);
    IFlight flight8 = new BDFlight("Los Angeles","Miami",4);
    IFlight flight9 = new BDFlight("Seattle","Chicago",2);
    IFlight flight10 = new BDFlight("Los Angeles","Chicago",4);
    IFlight flight11 = new BDFlight("Los Angeles","Chicago",4);
    IFlight flight12 = new BDFlight("Los Angeles","Chicago",4);




    /**
     * Tests display all cities
     */
    @Test
    public void BackendDeveloperTest1() {
        String[] cities = new String[] {"New York","Chicago","Miami","Atlanta","Dallas",
            "Seattle","Los Angeles"};
        String [] expected = backend.DisplayallFlights();
        assertArrayEquals(expected,cities);

    }

    /**
     * Setting up graph and adding vertex that are IFlight nodes
     */
    @Test
    public void BackendDeveloperTest2() {
        assertEquals(0,backend.vertices.size());
        backend.insertVertex(flight1);
        assertEquals(backend.containsVertex(flight1),true);
        backend.insertVertex(flight2);
        assertEquals(2,backend.getVertexCount());

    }


    /**
     * Checking Flight info and adding edges
     */
    @Test
    public void BackendDeveloperTest3() {
        assertEquals("NYC",flight1.getOriginCityName());
        assertEquals("Los Angeles",flight8.getOriginCityName());
        assertEquals(90,flight1.getDistance());
        System.out.println(backend.getVertexCount());
        backend.insertVertex(flight1);
        backend.insertVertex(flight2);
        backend.insertEdge(flight1,flight2,34);
        assertEquals(2,backend.getVertexCount());
    }


    /**
     * Flights too Method from backend
     */
    @Test
    public void BackendDeveloperTest4() {
        // Test getting the flights too MPLS
        backend.insertVertex(flight6);
        backend.insertVertex(flight2);
        backend.insertVertex(flight4);
        backend.insertEdge(flight4,flight2, 32);
        backend.insertEdge(flight4, flight6, 56);
        List<String> flights = backend.Flightto1("MPLS");
        assertEquals(2, flights.size());
        assertTrue(flights.contains("Atlanta"));
        assertTrue(flights.contains("Madison"));

        // Test getting the flights too
        backend.insertVertex(flight9);
        backend.insertVertex(flight10);
        backend.insertEdge(flight9,flight10, 32);
        backend.insertEdge(flight2, flight9, 56);
        flights = backend.Flightto1("Chicago");
        assertEquals(2, flights.size());
        assertTrue(flights.contains("Seattle"));
        assertTrue(flights.contains("Los Angeles"));
    }

    /**
     * Flights from Method
     */
    @Test
    public void BackendDeveloperTest5() {
        // Test getting the flights too MPLS
        backend.insertVertex(flight3);
        backend.insertVertex(flight4);
        backend.insertVertex(flight6);
        backend.insertEdge(flight6,flight4, 32);
        backend.insertEdge(flight6, flight3, 56);
        List<String> flights = backend.Flightfrom1("Chicago");
        assertEquals(2, flights.size());
        assertTrue(flights.contains("Madison"));
        assertTrue(flights.contains("MPLS"));

    }



    /**
     * Methods tests formats of Frontend Developer
     */
    @Test
    public void FrontdeveloperPartnerTest1() {
        FlightLoader fl = new FlightLoader();
        try {
            FlightBackend backend = new FlightBackend();
            TextUITester tester = new TextUITester("6\n");
            Scanner scnr = new Scanner(System.in);
            FlightFrontend frontend = new FlightFrontend(scnr, backend);
            frontend.runCommandLoop();
            String output = tester.checkOutput();
            assertTrue(output.startsWith("Welcome to Flight Finder Application!"));
            assertTrue(output.contains("You are in the Main Menu:"));
            assertTrue(output.contains("Goodbye!"));
        } catch (Exception e) {
            System.out.println("ERROR: FD Partner");
        }
    }


    /**
     * Test flight too
     */
    @Test
    public void FrontdeveloperPartnerTest2() {
        FlightLoader fl = new FlightLoader();
        try {
            FlightBackend backend = new FlightBackend();
            TextUITester tester = new TextUITester("4\n");
            Scanner scnr = new Scanner(System.in);
            FlightFrontend frontend = new FlightFrontend(scnr, backend);
            frontend.runCommandLoop();
            String output = tester.checkOutput();
            assertTrue(output.startsWith("Welcome to Flight Finder Application!"));
            assertTrue(output.contains("You are in the Main Menu:"));
            assertTrue(output.contains("Flights to City Menu;"));
        } catch (Exception e) {
            System.out.println("ERROR: FD Partner");
        }
    }

         /**
     *
     */
    @Test
    public void IntegrationTest1() {
        ArrayList<String> cities = new ArrayList<String>();
        FlightLoader loader = new FlightLoader();
        try {
            FlightLoader fi = new FlightLoader();
            String file = "flights.dot";

            // load
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
            IFlightBackend backend1 = new FlightBackend();
            for (int i = 0; i < cities.size(); i++) {
                backend1.addVertex(cities.get(i));
            }
            for (int i = 0; i < list.size(); i++) {
                backend1.addEdge(list.get(i).getOriginCityName(),
                    list.get(i).getDestinationCityName(),
                    list.get(i).getDistance());
            }
	    assertTrue(backend1.getShortestPath("Chicago","New York").equals("[Chicago, Los Angeles, New York]"));
            assertTrue(backend1.getShortestPath("Miami","Chicago").equals("[Miami, New York, Chicago]"));
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

  
    /**
     * Test from Chicago
     */
    @Test
    public void IntegrationTest2() {
        ArrayList<String> cities = new ArrayList<String>();
        FlightLoader loader = new FlightLoader();
        try {
            FlightLoader fi = new FlightLoader();
            String file = "flights.dot";

            // load
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
            IFlightBackend backend1 = new FlightBackend();
            for (int i = 0; i < cities.size(); i++) {
                backend1.addVertex(cities.get(i));
            }
            for (int i = 0; i < list.size(); i++) {
                backend1.addEdge(list.get(i).getOriginCityName(),
                    list.get(i).getDestinationCityName(),
                    list.get(i).getDistance());
            }
	    String s = backend1.Flightfrom("Chicago").toString();
            assertTrue(s.contains("Dallas"));
	    assertTrue(s.contains("Los Angeles"));
        } catch (Exception e) {
            System.out.println("Error");
        }

    }
}
