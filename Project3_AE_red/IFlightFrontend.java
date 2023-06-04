import java.util.List;
import java.util.Scanner;

// --== CS400 Project One File Header ==--
// Name: Suyog Rithesh
// CSL Username: suyog
// Email: rithesh@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: none
/**
 * This interface will be used by FlightFrontend.
 * @author suyogrithesh
 *
 */
public interface IFlightFrontend {
    /**
     * The constructor that the implementation of this interface will provide.
     * It takes the Scanner that will read user input as a parameter as well
     * as the backend and Dijkstras implementation.
     */
    // void FlightFrontend(Scanner userInputScanner, IFlightBackend backend);

    /**
     * This method starts the command loop for the frontend,
     * and will terminate when the user exists the app.
     */
        public void runCommandLoop();
    //the following helper methods will support runCommandLoop()


        /**
         * Displays the main menu 
         */
        public void displayMainMenu(); //prints command options to System.out

        /**
         * Displays the shortest path given two input cities
         */
        public void shortestPath(); 

        /**
         * Displays the minimum Spanning Tree
         */
        public void minimumSpanningTree();
        /**
         * Finds all flights from an airport
         */
         public void findFlightFrom();
    
         /**
         * Finds all flights to an airport
         */
         public void findFlightTo();
    
         /**
         * Displays all cities
         */
         public void displayAllCities();


}
