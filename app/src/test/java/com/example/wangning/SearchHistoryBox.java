package com.example.wangning;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Administrator on 2018/8/20.
 */
public class SearchHistoryBox implements Queue<String> {

    final int limit = 10;
    Queue<String> queue = new LinkedList<String>();

    @Override
    public boolean add(String s) {
        return queue.add(s);
    }

    @Override
    public boolean offer(String s) {
        if (queue.size() >= limit) {
            //如果超出长度,入队时,先出队
            queue.poll();
        }
        return queue.offer(s);
    }

    @Override
    public String remove() {
        return queue.remove();
    }

    @Override
    public String poll() {
        return queue.poll();
    }

    @Override
    public String element() {
        return queue.element();
    }

    @Override
    public String peek() {
        return queue.peek();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.size() == 0 ? true : false;
    }

    @Override
    public boolean contains(Object o) {
        return queue.contains(o);
    }

    @NonNull
    @Override
    public Iterator<String> iterator() {
        return queue.iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return queue.toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(T[] a) {
        return queue.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return queue.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return queue.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return queue.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return queue.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return queue.retainAll(c);
    }

    @Override
    public void clear() {
        queue.clear();
    }

    public Queue<String> getQueue() {
        return queue;
    }

    public void setQueue(Queue<String> queue) {
        this.queue = queue;
    }
}
