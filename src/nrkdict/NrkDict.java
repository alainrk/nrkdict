package nrkdict;
import nrkdictGUI.*;
/**
 *
 * @author narko
 */
public class NrkDict {
    public static Controller controller;
    public static SingletonRequests singletonRequests;
    public static GuiController guiController;
    
    public static void main(String[] args) {
        /* Logic Creation */
        controller = new Controller();
        singletonRequests = new SingletonRequests();
        /* GUI Creation */
        guiController = new GuiController();
        /* GUI launch */
        guiController.start();
        
        
        /* TESTING */
        singletonRequests.createDict("enit");
        singletonRequests.createDict("spen");
        //singletonRequests.removeDict("spen");
        //singletonRequests.removeDict("enit");
        singletonRequests.loadDict("enit");
        singletonRequests.createTerm("water", "acqua");
        System.out.println(singletonRequests.getTransl("water"));
        
    }
}
