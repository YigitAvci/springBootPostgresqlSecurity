package com.hunter.springbootpostgresql.core.utilities.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CustomSuccessResult extends CustomResult{

    public CustomSuccessResult(String message) {
        super(true, message);
    }

    public CustomSuccessResult() {
        super();
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
