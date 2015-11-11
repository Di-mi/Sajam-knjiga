/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Korisnik;
import java.io.IOException;

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
public class loginServlet extends HttpServlet {

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
        
        Connection con = null;
        Statement stmt = null;
       
        String username = request.getParameter("user");
        String pass     = request.getParameter("pass");
        Korisnik kor = new Korisnik();
        
        kor.setUser(username);
        kor.setPass(pass);
        
        request.setAttribute("user", kor);
        int cnt = 0;
        Object obj = request.getSession().getAttribute("count");
        if (obj == null )
        {
             request.getSession().setAttribute("count", 1);
        }
        else 
        {
            cnt = (int) request.getSession().getAttribute("count");
            request.getSession().setAttribute("count", ++cnt);
        }
        
        
        try 
        {
            con = DB.getInstance().getConnection();
            if (con == null) 
            {
                request.setAttribute("poruka", "Problem sa bazom - Pokusajte kasnije");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            stmt = con.createStatement();
                            
            String query ="SELECT * FROM korisnik k WHERE k.korisnickoime='"+username+"';";
            
            ResultSet rs = stmt.executeQuery(query);
            
            if(rs.next())
            {
                if(!(rs.getString("lozinka")).equals(pass))
                {
                       if(cnt == 4)
                       {
                           query = "UPDATE korisnik k SET blokiran=1 WHERE k.korisnickoime='"+username+"';";
                           stmt.executeUpdate(query);
                       }
                    
                    request.setAttribute("poruka", "Losa loznika");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                
                
                String ime     = rs.getString("ime");
                String prezime = rs.getString("prezime");
                int god        = rs.getInt("godinarodj");
                
                boolean stud   = rs.getBoolean("student");
                boolean blok   = rs.getBoolean("blokiran");
                
                if(blok)
                {
                    request.setAttribute("poruka", "Ovaj user je blokiran");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
               
                kor.setIme(ime);
                kor.setPrezime(prezime);
                kor.setGodRodj(god);
                kor.setStudent(stud);
                kor.setBlokiran(blok);
                
            }
            else 
            {                
                request.setAttribute("poruka", "pogresan Username, pokusajte opet");
                //brojac
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
            
            stmt.close();
        }catch(SQLException sqle)
        {
            request.setAttribute("poruka", sqle.getLocalizedMessage());           
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }finally
        {            
            DB.getInstance().putConnection(con);  
        }
        
        //request.getRequestDispatcher("glavna.jsp").forward(request, response);
        
        
        request.getSession().setAttribute("user", kor);
        request.getSession().setAttribute("logged", true);
        response.sendRedirect("glavna.jsp");
       
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
