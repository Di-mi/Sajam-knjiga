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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DB;

/**
 *
 * @author Dimi
 */
public class PregledServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          Statement stmt = null;
          Connection con = null;
          Korisnik kor = null;
          
          kor = (Korisnik) request.getSession().getAttribute("user");
          
          
          
           try {
            con = DB.getInstance().getConnection();
            if (con == null) {
                request.setAttribute("error", "Pokusajte kasnije");
                request.getRequestDispatcher("glavna.jsp").forward(request, response);
                return;
            }
            stmt = con.createStatement();
            
            
            String upit ="SELECT k.naslov, k.autori, k.izdavac, r.idrezervacije,k.cena,k.opis FROM rezervacija AS r, knjiga AS k WHERE r.idkorisnika='"
                    +kor.getUser() +"' AND r.idknjige=k.id ORDER BY k.naslov;";
            Vector<Knjiga> knjige= new Vector<Knjiga>();
            ResultSet rs = stmt.executeQuery(upit);
            
           if(!rs.next())
           {
               request.setAttribute("error", "nema rezultata za datu preragu");
                request.getRequestDispatcher("glavna.jsp").forward(request, response);
           }
            
           
            do{
                Knjiga knjiga = new Knjiga();               
                knjiga.setId(rs.getInt("idrezervacije"));
                knjiga.setAutori(rs.getString("autori"));
                knjiga.setNaslov(rs.getString("naslov"));
                knjiga.setCena(rs.getDouble("cena"));                
                knjige.add(knjiga);                
                
            } while(rs.next());
            
            
            request.setAttribute("knjige1", knjige);
            
            stmt.close();
         }catch(SQLException sqle)
         {
             request.setAttribute("error", sqle.getLocalizedMessage());           
            request.getRequestDispatcher("pregled.jsp").forward(request, response);
        }finally
        {            
            DB.getInstance().putConnection(con);  
        }
           request.getRequestDispatcher("pregled.jsp").forward(request, response);
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
