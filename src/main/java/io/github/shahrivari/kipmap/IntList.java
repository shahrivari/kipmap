package io.github.shahrivari.kipmap;

import java.nio.charset.Charset;
import java.util.Arrays;

public class IntList {
    private static final int DEFAULT_SIZE = 16;
    private static final float DEFAULT_GROW_FACTOR = 1.1f;

    int[] ints;
    private int occupied = 0;
    private float growFactor;

    public IntList(int capacity, float growFactor) {
        ints = new int[capacity];
        this.growFactor = growFactor;
    }

    public IntList() { this(DEFAULT_SIZE, DEFAULT_GROW_FACTOR); }

    public IntList(int capacity) { this(capacity, DEFAULT_GROW_FACTOR); }

    public void append(int i) {
        if (remainingCap() == 0) {
            int newCap = (int) (ints.length * growFactor) + 1;
            grow(newCap);
        }
        ints[occupied++] = i;
    }

    public int size() { return occupied; }

    public int totalCap() {return ints.length; }

    public int remainingCap() { return ints.length - occupied; }

    public void grow(int capacity) {
        Preconditions.checkArg(totalCap() < capacity, "New capacity must be larger than current.");
        int[] newBuf = new int[capacity];
        System.arraycopy(ints, 0, newBuf, 0, occupied);
        ints = newBuf;
    }

    public int get(int idx) {
        Preconditions.checkArg(idx < size(), String.format("Index out of range: %d  size: %d", idx, size()));
        return ints[idx];
    }
}
