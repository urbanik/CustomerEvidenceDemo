package com.inloopx.userservice.exception;

public class ErrorResponse {

    private String errorMessage;
    private String errorDescription;

    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorResponse(String errorMessage, String errorDescription) {
        this.errorMessage = errorMessage;
        this.errorDescription = errorDescription;
    }

    public ErrorResponse() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
