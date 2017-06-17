import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the movie name: ");
		String name = s.nextLine();
		System.out.println("Please Wait! finding the page on IMDB...");
		FetchSearch find_page = new FetchSearch(name,true);
		String url = find_page.scrape_web();
		if(url == null)
		{
			System.out.println("Movie Not Found On IMDB");
			System.exit(0);
		}
		System.out.println("Found a page!");
		System.out.println("Extracting data from the page...");
		FetchSearch page_data = new FetchSearch(url,false);
		page_data.read();
		
		
		s.close();

	}

}
