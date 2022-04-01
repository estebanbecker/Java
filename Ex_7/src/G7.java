import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class G7 {

    public static void createAndShowGUI() {

    	ActionListener myActionListener = new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("Button = "+e.getActionCommand());
    		}    		
    	};
    	
    	JFrame jf = new JFrame("My First Frame");
        jf.setLayout(new FlowLayout());

        JButton jb = new JButton("Hello!");
        jb.addActionListener(myActionListener);
        jf.getContentPane().add(jb);

        JButton jb2 = new JButton("How are you?");
        jb2.addActionListener(myActionListener);
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