/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.css.parser;

import java.util.List;
import java.util.function.Predicate;

public final class TokenStream {

    private final List<Token> source;
    private final int size;
    private int currentIndex = -1;
    private Token currentItem;

    public TokenStream(List<Token> source) {
        this.source = source;
        this.size = source.size();
    }

    /**
     * Returns the number of tokens in the stream.
     *
     * @return the number of tokens in the stream
     */
    public int size() {
        return size;
    }

    /**
     * Returns the last token that was consumed from the stream.
     *
     * @return the token, or {@code null} if no token was consumed
     */
    public Token current() {
        return currentItem;
    }

    /**
     * Consumes and returns the next token in the token stream.
     *
     * @return the token, or {@code null} if there are no tokens left in the stream
     */
    public Token consume() {
        if (currentIndex < size - 1) {
            return currentItem = source.get(++currentIndex);
        }

        if (currentIndex < size) {
            currentIndex++;
        }

        return null;
    }

    /**
     * Consumes and returns the next token in the token stream if it satisfies the specified predicate.
     *
     * @param predicate the predicate
     * @return the token, or {@code null} if there are no tokens left in the stream,
     *         or if the next token didn't satisfy the predicate
     */
    public Token consumeIf(Predicate<Token> predicate) {
        Token nextToken = consume();
        if (nextToken != null && predicate.test(nextToken)) {
            return nextToken;
        }

        reconsume();
        return null;
    }

    /**
     * Pushes the current token back onto the front of the token stream, so that the next
     * time a token is read from the stream, the current token will be reconsumed.
     */
    public void reconsume() {
        if (currentIndex > 0) {
            currentItem = source.get(--currentIndex);
        } else {
            currentItem = null;
            currentIndex = -1;
        }
    }

    /**
     * Returns the next token in the stream, but does not consume it.
     *
     * @return the token, or {@code null} if there are no tokens left in the stream
     */
    public Token peek() {
        return currentIndex < size - 1 ? source.get(currentIndex + 1) : null;
    }

    /**
     * Returns whether the next tokens in the stream satisfy the specified predicates.
     *
     * @param predicates the predicates
     * @return {@code true} if the tokens satisfy the predicates
     */
    @SafeVarargs
    public final boolean matches(Predicate<Token>... predicates) {
        int index = currentIndex;

        try {
            for (Predicate<Token> predicate : predicates) {
                if (consumeIf(predicate) == null) {
                    return false;
                }
            }

            return true;
        } finally {
            currentIndex = index;
            currentItem = index >= 0 ? source.get(index) : null;
        }
    }
}
