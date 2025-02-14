package com.itau.desafio;

public class ErrorResponse {
    private final String message;
    private final int status;
    private final String errorCode;
    private final String timestamp;

    public ErrorResponse(String message, int status, String errorCode, String timestamp) {
        this.message = message;
        this.status = status;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
    }
    public String getMessage() {
        return message;
    }
    public int getStatus() {
        return status;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public String getTimestamp() {
        return timestamp;
    }


}
