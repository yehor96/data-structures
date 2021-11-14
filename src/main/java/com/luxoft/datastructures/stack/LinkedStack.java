package com.luxoft.datastructures.stack;

import com.luxoft.datastructures.Node;

import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedStack<T> extends AbstractStack<T> {

    private Node<T> head;

    @Override
    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        if (size != 0) {
            newNode.next = head;
        }
        head = newNode;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Unable to pop on empty stack");
        }

        Node<T> popped = head;
        if (size != 1) {
            head = head.next;
        } else {
            head = null;
        }
        size--;
        return popped.getValue();
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Unable to peek on empty stack");
        }
        return head.getValue();
    }

    @Override
    public boolean contains(T value) {
        if (Objects.isNull(value)) {
            return false;
        }

        Node<T> current = head;
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
    public java.util.Iterator<T> iterator() {
        return new Iterator();
    }

    private void removeAt(int index) {
        Node<T> current = head;
        if (size == 1) {
            clear();
        } else if (index == 0) {
            head = head.next;
        } else {
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            if (index == size - 1) {
                current.next = null;
            } else {
                Node<T> oldNode = current.next;
                current.next = oldNode.next;
            }
        }
        size--;
    }

    private class Iterator implements java.util.Iterator<T> {

        private Node<T> nextElement = head;
        private int counter = -1;
        private boolean isRemovable = false;

        @Override
        public boolean hasNext() {
            return counter < size - 1;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                String errorMessage = String.format("Actual size of %d is reached", size);
                throw new NoSuchElementException(errorMessage);
            }

            T value = nextElement.getValue();
            nextElement = nextElement.next;
            counter++;
            isRemovable = true;
            return value;
        }

        @Override
        public void remove() {
            if (!isRemovable) {
                throw new IllegalStateException("Call next() before removing any elements");
            }
            removeAt(counter--);
            isRemovable = false;
        }
    }
}
