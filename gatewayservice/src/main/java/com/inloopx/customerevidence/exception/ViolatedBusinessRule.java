package com.inloopx.customerevidence.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ViolatedBusinessRule extends RuntimeException {

    public ViolatedBusinessRule(String message) {
        super(message);
    }

    public ViolatedBusinessRule() {
    }
}
