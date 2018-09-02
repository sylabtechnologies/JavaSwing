package xfileview;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ErrWindow extends JFrame {
    private JLabel errLabel;
    
    ErrWindow(String err) {
        super("Error!");
        this.setSize(300,200);
        this.getContentPane().setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        
        errLabel = new JLabel("", JLabel.CENTER);
        errLabel.setSize(300,200);
        errLabel.setFont(new Font("Serif", Font.PLAIN, 19));        
        errLabel.setText(err);
        
        this.add(errLabel, BorderLayout.CENTER);

        this.setAlwaysOnTop(true);
        this.setVisible(true);
        
        this.addMouseListener(new ClickDetector());
    }
   
    private class ClickDetector implements MouseListener{

        @Override
        public void mouseEntered(MouseEvent me) {
            errLabel.setForeground(Color.red);
        }
        
        @Override
        public void mouseExited(MouseEvent me) {
            errLabel.setForeground(Color.black);
        }
    
        @Override
        public void mouseClicked(MouseEvent me) {
            System.exit(0);
        }
    
        @Override
        public void mouseReleased(MouseEvent me) {}

        @Override
        public void mousePressed(MouseEvent me) {
            System.exit(0);
        }
        
    }
    
}

