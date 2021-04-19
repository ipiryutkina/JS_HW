package hw2.tsk5;

import java.util.*;

//ListIterator wrapper
public class ReverseIterator<T> implements Iterator<T>, Iterable<T> {

    ListIterator<T> li;

    public ReverseIterator(List<T> col) {
        this.li = col.listIterator(col.size());
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return li.hasPrevious();
    }

    @Override
    public T next() {
        return li.previous();//col.get(pos--);
    }

    /*
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    */
}
