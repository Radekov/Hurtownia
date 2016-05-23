/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurt.servlets.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Priorities;
import pl.pawww.hurt.jpa.Users;
import pl.pawww.hurt.jpa.UsersFacade;

/**
 * Serwlet login, odbiera formularz, w którym powinno znajdywać się wypełnione pola
 * login i password oraz opcjonalnie zaznaczony checkbox zapamietaj. Wyszukuje po
 * loginie w tabeli Users po nazwie użytkownika, następnie jeżeli nie jest puste, to
 * porównuje hasło(błędne, ponieważ tabela nie zawiera haseł zahaszowanych, porównanie
 * haseł jest bez zabezpieczenia poprzez hashowanie). Jeżeli logowanie się powiodło
 * to zapisuje do sesji atrybut "login". Jeżeli zaznaczono "zapamiętaj" zapisuje ciastka
 * jako login i hasło.
 * 
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
        
        List<Users> users = usersFacade.findAllByLogin(login);
        Users user = null;
        if(users != null)
            user = users.get(0);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession(true);
            synchronized (session) {
                session.setAttribute("login", user.getLogin());
            }
            if (zapamietaj != null && zapamietaj.equals("on")) {
                synchronized (session) {
                    /*
                    Cookie cookie = new Cookie("IdSesji", session.getId());
                    cookie.setMaxAge(60 * 60);
                    response.addCookie(cookie);
                    */
                    Cookie cookie = new Cookie("zapamietaj", zapamietaj);
                    cookie.setMaxAge(60 * 60 * 24 * 365);
                    response.addCookie(cookie);
                    
                    cookie = new Cookie("login", user.getLogin());
                    cookie.setMaxAge(60 * 60 * 24 * 365);
                    response.addCookie(cookie);
                    
                }
            }
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            response.sendRedirect("/Hurt/index.jsp");

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
