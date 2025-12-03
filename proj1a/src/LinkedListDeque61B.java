import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>, Iterable<T> {

    private Node<T> sentinel;
    private int size;


    private static class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> previous;

        //      Node构造
        private Node(T i, Node<T> n, Node<T> p) {
            item = i;
            next = n;
            previous = p;
        }
    }

    //    private void Seque(T x) {
//        Node(x,)
//    }
    public LinkedListDeque61B() {
        sentinel = new Node<T>(null, null, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
        this.size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node<T> newNode = new Node<T>(x, sentinel.next, sentinel);
        sentinel.next.previous = newNode;
        sentinel.next = newNode;
        this.size++;
    }

    @Override
    public void addLast(T x) {
        Node<T> newNode = new Node<T>(x, sentinel, sentinel.previous);
        sentinel.previous.next = newNode;
        sentinel.previous = newNode;
        this.size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node<T> thisNode = this.sentinel.next;
        while (thisNode != sentinel) {
            returnList.add(thisNode.item);
            thisNode = thisNode.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if (this.size == 0) {
            return null;
        } else {
            Node<T> nodeFirst = this.sentinel.next;
            nodeFirst.next.previous = sentinel;
            sentinel.next = nodeFirst.next;
            this.size--;
            return nodeFirst.item;
        }
    }

    @Override
    public T removeLast() {
        if (this.size == 0) {
            return null;
        } else {
            Node<T> nodeLast = this.sentinel.previous;
            nodeLast.previous.next = sentinel;
            sentinel.previous = nodeLast.previous;
            this.size--;
            return nodeLast.item;
        }
    }

    /**
     * returns an iterator for LLDeque
     */
    @Override
    public Iterator<T> iterator() {
        return new LLDequeIterator();
    }


    private class LLDequeIterator implements Iterator<T> {
        private Node<T> current;

        private LLDequeIterator() {
            this.current = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return current != sentinel;
        }

        @Override
        public T next() {
            T returnItem = current.item;
            current = current.next;
            return returnItem;
        }
    }


    @Override
    public T get(int index) {
        Iterator<T> seer = iterator();
        if (index < 0 || index >= this.size) {
            return null;
        } else {
            for (int i = 0; i < index; i++) {
                seer.next();
            }
            return seer.next();
        }
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        } else {
            return getRecursiveHelper(sentinel.next, index);
        }

    }

    private T getRecursiveHelper(Node<T> node, int index) {
        if (index == 0) {
            return node.item;
        } else {
            return getRecursiveHelper(node.next,index - 1);
        }
    }
}