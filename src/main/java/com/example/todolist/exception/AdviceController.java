package com.example.todolist.exception;

import com.example.todolist.dto.response.CommonResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(BaseBusinessLogicException.class)
    public CommonResponseDto<Void> handleBaseException(BaseBusinessLogicException ex){
        return new CommonResponseDto<Void>()
                .setMessage(ex.getMessage())
                .setStatus(200);
    }
}
