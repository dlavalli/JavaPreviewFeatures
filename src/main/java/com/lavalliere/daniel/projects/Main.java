package com.lavalliere.daniel.projects;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;
import com.lavalliere.daniel.projects.utils.AnnotationsScanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AnnotationsScanner.scan(
            IsDemoable.class,
            Main.class.getPackageName(),
            (bean) -> {
                if (bean instanceof Demoable demoable) {
                    demoable.demo();
                }
            });
    }
}