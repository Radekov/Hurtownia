/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurt.servlets.orders;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.pawww.hurt.jpa.Products;
import pl.pawww.hurt.jpa.ProductsFacade;
import pl.pawww.hurt.jpa.Shops;
import pl.pawww.hurt.jpa.ShopsFacade;

/**
 *
 * @author r
 */
@WebServlet(name = "sendProductsToOrders", urlPatterns = {"/sendProductsToOrders"})
public class sendProductsToOrders extends HttpServlet {

    @EJB
    ProductsFacade productsFacade;
    @EJB
    ShopsFacade shopsFacade;
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
        System.out.println(request.getRequestURL());
        response.setContentType("text/html;charset=UTF-8");
        System.out.println(this.getClass().getName()+" teraz działa serwlet");
        List<Products> produkty = productsFacade.findAll();
        request.setAttribute("produkty", produkty);
        
        List<Shops> shops = shopsFacade.findAll();
        request.setAttribute("shops", shops);
        System.out.println(this.getClass().getName()+" teraz końcczy serwlet");
        request.getRequestDispatcher("/restricted/orders/index.jsp").forward(request, response);
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
