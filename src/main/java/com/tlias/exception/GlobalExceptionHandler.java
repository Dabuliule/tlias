package com.tlias.exception;

import com.tlias.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        log.error("An exception occurred: ", e);
        return Result.error("对不起,操作失败,请联系管理员");
    }
}
