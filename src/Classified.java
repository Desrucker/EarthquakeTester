/**
 * Classified is a subclass of Earthquake that represents a classified earthquake event with additional information
 * about its magnitude classification.
 */
public class Classified extends Earthquake {

    /**
     * Constructs a Classified earthquake object with the specified attributes.
     *
     * @param dateTimeZ   The date and time of the earthquake in the format "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'".
     * @param latitude    The latitude of the earthquake's location.
     * @param longitude   The longitude of the earthquake's location.
     * @param magnitude   The magnitude of the earthquake.
     * @param id          The unique identifier for the earthquake.
     * @param place       The place description of the earthquake's location.
     * @param lineCounter The line number in the input file where this earthquake's data was read.
     */
    public Classified(String dateTimeZ, double latitude, double longitude, double magnitude, String id, String place, int lineCounter) {
        super(dateTimeZ, latitude, longitude, magnitude, id, place, lineCounter);
    }

    /**
     * Returns a string representation of the classified earthquake, including its attributes and magnitude classification.
     *
     * @return A string in the format: "[Classified] [eID='<id>'][dateTimeZ:<dateTimeZ>][longitude:<longitude>][latitude:<latitude>][magnitude:<magnitude>][place:<place>][magnitudeClass:<magnitudeClass>]"
     */
    @Override
    public String toString() {
        return "[Classified] [eID='" + getId() + "]" +
                "[dateTimeZ:" + getDateTimeZ() + "]" +
                "[longitude:" + getLongitude() + "]" +
                "[latitude:" + getLatitude() + "]" +
                "[magnitude:" + getMagnitude() + "]" +
                "[place:" + getPlace() + "]" +
                "[magnitudeClass:" + getMagnitudeClass() + "]";
    }

    /**
     * Determines the magnitude classification of the earthquake based on its magnitude.
     *
     * @return "Major" if magnitude is greater than or equal to 7.0, "Moderate" if magnitude is greater than or equal to 5.0, or "Minor" otherwise.
     */
    public String getMagnitudeClass() {
        if (getMagnitude() >= 7.0) {
            return "Major";
        } else if (getMagnitude() >= 5.0) {
            return "Moderate";
        } else {
            return "Minor";
        }
    }
}
