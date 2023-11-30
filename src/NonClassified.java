/**
 * NonClassified is a subclass of Earthquake that represents a non-classified earthquake event.
 * It inherits attributes and methods from the Earthquake class and does not introduce any new functionality.
 */
public class NonClassified extends Earthquake {

    /**
     * Constructs a NonClassified earthquake object with the specified attributes.
     *
     * @param dateTimeZ   The date and time of the earthquake in the format "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'".
     * @param latitude    The latitude of the earthquake's location.
     * @param longitude   The longitude of the earthquake's location.
     * @param magnitude   The magnitude of the earthquake.
     * @param id          The unique identifier for the earthquake.
     * @param place       The place description of the earthquake's location.
     * @param lineCounter The line number in the input file where this earthquake's data was read.
     */
    public NonClassified(String dateTimeZ, double latitude, double longitude, double magnitude, String id, String place, int lineCounter) {
        super(dateTimeZ, latitude, longitude, magnitude, id, place, lineCounter);
    }
}
