<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ include file="navbar.jsp" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/css/form.css'/>">
    <!-- Link to Font Awesome for social media icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <title>SignIn</title>
</head>
<body>
    <div class="container">
        <div class="signin-section">
            <h1>Sign in</h1>
            <form action="/login" method="post">
                <input type="text" placeholder="Enter your userName" id="userName" name="userName" required />
                <input type="email" placeholder="Enter your email" id="email" name="email" required/>
                <input type="password" placeholder="Enter your password" id="password" name="password" required />
                <button type="submit">Signin</button>
                <p>or signin with</p>
                <div class="social-icons">
                    <a href="#" class="fb"><i class="fab fa-facebook-f"></i></a>
                    <a href="#" class="gplus"><i class="fab fa-google-plus-g"></i></a>
                    <a href="#" class="linkedin"><i class="fab fa-linkedin-in"></i></a>
                </div>
            </form>
        </div>
        <div class="welcome-section">
            <h2>Welcome back!</h2>
            <p>Welcome back! We are so happy to have you here. It's great to see you again. We hope you had a safe and enjoyable time away.</p>
            <a href="/signup" class="signup-link">No account yet? Signup.</a>
        </div>
    </div>
</body>
</html>
