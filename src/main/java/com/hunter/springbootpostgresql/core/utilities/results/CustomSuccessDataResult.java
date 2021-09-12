package com.hunter.springbootpostgresql.core.utilities.results;

public class CustomSuccessDataResult<T> extends CustomDataResult<T>{

    public CustomSuccessDataResult(T data) {
        super(data);
    }

    public CustomSuccessDataResult() {
        super();
    }

    @Override
    public T getData() {
        return super.getData();
    }

    @Override
    public void setData(T data) {
        super.setData(data);
    }

    public CustomSuccessDataResult(String message, T data) {
        super(true, message, data);
    }

    @Override
    public boolean isSuccess() {
        return super.isSuccess();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void setSuccess(boolean success) {
        super.setSuccess(success);
    }

    @Override
    public void setMessage(String message) {
        super.setMessage(message);
    }
}
