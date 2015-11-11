/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Knjiga;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DB;

/**
 *
 * @author Dimi
 */
public class rezervacijaServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            Knjiga knjiga = null;
        Connection con = null;
        Statement stmt = null;
        
        
        String id = request.getParameter("koja");
          try {
            con=DB.getInstance().getConnection();
            stmt = con.createStatement();
            
            String upit = "SELECT * FROM knjiga WHERE id="+id+";";
            ResultSet rs = stmt.executeQuery(upit);
            knjiga = new Knjiga();
            if(rs.next())
            {
                knjiga.setId(rs.getInt("id"));
                knjiga.setAutori(rs.getString("autori"));
                knjiga.setNaslov(rs.getString("naslov"));
                knjiga.setIzdavac(rs.getString("izdavac"));
                knjiga.setOpis(rs.getString("opis"));
                knjiga.setCena(rs.getDouble("cena"));
                knjiga.setBrStr(rs.getInt("brojstrana"));
            }
            
            stmt.close();
          }catch (SQLException sqle) {
              
              
          }finally
          {
                DB.getInstance().putConnection(con);  
          }
          request.getSession().setAttribute("knjiga", knjiga);
          
          response.sendRedirect("rezervacija.jsp");
        
        
      
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
