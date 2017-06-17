import java.util.ArrayList;

public class Movie {
	private String title,description,released,imdb_url,runtime,director,rating;
	private ArrayList<String> genre, actors,keyword;
	
	Movie()
	{
		title = null;
		description = null;
		released = null;
	   imdb_url = null;
	   runtime = null;
	   director = null;
	   rating = null;
	   genre = new ArrayList<String>();
	   actors = new ArrayList<String>();
	   keyword = new ArrayList<String>();
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = (this.director == null ? director:this.director);
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = (this.released == null ? released:this.released);
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
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre.add(genre);
	}

	/**
	 * @return the actors
	 */
	public ArrayList<String> getActors() {
		return actors;
	}

	/**
	 * @param actors the actors to set
	 */
	public void setActors(String actors) {
		this.actors.add(actors);
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
		this.description = (this.description == null ? description:
			this.description.trim().replace("&quot;", "\"").replace("&amp;", "&")
			.replace("&apos", "\'").replace("&gt", ">").replace("&lt", "<"));
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
		this.runtime = (this.runtime == null ? runtime:this.runtime);
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
		this.title = (this.title == null ? title:this.title);
	}

	/**
	 * @return the keyword
	 */
	public ArrayList<String> getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword.add(keyword);
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
		this.rating = (this.rating == null ? rating:this.rating);
	}
	
	public void print() 
	{
		System.out.println("\nTitle: " + title);
		System.out.println("\nDescription: "+this.description);
		System.out.println("\nReleased: "+this.released);
		System.out.println("\nRating: "+this.rating + "/10.0");
		System.out.println("\nURL: "+this.imdb_url);
		System.out.println("\nRuntime: " + this.runtime);
		System.out.println("\nDirector: "+this.director);
		System.out.print("\nGenre: ");
		for(String s:this.genre)
		{
			System.out.print(s + ",");
		}
		System.out.print("\nActor: ");
		for(String s:this.actors)
		{
			System.out.print(s + ",");
		}
		System.out.print("\nKeywords: " );
		for(String s:this.keyword)
		{
			System.out.print(s + ", ");
		}
		System.out.println();
	}

	
}
