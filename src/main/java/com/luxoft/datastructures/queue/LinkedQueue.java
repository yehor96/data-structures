package com.luxoft.datastructures.queue;

import com.luxoft.datastructures.Node;

import java.util.Objects;
import java.util.StringJoiner;

public class LinkedQueue implements Queue {

    private Node head;
    private Node tail;
    private int size;

    @Override
    public void enqueue(Object value) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("Null values are not allowed in the queue");
        }

        Node newNode = new Node(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else if (size == 1) {
            head.prev = newNode;
            newNode.next = head;
            tail = newNode;
        } else {
            Node current = tail;
            tail = newNode;
            current.prev = newNode;
            newNode.next = current;
        }
        size++;
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Unable to dequeue on empty queue");
        }
        Node dequeueNode = head;

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head.prev.next = null;
            head = head.prev;
        }

        size--;
        return dequeueNode.getValue();
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Unable to peek on empty queue");
        }
        return head.getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object value) {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        Node current = head;
        for (int i = 0; i < size; i++) {
            sj.add(String.valueOf(current.getValue()));
            current = current.prev;
        }
        return sj.toString();
    }
}
