/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.struc;

public class LinkedList {
    protected Node head = null;
    protected Node tail = null;
    protected int size = 0;

    protected Node createNode(Object o) {
        return new Node(this, o);
    }

    protected void insertBefore(Node n, Object o) {
        Node p = this.createNode(o);
        if (this.size == 0) {
            this.head = p;
            this.tail = p;
        } else if (n == this.head) {
            p.next = this.head;
            this.head.prev = p;
            this.head = p;
        } else {
            n.prev.next = p;
            p.prev = n.prev;
            n.prev = p;
            p.next = n;
        }
        ++this.size;
    }

    protected void insertAfter(Node n, Object o) {
        Node p = this.createNode(o);
        if (this.size == 0) {
            this.head = p;
            this.tail = p;
        } else if (n == this.tail) {
            p.prev = this.tail;
            this.tail.next = p;
            this.tail = p;
        } else {
            n.next.prev = p;
            p.next = n.next;
            n.next = p;
            p.prev = n;
        }
        ++this.size;
    }

    protected Object removeNode(Node n) {
        if (this.size == 0) {
            return null;
        }
        Object o = n.userObject;
        if (n == this.head) {
            this.head = this.head.next;
            if (this.head == null) {
                this.tail = null;
            } else {
                this.head.prev = null;
            }
        } else if (n == this.tail) {
            this.tail = this.tail.prev;
            this.tail.next = null;
        } else {
            n.prev.next = n.next;
            n.next.prev = n.prev;
        }
        n.list = null;
        --this.size;
        return o;
    }

    public Node getHead() {
        return this.head;
    }

    public Node getTail() {
        return this.tail;
    }

    public void addToHead(Object o) {
        this.insertBefore(this.head, o);
    }

    public void addToTail(Object o) {
        this.insertAfter(this.tail, o);
    }

    public Object removeHead() {
        return this.removeNode(this.head);
    }

    public Object removeTail() {
        return this.removeNode(this.tail);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(6 * this.size);
        sb.append("[");
        Node n = this.head;
        if (n != null) {
            sb.append(n.userObject);
            n = n.next;
        }
        while (n != null) {
            sb.append(", ");
            sb.append(n.userObject);
            n = n.next;
        }
        return sb.append("]").toString();
    }

    public static class Node {
        protected LinkedList list = null;
        protected Node next = null;
        protected Node prev = null;
        protected Object userObject = null;

        protected Node(LinkedList list, Object userObject) {
            this.list = list;
            this.userObject = userObject;
        }

        public LinkedList list() {
            return this.list;
        }

        public Node next() {
            return this.next;
        }

        public Node prev() {
            return this.prev;
        }

        public Object getUserObject() {
            return this.userObject;
        }

        public void setUserObject(Object userObject) {
            this.userObject = userObject;
        }

        public void insertBefore(Object o) {
            this.list.insertBefore(this, o);
        }

        public void insertAfter(Object o) {
            this.list.insertAfter(this, o);
        }

        public void remove() {
            this.list.removeNode(this);
        }
    }
}

