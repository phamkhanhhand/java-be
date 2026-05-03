package com.phamkhanhhand.kap.intercepter;


public class InvalidException extends RuntimeException {
    private final Object data;
    private String mess;

    public InvalidException(String mess, Object data) {
        super(mess); // message cố định
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
