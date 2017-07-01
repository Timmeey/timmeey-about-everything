package de.timmeey.blog.singleStatementUnitTests;

import java.util.AbstractList;
import java.util.ArrayList;

public class IterableAsList<T> extends AbstractList<T> {
    private final Iterable<T> source;
    private final ArrayList<T> cache;

    /**
     * Ctor.
     *
     * @param iterable
     *     An {@link Iterable}
     */
    public IterableAsList(final Iterable<T> iterable) {
        super();
        this.source = iterable;
        this.cache = new ArrayList<>(0);
    }

    @Override
    public int size() {
        int size =0;
        for (T ignored : this.source) {
            size++;
        }
        return size;    }

    /**
     * Find item in cache by index.
     *
     * @param index
     *     Item index
     * @return Cached item
     */
    @Override
    public T get(final int index) {
        //if the cache is not yet filled prefetch everything
        if (this.cache.size() != this.size()) {
            //iterate over the underlying iterable to fetch everything
            for (final T item : this.source) {
                this.cache.add(item);
            }
        }
        return this.cache.get(index);
    }
}
