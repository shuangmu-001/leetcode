package com.data.structure.heap;

import java.util.*;

/**
 * @author zms
 * @date 2:26 下午 2021/5/11
 */
public class Heap02<T> {

    private final List<T> heap;
    private final Map<T, Integer> indexMap;
    private int heapSize;

    private final Comparator<? super T> comparator;

    public Heap02(Comparator<? super T> comparator) {
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T key) {
        return indexMap.containsKey(key);
    }

    public void push(T val) {
        heap.add(val);
        indexMap.put(val, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("heap is empty");
        }
        T ans = heap.get(0);
        int end = heapSize - 1;
        swap(0, end);
        heap.remove(end);
        indexMap.remove(ans);
        heapify(0, --heapSize);
        return ans;
    }

    public void resign(T value) {
        int index = indexMap.get(value);
        heapInsert(index);
        heapify(index, heapSize);
    }

    private void heapify(int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize &&
                    comparator.compare(heap.get(left + 1),
                            heap.get(left)) > 0 ? left + 1 : left;
            largest = comparator.compare(heap.get(index),
                    heap.get(largest)) > 0 ? index : largest;
            if(largest == left) {
                break;
            }
            swap(index, largest);
            index = largest;
            left = index * 2 + 1;
        }

    }

    private void heapInsert(int i) {
        while (i > 0 && comparator.compare(heap.get(i), heap.get((i - 1) / 2)) > 0) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        indexMap.put(o1, j);
        indexMap.put(o2, i);
        heap.set(i, o2);
        heap.set(j, o1);
    }
}
