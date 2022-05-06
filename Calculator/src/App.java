
import java.awt.BorderLayout;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App {

    public static void createAndShowGUI() {
        JFrame jf = new JFrame("Calculator");

        //Create a graphical user interface (GUI) for a calculator

        JTextField jtf = new JTextField("0");
        jtf.setHorizontalAlignment(JTextField.RIGHT);
        jtf.setEditable(false);
        jf.getContentPane().add(jtf,BorderLayout.NORTH);

        //Create action listener
        ActionListener myActionListener = new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()) {
                    case "+":
                        if(pressedEqual) {
                            firstNumber = result;
                            pressedEqual = false;
                        }
                        if(secondNumber != 0) {
                            firstNumber = MakeOperation(firstNumber, secondNumber, operation, false);
                            secondNumber = 0;
                        }
                        operation = "+";
                        pressedNumber = false;
                        jtf.setText(String.valueOf(firstNumber));
                        NoOperation = false;
                        pressedOperator = true;
                        pressedEqual2 = false;
                        break;
                    case "-":
                        if(pressedEqual) {
                            firstNumber = result;
                            pressedEqual = false;
                        }
                        if(secondNumber != 0) {
                            firstNumber = MakeOperation(firstNumber, secondNumber, operation, false);
                            secondNumber = 0;
                        }
                        operation = "-";
                        pressedNumber = false;
                        jtf.setText(String.valueOf(firstNumber));
                        NoOperation = false;
                        pressedOperator = true;
                        pressedEqual2 = false;
                        break;
                    case "*":
                        if(pressedEqual) {
                            firstNumber = result;
                            pressedEqual = false;
                        }
                        if(secondNumber != 0) {
                            firstNumber = MakeOperation(firstNumber, secondNumber, operation, false);
                            secondNumber = 0;
                        }
                        operation = "*";
                        pressedNumber = false;
                        jtf.setText(String.valueOf(firstNumber));
                        NoOperation = false;
                        pressedOperator = true;
                        pressedEqual2 = false;
                        break;
                    case "/":
                        if(pressedEqual) {
                            firstNumber = result;
                            pressedEqual = false;
                        }
                        if(secondNumber != 0) {
                            firstNumber = MakeOperation(firstNumber, secondNumber, operation, false);
                            secondNumber = 0;
                        }
                        operation = "/";
                        jtf.setText(String.valueOf(firstNumber));
                        pressedNumber = false;
                        NoOperation = false;
                        pressedOperator = true;
                        pressedEqual2 = false;
                        break;
                    case "=":
                        if(pressedNumber == false){
                            secondNumber = firstNumber;
                            pressedNumber = true;
                        }
                        if(NoOperation == true) {
                            jtf.setText(String.valueOf(firstNumber));
                            pressedNumber = false;
                        }
                        else if(pressedOperator == true){
                            result = MakeOperation(firstNumber,secondNumber , operation, true);
                            jtf.setText(String.valueOf(result));
                            pressedEqual = true;
                            pressedNumber = false;
                        }
                        else if(pressedEqual == false) {
                            result = MakeOperation(firstNumber, secondNumber, operation, false);
                            if(operation == "-" && secondNumber == 0) {
                                firstNumber = -firstNumber;
                            }
                            jtf.setText(String.valueOf(result));
                            pressedEqual = true;
                            pressedNumber = false;
                        }else{
                            result = MakeOperation(firstNumber,result , operation, true);
                            jtf.setText(String.valueOf(result));
                            pressedNumber = false;
                            pressedEqual = true;
                        }
                        secondNumber = 0;
                        pressedOperator = false;
                        pressedEqual2 = true;

                        break;
                    case "C":
                        firstNumber = 0;
                        secondNumber = 0;
                        result = 0;
                        operation = "";
                        pressedNumber = true;
                        pressedEqual2 = false;
                        pressedEqual = false;
                        NoOperation = true;
                        pressedOperator = false;
                        jtf.setText(String.valueOf(firstNumber));
                        break;
                    default:
                        if(pressedEqual2) {
                            firstNumber = 0;
                            secondNumber = 0;
                            result = 0;
                            operation = "";
                            pressedNumber = true;
                            pressedEqual2 = false;
                            pressedEqual = false;
                            NoOperation = true;
                            pressedOperator = false;
                            jtf.setText(String.valueOf(firstNumber));
                        }
                        
                        if (pressedNumber == true) {
                            if(operation == "-" && secondNumber == 0) {
                                firstNumber = firstNumber * -10 - Integer.parseInt(e.getActionCommand());
                            }else{
                                firstNumber = firstNumber * 10 + Integer.parseInt(e.getActionCommand());
                            }
                            
                        }else{
                            secondNumber = firstNumber;
                            pressedNumber = true;
                            if(operation == "-" && secondNumber == 0) {
                                firstNumber = -Integer.parseInt(e.getActionCommand());
                            }else{
                                firstNumber = Integer.parseInt(e.getActionCommand());
                            }
                        }
                        pressedEqual = false;
                        pressedEqual2 = false;
                        pressedOperator = false;
                        jtf.setText(String.valueOf(firstNumber));
                        
                        break;
                }
    		}
            private String operation = "";
            private int firstNumber=0;
            private int secondNumber=0;
            private boolean pressedNumber=true;
            private boolean pressedEqual=false;
            private int result=0;
            private boolean NoOperation = true;
            private boolean pressedOperator = false;
            private boolean pressedEqual2 = false;

            

            private int MakeOperation (int firstNumber, int secondNumber, String operation, Boolean repeat) {
                if(operation == "-" && secondNumber == 0 && firstNumber < 0 && repeat == false) {
                    return firstNumber;
                }
                switch (operation) {
                    case "+":
                        return firstNumber + secondNumber;
                    case "-":
                        return secondNumber - firstNumber;
                    case "*":
                        return firstNumber * secondNumber;
                    case "/":
                        return (int)(secondNumber / firstNumber);
                    default:
                        return 0;
                }
            }
    	};



        //Create a GridLayout manager with four rows and four columns
        GridLayout grid = new GridLayout(4,4);
        JPanel jp = new JPanel(grid);


        //Create buttons
        String list_button[] = {"1","2","3","+","4","5","6","-","7","8","9","*","0","=","/","C"};

        for (int i = 0; i < list_button.length; i++) {
            JButton jb = new JButton(list_button[i]);
            jb.addActionListener(myActionListener);
            jp.add(jb);
        }
        
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