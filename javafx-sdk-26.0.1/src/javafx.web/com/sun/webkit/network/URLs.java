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

package com.sun.webkit.network;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Map;

/**
 * A collection of static methods for URL creation.
 */
public final class URLs {

    /**
     * The mapping between WebPane-specific protocol names and their
     * respective handlers.
     */
    private static final Map<String,URLStreamHandler> HANDLER_MAP = Map.of(
        "about", new com.sun.webkit.network.about.Handler(),
        "data", new com.sun.webkit.network.data.Handler());

    /**
     * The private default constructor. Ensures non-instantiability.
     */
    private URLs() {
        throw new AssertionError();
    }


    /**
     * Creates a {@code URL} object from the {@code String} representation.
     * This method is equivalent to the {@link URL#URL(String)} constructor
     * with the additional support for WebPane-specific protocol handlers.
     * @param spec the {@code String} to parse as a {@code URL}.
     * @throws MalformedURLException if the string specifies an unknown
     *         protocol.
     */
    public static URL newURL(String spec) throws MalformedURLException {
        return newURL(null, spec);
    }

    /**
     * Creates a URL by parsing the given spec within a specified context.
     * This method is equivalent to the {@link URL#URL(URL,String)}
     * constructor with the additional support for WebPane-specific protocol
     * handlers.
     * @param context the context in which to parse the specification.
     * @param spec the {@code String} to parse as a {@code URL}.
     * @throws MalformedURLException if no protocol is specified, or an
     *         unknown protocol is found.
     */
    public static URL newURL(final URL context, final String spec)
        throws MalformedURLException
    {
        try {
            // Try the standard protocol handler selection procedure
            return new URL(context, spec);
        } catch (MalformedURLException ex) {

            // Try WebPane-specific protocol handler, if any
            int colonPosition = spec.indexOf(':');
            final URLStreamHandler handler = (colonPosition != -1) ?
                HANDLER_MAP.get(spec.substring(0, colonPosition).toLowerCase()) :
                null;

            if (handler == null) throw ex;

            return new URL(context, spec, handler);
        }
    }
}
