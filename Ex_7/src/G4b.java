import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class G4b {

    public static void createAndShowGUI() {
        JFrame jf = new JFrame("My First Frame");
        jf.setLayout(new FlowLayout());

        JButton jb = new JButton("Hello!");
        jf.getContentPane().add(jb);

        JButton jb2 = new JButton("How are you?");
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

