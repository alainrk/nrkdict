/* TODO: See JAXB for XML definable by XMLSchema */
package nrkdict;

import java.io.*;
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
              System.out.println("XMLDictNameMapping File existed");
	  }else{
               System.out.println("XMLDictNameMapping File not found!\nCreating xml...");
               createXMLDictNameMapping (this.XMLDictNameMapping);
	  }
    }
    
    private void createXMLDictNameMapping (String XMLDictNameMapping){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("dicts");
            doc.appendChild(rootElement);
            
            /********************** TEST XML
            Element a = doc.createElement("prova");
            rootElement.appendChild(a);
            Text text = doc.createTextNode("trallalla");
            a.appendChild(text);
            Attr attr = doc.createAttribute("attribute");
            attr.setNodeValue("pino");
            a.setAttributeNode(attr);
            ************************************/
            

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(XMLDictNameMapping));

            transformer.transform(source, result);
            System.out.println("File saved!");
 
        } catch (ParserConfigurationException | TransformerException pce) {
              pce.printStackTrace();
        }
    }
    
    public void loadDict (String dict) 
        throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        DocumentBuilderFactory dictDOMFactory = DocumentBuilderFactory.newInstance();
        dictDOMFactory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = dictDOMFactory.newDocumentBuilder();
        Document dictDoc = builder.parse(XMLDictNameMapping);
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
    public void createDict (String dict){
    
    }
    public void removeDict (){
    
    }
    
}
