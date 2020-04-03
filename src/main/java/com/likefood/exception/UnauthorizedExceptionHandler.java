package com.likefood.exception;

import com.likefood.pojo.common.ErrorMsg;
import net.sf.json.JSONObject;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class UnauthorizedExceptionHandler {

    @ResponseStatus(UNAUTHORIZED)
    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    public String methodSQLIntegrityConstraintViolationException(BadCredentialsException ex) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("status", UNAUTHORIZED.value());
            jo.put("message", ErrorMsg.USER_PASSWORD_ERROR);
        }catch (Exception e) {}

        return jo.toString();
    }


    @ResponseStatus(FORBIDDEN)
    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public String methodSQLIntegrityConstraintViolationException(AccessDeniedException ex) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("status", FORBIDDEN.value());
            jo.put("message", ErrorMsg.NO_PERMISSION);
        }catch (Exception e) {}
        return jo.toString();
    }


}