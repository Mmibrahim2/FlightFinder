// --== CS400 Project One File Header ==--
// Name: Suyog Rithesh
// CSL Username: suyog
// Email: rithesh@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: none
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class will be used to display menu options to the user. It contains all the methods used for
 * the user interface of the application such that the user can interact and select options.
 * 
 * @author suyogrithesh
 *
 */
public class FlightFrontend implements IFlightFrontend {

  Scanner scnr; // scanner object
  IFlightBackend backend; // backend object
  // array of all cities to check user input
  private String[] cities =
      new String[] {"New York", "Chicago", "Miami", "Seattle", "Atlanta", "Los Angeles", "Dallas"};

  /**
   * The constructor that the implementation this interface will provide. It takes the Scanner that
   * will read user input as a parameter and the backend.
   */
  public FlightFrontend(Scanner userInputScanner, IFlightBackend backend) {
    scnr = userInputScanner;
    this.backend = backend;
  }

  /**
   * This method starts the command loop for the frontend, and will terminate when the user exists
   * the app.
   */
  @Override
  public void runCommandLoop() {
    System.out.println("Welcome to Flight Finder Application!" + "\n");
    int sc_input = -1;
    boolean user = true;
    boolean user2 = true;
    // loop that doesn't end until
    while (user) {
      displayMainMenu();
      // while loop to make sure input is right
      while (user2) {
        try {
          sc_input = Integer.parseInt(scnr.nextLine());
          // Checking for valid input
          if (sc_input > 0 && sc_input < 7) {
            break;
          } else {
            System.out.println("Incorrect value, enter a new value.");
          }
        } catch (Exception excpt) {
          System.out.println("Threw an unexpected error, enter a new value.");
        }
      }
      switch (sc_input) {
        case (1):
          shortestPath();
          break;
        case (2):
          minimumSpanningTree();
          break;
        case (3):
          findFlightFrom();
          break;
        case (4):
          findFlightTo();
          break;
        case (5):
          displayAllCities();
          break;
        case (6):
          System.out.println("Goodbye!");
          user = false;
          break;
      }
    }

  }

  /**
   * Displays the main menu
   */
  @Override
  public void displayMainMenu() {
    System.out.println("You are in the Main Menu:");
    System.out.println(
        "          1) Find Shortest Path\r\n" + "          2) Find Minimum Spanning Tree\r\n"
            + "          3) Find Flights From City\r\n" + "          4) Find Flights To City\r\n"
            + "          5) Display All Cities\r\n" + "          6) Exit Application\r\n");
  }

  /**
   * Displays the shortest path given two input cities
   */
  @Override
  public void shortestPath() {
    System.out.println("Find Shortest Path Menu:");
    System.out.println("          Enter the name of the airport you are flying from:");
    String origin = scnr.nextLine();
    System.out.println("          Enter the name of the airport you are flying to:");
    String destination = scnr.nextLine();

    boolean check1 = Arrays.asList(cities).contains(origin);
    boolean check2 = Arrays.asList(cities).contains(destination);
    if (check1 && check2) {
      String path = backend.getShortestPath(origin, destination);

      System.out.println("Shortest Path: " + path);
    } else {
      System.out.println("The city(s) entered are not in the system!");
    }

  }

  /**
   * Displays the minimum Spanning Tree
   */
  @Override
  public void minimumSpanningTree() {
    System.out.println("Find Minimum Spanning Tree Menu:");
    System.out.println("          Enter the name of the airport you are flying from:");
    String origin = scnr.nextLine();

    boolean check1 = Arrays.asList(cities).contains(origin);

    if (check1) {
      List<String> paths = backend.getMST(origin);

      System.out.println("Minimum Spanning Tree: ");
      for (int i = 0; i < paths.size(); i++) {
        System.out.println(paths.get(i));
      }

    } else {
      System.out.println("The city(s) entered are not in the system!");
    }

  }

  /**
   * Finds all flights from an airport
   */
  @Override
  public void findFlightFrom() {
    System.out.println("Flights from City Menu:");
    System.out.println("          Enter the name of the airport you are flying from:");
    String origin = scnr.nextLine();

    boolean check1 = Arrays.asList(cities).contains(origin);

    if (check1) {
      System.out.println("Flights from " + origin + ":");
      List<String> list = backend.Flightfrom(origin);

      for (int i = 0; i < list.size(); i++) {
        System.out.println(list.get(i));
      }
    } else {
      System.out.println("The city(s) entered are not in the system!");
    }

  }

  /**
   * Finds all flights to an airport
   */
  @Override
  public void findFlightTo() {
   System.out.println("Flights to City Menu:");
    System.out.println("          Enter the name of the airport you are flying to:");
    String destination = scnr.nextLine();

    boolean check1 = Arrays.asList(cities).contains(destination);

    if (check1) {
      System.out.println("Flights to " + destination + ":");
      List<String> list = backend.Flightto(destination);

      for (int i = 0; i < list.size(); i++) {
        System.out.println(list.get(i));
      }
    } else {
      System.out.println("The city(s) entered are not in the system!");
    }



  }

  /**
   * Displays all cities.
   */
  @Override
  public void displayAllCities() {
    System.out.println("List of Cities: ");
    for (int i = 0; i < cities.length; i++) {
      System.out.println(cities[i]);
    }

  }

}


