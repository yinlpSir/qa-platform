package com.gjw.wdsystem.exception;

import lombok.Data;

/**
 * 自定义系统异常
 */
@Data
public class WdSystemException extends RuntimeException{
    private int errorCode; // 错误码

    public WdSystemException(){

    }

    public WdSystemException(String message){
        super(message);
    }

    public WdSystemException(int errorCode,String message){
        super(message);
        this.errorCode = errorCode;
    }
}
