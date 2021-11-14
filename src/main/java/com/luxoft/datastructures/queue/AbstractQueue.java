package com.luxoft.datastructures.queue;

import java.util.StringJoiner;

public abstract class AbstractQueue<T> implements Queue<T> {

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (T current : this) {
            stringJoiner.add(String.valueOf(current));
        }
        return stringJoiner.toString();
    }

}
