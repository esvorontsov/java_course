import java.util.*;

public class LinkList<E> extends AbstractCollection<E>{
    int size = 0;
    ListItem<E> first;
    ListItem<E> last;

    public LinkList() {
    }

    public LinkList(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    private String outOfBoundsMsg(int index) {
        return String.format("Index: %d , Size: %d", index, size);
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    void linkLast(E e) {
        final LinkList.ListItem<E> l = last;
        final LinkList.ListItem<E> newItem = new LinkList.ListItem<>(l, e, null);
        last = newItem;
        if (l == null)
            first = newItem;
        else
            l.next = newItem;
        size++;
    }

    void linkBefore(E e, LinkList.ListItem<E> item) {
        final LinkList.ListItem<E> prev = item.prev;
        final LinkList.ListItem<E> newItem = new LinkList.ListItem<>(prev, e, item);
        item.prev = newItem;
        if (prev == null)
            first = newItem;
        else
            prev.next = newItem;
        size++;
    }

    E unlink(LinkList.ListItem<E> x) {
        final E element = x.data;
        final LinkList.ListItem<E> next = x.next;
        final LinkList.ListItem<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.data = null;
        size--;
        return element;
    }

    LinkList.ListItem<E> Item(int index) {
        if (index < (size >> 1)) {
            LinkList.ListItem<E> x = first;
            for (int i = 0; i < index; i++) { x = x.next; }
            return x;
        } else {
            LinkList.ListItem<E> x = last;
            for (int i = size - 1; i > index; i--) { x = x.prev; }
            return x;
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int newCount = a.length;
        if (newCount == 0) { return false; }

        LinkList.ListItem<E> prev = last;

        for (Object o : a) {
            E e = (E) o;
            LinkList.ListItem<E> newItem = new LinkList.ListItem<>(prev, e, null);
            if (prev == null)
                first = newItem;
            else
                prev.next = newItem;
            prev = newItem;
        }

        last = prev;
        size += newCount;
        return true;
    }

    @Override
    public boolean add(E element) {
        linkLast(element);
        return true;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);

        if (index == size)
            linkLast(element);
        else
            linkBefore(element, Item(index));
    }

    public E get(int index) {
        checkElementIndex(index);
        return Item(index).data;
    }

    public E set(int index, E element) {
        checkElementIndex(index);
        LinkList.ListItem<E> x = Item(index);
        E oldVal = x.data;
        x.data = element;
        return oldVal;
    }

    public E remove(int index) {
        checkElementIndex(index);
        return unlink(Item(index));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (LinkList.ListItem<E> x = first; x != null; ) {
            LinkList.ListItem<E> next = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator(){
        return new ListIterator();
    }

    private static class ListItem<E> {
        E data;
        LinkList.ListItem<E> next;
        LinkList.ListItem<E> prev;

        ListItem(LinkList.ListItem<E> prev, E element, LinkList.ListItem<E> next) {
            this.data = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private class ListIterator implements Iterator<E> {
        private ListItem<E> lastReturned;
        private ListItem<E> next;
        private int nextIndex;

        ListIterator() {
            next = first;
            nextIndex = 0;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.data;
        }
    }

}
