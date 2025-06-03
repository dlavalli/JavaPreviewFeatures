package com.lavalliere.daniel.projects.ocaocr.java21;

import java.util.logging.Logger;

final public class AccountingDept extends Department {
    private final Logger logger = Logger.getLogger(AccountingDept.class.getName());

    public void accounting() {
        logger.info("Custom accounting");
    }

    @Override
    public String toString() {
        return "Accounting";
    }
}
