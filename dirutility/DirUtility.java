/** DFS directory, code to that model
 */

package dirutility;
import java.util.regex.*;
import java.io.*;
import java.util.*;

public class DirUtility
{
    static TreeInfo recurseDirs(File startDir)
    {
        TreeInfo result = new TreeInfo();
        
        for(File item : startDir.listFiles())
        {
            if(item.isDirectory())
            {
                result.addAll(recurseDirs(item));
            }
            else
            {
                if (getExtension(item.getName()).equals("java"))
                    result.files.add(item);
            }
        }
        return result;
    }

    public static TreeInfo walk(String start) {
        return recurseDirs(new File(start));
    }


    // tuple for returning a pair of objects:
    static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<File>();
        public List<File> dirs = new ArrayList<File>();

        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        public String toString() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        ArrayList<String> printAll(String[] keys)
        {
            ArrayList<String> result = new ArrayList<>();
            
            for (File f : files) {
                try (BufferedReader reader = new BufferedReader(new FileReader(f)))
                {
                    String line;
                    int lineNo = 0;
                    int count = 0;
                    
                    while ((line = reader.readLine()) != null)
                    {
                        lineNo++;
                        
                        if (line.length() == 0) continue;                    

                        int ind = -1;
                        
                        for (String k : keys)
                        {
                            k = k.toLowerCase();
                            String lcLine = line.toLowerCase();
                            
                            if ((ind = lcLine.indexOf(k)) >= 0)
                            {
                                count++;
                                if (count == 1)
                                {
                                    if (!result.isEmpty())
                                        result.add("\n");
                                                
                                    result.add(f.getPath());
                                }
                                    
                                
                                StringBuilder sb = new StringBuilder(Integer.toString(lineNo));
                                sb.append(":\t");
                                sb.append(line.substring(ind));
                                result.add(sb.toString());
                                break;
                            }
                        }
                    }
                    
                    if (count > 0)
                        System.out.println("");
                    
                 }
                 catch(Exception ex)
                 {
                     System.out.println("cant read " + f.getName());
                     ex.printStackTrace();
                     System.exit(0);
                 }
            }
            
            return result;
        }
            
    }
  
    private static String getExtension(String nm)
    {
        int i = nm.lastIndexOf('.');
        
        if (i <= 0)
            return "";
        else
            return nm.substring(i+1);
    }

    public static void main(String[] args)
    {
        QueryForm qf = new QueryForm();
        
        /*
        Scanner scan = new Scanner(System.in);
        System.out.println("directory:");
        String getDir = scan.nextLine();
        System.out.println("keys:");
        System.out.println(">>>:");
        walk(getDir).printAll(keys);
        */
       
        
    }

    
}
