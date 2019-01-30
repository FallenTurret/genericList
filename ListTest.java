package com.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ListTest {
    private myList<Integer> list;

    @BeforeEach
    void initList() {
        list = new myList<Integer>();
    }

    @Test
    void addGet() {
        assertTrue(list.add(0));
        assertTrue(list.add(1));
        assertTrue(list.add(2));
        assertEquals(0, list.get(0));
        assertEquals(1, list.get(1));
        assertEquals(2, list.get(2));
    }

    @Test
    void addPosition() {
        list.add(0, 0);
        list.add(1, 1);
        list.add(0, -1);
        assertEquals(-1, list.get(0));
        assertEquals(0, list.get(1));
        assertEquals(1, list.get(2));
    }

    @Test
    void addOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(0, 10));
        list.add(0);
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, 11));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 12));
    }

    @Test
    void getOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        list.add(0);
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void removeTest() {
        genList();
        assertEquals(0, list.remove(0)); // -0 1 2 3 4 5 6 7 8 9
        assertEquals(1, list.remove(0)); // -1 2 3 4 5 6 7 8 9
        assertEquals(3, list.remove(4)); // 2 3 4 5 -6 7 8 9
        assertEquals(3, list.remove(6)); // 2 3 4 5 7 8 -9
    }

    @Test
    void removeOutOfBound() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
        list.add(0);
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    @Test
    void setTest() {
        genList();
        list.set(1, 11);
        assertEquals(11, list.get(1));
        list.set(3, 13);
        assertEquals(13, list.get(3));
        assertEquals(2, list.get(2));
    }

    // tests next, previous, hasNext, hasPrevious, nextIndex
    @Test
    void iteratorToThereAndBackAgain() {
        genList();
        var iterator = list.listIterator();
        for (var i = 0; i < 10; i++) {
            assertTrue(iterator.hasNext());
            assertEquals(i, iterator.nextIndex());
            assertEquals(i, iterator.next());
            assertEquals(i, iterator.previousIndex());
        }
        assertFalse(iterator.hasNext());
        assertEquals(-1, iterator.nextIndex());

        for (var i = 9; i >= 0; i--) {
            assertTrue(iterator.hasPrevious());
            assertEquals(i, iterator.previousIndex());
            assertEquals(i, iterator.previous());
            assertEquals(i, iterator.nextIndex());
        }
        assertFalse(iterator.hasPrevious());
        assertEquals(-1, iterator.previousIndex());
    }

    @Test
    void sizeTest() {
        for (int i = 0; i < 10; i++) {
            assertEquals(i, list.size());
            list.add(i);
        }
        for (int i = 9; i >= 0; i--) {
            list.remove(0);
            assertEquals(i, list.size());
        }
    }

    void genList() {
        for (int i = 9; i >= 0; i--) {
            list.add(i);
        }
    }
}