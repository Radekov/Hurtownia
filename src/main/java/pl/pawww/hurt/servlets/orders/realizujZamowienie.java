/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurt.servlets.orders;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import pl.pawww.hurt.jpa.Orders;
import pl.pawww.hurt.jpa.OrdersFacade;
import pl.pawww.hurt.jpa.OrdersProdut;
import pl.pawww.hurt.jpa.Products;
import pl.pawww.hurt.jpa.ProductsFacade;

/**
 *
 * @author r
 */
public class realizujZamowienie extends HttpServlet {

    @EJB
    OrdersFacade ordersFacade;
    @EJB
    ProductsFacade productsFacade;

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
        Integer id = Integer.parseInt((String) request.getParameter("id"));
        System.out.println(id);
        Orders order = ordersFacade.find(id);
        boolean mozliwe_do_realizacji = true;
        for (OrdersProdut op : order.getOrdersProdutCollection()) {
            if (op.getLiczbaSztuk() > op.getIdProduct().getLiczbaSztuk()) {
                mozliwe_do_realizacji = false;
            }
        }
        if (mozliwe_do_realizacji) {
            for (OrdersProdut op : order.getOrdersProdutCollection()) {
                Products p = op.getIdProduct();
                p.setLiczbaSztuk(p.getLiczbaSztuk() - op.getLiczbaSztuk());
                productsFacade.edit(p);
            }
            order.setDateEnd(new Date());
            ordersFacade.edit(order);
            //WYKREOWAC CALY ORDER DO XMLa
            JAXBContext jaxbc;
            Marshaller m;
            try {
                jaxbc = JAXBContext.newInstance(Orders.class);
                m = jaxbc.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                m.marshal(order, new File("/home/r/Pulpit/Zamówienie_"+order.getId()+".xml"));//handler plik
            } catch (JAXBException ex) {
                Logger.getLogger(realizujZamowienie.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        request.getRequestDispatcher("sendProductsToOrders").forward(request, response);
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
