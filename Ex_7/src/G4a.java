import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class G4a {

    public static void createAndShowGUI() {
        JFrame jf = new JFrame("My First Frame");

        JButton jb = new JButton("Hello!");
        jf.getContentPane().add(jb, BorderLayout.NORTH);

        JButton jb2 = new JButton("How are you?");    // BorderLayout.CENTER
        jf.getContentPane().add(jb2);
        
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