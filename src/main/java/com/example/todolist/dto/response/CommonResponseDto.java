package com.example.todolist.dto.response;


public class CommonResponseDto<T> {
    private T data;
    private Integer status;
    private String message;

    public T getData() {
        return data;
    }

    public CommonResponseDto<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public CommonResponseDto<T> setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
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
