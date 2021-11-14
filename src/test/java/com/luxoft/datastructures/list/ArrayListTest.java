package com.luxoft.datastructures.list;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.luxoft.datastructures.list.AbstractList.NO_SUCH_ELEMENT_ERROR_MESSAGE;
import static com.luxoft.datastructures.list.ArrayList.INVALID_RANGE_EXCEPTION_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ArrayListTest {

    @Test
    void testAddElement() {
        List<String> list = new ArrayList<>();

        list.add("A");

        assertEquals(1, list.size());
        assertTrue(list.contains("A"));
    }

    @Test
    void testAddDuplicateElement() {
        List<String> list = new ArrayList<>();

        list.add("A");

        assertEquals(1, list.size());

        list.add("A");

        assertEquals(2, list.size());
    }

    @Test
    void testAddElements() {
        List<String> list = new ArrayList<>();

        list.add("A");
        list.add("B");

        assertEquals(2, list.size());
        assertTrue(list.contains("A"));
        assertTrue(list.contains("B"));
    }

    @Test
    void testAddElementsAndAssertPositions() {
        List<String> list = new ArrayList<>();

        list.add("A");
        list.add("B");

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    void testAddElementAtFirstIndex() {
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

        list.set("C", 0);

        assertEquals(2, list.size());
        assertEquals("C", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    void testSetLastElement() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

        list.set("C", list.size() - 1);

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
    }

    @Test
    void testExceptionSetInvalidIndex() {
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

        assertFalse(list.isEmpty());

        list.clear();

        assertTrue(list.isEmpty());
    }

    @Test
    void testAddAfterClear() {
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

        assertEquals(2, list.size());

        list.remove(0);

        assertEquals(1, list.size());
    }

    @Test
    void testContains() {
        List<String> list = new ArrayList<>();
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
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("C");

        int actualIndex = list.indexOf("A");

        assertEquals(0, actualIndex);
    }

    @Test
    void testLastIndexOf() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("C");

        int actualIndex = list.lastIndexOf("A");

        assertEquals(2, actualIndex);
    }

    @Test
    void testEmptyToString() {
        List<String> list = new ArrayList<>();

        assertEquals("[]", list.toString());
    }

    @Test
    void testFilledToString() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

        assertEquals("[A, B]", list.toString());
    }

    @Test
    void testCapacity() {
        List<String> list = new ArrayList<>(2);
        list.add("A");
        list.add("B");

        list.add("C");

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    void testIteratorFullForeach() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        Iterator<String> iterator = list.iterator();

        assertEquals(3, list.size());
        assertEquals(list.get(0), iterator.next());
        assertEquals(list.get(1), iterator.next());
        assertEquals(list.get(2), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorThrowsExceptionWhenAccessingNextAfterLastElement() {
        List<String> list = new ArrayList<>();
        list.add("A");

        Iterator<String> iterator = list.iterator();
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
        List<String> list = new ArrayList<>();
        list.add("A");

        Iterator<String> iterator = list.iterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    void testIteratorReturnsFalseWhenNextElementDoesNotExist() {
        List<String> list = new ArrayList<>();

        Iterator<String> iterator = list.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorAfterRemovingElement() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        list.remove(0);
        Iterator<String> iterator = list.iterator();

        assertEquals(2, list.size());
        assertEquals(list.get(0), iterator.next());
        assertEquals(list.get(1), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorRemoveMethod() {
        List<String> list = new ArrayList<>();
        String expectedToString = "[A, C]";
        list.add("A");
        list.add("B");
        list.add("C");

        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();

        assertEquals(2, list.size());
        iterator.next();
        assertFalse(iterator.hasNext());
        assertEquals(expectedToString, list.toString());
    }

}
