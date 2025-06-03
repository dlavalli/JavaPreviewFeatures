package com.lavalliere.daniel.projects.ocaocr.java21;


import java.util.logging.Logger;

final public class ComputerEngineeringDept extends Department {
    private final Logger logger = Logger.getLogger(ComputerEngineeringDept.class.getName());
    public void compEng() {
        logger.info("Custom computer engineering");
    }

    @Override
    public String toString() {
        return "Computer Engineering";
    }
}
