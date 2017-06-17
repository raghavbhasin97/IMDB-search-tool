import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class FetchSearch {
	private URL host;
	private URLConnection connection;
	private BufferedReader reader = null;
	
	private final String IMDB_SEARCH = "http://www.imdb.com/find?ref_=nv_sr_fn&q=";
	private final String SEARCH_TAG = "/title/";
	private final String PREFIX_URL = "http://www.imdb.com";
	private boolean do_actor = false;
	private int num = 0;
	Movie movie;
	
	
	FetchSearch(String name, boolean is_name)
	{	
		String url = name;
		if(is_name)
		{
		String target = create_search_target(name);
		url = IMDB_SEARCH + target + "&s=all";
		} else
		{
			movie = new Movie();
			movie.setImdb_url(name);
		}
		try {
			host = new URL(url);
			connection = host.openConnection();
			reader = new BufferedReader(new InputStreamReader
			(connection.getInputStream()));
		} catch (IOException e) {
			
		}
	}
	
	
	private  String scrape(String line)
	{
		int start = line.indexOf(SEARCH_TAG);
		String intm = line.substring(start);
		int end = line.indexOf(">") - 1;
		return intm.substring(0, end);
	}
	
	
	public String scrape_web()
	{
		String line = null;
		String data = null;
		
		try {
			while ((line = reader.readLine()) != null) {
				
				if(line.contains(SEARCH_TAG)) {
					String link = PREFIX_URL + scrape(line);
					data = link;
					break;
				}
				
			}
		} 
		
		catch (IOException e) {
			
		}
		
		return data;
	}
	
	private String create_search_target(String name)
	{
		String[] list = name.split(" ");
		String res = "";
		for(String part:list)
		{
			res += "+" + part.toLowerCase();
		}
		return res.substring(1);
	}
	private String get_keyword(String line)
	{
		int start = line.indexOf("itemprop=\"keywords\"");
		String im = line.substring(start);
		start = im.indexOf(">") + 1;
		int end = im.indexOf("<");
		return im.substring(start, end);
	}
	
	private String get_time(String line)
	{
		int start = line.indexOf(">") + 1;
		String im = line.substring(start);
		int end = im.indexOf("</time>");
		return im.substring(0,end);
	}
	
	private String get_released(String line)
	{
		int start = line.indexOf("content=\"");
		String intermediate_string = line.substring(start);
		start = intermediate_string.indexOf("\"") + 1;
		intermediate_string = intermediate_string.substring(start);
		int end = intermediate_string.indexOf("\"");
		return intermediate_string.substring(0, end);
	}
	
	private String get_title(String line)
	{
		int start = line.indexOf("property=\'og:title\' content=\"");
		String im = line.substring(start);
		start = im.indexOf("\"") + 1;
		im = im.substring(start);
		int end = im.indexOf("\"");
		return im.substring(0, end);
	}
	
	private String get_director(String line)
	{
		int start = line.indexOf("itemprop=\"name\"");
		String im = line.substring(start);
		 start = im.indexOf(">") + 1;
		int end = im.indexOf("<");
		return im.substring(start, end);
	}
	private String get_actor(String line)
	{
		int start = line.indexOf("itemprop=\"name\"");
		String im = line.substring(start);
		start = im.indexOf(">") + 1;
		int end = im.indexOf("<");
		return im.substring(start, end);
	}
	
	private String get_genre(String line)
	{
		int start = line.indexOf("itemprop=\"genre\"");
		String im = line.substring(start);
		start = im.indexOf(">") + 1;
		int end = im.indexOf("<");
		return im.substring(start, end);
	}
	
	public void read()
	{
		String line = null;
		
		try {
			while ((line = reader.readLine()) != null) {
				if(line.contains("itemprop=\"description\""))
				{ String des = reader.readLine();
				  movie.setDescription(des);
				}
				else if (line.contains("itemprop=\"keywords\""))
				{	
					try{
					String key = get_keyword(line);
					movie.setKeyword(key);
				  }catch(Exception e) {}
				}
				else if (line.contains("itemprop=\"duration\""))
				{
					try{
						String time = get_time(line);
						movie.setRuntime(time);
					}catch(Exception e) {}
				}
				else if(line.contains("itemprop=\"datePublished\" content=\""))
				{
					try{
						String rel = this.get_released(line);
						movie.setReleased(rel);
					}catch(Exception e) {}
				}
				else if(line.contains("property=\'og:title\' content=\""))
				{
					try{
						String title = this.get_title(line);
						movie.setTitle(title);
					}catch(Exception e) {}
				}
				else if(line.contains("itemprop=\"director\""))
				{
					reader.readLine();
					line = reader.readLine();
					if(line.contains("itemprop=\"name\""))
					{
						try{
							String dir = get_director(line);
							movie.setDirector(dir);
							do_actor = true;
						}catch (Exception e) {}
					}
				}
				else if (line.contains("itemprop=\"name\"") && do_actor )
				{
					try{
						String actor = get_actor(line);
						movie.setActors(actor);
						num++;
						if(num > 3) do_actor = false;
					}catch (Exception e) {}
				}
				else if(line.contains("itemprop=\"genre\""))
				{
					try{
						String genre = get_genre(line);
						movie.setGenre(genre);
					}catch (Exception e) {}
				}
			}
		} 
		
		catch (IOException e) {
			
		}
		movie.print();
	}
}
