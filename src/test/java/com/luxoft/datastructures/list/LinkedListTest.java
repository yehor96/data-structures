package com.luxoft.datastructures.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.luxoft.datastructures.list.AbstractList.INVALID_RANGE_EXCEPTION_MESSAGE;
import static com.luxoft.datastructures.list.AbstractList.NO_SUCH_ELEMENT_ERROR_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class LinkedListTest {

    private List list;

    @BeforeEach
    void beforeEach() {
        list = new LinkedList();
    }

    @Test
    void testAddElement() {
        list.add("A");

        assertEquals(1, list.size());
        assertTrue(list.contains("A"));
    }

    @Test
    void testAddDuplicateElement() {
        list.add("A");

        assertEquals(1, list.size());

        list.add("A");

        assertEquals(2, list.size());
    }

    @Test
    void testAddElements() {
        list.add("A");
        list.add("B");

        assertEquals(2, list.size());
        assertTrue(list.contains("A"));
        assertTrue(list.contains("B"));
    }

    @Test
    void testAddElementsAndAssertPositions() {
        list.add("A");
        list.add("B");

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    void testAddElementAtFirstIndex() {
        list.add("A");
        list.add("B");

        list.add("C", 0);

        assertEquals(3, list.size());
        assertEquals("C", list.get(0));
        assertEquals("A", list.get(1));
        assertEquals("B", list.get(2));
    }

    @Test
    void testAddElementAtLastIndex() {
        list.add("A");
        list.add("B");

        list.add("C", list.size());

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    void testAddElementAtMiddleIndex() {
        list.add("A");
        list.add("B");

        list.add("C", 1);

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
        assertEquals("B", list.get(2));
    }

    @Test
    void testAddElementsAtIndex() {
        list.add("A");
        list.add("B");

        list.add("C", 1);
        list.add("D", 1);

        assertEquals(4, list.size());
        assertEquals("A", list.get(0));
        assertEquals("D", list.get(1));
        assertEquals("C", list.get(2));
        assertEquals("B", list.get(3));
    }

    @Test
    void testExceptionThrownWhenAddAtIndexLargerThanSize() {
        String expectedMessage = String.format(INVALID_RANGE_EXCEPTION_MESSAGE, 5, 2);
        list.add("A");
        list.add("B");

        try {
            list.add("C", 5);
            fail("Exception was not thrown");
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void testExceptionThrownWhenAddAtInvalidIndex() {
        String expectedMessage = String.format(INVALID_RANGE_EXCEPTION_MESSAGE, -1, 2);
        list.add("A");
        list.add("B");

        try {
            list.add("C", -1);
            fail("Exception was not thrown");
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void testRemoveAtFirstIndex() {
        list.add("A");
        list.add("B");
        list.add("C");

        Object actualRemoved = list.remove(0);

        assertEquals("A", actualRemoved);
        assertEquals(2, list.size());
        assertEquals("B", list.get(0));
        assertEquals("C", list.get(1));
    }

    @Test
    void testRemoveAtLastIndex() {
        list.add("A");
        list.add("B");
        list.add("C");

        Object actualRemoved = list.remove(list.size() - 1);

        assertEquals("C", actualRemoved);
        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    void testRemoveAddRemoveAgain() {
        list.add("A");
        list.add("B");

        Object actualFirstRemoved = list.remove(list.size() - 1);

        assertEquals("B", actualFirstRemoved);
        assertEquals(1, list.size());

        list.add("D");

        Object actualSecondRemoved = list.remove(0);

        assertEquals("A", actualSecondRemoved);
        assertEquals(1, list.size());
    }

    @Test
    void testExceptionRemoveInvalidInvalidIndex() {
        String expectedMessage = String.format(INVALID_RANGE_EXCEPTION_MESSAGE, -1, 2);

        list.add("A");
        list.add("B");
        list.add("C");

        try {
            list.remove(-1);
            fail("Exception was not thrown");
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void testExceptionRemoveIndexLargerThanSize() {
        String expectedMessage = String.format(INVALID_RANGE_EXCEPTION_MESSAGE, 5, 2);
        list.add("A");
        list.add("B");
        list.add("C");

        try {
            list.remove(5);
            fail("Exception was not thrown");
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void testGetIndex() {
        list.add("A");
        list.add("B");

        Object actualGot = list.get(1);

        assertEquals("B", actualGot);
        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    void testExceptionGetInvalidIndex() {
        String expectedMessage = String.format(INVALID_RANGE_EXCEPTION_MESSAGE, -1, 1);
        list.add("A");
        list.add("B");

        try {
            list.get(-1);
            fail("Exception was not thrown");
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void testExceptionGetIndexLargerThanSize() {
        String expectedMessage = String.format(INVALID_RANGE_EXCEPTION_MESSAGE, 5, 1);
        list.add("A");
        list.add("B");

        try {
            list.get(5);
            fail("Exception was not thrown");
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void testSetFirstElement() {
        list.add("A");
        list.add("B");

        list.set("C", 0);

        assertEquals(2, list.size());
        assertEquals("C", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    void testSetLastElement() {
        list.add("A");
        list.add("B");

        list.set("C", list.size() - 1);

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
    }

    @Test
    void testExceptionSetInvalidIndex() {
        String expectedMessage = String.format(INVALID_RANGE_EXCEPTION_MESSAGE, -1, 1);
        list.add("A");
        list.add("B");

        try {
            list.set("C", -1);
            fail("Exception was not thrown");
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void testExceptionSetIndexLargerThanSize() {
        String expectedMessage = String.format(INVALID_RANGE_EXCEPTION_MESSAGE, 5, 1);
        list.add("A");
        list.add("B");

        try {
            list.set("C", 5);
            fail("Exception was not thrown");
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    void testClearAndEmpty() {
        list.add("A");
        list.add("B");

        assertFalse(list.isEmpty());

        list.clear();

        assertTrue(list.isEmpty());
    }

    @Test
    void testAddAfterClear() {
        list.add("A");
        list.add("B");

        list.clear();

        assertTrue(list.isEmpty());

        list.add("C");
        list.add("D");

        assertEquals(2, list.size());
        assertEquals("C", list.get(0));
        assertEquals("D", list.get(1));
    }

    @Test
    void testSize() {
        list.add("A");
        list.add("B");

        assertEquals(2, list.size());

        list.remove(0);

        assertEquals(1, list.size());
    }

    @Test
    void testContains() {
        list.add("A");
        list.add("B");

        assertTrue(list.contains("A"));

        list.remove(0);

        assertFalse(list.contains("A"));

        list.set("C", 0);

        assertFalse(list.contains("B"));
        assertTrue(list.contains("C"));
    }

    @Test
    void testIndexOf() {
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("C");

        int actualIndex = list.indexOf("A");

        assertEquals(0, actualIndex);
    }

    @Test
    void testLastIndexOf() {
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("C");

        int actualIndex = list.lastIndexOf("A");

        assertEquals(2, actualIndex);
    }

    @Test
    void testEmptyToString() {
        assertEquals("[]", list.toString());
    }

    @Test
    void testFilledToString() {
        list.add("A");
        list.add("B");

        assertEquals("[A, B]", list.toString());
    }

    @Test
    void testManyAdditionsInRandomPositions() {
        list.add("A");
        list.add("B");
        list.add("C", 0);
        list.add("D", 1);
        list.add("E", 3);
        list.add("F", 0);
        list.add("G", list.size() - 1);
        list.add("Y", list.size() - 1);
        list.add("S");
        list.add("X", list.size() - 2);
        list.add("Z", list.size() - 2);
        list.add("O", 0);

        assertEquals(12, list.size());
        assertEquals("[O, F, C, D, A, E, G, Y, X, Z, B, S]", list.toString());
    }

    @Test
    void testManySetsInRandomPositions() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        list.set("G", 0);
        list.set("H", 1);
        list.set("I", 3);
        list.set("S", 0);
        list.set("Y", list.size() - 1);
        list.set("X", list.size() - 2);
        list.set("Z", list.size() - 2);
        list.set("O", 0);

        assertEquals(6, list.size());
        assertEquals("[O, H, C, I, Z, Y]", list.toString());
    }

    @Test
    void testIteratorFullForeach() {
        List list = new LinkedList();
        list.add("A");
        list.add("B");
        list.add("C");

        Iterator iterator = list.iterator();

        assertEquals(3, list.size());
        assertEquals(list.get(0), iterator.next());
        assertEquals(list.get(1), iterator.next());
        assertEquals(list.get(2), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorThrowsExceptionWhenAccessingNextAfterLastElement() {
        List list = new LinkedList();
        list.add("A");

        Iterator iterator = list.iterator();
        iterator.next();
        try {
            iterator.next();
            fail("Exception was not thrown");
        } catch (Exception e) {
            String expectedErrorMessage = String.format(NO_SUCH_ELEMENT_ERROR_MESSAGE, 1, 1);
            assertEquals(NoSuchElementException.class, e.getClass());
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    @Test
    void testIteratorReturnsTrueWhenNextElementExists() {
        List list = new LinkedList();
        list.add("A");

        Iterator iterator = list.iterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    void testIteratorReturnsFalseWhenNextElementDoesNotExist() {
        List list = new LinkedList();

        Iterator iterator = list.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorAfterRemovingElement() {
        List list = new LinkedList();
        list.add("A");
        list.add("B");
        list.add("C");

        list.remove(0);
        Iterator iterator = list.iterator();

        assertEquals(2, list.size());
        assertEquals(list.get(0), iterator.next());
        assertEquals(list.get(1), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorRemoveMethod() {
        List list = new LinkedList();
        list.add("A");
        list.add("B");
        list.add("C");

        Iterator iterator = list.iterator();
        iterator.next();
        iterator.remove();

        assertEquals(2, list.size());
        assertEquals(list.get(0), iterator.next());
        assertEquals(list.get(1), iterator.next());
        assertFalse(iterator.hasNext());
    }
}
