package example.server;

import example.User;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Heavenkenyn on 2017/4/17.
 */

public class XMLFactory {
    private static XMLFactory xmlFactory;
    private File file;
    private Document document;
    private Element root;
    //private final String USER = "user";
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String BIRTHDAY = "birthday";
    private final String TELEPHONE = "telephone";
    private final String ADDRESS = "address";

    private XMLFactory(File file){
        this.file = file;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse(file);
            root = document.getDocumentElement();
        } catch (Exception e) {
            document = null;
        }
    }

    public synchronized static XMLFactory getInstance(File file){
        if (xmlFactory==null){
            xmlFactory = new XMLFactory(file);
        }
        return xmlFactory;
    }

    public boolean createElement(User user){
        String username = user.getUsername();
        if (root.getElementsByTagName(username).getLength()>0){
            return false;
        }else {
            Element xml = document.createElement(username);
            /*Element sec;
            sec = document.createElement(USERNAME);
            sec.setTextContent(user.getUsername());
            xml.appendChild(sec);*/
            xml.setAttribute(PASSWORD,user.getPassword());
            xml.setAttribute(BIRTHDAY,user.getBirthday());
            xml.setAttribute(TELEPHONE,user.getTelephone());
            xml.setAttribute(ADDRESS,user.getAddress());
            root.appendChild(xml);
            return true;
        }
    }

    private Element getElement(String username){
        Element xml = null;
        NodeList list = root.getElementsByTagName(username);
        if (list.getLength()>0){
            xml = (Element) list.item(0);
        }
        return xml;
    }

    public String getPassword(String username){
        Element xml = getElement(username);
        if (xml != null){
            return xml.getAttribute(PASSWORD);
        }
        return null;
    }

    public boolean updateElement(String username,String attr,String value){
        Element xml = getElement(username);
        if (xml != null){
            xml.setAttribute(attr,value);
            return true;
        }
        return false;
    }

    public void save(){
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer t = tf.newTransformer();document.getElementsByTagName("");
            t.setOutputProperty(OutputKeys.INDENT,"yes");
            t.transform(new DOMSource(document),new StreamResult(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
