/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nrkdictGUI;

/**
 *
 * @author narko
 */

import java.util.ArrayList;
import javax.swing.*;
import nrkdict.SingletonRequests;

/**
 *
 * @author narko
 */
public class GuiController {
    public static MainFrame mainFrame;
    private static SingletonRequests singletonRequests;
    
    public GuiController (){
        singletonRequests = new SingletonRequests();
    }
    
    public void start(){
        
        /* LOGIC */
        /* TESTING */
        //singletonRequests.createDict("enit");
        //singletonRequests.createDict("spen");
        //singletonRequests.createDict("itfi");
        //singletonRequests.removeDict("swit");
        //singletonRequests.removeDict("spen");
        //singletonRequests.removeDict("enit");
        //singletonRequests.loadDict("spen");
        //singletonRequests.removeDict("spen");
        singletonRequests.loadDict("enit");
        //singletonRequests.removeTerm("guardare");
        //singletonRequests.createTerm("water", "acqua");
        //singletonRequests.createTerm("wait", "aspettare");
        //singletonRequests.createTerm("wine", "vino");
        //singletonRequests.createTerm("wool", "lana");
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

       /* LOGIC */
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            createGui();
        } catch (Exception e){System.out.println(e.toString());}
        
    }
    
    private void createGui() throws Exception {
        /* Create mainFrame, and pass this object as Gui Controller */
        mainFrame = new MainFrame(this);
    }
    
    /* Interface for MainFrame Requests */
    public void loadDict (String dict) {
        singletonRequests.loadDict(dict);
    }

    public String getTransl (String word){
        return singletonRequests.getTransl(word);
    }

    public ArrayList<String> getWordsStartWith (String start){
        return singletonRequests.getWordsStartWith(start);
    }
    
    public ArrayList<String> getAllDicts (){
        return singletonRequests.getAllDicts();
    }
    
    public ArrayList<String> getAllWords (){
        return singletonRequests.getAllWords();
    }

    public int createTerm (String word, String transl){
        return singletonRequests.createTerm (word, transl);
    }

    public int removeTerm (String word){
        return singletonRequests.removeTerm (word);
    }

    public int modifyTerm (String word, String transl){
        return singletonRequests.modifyTerm (word, transl);
    }

    public int createDict (String dict){
        return singletonRequests.createDict(dict);
    }

    public int removeDict (String dict){
        return singletonRequests.removeDict(dict);
    }
}