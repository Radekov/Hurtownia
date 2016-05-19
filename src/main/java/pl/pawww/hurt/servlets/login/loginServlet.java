/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurt.servlets.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.pawww.hurt.jpa.Users;
import pl.pawww.hurt.jpa.UsersFacade;

/**
 * 
 * @author r
 */
public class loginServlet extends HttpServlet {

    @EJB
    UsersFacade usersFacade;

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
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String zapamietaj = request.getParameter("zapamietaj");//jesli zaznaczone = "on"
        Users user = usersFacade.findAllByLogin(login).get(0);
        if (user == null || !user.getPassword().equals(password)) {
            response.sendRedirect("/Hurt/index.jsp");
        } else {
            HttpSession session = request.getSession(true);
            synchronized (session) {
                session.setAttribute("user", user);
            }
            if (zapamietaj != null && zapamietaj.equals("on")) {
                synchronized (session) {
                    Cookie cookie = new Cookie("IdSesji", session.getId());
                    cookie.setMaxAge(60 * 60);
                    response.addCookie(cookie);
                    //session.setMaxInactiveInterval(0);
                }
            }
            System.out.println(session.getId());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
