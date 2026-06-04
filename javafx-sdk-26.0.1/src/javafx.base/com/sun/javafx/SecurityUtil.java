/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.sun.javafx;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Utility class to check for the presence of a security manager.
 */
public class SecurityUtil {

    // Prevent class from being instantiated.
    private SecurityUtil() {}

    /**
     * Check for the presence of a security manager (from an older JDK) and
     * throw UnsupportedOperationException if enabled. Use reflection to avoid
     * a dependency on an API that is deprecated for removal. This method does
     * nothing if the security manager is not enabled or if
     * System::getSecurityManager cannot be invoked.
     *
     * @throws UnsupportedOperationException if the security manager is enabled
     */
    public static void checkSecurityManager() {
        try {
            // Call System.getSecurityManager() using reflection. Throw an
            // UnsupportedOperationException if it returns a non-null object.
            // If we cannot find or invoke getSecurityManager, ignore the error.
            Method meth = System.class.getMethod("getSecurityManager");
            Object sm = meth.invoke(null);
            if (sm != null) {
                throw new UnsupportedOperationException("JavaFX does not support running with the Security Manager");
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            // Ignore the error
        }
    }
}
