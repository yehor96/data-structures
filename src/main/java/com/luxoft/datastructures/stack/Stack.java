package com.luxoft.datastructures.stack;

// ADT -> Abstract Data Type
// LIFO -> last-in-fist-out
public interface Stack extends Iterable {
    void push(Object value);

    Object pop();

    Object peek();

    boolean contains(Object value);

    int size();

    boolean isEmpty();

    void clear();
}