/**
 * Earthquake represents an earthquake event with various attributes including date-time, location, magnitude, and more.
 * It implements the Comparable interface to allow for sorting earthquakes by magnitude.
 */
public class Earthquake implements Comparable<Earthquake> {
    private String dateTimeZ;
    private double latitude;
    private double longitude;
    private double magnitude;
    private String id;
    private String place;
    private int lineCounter;

    /**
     * Constructs an Earthquake object with the specified attributes.
     *
     * @param dateTimeZ   The date and time of the earthquake in the format "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'".
     * @param latitude    The latitude of the earthquake's location.
     * @param longitude   The longitude of the earthquake's location.
     * @param magnitude   The magnitude of the earthquake.
     * @param id          The unique identifier for the earthquake.
     * @param place       The place description of the earthquake's location.
     * @param lineCounter The line number in the input file where this earthquake's data was read.
     */
    public Earthquake(String dateTimeZ, double latitude, double longitude, double magnitude, String id, String place, int lineCounter) {
        this.dateTimeZ = dateTimeZ;
        this.latitude = latitude;
        this.longitude = longitude;
        this.magnitude = magnitude;
        this.id = id;
        this.place = place;
        this.lineCounter = lineCounter;
    }

    /**
     * Compares two earthquakes based on their magnitudes for sorting purposes.
     *
     * @param other The Earthquake object to compare to.
     * @return -1 if this earthquake has a greater magnitude, 1 if other has a greater magnitude, or 0 if they have the same magnitude.
     */

    public int compareTo(Earthquake other) {
        if (this.magnitude > other.magnitude) {
            return 1;
        } else if (this.magnitude < other.magnitude) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Returns a string representation of the earthquake, including its attributes.
     *
     * @return A string in the format: "|Line <lineCounter>| [eID='<id>'][dateTimeZ:<dateTimeZ>][longitude:<longitude>][latitude:<latitude>][magnitude:<magnitude>][place:<place>]"
     */
    @Override
    public String toString() {
        return "|Line " + getLineCounter() + "| [eID:" + getId() + "]" +
                "[dateTimeZ:" + getDateTimeZ() + "]" +
                "[longitude:" + getLongitude() + "]" +
                "[latitude:" + getLatitude() + "]" +
                "[magnitude:" + getMagnitude() + "]" +
                "[place:" + getPlace() + "]";
    }

    /**
     * Gets the date and time of the earthquake.
     *
     * @return The date and time in the format "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'".
     */
    public String getDateTimeZ() {
        return dateTimeZ;
    }

    /**
     * Gets the latitude of the earthquake's location.
     *
     * @return The latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the longitude of the earthquake's location.
     *
     * @return The longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Gets the magnitude of the earthquake.
     *
     * @return The magnitude.
     */
    public double getMagnitude() {
        return magnitude;
    }

    /**
     * Gets the unique identifier for the earthquake.
     *
     * @return The earthquake's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the place description of the earthquake's location.
     *
     * @return The place description.
     */
    public String getPlace() {
        return place;
    }

    /**
     * Gets the line number in the input file where this earthquake's data was read.
     *
     * @return The line number.
     */
    public int getLineCounter() {
        return lineCounter;
    }
}
