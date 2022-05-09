package com.example;

import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeReflection;

public class MyFeature implements Feature {

    private static final String GOOGLE_API_CLIENT_CLASS =
            "com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient";

    private static final String GOOGLE_API_CLIENT_REQUEST_CLASS =
            "com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest";

    private static final String GENERIC_JSON_CLASS = "com.google.api.client.json.GenericJson";

    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        loadRandomClasses(access);
    }


    private void loadRandomClasses(BeforeAnalysisAccess access) {
        Class<?> googleApiClientClass = access.findClassByName(GOOGLE_API_CLIENT_CLASS);

        // Calling some reflection code to check how provided/compile scope for graal-sdk impacts the code.
        if (googleApiClientClass!=null) {
            access.registerSubtypeReachabilityHandler(
                    (duringAccess, subtype) -> registerClassForReflection(access, subtype.getName()),
                    googleApiClientClass);
            access.registerSubtypeReachabilityHandler(
                    (duringAccess, subtype) -> registerClassForReflection(access, subtype.getName()),
                    access.findClassByName(GOOGLE_API_CLIENT_REQUEST_CLASS));

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

