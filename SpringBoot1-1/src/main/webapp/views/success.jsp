<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
 <%@ include file="navbar.jsp" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success</title>
<link rel="stylesheet" href="<c:url value="/css/table.css" />" >
</head>
<body>
<h1>Sign-in success</h1>
<table class="styled-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>UserName</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="datas" items="${data}" >
            <tr>
                <td>${datas.id}</td>
                <td>${datas.userName}</td>
                <td>${datas.email}</td>
                <td>
                   <a href="<c:url value='/delete/${datas.id}' />" >
                     <button type="button" >Delete</button>
                    </a>
                        
                    
                </td>
            </tr>
        </c:forEach> 
        </tbody>
    </table>
</body>
</html>