package com.qt.question.util;

public class Result {

    private Object data;
    private String message;
    private boolean result;
    private int status;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Result(Object data, String message, boolean result, int status) {
        this.data = data;
        this.message = message;
        this.result = result;
        this.status = status;
    }

    private Result(){

    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
