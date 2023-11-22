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

        Utils.loginInit(session);


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


        /*템프릿에 출력할려면 커멘드 객체로 자동적으로 가능하지만 request가 있어서 안됨 session으로 하는데 실패했을때 다시 로그인
        페이지로 이동하기 위해 요청범위가 달라져 데이터 손실때문에 session에 넣어서 사용
        검증실패 등등 프론트에 출력가능하게 코드를 짠거다.
        상단에 초기화 하고 다시 돌아가는 형식으로 보여준다


*/
    }


}
