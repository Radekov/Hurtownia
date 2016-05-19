/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurt.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 * Na początku programu ustawia dwa atrybuty na false, takie pseudo zabezpieczenie
 * gdy co najmniej dwóch ludzi w tym samym czasie zmodyfikowało informacje o przedmiocie lub zamówieniu
 * to tylko jednego z nich przejdzie żądanie. Oczywiście działa już JTA, który został ustawiony w psersistence.xml
 * @param edytProduct
 * @param edytOrder
 * @author r
 */
public class ContextListenerEdycji implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        boolean edytProduct = false, edytOrder = false;
        sc.setAttribute("edytProduct", edytProduct);
        sc.setAttribute("edytOrder", edytOrder);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        boolean edytProduct = false, edytOrder = false;
        sc.setAttribute("edytProduct", edytProduct);
        sc.setAttribute("edytOrder", edytOrder);
    }
}
