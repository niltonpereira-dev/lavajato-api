package br.com.niltonpereira.lavajato_api.exception;

import lombok.Builder;

@Builder
public class ErroResponse {

    private String message;
    private int status;

    public ErroResponse() {
    }

    public ErroResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
