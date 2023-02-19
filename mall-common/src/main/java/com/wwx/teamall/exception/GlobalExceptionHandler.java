package com.wwx.teamall.exception;

import com.wwx.teamall.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = BadRequestException.class)
    public Result handle(BadRequestException e) {
        return Result.failed(e.getMessage());
    }
}
