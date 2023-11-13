package org.bebeaubn.model.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.bebeaubn.commons.Utils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        HttpSession session = request.getSession();

        init(session);

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isRequiredFiledCheck = false;

        session.setAttribute("email", email);

        /* 팔수항목 검증 - email, password */
        if(email == null || email.isBlank()){
            session.setAttribute("NotBlank_email", Utils.getMessage("NotBlank.email", "validation"));

         isRequiredFiledCheck = true;
        }
       if(password == null || password.isBlank()){
           session.setAttribute("NotBlank_password", Utils.getMessage("NotBlank.password", "validation"));
           isRequiredFiledCheck = true;
       }
        /*필수항목 검증 - email, password */

        if(isRequiredFiledCheck){
            session.setAttribute("globalError", Utils.getMessage("Login.fail", "validation"));
        }



        response.sendRedirect(request.getContextPath() + "/member/login");

    }

    private void init(HttpSession session){

    }
}
