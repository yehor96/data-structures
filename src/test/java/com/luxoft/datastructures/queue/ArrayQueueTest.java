package com.luxoft.datastructures.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {

    @Test
    void testEnqueue() {
        ArrayQueue queue = new ArrayQueue();
        queue.enqueue("A");
        queue.enqueue("B");

        assertFalse(queue.isEmpty());
        assertEquals(2, queue.size());
        assertTrue(queue.contains("A"));
    }

    @Test
    void testDequeueReturnsFirstElement() {
        ArrayQueue queue = new ArrayQueue();
        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals("A", queue.dequeue());
    }

    @Test
    void testDequeueRemovesDequeueElement() {
        ArrayQueue queue = new ArrayQueue();
        queue.enqueue("A");
        queue.enqueue("B");

        queue.dequeue();

        assertFalse(queue.contains("A"));
        assertEquals(1, queue.size());
    }

    @Test
    void testDequeueOnEmptyQueue() {
        ArrayQueue queue = new ArrayQueue();

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
        ArrayQueue queue = new ArrayQueue();
        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals("A", queue.peek());
    }

    @Test
    void testPeekReturnsFirstElementAfterDequeue() {
        ArrayQueue queue = new ArrayQueue();
        queue.enqueue("A");
        queue.enqueue("B");

        queue.dequeue();

        assertEquals("B", queue.peek());
    }

    @Test
    void testPeekOnEmptyQueue() {
        ArrayQueue queue = new ArrayQueue();

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
        ArrayQueue queue = new ArrayQueue();
        queue.enqueue("A");

        queue.dequeue();

        assertTrue(queue.isEmpty());
    }

    @Test
    void testQueueSizeIncreasesAndDecreasesAfterEnqueueAndDequeue() {
        ArrayQueue queue = new ArrayQueue();
        assertEquals(0, queue.size());

        queue.enqueue("A");
        assertEquals(1, queue.size());

        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    void testClearQueue() {
        ArrayQueue queue = new ArrayQueue();
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
        ArrayQueue queue = new ArrayQueue();
        queue.enqueue("A");

        assertTrue(queue.contains("A"));

        queue.dequeue();
        assertFalse(queue.contains("A"));
    }

    @Test
    void testToStringEmptyQueue() {
        ArrayQueue queue = new ArrayQueue();
        String expectedToString = "[]";

        String actualToString = queue.toString();

        assertEquals(expectedToString, actualToString);
    }

    @Test
    void testToStringFilledQueue() {
        ArrayQueue queue = new ArrayQueue();
        String expectedToString = "[A, B, C]";
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        String actualToString = queue.toString();

        assertEquals(expectedToString, actualToString);
    }

    @Test
    void testQueueOverflow() {
        ArrayQueue queue = new ArrayQueue(2);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        assertFalse(queue.isEmpty());
        assertEquals(3, queue.size());
        assertTrue(queue.contains("A"));
        assertTrue(queue.contains("B"));
        assertTrue(queue.contains("C"));
    }

}
