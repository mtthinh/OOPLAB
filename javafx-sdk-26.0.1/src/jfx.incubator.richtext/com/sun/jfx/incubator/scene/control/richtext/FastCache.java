/*
 * Copyright (c) 2023, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jfx.incubator.scene.control.richtext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * A simple cache implementation which provides a cheap invalidation via {@link #clear()}
 * and a cheap random eviction via {@link #evict()}.
 * This object must be accessed from the FX application thread, although it does not check.
 */
public class FastCache<T> {
    private static record Entry<V>(int index, V cell) { }

    private int size;
    private final Entry<T>[] linear;
    private final HashMap<Integer, T> data;
    private final static Random random = new Random();

    public FastCache(int capacity) {
        linear = new Entry[capacity];
        data = new HashMap<>(capacity);
    }

    public T get(int row) {
        return data.get(row);
    }

    /**
     * Adds a new cell to the cache. When the cache is full, this method evicts a
     * random cell from the cache first. NOTE: this method does not check whether
     * another cell for the given row is present, so this call must be preceded by a
     * {@link #get(int)}.
     */
    public void add(int index, T cell) {
        int ix;
        if (size >= capacity()) {
            ix = evict();
        } else {
            ix = size++;
        }

        data.put(index, cell);
        linear[ix] = new Entry<>(index, cell);
    }

    /** returns an index in the linear array of the cell that has been evicted */
    protected int evict() {
        int ix = random.nextInt(size);
        // does not clear the slot because it will get overwritten by the caller
        Entry<T> en = linear[ix];
        int index = en.index();
        data.remove(index);
        return ix;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return linear.length;
    }

    public void clear() {
        size = 0;
        Arrays.fill(linear, null);
        data.clear();
    }
}
