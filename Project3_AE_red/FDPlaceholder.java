// --== CS400 Project One File Header ==--
// Name: Suyog Rithesh
// CSL Username: suyog
// Email: rithesh@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: none
import java.util.ArrayList;
import java.util.List;

public class FDPlaceholder implements IFlightBackend {

	/**
	         * Displays all city available
	         *
	         */
	        // display all flights in tree
	        public String[] DisplayallFlights(){
	        	return null;
	        }
	        /**
	         * Displays all flights that connect to a given city
	         *
	         */
	        public List<String> Flightto(String city){
	        	List<String> list = new ArrayList<String>();
	        	list.add("Miami → New York");
	        	list.add("Los Angeles → New York");
	        	return list;
	        }
	        /**
	         * Displays all flights from a given city
	         *
	         */
	        public List<String> Flightfrom(String city){
	        	List<String> list = new ArrayList<String>();
	        	list.add("New York → Chicago");
	        	list.add("New York → Atlanta");
	        	return list;
	        }
	        
	        public String getShortestPath(String starting, String ending){
				return "New York → Chicago";
	        	
	        }
	        
	        public List<String> getMST(String starting){
	        	List<String> list = new ArrayList<String>();
	        	list.add("New York -> Chicago");
	        	list.add("New York -> Atlanta");
	        	list.add("Atlanta -> Miami");
	        	list.add("Chicago -> Dallas");
	        	list.add("Dallas -> Los Angeles");
	        	list.add("Los Angeles -> Seattle");
	        	return list;
	        }
		@Override
		public void addVertex(String vertex) {
			// TODO Auto-generated method stub
		}
		@Override
		public void addEdge(String starting, String ending, int distance) {
			// TODO Auto-generated method stub
				
		}
}

