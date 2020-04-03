package com.likefood.exception;

import com.likefood.pojo.common.ErrorMsg;
import net.sf.json.JSONObject;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class DataIntegrityViolationExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String methodSQLIntegrityConstraintViolationException(DataIntegrityViolationException ex) {
        if (ex.getCause().getCause().toString().contains("Duplicate entry")) {
            return processFieldErrors("username", ErrorMsg.USERNAME_ALREADY);
        } else {
            JSONObject jo = new JSONObject();
            try {
                jo.put("message", ex.getMessage());
            }catch (Exception e) {}
            return jo.toString();
        }
    }

    private String processFieldErrors(String fieldName, String fieldValue) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("status", BAD_REQUEST.value());
            jo.put("message", ErrorMsg.VALID_ERROR);
            JSONObject fields = new JSONObject();
            jo.put("fields", fields);
            fields.put(fieldName, fieldValue);
        } catch (Exception e){}
        return jo.toString();
    }

}