/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author daniel.psavoy
 */
public class Autenticar extends HttpServlet {

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
        
        String username = request.getParameter("username");
        String imageUrl = request.getParameter("imageUrl");
        
        if(username != null && username.length() > 2){
            Usuario usuario = new Usuario(username, imageUrl);
            request.getSession().setAttribute("eu", usuario);
            ArrayList<Usuario> logados = (ArrayList) request
                    .getServletContext().getAttribute("logados");
            if(logados == null){
                logados = new ArrayList<Usuario>();
                request.getServletContext().setAttribute("logados", logados);
            }
            logados.add(usuario);
            
            ArrayList<String[]> conversas = (ArrayList) request
                    .getServletContext().getAttribute("conversas");
            if(conversas == null){
                conversas = new ArrayList<String[]>();
                request.getServletContext().setAttribute("conversas", conversas);
            }
        }
        
        response.sendRedirect("Conversar");
        
        /*request.getRequestDispatcher("/WEB-INF/chat.jspx")
                .forward(request, response);*/
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
