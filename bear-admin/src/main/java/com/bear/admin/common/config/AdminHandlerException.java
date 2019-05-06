package com.bear.admin.common.config;

import com.bear.admin.common.base.entity.ResultView;
import com.bear.common.core.exception.CaptchaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by mby on 2019/4/30.
 */
@RestControllerAdvice
public class AdminHandlerException {

    @ExceptionHandler(value = CaptchaException.class)
    @ResponseBody
    public Object validationExceptionHandler(CaptchaException exception) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<Object>(ResultView.result(exception.getCode(),exception.getMessage(),null), headers, HttpStatus.OK);
    }


}
