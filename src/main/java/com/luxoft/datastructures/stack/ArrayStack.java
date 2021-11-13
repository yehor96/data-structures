package com.luxoft.datastructures.stack;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayStack extends AbstractStack {

    private static final int DEFAULT_CAPACITY = 16;

    private Object[] array;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int capacity) {
        array = new Object[capacity];
    }

    @Override
    public void push(Object value) {
        ensureCapacity();
        array[size++] = value;
    }

    @Override
    public Object pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Unable to pop on empty stack");
        }
        Object element = peek();
        array[size] = null;
        size--;
        return element;
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Unable to peek on empty stack");
        }
        return array[size - 1];
    }

    @Override
    public boolean contains(Object value) {
        if (Objects.isNull(value)) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public java.util.Iterator iterator() {
        return new Iterator();
    }

    private void ensureCapacity() {
        if (size == array.length) {
            Object[] newArray = new Object[(int) (array.length * 1.5)];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    private class Iterator implements java.util.Iterator {

        private int counter = 0;

        @Override
        public boolean hasNext() {
            return counter < size;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                String errorMessage = String.format("Actual size of %d is reached", size());
                throw new NoSuchElementException(errorMessage);
            }
            return array[counter++];
        }
    }
}
