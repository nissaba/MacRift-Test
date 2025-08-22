/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rifttest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

/**
 *
 * @author Pascale
 */
public class xmlIO {

    public void WriteXMLFIle(Document document, String filename) {
        try {
            // create DOMSource for source XML document
            Source xmlSource = new DOMSource(document);

            // create StreamResult for transformation result
            Result result = new StreamResult(new FileOutputStream(filename));

            // create TransformerFactory
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            // create Transformer for transformation
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes"); //Java XML Indent

            // transform and deliver content to client
            transformer.transform(xmlSource, result);
        } // handle exception creating TransformerFactory
        catch (TransformerFactoryConfigurationError factoryError) {
            System.err.println("Error creating " + "TransformerFactory");
            factoryError.printStackTrace();
        } catch (TransformerException transformerError) {
            System.err.println("Error transforming document");
            transformerError.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public Document LoadXMLFile(String filename) {
        Document doc = null;
        try {
            File file = new File(filename);
            if (file.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                doc = builder.parse(file);
            } else {
                System.out.println("File not found!");
            }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(0);
        }
        return doc;
    }
}
