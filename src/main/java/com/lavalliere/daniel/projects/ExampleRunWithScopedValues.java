package com.lavalliere.daniel.projects;

public class ExampleRunWithScopedValues implements Runnable {
    private String user;

    // The old way of doing it
    // private static ThreadLocal<String> userContext = new ThreadLocal<>();
    private static ScopedValue<String> userContext = ScopedValue.newInstance();

    public ExampleRunWithScopedValues(String user) {
        this.user = user;
    }

    @Override
    public void run() {
        // The old way of doing it
        // userContext.set(user);
        //

        // The difference with a ThreadLocal is that the code executed in the run method and the code of any method that it calls
        // cannot change the value of the user context
        ScopedValue.where(userContext, user).run(() -> {
            System.out.println("Thread: "+Thread.currentThread().getName() + " User: " + userContext.get());
        });
    }
}
