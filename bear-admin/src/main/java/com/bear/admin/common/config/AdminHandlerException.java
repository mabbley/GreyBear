package com.bear.admin.common.config;

import com.bear.admin.common.base.entity.ResultView;
import com.bear.common.core.exception.CaptchaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mby on 2019/4/30.
 */
@Slf4j
@RestControllerAdvice
public class AdminHandlerException {


    private ResponseEntity<Object> toResponse(Integer code,String message,HttpStatus httpStatus){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        log.error("errorExceptionHandler code:{},mmessage:{},httpStatus:{}",code,message,httpStatus);
        return new ResponseEntity<>(ResultView.result(code,message,null), headers, httpStatus);
    }

    @ExceptionHandler(value = CaptchaException.class)
    @ResponseBody
    public Object validationExceptionHandler(HttpServletRequest request,CaptchaException exception) throws Exception {
        return toResponse(exception.getCode(),exception.getMessage(), HttpStatus.OK);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object MethodArgumentNotValidHandler(HttpServletRequest request, MethodArgumentNotValidException exception) throws Exception {
        return toResponse(-200,exception.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Object HttpMessageNotReadableHandler(HttpServletRequest request, HttpMessageNotReadableException exception) throws Exception {
        return toResponse(-200,exception.getMessage(), HttpStatus.OK);
    }


    @ExceptionHandler(value = AccessDeniedException.class)
    public Object AccessDeniedExceptionHandler(HttpServletRequest request,AccessDeniedException exception) throws Exception {
        return toResponse(-200,exception.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public Object NoHandlerFoundExceptionHandler(HttpServletRequest request,NoHandlerFoundException exception) throws Exception {
        return toResponse(-200,exception.getMessage(), HttpStatus.OK);
    }


    @ExceptionHandler(value = AuthenticationException.class)
    public Object AuthenticationExceptionHandler(HttpServletRequest request,AuthenticationException e) {
        return toResponse(-200,e.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(value = HttpClientErrorException.Unauthorized.class)
    public Object AuthenticationExceptionHandler(HttpServletRequest request,HttpClientErrorException.Unauthorized e) {
        return toResponse(-200,e.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    public Object DuplicateKeyExceptionHandler(HttpServletRequest request,DuplicateKeyException e) throws Exception {
        return toResponse(-200,e.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public Object BadCredentialsExceptionHandler(HttpServletRequest request,BadCredentialsException e) throws Exception {
        return toResponse(-200,e.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(value = Exception.class)
    public Object ExceptionHandler(Exception e) throws Exception {
        return toResponse(-200,e.getMessage(), HttpStatus.OK);
    }

}
