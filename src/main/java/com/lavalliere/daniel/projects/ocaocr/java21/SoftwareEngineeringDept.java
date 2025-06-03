package com.lavalliere.daniel.projects.ocaocr.java21;

import java.util.logging.Logger;

final class SoftwareEngineeringDept extends Department {
    private final Logger logger = Logger.getLogger(SoftwareEngineeringDept.class.getName());

    public void swEng() {
        logger.info("Custom software engineering");
    }

    @Override
    public String toString() {
        return "Software Engineering";
    }
}
