run: FlightMapper.class
	java FlightMapper
FlightMapper.class: compileAll
	javac FlightMapper.java

compileAll: FlightFrontend.class FlightLoader.class FlightBackend.class CS400Graph.class   

runFrontendDeveloperTests: FlightFrontend.class FrontendDeveloperTests.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java
	javac -cp .:junit5.jar FrontendDeveloperTests.java

FlightFrontend.class: FlightFrontend.java
	javac FlightFrontend.java

runTests: compile runAlgorithmEngineerTests runBackenddeveloperTests
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=DataWranglerTests
compile: Flight.class FlightLoader.class DataWranglerTests.class
DataWranglerTests.class: DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java
Flight.class: Flight.java
	javac Flight.java
FlightLoader.class: FlightLoader.java
	javac FlightLoader.java

runAlgorithmEngineerTests: algorithmEngineerTests

algorithmEngineerTests: AlgorithmEngineerTests.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=AlgorithmEngineerTests

AlgorithmEngineerTests.class: CS400Graph.class
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

CS400Graph.class:
	javac CS400Graph.java


runBackenddeveloperTests: BDFlight.class CS400Graph.class FlightBackend.class BackendDeveloperTests.java 
	javac -cp .:junit5.jar BackendDeveloperTests.java
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=BackendDeveloperTests


BDFlight.class:
	javac BDFlight.java

CS400Graph.class: CS400Graph.java BDCS400Graph.java
	javac GraphADT.java
	javac BDCS400Graph.java
	javac CS400Graph.java


FlightBackend.class: FlightBackend.java
	javac FlightBackend.java


clean:
	rm *.class

run: CS400Graph.class FlightBackend.class FlightLoader.class Flight.class FlightFrontend.class FlightMapper.class
	java FlightMapper.java
CS400Graph.class:
	javac CS400Graph.java

FlightBackend.class:
	javac FlightBackend.java

FlightLoader.class:
	javac FlightLoader.java

Flight.class:
	javac Flight.java

FlightMapper.class:
	javac FlightMapper.java

FlightFrontend.class:
	javac FlightFrontend.java
