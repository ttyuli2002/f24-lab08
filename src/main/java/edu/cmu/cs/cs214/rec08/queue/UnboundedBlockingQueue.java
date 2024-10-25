package edu.cmu.cs.cs214.rec08.queue;

import java.util.ArrayDeque;
import java.util.Deque;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

/**
 * Modify this class to be thread-safe and be an UnboundedBlockingQueue.
 */
@ThreadSafe
public class UnboundedBlockingQueue<E> implements SimpleQueue<E> {
    @GuardedBy("this")
    private Deque<E> queue = new ArrayDeque<>();

    public UnboundedBlockingQueue() { }

    public synchronized boolean isEmpty() { return queue.isEmpty(); }

    public synchronized int size() { return queue.size(); }

    public synchronized E peek() { return queue.peek(); }

    public synchronized void enqueue(E element) {
        queue.add(element); 
        notifyAll();
    }

    // public E dequeue() { return queue.remove(); }

    /**
     * This method will block if the queue is empty, waiting for an
     * element to be enqueued.
     */
    public synchronized E dequeue() throws InterruptedException {
        // Block while the queue is empty
        while (queue.isEmpty()) {
            wait();
        }
        return queue.remove();
    }

    public synchronized String toString() { return queue.toString(); }
}
