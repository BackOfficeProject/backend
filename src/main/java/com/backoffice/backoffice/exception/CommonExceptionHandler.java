package com.backoffice.backoffice.exception;

public class CommonExceptionHandler extends RuntimeException{
    private final ErrorCode errorCode;

    public CommonExceptionHandler(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
