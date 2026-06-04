/*
 * Copyright (c) 2011, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.webkit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

public abstract class Utilities {

    private static Utilities instance;

    public static synchronized void setUtilities(Utilities util) {
        instance = util;
    }

    public static synchronized Utilities getUtilities() {
        return instance;
    }

    protected abstract Pasteboard createPasteboard();
    protected abstract PopupMenu createPopupMenu();
    protected abstract ContextMenu createContextMenu();

    // List of Class methods to allow
    private static final Set<String> CLASS_METHODS_ALLOW_LIST = Set.of(
        "getCanonicalName",
        "getEnumConstants",
        "getFields",
        "getMethods",
        "getName",
        "getPackageName",
        "getSimpleName",
        "getSuperclass",
        "getTypeName",
        "getTypeParameters",
        "isAssignableFrom",
        "isArray",
        "isEnum",
        "isInstance",
        "isInterface",
        "isLocalClass",
        "isMemberClass",
        "isPrimitive",
        "isSynthetic",
        "toGenericString",
        "toString"
    );

    // List of classes to reject
    private static final Set<String> CLASSES_REJECT_LIST = Set.of(
        "java.lang.ClassLoader",
        "java.lang.Module",
        "java.lang.Runtime",
        "java.lang.System"
    );

    // List of packages to reject
    private static final List<String> PACKAGES_REJECT_LIST = List.of(
        "java.lang.invoke",
        "java.lang.module",
        "java.lang.reflect",
        "java.security",
        "sun.misc"
    );

    private static Object fwkInvokeWithContext(final Method method,
                                               final Object instance,
                                               final Object[] args,
                                               Object dummyAcc) // UNUSED
            throws Throwable {

        final Class<?> clazz = method.getDeclaringClass();
        if (clazz.equals(java.lang.Class.class)) {
            // check list of allowed Class methods
            if (!CLASS_METHODS_ALLOW_LIST.contains(method.getName())) {
                throw new UnsupportedOperationException("invocation not supported");
            }
        } else {
            // check list of rejected class names
            final String className = clazz.getName();
            if (CLASSES_REJECT_LIST.contains(className)) {
                throw new UnsupportedOperationException("invocation not supported");
            }
            // check list of rejected packages
            PACKAGES_REJECT_LIST.forEach(packageName -> {
                if (className.startsWith(packageName + ".")) {
                    throw new UnsupportedOperationException("invocation not supported");
                }
            });
        }

        try {
            return MethodHelper.invoke(method, instance, args);
        } catch (InvocationTargetException ex) {
            Throwable cause = ex.getCause();
            throw cause != null ? cause : ex;
        }
    }
}
