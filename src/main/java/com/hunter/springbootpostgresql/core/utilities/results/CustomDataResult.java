package com.hunter.springbootpostgresql.core.utilities.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomDataResult<T> extends CustomResult{
    private T data;

    public CustomDataResult(boolean success, String message, T data) {
        super(success, message);
        this.data = data;
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
