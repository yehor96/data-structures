package com.luxoft.datastructures.queue;

// FIFO
//
public interface Queue {
    void enqueue(Object value);

    Object dequeue();

    Object peek();

    int size();

    boolean isEmpty();

    boolean contains(Object value);

    void clear();

    // [A, B, C] if size = 3
    String toString();
}
