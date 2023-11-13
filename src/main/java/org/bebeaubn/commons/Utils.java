package org.bebeaubn.commons;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;import java.util.ResourceBundle;


    @Component
    @RequiredArgsConstructor
    public class Utils {
        private static ResourceBundle validationsBundle;
        private static ResourceBundle errorsBundle;

        private final HttpServletRequest request;

        private final HttpSession session;

        static {
            validationsBundle = ResourceBundle.getBundle("messages.validations");
            errorsBundle = ResourceBundle.getBundle("messages.errors");
        }

        public static String getMessage(String code, String bundleType) {
            bundleType = Objects.requireNonNullElse(bundleType, "validation");
            ResourceBundle bundle = bundleType.equals("error") ? errorsBundle : validationsBundle;
            try {
                return bundle.getString(code);
            } catch (Exception e) {
                return null;
            }
        }


        public boolean isMobile() {
            String device = (String) session.getAttribute("device");
            if(device != null){
                return device.equals("mobile");
            }

            boolean isMobile = request.getHeader("User-Agent").matches
                    (".*(iPhone|iPod|iPad|BlackBerry|Android|Windows CE|LG|MOT|SAMSUNG|SonyEricsson).*");


                return isMobile;

        }

        public String tpl(String tplPath){
            return String.format("%s/" + tplPath, isMobile()?"mobile":"front");

        }
        public static void loginInit(HttpSession session){
            session.removeAttribute("email");
            session.removeAttribute("NotBlank_email");
            session.removeAttribute("NotBlank_password");
            session.removeAttribute("globalError");

        }


    }

