// NO PACKAGE ALLOWED
// In Java, a class without a specified package is considered part of the "unnamed package" or "default package".

// All class must be public ELSE cannot load in unnamed class
import com.lavalliere.daniel.projects.ocaocr.java21.*;

import java.util.*;
import java.util.logging.Logger;



private final static Logger logger = Logger.getLogger("University");
// Also possible for logger in an unnamed class
// private final static Logger logger1 = Logger.getAnonymousLogger();

EngineeringFaculty engineeringFaculty = new EngineeringFaculty();
BusinessFaculty businessFaculty = new BusinessFaculty();
HumanitiesFaculty humanitiesFaculty = new HumanitiesFaculty();

SoftwareEngineeringDept softwareEngineeringDept = new SoftwareEngineeringDept();
AccountingDept accountingDept = new AccountingDept();
SocialCareDept socialCareDept = new SocialCareDept();

void seqColl() {
    SequencedCollection<LecturerRecord> lecturerRecords = new ArrayList<>();
    lecturerRecords.addFirst(
        new LecturerRecord("Jane Bloggs", 24, engineeringFaculty, softwareEngineeringDept)
    );
    lecturerRecords.addFirst(
        new LecturerRecord("Dr. Anne Bloggs", 35, engineeringFaculty, softwareEngineeringDept)
    );
    lecturerRecords.addLast(
        new LecturerRecord("Joe Bloggs PhD", 54, engineeringFaculty, softwareEngineeringDept)
    );
    logger.info(lecturerRecords.toString());
    logger.info(lecturerRecords.getFirst().toString());
    logger.info(lecturerRecords.getLast().toString());
    logger.info(lecturerRecords.removeLast().toString());
    logger.info(lecturerRecords.toString());
    for(var lecturer : lecturerRecords) {
        logger.info(lecturer.toString());
    }
    for(var lecturer : lecturerRecords.reversed()) {
        logger.info(lecturer.toString());
    }
}

void seqSet() {
    SequencedSet<LecturerRecord> lecturerRecords = new LinkedHashSet<>();
    LecturerRecord jane = new LecturerRecord("Jane Austin", 24, businessFaculty, accountingDept);
    LecturerRecord charlotte = new LecturerRecord("Dr Charlotte Bronte", 35, businessFaculty, accountingDept);
    LecturerRecord anne = new LecturerRecord("Anne Bronte PhD", 54, businessFaculty, accountingDept);
    lecturerRecords.addFirst(jane);
    lecturerRecords.addFirst(jane);
    lecturerRecords.addFirst(jane);
    lecturerRecords.addFirst(charlotte);
    lecturerRecords.addLast(jane);
    lecturerRecords.addLast(anne);
    logger.info(lecturerRecords.toString());
    logger.info(lecturerRecords.getFirst().toString());
    logger.info(lecturerRecords.getLast().toString());
    logger.info(lecturerRecords.removeFirst().toString());
    logger.info(lecturerRecords.toString());
    for(var lecturer : lecturerRecords) {
        logger.info(lecturer.toString());
    }
    for(var lecturer : lecturerRecords.reversed()) {
        logger.info(lecturer.toString());
    }
}

void seqMap() {
    SequencedMap<LecturerRecord, String> lecturerRecords = new LinkedHashMap<>();
    lecturerRecords.putFirst(new LecturerRecord("Goneril", 55, humanitiesFaculty, socialCareDept),"Eldest");
    lecturerRecords.putFirst(new LecturerRecord("Regan", 50, humanitiesFaculty, socialCareDept),"Middle");
    lecturerRecords.putLast(new LecturerRecord("Cordelia", 45, humanitiesFaculty, socialCareDept),"Youngest");
    lecturerRecords.putLast(new LecturerRecord("King Lear", 88, humanitiesFaculty, socialCareDept),"Father");
    logger.info(lecturerRecords.toString());
    logger.info(lecturerRecords.firstEntry().toString());
    logger.info(lecturerRecords.lastEntry().toString());
    logger.info(lecturerRecords.pollLastEntry().toString());
    logger.info(lecturerRecords.toString());
    for(var lecturer : lecturerRecords.sequencedEntrySet()) {
        logger.info(lecturer.toString());
    }
    for(var lecturer : lecturerRecords.sequencedEntrySet().reversed()) {
        logger.info(lecturer.toString());
    }
}

void recordPatterns(Object obj) {
    logger.info(String.format(
        switch(obj) {
            case LecturerRecord lr when lr.age() >= 64 : { yield """
                    Name: %s
                    Age: %d
                    Faculty: %s
                    Department: %s
                    """.formatted(lr.name(), lr.age(), lr.faculty(), lr.dept());
            }
            case null, default : yield "";
        }
    ));
}

void main(String[] args) {
    seqColl();
    seqSet();
    seqMap();
    recordPatterns(new LecturerRecord("Mike Bloggs", 44, engineeringFaculty, softwareEngineeringDept));
    recordPatterns(new LecturerRecord("Alan Austin", 64, businessFaculty, accountingDept));
    recordPatterns(new LecturerRecord("Lisa Bloggs", 65, humanitiesFaculty, socialCareDept));
}

