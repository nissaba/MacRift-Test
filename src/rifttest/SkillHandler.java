/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rifttest;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author Pascale
 */
public class SkillHandler {

    private Document document;
    private xmlIO xmlDoc;

    SkillHandler() {
        document = null;
        xmlDoc = new xmlIO();
    }

    public void LoadXMLFile() {
        document = xmlDoc.LoadXMLFile("c:\\test\\SKILL.XML");
        if (document == null) {
            //create new document.
            DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = dBF.newDocumentBuilder(); // java xml documentbuilder
                document = builder.newDocument();
            } catch (ParserConfigurationException parserException) {
                parserException.printStackTrace();
            }

            Element root = document.createElement("list");
            document.appendChild(root);
        }
    }

    public ArrayList getCategories() {
        ArrayList vec = new ArrayList();
        Element root = document.getDocumentElement();
        NodeList catList = root.getElementsByTagName("Categorie");

        for (int i = 0; i < catList.getLength(); i++) {
            Element catElement = (Element) catList.item(i);
            String catName = catElement.getAttribute("name");
            vec.add(catName);
        }

        return vec;
    }

    public ArrayList getSkills(String item) {
        ArrayList list = new ArrayList();

        Element catElement = findCategory(item);
        NodeList skillList = catElement.getElementsByTagName("Skill");
        for (int i = 0; i < skillList.getLength(); i++) {
            Element skillElement = (Element) skillList.item(i);
            list.add(skillElement.getAttribute("name"));
        }
        return list;
    }

    public Element getSkillByName(String catName, String skillName) {
        Element skillElement = null;

        Element catElem = findCategory(catName);
        NodeList skillList = catElem.getElementsByTagName("Skill");

        for(int i=0; i<skillList.getLength();i++){
            skillElement = (Element) skillList.item(i);
            if (skillName.equalsIgnoreCase(skillElement.getAttribute("name"))){
                return skillElement;
            }
        }

        return null;
    }

    public Element findCategory(String name) {
        Element root = document.getDocumentElement();
        NodeList catList = root.getElementsByTagName("Categorie");
        for (int i = 0; i < catList.getLength(); i++) {
            Element catElement = (Element) catList.item(i);
            if (name.equalsIgnoreCase(catElement.getAttribute("name"))) {
                return catElement;
            }
        }
        return null;
    }

    public void WriteXMLFIle() {
        xmlDoc.WriteXMLFIle(document, "c:\\test\\SKILL.XML");
    }

    public Element createElement(String type, String name) {
        Element E = document.createElement(type);
        Attr attr = document.createAttribute("name");
        attr.setValue(name);
        E.setAttributeNode(attr);
        return E;
    }

    public Element createElement(String type) {
        Element E = document.createElement(type);
        return E;
    }

    public void addCategory(Element E) {
        Element root = document.getDocumentElement();
        root.appendChild(E);
    }

    public void addSkill(String item, Element skill) {
        Element root = document.getDocumentElement();
        NodeList nodes = root.getElementsByTagName("Categorie");
        for(int i=0;i<nodes.getLength();i++){
            Element e = (Element) nodes.item(i);
            if(e.getAttribute("name").equalsIgnoreCase(item))
                e.appendChild(skill);
        }
    }

    public void addAttr(Element e, String name) {
        Attr attr = document.createAttribute(name);
        e.setAttributeNode(attr);
    }

    public Text createText() {
        return document.createTextNode("");
    }

    public void updateNode(Element parent, Element newNode, Element oldNode) {
        System.out.println("trying to replace: " + newNode.getAttribute("name")
                +" with " + oldNode.getAttribute("name")+ " from "+ parent.getAttribute("name"));
        NodeList nodes = parent.getElementsByTagName("Skill");
        for(int i=0;i<nodes.getLength();i++){
            Element e = (Element) nodes.item(i);
            String str = e.getAttribute("name");
            if (str.equalsIgnoreCase(oldNode.getAttribute("name"))){
                parent.replaceChild(newNode, e);
            }
        }

        
    }

    public void removeNode(String nodeType, Element parentNode, int index) {
        NodeList nodes = parentNode.getElementsByTagName(nodeType);
        parentNode.removeChild(nodes.item(index));
    }

    public Element getCategory(int selectedIndex) {
       Element root = document.getDocumentElement();
       NodeList list = root.getElementsByTagName("Categorie");
       return (Element) list.item(selectedIndex);
    }

    public boolean asChildren(Element element) {
        NodeList n = element.getChildNodes();
        if(n.getLength()>0){
            return true;
        }
        return false;
    }

     public boolean asChildren(Element element, String name) {
        NodeList n = element.getElementsByTagName(name);
        if(n.getLength()>0){
            return true;
        }
        return false;
    }

    public Element getRoot(){
        return document.getDocumentElement();
    }
}
