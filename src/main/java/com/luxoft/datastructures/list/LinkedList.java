package com.luxoft.datastructures.list;

import com.luxoft.datastructures.Node;

import java.util.NoSuchElementException;

public class LinkedList<T> extends AbstractList<T> {

    private Node<T> head;
    private Node<T> tail;

    @Override
    public void add(T value, int index) {
        verifyIndexAdd(index);
        verifyNotNull(value);

        Node<T> current = head;
        Node<T> newNode = new Node<>(value);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else if (index == size) {
            current = tail;
            tail = newNode;
            current.next = tail;
            tail.prev = current;
        } else if (index == 0) {
            head = newNode;
            head.next = current;
            current.prev = head;
        } else {
            current = getNodeAt(index);

            current.prev.next = newNode;
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev = newNode;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        verifyIndex(index);
        Node<T> oldNode = head;
        if (size == 1) {
            clear();
        } else if (index == 0) {
            head.next.prev = null;
            head = head.next;
        } else if (index == size - 1) {
            oldNode = tail;
            tail.prev.next = null;
            tail = tail.prev;
        } else {
            oldNode = getNodeAt(index);

            oldNode.prev.next = oldNode.next;
            oldNode.next.prev = oldNode.prev;
        }
        size--;
        return oldNode.getValue();
    }

    @Override
    public T get(int index) {
        verifyIndex(index);
        Node<T> current = getNodeAt(index);
        return current.getValue();
    }

    @Override
    public T set(T value, int index) {
        verifyIndex(index);
        verifyNotNull(value);

        T oldValue = null;
        if (size == 1) {
            Node<T> newNode = new Node<>(value);
            tail = newNode;
            head = newNode;
        } else if (index == 0) {
            oldValue = head.getValue();
            head.setValue(value);
        } else if (index == size - 1) {
            oldValue = tail.getValue();
            tail.setValue(value);
        } else {
            Node<T> oldNode = getNodeAt(index);
            oldValue = oldNode.getValue();
            oldNode.setValue(value);
        }
        return oldValue;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int indexOf(T value) {
        if (value == null) {
            return -1;
        }

        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.getValue().equals(value)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        if (value == null) {
            return -1;
        }

        Node<T> current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (current.getValue().equals(value)) {
                return i;
            }
            current = current.prev;
        }
        return -1;
    }

    private Node<T> getNodeAt(int index) {
        Node<T> current;
        if (size / 2 <= index) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = 0; i < size - index - 1; i++) {
                current = current.prev;
            }
        }
        return current;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new Iterator();
    }

    private class Iterator implements java.util.Iterator<T> {

        private boolean isRemovable = false;
        private int counter = -1;
        private Node<T> nextElement = head;

        @Override
        public boolean hasNext() {
            return counter < size - 1;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                String errorMessage = String.format(NO_SUCH_ELEMENT_ERROR_MESSAGE, size, counter + 1);
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
            LinkedList.this.remove(counter--);
            isRemovable = false;
        }
    }
}
