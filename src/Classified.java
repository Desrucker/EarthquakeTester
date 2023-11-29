public class Classified extends Earthquake {

    public Classified(String dateTimeZ, double latitude, double longitude, double magnitude, String id, String place) {
        super(dateTimeZ, latitude, longitude, magnitude, id, place);
    }

    @Override
    public String toString() {
        return "[Classified] {dateTimeZ='" + getDateTimeZ() + "', " +
                "latitude=" + getLatitude() + ", " +
                "longitude=" + getLongitude() + ", " +
                "magnitude=" + getMagnitude() + ", " +
                "id='" + getId() + "', " +
                "place='" + getPlace() + "', " +
                "magnitudeClass='" + getMagnitudeClass() + "'}";
    }

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
