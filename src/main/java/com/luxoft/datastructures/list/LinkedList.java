package com.luxoft.datastructures.list;

import com.luxoft.datastructures.Node;

public class LinkedList extends AbstractList {

    private Node head;
    private Node tail;

    @Override
    public void add(Object value) {
        verifyNotNull(value);

        Node newNode = new Node(value);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            Node current = tail;
            tail = newNode;
            current.next = tail;
            tail.prev = current;
        }
        size++;
    }

    @Override
    public void add(Object value, int index) {
        verifyIndexWithinArrayBounds(index, size);
        if (index == size) {
            add(value);
            return;
        }
        verifyNotNull(value);

        Node current = head;
        Node newNode = new Node(value);
        if (index == 0) {
            head = newNode;
            head.next = current;
            current.prev = head;
        } else {
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.prev.next = newNode;
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev = newNode;
        }
        size++;
    }

    @Override
    public Object remove(int index) {
        verifyIndexWithinArrayBounds(index, size - 1);
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
            for (int i = 0; i < index; i++) {
                oldNode = oldNode.next;
            }
            oldNode.prev.next = oldNode.next;
            oldNode.next.prev = oldNode.prev;
        }
        size--;
        return oldNode.getValue();
    }

    @Override
    public Object get(int index) {
        verifyIndexWithinArrayBounds(index, size - 1);
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.getValue();
    }

    @Override
    public Object set(Object value, int index) {
        verifyIndexWithinArrayBounds(index, size - 1);
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
            for (int i = 0; i < index; i++) {
                oldNode = oldNode.next;
            }
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        for (int i = 0; i < size; i++) {
            sb.append(current.getValue());
            if (i != size - 1) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
