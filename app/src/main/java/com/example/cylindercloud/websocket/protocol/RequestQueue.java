package com.example.cylindercloud.websocket.protocol;

import java.util.LinkedList;

/**
 * Created by Administrator on 2015-9-21.
 */
public class RequestQueue<E> {
    private LinkedList<E> queue;

    public RequestQueue() {
        queue = new LinkedList<E>();
    }

    public void add(E e) {
        queue.addLast(e);
    }

    public void remove(E e) {
        queue.remove(e);
    }

    public void removeFistElement() {
        queue.removeFirst();
    }

    public void removeLastElement() {
        queue.remove();
    }

    public E getFistElement() {
        return queue.getFirst();
    }

    public E getLastElement() {
        return queue.getLast();
    }
}
