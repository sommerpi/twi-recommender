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
import java.io.*;

public class FirstRatings {
    public static void main(String[] args) throws IOException {
    	FirstRatings test = new FirstRatings();
    	test.testLoadMovies("./data/ratedmoviesfull.csv");
    	System.out.print("\n");
    	test.testLoadRaters("./data/ratings.csv");
    }
    
    
    /**
     * Process every record from the CSV file whose name
     * is file name and return an ArrayList of Moive objects.
     * 
     * @param filename name of the file
     * @return 	the ArrayList of Movie objects
     */
    public ArrayList<Movie> loadMovies(String filename) throws IOException {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        // Read csv files
        Reader in = new FileReader(filename);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().withSkipHeaderRecord().parse(in);
        for (CSVRecord record : records) {
            String id = record.get(0);
            String title = record.get(1);
            String year = record.get(2);
            String country = record.get(3);
            String genre = record.get(4);
            String director = record.get(5);
            int minutes = Integer.parseInt(record.get(6));
            String poster = record.get(7);

            Movie newMovie = new Movie(id, title, year, genre, director, country, poster, minutes);
            movies.add(newMovie);
        }
		return movies;
    }

    
    /**
     * Test the loadMovies method: print number of movies, determine number of
     * Comedy movies, determine number of movies that are greater than 150 minutes
     * in length, determine maximum number of movies by any director.
     * 
     * @param filename name of the file
     * @throws IOException 
     */
    public void testLoadMovies(String filename) throws IOException {
    	ArrayList<Movie> movies = new FirstRatings().loadMovies(filename);
    	int numOfMovies = 0;
    	int numOfComedies = 0;
    	int numOfLongMovies = 0;
    	Map<String, Integer> directorMoviesNum = new HashMap<String, Integer>();
    	for (Movie movie : movies) {
    		// count the number of comedies
    		if (movie.getGenres().contains("Comedy")) numOfComedies++;
    		// count the number of movies with length of more than 150 minutes
    		if (movie.getMinutes() > 150) numOfLongMovies++;
    		// keep track of directors and number of movies they've directed
    		String[] directors = movie.getDirector().split(",");
    		for (String director : directors) {
    			directorMoviesNum.put(director.trim(), directorMoviesNum.get(director) == null ? 1 : directorMoviesNum.get(director) + 1);
    		}
    		numOfMovies++;
    		//System.out.print(numOfMovies + " " + movie.getID() + " " + movie.getTitle() + "\n");
    	}
    	System.out.print("Total number of movies: " + numOfMovies + "\n");
    	System.out.print("Number of comedies: " + numOfComedies + "\n");
    	System.out.print("Number of movies with length of more than 150 minutes: " + numOfLongMovies + "\n");
    	// find the director that directed the most movies
    	Map.Entry<String, Integer> maxDirectorMovies = null;
    	for (Map.Entry<String, Integer> entry : directorMoviesNum.entrySet()) {
    		if (maxDirectorMovies == null || entry.getValue() > maxDirectorMovies.getValue()) {
    			maxDirectorMovies = entry;
    		}
    	}
    	System.out.print(maxDirectorMovies.getKey() + " directed " + maxDirectorMovies.getValue() + " movies" + "\n");
    }
//
    
    /**
     * Process every record from the CSV file whose name
     * is file name and return an ArrayList of Rater objects.
     * 
     * @param filename name of the file
     * @return  the ArrayList of Rater objects
     */
    public ArrayList<Rater> loadRaters(String filename) throws IOException {
        ArrayList<Rater> raters = new ArrayList<Rater>();
        Map<String, Rater> idRater = new HashMap<String, Rater>();
        // Read csv files
        Reader in = new FileReader(filename);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().withSkipHeaderRecord().parse(in);
        for (CSVRecord record : records) {
        	String id = record.get(0);
        	String item = record.get(1);
        	double rating = Double.parseDouble(record.get(2));
        	
        	Rater rater;
        	if (idRater.containsKey(id)) {
        		// if existed add a new rating if the rater
        		rater = idRater.get(id);
        	} else {
        		// if not existed, add new rater to map and array list
        		rater = new Rater(id);
        		idRater.put(id, rater);
        		raters.add(rater);
        	}
            rater.addRating(item, rating);
        }
		return raters;
    }
    
    
    /**
     * Test the loadRaters method: print number of raters, find number of ratings of
     * a particular rater, find maximum number of ratings by any rater, find
     * maximum number of ratings with the raters, find the number of ratings a
     * movie has, determine number of different movies that has been rated.
     * 
     * @param filename name of the file
     * @throws IOException 
     */
    public void testLoadRaters(String filename) throws IOException {
    	ArrayList<Rater> raters = new FirstRatings().loadRaters(filename);
    	int numOfRaters = 0;
    	
    	String aRater = "193";
    	int targetRaterRatings = 0;
    	
    	int maxRatings = 0;
    	ArrayList<Rater> maxRaters = new ArrayList<Rater>();
    	
    	String targetItem = "1798709";
    	int numItemRatings = 0;
    	// use a set to count number of rated items
    	Set<String> itemSet = new HashSet<String>();
    	
    	for (Rater rater : raters) {
//    		System.out.print(rater.getID() + " " + rater.numRatings() + "\n");
//    		ArrayList<String> items = rater.getItemsRated();
//    		for (String item : items) {
//    			System.out.print(item + " " + rater.getRating(item) + " | ");
//    		}
//    		System.out.print("\n");
    		// count the number of ratings of specific rater
    		if (rater.getID().equals(aRater)) targetRaterRatings += rater.numRatings();
    		// find raters with max number of ratings
    		if (rater.numRatings() > maxRatings) {
    			maxRatings = rater.numRatings();
    			maxRaters.clear();
    			maxRaters.add(rater);
    		} else if (rater.numRatings() == maxRatings) {
    			maxRaters.add(rater);
    		}
    		// count ratings of a specific movie
    		if (rater.getRating(targetItem) > 0) numItemRatings++;
    		// count number of rated movie
    		itemSet.addAll(rater.getItemsRated());
    		numOfRaters++;
    	}
    	System.out.print("Number of raters: " + numOfRaters + "\n");
    	System.out.print("Number of ratings for rater " + aRater + ": " + targetRaterRatings + "\n");
    	// print all raters with max number of ratings
    	StringBuffer buffer = new StringBuffer();
    	for (Rater rater : maxRaters) {
    		buffer.append(rater.getID() + " ");
    	}
    	System.out.print("Raters with most ratings are raters " + buffer.toString() + ": " + maxRatings + "\n");
    	System.out.print("Movie " + targetItem + " has been rated " + numItemRatings + " times" + "\n");
    	System.out.print("Number of rated movies: " + itemSet.size() + "\n");
    }

}