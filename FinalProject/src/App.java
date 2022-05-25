import java.awt.*;
import javax.swing.*;

import javax.imageio.ImageIO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import java.awt.BorderLayout;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    static JFrame frame = new JFrame("TMDB");
    
    static JTextField textField = new JTextField(20);
    static JButton button = new JButton("Search");

    static JButton buttonsort1 = new JButton("Sort by Popularity");
    static JButton buttonsort2 = new JButton("Sort by Date");
    static JButton buttonsort3 = new JButton("Sort by Name");


    //Add a card layout manager to the frame
    static CardLayout cardLayout = new CardLayout();
    static JPanel cards = new JPanel(cardLayout);
    
    static int p = 0;

    static Elements movieresults;

    static String sorting_option = new String();

    static ArrayList<Movie> movies = new ArrayList<Movie>();

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        
        buttonsort1.setVisible(false);
        buttonsort2.setVisible(false);
        buttonsort3.setVisible(false);

        sorting_option = "popularity.desc";

        GridLayout grid = new GridLayout(2, 3);
        JPanel panel = new JPanel(grid);

        JLabel label = new JLabel("Enter movie name:");        
        

        //Create ActionListener
        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                button.setText("Searching...");
                pool.submit(new Task());      
            }

        };
        ActionListener listener2 = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                button.setText("Sorting...");
                switch(e.getActionCommand()){
                    case "Sort by Popularity":
                        
                        if(sorting_option == "popularity.desc"){
                            sorting_option = "popularity.asc";
                        }
                        else{
                            sorting_option = "popularity.desc";
                        }
                        break;
                    case "Sort by Date":
                        
                        if(sorting_option == "release_date.asc"){
                            sorting_option = "release_date.desc";
                        }
                        else{
                            sorting_option = "release_date.asc";
                        }
                        break;
                    case "Sort by Name":
                        
                        if(sorting_option == "original_title.asc"){
                            sorting_option = "original_title.desc";
                        }
                        else{
                            sorting_option = "original_title.asc";
                        }
                        break;
                }
                pool.submit(new Task2());
            }

        };

        textField.addActionListener(listener);
        button.addActionListener(listener);

        buttonsort1.addActionListener(listener2);
        buttonsort2.addActionListener(listener2);
        buttonsort3.addActionListener(listener2);

        panel.add(label);
        panel.add(textField);
        panel.add(button);
        panel.add(buttonsort1);
        panel.add(buttonsort2);
        panel.add(buttonsort3);

        frame.getContentPane().add(BorderLayout.NORTH, panel);
        
        frame.getContentPane().add(cards,   BorderLayout.CENTER);
            
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        }

    }

class Task implements Runnable {

    private static int liczba;
	int l;
	public Task() {
		l=++liczba;
	}
   
    public void run() {
        String name = App.textField.getText();
        String url = "https://www.themoviedb.org/search/movie?language=en&query=" + name;
            Document doc = null;
            try{
                doc = Jsoup.connect(url).get();
            } catch ( IOException ex) {
                System.out.println("Error: " + ex.getMessage());
                //Quit
                System.exit(0);
            }
            
            App.movieresults = doc.getElementsByClass("card v4 tight");

                
                GridLayout grid2 = new GridLayout(App.movieresults.size(), 4);
                JPanel panel2 = new JPanel(grid2);
                //Limit panel2 width
                panel2.setPreferredSize(new Dimension(1000, 200 * App.movieresults.size()));
                
                JScrollPane scroll = new JScrollPane(panel2 , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                
                panel2.removeAll();
                
                URL urlimage = null;
                Image image = null;
               // GridLayout gridmovie = new GridLayout(0, 4);
                //JPanel panelmovie = new JPanel(gridmovie);
                
                App.movies.clear();

                for (Element movieresult : App.movieresults) {
                    Movie m = new Movie();
        
                    m.setName(movieresult.getElementsByClass("result").text());
                    m.setReleaseDate(movieresult.getElementsByClass("release_date").text());
                    m.setDescription(movieresult.getElementsByClass("overview").text());
                    m.setImage(movieresult.getElementsByClass("poster").attr("src"));
                    m.setUrl(movieresult.getElementsByClass("result").attr("href"));
                    App.movies.add(m);
                }

                int i=0;
        
                while(App.movieresults.size() > i){
                    

                    Movie m=App.movies.get(i);
                
                    try{
                        
                        urlimage = new URL("https://www.themoviedb.org"+m.getImage());
                        
                    
                        image = ImageIO.read(urlimage);
                        m.setrImage(image);
                        if(image != null){
                            JLabel label = new JLabel(new ImageIcon(image));
                            panel2.add(label);
                        }else{
                            JLabel label = new JLabel("No image");
                            panel2.add(label);
                        }
                    } catch (MalformedURLException ex) {
                        System.out.println("Malformed URL");
                    } catch (IOException iox) {
                        System.out.println("Can not load file");
                    }
                    
                    
        
                    JLabel labelmovie = new JLabel(m.getName());
                    panel2.add(labelmovie);
                    if(m.getReleaseDate() != ""){
                        JLabel labelreleasedate = new JLabel(m.getReleaseDate());
                        panel2.add(labelreleasedate);
                    }else{
                        JLabel labelreleasedate = new JLabel("No release date");
                        panel2.add(labelreleasedate);
                    }
                    JLabel labeldescription = new JLabel("<html>"+m.getDescription()+"</html>");
                    panel2.add(labeldescription);
        
                    i++;
        
                    
                }
        
                
                
                    
                App.buttonsort1.setVisible(true);
                App.buttonsort2.setVisible(true);
                App.buttonsort3.setVisible(true);

                if(App.movieresults.size() == 0){
                    JLabel label = new JLabel("No results");
                    panel2.add(label);
                    App.buttonsort1.setVisible(false);
                    App.buttonsort2.setVisible(false);
                    App.buttonsort3.setVisible(false);
                }
                
                App.cards.removeAll();
                App.cards.add(scroll, "movie");
                App.cards.revalidate();
                App.cards.repaint();
        
                App.frame.pack();
                App.frame.setVisible(true);
        
                App.button.setText("Search");
        
            }
    }


class Task2 implements Runnable {

    private static int liczba;
	int l;
	public Task2() {
		l=++liczba;
	}

    public void run() {
        
        GridLayout grid2 = new GridLayout(App.movieresults.size(), 4);
        JPanel panel2 = new JPanel(grid2);
        //Limit panel2 width
        panel2.setPreferredSize(new Dimension(1000, 200 * App.movieresults.size()));
        
        JScrollPane scroll = new JScrollPane(panel2 , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        panel2.removeAll();
        
        int i=0;

        //Create a copy of movies
        ArrayList<Movie> movies = new ArrayList<Movie>(App.movies);


        //Sort movies by name
        if (App.sorting_option == "original_title.desc") {
            movies.sort(Comparator.comparing(Movie::getName).reversed());
        } else if (App.sorting_option == "original_title.asc") {
            movies.sort(Comparator.comparing(Movie::getName));
        } else if (App.sorting_option == "release_date.desc") {
            movies.sort(Comparator.comparing(Movie::getTimeStamp).reversed());
        } else if (App.sorting_option == "release_date.asc") {
            movies.sort(Comparator.comparing(Movie::getTimeStamp));
        } else if (App.sorting_option == "popularity.asc"){
            //reverse the list
            Collections.reverse(movies);

        }
        while(App.movieresults.size() > i){


            Movie m = new Movie();
            m = movies.get(i);

        
            if(m.getrImage() != null){
                JLabel label = new JLabel(new ImageIcon(m.getrImage()));
                panel2.add(label);
            }else{
                JLabel label = new JLabel("No image");
                panel2.add(label);
            }
            

            JLabel labelmovie = new JLabel(m.getName());
            panel2.add(labelmovie);
            if(m.getReleaseDate() != ""){
                JLabel labelreleasedate = new JLabel(m.getReleaseDate());
                panel2.add(labelreleasedate);
            }else{
                JLabel labelreleasedate = new JLabel("No release date");
                panel2.add(labelreleasedate);
            }
            JLabel labeldescription = new JLabel("<html>"+m.getDescription()+"</html>");
            panel2.add(labeldescription);

            i++;

            
        }
        
        App.cards.removeAll();
        App.cards.add(scroll, "movie");
        App.cards.revalidate();
        App.cards.repaint();

        App.frame.pack();
        App.frame.setVisible(true);

        App.button.setText("Search");
    }

    
}

class Movie {
    private String name;
    private String description;
    private String image;
    private String url;
    private String releaseDate;
    private Image rimage;

    public Image getrImage() {
        return this.rimage;
    }

    public void setrImage(Image rimage) {
        this.rimage = rimage;
    }

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

    public String getTimeStamp() {
        //convert string to date, "June 28, 2022" to 2022-06-28
        System.out.println(getName());
        System.out.println(getReleaseDate());
        if(getReleaseDate() == ""){
            return "99999999";
        }else{

            String date = getReleaseDate();
            String[] parts = date.split(" ");
            String day = parts[1]; // day
            day = day.split(",")[0];
            String month = parts[0]; // month
            String year = parts[2]; // year

            switch(month){
                case "January":
                    month = "01";
                    break;
                case "February":
                    month = "02";
                    break;
                case "March":
                    month = "03";
                    break;
                case "April":
                    month = "04";
                    break;
                case "May":
                    month = "05";
                    break;
                case "June":
                    month = "06";
                    break;
                case "July":
                    month = "07";
                    break;
                case "August":
                    month = "08";
                    break;
                case "September":
                    month = "09";
                    break;
                case "October":
                    month = "10";
                    break;
                case "November":
                    month = "11";
                    break;
                case "December":
                    month = "12";
                    break;
            }


            String date_string = year+"-"+month+"-"+day;
            return date_string;
        
        }
    }

}
