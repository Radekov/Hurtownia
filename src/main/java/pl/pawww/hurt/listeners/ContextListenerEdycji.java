/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurt.listeners;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 * Web application lifecycle listener. Na początku programu ustawia dwa atrybuty
 * na false, takie pseudo zabezpieczenie gdy co najmniej dwóch ludzi w tym samym
 * czasie zmodyfikowało informacje o przedmiocie lub zamówieniu to tylko jednego
 * z nich przejdzie żądanie. Oczywiście działa już JTA, który został ustawiony w
 * psersistence.xml. Ustawia również globalny URL dla schematu XML.
 *
 * @param edytProduct
 * @param edytOrder
 * @author r
 */
public class ContextListenerEdycji implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Działa Initialized:" + this.getClass().getName());
        ServletContext sc = sce.getServletContext();
        boolean edytProduct = false, edytOrder = false;
        sc.setAttribute("edytProduct", edytProduct);
        sc.setAttribute("edytOrder", edytOrder);
        
        URL schemaFile = this.getClass().getClassLoader().getResource("schemaXML.xsd");
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Validator validator = null;
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            validator = schema.newValidator();
        } catch (SAXException ex) {
            Logger.getLogger(ContextListenerEdycji.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(validator);
        
        sc.setAttribute("validator", validator);
        System.out.println("Kończy Initialized:" + this.getClass().getName());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Działa Destroyed:" + this.getClass().getName());
        ServletContext sc = sce.getServletContext();
        boolean edytProduct = false, edytOrder = false;
        sc.setAttribute("edytProduct", edytProduct);
        sc.setAttribute("edytOrder", edytOrder);
        System.out.println("Kończy Destroyed:" + this.getClass().getName());
    }
}
