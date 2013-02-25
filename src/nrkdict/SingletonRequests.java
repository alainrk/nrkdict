/* TODO: See JAXB for XML definable by XMLSchema */
package nrkdict;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


/**
 *
 * @author narko
 */
public class SingletonRequests {
    //private DocumentBuilderFactory dictDOMFactory;
    /* Name and corrispondent DOMDocument for DOM access dictNameMapping.xml */
    private String XML_MAP_FILENAME = "dictNameMapping.xml";;
    private Document XML_MAP_DOC = null;
    /* Current Dictionary DOM XML */
    private Document CURRENT_DICT_DOC = null;
    private String CURRENT_DICT_NAME = "";

    public SingletonRequests() {
        File f = new File(this.XML_MAP_FILENAME); 
	  if(f.exists()){
              System.out.println(XML_MAP_FILENAME +"File exist");
	  }else{
               System.out.println(XML_MAP_FILENAME +"File not found!\nCreating xml...");
               createMappingDictFile (XML_MAP_FILENAME);
	  }
        /* Create "XML_MAP_DOC" DOCUMENT for DOM in XML_MAP_FILENAME access */
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            XML_MAP_DOC = docBuilder.parse(XML_MAP_FILENAME);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /* Load in memory (see "currentDict" variable) a dictionary */
    public void loadDict (String dict) {
        CURRENT_DICT_NAME = dict+".xml";
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            CURRENT_DICT_DOC = docBuilder.parse(CURRENT_DICT_NAME);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getTransl (String word){
        NodeList nodes = getNodeListFromDoc(CURRENT_DICT_DOC, "/terms/term/word[text() = '"+word+"']/../transl");
        return (nodes != null) ? nodes.item(0).getTextContent() : null;
    }
    
    public ArrayList<String> getAllWords (){
        NodeList nodes = getNodeListFromDoc(CURRENT_DICT_DOC, "/terms/term/word");
        if (nodes == null){
            return null;
        }
        ArrayList <String> words;
        words = new ArrayList(nodes.getLength());
        for (int i=0;i<nodes.getLength();i++){
            words.add(nodes.item(i).getTextContent());
        }
        return words;
    }
    
    public ArrayList<String> getWordsStartWith (String start){
        NodeList nodes = getNodeListFromDoc(CURRENT_DICT_DOC, "/terms/term/word[starts-with(.,'"+start+"')]");
        if (nodes == null){
            return null;
        }
        ArrayList <String> words;
        words = new ArrayList(nodes.getLength());
        for (int i=0;i<nodes.getLength();i++){
            words.add(nodes.item(i).getTextContent());
        }
        return words;
    }
    
    /* Create new term in current dictionary, on error return -1 */
    public int createTerm (String word, String transl){
        // TODO: Check if already exist (USE XPATH ID OR SIMILAR IF POSSIBLE
        NodeList nodes = getNodeListFromDoc(CURRENT_DICT_DOC, "terms");
        if (nodes == null) {
            return -1;
        }
        Element termEl = CURRENT_DICT_DOC.createElement("term");
        Element wordEl = CURRENT_DICT_DOC.createElement("word");
        Text wordTxt = CURRENT_DICT_DOC.createTextNode(word);
        Element translEl = CURRENT_DICT_DOC.createElement("transl");
        Text translTxt = CURRENT_DICT_DOC.createTextNode(transl);
        nodes.item(0).appendChild(termEl);
        termEl.appendChild(wordEl);
        termEl.appendChild(translEl);
        wordEl.appendChild(wordTxt);
        translEl.appendChild(translTxt);

        saveDocChanges(CURRENT_DICT_DOC, CURRENT_DICT_NAME);
        return 0;
    }
    
    public int removeTerm (String word){
        NodeList nodes = getNodeListFromDoc(CURRENT_DICT_DOC, "/terms/term[word[text() = '"+word+"']]");
        if (nodes != null) {
            nodes.item(0).getParentNode().removeChild(nodes.item(0));
            saveDocChanges(CURRENT_DICT_DOC, CURRENT_DICT_NAME);
            System.out.println("DEBUGGING: removeTerm, term removed");
            return 0;
        }
        else {
            return -1;
        }
    }
    
    public int modifyTerm (String word, String transl){
        NodeList nodes = getNodeListFromDoc(CURRENT_DICT_DOC, "/terms/term/word[text() = '"+word+"']/../transl");
        if (nodes != null) {
            Node newTransl = CURRENT_DICT_DOC.createTextNode(transl);
            nodes.item(0).removeChild(nodes.item(0).getFirstChild());
            nodes.item(0).appendChild(newTransl);
            saveDocChanges(CURRENT_DICT_DOC, CURRENT_DICT_NAME);
            System.out.println("DEBUGGING: modifyTerm, term modified");
            return 0;
        }
        else {
            return -1;
        }
    }
    
    
    public ArrayList<String> getAllDicts (){
        NodeList nodes = getNodeListFromDoc(XML_MAP_DOC, "/dicts/dict/name");
        if (nodes == null){
            return null;
        }
        ArrayList <String> dicts;
        dicts = new ArrayList(nodes.getLength());
        for (int i=0;i<nodes.getLength();i++){
            dicts.add(nodes.item(i).getTextContent());
        }
        return dicts;
    }
    
    /* Return -1 if already exist the dictionary with "dict" name, else
     * return 0, and create the item in XML map file and the XML dict file.
     */
    public int createDict (String dict){
        if (!existDict(dict)){
            /* CREATION XML: If dict does not exist is launched this catch, creates dict and return 0 
             * Same process of createXMLDictNameMapping, see above. */
            createEmptyDictionary(dict);
            /* ADDING ITEM XMLMAP: Take the root element "dicts" (first item, index 0) with xpath */
            addDictItemMap(dict);
            /* SUCCESS */
            return 0;
        } else {
            System.out.println("DEBUGGING: createdict, dict already exist, return -1");
            /* If dict already exist, it will not be created */
            return -1;
        }
    }
    
    /* Return -1 if does not exist the dictionary with "dict" name, else
     * return 0, and remove the item in XML map file and the XML dict file.
     */
    public int removeDict (String dict){
        if (existDict(dict)) {
            // TODO: Set the next dictionary in XML, and not only set null value
            if (dict.equals(CURRENT_DICT_NAME)) {
                CURRENT_DICT_NAME = "";
            }
            removeDictionary(dict);
            /* ADDING ITEM XMLMAP: Take the root element "dicts" (first item, index 0) with xpath */
            removeDictItemMap(dict);
            return 0;
        } else {
            System.out.println("DEBUGGING: removedict, dict does not exist, return -1");
            return -1;
        }
    }
    
    /**** PRIVATE UTILS ****/
    /* Creates the initial XML File with associations dictionaryName-dictionaryFile */
    private void createMappingDictFile (String xmlMapFilename){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("dicts");
            doc.appendChild(rootElement);

            saveDocChanges(doc, xmlMapFilename);
            System.out.println("File saved!");
 
        } catch (ParserConfigurationException pce) {
            System.out.println("Error: "+pce.toString());
        }
    }
    
    /* Check if dict is an item in the XML Map file */
    private boolean existDict (String dict){
        NodeList nodes = getNodeListFromDoc(XML_MAP_DOC, "/dicts/dict/name[text() = '"+dict+"']");
        return (nodes != null)? true:false;
    }
    
    /* Create an empty dictionary XML file */
    private void createEmptyDictionary (String dictXMLFile) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("terms");
        doc.appendChild(rootElement);
        
        saveDocChanges(doc, dictXMLFile+".xml");

        System.out.println("New dictionary "+dictXMLFile+".xml saved!");
    }
    
    /* Add an item in XML Map File for dict */
    private void addDictItemMap(String dict) {

        NodeList nodes = getNodeListFromDoc(XML_MAP_DOC, "/dicts");
        
        /* Create and append the new dict element */
        Element newDict = XML_MAP_DOC.createElement("dict");

        Element dictName = XML_MAP_DOC.createElement("name");
        Text textName = XML_MAP_DOC.createTextNode(dict);
        dictName.appendChild(textName);

        Element dictFile = XML_MAP_DOC.createElement("file");
        Text textFile = XML_MAP_DOC.createTextNode(dict+".xml");
        dictFile.appendChild(textFile);

        newDict.appendChild(dictName);
        newDict.appendChild(dictFile);
        nodes.item(0).appendChild(newDict);

        saveDocChanges(XML_MAP_DOC, XML_MAP_FILENAME);
        System.out.println("DEBUGGING: createMappingNameXMLFile, Saved changes in XML Map file!");    
    }
    
    /* Remove the XML file "dict".xml */
    private void removeDictionary(String dict){
        File f = new File(dict+".xml");
        try{
            f.delete();
        } catch(Error err){
            System.out.println("DEBUGGING: removeXMLFile, error on delete: "+err);
        }
        System.out.println("DEBUGGING: removeXMLFile, file "+dict+".xml removed!");
    }

    private void removeDictItemMap(String dict){

        NodeList node2remList = getNodeListFromDoc(XML_MAP_DOC, "/dicts/dict[name[text() = '"+dict+"']]");

        /* Go back to parent and remove myself */
        node2remList.item(0).getParentNode().removeChild(node2remList.item(0));
            
        saveDocChanges(XML_MAP_DOC, XML_MAP_FILENAME);
        System.out.println("DEBUGGING: removeMappingNameXMLFile, Saved changes in XML Map file!");    
    }
    

    /* Take a Document and an XPath Query, and return directly a NodeSet (as a Nodelist)
     * as result of the xquery, if the NodeSet is empty return null
     */
    private NodeList getNodeListFromDoc (Document doc, String xquery){
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression xp = null;
        try {
            System.out.println("DEBUGGING: getNodeListFromDoc, Query: "+xquery);
            xp = xpath.compile(xquery);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            NodeList nodes = (NodeList) xp.evaluate(doc, XPathConstants.NODESET);
            if (nodes.getLength() != 0) {
                System.out.println("DEBUGGING: getNodeListFromDoc, NOT Empty node-set!");
                return nodes;
            }
            else {
                System.out.println("DEBUGGING: getNodeListFromDoc, Empty node-set!");
                return null;
            }
        } catch (XPathExpressionException | NullPointerException ex) {
            System.out.println("DEBUGGING: getNodeListFromDoc, Error: "+ex.toString());
            return null;
        }
    }
    
    private void saveDocChanges(Document doc, String nameFile){
        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(nameFile));
            transformer.transform(source, result);
            System.out.println("DEBUGGING: saveDocChanges, document "+nameFile+" saved!"); 
       } catch (TransformerException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
