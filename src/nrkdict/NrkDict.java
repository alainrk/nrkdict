package nrkdict;

import nrkdictGUI.*;
/**
 *
 * @author narko
 */
public class NrkDict {
    public static SingletonRequests singletonRequests;
    public static GuiController guiController;
    
    public static void main(String[] args) {
        singletonRequests = new SingletonRequests();
        /* GUI Creation */
        guiController = new GuiController();
        guiController.start();
    }
    
}
