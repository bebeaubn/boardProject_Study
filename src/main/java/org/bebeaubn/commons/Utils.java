package org.bebeaubn.commons;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;import java.util.ResourceBundle;


    @Component
    @RequiredArgsConstructor
    public class Utils {
        private static ResourceBundle validationsBundle;
        private static ResourceBundle errorsBundle;

        private final HttpServletRequest request;

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
                //요청헤더 User-Agen
            boolean isMobile = request.getHeader("User-Agent").matches
                    (".*(iPhone|iPod|iPad|BlackBerry|Android|Windows CE|LG|MOT|SAMSUNG|SonyEricsson).*");

                return isMobile;

        }

        public String tpl(String tplPath){
            return String.format("%s/" + tplPath, isMobile()?"mobile":"front");

        }
    }

