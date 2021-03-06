package com.luxoft.datastructures.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class LinkedQueueTest {

    private Queue<String> queue;

    @BeforeEach
    void beforeEach() {
        queue = new LinkedQueue<>();
    }

    @Test
    void testEnqueue() {
        queue.enqueue("A");
        queue.enqueue("B");

        assertFalse(queue.isEmpty());
        assertEquals(2, queue.size());
        assertTrue(queue.contains("A"));
    }

    @Test
    void testDequeueReturnsFirstElement() {
        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals("A", queue.dequeue());
    }

    @Test
    void testDequeueRemovesDequeueElement() {
        queue.enqueue("A");
        queue.enqueue("B");

        queue.dequeue();

        assertFalse(queue.contains("A"));
        assertEquals(1, queue.size());
    }

    @Test
    void testDequeueOnEmptyQueue() {
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
        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals("A", queue.peek());
    }

    @Test
    void testPeekReturnsFirstElementAfterDequeue() {
        queue.enqueue("A");
        queue.enqueue("B");

        queue.dequeue();

        assertEquals("B", queue.peek());
    }

    @Test
    void testPeekOnEmptyQueue() {
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
        queue.enqueue("A");

        queue.dequeue();

        assertTrue(queue.isEmpty());
    }

    @Test
    void testQueueSizeIncreasesAndDecreasesAfterEnqueueAndDequeue() {
        assertEquals(0, queue.size());

        queue.enqueue("A");
        assertEquals(1, queue.size());

        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    void testClearQueue() {
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
        queue.enqueue("A");

        assertTrue(queue.contains("A"));

        queue.dequeue();
        assertFalse(queue.contains("A"));
    }

    @Test
    void testToStringEmptyQueue() {
        String expectedToString = "[]";

        String actualToString = queue.toString();

        assertEquals(expectedToString, actualToString);
    }

    @Test
    void testToStringFilledQueue() {
        String expectedToString = "[A, B, C]";
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        String actualToString = queue.toString();

        assertEquals(expectedToString, actualToString);
    }

    @Test
    void testManyEnqueueDequeueOperation() {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());
        queue.enqueue("E");
        queue.enqueue("D");
        queue.enqueue("F");
        queue.enqueue("Z");
        assertEquals("E", queue.dequeue());
        assertEquals(3, queue.size());
    }

    @Test
    void testIteratorFullForeach() {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        Queue<String> result = new LinkedQueue<>();

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
        queue.enqueue("A");

        Iterator<String> iterator = queue.iterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    void testIteratorReturnsFalseWhenNextElementDoesNotExist() {
        Iterator<String> iterator = queue.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorAfterRemovingElement() {
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
