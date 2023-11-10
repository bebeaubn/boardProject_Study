package org.bebeaubn.commons.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class CommonException extends RuntimeException{
    private HttpStatus status;

    public CommonException(String message){
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);  //500
    }

    public CommonException(String message, HttpSecurity status){
        super(message);
        this.status = status;

    }

    public HttpStatus getStatus(){
        return status;
    }

}
