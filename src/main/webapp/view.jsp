<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Saisie d'un taux de remise</title>
    </head>
    
    <body>
        
        <h1>Edition des taux de remise</h1>
        
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
        
        <table style = 'border : 1px solid black'>
            
            <tr>
            <th>Code</th>
            <th>Taux</th>
            <th>Action</th>
            </tr>
            
            <c:forEach var="dce" items="${dcelist}">
                <tr>
                <td>${dce.code}</td>
                <td>${dce.taux}</td>
                <td><a href="?action=DELETE&amp;code=${dce.code}">Delete</a></td>
                </tr>
            </c:forEach>
                    
        </table>
        
    </body>
    
</html>
