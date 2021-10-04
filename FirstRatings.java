
/**
 * This class FirstRatings process the movie and ratings
 * data and answer questions about them.
 * 
 * @author Xinxin Yang
 * @version 211004
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.io.IOException;

public class FirstRating {
    /**
     * Process every record from the CSV file whose name
     * is file name and return an ArrayList of Moive objects.
     * 
     * @param filename name of the file
     * @return 	the ArrayList of Movie objects
     */
    public ArrayList<Movie> loadMovies(String filename) throws IOException {
        // Read csv files
        try {

        } catch (IOException e) {
            e.printStackTrace();
        }
        // Create a Movie object for each entry to store data
    }

    /**
     * Test the loadMovies method
     * 
     * @param filename name of the file
     * @return  the ArrayList of Movie objects
     */
    public ArrayList<Movie> testLoadMovies(String filename) {

    }

    /**
     * Process every record from the CSV file whose name
     * is file name and return an ArrayList of Moive objects.
     * 
     * @param filename name of the file
     * @return  the ArrayList of Movie objects
     */
    public ArrayList<Movie> testLoadRaters(String filename) {

    }

}