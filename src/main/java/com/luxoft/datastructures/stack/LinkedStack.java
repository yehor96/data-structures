package com.luxoft.datastructures.stack;

import com.luxoft.datastructures.Node;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedStack extends AbstractStack {

    private Node head;

    @Override
    public void push(Object value) {
        Node newNode = new Node(value);
        if (size != 0) {
            newNode.next = head;
        }
        head = newNode;
        size++;
    }

    @Override
    public Object pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Unable to pop on empty stack");
        }

        Node popped = head;
        if (size != 1) {
            head = head.next;
        } else {
            head = null;
        }
        size--;
        return popped.getValue();
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Unable to peek on empty stack");
        }
        return head.getValue();
    }

    @Override
    public boolean contains(Object value) {
        if (Objects.isNull(value)) {
            return false;
        }

        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.getValue().equals(value)) {
                return true;
            }
            current = current.next;
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
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        Node current = head;
        for (int i = 0; i < size; i++) {
            stringJoiner.add(String.valueOf(current.getValue()));
            current = current.next;
        }
        return stringJoiner.toString();
    }

    @Override
    public java.util.Iterator iterator() {
        return new Iterator();
    }

    private class Iterator implements java.util.Iterator {

        private int counter = 0;
        Node current = head;

        @Override
        public boolean hasNext() {
            return counter < size;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                String errorMessage = String.format("Actual size of %d is reached", size);
                throw new NoSuchElementException(errorMessage);
            }

            Node next = current;
            current = current.next;
            counter++;
            return next.getValue();
        }
    }
}
