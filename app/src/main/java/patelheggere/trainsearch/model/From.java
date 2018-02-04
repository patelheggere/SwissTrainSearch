package patelheggere.trainsearch.model;

/**
 * Created by Patel Heggere on 2/4/2018.
 */

public class From {
    private String arrival;
    private Long arrivalTimestamp;
    private String departure;
    private String platform;
    private Long departureTimestamp;
    private Prognosis prognosis;
    private Station station;
    private Coordinate coordinate;
    private Location location;


    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Long getArrivalTimestamp() {
        return arrivalTimestamp;
    }

    public void setArrivalTimestamp(Long arrivalTimestamp) {
        this.arrivalTimestamp = arrivalTimestamp;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Long getDepartureTimestamp() {
        return departureTimestamp;
    }

    public void setDepartureTimestamp(Long departureTimestamp) {
        this.departureTimestamp = departureTimestamp;
    }

    public Prognosis getPrognosis() {
        return prognosis;
    }

    public void setPrognosis(Prognosis prognosis) {
        this.prognosis = prognosis;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate1(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
