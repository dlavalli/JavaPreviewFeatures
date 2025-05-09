package com.lavalliere.daniel.projects;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.util.Optional;

import static java.util.FormatProcessor.FMT;

@IsDemoable
public class NewJava21Features implements Demoable {

    public record Product(String id, String name, double price) {}

    // String templates - REMOVED in Java 23
    public NewJava21Features showStringTemplates() {
        var product = new Product("bogusId", "bogusName", 2.0);

        // STR is statically imported implicitly into every Java compilation unit.
        String string4 = STR."Products with id \{ product.id() } is \{ product.name() } and has price $\{ product.price()  }";

        // Unlike STR, FMT/RAW must be statically imported explicitly.
        String string5 = FMT."Products with id \{ product.id() } is \{ product.name() } and has price $%.2f\{ product.price()  }";
        System.out.println(string4);
        System.out.println(string5);

        System.out.println();
        return this;
    }

    public NewJava21Features testForeignFunctionAndMemory() {

        // Arena is a new interface and represents a region of heap memory
        // and used in try-with-resources to make sure it is closed when done with its resources

        // Arena Returns a new confined arena. Segments allocated with the confined arena can be accessed by the thread
        // that created the arena, the arena's owner thread.
        // Memory segments allocated by the returned arena are zero-initialized.

        // NOTE : could use var BUT wanted to see the types returned
        try(Arena arena = Arena.ofConfined()) {

            // A linker is something that lets you call functions from outside the jvm
            final Linker linker = Linker.nativeLinker();

            // Gets the address of a symbol in a library
            final SymbolLookup symbolLookup = linker.defaultLookup();

            final Optional<MemorySegment> memorySegment = symbolLookup.find("strlen");
            if (memorySegment.isPresent()) {

                final FunctionDescriptor functionDescriptor = FunctionDescriptor.of(
                    ValueLayout.JAVA_LONG, // How to represent the return type of the function, in java,
                    ValueLayout.ADDRESS// The argument layouts for the function
                );

                // Can be used to call a function
                final MethodHandle methodHandle = linker.downcallHandle(memorySegment.get(), functionDescriptor);

                // Allocate memory and copy the string that will be used as argument to the function to be called
                final MemorySegment segmentAllocator = arena.allocateUtf8String("Hello World");

                final long result = (long)methodHandle.invokeExact(segmentAllocator);

                System.out.println("strlen(\"Hello World\") is: " + result);
            } else {
                System.out.println("Unable to find symbol: strlen");
            }

            System.out.println();
        } catch (Throwable ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println();
        return this;
    }

    public NewJava21Features testScopedVallues() {
        System.out.println();
        System.out.println();
        return this;
    }

    @Override()
    public void demo() {
        this.showStringTemplates()
            .testForeignFunctionAndMemory()
        ;
    }
}
