package org.bebeaubn.commons.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class commonInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        init(request);

        return true;
    }

    private void init(HttpServletRequest request){
        HttpSession session = request.getSession();

        /* PC, mobile 수동 처리 변경 S */
        String device = request.getParameter("device");
        System.out.println("device:" + device);
        if (device != null && !device.isBlank()) {
            session.setAttribute("device",device.toLowerCase().equals("mobile")?"mobile" : "pc");
        }

        /* PC, MOBILE 수동처리 변경 E */
    }
}
