// Book.aidl
package com.ruaho.studyapp;

// Declare any non-default types here with import statements

interface Book {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
