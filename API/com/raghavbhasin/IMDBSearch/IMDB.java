package com.raghavbhasin.IMDBSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * This class represents an IMDB Object that can be initialized with a movie name
 * or the URL for movie page on IMDB, and can be used to scrape the web for 
 * movie details.
 * 
 * The methods work in O(n) time, where n is the number of line for HTML
 * code, and hence is pretty fast given a decent Internet connection speed.
 * 
 * @version 1.0
 * @author RaghavBhasin
 *
 */
public class IMDB {
	
	// HTML TAGS for scraping the IMDB site
	private final String IMDB_SEARCH = "http://www.imdb.com/find?ref_=nv_sr_fn&q=";
	private final String SEARCH_PARAM = "name=\"tt\"";
	private final String SEARCH_TAG = "/title/";
	private final String PREFIX_URL = "http://www.imdb.com";
	private final String LANG = "<";
	private final String RANG = ">";
	private final String NAME_TAG = "itemprop=\"name\"";
	private final String GENRE_TAG = "itemprop=\"genre\"";
	private final String DIRECTOR_TAG = "itemprop=\"director\"";
	private final String TITLE_TAG = "property=\'og:title\' content=\"";
	private final String TYPE_TAG = "content=\"video.movie\"";
	private final String DATE_TAG = "itemprop=\"datePublished\" content=\"";
	private final String DURATION_TAG = "itemprop=\"duration\"";
	private final String KEYWORD_TAG = "itemprop=\"keywords\"";
	private final String DESCRIPTION_TAG = "class=\"summary_text\"";
	private final String QUOTE = "\"";
	private final String CONTENT = "content=\"";
	private final String TIME_TAG = "\"PT";
	private final String RATING_TAG = "itemprop=\"ratingValue\"";
	private final String SPACE = " ";
	private final String PLUS = "+";
	
	private URL host, hostSearch;
	private URLConnection connection, connectionSearch;
	private BufferedReader reader = null, readerSearch = null;
	private String address = null;
	private boolean do_actor = false;
	private boolean should_extract = false;
	private int number_of_actors = 0;
	private Movie movie = new Movie();

	/**
	 * Initializes an IMDB Object.
	 * 
	 * @param name of the movie or the URL to movie page
	 * @param isFirstParamMovieName does the first parameter represent a movie 
	 * 		 name. If false, then the first parameter is treated as the URL to
	 * 		 movie search page.
	 */
	public IMDB(String name, boolean isFirstParamMovieName) {

		// If true, takes step to scrape web to determine the IMDB link for
		// the movie and then setup the movie object
		if (isFirstParamMovieName) {
			//Setup the search url
			String target = createSearchTarget(name);
			String url = IMDB_SEARCH + target + "&s=all";
			//Open connection for reading HTML code
			try {
				hostSearch = new URL(url);
				connectionSearch = hostSearch.openConnection();
				readerSearch = new BufferedReader(new InputStreamReader
						         (connectionSearch.getInputStream()));
			} catch (IOException e) {}
			// After that call scrapeWeb to get the first URL that matches
			// the movie search.
			try {
				scrapeWeb();
			} catch (Exception e) {
			}
			//ELSE treat the parameter name as URL to movie page
		} else {
			//Initialize the movie object with the IMDB URL
			reinit(name);
		}

	}
	
	/**
	 * Scrapes the HTML code line for URL
	 * @param line to scrape for IMDB link
	 * @return partial URl to the movie page
	 */
	private String scrape(String line) {
		int start = line.indexOf(SEARCH_TAG);
		String intm = line.substring(start);
		int end = line.indexOf(RANG) - 1;
		return intm.substring(0, end);
	}
	
	/**
	 * Used to scrape the web and extract a URL that is contained on the 
	 * search page for the movie.
	 * @return The URL to movie page on IMDB
	 * @throws Exception to make the caller handle any Null pointer or such
	 * 		  exceptions since these may occur if no URL is found
	 *         or the last URL was just scraped.
	 */
	private String scrapeWeb() throws Exception {
		String line = null;
		String data = null;
		
		//Read HTML Code
		try {
			while ((line = readerSearch.readLine()) != null) {

				if (line.contains(SEARCH_PARAM))
					should_extract = true;

				if (should_extract && line.contains(SEARCH_TAG)) {
					String link = PREFIX_URL + scrape(line);
					data = link;
					break;
				}

			}
		}

		catch (IOException e) {
			// Occurs only if the Internet connection is lost
			
		}
		if (data != null) {
			// If an URL was found Initialize the readers object with it
			address = data;
			reinit(data);
		}
		return address;
	}
	
	/**
	 * Initialize the readers with movie target page.
	 * @param url of the movie page
	 */
	private void reinit(String url) {
		movie.reset();
		movie.setIMDBUrl(url);
		try {
			host = new URL(url);
			connection = host.openConnection();
			reader = new BufferedReader(new InputStreamReader
					   (connection.getInputStream()));
		} catch (IOException e) {

		}
	}
	
	/**
	 * Returns a string with the spaces replaced with +, that the search URL 
	 * can be made out of.
	 * @param name of the movie with spaces
	 * @return search string with + instead of " "
	 */
	private String createSearchTarget(String name) {
			return name.trim().replace(SPACE, PLUS);
	}
	
	/**
	 * Gets the duration of the movie in minutes
	 * @param line containing the HTML code
	 * @return the duration of the movie
	 */
	private String getDuration(String line) {
		int start = line.indexOf(TIME_TAG) + TIME_TAG.length();
		String intermediateLine = line.substring(start);
		int end = intermediateLine.indexOf(QUOTE) - 1;
		return intermediateLine.substring(0, end);
	}
	
	/**
	 * tries to scrape the release date from the given code line.
	 * @param line containing the HTML code
	 * @return Release date of the movie
	 */
	private String getReleaseDate(String line) {
		int start = line.indexOf(CONTENT);
		String intermediateLine = line.substring(start);
		start = intermediateLine.indexOf(QUOTE) + 1;
		intermediateLine = intermediateLine.substring(start);
		int end = intermediateLine.indexOf(QUOTE);
		return intermediateLine.substring(0, end);
	}

	/**
	 * Tries to scrape the page for IMDB title that usually is like
	 * Name of the Movie (Year of release)
	 * @param line containing the HTML code
	 * @return The Title of the Movie
	 */
	private String getTitle(String line) {
		int start = line.indexOf(TITLE_TAG);
		String intermediateLine = line.substring(start);
		start = intermediateLine.indexOf(QUOTE) + 1;
		intermediateLine = intermediateLine.substring(start);
		int end = intermediateLine.indexOf(QUOTE);
		return intermediateLine.substring(0, end);
	}

	/**
	 * A general method to extract most of the components for movie based on the
	 *  structure and ID of the HTML tag
	 * @param line of the HTML code
	 * @param tag to take as the reference while extracting
	 * @return The data enclosed within the tag
	 */
	private String extractFromHTMLTag(String line, String tag) {
		int start = line.indexOf(tag);
		String intermediateLine = line.substring(start);
		start = intermediateLine.indexOf(RANG) + 1;
		int end = intermediateLine.indexOf(LANG);
		return intermediateLine.substring(start, end);
	}
	
	
	// Public accessible API methods
	
	/**
	 * Checks weather you are on a movie page or not
	 * @return true if the current page ready to scrape contains a movie
	 */
	public boolean isMovie() {
		String line = null;
		//Try finding the tag that suggests a movie page
		try {
			while ((line = reader.readLine()) != null) {
				//If found return true
				if (line.contains(TYPE_TAG)) {
					//Reinit the reader so the page can be read again
					// by other methods
					reinit(address);
					return true;
				}
			}
		}
		catch (IOException e) { }
		reinit(address);
		return false;
	}
	
	/**
	 * Gives back a movie object with all the required data
	 * @return A Movie object initialized with all the data found from the 
	 * IMDB page.
	 */
	public Movie getMovie() {
		String line = null;

		try {
			//Read the HTML Code line by line an compare the tags
			// for a match then call the extract method on success.
			while ((line = reader.readLine()) != null) {
				if (line.contains(DESCRIPTION_TAG)) {
					String des = reader.readLine();
					movie.setDescription(des);
				} else if (line.contains(KEYWORD_TAG)) {
					try {
						String key = extractFromHTMLTag(line, KEYWORD_TAG);
						movie.setKeyword(key);
					} catch (Exception e) {
					}
				} else if (line.contains(DURATION_TAG)) {
					try {
						String time = getDuration(line);
						movie.setRuntime(time);
					} catch (Exception e) {
					}
				} else if (line.contains(DATE_TAG)) {
					try {
						String rel = getReleaseDate(line);
						movie.setReleased(rel);
					} catch (Exception e) {
					}
				} else if (line.contains(TITLE_TAG)) {
					try {
						String title = getTitle(line);
						movie.setTitle(title);
					} catch (Exception e) {
					}
				} else if (line.contains(DIRECTOR_TAG)) {
					reader.readLine();
					line = reader.readLine();
					if (line.contains(NAME_TAG)) {
						try {
							String dir = extractFromHTMLTag(line, NAME_TAG);
							movie.setDirector(dir);
							do_actor = true;
						} catch (Exception e) {
						}
					}
				} else if (do_actor) {
					if (line.contains(NAME_TAG)) {
						try {
							String actor = extractFromHTMLTag(line, NAME_TAG);
							movie.setActors(actor);
							number_of_actors++;
							if (number_of_actors > 8)
								do_actor = false;

						} catch (Exception e) {
						}
					}
				} else if (line.contains(GENRE_TAG)) {
					try {
						String genre = extractFromHTMLTag(line, GENRE_TAG);
						movie.setGenre(genre);
					} catch (Exception e) {
					}
				} else if (line.contains(RATING_TAG)) {
					try {
						String ratings = extractFromHTMLTag(line, RATING_TAG);
						movie.setRating(ratings);
					} catch (Exception e) {
					}
				}
			}
		}

		catch (IOException e) {

		}
		//Reinit the Movie object so other methods can read the page again
		reinit(movie.getIMDBUrl());
		return movie;
	}

	/**
	 * Gets the current page's URL
	 * @return The URL of the movie page currently being taken into 
	 * Consideration.
	 */
	public String getURL() {
		return address;
	}
	
	/**
	 * Just a security method, which lets you get the movie object again 
	 * without calling the getMovie() method which will scrape the page again
	 * to initialize the object with same data.
	 * 
	 * <p>Calling this method before the web scrape will return an object
	 * with all null fields with an exception of URL field.</p>
	 * 
	 * @return Movie object containing the data
	 */
	public Movie getExtractedMovie() {
		return movie;
	}
	
	/**
	 * Moves the reader to the next IMDB page found in the search for the movie 
	 * name. In case of search by URL this return false always
	 * 
	 * @return true if it successfully moved on to the next movie page false 
	 * otherwise.
	 */
	public boolean nextSearch() {
		String initialURL = address;
		if (initialURL == null)
			return false;

		try {
			scrapeWeb();
		} catch (Exception e) {
		}
		String nextURL = address;

		if (initialURL.equals(nextURL) || nextURL == null)
			return false;
		
		return true;
	}
	
	
}
