/*
 * Copyright (c) 2013, 2017, Oracle and/or its affiliates. All rights reserved.
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

/**
 * <p>This package provides means for loading and displaying Web content. Its
 *     functionality is implemented by two core classes:
 *
 * <p>{@link javafx.scene.web.WebEngine} is a non-visual component capable of
 *     loading Web pages, creating DOM objects for them, and running scripts
 *     inside pages.
 *
 * <p>{@link javafx.scene.web.WebView} is a {@link javafx.scene.Node} that
 *     presents a Web page managed by a {@code WebEngine}. Each {@code WebView}
 *     has a {@code WebEngine} associated with it. This association is
 *     established at the time {@code WebView} is instantiated, and cannot be
 *     changed later.
 *
 * <p>Both {@code WebEngine} and {@code WebView} should be created and
 *     manipulated on FX User thread.
 *
 * <p>The code snippet below shows a typical usage scenario:
 *
 * <pre>{@code
 *     WebView webView = new WebView();
 *     WebEngine webEngine = webView.getEngine();
 *     webEngine.load("http://javafx.com");
 *     // add webView to the scene
 * }</pre>
 */
package javafx.scene.web;
