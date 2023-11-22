package org.bebeaubn.commons;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;import java.util.ResourceBundle;


// Utils는 편의기능이 담겨져 있는 클래스


    @Component
    @RequiredArgsConstructor
    public class Utils {
        private static ResourceBundle validationsBundle;
        private static ResourceBundle errorsBundle;

        private static ResourceBundle commonsBundle;

        private final HttpServletRequest request;

        private final HttpSession session;

        static {
            validationsBundle = ResourceBundle.getBundle("messages.validations");
            errorsBundle = ResourceBundle.getBundle("messages.errors");
            commonsBundle = ResourceBundle.getBundle("message.commons");
        }

        public static String getMessage(String code, String bundleType) {
            bundleType = Objects.requireNonNullElse(bundleType, "validation");
            ResourceBundle bundle = null;
            if(bundleType.equals("common")){
                bundle =commonsBundle;
            } else if (bundleType.equals("error")){
                bundle = errorsBundle;
            }else {
                bundle = validationsBundle;
            }

            try {
                return bundle.getString(code);
            } catch (Exception e) {
                return null;
            }
        }

        public static int getNumber(int page, int i) {
        }


        public boolean isMobile() {
            String device = (String) session.getAttribute("device");
            if(device != null){
                return device.equals("mobile");
            }

            boolean isMobile = request.getHeader("User-Agent").matches
                    (".*(iPhone|iPod|iPad|BlackBerry|Android|Windows CE|LG|MOT|SAMSUNG|SonyEricsson).*");

             // 접속한 정보가 어디인지 알려주는것 ㅍ


                return isMobile;

        }

        public String tpl(String tplPath){
            return String.format("%s/" + tplPath, isMobile()?"mobile":"front");

            //장비에 따라서 다르게 가능해진다.

        }
        public static void loginInit(HttpSession session){
            session.removeAttribute("email");
            session.removeAttribute("NotBlank_email");
            session.removeAttribute("NotBlank_password");
            session.removeAttribute("globalError");


            //로그인 구현을 위해 만든 부분

        }


    }

