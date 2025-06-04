package com.transparent.fleet.ResponceModel;

public class LoginData {
    private String access_token;
    private String expires_at;
    private String token_type;
    private String status;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccess_token() {
        return this.access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_at() {
        return this.expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }

    public String getToken_type() {
        return this.token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
