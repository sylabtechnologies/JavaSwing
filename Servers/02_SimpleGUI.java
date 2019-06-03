/**
 * just p355
 * 
 * *** just see messaging! **
 * 
 * 1. content pane is exactly the pane for content, there are others
 * 
 * 2. OK TOTALLY CLEAR - THE PARADIGM IS TO CREATE INSTANCE AND RUN IT
 * BUTTON MUST BE INSTANCE VARIABLE
 * 
 * *** IT IS REALLY #NICE - how many will be instance specifit, classic!
 * 
 * **** totally can lanuch independently  ui & uiStart #N
 * 
 * IT IS APPARENT STRATEGY W/ IMPLEMENTING ACTION LISTENER!
 * => 3. check messaging! *** ALL JUST MESSAGING AND ABSTRACTION **
 * 
 * 
 */

package simplegui;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;

public class SimpleGUI implements ActionListener
{
    JButton button;
    int howmany = 1;        // w/ default initialization

    private void start()
    {
        JFrame frame = new JFrame();
        button = new JButton("click me 1st time");
        button.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(button);
        frame.setSize(300, 250);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        howmany++;
        
        String suffix = "th";
        
        if (howmany == 2)
            suffix = "nd";
        else if (howmany == 3)
            suffix = "rd";
            
        button.setText("click me " + Integer.toString(howmany) + suffix + " time");
        
    }
    
    public static void main(String[] args)
    {
        SimpleGUI gui = new SimpleGUI();
        gui.start();
        
    }

}
