public class Earthquake implements Comparable<Earthquake> {
    private String dateTimeZ;
    private double latitude;
    private double longitude;
    private double magnitude;
    private String id;
    private String place;

    public Earthquake(String dateTimeZ, double latitude, double longitude, double magnitude, String id, String place) {
        this.dateTimeZ = dateTimeZ;
        this.latitude = latitude;
        this.longitude = longitude;
        this.magnitude = magnitude;
        this.id = id;
        this.place = place;
    }

    @Override
    public int compareTo(Earthquake other) {
        return Double.compare(other.magnitude, this.magnitude);
    }

    @Override
    public String toString() {
            return "{dateTimeZ='" + dateTimeZ + "', " +
                    "latitude=" + latitude + ", " +
                    "longitude=" + longitude + ", " +
                    "magnitude=" + magnitude +
                    ", id='" + id + "', " +
                    "place='" + place + "'}";
    }

    public String getDateTimeZ() {
        return dateTimeZ;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }
}
