package xfileview;

public class XFileView {
        
    /**
     * @param args:
     * arg[0] = directory
     * arg[1] = file name
     */
    public static void main(String[] args) {
        
        if (args.length != 2) {
            ErrWindow err = new ErrWindow("Run with Dir & Filename!");
        }
            
        xFileViewWin win = new xFileViewWin(args[0], args[1]);
        
    }
    
}
