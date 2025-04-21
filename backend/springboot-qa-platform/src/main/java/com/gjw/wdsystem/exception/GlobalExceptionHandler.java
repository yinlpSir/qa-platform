package com.gjw.wdsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     *  处理非法参数异常 (例如接收到了不合法或不允许的参数)
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException (IllegalArgumentException e){
        return this.buildErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLException (SQLException e){
        if(e instanceof SQLIntegrityConstraintViolationException subEx){
            // 处理SQL完整性约束违反异常
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST,subEx.getMessage());
        }
        return this.buildErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    /**
     * Customize build error response
     * @param httpStatusCode
     * @param message
     * @return
     */
    protected ResponseEntity<Object> buildErrorResponse(HttpStatusCode httpStatusCode, String message){
        var result = new HashMap<String,String>();
        result.put("message",message);
        return ResponseEntity.status(httpStatusCode).body(result);
    }
}
