package org.bebeaubn.commons.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.bebeaubn.commons.Utils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class commonInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        init(request);

        return true;
    }

    private void init(HttpServletRequest request) {
        HttpSession session = request.getSession();

        /* PC, mobile 수동 처리 변경 S */
        String device = request.getParameter("device");
        System.out.println("device:" + device);
        if (device != null && !device.isBlank()) {
            session.setAttribute("device", device.toLowerCase().equals("mobile") ? "mobile" : "pc");
        }

        /* PC, MOBILE 수동처리 변경 E */

        /* 로그인 페이지 아닐경우 로그인 유효성 검사 세션 삭제 처리 */

        String URI = request.getRequestURI();
        if(URI.indexOf("/member/login") == -1) {
            Utils.loginInit(session);

        }

        /*성공시 다른페이지에 접속했을때 다른 정보를 보여주면 안되기 때문에 session값 지우게 되게 보여주는것
             Utils.loginInit(session); 이렇게 util쪽 정보 지워주기

         */

    }

}
