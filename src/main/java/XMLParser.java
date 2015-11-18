import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by kimia on 11/16/15.
 */
public class XMLParser {
    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Document doc;
    private File file;
    private Element root;
    private NodeList childNodes;
    private Node node;


    public XMLParser(String fileName){
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            file = new File(fileName);
            doc = db.parse(file);
        } catch (Exception e) {
            System.out.println("XMLParser.java : constructor : " + e);
        }
    }

    public void setRootElement(){
        root = doc.getDocumentElement();
    }
    public Element getRootElement(){
        return root;
    }
    public String getElementAttribute(Element element,String attrName){
        return element.getAttribute(attrName);
    }
    public NodeList getElementChildNodes(Element element){
        return element.getChildNodes();
    }
    public Element castNodeToElement(Node n){
        Element element = (Element) n;
        return element;
    }

}
