package com.lavalliere.daniel.projects;
import com.lavalliere.daniel.projects.ExampleRunWithScopedValues;

public class MainExampleWithScopedValues {

    public static void main(String[] args) {
        var exampleRunnable1 = new ExampleRunWithScopedValues("User 1");
        var thread1 = new Thread(exampleRunnable1);
        var exampleRunnable2 = new ExampleRunWithScopedValues("User 2");
        var thread2 = new Thread(exampleRunnable2);
        thread1.start();
        thread2.start();
    }
}
