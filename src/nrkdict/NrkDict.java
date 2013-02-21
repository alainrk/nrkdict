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
    /* TODO: Save String? Or Document? for xpath quesries...Better Document.
     * public static String currentDict; */
    private static String XMLDictNameMapping;
    public static void main(String[] args) {
        /* Logic Creation */
        controller = new Controller();
        /* File for mapping DictionaryName - DictionaryFile */
        XMLDictNameMapping = "dictNameMapping.xml";
        singletonRequests = new SingletonRequests(XMLDictNameMapping);
        /* GUI Creation */
        guiController = new GuiController();
        /* GUI launch */
        guiController.start();
        
        
        /* TESTING */
        //singletonRequests.createDict("enit");
        //singletonRequests.createDict("spen");
        //singletonRequests.removeXMLFile("enit");
        singletonRequests.removeDict("spen");
        
    }
}
