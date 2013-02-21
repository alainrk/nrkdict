/* TODO: See JAXB for XML definable by XMLSchema */
package nrkdict;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import com.sun.org.apache.xpath.internal.jaxp.XPathExpressionImpl;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;


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
              System.out.println("XML_MAP_FILENAME File exist");
	  }else{
               System.out.println("XML_MAP_FILENAME File not found!\nCreating xml...");
               createMappingDictFile (this.XML_MAP_FILENAME);
	  }
        /* Create "XML_MAP_DOC" DOCUMENT for DOM in XML_MAP_FILENAME access */
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            XML_MAP_DOC = docBuilder.parse(XML_MAP_FILENAME);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /* Load in memory (see "currentDict" variable) a dictionary */
    public void loadDict (String dict) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            CURRENT_DICT_DOC = docBuilder.parse(CURRENT_DICT_NAME);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getTransl (String word){
        
    }
    
    public void getAllWords (){
    
    }
    
    public void createTerm (String word, String transl){
        
    }
    
    public void removeTerm (String word){
    
    }
    
    public void modifyTransl (String word, String transl){
    
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
            if (dict == CURRENT_DICT_NAME) CURRENT_DICT_NAME = "";
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
    private void createMappingDictFile (String XML_MAP_FILENAME){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("dicts");
            doc.appendChild(rootElement);

            /* Write the content into xml file */
            /* Create a transformer */
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            /* Create a DOMSource */
            DOMSource source = new DOMSource(doc);
            /* Create a stream for file to fill with DOM */
            StreamResult result = new StreamResult(new File(XML_MAP_FILENAME));
            
            /* Utils to get the DTD infos from the source and put in new XML 
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,doc.getDoctype().getSystemId());*/

            /* From DOMDocument to result */
            transformer.transform(source, result);
            System.out.println("File saved!");
 
        } catch (ParserConfigurationException | TransformerException pce) {
              pce.printStackTrace();
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
        Document docDict = docBuilder.newDocument();
        Element rootElement = docDict.createElement("words");
        docDict.appendChild(rootElement);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        DOMSource source = new DOMSource(docDict);
        StreamResult result = new StreamResult(new File(dictXMLFile+".xml"));
        try {
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        System.out.println("DEBUGGING: createMappingNameXMLFile, Saving changes in XML Map file...");
        TransformerFactory transformerFactoryMap = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactoryMap.newTransformer();
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        DOMSource source = new DOMSource(XML_MAP_DOC);
        StreamResult result = new StreamResult(new File(XML_MAP_FILENAME));
        try {
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        System.out.println("DEBUGGING: removeMappingNameXMLFile, Saving changes in XML Map file...");
        TransformerFactory transformerFactoryMap = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactoryMap.newTransformer();
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        DOMSource source = new DOMSource(XML_MAP_DOC);
        StreamResult result = new StreamResult(new File(XML_MAP_FILENAME));
        try {
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            NodeList nodes = (NodeList) xp.evaluate(XML_MAP_DOC, XPathConstants.NODESET);
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
}
