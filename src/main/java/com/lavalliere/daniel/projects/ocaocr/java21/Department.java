package com.lavalliere.daniel.projects.ocaocr.java21;

abstract public sealed class Department
       permits ComputerEngineeringDept,
               SoftwareEngineeringDept,
               AccountingDept,
               SocialCareDept {
}
