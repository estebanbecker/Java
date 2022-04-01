import javax.swing.JButton;
import javax.swing.JFrame;

public class G2 {

    public static void createAndShowGUI() {
        JFrame jf = new JFrame("My First Frame");

        JButton jb = new JButton("Hello!");
        jf.getContentPane().add(jb);

        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() { createAndShowGUI(); }
        });        
    }

}