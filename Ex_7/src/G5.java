import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class G5 {

    public static void createAndShowGUI() {
        JFrame jf = new JFrame("My First Frame");
        jf.setLayout(new FlowLayout());

        JButton jb = new JButton("Hello!");
        jb.addActionListener(new MyActionListener()); // object is created
        jf.getContentPane().add(jb);

        JButton jb2 = new JButton("How are you?");
        jb2.addActionListener(new MyActionListener()); // object is created
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


class MyActionListener implements java.awt.event.ActionListener {

	public void actionPerformed(ActionEvent e) {
		System.out.println("Button = "+e.getActionCommand());
	}
	
}