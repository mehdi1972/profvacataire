<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Liste des Modules</title>
</head>
<body>
    <h1>Liste des Modules</h1>
    <a href="moduleServlet?action=ajout">Ajouter Module</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nom du Module</th>
            <th>Abbréviation du Module</th>
            <th>Action:</th>
        </tr>
        <c:forEach items="${modules}" var="module">
            <tr>
                <td>${module.id_module}</td>
                <td>${module.nom_module}</td>
                <td>${module.abrev_module}</td>
                <td>
                    <a href="moduleServlet?id=${module.id_module}&action=edit">Éditer</a>
                    <a href="moduleServlet?id=${module.id_module}&action=supprimer" onclick="return confirm('Voulez-vous vraiment supprimer ce module ?')">Supprimer</a>
                    <a href="moduleServlet?id=${module.id_module}&action=consulter">Consulter</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
