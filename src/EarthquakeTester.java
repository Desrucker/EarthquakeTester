import java.io.*;
import java.util.*;
import java.util.regex.*;

public class EarthquakeTester {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java EarthquakeTester <inputFile> <invalidOutputFile> <sortedOutputFile>");
            return;
        }

        String inputFile = args[0];
        String invalidOutputFile = args[1];
        String sortedOutputFile = args[2];

        List<Earthquake> earthquakes = new ArrayList<>();
        List<String> invalidData = new ArrayList<>();

        int lineCounter = 0;

        try {
            Scanner fileScanner = new Scanner(new File(inputFile));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lineCounter++;
                try {
                    Earthquake earthquake = processLine(line);
                    earthquakes.add(earthquake);
                } catch (IllegalArgumentException e) {
                    invalidData.add(line);
                }
            }
            fileScanner.close();

            Collections.sort(earthquakes);

            PrintWriter sortedWriter = new PrintWriter(new File(sortedOutputFile));
            for (Earthquake eq : earthquakes) {
                sortedWriter.println(eq.toString());
            }
            sortedWriter.close();

            PrintWriter invalidWriter = new PrintWriter(new File(invalidOutputFile));
            for (String invalidLine : invalidData) {
                invalidWriter.println(invalidLine);
            }
            invalidWriter.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    private static Earthquake processLine(String line) throws IllegalArgumentException {
        String[] parts = line.split(",");
        if (parts.length != 6) {
            System.err.println("Invalid data format: " + line);
            throw new IllegalArgumentException("Invalid data format");
        }

        String dateTimeZ = parts[0].trim();
        double latitude = Double.parseDouble(parts[1]);
        double longitude = Double.parseDouble(parts[2]);
        double magnitude = Double.parseDouble(parts[3]);
        String id = parts[4].trim();
        String place = parts[5].trim();

        boolean dateTimeCheck = isValidDateTimeZ(dateTimeZ);
        boolean latitudeCheck = (latitude >= -90 && latitude <= 90);
        boolean longitudeCheck = (longitude >= -180 && longitude <= 180);
        boolean magnitudeCheck = (magnitude >= -1.0 && magnitude <= 10.0);
        boolean idCheck = !id.isEmpty();
        boolean placeCheck = !place.isEmpty();

        if (dateTimeCheck && latitudeCheck && longitudeCheck && magnitudeCheck && idCheck && placeCheck) {
            return new NonClassified(dateTimeZ, latitude, longitude, magnitude, id, place);
        } else {
            System.err.println("Invalid data: " + line);
            return new Classified(dateTimeZ, latitude, longitude, magnitude, id, place);
        }
    }
    private static boolean isValidDateTimeZ(String dateTimeZ) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateTimeZ);
        return matcher.matches();
    }
}
