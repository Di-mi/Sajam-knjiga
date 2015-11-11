<%-- 
    Document   : pregled
    Created on : Nov 11, 2015, 3:11:14 PM
    Author     : Dimi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pregled</title>
    </head>
    <body>
        <h1>Pregled</h1>
         <span style="color:red"> ${error}</span>
        <br/>
          <table>
              
              <tr><th>Naslov</th><th>Autor</th><th>Cena</th> <th>Otkazi</th></tr>
          <c:forEach items="${knjige1}" var="i">
              <tr>
                  <td>${i.naslov}</td>
                  <td>${i.autori}</td>                  
                  <td>${i.cena}</td>
                  <td><a href="deleteServlet?id=${i.id}">Otkazati</a></td>
              </tr>
          </c:forEach>
          </table>
    </body>
</html>
