package com.company.infoservice.exception.message;

public record ErrorExtension(String errorMessage, String errorCode) {

    public static final String NOT_FOUND = "not_found";

    public static ErrorExtension createForInvalidQueryParam(String errorMessage) {
        return new ErrorExtension(errorMessage, "invalid_query_param");
    }

    public ErrorExtension append(Object text) {
        return new ErrorExtension(this.errorMessage + " " + text, this.errorCode);
    }
}