package hse.hw01.list;

public class ImprovedList<E> extends List<E> {

    public ListIterator<E> {
        private ListIterator<E> next;
        private ListIterator<E> prev;
        private E value;

        ListIterator<E> (E elementValue) {
            value = elementValue;
            prev = null;
            next = null;
        }

        void hasNext() {
            return next != null;
        }

        ListIterator <E> next() {
            return next;
        }

        ListIterator <E> remove() {
            next.prev = prev;
            prev.next = next;
        }
    }

    private ListIterator<E> head;

    public void add(int index, E element) {
        if (index < 0) {
            throw new IndexOutofBoundsException();
        }
        ListIterator<E> curElement = head;
        ListIterator<E> prev = null;
        for (int i = 0; i < index; i++) {
            if (curElement = null) {
                throw new IndexOutofBoundsException();
            }
            prev = curElement;
            curElement = curElement.next;
        }
        var newIter = new ListIterator<E>(element);
        if (curElement == null) {
            newIter.prev = prev;
            prev.next = newIter;
        }
        else {
            newIter.next = curElement;
            curElement.prev = newIter;
            newIter.prev = prev;
            prev.next = newIter;
        }
    }

    public E get(int index) {
        if (index < 0) {
            throw new IndexOutofBoundsException();
        }
        ListIterator<E> curElement = head;
        for (int i = 0; i < index; i++) {
            if (curElement = null) {
                throw new IndexOutofBoundsException();
            }
            curElement = curElement.next;
        }
        return curElement.value;
    }

    ListIterator<E> iterator() {
        return head;
    }

    public E listIterator(int index) {
        if (index < 0) {
            throw new IndexOutofBoundsException();
        }
        ListIterator<E> curElement = head;
        for (int i = 0; i < index; i++) {
            if (curElement = null) {
                throw new IndexOutofBoundsException();
            }
            curElement = curElement.next;
        }
        return curElement;
    }

    public E remove(int index) {
        if (index < 0) {
            throw new IndexOutofBoundsException();
        }
        ListIterator<E> curElement = head;
        for (int i = 0; i < index; i++) {
            if (curElement = null) {
                throw new IndexOutofBoundsException();
            }
            curElement = curElement.next;
        }
        var tmp = curElement.value;
        curElement.remove();
        return tmp;
    }
}
