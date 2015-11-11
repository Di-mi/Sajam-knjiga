/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Knjiga;
import beans.Korisnik;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DB;

/**
 *
 * @author Dimi
 */
public class rezervisiServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
     
        
        Connection con = null;
        Statement stmt = null;
        
        int kolicina = Integer.parseInt(request.getParameter("kolicina"));
        Korisnik kor = null;
        Knjiga knjiga = null;
        kor = (Korisnik) request.getSession().getAttribute("user");
        knjiga = (Knjiga) request.getSession().getAttribute("knjiga"); 
        
        try 
        {
            con=DB.getInstance().getConnection();
            
            stmt = con.createStatement();
            Date datum = new Date(Calendar.getInstance().getTimeInMillis());
            String upit = "INSERT INTO rezervacija (idkorisnika,idknjige,brojknjiga,datumrez) values('"+kor.getUser()+"',"
                    +knjiga.getId()+","+kolicina+",'"+datum+"');";
            stmt.executeUpdate(upit);
            
            stmt.close();
        }catch (SQLException sqle) {
            request.setAttribute("message", sqle.getLocalizedMessage());
              request.getRequestDispatcher("rezervacija.jsp").forward(request, response);
            
        }finally
        {
            DB.getInstance().putConnection(con);
        }
        double cena = knjiga.getCena() * kolicina;
        
        if(kor.isStudent())
        {
            cena = cena * (95./100);
        }
        
        request.setAttribute("imacena", true);
        request.setAttribute("cena", cena);
        request.getRequestDispatcher("rezervacija.jsp").forward(request, response);
        
        
        
        
        
        
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
