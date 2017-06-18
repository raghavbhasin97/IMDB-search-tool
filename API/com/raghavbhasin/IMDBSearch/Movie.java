package com.raghavbhasin.IMDBSearch;

import java.util.ArrayList;
/**
 * This class represents an IMDB Movie, which contains the following components:
 * 1). Title: The name of the movie
 * 2). Description: Plot summary for the movie
 * 3). Release Date: When the movie was released
 * 4). IMDB URL: IMDB address of the page corresponding to the movie
 * 5). Runtime: Duration of the movie in Minutes
 * 6). Director: Name of the director for the movie.
 * 7). Rating: How good the movie was on a scale of 1.0 to 10.0
 * 8). Genres: A list of all the genres that the movie falls under
 * 9). Actors: A list of people who were in the movie (some not all at times).
 * 10). Keywords: A list of keywords that match the movie search.
 * 
 * One or more of these fields may be null at times, representing that the
 * data was not found on IMDB site.
 * 
 * @version 1.0
 * @author RaghavBhasin
 *
 */
public class Movie {
	private String title, description, releaseDate, IMDBUrl, runtime, director
	               , rating;
	private ArrayList<String> listOfGenres, listOfActors, listOfKeywords;

	// HTML Components to decide if should extract
	private final String EQ = "=";
	private final String ANG = "<";

	/**
	 * Constructor for movie object
	 */
	Movie() {
		title = null;
		description = null;
		releaseDate = null;
		IMDBUrl = null;
		runtime = null;
		director = null;
		rating = null;
		listOfGenres = new ArrayList<String>();
		listOfActors = new ArrayList<String>();
		listOfKeywords = new ArrayList<String>();
	}

	/**
	 * Checks if the item being added is clean of HTML tags. 
	 * @param text that is to be added
	 * @return true if the parameters don't meet the requirements i.e. contain
	 * HTML elements.
	 * 
	 */
	private boolean shouldReject(String text) {
		return text.contains(ANG) && text.contains(EQ);
	}
	
	/**
	 * Outer world's access to name of director
	 * @return Director of the movie
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Used to set the director of the movie
	 * @param director of the movie
	 */
	 void setDirector(String director) {
		this.director = (this.director == null && !shouldReject(director) 
				          ? director : this.director);
	}
	
	/**
	 * Outer world's access to release date of the movie
	 * @return release date of the movie
	 */
	public String getReleased() {
		return releaseDate;
	}

	/**
	 * Used to set the release date of the movie
	 * @param release date of the movie
	 */
	 void setReleased(String release) {
		this.releaseDate = (this.releaseDate == null && !shouldReject(release)
				              ? release : this.releaseDate);
	}

	/**
	 * Outer world's access to IMDB URL of the movie page
	 * @return  IMDB URL of the movie page
	 */
	public String getIMDBUrl() {
		return IMDBUrl;
	}

	/**
	 * Used to set the IMDB URL for the movie page.
	 * @param IMDBUrl: The URL for movie page.       
	 */
	 void setIMDBUrl(String IMDBUrl) {
		this.IMDBUrl = IMDBUrl;
	}

	/**
	 * Outer world's access to list of genres that the movie fits
	 * @return  A list of genres that the movie fits
	 */
	public ArrayList<String> getGenre() {
		return listOfGenres;
	}

	/**
	 * Used to insert a genre that the movie fits to the list of all genres.
	 * @param genre that the movie fits          
	 */
	 void setGenre(String genre) {
		if (!listOfGenres.contains(genre))
			listOfGenres.add(genre);
	}

	/**
	 * Outer world's access to the list containing names of actors who worked in
	 * the movie 
	 * @return A list of all actors that worked in the movie (most/some of them).
	 */
	public ArrayList<String> getActors() {
		return listOfActors;
	}

	/**
	 * Add an actor to the list of those who worked in the movie
	 * @param actor who worked in the movie        
	 */
	 void setActors(String actor) {
		if (!listOfActors.contains(actor))
			listOfActors.add(actor);
	}

	/**
	 * Outer world's access to the plot summary for the movie
	 * @return  Description of the movie/plot-summary for the movie.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Used to set the plot-summary for the movie.
	 * @param description or plot-summary for the movie
	 *          
	 */
	 void setDescription(String description) {
		this.description = (this.description == null && !shouldReject(description)
				? description.trim().replace("&quot;", "\"").replace("&amp;", "&")
						 .replace("&apos", "\'")
						.replace("&gt", ">").replace("&lt", "<")
				: this.description);
	}

	/**
	 * Outer world's access to duration of the movie
	 * @return Runtime/Duration of the movie
	 */
	public String getRuntime() {
		return runtime;
	}

	/**
	 * Sets the runtime/duration of the movie in minutes
	 * @param runtime or duration of the movie(in minutes).
	 *       
	 */
	 void setRuntime(String runtime) {
		this.runtime = (this.runtime == null && !shouldReject(runtime) ? runtime 
				         : this.runtime);
	}

	/**
	 * Outer world's access to name of the movie
	 * @return The title/name of the movie
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the name/title of the movie
	 * @param title of the movie				 
	 */
	 void setTitle(String title) {
		this.title = (this.title == null && !shouldReject(title) ? title.trim()
				.replace("&quot;", "\"")
				.replace("&amp;", "&").replace("&apos", "\'").replace("&gt", ">")
				.replace("&lt", "<") : this.title);
	}

	/**
	 * Outer world's access to a list of keyword that best describe the movie
	 * @return A list of keyword that the movie fits
	 */
	public ArrayList<String> getKeyword() {
		return listOfKeywords;
	}

	/**
	 * Adds a keyword that can be used to describe the move
	 * @param keyword describing the movie        
	 */
	 void setKeyword(String keyword) {
		if (!listOfKeywords.contains(keyword))
			listOfKeywords.add(keyword);
	}

	/**
	 * Outer world's access to the rating of this movie
	 * @return The rating of the movie on a scale of 1.0 to 10.0
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * Sets the rating for the movie
	 * @param rating of the movie          
	 */
	 void setRating(String rating) {
		this.rating = (this.rating == null && !shouldReject(rating) 
				        ? rating : this.rating);
	}
	 
	 /**
	  * Clears the current object
	  */
	 void reset()
	 {
		 title = null;
			description = null;
			releaseDate = null;
			IMDBUrl = null;
			runtime = null;
			director = null;
			rating = null;
			listOfGenres = new ArrayList<String>();
			listOfActors = new ArrayList<String>();
			listOfKeywords = new ArrayList<String>();
	 }
	
	@Override
	public String toString() {
		String res = "";
		if (title != null)
			res += "\nTitle: " + title + "\n";
		else
			res += "\nTitle: " + "Not Found" + "\n";

		if (description != null)
			res += ("\nDescription: " + description) + "\n";
		else
			res += ("\nDescription: " + "Not Found") + "\n";

		if (releaseDate != null)
			res += ("\nReleased: " + releaseDate) + "\n";
		else
			res += ("\nReleased: " + "Not Found") + "\n";

		if (rating != null)
			res += ("\nRating: " + rating + "/10.0") + "\n";
		else
			res += ("\nRating: " + "Not Found") + "\n";
		res += ("\nURL: " + this.IMDBUrl) + "\n";

		if (runtime != null)
			res += ("\nRuntime: " + runtime + " Minutes") + "\n";
		else
			res += ("\nRuntime: " + "Not Found") + "\n";

		if (director != null)
			res += ("\nDirector: " + director) + "\n";
		else
			res += ("\nDirector: " + "Not Found") + "\n";
		res += ("\nGenre: ");
		int size = listOfGenres.size();
		for (int i = 0; i < size; i++) {
			res += (listOfGenres.get(i));
			if (i != size - 1)
				res += (", ");
		}
		if (size == 0)
			res += "Not Found";
		res += "\n";
		res += ("\nActor: ");
		size = listOfActors.size();
		for (int i = 0; i < size; i++) {
			res += (listOfActors.get(i));
			if (i != size - 1)
				res += (", ");
		}
		if (size == 0)
			res += "Not Found";
		res += "\n";
		res += ("\nKeywords: ");
		size = listOfKeywords.size();
		for (int i = 0; i < size; i++) {
			res += (listOfKeywords.get(i));
			if (i != size - 1)
				res += (", ");
		}
		if (size == 0)
			res += "Not Found";
		res += "\n";
		return res;
	}

}
