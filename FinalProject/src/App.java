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
import javax.swing.ImageIcon;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    static JFrame frame = new JFrame("TMDB");
    
    static JTextField textField = new JTextField(20);
    static JButton button = new JButton("Search");

    //Add a card layout manager to the frame
    static CardLayout cardLayout = new CardLayout();
    static JPanel cards = new JPanel(cardLayout);
    
    static int p = 0;

    static Elements movieresults;
    static JButton button2 = new JButton("Next");

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        
        GridLayout grid = new GridLayout(1, 3);
        JPanel panel = new JPanel(grid);

        JLabel label = new JLabel("Enter movie name:");        

        button2.setVisible(false);
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
                button.setText("Searching...");
                pool.submit(new Task2());
            }

        };

        textField.addActionListener(listener);
        button.addActionListener(listener);
        button2.addActionListener(listener2);

        panel.add(label);
        panel.add(textField);
        panel.add(button);

        frame.getContentPane().add(BorderLayout.NORTH, panel);
        
        frame.getContentPane().add(cards,   BorderLayout.CENTER);
        frame.getContentPane().add(button2, BorderLayout.SOUTH);
            
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        }

      


public static void show_data(int page){
    int min=page*5;
    int max=min+5;
    int rows;
        if (App.movieresults.size() > max ) {
            rows = 5;
        }else{
            rows = App.movieresults.size()-min;
        }

        GridLayout grid2 = new GridLayout(rows, 1);
        JPanel panel2 = new JPanel(grid2);
        panel2.removeAll();
        
        URL urlimage = null;
        Image image = null;
        GridLayout gridmovie = new GridLayout(0, 4);
        JPanel panelmovie = new JPanel(gridmovie);
        
        int i=min;

        while(App.movieresults.size() > i & i < max){

            Element movieresult = App.movieresults.get(i);

            Movie m = new Movie();

            gridmovie = new GridLayout(0, 4);
            panelmovie = new JPanel(gridmovie);
            panelmovie.removeAll();

            m.setName(movieresult.getElementsByClass("result").text());
            m.setReleaseDate(movieresult.getElementsByClass("release_date").text());
            m.setDescription(movieresult.getElementsByClass("overview").text());
            m.setImage(movieresult.getElementsByClass("poster").attr("src"));
            m.setUrl(movieresult.getElementsByClass("result").attr("href"));

            System.out.println(m.getName());

        
            try{
                
                urlimage = new URL("https://www.themoviedb.org"+m.getImage());
                //urlimage =new URL("https://www.themoviedb.org/t/p/w94_and_h141_bestv2/x2kzCjYqAPkqLSbv29fBQKE33Fl.jpg");
                System.out.println(urlimage);
                image = ImageIO.read(urlimage);
                if(image != null){
                    JLabel label = new JLabel(new ImageIcon(image));
                    panelmovie.add(label);
                }else{
                    JLabel label = new JLabel("No image");
                    panelmovie.add(label);
                }
            } catch (MalformedURLException ex) {
                System.out.println("Malformed URL");
            } catch (IOException iox) {
                System.out.println("Can not load file");
            }
            
            

            JLabel labelmovie = new JLabel(m.getName());
            panelmovie.add(labelmovie);
            if(m.getReleaseDate() != ""){
                JLabel labelreleasedate = new JLabel(m.getReleaseDate());
                panelmovie.add(labelreleasedate);
            }else{
                JLabel labelreleasedate = new JLabel("No release date");
                panelmovie.add(labelreleasedate);
            }
            JLabel labeldescription = new JLabel("<html>"+m.getDescription()+"</html>");
            panelmovie.add(labeldescription);
            
            panel2.add(panelmovie);

            i++;

            
        }

        

        if(App.movieresults.size() == 0){
            JLabel label = new JLabel("No results");
            panel2.add(label);
        }
        
        App.cards.removeAll();
        App.cards.add(panel2, "movie");
        App.cards.revalidate();
        App.cards.repaint();

        App.frame.pack();
        App.frame.setVisible(true);

        App.button.setText("Search");

        if(App.movieresults.size() > max){
            App.button2.setVisible(true);
        }else{
            App.button2.setVisible(false);
        }

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
            
            App.show_data(0);
            App.p = 0;
    }
}

class Task2 implements Runnable {

    private static int liczba;
	int l;
	public Task2() {
		l=++liczba;
	}

    public void run() {
        App.p++;
        App.show_data(App.p);
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
