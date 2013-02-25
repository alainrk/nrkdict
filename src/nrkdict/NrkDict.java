package nrkdict;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import nrkdictGUI.*;
/**
 *
 * @author narko
 */
public class NrkDict {
    public static SingletonRequests singletonRequests;
    public static GuiController guiController;
    
    public static void main(String[] args) {
        /* GUI Creation */
        guiController = new GuiController();
        /* GUI launch */
        guiController.start();
    }
    
}
