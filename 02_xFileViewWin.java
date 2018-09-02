// need exceptions
// hierarchy of caret and custom highlighter
// exceptions

package xfileview;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*;
import java.util.*;

public class xFileViewWin extends JFrame {

    private String dirName;
    private String fileName;
    private int lastX, lastY;
    
    xFileViewWin(String dir, String fname)
    {
        super("Java File View");
        
        dirName  = dir;
        fileName = fname;
                
        JPanel myPanel = new JPanel(new BorderLayout());
        myPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.lightGray));
        myPanel.setSize(new Dimension(550, 700));
        
        JToolBar topbar = createToolBar();
        topbar.addMouseListener(new MoveToolBar());
        myPanel.add(topbar, BorderLayout.NORTH);
        
        JTextArea mytext = makeTextArea();
        myPanel.add(mytext, BorderLayout.CENTER);
        
        this.add(myPanel);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setSize(new Dimension(550, 700));
        
        JScrollPane myscroller = new JScrollPane(mytext);
        myscroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        myscroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        myPanel.add(myscroller);
        
        setLocationRelativeTo(null);
        useTheStyle();
        setVisible(true);
        
    }
    
    private JToolBar createToolBar() {
        
        JToolBar tb =  new JToolBar(); tb.setFloatable(false);
        
        JButton copyButton = new JButton(new ImageIcon("copy.gif"));
        tb.add(copyButton);
        
        JButton saveButton = new JButton(new ImageIcon("save.gif"));
        tb.add(saveButton);
        
        JButton printButton = new JButton(new ImageIcon("print.gif"));
        tb.add(printButton);
        
        JButton exitButton = new JButton(new ImageIcon("exit.gif"));
        exitButton.addActionListener(new CloseListener());
        tb.add(Box.createHorizontalGlue());
        tb.add(exitButton);
        
        return tb;
    }

    private JTextArea makeTextArea() {
        
        JTextArea ta = new JTextArea( );
        ta.setBackground(new Color(0,113,238,244).brighter());
        ta.setForeground(Color.white);
        ta.setFont(ta.getFont().deriveFont(16f));

        Highlighter hilite = ta.getHighlighter();
        
        Highlighter.HighlightPainter yPainter
        = new DefaultHighlighter.DefaultHighlightPainter( Color.yellow );
        
        try {
            int sel1 = ta.getSelectionStart();
            int sel2 = ta.getSelectionEnd();
            hilite.addHighlight(sel1, sel2, yPainter);
        } catch (BadLocationException ex) {
            System.out.println("cant highlight");
        }
        
        JScrollPane jsp = new JScrollPane(ta);
        this.getContentPane().add(jsp, BorderLayout.CENTER);
        
        File f = new File(dirName + fileName);
        
        try {
            BufferedReader reader =
                new BufferedReader(new FileReader(dirName + fileName));
            
            int i = 1, num = 0;
            String line = null;
            while ((line = reader.readLine()) != null) {
                String lineno = String.format("%02d  ", i % 100);
                ta.append(lineno + line + "\n");
                i++;
            }
            reader.close();

        } catch(Exception ex) {
            ErrWindow errwin = new ErrWindow("Couldn't read " + fileName);
            ex.printStackTrace();
        }        
        
        return ta;
    }
    
    class CloseListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            System.exit(0);
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

            newX += xFileViewWin.super.getLocationOnScreen().x;
            newY += xFileViewWin.super.getLocationOnScreen().y;
            
            xFileViewWin.super.setLocation(newX, newY);
        }
        
        @Override
        public void mouseEntered(MouseEvent me) {}

        @Override
        public void mouseClicked(MouseEvent me) {}
        
        @Override
        public void mouseExited(MouseEvent me) {}
        
    }

    private void useTheStyle()
    {
        String lnfName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        
        try
        {
            UIManager.setLookAndFeel(lnfName);
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch (Exception ex)
        {
            System.err.println(ex.toString());
        }
    }
    
}
