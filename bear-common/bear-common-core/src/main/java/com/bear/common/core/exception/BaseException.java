package com.bear.common.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by mby on 2019/4/17.
 */
@Getter
@Setter
public class BaseException extends RuntimeException {

    private Integer code;

    private String message;

    public BaseException() {
    }


    public BaseException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}
