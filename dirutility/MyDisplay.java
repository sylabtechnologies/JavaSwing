// #d: add correct highligher
// https://stackoverflow.com/questions/10191723/highlight-one-specific-row-line-in-jtextarea

package dirutility;
import javax.swing.*; 
import java.awt.*; 
import java.util.ArrayList;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class MyDisplay extends JFrame
{

    MyDisplay(String dir, ArrayList<String> result)
    {
        super(dir);
        
        JPanel myPanel = new JPanel(new BorderLayout());
        myPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.lightGray));
        myPanel.setSize(new Dimension(650, 700));
        
        JTextArea myTArea = makeTextArea(result);
        myTArea.setCaretPosition(0);
        myPanel.add(myTArea, BorderLayout.CENTER);

        add(myPanel);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(550, 700));
        
        JScrollPane myscroller = new JScrollPane(myTArea);
        myscroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        myscroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        myPanel.add(myscroller);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private static JTextArea makeTextArea(ArrayList<String> result)
    {
        JTextArea ta = new JTextArea( );
        ta.setBackground(new Color(0,113,238,244).brighter());
        ta.setForeground(Color.yellow.brighter().brighter());
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
        
        try
        {
            for (int i = 0; i < result.size(); i++)
                ta.append(result.get(i) + "\n");

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
        
        return ta;
    }    
}
