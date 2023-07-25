package com.company.infoservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static com.company.infoservice.exception.message.ErrorExtension.NOT_FOUND;

public class NotFoundException extends ResponseStatusException {
    public NotFoundException(String message, Object... args) {
        super(HttpStatus.NOT_FOUND, args.length == 0 ? message : message.formatted(args), null, NOT_FOUND, null);
    }
}
