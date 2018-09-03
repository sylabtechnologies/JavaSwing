package xchatclient;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.util.*;

/**
 * @author D&Q
 */

public class xChatClientWin extends JFrame {
    
    private JTextField toplabel;
    JTextArea topdisplay;
    JTextArea bottomdisplay;
    
    JButton copyButton;
    JButton printButton;
    JButton exitButton;
    JButton connectButton;
    JButton disconnectButton;
    
    private int lastX, lastY;
    
    xChatClientWin() {
        super("Chat Client");
        
        JToolBar topbar = createTopBar();
        toplabel.setText("TESTER 1");
        toplabel.addMouseListener(new MoveToolBar());
        
        JToolBar bottombar = createBottomBar();

        JPanel textareas = createTextAreas();
        
        JPanel myPanel = new JPanel(new BorderLayout());
        myPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.lightGray));
        myPanel.setSize(new Dimension(550, 700));
        
        // add all components
        myPanel.add(topbar, BorderLayout.NORTH);
        myPanel.add(bottombar, BorderLayout.SOUTH);
        myPanel.add(textareas, BorderLayout.CENTER);        
        
        this.add(myPanel);
        
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setSize(new Dimension(550, 700));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }

    private JPanel createTextAreas() {

        JPanel panel = new JPanel();
        panel.setSize(new Dimension(550, 620));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        topdisplay = new JTextArea(25, 55);
        topdisplay.setBackground(new Color(0,113,238,244).brighter());
        topdisplay.setForeground(Color.white);
        topdisplay.setFont(topdisplay.getFont().deriveFont(16f));
        topdisplay.setEditable(false);
        topdisplay.append("test 1\n");

        JScrollPane scroll = new JScrollPane(topdisplay);

        topdisplay.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));
        scroll.setBorder(null);

        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scroll);
        
        bottomdisplay = new JTextArea(10, 55);
        bottomdisplay.setBackground(Color.cyan);
        bottomdisplay.setForeground(Color.white);
        bottomdisplay.setFont(bottomdisplay.getFont().deriveFont(16f));
        bottomdisplay.setLineWrap(true);        

        bottomdisplay.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));
        
        panel.add(bottomdisplay);
        
        return panel;
    }
    
    
    private JToolBar createTopBar() {
        
        JToolBar tb =  new JToolBar(); tb.setFloatable(false);
        tb.setBorder(BorderFactory.createRaisedBevelBorder());

        toplabel = new JTextField(5);
        toplabel.setFont(toplabel.getFont().deriveFont(16f));
        toplabel.setEditable(false);
        toplabel.setHorizontalAlignment(JTextField.CENTER);
        toplabel.setBorder(null);
        tb.add(toplabel);
        
        copyButton = new JButton(new ImageIcon("copy.gif"));
        tb.add(copyButton);
        
        printButton = new JButton(new ImageIcon("print.gif"));
        tb.add(printButton);
        
        exitButton = new JButton(new ImageIcon("exit.gif"));
        exitButton.addActionListener(new CloseListener());
        tb.add(exitButton);
        
        return tb;
    }

    private JToolBar createBottomBar() {

        JToolBar tb =  new JToolBar(); tb.setFloatable(false);
        tb.setBorder(BorderFactory.createRaisedBevelBorder());
        
        connectButton = new JButton(new ImageIcon("connect.gif"));
        tb.add(connectButton);
        connectButton.addActionListener(new SendEvent());
        
        disconnectButton = new JButton(new ImageIcon("disconnect.gif"));
        tb.add(Box.createHorizontalGlue());
        disconnectButton.addActionListener(new DisconnectEvent());
        tb.add(disconnectButton);
        
        return tb;
    }

    class CloseListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            System.exit(0);
        }
    }
    
    class SendEvent implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            
            String newtext = bottomdisplay.getText();

            if (newtext.length() > 0) {
                
                char lastchar = newtext.charAt(newtext.length()-1);
                
                if ( lastchar == '\n')
                    newtext = newtext.substring(0, newtext.length() - 1);
            }

            if (newtext.length() == 0) return;
                
            topdisplay.append(newtext + "\n");
            bottomdisplay.setText(null);
            bottomdisplay.requestFocus();
        }
        
    }

    class DisconnectEvent implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            bottomdisplay.setText(null);
            topdisplay.setText(null);
        }
    }


    
    class MoveToolBar implements MouseListener {
        @Override
        public void mousePressed(MouseEvent m_event)
        {
            lastX = m_event.getXOnScreen();
            lastY = m_event.getYOnScreen();
        }
        
        @Override
        public void mouseReleased(MouseEvent m_event)
        {
            int newX = m_event.getXOnScreen() - lastX;
            int newY = m_event.getYOnScreen() - lastY;

            newX += xChatClientWin.super.getLocationOnScreen().x;
            newY += xChatClientWin.super.getLocationOnScreen().y;
            
            xChatClientWin.super.setLocation(newX, newY);
        }
        
        @Override
        public void mouseEntered(MouseEvent me) {}
        @Override
        public void mouseClicked(MouseEvent me) {}
        @Override
        public void mouseExited(MouseEvent me) {}
    }
    
    
}
