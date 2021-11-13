package com.luxoft.datastructures.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class LinkedStackTest {

    private Stack stack;

    @BeforeEach
    void beforeEach() {
        stack = new LinkedStack();
    }

    @Test
    void testPush() {
        stack.push("A");
        stack.push("B");

        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());
        assertTrue(stack.contains("A"));
    }

    @Test
    void testPopReturnsLastAddedElement() {
        stack.push("A");
        stack.push("B");

        assertEquals("B", stack.pop());
    }

    @Test
    void testPopRemovesElement() {
        stack.push("A");
        stack.push("B");

        stack.pop();

        assertFalse(stack.contains("B"));
        assertEquals(1, stack.size());
    }

    @Test
    void testPopOnEmptyStack() {
        try {
            stack.pop();
            fail("Exception was not thrown");
        } catch (Exception e) {
            assertEquals(IllegalStateException.class, e.getClass());
            assertEquals("Unable to pop on empty stack", e.getMessage());
        }
    }

    @Test
    void testPeekReturnsLastAddedElement() {
        stack.push("A");
        stack.push("B");

        assertEquals("B", stack.peek());
    }

    @Test
    void testPeekReturnsLastAddedElementAfterPop() {
        stack.push("A");
        stack.push("B");

        stack.pop();

        assertEquals("A", stack.peek());
    }

    @Test
    void testPeekOnEmptyStack() {
        try {
            stack.peek();
            fail("Exception was not thrown");
        } catch (Exception e) {
            assertEquals(IllegalStateException.class, e.getClass());
            assertEquals("Unable to peek on empty stack", e.getMessage());
        }
    }

    @Test
    void testStackIsEmptyAfterAllElementsPopped() {
        stack.push("A");

        stack.pop();

        assertTrue(stack.isEmpty());
    }

    @Test
    void testStackSizeIncreasesAndDecreasesAfterPushAndPop() {
        assertEquals(0, stack.size());

        stack.push("A");
        assertEquals(1, stack.size());

        stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    void testClearStack() {
        stack.push("A");
        stack.push("B");
        stack.push("C");

        assertEquals(3, stack.size());
        stack.clear();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void testContains() {
        stack.push("A");

        assertTrue(stack.contains("A"));

        stack.pop();
        assertFalse(stack.contains("A"));
    }

    @Test
    void testToStringEmptyStack() {
        String expectedToString = "[]";

        String actualToString = stack.toString();

        assertEquals(expectedToString, actualToString);
    }

    @Test
    void testToStringFilledStack() {
        String expectedToString = "[C, B, A]";
        stack.push("A");
        stack.push("B");
        stack.push("C");

        String actualToString = stack.toString();

        assertEquals(expectedToString, actualToString);
    }

    @Test
    void testManyPushAndPopOperation() {
        stack.push("A");
        stack.push("B");
        stack.push("C");
        assertEquals("C", stack.pop());
        assertEquals("B", stack.pop());
        assertEquals("A", stack.pop());
        stack.push("E");
        stack.push("D");
        stack.push("F");
        stack.push("Z");
        assertEquals("Z", stack.pop());
        assertEquals(3, stack.size());
    }

    @Test
    void testIteratorFullForeach() {
        stack.push("A");
        stack.push("B");
        stack.push("C");
        Stack result = new LinkedStack();

        for (Object o : stack) {
            result.push(o);
        }

        assertEquals(stack.size(), result.size());
        assertEquals("A", result.pop());
        assertEquals("B", result.pop());
        assertEquals("C", result.pop());
    }

    @Test
    void testIteratorThrowsExceptionWhenAccessingNextAfterLastElement() {
        stack.push("A");

        Iterator iterator = stack.iterator();
        iterator.next();
        try {
            iterator.next();
            fail("Exception was not thrown");
        } catch (Exception e) {
            assertEquals(NoSuchElementException.class, e.getClass());
            assertEquals("Actual size of 1 is reached", e.getMessage());
        }
    }

    @Test
    void testIteratorReturnsTrueWhenNextElementExists() {
        stack.push("A");

        Iterator iterator = stack.iterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    void testIteratorReturnsFalseWhenNextElementDoesNotExist() {
        Iterator iterator = stack.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorAfterRemovingElement() {
        stack.push("A");
        stack.push("B");
        stack.push("C");
        Stack result = new LinkedStack();

        stack.pop();
        for (Object o : stack) {
            result.push(o);
        }

        assertEquals(stack.size(), result.size());
        assertEquals("A", result.pop());
        assertEquals("B", result.pop());
    }

    @Test
    void testIteratorRemoveMethod() {
        stack.push("A");
        stack.push("B");
        stack.push("C");

        Iterator iterator = stack.iterator();
        iterator.next();
        iterator.remove();

        assertEquals(2, stack.size());
        assertEquals(stack.peek(), iterator.next());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

}
