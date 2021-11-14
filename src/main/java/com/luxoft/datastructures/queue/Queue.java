package com.luxoft.datastructures.queue;

// FIFO
//
public interface Queue<T> extends Iterable<T> {
    void enqueue(T value);

    T dequeue();

    T peek();

    int size();

    boolean isEmpty();

    boolean contains(T value);

    void clear();

    // [A, B, C] if size = 3
    String toString();
}
