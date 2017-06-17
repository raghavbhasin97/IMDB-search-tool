package Sample;
import com.raghavbhasin.IMDBSearch.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the movie name: ");
		String name = s.nextLine();
		System.out.println("Please Wait! finding the page on IMDB...");
		ArrayList<String> urls = new ArrayList<String>();
		IMDB find_page = new IMDB(name,true);
		String url = find_page.getURL();
		urls.add(url);
	   while(find_page.nextSearch())
	   {
	   	url = find_page.getURL();
			urls.add(url);
	   }
		
	   for(String str: urls)
			System.out.println(str);
			
		
		
		if(url == null)
		{				
			System.out.println("Movie not found on IMDB");
			System.exit(0);
		}
		System.out.println("Found a page!");
		System.out.println("Extracting data from the page...");
		Movie movie = find_page.getMovie();
		System.out.println(movie.toString());
		s.close();
		

	}

}
