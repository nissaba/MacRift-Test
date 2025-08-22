/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rifttest;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author Pascale
 */
public class XMLHandler {

    xmlIO xmlio;
    Document document;

    public boolean loadXMLFile(String path) {
        xmlio = new xmlIO();
        document = xmlio.LoadXMLFile(path);
        if (document != null) {
            document.getDocumentElement().normalize();
            return true;
        }
        return false;
    }

    public void saveData(String path) {
        xmlio.WriteXMLFIle(document, path);
    }

    public void addAttr(Element e, String name) {
        Attr attr = document.createAttribute(name);
        e.setAttributeNode(attr);
    }

    public Text createText() {
        return document.createTextNode("");
    }

    public void removeNode(String nodeType, Element parentNode, int index) {
        NodeList nodes = parentNode.getElementsByTagName(nodeType);
        parentNode.removeChild(nodes.item(index));
    }

    public boolean asChildren(Element element, String name) {
        NodeList n = element.getElementsByTagName(name);
        if (n.getLength() > 0) {
            return true;
        }
        return false;
    }

    public Element getRoot() {
        return document.getDocumentElement();
    }

    public String getNodeText(Element e) {
        Text txt = (Text) e.getFirstChild();
        if(txt == null)
            return "";
        return txt.getNodeValue();
    }

    public String getNodeText(Element e, String name) {
        NodeList nodes = e.getElementsByTagName(name);
        Element child = (Element) nodes.item(0);
        if (child != null) {
            Text txt = (Text) child.getFirstChild();
            if (txt != null) {
                return txt.getNodeValue();
            }
        }
        return "";
    }

    public Element createNodeText(String name, String valu) {
        Element e = document.createElement(name);
        Text txt = document.createTextNode(valu);
        e.appendChild(txt);
        return e;
    }
}
