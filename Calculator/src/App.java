
import java.awt.BorderLayout;

import java.awt.*;
import javax.swing.*;


public class App {

    public static void createAndShowGUI() {
        JFrame jf = new JFrame("My First Frame");

        //Create a graphical user interface (GUI) for a calculator
        
        JTextField jtf = new JTextField();
        jf.getContentPane().add(jtf,BorderLayout.NORTH);

        //Create a GridLayout manager with four rows and four columns
        GridLayout grid = new GridLayout(4,4);
        JPanel jp = new JPanel(grid);


        //Create buttons
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton button0 = new JButton("0");
        JButton buttonAdd = new JButton("+");
        JButton buttonSub = new JButton("-");
        JButton buttonMul = new JButton("*");
        JButton buttonDiv = new JButton("/");
        JButton buttonEql = new JButton("=");
        JButton buttonClr = new JButton("C");

        //Add buttons to the panel
        jp.add(button1);
        jp.add(button2);
        jp.add(button3);
        jp.add(buttonAdd);
        jp.add(button4);
        jp.add(button5);
        jp.add(button6);
        jp.add(buttonSub);
        jp.add(button7);
        jp.add(button8);
        jp.add(button9);
        jp.add(buttonMul);
        jp.add(buttonClr);
        jp.add(button0);
        jp.add(buttonEql);
        jp.add(buttonDiv);

        //Add panel to the frame
        jf.getContentPane().add(jp,BorderLayout.CENTER);

        

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