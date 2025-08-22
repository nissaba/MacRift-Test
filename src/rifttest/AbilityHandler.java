/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rifttest;

import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Pascale
 */
public class AbilityHandler extends XMLHandler {

    private Element Spells;
    private Element Psionics;

    public ArrayList getMageSphere() {
        ArrayList list = new ArrayList();
        NodeList nodes = Spells.getElementsByTagName("mageSphere");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            list.add(e.getAttribute("name"));
        }
        return list;
    }

    public ArrayList getSpellLevels(int index) {

        ArrayList list = new ArrayList();
        NodeList nodes = Spells.getElementsByTagName("mageSphere");
        Element sphere = (Element) nodes.item(index);
        nodes = sphere.getElementsByTagName("level");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            list.add(e.getAttribute("lvl"));
        }
        return list;
    }

    public ArrayList getPsionicCategories(int index) {
        ArrayList list = new ArrayList();
        NodeList nodes = Psionics.getElementsByTagName("psiSphere");
        Element psiSphere = (Element) nodes.item(index);
        nodes = psiSphere.getElementsByTagName("category");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            list.add(e.getAttribute("type"));
        }
        return list;
    }

    public ArrayList getSpells(int sphereIndex, int levelIndex) {
        NodeList nodes = Spells.getElementsByTagName("mageSphere");
        Element e = (Element) nodes.item(sphereIndex);
        nodes = e.getElementsByTagName("level");
        e = (Element) nodes.item(levelIndex);
        nodes = e.getElementsByTagName("spell");
        ArrayList list = new ArrayList();
        for (int i = 0; i < nodes.getLength(); i++) {
            Element spell = (Element) nodes.item(i);
            NodeList names = spell.getElementsByTagName("name");
            list.add(getNodeText((Element) names.item(0)));
        }
        return list;
    }

    public ArrayList getPsionics(int sphereIndex, int categoryIndex) {
        NodeList nodes = Psionics.getElementsByTagName("psiSphere");
        Element e = (Element) nodes.item(sphereIndex);
        nodes = e.getElementsByTagName("category");
        e = (Element) nodes.item(categoryIndex);
        nodes = e.getElementsByTagName("psipower");
        ArrayList list = new ArrayList();
        for (int i = 0; i < nodes.getLength(); i++) {
            Element spell = (Element) nodes.item(i);
            NodeList names = spell.getElementsByTagName("name");
            list.add(getNodeText((Element) names.item(0)));
        }
        return list;
    }

    public void createMainSection(String name, int type) {
        if (type == 0) {
            Element sphere = document.createElement("mageSphere");
            sphere.setAttribute("name", name);
            Spells.appendChild(sphere);
        } else if (type == 1) {
            Element sphere = document.createElement("psiSphere");
            sphere.setAttribute("name", name);
            Psionics.appendChild(sphere);
        }
    }

    public void createNewSpellLevel(String name, int sphereIndex) {
        Element level = document.createElement("level");
        level.setAttribute("lvl", name);

        NodeList nodes = Spells.getElementsByTagName("mageSphere");
        Element e = (Element) nodes.item(sphereIndex);
        e.appendChild(level);
    }

    public void createNewPsiCategory(String name, int sphereIndex) {
        Element category = document.createElement("category");
        category.setAttribute("type", name);

        NodeList nodes = Psionics.getElementsByTagName("psiSphere");
        Element e = (Element) nodes.item(sphereIndex);
        e.appendChild(category);
    }

    public void removeSpellSphere(int selectedIndex) {
        NodeList nodes = Spells.getElementsByTagName("mageSphere");
        Spells.removeChild(nodes.item(selectedIndex));
    }

    public void removePsiSphere(int selectedIndex) {
        NodeList nodes = Psionics.getElementsByTagName("psiSphere");
        Psionics.removeChild(nodes.item(selectedIndex));
    }

    public void removePsiCategory(int sphereIndex, int categoryIndex) {
        NodeList nodes = Psionics.getElementsByTagName("psiSphere");
        Element e = (Element) nodes.item(sphereIndex);
        nodes = e.getElementsByTagName("category");
        e.removeChild(nodes.item(categoryIndex));
    }

    @Override
    public boolean loadXMLFile(String path) {
        boolean done = super.loadXMLFile(path);
        Element e = document.getDocumentElement();
        NodeList nodes = e.getElementsByTagName("spells");
        Spells = (Element) nodes.item(0);
        nodes = e.getElementsByTagName("psionics");
        Psionics = (Element) nodes.item(0);
        return done;
    }

    public void setSpellSphereName(String name, int sphereIndex) {
        NodeList nodes = Spells.getElementsByTagName("mageSphere");
        Element e = (Element) nodes.item(sphereIndex);
        e.setAttribute("name", name);
    }

    public void setSpellLevel(String name, int sphereIndex, int levelIndex) {
        NodeList nodes = Spells.getElementsByTagName("mageSphere");
        Element e = (Element) nodes.item(sphereIndex);
        nodes = e.getElementsByTagName("level");
        e = (Element) nodes.item(levelIndex);
        e.setAttribute("lvl", name);
    }

    public void setPsiSphereName(String name, int index) {
        NodeList nodes = Psionics.getElementsByTagName("psiSphere");
        Element e = (Element) nodes.item(index);
        e.setAttribute("name", name);
    }

    public void setPsionicCategory(String name, int sphereIndex, int categoryIndex) {
        NodeList nodes = Psionics.getElementsByTagName("psiSphere");
        Element e = (Element) nodes.item(sphereIndex);
        nodes = e.getElementsByTagName("category");
        e = (Element) nodes.item(categoryIndex);
        e.setAttribute("type", name);
    }

    public ArrayList getSpellInfo(int sphereIndex, int levelIndex, int spellIndex) {
        NodeList nodes = Spells.getElementsByTagName("mageSphere");
        Element e = (Element) nodes.item(sphereIndex);
        nodes = e.getElementsByTagName("level");
        e = (Element) nodes.item(levelIndex);
        nodes = e.getElementsByTagName("spell");
        e = (Element) nodes.item(spellIndex);
        ArrayList list = new ArrayList();
        nodes = e.getElementsByTagName("name");
        Element element = (Element) nodes.item(0);
        if (e != null) {
            list.add(this.getNodeText(element));
        } else {
            list.add("");
        }
        nodes = e.getElementsByTagName("range");
        element = (Element) nodes.item(0);
        if (element != null) {
            list.add(this.getNodeText(element));
        } else {
            list.add("");
        }
        nodes = e.getElementsByTagName("duration");
        element = (Element) nodes.item(0);
        if (element != null) {
            list.add(this.getNodeText(element));
        } else {
            list.add("");
        }
        nodes = e.getElementsByTagName("damage");
        element = (Element) nodes.item(0);
        if (element != null) {
            list.add(this.getNodeText(element));
        } else {
            list.add("");
        }
        nodes = e.getElementsByTagName("ppe");
        element = (Element) nodes.item(0);
        if (element != null) {
            list.add(this.getNodeText(element));
        } else {
            list.add("");
        }
        nodes = e.getElementsByTagName("saving");
        element = (Element) nodes.item(0);
        if (element != null) {
            list.add(this.getNodeText(element));
        } else {
            list.add("");
        }
        nodes = e.getElementsByTagName("description");
        element = (Element) nodes.item(0);
        if (element != null) {
            list.add(this.getNodeText(element));
        } else {
            list.add("");
        }
        return list;
    }

    public void appendSpell(int sphereIndex, int levelIndex, ArrayList data) {

        NodeList nodes = Spells.getElementsByTagName("mageSphere");
        Element e = (Element) nodes.item(sphereIndex);
        nodes = e.getElementsByTagName("level");
        e = (Element) nodes.item(levelIndex);
        e.appendChild(createSpellNode(data));

    }

    public ArrayList getPsionicInfo(int sphereIndex, int categoryIndex, int psiIndex) {
        NodeList nodes = Psionics.getElementsByTagName("psiSphere");
        Element e = (Element) nodes.item(sphereIndex);
        nodes = e.getElementsByTagName("category");
        e = (Element) nodes.item(categoryIndex);
        nodes = e.getElementsByTagName("psipower");
        e = (Element) nodes.item(psiIndex);
        ArrayList list = new ArrayList();

        nodes = e.getElementsByTagName("name");
        Element element = (Element) nodes.item(0);
        if (element != null) {
            list.add(this.getNodeText(element));
        } else {
            list.add("");
        }
        nodes = e.getElementsByTagName("range");
        element = (Element) nodes.item(0);
        if (element != null) {
            list.add(this.getNodeText(element));
        } else {
            list.add("");
        }
        nodes = e.getElementsByTagName("duration");
        element = (Element) nodes.item(0);
        if (element != null) {
            list.add(this.getNodeText(element));
        } else {
            list.add("");
        }
        nodes = e.getElementsByTagName("trance");
        element = (Element) nodes.item(0);
        if (element != null) {
            list.add(this.getNodeText(element));
        } else {
            list.add("");
        }
        nodes = e.getElementsByTagName("isp");
        element = (Element) nodes.item(0);
        if (element != null) {
            list.add(this.getNodeText(element));
        } else {
            list.add("");
        }
        nodes = e.getElementsByTagName("saving");
        element = (Element) nodes.item(0);
        if (element != null) {
            list.add(this.getNodeText(element));
        } else {
            list.add("");
        }
        nodes = e.getElementsByTagName("description");
        element = (Element) nodes.item(0);
        if (element != null) {
            list.add(this.getNodeText(element));
        } else {
            list.add("");
        }
        return list;
    }

    public void appendPsionic(int sphereIndex, int levelIndex, ArrayList data) {

        NodeList nodes = Psionics.getElementsByTagName("psiSphere");
        Element e = (Element) nodes.item(sphereIndex);
        nodes = e.getElementsByTagName("category");
        e = (Element) nodes.item(levelIndex);
        e.appendChild(this.createPsionicNode(data));
    }

    public void removePsionicNode(int shpereIndex, int levelIndex, int psiIndex) {
        NodeList nodes = Psionics.getElementsByTagName("psiSphere");
        Element e = (Element) nodes.item(shpereIndex);
        nodes = e.getElementsByTagName("category");
        e = (Element) nodes.item(levelIndex);
        nodes = e.getElementsByTagName("psipower");
        
        e.removeChild(nodes.item(psiIndex));
    }

    public void removeSpellNode(int sphereIndex, int levelIndex, int spellIndex) {
        NodeList nodes = Spells.getElementsByTagName("mageSphere");
        Element e = (Element) nodes.item(sphereIndex);
        nodes = e.getElementsByTagName("level");
        e = (Element) nodes.item(levelIndex);
        nodes = e.getElementsByTagName("spell");
        Element remNode = (Element) nodes.item(spellIndex);
        e.removeChild(remNode);
    }

    public void updateSpell(int sphereIndex, int levelIndex, int ability, ArrayList data) {
        NodeList nodes = Spells.getElementsByTagName("mageSphere");
        Element e = (Element) nodes.item(sphereIndex);
        nodes = e.getElementsByTagName("level");
        e = (Element) nodes.item(levelIndex);
        nodes = e.getElementsByTagName("spell");
        Element oldSpell = (Element) nodes.item(ability);
        e.replaceChild(this.createSpellNode(data), oldSpell);
    }

    public void updatePsionicNode(int sphereIndex, int categoryIndex, int psiIndex, ArrayList data) {
        NodeList nodes = Psionics.getElementsByTagName("psiSphere");
        Element e = (Element) nodes.item(sphereIndex);
        nodes = e.getElementsByTagName("category");
        e = (Element) nodes.item(categoryIndex);
        nodes = e.getElementsByTagName("psipower");
        Element oldPsionic = (Element) nodes.item(psiIndex);
        e.replaceChild(this.createPsionicNode(data), oldPsionic);
    }

    public Element createSpellNode(ArrayList data) {
        Element spell = document.createElement("spell");

        spell.appendChild(this.createNodeText("name", (String) data.get(0)));
        spell.appendChild(this.createNodeText("range", (String) data.get(1)));
        spell.appendChild(this.createNodeText("duration", (String) data.get(2)));
        spell.appendChild(this.createNodeText("damage", (String) data.get(3)));
        spell.appendChild(this.createNodeText("ppe", (String) data.get(4)));
        spell.appendChild(this.createNodeText("saving", (String) data.get(5)));
        spell.appendChild(this.createNodeText("description", (String) data.get(6)));
        return spell;
    }

    public Element createPsionicNode(ArrayList data) {
        Element psionic = document.createElement("psipower");
        psionic.appendChild(this.createNodeText("name", (String) data.get(0)));
        psionic.appendChild(this.createNodeText("range", (String) data.get(1)));
        psionic.appendChild(this.createNodeText("duration", (String) data.get(2)));
        psionic.appendChild(this.createNodeText("trance", (String) data.get(3)));
        psionic.appendChild(this.createNodeText("isp", (String) data.get(4)));
        psionic.appendChild(this.createNodeText("saving", (String) data.get(5)));
        psionic.appendChild(this.createNodeText("description", (String) data.get(6)));
        return psionic;
    }

   public ArrayList getPsiSphere() {
       ArrayList list = new ArrayList();
       NodeList nodes = Psionics.getElementsByTagName("psiSphere");
       for (int i =0; i< nodes.getLength();i++){
           Element e = (Element) nodes.item(i);
           list.add(e.getAttribute("name"));
       }
       return list;
    }

    public void removeSpellLevel(int sphereIndex, int levelIndex) {
        NodeList nodes = Spells.getElementsByTagName("mageSphere");
        Element e = (Element) nodes.item(sphereIndex);
        nodes = e.getElementsByTagName("level");

        e.removeChild(nodes.item(levelIndex));
    }


}
