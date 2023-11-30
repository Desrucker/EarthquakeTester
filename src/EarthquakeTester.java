import java.io.*;
import java.text.*;
import java.util.*;
/**
 * EarthquakeTester is a program that processes earthquake data from an input file, validates the data,
 * classifies earthquakes into categories, sorts them by magnitude, and outputs the results to two separate files.
 * Invalid data is recorded in an invalid output file, and valid earthquake data is sorted and written to a sorted output file.
 *
 * @author Dominic Rucker
 */
public class EarthquakeTester {
    /**
     * A list to store valid earthquake objects.
     */
    public static List<Earthquake> earthquakes = new ArrayList<>();

    /**
     * The current earthquake being processed.
     */
    public static Earthquake currEarthquake = null;

    /**
     * The main entry point of the EarthquakeTester program.
     * <p>
     * This method processes earthquake data from an input file, validates it, sorts it,
     * and generates two output files: one for valid earthquake data and another for
     * invalid data entries.
     *
     * @param args An array of command-line arguments containing the paths to the input file,
     *             the output file for invalid data, and the output file for sorted earthquake data.
     */
    public static void main(String[] args) {
        // Check if the correct number of command-line arguments is provided
        if (args.length != 3) {
            System.out.println("Usage: java EarthquakeTester <inputFile> <invalidOutputFile> <sortedOutputFile>");
            return;
        }

        // Extract the command-line arguments
        String inputFile = args[0];
        String invalidOutputFile = args[1];
        String sortedOutputFile = args[2];

        // Initialize a list to store invalid data entries
        List<String> invalidData = new ArrayList<>();

        // Initialize a line counter to track the current line being processed
        int lineCounter = 0;

        try {
            // Create a scanner to read the input file line by line
            Scanner fileScanner = new Scanner(new File(inputFile));

            // Process each line of the input file
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lineCounter++;

                try {
                    // Process the current line and validate its data
                    processLine(line, lineCounter);

                    // Add the valid earthquake data to the earthquakes list
                    if (currEarthquake != null) {
                        earthquakes.add(currEarthquake);
                    } else {
                        throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException e) {
                    // Handle invalid data entries by adding them to the invalidData list
                    invalidData.add("|Line " + lineCounter + "| " + line + " " + e.getMessage());
                }
            }

            // Close the input file scanner
            fileScanner.close();

            // Sort the list of earthquakes based on natural ordering
            Collections.sort(earthquakes);

            // Create a PrintWriter for the sorted earthquake data output file
            PrintWriter sortedWriter = new PrintWriter(new File(sortedOutputFile));

            // Write sorted earthquake data to the output file
            for (Earthquake eq : earthquakes) {
                sortedWriter.println(eq.toString());
            }

            // Close the sorted output file writer
            sortedWriter.close();

            // Create a PrintWriter for the invalid data output file
            PrintWriter invalidWriter = new PrintWriter(new File(invalidOutputFile));

            // Write invalid data entries to the output file
            for (String invalidLine : invalidData) {
                invalidWriter.println(invalidLine);
            }

            // Close the invalid data output file writer
            invalidWriter.close();

        } catch (FileNotFoundException e) {
            // Handle the case where the input file is not found
            System.err.println("File not found: " + e.getMessage());
        }
    }

    /**
     * Processes a line of earthquake data and creates an Earthquake object if the data is valid.
     *
     * @param line        The input line containing earthquake data in CSV format.
     * @param lineCounter The line number in the input file for error reporting.
     * @throws IllegalArgumentException If the input line is invalid or contains incorrect data.
     * @throws NumberFormatException    If there is an issue parsing numerical values.
     */
    private static void processLine(String line, int lineCounter) throws IllegalArgumentException {
        try {
            // Split the input line into parts using a comma as the delimiter
            String[] parts = line.split(",");

            // Check if there are at least 6 parts (date-time, latitude, longitude, magnitude, id, and place)
            if (parts.length < 6) {
                throw new IllegalArgumentException("Invalid data format");
            }

            // Extract and validate date-time
            String dateTimeZ = parts[0].trim();
            if (!isValidDateTimeZ(dateTimeZ)) {
                throw new IllegalArgumentException("Invalid date-time format");
            }

            // Extract and validate latitude, longitude, and magnitude
            double latitude = Double.parseDouble(parts[1]);
            double longitude = Double.parseDouble(parts[2]);
            double magnitude = Double.parseDouble(parts[3]);
            if (magnitude < 0.0) {
                throw new IllegalArgumentException("Invalid magnitude");
            }

            // Extract and validate earthquake ID
            String id = parts[4].trim();
            StringBuilder placeBuilder = new StringBuilder(parts[5].trim());
            for (int i = 6; i < parts.length; i++) {
                placeBuilder.append(" ").append(parts[i].trim());
            }
            String place = placeBuilder.toString();

            // Check latitude and longitude ranges
            boolean latitudeCheck = (latitude >= -90 && latitude <= 90);
            boolean longitudeCheck = (longitude >= -180 && longitude <= 180);

            // Check if the earthquake ID is unique
            boolean idCheck = true;
            for (Earthquake str : earthquakes) {
                if (str.getId().equals(id)) {
                    idCheck = false;
                    break;
                }
            }

            // Throw exceptions for invalid data
            if (!latitudeCheck) {
                throw new IllegalArgumentException("Invalid latitude");
            } else if (!longitudeCheck) {
                throw new IllegalArgumentException("Invalid longitude");
            } else if (!idCheck) {
                throw new IllegalArgumentException("Invalid ID");
            }

            // Create an Earthquake object based on magnitude and add it to the earthquakes list
            currEarthquake = (magnitude < 3) ?
                    new NonClassified(dateTimeZ, latitude, longitude, magnitude, id, place, lineCounter) :
                    new Classified(dateTimeZ, latitude, longitude, magnitude, id, place, lineCounter);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Number format exception");
        }
    }

    /**
     * Checks if a date-time string is in the valid format "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'".
     *
     * @param dateTimeZ The date-time string to check.
     * @return True if the string is in the valid format, false otherwise.
     */
    private static boolean isValidDateTimeZ(String dateTimeZ) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            Date date = format.parse(dateTimeZ);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}