package com.order.handler;

import com.order.constant.MessageConstant;
import com.order.exception.BaseException;
import com.order.result.Result;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        // Duplicate entry 'zhangsan' for key 'idx_username'
        log.error("异常信息：{}", ex.getMessage());
        if(ex.getMessage().contains("Duplicate entry")){
            String username = ex.getMessage().split(" ")[2];
            return Result.error(username + MessageConstant.ALREADY_EXIST);
        }else{
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

}
