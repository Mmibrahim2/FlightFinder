// --== CS400 Project One File Header ==--
// Name: Suyog Rithesh
// CSL Username: suyog
// Email: rithesh@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: none
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class contains various tester methods that test the methods in 
 * FlightFrontend.java
 * 
 * @author SuyogRithesh
 *
 */
public class FrontendDeveloperTests {
	private static Scanner scnr;

	/**
	 * test1() checks the working of our runCommandLoop() method.
	 * 
	 */
	@Test
	public void FrontendDeveloperTest1() {
		try {

			TextUITester test = new TextUITester("6\n");
			scnr = new Scanner(System.in);
			FlightFrontend frontend = new FlightFrontend(scnr, new FDPlaceholder());

			frontend.runCommandLoop();

			String required_output = test.checkOutput();

			assertTrue(required_output.startsWith("Welcome to Flight Finder Application!"));
			assertTrue(required_output.contains("You are in the Main Menu:"));
			assertTrue(required_output.contains("Goodbye!"));

		}catch(Exception e) {
			e.printStackTrace();
			fail("Threw an unexpected exception!");		
		}


	}

	/**
	 * test2() checks the working of our shortestPath() method.
	 * 
	 */
	@Test
	public void FrontendDeveloperTest2() {
		try {
			TextUITester test = new TextUITester("New York\nChicago\n");
			scnr = new Scanner(System.in);
			FlightFrontend frontend = new FlightFrontend(scnr, new FDPlaceholder());
			frontend.shortestPath();

			String required_output = test.checkOutput();


			assertTrue(required_output.startsWith("Find Shortest Path Menu:"));
			assertTrue(required_output.contains("Enter the name of the airport you are flying from:"));
			assertTrue(required_output.contains("Enter the name of the airport you are flying to:"));
			assertTrue(required_output.contains("Shortest Path: New York → Chicago"));
		}catch(Exception e) {
			e.printStackTrace();
			fail("Threw an unexpected exception!");		
		}

	}
	
	/**
	 * test3() checks the working of our minimumSpanningTree() method.
	 * 
	 */
	@Test
	public void FrontendDeveloperTest3() {
		try {
			TextUITester test = new TextUITester("New York\n");
			scnr = new Scanner(System.in);
			FlightFrontend frontend = new FlightFrontend(scnr, new FDPlaceholder());
			frontend.minimumSpanningTree();

			String required_output = test.checkOutput();

			assertTrue(required_output.startsWith("Find Minimum Spanning Tree Menu:"));
			assertTrue(required_output.contains("Enter the name of the airport you are flying from:"));
			assertTrue(required_output.contains("New York -> Chicago"));
			assertTrue(required_output.contains("New York -> Atlanta"));
		}catch(Exception e) {
			e.printStackTrace();
			fail("Threw an unexpected exception!");		
		}

	}

	/**
	 * test4() checks the working of our findFlightFrom() method.
	 * 
	 */
	@Test
	public void FrontendDeveloperTest4() {
		try {
			TextUITester test = new TextUITester("New York\n");
			scnr = new Scanner(System.in);
			FlightFrontend frontend = new FlightFrontend(scnr, new FDPlaceholder());
			frontend.findFlightFrom();

			String required_output = test.checkOutput();
			
			assertTrue(required_output.startsWith("Flights from City Menu:"));
			assertTrue(required_output.contains("Enter the name of the airport you are flying from:"));
			assertTrue(required_output.contains("Flights from New York:"));
			assertTrue(required_output.contains("New York → Chicago"));
			assertTrue(required_output.contains("New York → Atlanta"));
		}catch(Exception e) {
			e.printStackTrace();
			fail("Threw an unexpected exception!");		
		}

	}

	
	/**
	 * test5() checks the working of our findFlightTo() method.
	 * 
	 */
	@Test
	public void FrontendDeveloperTest5() {
		try {
			TextUITester test = new TextUITester("New York\n");
			scnr = new Scanner(System.in);
			FlightFrontend frontend = new FlightFrontend(scnr, new FDPlaceholder());
			frontend.findFlightTo();

			String required_output = test.checkOutput();

			assertTrue(required_output.startsWith("Flights to City Menu:"));
			assertTrue(required_output.contains("Enter the name of the airport you are flying to:"));
			assertTrue(required_output.contains("Flights to New York:"));
			assertTrue(required_output.contains("Miami → New York"));
			assertTrue(required_output.contains("Los Angeles → New York"));
		}catch(Exception e) {
			e.printStackTrace();
			fail("Threw an unexpected exception!");		
		}

	}
}
