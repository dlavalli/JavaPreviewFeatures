package com.lavalliere.daniel.projects.ocaocr.java21;

abstract public sealed class Faculty implements Educational
       permits EngineeringFaculty,
               HumanitiesFaculty,
               BusinessFaculty {
}
