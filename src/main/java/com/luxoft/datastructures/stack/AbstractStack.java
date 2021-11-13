package com.luxoft.datastructures.stack;

import java.util.StringJoiner;

public abstract class AbstractStack implements Stack {

    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (Object current : this) {
            stringJoiner.add(String.valueOf(current));
        }
        return stringJoiner.toString();
    }
}
