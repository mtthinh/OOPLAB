/*
 * Copyright (c) 2011, 2023, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.application;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.application.Application;

public abstract class HostServicesDelegate {

    public static HostServicesDelegate getInstance(final Application app) {
        return StandaloneHostService.getInstance(app);
    }

    protected HostServicesDelegate() {
    }

    public abstract String getCodeBase();

    public abstract String getDocumentBase();

    public abstract void showDocument(String uri);

    // StandaloneHostService implementation
    private static class StandaloneHostService extends HostServicesDelegate {

        private static HostServicesDelegate instance = null;

        private Class appClass = null;

        public static HostServicesDelegate getInstance(Application app) {
            synchronized (StandaloneHostService.class) {
                if (instance == null) {
                    instance = new StandaloneHostService(app);
                }
                return instance;
            }
        }

        private StandaloneHostService(Application app) {
             appClass = app.getClass();
        }

        @Override
        public String getCodeBase() {
            // If the application was launched in standalone mode, this method
            // returns the directory containing the application jar file.
            // If the application is not packaged in a jar file, this method
            // returns the empty string.
            String theClassFile = appClass.getName();
            int idx = theClassFile.lastIndexOf(".");
            if (idx >= 0) {
                // Strip off package name prefix in class name if exists
                // getResoruce will automatically add in package name during
                // lookup; see Class.getResource javadoc for more details
                theClassFile = theClassFile.substring(idx + 1);
            }
            theClassFile = theClassFile + ".class";

            String classUrlString = appClass.getResource(theClassFile).toString();
            if (!classUrlString.startsWith("jar:file:") ||
                    classUrlString.indexOf("!") == -1) {
                return "";
            }
            // Strip out the "jar:" and everything after and including the "!"
            String urlString = classUrlString.substring(4,
                    classUrlString.lastIndexOf("!"));
            File jarFile = null;
            try {
                jarFile = new File(new URI(urlString).getPath());
            } catch (Exception e) {
                // should not happen
            }
            if (jarFile != null) {
                String codebase = jarFile.getParent();
                if (codebase != null) {
                    return toURIString(codebase);
                }
            }

            return "";
        }

        private String toURIString(String filePath) {
            try {
                return new File(filePath).toURI().toString();
            } catch (Exception e) {
                // should not happen
                // dump stack for debug purpose
                e.printStackTrace();
            }
            return "";
        }

        @Override public String getDocumentBase() {
            // If the application was launched in standalone mode,
            // this method returns the URI of the current directory.
            return toURIString(System.getProperty("user.dir"));
        }

        @Override
        public void showDocument(final String uri) {
            URI link = null;
            // Normalize link
            try {
                link = new URI(uri);
            } catch (URISyntaxException ignore) {}

            if (link == null || link.getScheme() == null) {
                // Try to create URI from file
                File file = new File(uri);
                if (file.exists()) {
                    link = file.toURI();
                } else {
                    System.err.println("ERROR: unable to open: " + uri);
                    return;
                }
            }

            com.sun.glass.ui.Application.GetApplication().showDocument(link.toString());
        }
    }
}
