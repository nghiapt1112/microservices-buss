package com.nghia.libraries.email.infrustructure.mail;

import com.nghia.libraries.email.infrustructure.exception.DomainException;

public class EmailException extends DomainException {
    private static final int DOMAIN_CODE = 7;

    protected EmailException(int errorCode, String message) {
        super(DOMAIN_CODE, errorCode, message);
    }

    protected EmailException(int errorCode, String message, Throwable e) {
        super(DOMAIN_CODE, errorCode, message, e);
    }
}
