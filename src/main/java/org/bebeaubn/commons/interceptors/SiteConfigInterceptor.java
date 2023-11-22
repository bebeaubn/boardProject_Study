package org.bebeaubn.commons.interceptors;


import jakarta.servlet.http.HttpServletResponse;
  //여기 부분 수정 해줘야함  config에 파일 생성해줘야 한다
import org.bebeaubn.commons.exceptions.AlertBackException;
import org.bebeaubn.commons.exceptions.AlertException;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 예외를 자바스크립트 처리하는 공통 인터페이스
 * 여기 부분 다시 봐줘야 한다
 */
public interface ScriptExceptionProcess {
    @ExceptionHandler(AlertException.class)
    default String scriptHandler(AlertException e, Model model, HttpServletResponse response) {

        response.setStatus(e.getStatus().value());
        String script = String.format("alert('%s');", e.getMessage());
        if (e instanceof AlertBackException) {
            script += "history.back();";
        }

        model.addAttribute("script", script);

        return "commons/_execute_script";
    }
}