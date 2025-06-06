package com.lavalliere.daniel.projects.ocaocr.java21;

import java.util.logging.Logger;

final public class HumanitiesFaculty extends Faculty {
    private final Logger logger = Logger.getLogger(HumanitiesFaculty.class.getName());

    public void humanities() {
        logger.info("We teach social care, European studies etc...");
    }

    @Override
    public String toString() {
        return "Humanities";
    }
}
