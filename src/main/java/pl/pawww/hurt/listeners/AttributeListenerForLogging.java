package pl.pawww.hurt.listeners;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Web application lifecycle listener.
 *
 * @author r
 */
public class AttributeListenerForLogging implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (event.getName().equals("login")) {
            // /home/r/Pulpit/tmp/user.log
            File file = new File("tmp_logs/"+(String) event.getValue() + ".log");
            System.out.println(file.getAbsoluteFile());
            try {
                if (!file.exists()) {
                    if (file.createNewFile()) {
                        System.out.println(file.getName());
                        FileWriter fileWriter = new FileWriter(file.getAbsolutePath(), true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:");
                        Date acctuall = Calendar.getInstance().getTime();
                        bufferedWriter.write(format.format(acctuall)+"Utworzono plik log dla:"+(String) event.getValue()+"\n");
                        bufferedWriter.close();
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(AttributeListenerForLogging.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
