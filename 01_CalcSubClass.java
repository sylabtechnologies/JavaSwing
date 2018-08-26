/*
 * Attach UI to 
 * https://github.com/sylabtechnologies/June_test/blob/master/Codility2/03_polishcalc.cpp
 */
package xcalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalcSubClass extends JFrame
{
    private static final long My_Version = 110L;

    private JTextField numDisplay;
    private JButton[] numButton = new JButton[10];
    private JButton[] opButton  = new JButton[4];
    private String[]  opLabel = {"+", "-", "x", "\u00F7"};
    private JButton enterNumber;
    private JButton popNumber;
    private JButton dupNumber;
    private JButton negateNumber;
    
    private ModelClass MyModel;
    
    public CalcSubClass(ModelClass model)
    {
        super("Calculator");
        MyModel = model;
        
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();

        numDisplay = new JTextField();
        numDisplay.setPreferredSize(new Dimension(130, 30));
        numDisplay.setEditable(false);
        numDisplay.setBackground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(numDisplay, gbc);

        // prep num & oper buttons
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        int x_start = 0;
        int y_start = 2;
        for (int i = 0; i < numButton.length; i++)
        {
            numButton[i] = new JButton(Integer.toString(i));

            if (i == 0)
            {
                gbc.gridx = 0;
                gbc.gridy = 5;
            }
            else
            {
                gbc.gridx = x_start;
                gbc.gridy = y_start;
                x_start++; if (x_start > 2) { x_start = 0; y_start++; }
            }
            
            add(numButton[i], gbc);
            numButton[i].addActionListener(new MyButtonListener());
        }
        
        for (int i = 0; i < opLabel.length; i++)
        {
            opButton[i] = new JButton(opLabel[i]);
            gbc.gridx = i;
            gbc.gridy = 1;
            if (i == 3 ) gbc.gridwidth = 2;
            add(opButton[i], gbc);
            opButton[i].addActionListener(new MyButtonListener());            
        }

        enterNumber = new JButton("Enter");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(enterNumber, gbc);
        enterNumber.addActionListener(new MyButtonListener());
        
        dupNumber = new JButton("DUP");
        gbc.gridx = 3;
        gbc.gridy = 2;
        add(dupNumber, gbc);
        dupNumber.addActionListener(new MyButtonListener());
        
        popNumber = new JButton("POP");
        gbc.gridx = 3;
        gbc.gridy = 3;
        add(popNumber, gbc);
        popNumber.addActionListener(new MyButtonListener());
        
        negateNumber = new JButton("+/-");
        gbc.gridx = 3;
        gbc.gridy = 4;
        add(negateNumber, gbc);
        negateNumber.addActionListener(new MyButtonListener());
       
    }

    private class MyButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            int code = MyModel.execOper(ae.getActionCommand());
            
            if (code != -1)
                numDisplay.setText(Integer.toString(MyModel.getResult()));
            else
                numDisplay.setText("ERR");
        }
    };    
    
}
