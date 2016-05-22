/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurt.servlets.orders;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
import pl.pawww.hurt.jpa.Orders;

/**
 *
 * @author r
 */
@MultipartConfig
public class addOrderByXML extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());
        List<String> dodano = new ArrayList<>();
        List<String> niedodano = new ArrayList<>();
        Validator validator = (Validator) request.getServletContext().getAttribute("validator");
        JAXBContext jaxbc = null;
        Unmarshaller jaxbUnmarshaller = null;
        try {
             jaxbc = JAXBContext.newInstance(Orders.class);
             jaxbUnmarshaller = jaxbc.createUnmarshaller();
        } catch (JAXBException ex) {
            Logger.getLogger(addOrderByXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (validator != null && jaxbc !=null && jaxbUnmarshaller != null) {
            for (Part filePart : fileParts) {
                Source xmlFile = new StreamSource(filePart.getInputStream());
                try {
                    validator.validate(xmlFile);
                    Orders o = (Orders) jaxbUnmarshaller.unmarshal(xmlFile);
                    
                    
                    System.out.println(o);
                    
                    
                    
                    dodano.add(filePart.getSubmittedFileName());
                } catch (SAXException ex) {
                    niedodano.add(filePart.getSubmittedFileName());
                } catch (JAXBException ex) {
                    niedodano.add(filePart.getSubmittedFileName());
                }

            }
        }
        request.setAttribute("poprawne", dodano);
        request.setAttribute("niepoprawne", niedodano);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
