package com.luxoft.datastructures.queue;

import java.util.StringJoiner;

public abstract class AbstractQueue implements Queue {

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (Object current : this) {
            stringJoiner.add(String.valueOf(current));
        }
        return stringJoiner.toString();
    }

}
