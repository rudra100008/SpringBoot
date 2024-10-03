<<<<<<< HEAD
package com.blogrestapi.Security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//when user tries to access the protected resource then this send the respone to user as "Access Denied"
// if user is not loggedIn
@Component
public class JWTAuthencticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
       response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Access Denied");
    }
    
}
=======
package com.blogrestapi.Security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//when user tries to access the protected resource then this send the respone to user as "Access Denied"
// if user is not loggedIn
@Component
public class JWTAuthencticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
       response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Access Denied");
    }
    
}
>>>>>>> 4e1d3c33e347acf859fd8818dbc58cef4c7a60f3
