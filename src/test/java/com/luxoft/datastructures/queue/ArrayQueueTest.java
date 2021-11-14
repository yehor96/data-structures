package com.luxoft.datastructures.queue;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ArrayQueueTest {

    private Queue<String> queue;

    @Test
    void testEnqueue() {
        queue = new ArrayQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");

        assertFalse(queue.isEmpty());
        assertEquals(2, queue.size());
        assertTrue(queue.contains("A"));
    }

    @Test
    void testDequeueReturnsFirstElement() {
        queue = new ArrayQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals("A", queue.dequeue());
    }

    @Test
    void testDequeueRemovesDequeueElement() {
        queue = new ArrayQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");

        queue.dequeue();

        assertFalse(queue.contains("A"));
        assertEquals(1, queue.size());
    }

    @Test
    void testDequeueOnEmptyQueue() {
        queue = new ArrayQueue<>();

        try {
            queue.dequeue();
            fail("Exception was not thrown");
        } catch (Exception e) {
            assertEquals(IllegalStateException.class, e.getClass());
            assertEquals("Unable to dequeue on empty queue", e.getMessage());
        }
    }

    @Test
    void testPeekReturnsFirstElement() {
        queue = new ArrayQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals("A", queue.peek());
    }

    @Test
    void testPeekReturnsFirstElementAfterDequeue() {
        queue = new ArrayQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");

        queue.dequeue();

        assertEquals("B", queue.peek());
    }

    @Test
    void testPeekOnEmptyQueue() {
        queue = new ArrayQueue<>();

        try {
            queue.peek();
            fail("Exception was not thrown");
        } catch (Exception e) {
            assertEquals(IllegalStateException.class, e.getClass());
            assertEquals("Unable to peek on empty queue", e.getMessage());
        }
    }

    @Test
    void testQueueIsEmptyAfterAllElementsDequeue() {
        queue = new ArrayQueue<>();
        queue.enqueue("A");

        queue.dequeue();

        assertTrue(queue.isEmpty());
    }

    @Test
    void testQueueSizeIncreasesAndDecreasesAfterEnqueueAndDequeue() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue("A");
        assertEquals(1, queue.size());

        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    void testClearQueue() {
        queue = new ArrayQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        assertEquals(3, queue.size());
        queue.clear();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void testContains() {
        queue = new ArrayQueue<>();
        queue.enqueue("A");

        assertTrue(queue.contains("A"));

        queue.dequeue();
        assertFalse(queue.contains("A"));
    }

    @Test
    void testToStringEmptyQueue() {
        queue = new ArrayQueue<>();
        String expectedToString = "[]";

        String actualToString = queue.toString();

        assertEquals(expectedToString, actualToString);
    }

    @Test
    void testToStringFilledQueue() {
        queue = new ArrayQueue<>();
        String expectedToString = "[A, B, C]";
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        String actualToString = queue.toString();

        assertEquals(expectedToString, actualToString);
    }

    @Test
    void testQueueOverflow() {
        queue = new ArrayQueue<>(2);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        assertFalse(queue.isEmpty());
        assertEquals(3, queue.size());
        assertTrue(queue.contains("A"));
        assertTrue(queue.contains("B"));
        assertTrue(queue.contains("C"));
    }

    @Test
    void testIteratorFullForeach() {
        queue = new ArrayQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        Queue<String> result = new ArrayQueue<>();

        for (String o : queue) {
            result.enqueue(o);
        }

        assertEquals(queue.size(), result.size());
        assertEquals(queue.dequeue(), result.dequeue());
        assertEquals(queue.dequeue(), result.dequeue());
        assertEquals(queue.dequeue(), result.dequeue());
    }

    @Test
    void testIteratorThrowsExceptionWhenAccessingNextAfterLastElement() {
        queue = new ArrayQueue<>();
        queue.enqueue("A");

        Iterator<String> iterator = queue.iterator();
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
        queue = new ArrayQueue<>();
        queue.enqueue("A");

        Iterator<String> iterator = queue.iterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    void testIteratorReturnsFalseWhenNextElementDoesNotExist() {
        queue = new ArrayQueue<>();

        Iterator<String> iterator = queue.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorAfterRemovingElement() {
        queue = new ArrayQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        Queue<String> result = new ArrayQueue<>();

        queue.dequeue();
        for (String o : queue) {
            result.enqueue(o);
        }

        assertEquals(queue.size(), result.size());
        assertEquals(queue.dequeue(), result.dequeue());
        assertEquals(queue.dequeue(), result.dequeue());
    }

    @Test
    void testIteratorRemoveMethod() {
        queue = new ArrayQueue<>();
        String expectedToString = "[A, C]";
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        Iterator<String> iterator = queue.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();

        assertEquals(2, queue.size());
        iterator.next();
        assertFalse(iterator.hasNext());
        assertEquals(expectedToString, queue.toString());
    }

}
