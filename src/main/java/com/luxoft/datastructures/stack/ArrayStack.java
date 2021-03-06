package com.luxoft.datastructures.stack;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayStack<T> extends AbstractStack<T> {

    private static final int DEFAULT_CAPACITY = 16;

    private T[] array;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        array = (T[]) new Object[capacity];
    }

    @Override
    public void push(T value) {
        ensureCapacity();
        array[size++] = value;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Unable to pop on empty stack");
        }
        T element = peek();
        array[size] = null;
        size--;
        return element;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Unable to peek on empty stack");
        }
        return array[size - 1];
    }

    @Override
    public boolean contains(T value) {
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
    public java.util.Iterator<T> iterator() {
        return new Iterator();
    }

    private void ensureCapacity() {
        if (size == array.length) {
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[(int) (array.length * 1.5 + 1)];

            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    private void removeByIndex(int index) {
        if (index != size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        array[size - 1] = null;
        size--;
    }

    private class Iterator implements java.util.Iterator<T> {

        private int counter = size;
        private boolean isRemovable = false;

        @Override
        public boolean hasNext() {
            return counter > 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                String errorMessage = String.format("Actual size of %d is reached", size());
                throw new NoSuchElementException(errorMessage);
            }
            isRemovable = true;
            return array[--counter];
        }

        @Override
        public void remove() {
            if (!isRemovable) {
                throw new IllegalStateException("Call next() before removing any elements");
            }

            removeByIndex(counter);
            isRemovable = false;
        }
    }
}
