package io.github.shahrivari.kipmap;


import java.io.IOException;
import java.util.*;

public class StrList implements Iterable<String> {
    IntList begins = new IntList();
    BytesBuff bytesBuffer = new BytesBuff();

    public int size() { return begins.size(); }

    public boolean isEmpty() { return begins.size() == 0; }

    public boolean contains(String s) {
        for (String s1 : this)
            if (s.equals(s1))
                return true;
        return false;
    }

    public StrList() {}

    public StrList(Iterable<String> iterable) {
        for (String s : iterable)
            this.add(s);
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            int idx = 0;

            @Override
            public boolean hasNext() { return idx < size(); }

            @Override
            public String next() { return get(idx++); }
        };
    }

    public String[] toArray() {
        String[] array = new String[size()];
        for (int i = 0; i < size(); i++)
            array[i] = get(i);
        return array;
    }


    public void add(String s) {
        begins.append(bytesBuffer.size());
        bytesBuffer.append(s);
    }

    public void clear() {
        begins = new IntList();
        bytesBuffer = new BytesBuff();
    }

    public String get(int idx) {
        Preconditions.checkArg(idx < size(), String.format("Index out of range: %d  size: %d", idx, size()));
        int start = begins.get(idx);
        int end = idx + 1 < size() ? begins.get(idx + 1) : bytesBuffer.size();
        String str = bytesBuffer.getAsString(start, end - start);
        return str;
    }


    public int indexOf(Object o) {
        for (int i = 0; i < size(); i++)
            if (get(i).equals(o))
                return i;
        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = size() - 1; i >= 0; i--)
            if (get(i).equals(o))
                return i;
        return -1;
    }

    public Iterable<String> subList(int i, int i1) {
        StrList subList = new StrList();
        for (int x = i; x < i1; x++)
            subList.add(get(x));
        return subList;
    }

    @Override
    public String toString() { return Arrays.toString(toArray()); }

    public static void main(String[] args) throws IOException {
        int count = 1_000_000;
        for (int iter = 0; iter < 10; iter++) {
            long t0 = System.currentTimeMillis();
            List<String> list1 = new ArrayList<>();
            for (int i = 0; i < count; i++)
                list1.add("salam");
            System.out.printf("ArrayList took: %,d\n", System.currentTimeMillis() - t0);
            t0 = System.currentTimeMillis();
            StrList list2 = new StrList();
            for (int i = 0; i < count; i++)
                list2.add("salam");
            System.out.printf("StrList took: %,d\n", System.currentTimeMillis() - t0);
        }

        System.out.println("DONE");
        System.in.read();
    }
}
