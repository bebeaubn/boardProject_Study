package org.bebeaubn.commons.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class JSONData<T> {
    private boolean success = true;
    private HttpStatus status = HttpStatus.OK;
    private T data;
    private String message;

}
