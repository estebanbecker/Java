import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter a movie name:");

        String name = myObj.nextLine();

        String url = "https://www.themoviedb.org/search/movie?language=en&query=" + name;
        System.out.println(url);

        try{
            Document doc = Jsoup.connect(url).get();
        } catch (HttpStatusException ex) {
            System.out.println("Error: " + ex.getMessage());
            //Quit
            System.exit(0);
        }
        Document doc = Jsoup.connect(url).get();
        
        Elements movieresults = doc.getElementsByClass("card v4 tight");
        System.out.println(movieresults);

        List movieList = new ArrayList<Movie>();
        
        for (Element movieresult : movieresults) {
            Movie m = new Movie();

            m.setName(movieresult.getElementsByClass("result").text());
            m.setReleaseDate(movieresult.getElementsByClass("release_date").text());
            m.setDescription(movieresult.getElementsByClass("overview").text());
            m.setImage(movieresult.getElementsByClass("poster").attr("src"));
            m.setUrl(movieresult.getElementsByClass("result").attr("href"));

            System.out.println(m.getName());
            System.out.println(m.getReleaseDate());
            System.out.println(m.getDescription());
            System.out.println(m.getImage());
            System.out.println(m.getUrl());

            
        }

    }
}

class Movie {
    private String name;
    private String description;
    private String image;
    private String url;
    private String releaseDate;

    public Movie() {
        this.name = "";
        this.description = "";
        this.image = "";
        this.url = "";
        this.releaseDate = "";
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

}
