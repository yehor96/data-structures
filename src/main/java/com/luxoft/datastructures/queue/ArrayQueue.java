package com.luxoft.datastructures.queue;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayQueue<T> extends AbstractQueue<T> {

    private T[] array;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue() {
        this(100);
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int size) {
        array = (T[]) new Object[size];
    }

    @Override
    public void enqueue(T value) {
        ensureCapacity();
        array[tail++] = value;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Unable to dequeue on empty queue");
        }
        T firstElement = array[head];
        array[head] = null;
        head++;
        if (isEmpty()) {
            clear();
        }
        return firstElement;
    }

    @Override
    public T peek() {
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
    public boolean contains(T value) {
        if (Objects.isNull(value)) {
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

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (tail == array.length) {
            T[] newArray = (T[]) new Object[(int) (array.length * 1.5)];
            System.arraycopy(array, head, newArray, 0, size());
            array = newArray;
            head = 0;
            tail = array.length - 1;
        }
    }

    private void removeByIndex(int index) {
        if (index != size() - 1) {
            System.arraycopy(array, index + 1, array, index, size() - index - 1);
        }
        array[size() - 1] = null;
        tail--;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new Iterator();
    }

    private class Iterator implements java.util.Iterator<T> {

        private int current = head - 1;
        private boolean isRemovable = false;

        @Override
        public boolean hasNext() {
            return current < tail - 1;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                String errorMessage = String.format("Actual size of %d is reached", size());
                throw new NoSuchElementException(errorMessage);
            }
            isRemovable = true;
            return array[++current];
        }

        @Override
        public void remove() {
            if (!isRemovable) {
                throw new IllegalStateException("Call next() before removing any elements");
            }

            removeByIndex(current);
            current--;
            isRemovable = false;
        }
    }

}
