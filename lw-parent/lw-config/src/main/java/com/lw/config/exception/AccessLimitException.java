package com.lw.config.exception;

import com.lw.dto.AjaxResult;

/**
 * @author wangyanqiang
 * @title: AccessLimitException
 * @date 2020/5/2615:52
 */
public class AccessLimitException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    protected final String message;
    protected final int status;

    public AccessLimitException(String message, int status)
    {
        this.message = message;
        this.status = status;
    }

    public AccessLimitException(String message, Throwable e, int status)
    {
        super(message, e);
        this.message = message;
        this.status = status;
    }


    @Override
    public String getMessage()
    {
        return message;
    }
    public Integer getStatus(){return status;}
}
