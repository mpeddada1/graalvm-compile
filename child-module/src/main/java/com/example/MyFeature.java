package com.example;

import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeReflection;

public class MyFeature implements Feature {

    private static final String SOME_EXAMPLE_CLASS =
            "com.example.SomeExample";

    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        loadRandomClasses(access);
    }


    private void loadRandomClasses(BeforeAnalysisAccess access) {
        Class<?> someExampleClass = access.findClassByName(SOME_EXAMPLE_CLASS);

        // Calling some reflection code to check how provided/compile scope for graal-sdk impacts the code.
        if (someExampleClass!=null) {
            access.registerSubtypeReachabilityHandler(
                    (duringAccess, subtype) -> registerClassForReflection(access, subtype.getName()),
                    someExampleClass);
        }
    }

    /**
     * Registers an entire class for reflection use.
     */
    public static void registerClassForReflection(FeatureAccess access, String name) {
        Class<?> clazz = access.findClassByName(name);
        if (clazz!=null) {
            RuntimeReflection.register(clazz);
            RuntimeReflection.register(clazz.getDeclaredConstructors());
            RuntimeReflection.register(clazz.getDeclaredFields());
            RuntimeReflection.register(clazz.getDeclaredMethods());
        } else {
            System.out.println("Some Error");
        }
    }
}

