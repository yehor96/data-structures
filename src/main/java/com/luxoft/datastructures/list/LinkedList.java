package com.luxoft.datastructures.list;

import com.luxoft.datastructures.Node;

import java.util.NoSuchElementException;

public class LinkedList extends AbstractList {

    private Node head;
    private Node tail;

    @Override
    public void add(Object value, int index) {
        verifyIndexAdd(index);
        verifyNotNull(value);

        Node current = head;
        Node newNode = new Node(value);
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
    public Object remove(int index) {
        verifyIndex(index);
        Node oldNode = head;
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
    public Object get(int index) {
        verifyIndex(index);
        Node current = getNodeAt(index);
        return current.getValue();
    }

    @Override
    public Object set(Object value, int index) {
        verifyIndex(index);
        verifyNotNull(value);

        Node newNode = new Node(value);
        Node oldNode = head;
        if (size == 1) {
            tail = newNode;
            head = newNode;
        } else if (index == 0) {
            head.next.prev = newNode;
            newNode.next = head.next;
            head = newNode;
        } else if (index == size - 1) {
            oldNode = tail;
            tail.prev.next = newNode;
            newNode.prev = tail.prev;
            tail = newNode;
        } else {
            oldNode = getNodeAt(index);

            oldNode.prev.next = newNode;
            oldNode.next.prev = newNode;
            newNode.prev = oldNode.prev;
            newNode.next = oldNode.next;
        }
        return oldNode;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int indexOf(Object value) {
        if (value == null) {
            return -1;
        }

        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.getValue().equals(value)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        if (value == null) {
            return -1;
        }

        Node current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (current.getValue().equals(value)) {
                return i;
            }
            current = current.prev;
        }
        return -1;
    }

    private Node getNodeAt(int index) {
        Node current;
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
    public java.util.Iterator iterator() {
        return new Iterator();
    }

    private class Iterator implements java.util.Iterator {

        private int counter = 0;
        private Node pointer = head;

        @Override
        public boolean hasNext() {
            return counter < size;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                String errorMessage = String.format(NO_SUCH_ELEMENT_ERROR_MESSAGE, size, counter);
                throw new NoSuchElementException(errorMessage);
            }

            Object value = pointer.getValue();
            pointer = pointer.next;
            counter++;
            return value;
        }

        @Override
        public void remove() {
            LinkedList.this.remove(--counter);
        }
    }
}
