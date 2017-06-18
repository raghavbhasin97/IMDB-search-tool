package Sample;
import com.raghavbhasin.IMDBSearch.*;


import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the movie name: ");
		String name = s.nextLine();
		System.out.println("Please Wait! finding the page on IMDB...");
		IMDB find_page = new IMDB(name,true);
		System.out.println("Going to page!");
		System.out.println("Extracting data from the page...");
		Movie movie = find_page.getMovie();
		System.out.println(movie.toString());

	   while(find_page.nextSearch())
	   {
	   	System.out.println("Going to page!");
			System.out.println("Extracting data from the page...");
			 movie = find_page.getMovie();
			 
			System.out.println(movie.toString());
	   }
	 
		s.close();
		

	}

}
