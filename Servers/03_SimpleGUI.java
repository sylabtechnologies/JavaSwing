package simplegui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

class MyPanel extends JPanel
{
    @Override
    public void paintComponent(Graphics g)
    {
        Image img = new ImageIcon("C:\\Users\\Dennis\\Desktop\\JavaBoost\\chalice.gif").getImage();
        g.drawImage(img, 2, 2, this);
    }
}

public class SimpleGUI implements ActionListener
{
    JButton button;
    JFrame frame;
    MyPanel panel = new MyPanel();
    int howmany = 1;        // w/ default initialization

    private void start()
    {
        frame = new JFrame();
        button = new JButton("click me");
        button.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(button);
        frame.setSize(300, 250);
        frame.setLocationRelativeTo(null);
        // frame.setUndecorated(true);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        howmany++;
        frame.getContentPane().remove(button);
        frame.getContentPane().add(panel);
        frame.setSize(527, 165);
        frame.repaint();
    }
    
    public static void main(String[] args)
    {
        SimpleGUI gui = new SimpleGUI();
        gui.start();
        
    }
}
