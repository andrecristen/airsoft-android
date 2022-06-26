package com.ddm.airsoftorganize.response;

public class DefaultResponse {

    protected String success;
    protected String message;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "success='" + success + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
