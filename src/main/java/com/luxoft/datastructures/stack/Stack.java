package com.luxoft.datastructures.stack;

// ADT -> Abstract Data Type
// LIFO -> last-in-fist-out
public interface Stack<T> extends Iterable<T> {
    void push(T value);

    T pop();

    T peek();

    boolean contains(T value);

    int size();

    boolean isEmpty();

    void clear();
}