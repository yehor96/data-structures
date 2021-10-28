package com.luxoft.datastructures.list;

public class ArrayList implements List {

    static final String INVALID_RANGE_EXCEPTION_MESSAGE =
            "Unable to process value at index %d. Available range: [0, %d]";

    private Object[] array;
    private int size = 0;

    public ArrayList(int capacity) {
        array = new Object[capacity];
    }

    public ArrayList() {
        this(100);
    }

    @Override
    public void add(Object value) {
        add(value, size);
    }

    @Override
    public void add(Object value, int index) {
        verifyIndexWithinArrayBounds(index, size);
        ensureCapacity();

        if (index < size) {
            for (int i = size - 1; i >= index; i--) {
                array[i + 1] = array[i];
            }
        }
        array[index] = value;
        size++;
    }

    @Override
    public Object remove(int index) {
        verifyIndexWithinArrayBounds(index, size - 1);

        Object removed = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public Object get(int index) {
        verifyIndexWithinArrayBounds(index, size - 1);
        return array[index];
    }

    @Override
    public Object set(Object value, int index) {
        verifyIndexWithinArrayBounds(index, size - 1);
        Object replaced = array[index];
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
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object value) {
        if (value == null) {
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
    public int indexOf(Object value) {
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
    public int lastIndexOf(Object value) {
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
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private void ensureCapacity() {
        if (size == array.length) {
            Object[] newArray = new Object[(int) (array.length * 1.5)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void verifyIndexWithinArrayBounds(int index, int topBound) {
        if (!(index >= 0 && index <= topBound)) {
            String errorMsg = String.format(INVALID_RANGE_EXCEPTION_MESSAGE, index, topBound);
            throw new IndexOutOfBoundsException(errorMsg);
        }
    }
}
