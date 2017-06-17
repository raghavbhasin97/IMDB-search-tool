import java.util.ArrayList;

public class Movie {
	private String title,description,release_date,imdb_url,runtime,director,rating;
	private ArrayList<String> list_of_genres, list_of_actors,list_of_keywords;
	
	//HTML Components to decide if should extract
	private final String EQ = "=";
	private final String ANG = "<";
	Movie()
	{
		title = null;
		description = null;
		release_date = null;
	   imdb_url = null;
	   runtime = null;
	   director = null;
	   rating = null;
	   list_of_genres = new ArrayList<String>();
	   list_of_actors = new ArrayList<String>();
	   list_of_keywords = new ArrayList<String>();
	}

	private boolean should_reject(String text)
	{
		return text.contains(ANG) && text.contains(EQ);
	}
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = (this.director == null && !should_reject(director) ? director:this.director);
	}

	public String getReleased() {
		return release_date;
	}

	public void setReleased(String released) {
		this.release_date = (this.release_date == null && !should_reject(released) ? released:this.release_date);
	}

	/**
	 * @return the imdb_url
	 */
	public String getImdb_url() {
		return imdb_url;
	}

	/**
	 * @param imdb_url the imdb_url to set
	 */
	public void setImdb_url(String imdb_url) {
		this.imdb_url = imdb_url;
	}

	/**
	 * @return the genre
	 */
	public ArrayList<String> getGenre() {
		return list_of_genres;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		if(!list_of_genres.contains(genre))
		    list_of_genres.add(genre);
	}

	/**
	 * @return the actors
	 */
	public ArrayList<String> getActors() {
		return list_of_actors;
	}

	/**
	 * @param actors the actors to set
	 */
	public void setActors(String actors) {
		if(!list_of_actors.contains(actors))
			 list_of_actors.add(actors);
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = (this.description == null && !should_reject(description)
				? description.trim()
				.replace("&quot;", "\"").replace("&amp;", "&").replace("&apos", "\'")
				.replace("&gt", ">").replace("&lt", "<")
				:this.description);
	}

	/**
	 * @return the runtime
	 */
	public String getRuntime() {
		return runtime;
	}

	/**
	 * @param runtime the runtime to set
	 */
	public void setRuntime(String runtime) {
		this.runtime = (this.runtime == null && !should_reject(runtime) ? runtime:this.runtime);
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = (this.title == null && !should_reject(title) ? title.trim().replace("&quot;", "\"")
				.replace("&amp;", "&").replace("&apos", "\'").replace("&gt", ">")
				.replace("&lt", "<"):this.title);
	}

	/**
	 * @return the keyword
	 */
	public ArrayList<String> getKeyword() {
		return list_of_keywords;
	}

	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		if(!list_of_keywords.contains(keyword))
			 list_of_keywords.add(keyword);
	}
	
	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = (this.rating == null && !should_reject(rating) ? rating:this.rating);
	}
	
	
	@Override
	public String toString() 
	{  String res = "";
		if(title != null)
			res +="\nTitle: " + title + "\n";
		else 
			res +="\nTitle: " + "Not Found" + "\n";
		
		if(description != null)
			res += ("\nDescription: "+description) + "\n";
		else
			res += ("\nDescription: "+ "Not Found") + "\n";
		
		if(release_date != null)
			res += ("\nReleased: "+release_date) + "\n";
		else
			res += ("\nReleased: "+ "Not Found") + "\n";
		
		if(rating != null)
			res += ("\nRating: " + rating + "/10.0") + "\n";
		else
			res += ("\nRating: "+ "Not Found") + "\n";
		res += ("\nURL: "+this.imdb_url) + "\n";
		
		if(runtime != null)
			res += ("\nRuntime: " + runtime) + "\n";
		else
			res += ("\nRuntime: " + "Not Found") + "\n";
		
		if(director != null)
			res += ("\nDirector: "+ director) + "\n";
		else
			res += ("\nDirector: "+ "Not Found") + "\n"; 
		res += ("\nGenre: ");
		int size = list_of_genres.size();
		for(int i = 0; i < size; i++) {
			res += (list_of_genres.get(i));
				if(i != size - 1)
					res += (", ");
		}
		if(size == 0)
			res += "Not Found";
		res += "\n";
		res += ("\nActor: ");
		size = list_of_actors.size();
		for(int i = 0; i < size; i++) {
			res += (list_of_actors.get(i));
				if(i != size - 1)
					res += (", ");
		}
		if(size == 0)
			res += "Not Found";
		res += "\n";
		res += ("\nKeywords: " );
		size = list_of_genres.size();
		for(int i = 0; i < size; i++) {
			res += (list_of_genres.get(i));
				if(i != size - 1)
					res += (", ");
		}
		if(size == 0)
			res += "Not Found";
		res += "\n";
		return res;
	}
	
	
	
}
