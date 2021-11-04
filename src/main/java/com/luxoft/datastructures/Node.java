package com.luxoft.datastructures;

public class Node {

    private Object value;
    public Node next;
    public Node prev;

    public Node(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
