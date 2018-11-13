<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Saisie d'un taux de remise</title>
    </head>
    
    <body>
        
        <h1>Edition des taux de remise</h1>
        
        <div>
        <form method="GET">
            
            Code :
            <input name="code" size="1" maxlength="1" pattern="[A-Z]{1}+" 
                   title="Une lettre en MAJUSCULES">
            <br>
            
            Taux :
            <input name="taux" type="number" step="0.01" 
                   min="0.0" max="99.99" size="5">
            <br>
            
            <input type="hidden" name="action" value="ADD">
            <input type="submit" value="Ajouter">
            
        </form>
        </div>
        
        <div><h4>${message}</h4></div>

        <div>
        <table border = 2>
            
            <tr>
            <th>Code</th>
            <th>Taux</th>
            <th>Action</th>
            </tr>
            
            <c:forEach var="dce" items="${dcelist}">
                <tr>
                <td>${dce.code}</td>
                <td>${dce.taux}</td>
                <td><a href="?action=DELETE&code=${dce.code}">Delete</a></td>
                </tr>
            </c:forEach>
                    
        </table>
        </div>      

    </body>
    
</html>
