package com.luxoft.datastructures.queue;

public class ArrayQueue implements Queue {

    private Object[] array;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue() {
        this(100);
    }

    public ArrayQueue(int size) {
        array = new Object[size];
    }

    @Override
    public void enqueue(Object value) {
        ensureCapacity();
        array[tail++] = value;
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Unable to dequeue on empty queue");
        }
        Object firstElement = array[head];
        array[head] = null;
        head++;
        if (isEmpty()) {
            clear();
        }
        return firstElement;
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Unable to peek on empty queue");
        }
        return array[head];
    }

    @Override
    public int size() {
        return tail - head;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean contains(Object value) {
        if (value == null) {
            return false;
        }

        for (int i = head; i < tail; i++) {
            if (array[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = head; i < tail; i++) {
            array[i] = null;
        }
        head = 0;
        tail = 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = head; i < tail; i++) {
            stringBuilder.append(array[i]);
            if (i + 1 != tail) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private void ensureCapacity() {
        if (tail == array.length) {
            Object[] newArray = new Object[array.length * 2];
            int j = 0;
            for (int i = head; i < tail; i++, j++) {
                newArray[j] = array[i];
            }
            array = newArray;
            head = 0;
            tail = j;
        }
    }
}
