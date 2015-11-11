<%-- 
    Document   : glavna
    Created on : Nov 10, 2015, 10:24:23 PM
    Author     : Dimi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Glavna</title>
    </head>
    <c:choose>
    <c:when  test="${logged == true}">
    <body>
        <h1>Glavna</h1>
        <span style="color:red">${error}</span>
        <form method="POST" action="knjigeServlet" name="forma1">
            Naslov: 
            <input type="text" name="naslov"/><br/>
            Autor:
            <input type="text" name="autor"/><br/>
            Izdavac:
            <input type="text" name="izdavac"/><br/>
            
            <input type="submit" name="naslov" value="Pretrazi"/>
            
        </form>
        <div style="float:right; margin-left:500px; display:block ">
            <a href="PregledServlet"><h2>Pregleddaj rezervacije</h2></a>
          
            
            
        </div>
        <div>
            <c:choose>
                <c:when test="${rez}">
                    <h2> Knjige:</h2>
                    <form name="knjige" method="POST" action="rezervacijaServlet">
                    <c:forEach items="${knjige}" var="i" varStatus="status">                        
                        <input type="radio" name="koja" value="${i.id}">${i.naslov}<br>
                    </c:forEach>   
                        <br/><br/>
                        <input type="submit" value="rezervisi"/>
                    </form>
                    
                </c:when>
                <c:otherwise>
                    <br/>
                    Unesite pretragu                  
                    
                </c:otherwise>
                
            </c:choose>
            
        </div>
        
        
    </body>
    </c:when>
    <c:otherwise>
        <c:redirect url="index.jsp"></c:redirect>        
    </c:otherwise>
    </c:choose>
</html>
