/**
 * p355
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
