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
        //singletonRequests.createDict("enit");
        //singletonRequests.createDict("spen");
        //singletonRequests.createDict("itfi");
        singletonRequests.removeDict("swit");
        //singletonRequests.removeDict("spen");
        //singletonRequests.removeDict("enit");
        //singletonRequests.loadDict("itfi");
        //singletonRequests.removeTerm("guardare");
        //singletonRequests.createTerm("guardare", "katsoa");
        //singletonRequests.createTerm("water", "acqua");
        //singletonRequests.createTerm("ape", "scimmia");
        //singletonRequests.removeTerm("ape");
        //System.out.println(singletonRequests.getTransl("guardare"));
        
    }
}
