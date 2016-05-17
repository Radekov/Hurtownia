/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurt.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.Cookie;

/**
 * Web application lifecycle listener.
 *
 * @author r
 */
public class ContextListenerAplikacji implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String zalogowany = sce.getServletContext().getInitParameter("zalogowany");
        System.out.println(zalogowany+"-------------------------------------------------");
        //if(zalogowany.equals("on")){
            
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
