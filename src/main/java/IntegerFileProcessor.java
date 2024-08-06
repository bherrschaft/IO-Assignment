import java.io.*;
import java.util.*;

public class IntegerFileProcessor {
    public static void main(String[] args) {
        String inputFile1 = "src/main/java/input1.txt";
        String inputFile2 = "src/main/java/input2.txt";
        String mergedOutputFile = "src/main/java/merged.txt";
        String commonOutputFile = "src/main/java/common.txt";

        List<Integer> list1 = new ArrayList<>(); // List to store integers from the first file
        List<Integer> list2 = new ArrayList<>(); // List to store integers from the second file

        // Read integers from the first input file
        try (BufferedReader reader1 = new BufferedReader(new FileReader(inputFile1))) {
            String line;
            while ((line = reader1.readLine()) != null) {
                try {
                    list1.add(Integer.parseInt(line.trim())); // Parse and add the integer to the list
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in file 1: " + line); // Handle invalid format
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + inputFile1); // Handle file not found
        } catch (IOException e) {
            System.err.println("Error reading file: " + inputFile1); // Handle IO exception
        }

        // Read integers from the second input file
        try (BufferedReader reader2 = new BufferedReader(new FileReader(inputFile2))) {
            String line;
            while ((line = reader2.readLine()) != null) {
                try {
                    list2.add(Integer.parseInt(line.trim())); // Parse and add the integer to the list
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in file 2: " + line); // Handle invalid format
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + inputFile2); // Handle file not found
        } catch (IOException e) {
            System.err.println("Error reading file: " + inputFile2); // Handle IO exception
        }

        // Merge the two lists and write to the merged output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(mergedOutputFile))) {
            for (Integer number : list1) {
                writer.write(number + "\n"); // Write each number from the first list
            }
            for (Integer number : list2) {
                writer.write(number + "\n"); // Write each number from the second list
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + mergedOutputFile); // Handle IO exception
        }

        // Identify common integers and write to the common output file
        Set<Integer> commonIntegers = new HashSet<>(list1); // Convert the first list to a set
        commonIntegers.retainAll(list2); // Retain only integers present in both lists

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(commonOutputFile))) {
            for (Integer number : commonIntegers) {
                writer.write(number + "\n"); // Write each common number
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + commonOutputFile); // Handle IO exception
        }
    }
}
