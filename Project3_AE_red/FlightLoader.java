import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class can be used to load flight data from a DOT file
 */
public class FlightLoader implements IFlightLoader {
    /**
     * This method loads the list of flights from a DOT file.
     * @param filepathToDot path to the DOT file relative to the executable
     * @return a list of flight objects
     * @throws FileNotFoundException
     */
    @Override
    public List<IFlight> loadFlights(String filepathToDot) throws FileNotFoundException {
        //creates a new arraylist for storing the data
        List<IFlight> flights = new ArrayList<IFlight>();
        try{
            File file = new File(filepathToDot);
            //initializes a scanner
            Scanner scnr = new Scanner(file);
            //scans the first line
            scnr.nextLine();
            scnr.nextLine();
            String currLine;
            //runs till there are lines in the file
            while(scnr.hasNextLine()){
                currLine = scnr.nextLine();
                if(!(currLine.isEmpty())){
                    //splits the lines by \
                    String[] currLineSplit = currLine.split("\"");
                    //since we don't want the ending } in our arraylist
                    if(!(currLineSplit[0].equals("}"))){
                        //to get the substring of number in the last split element
                        int initial = currLineSplit[4].indexOf('=');
                        int fin = currLineSplit[4].indexOf(']');
                        Flight f = new Flight(currLineSplit[1], currLineSplit[3],
                                Integer.parseInt(currLineSplit[4].substring(initial + 1, fin)));
                        //adds data to the arraylist
                        flights.add(f);
                    }
                }
            }
        }
        catch (FileNotFoundException e){
            //catches an exception if the file is not found
            throw new FileNotFoundException("File not found");
        }
        return flights;
    }
}
