/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facebook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Theriault
 */
@WebServlet(name = "MyMovies", urlPatterns = {"/MyMovies"})
public class MyMovies extends HttpServlet {

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

        JDBC db = new JDBC();

        String fb_id = (String) request.getSession().getAttribute("id");

        String genr = "";
        String sort = "";

        String sortParam = (String) request.getParameter("sort");
        String genrParam = (String) request.getParameter("genre");
        
        if (genrParam != null) {
            request.setAttribute("genr", genrParam);
        }

        if (sortParam != null) {
            if (sortParam.equals("az") || sortParam.equals("za") || sortParam.equals("y09") || sortParam.equals("y90")) {
                sort = sortParam;
            }
        }        
        
        List<Movie> movies = db.getInventory(fb_id, sort);
        
        List<String> gnrs = new ArrayList<>();
        for ( Movie mov : movies ) {
            for ( String genre1 : mov.getGenre() ){
                boolean dupli = false;
                for (String gnrlst : gnrs) {
                    if ( genre1.equals(gnrlst) ) {
                        dupli = true;
                    }
                }
                if (dupli == false) {
                    gnrs.add(genre1);
                }
            }
        }

        request.getSession().setAttribute("movies", movies);

        request.setAttribute("movies", movies);
        
        request.setAttribute("genres", gnrs);

        request.getRequestDispatcher("fvmymovies.jsp").forward(request, response);
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
