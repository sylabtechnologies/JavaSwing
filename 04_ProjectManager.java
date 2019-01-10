/*
* * *** *** MAP IT AND THINK OF USEFUL ACTIONS
 */
package jmanager;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class JManager extends JFrame
{
    // mouse coords
    private int pressedX, pressedY;
    // exit button
    ActionListener myExit;
    // last open x y
    Point prevLaunch;
    final int launchDelta = 30;
    
    JLayeredPane desktop;
    Vector popups = new Vector();

    public JManager() {
        super("Project Manager");
        setSize(1400, 700);
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));   // #N
        
        Container contentPane = getContentPane();

        JToolBar jtb = new JToolBar();
        jtb.add(new CutAction(this));
        jtb.add(new CopyAction(this));
        jtb.add(new PasteAction(this));
        jtb.add(Box.createHorizontalGlue());
        JButton exitBtn = new JButton(new ImageIcon("images/exit.gif"));
        myExit = new CloseListener();
        exitBtn.addActionListener( myExit);
        jtb.add(exitBtn);
        
        // add drag toll bar listeners #N
        jtb.addMouseListener(new MoveToolBar());
        jtb.addMouseMotionListener(new DragToolBar());
        contentPane.add(jtb, BorderLayout.NORTH);

        // Add our LayeredPane object for the Internal frames
        desktop = new JDesktopPane();
        contentPane.add(desktop, BorderLayout.CENTER);

        // launch tracker
        prevLaunch = new Point(0,0);
        
        addDirFrame("C:\\Users\\Dennis\\Desktop");
        
    }

    private void addDirFrame(String dir)
    {
        JDirList mylist = new JDirList(dir);
        JDirFrame df = new JDirFrame(new JTableModel(mylist));
        df.setVisible(true);
        
        df.setLocation(prevLaunch);
        prevLaunch.x += launchDelta;
        prevLaunch.y += launchDelta;
        
        df.table.addMouseListener(new DirLauncher());

        df.setClosable(true);
        // df.addInternalFrameListener(il);
        
        popups.addElement(df);
        desktop.add(df, new Integer(2));  // Keep sites on top for now
        
        df.toFront();
        df.requestFocus();

    }
    
    
    public void addPageFrame(String name, Point start) {
        PageFrame pf = new PageFrame(name, this);
        
        /* track launch ACTUALLY KEEP ALWAYS MAXY MAXX AND KEEP TRACK OF OPEN CLOSE
        if (prevLaunch.x == 0 && prevLaunch.y == 0)
        {
            prevLaunch.x = start.x + launchOffset;
            prevLaunch.y = start.y + launchOffset;
        }
        else
        {
            prevLaunch.x += launchOffset;
            prevLaunch.y += launchOffset;
        }
        
        pf.setLocation(prevLaunch);
        
        */
        
        pf.setVisible(true);
        desktop.add(pf, new Integer(1));
        pf.setIconifiable(true);
        popups.addElement(pf);
    }

    public JInternalFrame getCurrentFrame() {
        for (int i = 0; i < popups.size(); i++) {
            JInternalFrame currentFrame = (JInternalFrame)popups.elementAt(i);
            if (currentFrame.isSelected()) {
                return currentFrame;
            }
        }
        return null;
    }

    // drag window by toolbar
    class DragToolBar implements MouseMotionListener {
        @Override
        public void mouseMoved(MouseEvent m_event) {}
       
        @Override
        public void mouseDragged(MouseEvent m_event)
        {
            int newX = JManager.super.getLocation().x + m_event.getX()- pressedX;
            int newY = JManager.super.getLocation().y + m_event.getY()- pressedY;

            JManager.super.setLocation(newX, newY);
        }
    }    

    class MoveToolBar implements MouseListener {
        @Override
        public void mousePressed(MouseEvent m_event)
        {
            pressedX = m_event.getX();
            pressedY = m_event.getY();
        }
        
        @Override
        public void mouseReleased(MouseEvent m_event) {}
        
        @Override
        public void mouseEntered(MouseEvent me) {}

        @Override
        public void mouseClicked(MouseEvent me) {}
        
        @Override
        public void mouseExited(MouseEvent me) {}
        
    }

    public static void main(String args[]) {
        JManager mgr = new JManager();
        mgr.setLocationRelativeTo(null);
        mgr.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        mgr.setVisible(true);
    }
 
    // add double click
    private class DirLauncher extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent me) {
            
            // get frame
            JInternalFrame cf = JManager.this.getCurrentFrame();
            
            if (!(cf instanceof JDirFrame)) return;
            
            JDirFrame df = (JDirFrame) cf;
            Point point = me.getPoint();
            
            if (me.getClickCount() >= 2 )
            {
                int row = df.table.rowAtPoint(point);
                int col = df.table.columnAtPoint(point);

                // System.out.println(me.getClickCount());

                String fullname = df.model.dirData.home.getAbsolutePath() + "\\"
                    + (String) df.table.getValueAt(row, col);
                
                // try to launch something
                if (TryToLaunch.try2run(fullname) == 0)
                    return;
                
                File file = new File(fullname);
                if (file.isDirectory()) {
                    JManager.this.addDirFrame(file.getAbsolutePath());
                }

            }
            
        }

    }

    
}
