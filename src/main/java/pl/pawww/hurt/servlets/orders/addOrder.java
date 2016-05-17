/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurt.servlets.orders;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.pawww.hurt.jpa.Orders;
import pl.pawww.hurt.jpa.OrdersFacade;
import pl.pawww.hurt.jpa.OrdersProdut;
import pl.pawww.hurt.jpa.OrdersProdutFacade;
import pl.pawww.hurt.jpa.Products;
import pl.pawww.hurt.jpa.ProductsFacade;
import pl.pawww.hurt.jpa.Shops;
import pl.pawww.hurt.jpa.ShopsFacade;

/**
 *
 * @author r
 */
public class addOrder extends HttpServlet {

    @EJB
    OrdersFacade ordersFacade;
    @EJB
    ProductsFacade productsFacade;
    @EJB
    ShopsFacade shopsFacade;
    @EJB
    OrdersProdutFacade ordersProdutFacade;

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
        Orders order = new Orders();
        //Ustawienie sklepu
        String sklep = request.getParameter("sklep");
        Shops shop = shopsFacade.findAllBySklep("'" + sklep + "'").get(0);
        order.setIdShops(shop);

        //Ustawienie daty
        /*
        SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");//sprawdzać po tym lub Date > Date
        Date today = new Date();
        String output = dateformat.format(today);
        */
        order.setDateStart(new Date());
        ordersFacade.create(order);
        
        //Jeżeli po create samo ustawia id to dobrze
        //Jeżeli nie to trzeba kombinować - pobrać ostatnio dodany
        //bo ordersProdut.setIdOrder(order); !@#$%^&*
        
        //OrdersProdut ordersProdut;
        //Tworzenie listy zamówienia
        Products produkt;
        List<Products> p;
        String[] produkty = request.getParameterValues("produkt");
        Integer[] ilosc = new Integer[produkty.length];
        for (int i = 0; i < produkty.length; i++) {
            ilosc[i] = Integer.parseInt(request.getParameter(produkty[i] + "ilosc"));
            produkt = productsFacade.findAllByNazwa(produkty[0]).get(0);
            OrdersProdut ordersProdut = new OrdersProdut();
            ordersProdut.setIdOrder(order);
            ordersProdut.setIdProduct(produkt);
            ordersProdut.setLiczbaSztuk(ilosc[i]);
            ordersProdutFacade.create(ordersProdut);
            
            //Problem podobny co do !@#$%^&*
            //Czy jest sens dodawać skoro to w ordersProduct już jest powiązanie do tego
            order.add(ordersProdut);
        }
        ordersFacade.edit(order);

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
