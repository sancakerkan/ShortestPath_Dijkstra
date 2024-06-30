//-----------------------------------------------------
// Title: File Reader Class
// Author: Erkan Sancak
// ID: 44293566706
// Section: 2
// Assignment: 3
// Description: This class reads a text file and returns the contents as a list of strings.
//-----------------------------------------------------
import java.io.*;  // Imports the input-output classes for file handling
import java.util.*;  // Imports utility classes for data structures like List and ArrayList

public class FileRead {
    
    //--------------------------------------------------------
    // Summary: Reads the contents of a text file.
    // Precondition: The file must exist and be accessible.
    // Postcondition: Returns the contents of the file as a list of strings.
    //--------------------------------------------------------
    public static List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();  // List to hold the lines of the file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));  // Creates a BufferedReader to read the file
            String line;
            while ((line = reader.readLine()) != null) {  // Reads each line of the file
                lines.add(line);  // Adds the line to the list
            }
            reader.close();  // Closes the reader
        } catch (IOException e) {
            e.printStackTrace();  // Prints the stack trace if an exception occurs
        }
        return lines;  // Returns the list of lines
    }
}