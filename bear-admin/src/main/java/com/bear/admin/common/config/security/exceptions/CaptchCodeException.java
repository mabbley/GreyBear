package com.bear.admin.common.config.security.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by mby on 2019/5/8.
 */
@Getter
@Setter
public class CaptchCodeException extends AuthenticationException {

    private Integer code;

    private String message;

    public CaptchCodeException(String msg) {
        super(msg);
    }

    public CaptchCodeException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
