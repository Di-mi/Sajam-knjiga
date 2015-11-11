<%-- 
    Document   : rezervacija
    Created on : Nov 11, 2015, 2:47:56 AM
    Author     : Dimi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rezevacija</title>
    </head>
    <body>
        <h1>Rezervacija</h1>
        <span style="color:red"> ${error}</span>
        <br/>
        <br/>
        <form method="POST" action="rezervisiServlet">
            <h3>${knjiga.naslov}</h3>
             <br/>
             broj strana: ${knjiga.brStr}
             <br/>
             Opis: ${knjiga.opis}
             <br/>
             <select name="kolicina">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                
            </select>
             <br/>
             <br/>
             <input type="submit" value="izvrsi rezervaciju"/>
            
        </form>
             <br/>
             <br/>
             
    <c:choose>
        <c:when  test="${imacena}">
            
        Cena vase naruzbine je: ${cena} 
        </c:when>
        <c:otherwise></c:otherwise>
        
     </c:choose>   
        
   
        
          
            
        
        
    </body>
</html>
