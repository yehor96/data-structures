package com.luxoft.datastructures.queue;

import com.luxoft.datastructures.Node;

import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedQueue<T> extends AbstractQueue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void enqueue(T value) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("Null values are not allowed in the queue");
        }

        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else if (size == 1) {
            head.prev = newNode;
            newNode.next = head;
            tail = newNode;
        } else {
            Node<T> current = tail;
            tail = newNode;
            current.prev = newNode;
            newNode.next = current;
        }
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Unable to dequeue on empty queue");
        }
        Node<T> dequeueNode = head;

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
    public T peek() {
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
    public boolean contains(T value) {
        Node<T> current = head;
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
    public java.util.Iterator<T> iterator() {
        return new Iterator();
    }

    private void removeAt(int index) {
        if (size == 1) {
            clear();
        } else if (index == 0) {
            head.prev.next = null;
            head = head.prev;
        } else if (index == size - 1) {
            tail.next.prev = null;
            tail = tail.next;
        } else {
            Node<T> oldNode = getNodeAt(index);

            oldNode.next.prev = oldNode.prev;
            oldNode.prev.next = oldNode.next;
        }
        size--;
    }

    private Node<T> getNodeAt(int index) {
        Node<T> current;
        if (size / 2 <= index) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.prev;
            }
        } else {
            current = tail;
            for (int i = 0; i < size - index - 1; i++) {
                current = current.next;
            }
        }
        return current;
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
            nextElement = nextElement.prev;
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
