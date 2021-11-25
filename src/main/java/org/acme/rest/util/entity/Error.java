package org.acme.rest.util.entity;

public class Error {
    public String code;
    public String status;
    public String title;
    public String detail;
    public Source source;

    public static Error getNotFoundError() {
        Error error = new Error();
        error.code = "404";
        error.status = "Not Found";
        error.detail = "Entry not found";
        error.title = "Not Found";
        return error;
    }

    public static Error getNotCreated() {
        Error error = new Error();
        error.code = "400";
        error.status = "Not Created";
        error.detail = "Entry not created";
        error.title = "Not Created";
        return error;
    }
}
