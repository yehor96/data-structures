package com.luxoft.datastructures.list;

public class ArrayList extends AbstractList {

    private Object[] array;

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
        verifyNotNull(value);
        ensureCapacity();

        if (index < size) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = value;
        size++;
    }

    @Override
    public Object remove(int index) {
        verifyIndexWithinArrayBounds(index, size - 1);

        Object removed = array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
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
        verifyNotNull(value);

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
}
