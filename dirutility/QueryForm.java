/**
 * very informative - see dry ops in
 * https://www.geeksforgeeks.org/java-swing-simple-user-registration-form/
 * 
 */

package dirutility;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.util.ArrayList;

public class QueryForm extends JFrame implements ActionListener 
{ 
    // Components of the Form 
    private Container container; 
    private JLabel title; 
    private JLabel getDir; 
    private JTextField textDir; 
    private JLabel keys; 
    private JTextField textKeys; 
    private JButton sub;
    private JButton reset;
    
    private final static String CLEAR_OP = "";
    
    public QueryForm() 
    { 
        setTitle("DirFind"); 
        setSize(650, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); 

        container = getContentPane(); 
        container.setLayout(null); 

        title = new JLabel("Find:"); 
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 
        title.setSize(300, 30); 
        title.setLocation(300, 30); 
        container.add(title); 

        getDir = new JLabel("Directory:"); 
        getDir.setFont(new Font("Arial", Font.PLAIN, 20)); 
        getDir.setSize(150, 20); 
        getDir.setLocation(100, 100); 
        container.add(getDir); 

        textDir = new JTextField(); 
        textDir.setFont(new Font("Arial", Font.PLAIN, 15)); 
        textDir.setSize(350, 20); 
        textDir.setLocation(200, 100); 
        container.add(textDir); 

        keys = new JLabel("Keys"); 
        keys.setFont(new Font("Arial", Font.PLAIN, 20)); 
        keys.setSize(100, 20); 
        keys.setLocation(100, 150); 
        container.add(keys); 

        textKeys = new JTextField(); 
        textKeys.setFont(new Font("Arial", Font.PLAIN, 15)); 
        textKeys.setSize(150, 20); 
        textKeys.setLocation(200, 150); 
        container.add(textKeys); 

        sub = new JButton("Submit"); 
        sub.setFont(new Font("Arial", Font.PLAIN, 15)); 
        sub.setSize(100, 20); 
        sub.setLocation(150, 200); 
        sub.addActionListener(this); 
        container.add(sub);         

        reset = new JButton("Reset"); 
        reset.setFont(new Font("Arial", Font.PLAIN, 15)); 
        reset.setSize(100, 20); 
        reset.setLocation(270, 200); 
        reset.addActionListener(this); 
        container.add(reset);
        
        setVisible(true); 
    } 

    public void actionPerformed(ActionEvent e) 
    { 
        if (e.getSource() == sub) 
        {
            String dir = textDir.getText();
            String[] keys = textKeys.getText().split(" ");
            if (dir.isEmpty() || keys.length == 0) return;
            
            DirUtility.TreeInfo info = DirUtility.walk(dir);
            ArrayList<String> result = info.printAll(keys);
            if (result.isEmpty()) return;
            
            MyDisplay disp = new MyDisplay(dir, result);
        }
        else if (e.getSource() == reset)
        {
            textDir.setText(CLEAR_OP);
            textKeys.setText(CLEAR_OP);
        }
    }

} 
