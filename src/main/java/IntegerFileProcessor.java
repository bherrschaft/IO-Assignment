import java.io.*; // Import classes for file handling
import java.util.*; // Import utility classes like List, Set, etc.

public class IntegerFileProcessor {
    public static void main(String[] args) {
        // Define file paths for input and output files
        String inputFile1 = "src/main/java/input1.txt"; // Path to the first input file
        String inputFile2 = "src/main/java/input2.txt"; // Path to the second input file
        String mergedOutputFile = "src/main/java/merged.txt"; // Path to the merged output file
        String commonOutputFile = "src/main/java/common.txt"; // Path to the common output file

        // Create lists to store integers read from each input file
        List<Integer> list1 = new ArrayList<>(); // List to store integers from the first file
        List<Integer> list2 = new ArrayList<>(); // List to store integers from the second file

        // Read integers from the first input file
        try (BufferedReader reader1 = new BufferedReader(new FileReader(inputFile1))) {
            String line; // Variable to store each line read from the file
            while ((line = reader1.readLine()) != null) { // Loop to read each line until the end of the file
                try {
                    list1.add(Integer.parseInt(line.trim())); // Parse the line as an integer and add to the list
                } catch (NumberFormatException e) {
                    // Handle cases where the line does not contain a valid integer
                    System.err.println("Invalid number format in file 1: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            // Handle the case where the input file is not found
            System.err.println("File not found: " + inputFile1);
        } catch (IOException e) {
            // Handle other I/O errors that may occur during reading
            System.err.println("Error reading file: " + inputFile1);
        }

        // Read integers from the second input file
        try (BufferedReader reader2 = new BufferedReader(new FileReader(inputFile2))) {
            String line; // Variable to store each line read from the file
            while ((line = reader2.readLine()) != null) { // Loop to read each line until the end of the file
                try {
                    list2.add(Integer.parseInt(line.trim())); // Parse the line as an integer and add to the list
                } catch (NumberFormatException e) {
                    // Handle cases where the line does not contain a valid integer
                    System.err.println("Invalid number format in file 2: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            // Handle the case where the input file is not found
            System.err.println("File not found: " + inputFile2);
        } catch (IOException e) {
            // Handle other I/O errors that may occur during reading
            System.err.println("Error reading file: " + inputFile2);
        }

        // Merge the two lists and write to the merged output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(mergedOutputFile))) {
            for (Integer number : list1) { // Loop through each integer in the first list
                writer.write(number + "\n"); // Write the integer to the merged output file
            }
            for (Integer number : list2) { // Loop through each integer in the second list
                writer.write(number + "\n"); // Write the integer to the merged output file
            }
        } catch (IOException e) {
            // Handle I/O errors that may occur during writing
            System.err.println("Error writing to file: " + mergedOutputFile);
        }

        // Identify common integers between the two lists and write them to the common output file
        Set<Integer> commonIntegers = new HashSet<>(list1); // Convert the first list to a set to allow set operations
        commonIntegers.retainAll(list2); // Retain only the integers that are also in the second list

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(commonOutputFile))) {
            for (Integer number : commonIntegers) { // Loop through each integer in the set of common integers
                writer.write(number + "\n"); // Write the common integer to the common output file
            }
        } catch (IOException e) {
            // Handle I/O errors that may occur during writing
            System.err.println("Error writing to file: " + commonOutputFile);
        }
    }
}

/*
 * Summary:
 * The IntegerFileProcessor class reads integers from two input text files, merges them into a single output file,
 * and identifies common integers between the two input files, saving them to another output file.
 * The program handles common file I/O exceptions, such as file not found or invalid number format, and logs
 * appropriate error messages. The use of BufferedReader and BufferedWriter ensures efficient reading and writing
 * operations. The common integers are identified using a Set to perform the intersection of the two lists.
 */
