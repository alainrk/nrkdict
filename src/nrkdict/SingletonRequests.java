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
    private DocumentBuilderFactory dictDOMFactory;
    private String XMLDictNameMapping;

    public SingletonRequests(String XMLDictNameMapping) {
        this.XMLDictNameMapping = XMLDictNameMapping;
        File f = new File(this.XMLDictNameMapping); 
	  if(f.exists()){
              System.out.println("XMLDictNameMapping File exist");
	  }else{
               System.out.println("XMLDictNameMapping File not found!\nCreating xml...");
               createXMLDictNameMapping (this.XMLDictNameMapping);
	  }
    }
    
    /* Creates an XML with associations dictionaryName-dictionaryFile */
    private void createXMLDictNameMapping (String XMLDictNameMapping){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("dicts");
            doc.appendChild(rootElement);
            
            /************ TEST XML *************
            Element a = doc.createElement("prova");
            rootElement.appendChild(a);
            Text text = doc.createTextNode("trallalla");
            a.appendChild(text);
            Attr attr = doc.createAttribute("attribute");
            attr.setNodeValue("pino");
            a.setAttributeNode(attr);
            ************************************/
            

            /* Write the content into xml file */
            /* Create a transformer */
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            /* Create a DOMSource */
            DOMSource source = new DOMSource(doc);
            /* Create a stream for file to fill with DOM */
            StreamResult result = new StreamResult(new File(XMLDictNameMapping));
            
            /* Utils to get the DTD infos from the source and put in new XML 
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,doc.getDoctype().getSystemId());*/

            /* From DOMDocument to result */
            transformer.transform(source, result);
            System.out.println("File saved!");
 
        } catch (ParserConfigurationException | TransformerException pce) {
              pce.printStackTrace();
        }
    }
    
    /* Load in memory (see "currentDict" variable) a dictionary */
    public void loadDict (String dict) 
        throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        /*DocumentBuilderFactory dictDOMFactory = DocumentBuilderFactory.newInstance();
        dictDOMFactory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = dictDOMFactory.newDocumentBuilder();
        Document dictDoc = builder.parse(XMLDictNameMapping);*/
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
            createXMLFile(dict);
            /* ADDING ITEM XMLMAP: Take the root element "dicts" (first item, index 0) with xpath */
            createMappingNameXMLFile(dict);
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
        
        return 0;
    }
    
    /**** UTILS ****/
    private boolean existDict (String dict){
        try {
            /* XPATH - Check if already exist dict file */
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = null;
            try {
                doc = docBuilder.parse(XMLDictNameMapping);
            } catch (SAXException | IOException ex) {
                Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            try {
                String query = "/dicts/dict/name[text() = '"+dict+"']";
                System.out.println("DEBUGGING: existdict, query for check exist dict: "+query);
                XPathExpression expr = xpath.compile(query);
                /* Necessary for process expression returning by xpath query */
                Object res = expr.evaluate(doc, XPathConstants.NODESET);
                NodeList existNode = (NodeList) res;
                System.out.println("DEBUGGING: existdict, after xpath check name dict, expr="+existNode.item(0).getNodeValue());
            } catch (XPathExpressionException | NullPointerException ex1a) {
                System.out.println("DEBUGGING: existdict, dict does not exist.");
                return false;
            }
            System.out.println("DEBUGGING: existdict, dict already exist.");
            return true;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    private void createXMLFile(String dictXMLFile) {
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
    
    private void createMappingNameXMLFile(String dict) {
        /* XPATH - Check if already exist dict file */
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        Document doc = null;
        try {
            doc = docBuilder.parse(XMLDictNameMapping);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression root = null;
        try {
            System.out.println("DEBUGGING: createdict, Create item in XML Map file");
            root = xpath.compile("/dicts");
        } catch (XPathExpressionException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object rootElem = new Object();
        try {
            rootElem = root.evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException ex3) {}
        NodeList nodes = (NodeList) rootElem;
        /* Create and append the new dict element */
        Element newDict = doc.createElement("dict");

        Element dictName = doc.createElement("name");
        Text textName = doc.createTextNode(dict);
        dictName.appendChild(textName);

        Element dictFile = doc.createElement("file");
        Text textFile = doc.createTextNode(dict+".xml");
        dictFile.appendChild(textFile);

        newDict.appendChild(dictName);
        newDict.appendChild(dictFile);
        nodes.item(0).appendChild(newDict);

        System.out.println("DEBUGGING: createdict, Saving changes in XML Map file...");
        TransformerFactory transformerFactoryMap = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactoryMap.newTransformer();
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(XMLDictNameMapping));
        try {
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            Logger.getLogger(SingletonRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("DEBUGGING: createdict, Saved changes in XML Map file!");    
    }
    
    //TODO: Not yet implemented, check if is it possible remove duplicate code
    private Document getXMLMapDocument (String XMLDictNameMapping){return null;}
    
}
