// FOR TESTERS
// Created BY MOHAMMUD IBRAHIM

public class BDFlight implements IFlight {

    private int distance = 0;
    private String OriginCityName = null;
    private String DestinationCityName = null;

    public BDFlight (String OriginCityName,String DestinationCityName,int distance) {
        this.OriginCityName = OriginCityName;
        this.DestinationCityName = DestinationCityName;
        this.distance = distance;
    }
    @Override
    public int getDistance() {
        return distance;
    }

    @Override public String getOriginCityName() {
        return OriginCityName;
    }

    @Override public String getDestinationCityName() {
        return DestinationCityName;
    }

    @Override public String toString() {
        return "[" + getOriginCityName() + ", " + getDestinationCityName() + ", " + getDistance() + "]";
    }
}
