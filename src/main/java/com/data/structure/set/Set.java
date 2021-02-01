package com.data.structure.set;

/**
 * @author zms
 * @date 4:19 下午 2020/12/28
 */
public interface Set<E> {

    int size();

    boolean isEmpty();

    void clear();

    boolean contains(E element);

    void add(E element);

    void remove(E element);

    void traversal(Visitor<E> visitor);

    abstract class Visitor<E> {
        boolean stop;

        abstract boolean visit(E element);
    }

}
