package io.github.shahrivari.kipmap;

import java.nio.charset.Charset;
import java.util.Arrays;

public class BytesBuff {
    private static final int DEFAULT_SIZE = 16;
    private static final float DEFAULT_GROW_FACTOR = 1.1f;
    private static final Charset UTF8 = Charset.forName("UTF8");

    byte[] buffer;
    private int occupied = 0;
    private float growFactor;

    public BytesBuff(int capacity, float growFactor) {
        buffer = new byte[capacity];
        this.growFactor = growFactor;
    }

    public BytesBuff() { this(DEFAULT_SIZE, DEFAULT_GROW_FACTOR); }

    public BytesBuff(int capacity) { this(capacity, DEFAULT_GROW_FACTOR); }

    public int size() { return occupied; }

    public int append(byte[] bytes) {
        if (remainingCap() < bytes.length) {
            int newCap = (int) (buffer.length * growFactor) + bytes.length;
            grow(newCap);
        }
        System.arraycopy(bytes, 0, buffer, occupied, bytes.length);
        occupied += bytes.length;
        return bytes.length;
    }

    public int append(String s) {
        byte[] bytes = s.getBytes(UTF8);
        return append(bytes);
    }

    public String getAsString(int start, int len) { return new String(buffer, start, len, UTF8);}


    public int totalCap() {return buffer.length;}

    public int remainingCap() { return buffer.length - occupied;}

    public void grow(int capacity) {
        Preconditions.checkArg(totalCap() < capacity, "New capacity must be larger than current.");
        byte[] newBuf = new byte[capacity];
        System.arraycopy(buffer, 0, newBuf, 0, occupied);
        buffer = newBuf;
    }

    public byte[] toBytes() { return Arrays.copyOf(buffer, occupied); }

    public byte[] getBuffer() { return buffer; }
}
