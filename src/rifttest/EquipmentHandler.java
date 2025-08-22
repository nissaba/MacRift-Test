/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rifttest;

import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author Pascale
 */
public class EquipmentHandler extends XMLHandler {

    public final static int BODY_ARMOR = 0;
    public final static int POWER_ARMOR = 1;
    public final static int VEHICULES = 2;
    public final static int ROBOT_VEHICULES = 3;
    public final static int WEAPONS = 4;
    private NodeList bodyArmor;
    private NodeList powerArmor;
    private NodeList vehicules;
    private NodeList robotVehicules;
    private NodeList weapons;

    public ArrayList getEquipmentList(int list) {
        ArrayList listing = new ArrayList();
        switch (list) {
            case 0:
                for (int i = 0; i < bodyArmor.getLength(); i++) {
                    Element e = (Element) bodyArmor.item(i);
                    NodeList nodes = e.getElementsByTagName("name");
                    listing.add(getNodeText((Element) nodes.item(0)));
                }
                break;
            case 1:
                for (int i = 0; i < powerArmor.getLength(); i++) {
                    Element e = (Element) powerArmor.item(i);
                    NodeList nodes = e.getElementsByTagName("name");
                    listing.add(getNodeText((Element) nodes.item(0)));
                }
                break;
            case 2:
                for (int i = 0; i < vehicules.getLength(); i++) {
                    Element e = (Element) vehicules.item(i);
                    NodeList nodes = e.getElementsByTagName("name");
                    listing.add(getNodeText((Element) nodes.item(0)));
                }
                break;
            case 3:
                for (int i = 0; i < robotVehicules.getLength(); i++) {
                    Element e = (Element) robotVehicules.item(i);
                    NodeList nodes = e.getElementsByTagName("name");
                    listing.add(getNodeText((Element) nodes.item(0)));
                }
                break;
            case 4:
                for (int i = 0; i < weapons.getLength(); i++) {
                    Element e = (Element) weapons.item(i);
                    NodeList nodes = e.getElementsByTagName("name");
                    listing.add(getNodeText((Element) nodes.item(0)));
                }
                break;
        }
        return listing;
    }

    public EquipmentHandler() {
        this.loadXMLFile("c:\\test\\equipment.xml");
        Element root = document.getDocumentElement();
        NodeList nodes = root.getElementsByTagName("armors");
        Element element = (Element) nodes.item(0);
        if (element != null) {
            bodyArmor = element.getElementsByTagName("bodyArmor");
            powerArmor = element.getElementsByTagName("powerArmor");
        }
        nodes = root.getElementsByTagName("vehicules");
        element = (Element) nodes.item(0);
        if (element != null) {
            vehicules = element.getElementsByTagName("vehicule");
            robotVehicules = element.getElementsByTagName("robotVehicule");
        }
        nodes = root.getElementsByTagName("weapons");
        element = (Element) nodes.item(0);
        if (element != null) {
            weapons = element.getElementsByTagName("weapon");
        }
    }

    public Element getEquipment(int type, int index) {
        Element e = null;
        switch (type) {
            case 0:
                e = (Element) bodyArmor.item(index);
                break;
            case 1:
                e = (Element) powerArmor.item(index);
                break;
            case 2:
                e = (Element) vehicules.item(index);
                break;
            case 3:
                e = (Element) robotVehicules.item(index);
                break;
            case 4:
                e = (Element) weapons.item(index);
                break;
        }
        return e;
    }

    public ArrayList getWeaponNodeData(int sIndex, int wIndex) {
        ArrayList list = new ArrayList();
        Element e = getEquipment(sIndex, wIndex);

        list.add(getNodeText(e, "name"));
        list.add(getNodeText(e, "megaDamage"));
        list.add(getNodeText(e, "sdcDamage"));
        list.add(getNodeText(e, "rateOfFire"));
        list.add(getNodeText(e, "effectiveRange"));
        list.add(getNodeText(e, "payload"));
        list.add(getNodeText(e, "cost"));
        list.add(getNodeText(e, "description"));
        list.add(getNodeText(e, "ref"));
        return list;
    }

    void setWeaponData(int sIndex, int wIndex, ArrayList data) {


        Element e = getEquipment(sIndex, wIndex);

        setTextNode(e, "name", (String) data.get(0));
        setTextNode(e, "megaDamage", (String) data.get(1));
        setTextNode(e, "sdcDamage", (String) data.get(2));
        setTextNode(e, "rateOfFire", (String) data.get(3));
        setTextNode(e, "effectiveRange", (String) data.get(4));
        setTextNode(e, "payload", (String) data.get(5));
        setTextNode(e, "cost", (String) data.get(6));
        setTextNode(e, "description", (String) data.get(7));
        setTextNode(e, "ref", (String) data.get(8));

    }

    private void setTextNode(Element e, String name, String val) {
        NodeList nodes = e.getElementsByTagName(name);
        Element element = (Element) nodes.item(0);
        if (element != null) {
            Text txt = (Text) element.getFirstChild();
            if (txt == null) {
                txt = document.createTextNode(val);
                element.appendChild(txt);
            }
            txt.setNodeValue(val);
        } else if (!val.equalsIgnoreCase("") || val != null) {
            Text txt = createText();
            txt.setNodeValue(val);
            Element newE = createNode(name);
            newE.appendChild(txt);
            e.appendChild(newE);
        }
    }

    private Element createNode(String name) {
        return document.createElement(name);
    }

    

    public void save() {
        Element root = document.getDocumentElement();
        NodeList nodesList = root.getElementsByTagName("weapons");
        Element nWeapons = (Element) nodesList.item(0);
        root.removeChild(nWeapons);
        root.appendChild((Node) weapons);
        saveData("c:\\test\\equipment.xml");
    }

    public void addWeapon(ArrayList list) {
        Element newWeapon = document.createElement("weapon");


        Element element = document.createElement("name");
        element.appendChild(document.createTextNode((String) list.get(0)));
        newWeapon.appendChild(element);

        element = document.createElement("megaDamage");
        element.appendChild(document.createTextNode((String) list.get(1)));
        newWeapon.appendChild(element);

        element = document.createElement("sdcDamage");
        element.appendChild(document.createTextNode((String) list.get(2)));
        newWeapon.appendChild(element);

        element = document.createElement("rateOfFire");
        element.appendChild(document.createTextNode((String) list.get(3)));
        newWeapon.appendChild(element);

        element = document.createElement("effectiveRange");
        element.appendChild(document.createTextNode((String) list.get(4)));
        newWeapon.appendChild(element);

        element = document.createElement("payload");
        element.appendChild(document.createTextNode((String) list.get(5)));
        newWeapon.appendChild(element);

        element = document.createElement("cost");
        element.appendChild(document.createTextNode((String) list.get(6)));
        newWeapon.appendChild(element);

        element = document.createElement("description");
        element.appendChild(document.createTextNode((String) list.get(7)));
        newWeapon.appendChild(element);

        element = document.createElement("ref");
        element.appendChild(document.createTextNode((String) list.get(8)));
        newWeapon.appendChild(element);

        Element root = document.getDocumentElement();
        NodeList nodes = root.getElementsByTagName("weapons");
        Element weaplist = (Element) nodes.item(0);
        weaplist.appendChild(newWeapon);
    }

    public ArrayList getBodyArmorNodeData(int index) {
        ArrayList list = new ArrayList();

        Element armor = (Element) bodyArmor.item(index);
        list.add(getNodeText(armor, "name"));
        list.add(getNodeText(armor, "mdc"));
        list.add(getNodeText(armor, "weight"));
        list.add(getNodeText(armor, "mobility"));
        list.add(getNodeText(armor, "cost"));
        list.add(getNodeText(armor, "description"));

        return list;
    }

    void addBodyArmor(ArrayList list) {
        Element newBodyArmor = document.createElement("bodyArmor");


        Element element = document.createElement("name");
        element.appendChild(document.createTextNode((String) list.get(0)));
        newBodyArmor.appendChild(element);

        element = document.createElement("mdc");
        element.appendChild(document.createTextNode((String) list.get(1)));
        newBodyArmor.appendChild(element);

        element = document.createElement("weight");
        element.appendChild(document.createTextNode((String) list.get(2)));
        newBodyArmor.appendChild(element);

        element = document.createElement("mobility");
        element.appendChild(document.createTextNode((String) list.get(3)));
        newBodyArmor.appendChild(element);

        element = document.createElement("cost");
        element.appendChild(document.createTextNode((String) list.get(4)));
        newBodyArmor.appendChild(element);

        element = document.createElement("description");
        element.appendChild(document.createTextNode((String) list.get(5)));
        newBodyArmor.appendChild(element);

        Element root = document.getDocumentElement();
        NodeList nodes = root.getElementsByTagName("armors");
        Element armorlist = (Element) nodes.item(0);
        armorlist.appendChild(newBodyArmor);
    }

    void setBodyArmorData(int Index, ArrayList data) {
        Element e = (Element) bodyArmor.item(Index);

        setTextNode(e, "name", (String) data.get(0));
        setTextNode(e, "mdc", (String) data.get(1));
        setTextNode(e, "weight", (String) data.get(2));
        setTextNode(e, "mobility", (String) data.get(3));
        setTextNode(e, "cost", (String) data.get(4));
        setTextNode(e, "description", (String) data.get(5));

    }

    public ArrayList getPowerArmorNodeData(int index) {
        ArrayList list = new ArrayList();
        Element armor = (Element) powerArmor.item(index);
        list.add(getNodeText(armor, "name"));
        list.add(getNodeText(armor, "model"));
        list.add(getNodeText(armor, "class"));
        list.add(getNodeText(armor, "crew"));
        ArrayList list2 = new ArrayList();
        NodeList nodes = armor.getElementsByTagName("MDCLocation");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            list2.add(getNodeText(e));
        }
        list.add(list2);
        list.add(getNodeText(armor, "speed"));
        list.add(getNodeText(armor, "statisticalData"));
        list2 = new ArrayList();
        nodes = armor.getElementsByTagName("weaponSystem");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            list2.add(getNodeText(e));
        }
        list.add(list2);        
        list.add(getNodeText(armor, "description"));
        list.add(getNodeText(armor, "cost"));
        list.add(getNodeText(armor, "ref"));
        return list;
    }

    public ArrayList getVehiculeNodeData(int index) {
        ArrayList list = new ArrayList();
        Element aVehicule = (Element) vehicules.item(index);
        list.add(getNodeText(aVehicule, "name"));
        list.add(getNodeText(aVehicule, "model"));
        list.add(getNodeText(aVehicule, "crew"));
        list.add(getNodeText(aVehicule, "speed"));
        list.add(getNodeText(aVehicule, "maxRange"));
        ArrayList list2 = new ArrayList();
        NodeList nodes = aVehicule.getElementsByTagName("MDCLocation");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            list2.add(getNodeText(e));
        }
        list.add(list2);
        list.add(getNodeText(aVehicule, "statisticData"));        
        list2 = new ArrayList();
        nodes = aVehicule.getElementsByTagName("weaponSystem");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            list2.add(getNodeText(e));
        }
        list.add(list2);
        list.add(getNodeText(aVehicule, "description"));
        list.add(getNodeText(aVehicule, "cost"));
        list.add(getNodeText(aVehicule, "ref"));
        return list;
    }

    public ArrayList getRobotVehiculeNodeData(int index) {
        ArrayList list = new ArrayList();
        Element robotVehicule = (Element) robotVehicules.item(index);
        list.add(getNodeText(robotVehicule, "name"));
        list.add(getNodeText(robotVehicule, "model"));
        list.add(getNodeText(robotVehicule, "class"));
        list.add(getNodeText(robotVehicule, "crew"));
        ArrayList list2 = new ArrayList();
        NodeList nodes = robotVehicule.getElementsByTagName("MDCLocation");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            list2.add(getNodeText(e));
        }
        list.add(list2);
        list.add(getNodeText(robotVehicule, "speed"));
        list.add(getNodeText(robotVehicule, "statisticalData"));
        list2 = new ArrayList();
        nodes = robotVehicule.getElementsByTagName("weaponSystem");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            list2.add(getNodeText(e));
        }
        list.add(list2);
        list.add(getNodeText(robotVehicule, "description"));
        list.add(getNodeText(robotVehicule, "cost"));
        list.add(getNodeText(robotVehicule, "ref"));

        return list;
    }

    void setPowerArmorData(int wIndex, ArrayList data) {
        Element e = (Element) powerArmor.item(wIndex);
        this.setTextNode(e, "name", (String) data.get(0));
        setTextNode(e, "model", (String) data.get(1));
        setTextNode(e, "class", (String) data.get(2));
        setTextNode(e, "crew", (String) data.get(3));
        ArrayList list = (ArrayList) data.get(4);
        NodeList nodes = e.getElementsByTagName("MDCLocation");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element mdc = (Element) nodes.item(i);
            e.removeChild(mdc);
        }
        for (int i = 0; i < list.size(); i++) {
            e.appendChild(createNodeText("MDCLocation", (String) list.get(i)));
        }
        setTextNode(e, "speed", (String) data.get(5));
        setTextNode(e, "statisticalData", (String) data.get(6));
        list = (ArrayList) data.get(7);
        nodes = e.getElementsByTagName("weaponSystem");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element weap = (Element) nodes.item(i);
            e.removeChild(weap);
        }
        for (int i = 0; i < list.size(); i++) {
            e.appendChild(createNodeText("weaponSystem", (String) list.get(i)));
        }
        setTextNode(e, "description", (String) data.get(8));
        setTextNode(e, "cost", (String) data.get(9));
        setTextNode(e, "ref", (String) data.get(10));
    }

    void setVehiculeData(int wIndex, ArrayList data) {
        Element e = (Element) vehicules.item(wIndex);
        this.setTextNode(e, "name", (String) data.get(0));
        setTextNode(e, "model", (String) data.get(1));
        setTextNode(e, "crew", (String) data.get(2));
        setTextNode(e, "speed", (String) data.get(3));
        setTextNode(e, "maxRange", (String) data.get(4));

        ArrayList list = (ArrayList) data.get(5);
        NodeList nodes = e.getElementsByTagName("MDCLocation");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element mdc = (Element) nodes.item(i);
            e.removeChild(mdc);
        }
        for (int i = 0; i < list.size(); i++) {
            e.appendChild(createNodeText("MDCLocation", (String) list.get(i)));
        }
        setTextNode(e, "statisticalData", (String) data.get(6));
        list = (ArrayList) data.get(7);
        nodes = e.getElementsByTagName("weaponSystem");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element weap = (Element) nodes.item(i);
            e.removeChild(weap);
        }
        for (int i = 0; i < list.size(); i++) {
            e.appendChild(createNodeText("weaponSystem", (String) list.get(i)));
        }
        setTextNode(e, "description", (String) data.get(8));
        setTextNode(e, "cost", (String) data.get(9));
        setTextNode(e, "ref", (String) data.get(10));
    }

    void setRobotVehiculeArmorData(int wIndex, ArrayList data) {
        Element e = (Element) robotVehicules.item(wIndex);
        this.setTextNode(e, "name", (String) data.get(0));
        setTextNode(e, "model", (String) data.get(1));
        setTextNode(e, "class", (String) data.get(2));
        setTextNode(e, "crew", (String) data.get(3));
        ArrayList list = (ArrayList) data.get(4);
        NodeList nodes = e.getElementsByTagName("MDCLocation");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element mdc = (Element) nodes.item(i);
            e.removeChild(mdc);
        }
        for (int i = 0; i < list.size(); i++) {
            e.appendChild(createNodeText("MDCLocation", (String) list.get(i)));
        }
        setTextNode(e, "speed", (String) data.get(5));
        setTextNode(e, "statisticalData", (String) data.get(6));
        list = (ArrayList) data.get(7);
        nodes = e.getElementsByTagName("weaponSystem");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element weap = (Element) nodes.item(i);
            e.removeChild(weap);
        }
        for (int i = 0; i < list.size(); i++) {
            e.appendChild(createNodeText("weaponSystem", (String) list.get(i)));
        }
        setTextNode(e, "description", (String) data.get(8));
        setTextNode(e, "cost", (String) data.get(9));
        setTextNode(e, "ref", (String) data.get(10));
    }

    void addPowerArmor(ArrayList list) {
        Element root = document.getDocumentElement();
        NodeList nodes = root.getElementsByTagName("armors");
        Element armorNode = (Element) nodes.item(0);

        Element armor = document.createElement("powerArmor");
        armor.appendChild(createNodeText("name",(String) list.get(0)));
        armor.appendChild(createNodeText("model",(String) list.get(1)));
        armor.appendChild(createNodeText("class",(String) list.get(2)));
        armor.appendChild(createNodeText("crew",(String) list.get(3)));
        ArrayList data = (ArrayList) list.get(4);
        for(int i = 0; i <data.size();i++ ){
            armor.appendChild(createNodeText("MDCLocation",(String) data.get(i)));
        }
        armor.appendChild(createNodeText("speed",(String) list.get(5)));
        armor.appendChild(createNodeText("statisticalData",(String) list.get(6)));
        data = (ArrayList) list.get(7);
        for(int i = 0; i <data.size();i++ ){
            armor.appendChild(createNodeText("weaponSystem",(String) data.get(i)));
        }
        armor.appendChild(createNodeText("description",(String) list.get(8)));
        armor.appendChild(createNodeText("cost",(String) list.get(9)));
        armor.appendChild(createNodeText("ref",(String) list.get(10)));

        armorNode.appendChild(armor);
    }

    void addVehicule(ArrayList list) {
        Element root = document.getDocumentElement();
        NodeList nodes = root.getElementsByTagName("vehicules");
        Element vehiculeNode = (Element) nodes.item(0);

        Element vehicule = document.createElement("vehicule");
        vehicule.appendChild(createNodeText("name",(String) list.get(0)));
        vehicule.appendChild(createNodeText("model",(String) list.get(1)));
        vehicule.appendChild(createNodeText("crew",(String) list.get(2)));
        vehicule.appendChild(createNodeText("speed",(String) list.get(3)));
        vehicule.appendChild(createNodeText("maxRange",(String) list.get(4)));
        ArrayList data = (ArrayList) list.get(5);
        for(int i = 0; i <data.size();i++ ){
            vehicule.appendChild(createNodeText("MDCLocation",(String) data.get(i)));
        }
        vehicule.appendChild(createNodeText("statisticalData",(String) list.get(6)));
        data = (ArrayList) list.get(7);
        for(int i = 0; i <data.size();i++ ){
            vehicule.appendChild(createNodeText("weaponSystem",(String) data.get(i)));
        }
        vehicule.appendChild(createNodeText("description",(String) list.get(8)));
        vehicule.appendChild(createNodeText("cost",(String) list.get(9)));
        vehicule.appendChild(createNodeText("ref",(String) list.get(10)));

        vehiculeNode.appendChild(vehicule);
    }

    void addRobotVehicule(ArrayList list) {
       Element root = document.getDocumentElement();
        NodeList nodes = root.getElementsByTagName("vehicules");
        Element robotVehiculeNode = (Element) nodes.item(0);

        Element robotVehicule = document.createElement("robotVehicule");
        robotVehicule.appendChild(createNodeText("name",(String) list.get(0)));
        robotVehicule.appendChild(createNodeText("model",(String) list.get(1)));
        robotVehicule.appendChild(createNodeText("class",(String) list.get(2)));
        robotVehicule.appendChild(createNodeText("crew",(String) list.get(3)));
        ArrayList data = (ArrayList) list.get(4);
        for(int i = 0; i <data.size();i++ ){
            robotVehicule.appendChild(createNodeText("MDCLocation",(String) data.get(i)));
        }
        robotVehicule.appendChild(createNodeText("speed",(String) list.get(5)));
        robotVehicule.appendChild(createNodeText("statisticalData",(String) list.get(6)));
        data = (ArrayList) list.get(7);
        for(int i = 0; i <data.size();i++ ){
            robotVehicule.appendChild(createNodeText("weaponSystem",(String) data.get(i)));
        }
        robotVehicule.appendChild(createNodeText("description",(String) list.get(8)));
        robotVehicule.appendChild(createNodeText("cost",(String) list.get(9)));
        robotVehicule.appendChild(createNodeText("ref",(String) list.get(10)));

        robotVehiculeNode.appendChild(robotVehicule);
    }
}
