package com.bear.common.core.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by mby on 2019/4/17.
 */
@Slf4j
public class FileException extends BaseException {

    public FileException(Integer code, String message) {
        super(code,message);
        log.error("FileException code={},message={}",code,message);
    }
}
