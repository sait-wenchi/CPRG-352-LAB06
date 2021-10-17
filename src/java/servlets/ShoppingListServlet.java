/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author wenchi
 */
public class ShoppingListServlet extends HttpServlet {

    

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
        HttpSession session = request.getSession();
        if ((String)session.getAttribute("userName") == null)
        {
            System.out.println("no session data(username) found");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);
            return;
        }
        String operation = request.getParameter("action");
        if( operation != null && operation.equals("logout")){
            System.out.println("logout");
            // if the user has clcik the reset button, invalidate the session
            session.invalidate();
            // we still need a session for the rest of code, so get new session
            session = request.getSession();
            request.setAttribute("message", "user has successfully logged out");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);
            return;
        } 
        System.out.println("load session");
        request.setAttribute("username",(String) session.getAttribute("userName"));
        ArrayList<String> itemlist= (ArrayList<String>) session.getAttribute("itemList");
        request.setAttribute("itemlist",itemlist);
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request,response);
        
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
        
        HttpSession session = request.getSession();
        String operation = request.getParameter("action");
        if( operation != null && operation.equals("register"))
        {
            System.out.println("register");
            String username = request.getParameter("username");
            if(username != null && !username.equals(""))
            {
                System.out.println("save username");
                session.setAttribute("userName",(String)request.getParameter("username"));
                session.setAttribute("itemList",new ArrayList<String>());
                request.setAttribute("username", (String)session.getAttribute("userName"));
                request.setAttribute("itemlist", (ArrayList<String>)session.getAttribute("itemList"));
            }
            else
            {
                request.setAttribute("message","invalid user name");
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);
                return;
            }
        }
        else if( operation != null && operation.equals("add"))
        {
            System.out.println("add");
            String itemName = request.getParameter("itemname");
            ArrayList<String> itemList= (ArrayList<String>) session.getAttribute("itemList");
            if(itemName != null && !itemName.equals(""))
            {
                itemList.add(itemName);
                session.setAttribute("itemList",itemList);
            }
            request.setAttribute("itemlist",itemList);
            request.setAttribute("username", (String) session.getAttribute("userName"));
            
        }
        else if( operation != null && operation.equals("delete"))
        {
            System.out.println("delete");
            String selectItem = request.getParameter("selectname");
            ArrayList<String> itemList= (ArrayList<String>) session.getAttribute("itemList");
            if (selectItem != null && !selectItem.equals(""))
            {
                itemList.remove(selectItem);
                session.setAttribute("itemList",itemList);
            }
            request.setAttribute("username", (String) session.getAttribute("userName"));
            request.setAttribute("itemlist",itemList);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request,response);
            
    }

}
