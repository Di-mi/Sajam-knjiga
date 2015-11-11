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
public class knjigeServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String autor = request.getParameter("autor");
        String naslov = request.getParameter("naslov");
        String izdavac = request.getParameter("izdavac");
        
        Statement stmt = null;
        Connection con = null;
        
        
        
        
        if(autor.isEmpty() && naslov.isEmpty() && izdavac.isEmpty())
        {
            request.setAttribute("error", "niste uneli parametre pretrage");           
            request.getRequestDispatcher("glavna.jsp").forward(request, response);
        }
        
        
        
        String upit = "SELECT * FROM knjiga k WHERE ";
        
        int tmp = 0; 
        if(!autor.isEmpty())
        {
            upit+="autori LIKE '%"+autor+"%' ";
            tmp++;
        }
        if(!naslov.isEmpty())
        {
            if(tmp > 0)
            {
                upit+="AND ";
            }
            upit+="naslov LIKE '%"+naslov+"%' ";
            tmp++;
        }
         if(!izdavac.isEmpty())
        {
            if(tmp > 0)
            {
                upit+="AND ";
            }
            upit+="izdavac LIKE '%"+izdavac+"%' ";
            tmp++;
        }
         upit+=";";
        
         try {
            con = DB.getInstance().getConnection();
            if (con == null) {
                request.setAttribute("error", "Pokusajte kasnije");
                request.getRequestDispatcher("glavna.jsp").forward(request, response);
                return;
            }
            stmt = con.createStatement();
            Vector<Knjiga> knjige= new Vector<Knjiga>();
            ResultSet rs = stmt.executeQuery(upit);
            
           if(!rs.next())
           {
               request.setAttribute("error", "nema rezultata za datu preragu");
                request.getRequestDispatcher("glavna.jsp").forward(request, response);
           }
            
           
            do{
                Knjiga knjiga = new Knjiga();
                
                knjiga.setId(rs.getInt("id"));
                knjiga.setAutori(rs.getString("autori"));
                knjiga.setNaslov(rs.getString("naslov"));
                knjiga.setCena(rs.getDouble("cena"));
                
                knjige.add(knjiga);                
                
            } while(rs.next());
            
            
            request.setAttribute("knjige", knjige);
            
            stmt.close();
         }catch(SQLException sqle)
         {
             request.setAttribute("error", sqle.getLocalizedMessage());           
            request.getRequestDispatcher("glavna.jsp").forward(request, response);
        }finally
        {            
            DB.getInstance().putConnection(con);  
        }
         request.setAttribute("rez",true);
         request.getRequestDispatcher("glavna.jsp").forward(request, response);
        
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
