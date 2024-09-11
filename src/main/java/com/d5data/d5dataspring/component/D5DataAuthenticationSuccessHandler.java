package com.d5data.d5dataspring.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class D5DataAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.setContentType("text/html;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(
                "username: "+authentication.getPrincipal()
                        +"， password: "+authentication.getCredentials()
                        +"， grantedAuthority: "+authentication.getAuthorities()
        );
    }


}
