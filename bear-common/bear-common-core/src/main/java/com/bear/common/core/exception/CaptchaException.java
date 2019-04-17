package com.bear.common.core.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by mby on 2019/4/17.
 */
@Slf4j
public class CaptchaException extends BaseException {

    public CaptchaException(Integer code, String message) {
        super(code,message);
        log.error("FileException code={},message={}",code,message);
    }
}
