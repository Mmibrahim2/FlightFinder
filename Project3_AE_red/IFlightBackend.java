// --== CS400 Fall 2022 File Header Information ==--
// Name: Mohammud Ibrahim
// Email: Mmibrahim2@wisc.edu
// Team: AE
// TA: Yuye
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.List;

/**
 * This interface is used by FlightBackend
 * @author Mohammud Ibrahim
 */
public interface IFlightBackend<NodeType, EdgeType extends Number>  {

    /**
     * Method for finding shortest path from the shortest
     * @param starting
     * @param ending
     * @return
     */
    public String getShortestPath(String starting,String ending);

    /**
     * 
     * @param starting
     * @return
     */
    public List<String> getMST(String starting);

    /**
     * Displays all city available
     *
     */
    // display all flights in tree
    public String[] DisplayallFlights();

    /**
     * Displays all flights that connect to a given city
     *
     */
    public List Flightto(String city);
    /**
     * Displays all flights from a given city
     *
     */
    public List Flightfrom(String city);
    
    public void addVertex(String vertex);
    
    public void addEdge(String starting, String ending, int distance);
}
