package com.luxoft.datastructures.stack;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ArrayStackTest {

    private Stack stack;

    @Test
    void testPush() {
        stack = new ArrayStack();
        stack.push("A");
        stack.push("B");

        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());
        assertTrue(stack.contains("A"));
    }

    @Test
    void testPopReturnsLastAddedElement() {
        stack = new ArrayStack();
        stack.push("A");
        stack.push("B");

        assertEquals("B", stack.pop());
    }

    @Test
    void testPopRemovesElement() {
        stack = new ArrayStack();
        stack.push("A");
        stack.push("B");

        stack.pop();

        assertFalse(stack.contains("B"));
        assertEquals(1, stack.size());
    }

    @Test
    void testPopOnEmptyStack() {
        stack = new ArrayStack();

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
        stack = new ArrayStack();
        stack.push("A");
        stack.push("B");

        assertEquals("B", stack.peek());
    }

    @Test
    void testPeekReturnsLastAddedElementAfterPop() {
        stack = new ArrayStack();
        stack.push("A");
        stack.push("B");

        stack.pop();

        assertEquals("A", stack.peek());
    }

    @Test
    void testPeekOnEmptyStack() {
        stack = new ArrayStack();

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
        stack = new ArrayStack();
        stack.push("A");

        stack.pop();

        assertTrue(stack.isEmpty());
    }

    @Test
    void testStackSizeIncreasesAndDecreasesAfterPushAndPop() {
        stack = new ArrayStack();
        assertEquals(0, stack.size());

        stack.push("A");
        assertEquals(1, stack.size());

        stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    void testClearStack() {
        stack = new ArrayStack();
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
        stack = new ArrayStack();
        stack.push("A");

        assertTrue(stack.contains("A"));

        stack.pop();
        assertFalse(stack.contains("A"));
    }

    @Test
    void testToStringEmptyStack() {
        stack = new ArrayStack();
        String expectedToString = "[]";

        String actualToString = stack.toString();

        assertEquals(expectedToString, actualToString);
    }

    @Test
    void testToStringFilledStack() {
        stack = new ArrayStack();
        String expectedToString = "[C, B, A]";
        stack.push("A");
        stack.push("B");
        stack.push("C");

        String actualToString = stack.toString();

        assertEquals(expectedToString, actualToString);
    }

    @Test
    void testStackOverflow() {
        stack = new ArrayStack(2);
        stack.push("A");
        stack.push("B");
        stack.push("C");

        assertFalse(stack.isEmpty());
        assertEquals(3, stack.size());
        assertTrue(stack.contains("A"));
        assertTrue(stack.contains("B"));
        assertTrue(stack.contains("C"));
    }

    @Test
    void testIteratorFullForeach() {
        stack = new ArrayStack();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        Stack result = new ArrayStack();

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
        stack = new ArrayStack();
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
        stack = new ArrayStack();
        stack.push("A");

        Iterator iterator = stack.iterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    void testIteratorReturnsFalseWhenNextElementDoesNotExist() {
        stack = new ArrayStack();

        Iterator iterator = stack.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorAfterRemovingElement() {
        stack = new ArrayStack();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        Stack result = new ArrayStack();

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
        stack = new ArrayStack();
        String expectedToString = "[C, A]";
        stack.push("A");
        stack.push("B");
        stack.push("C");

        Iterator iterator = stack.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();

        assertEquals(2, stack.size());
        iterator.next();
        assertFalse(iterator.hasNext());
        assertEquals(expectedToString, stack.toString());
    }
}
