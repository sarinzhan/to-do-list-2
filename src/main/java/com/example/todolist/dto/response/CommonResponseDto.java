package com.example.todolist.dto.response;

import lombok.Data;

@Data
public class CommonResponseDto<T> {
    private T data;
    private Integer status;
    private String message;

    public CommonResponseDto<T> setData(T data) {
        this.data = data;
        return this;
    }

    public CommonResponseDto<T> setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public CommonResponseDto<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public CommonResponseDto<T> setOk(){
        this.message = "OK";
        this.status = 200;
        return this;
    }
}
