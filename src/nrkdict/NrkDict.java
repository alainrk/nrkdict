package nrkdict;
import java.util.ArrayList;
import java.util.Iterator;
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
        //singletonRequests.removeDict("swit");
        //singletonRequests.removeDict("spen");
        //singletonRequests.removeDict("enit");
        //singletonRequests.loadDict("spen");
        //singletonRequests.removeDict("spen");
        //singletonRequests.loadDict("enit");
        //singletonRequests.removeTerm("guardare");
        //singletonRequests.createTerm("guardare", "katsoa");
        //singletonRequests.createTerm("water", "acqua");
        //singletonRequests.createTerm("wait", "aspettare");
        //singletonRequests.createTerm("wine", "vino");
        //singletonRequests.modifyTerm("wool", "lana merinos");
        //singletonRequests.createTerm("ape", "scimmia");
        //singletonRequests.removeTerm("ape");
        //System.out.println(singletonRequests.getTransl("guardare"));
        
        /*ArrayList words = new ArrayList<String> ();
        words = singletonRequests.getWordsStartWith("wa");
        Iterator itr = words.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }*/
        //System.out.println(singletonRequests.getWordsStartWith("wa"));
        
    }
}
