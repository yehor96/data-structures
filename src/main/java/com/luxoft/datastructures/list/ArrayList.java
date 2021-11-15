package com.luxoft.datastructures.list;

import java.util.NoSuchElementException;

public class ArrayList<T> extends AbstractList<T> {

    private T[] array;

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        array = (T[]) new Object[capacity];
    }

    public ArrayList() {
        this(100);
    }

    @Override
    public void add(T value, int index) {
        verifyIndexAdd(index);
        verifyNotNull(value);
        ensureCapacity();

        if (index < size) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = value;
        size++;
    }

    @Override
    public T remove(int index) {
        verifyIndex(index);

        T removed = array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        array[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T get(int index) {
        verifyIndex(index);
        return array[index];
    }

    @Override
    public T set(T value, int index) {
        verifyIndex(index);
        verifyNotNull(value);

        T replaced = array[index];
        array[index] = value;
        return replaced;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public int indexOf(T value) {
        if (value == null) {
            return -1;
        }

        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        if (value == null) {
            return -1;
        }

        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new Iterator();
    }

    private void ensureCapacity() {
        if (size == array.length) {
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[(int) (array.length * 1.5 + 1)];

            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private class Iterator implements java.util.Iterator<T> {

        private boolean isRemovable = false;
        private int counter = -1;

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
            isRemovable = true;
            return array[++counter];
        }

        @Override
        public void remove() {
            if (!isRemovable) {
                throw new IllegalStateException("Call next() before removing any elements");
            }
            ArrayList.this.remove(counter--);
            isRemovable = false;
        }
    }
}
