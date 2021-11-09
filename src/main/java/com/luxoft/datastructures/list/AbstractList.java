package com.luxoft.datastructures.list;

import java.util.Objects;

public abstract class AbstractList implements List {

    protected static final String NULL_PASSED_EXCEPTION_MESSAGE = "Null values are not allowed in the list";
    protected static final String INVALID_RANGE_EXCEPTION_MESSAGE =
            "Unable to process value at index %d. Available range: [0, %d]";

    protected int size = 0;

    @Override
    public void add(Object value) {
        add(value, size);
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
        return indexOf(value) != -1;
    }

    protected void verifyIndexAdd(int index) {
        if (!(index >= 0 && index <= size)) {
            String errorMsg = String.format(INVALID_RANGE_EXCEPTION_MESSAGE, index, size);
            throw new IndexOutOfBoundsException(errorMsg);
        }
    }

    protected void verifyIndex(int index) {
        if (!(index >= 0 && index < size)) {
            String errorMsg = String.format(INVALID_RANGE_EXCEPTION_MESSAGE, index, size - 1);
            throw new IndexOutOfBoundsException(errorMsg);
        }
    }

    protected void verifyNotNull(Object value) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException(NULL_PASSED_EXCEPTION_MESSAGE);
        }
    }
}