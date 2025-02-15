/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.list;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.OrderedIterator;

public abstract class AbstractLinkedList<E>
implements List<E> {
    transient Node<E> header;
    transient int size;
    transient int modCount;

    protected AbstractLinkedList() {
    }

    protected AbstractLinkedList(Collection<? extends E> coll) {
        this.init();
        this.addAll(coll);
    }

    protected void init() {
        this.header = this.createHeaderNode();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public E get(int index) {
        Node<E> node = this.getNode(index, false);
        return node.getValue();
    }

    @Override
    public Iterator<E> iterator() {
        return this.listIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new LinkedListIterator(this, 0);
    }

    @Override
    public ListIterator<E> listIterator(int fromIndex) {
        return new LinkedListIterator(this, fromIndex);
    }

    @Override
    public int indexOf(Object value) {
        int i = 0;
        Node node = this.header.next;
        while (node != this.header) {
            if (this.isEqualValue(node.getValue(), value)) {
                return i;
            }
            ++i;
            node = node.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        int i = this.size - 1;
        Node node = this.header.previous;
        while (node != this.header) {
            if (this.isEqualValue(node.getValue(), value)) {
                return i;
            }
            --i;
            node = node.previous;
        }
        return -1;
    }

    @Override
    public boolean contains(Object value) {
        return this.indexOf(value) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        for (Object o : coll) {
            if (this.contains(o)) continue;
            return false;
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        return this.toArray(new Object[this.size]);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < this.size) {
            Class<?> componentType = array.getClass().getComponentType();
            array = (Object[])Array.newInstance(componentType, this.size);
        }
        int i = 0;
        Node node = this.header.next;
        while (node != this.header) {
            array[i] = node.getValue();
            node = node.next;
            ++i;
        }
        if (array.length > this.size) {
            array[this.size] = null;
        }
        return array;
    }

    @Override
    public List<E> subList(int fromIndexInclusive, int toIndexExclusive) {
        return new LinkedSubList(this, fromIndexInclusive, toIndexExclusive);
    }

    @Override
    public boolean add(E value) {
        this.addLast(value);
        return true;
    }

    @Override
    public void add(int index, E value) {
        Node<E> node = this.getNode(index, true);
        this.addNodeBefore(node, value);
    }

    @Override
    public boolean addAll(Collection<? extends E> coll) {
        return this.addAll(this.size, coll);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> coll) {
        Node<E> node = this.getNode(index, true);
        for (E e : coll) {
            this.addNodeBefore(node, e);
        }
        return true;
    }

    @Override
    public E remove(int index) {
        Node<E> node = this.getNode(index, false);
        E oldValue = node.getValue();
        this.removeNode(node);
        return oldValue;
    }

    @Override
    public boolean remove(Object value) {
        Node node = this.header.next;
        while (node != this.header) {
            if (this.isEqualValue(node.getValue(), value)) {
                this.removeNode(node);
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        boolean modified = false;
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            if (!coll.contains(it.next())) continue;
            it.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        boolean modified = false;
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            if (coll.contains(it.next())) continue;
            it.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public E set(int index, E value) {
        Node<E> node = this.getNode(index, false);
        E oldValue = node.getValue();
        this.updateNode(node, value);
        return oldValue;
    }

    @Override
    public void clear() {
        this.removeAllNodes();
    }

    public E getFirst() {
        Node node = this.header.next;
        if (node == this.header) {
            throw new NoSuchElementException();
        }
        return node.getValue();
    }

    public E getLast() {
        Node node = this.header.previous;
        if (node == this.header) {
            throw new NoSuchElementException();
        }
        return node.getValue();
    }

    public boolean addFirst(E o) {
        this.addNodeAfter(this.header, o);
        return true;
    }

    public boolean addLast(E o) {
        this.addNodeBefore(this.header, o);
        return true;
    }

    public E removeFirst() {
        Node node = this.header.next;
        if (node == this.header) {
            throw new NoSuchElementException();
        }
        Object oldValue = node.getValue();
        this.removeNode(node);
        return oldValue;
    }

    public E removeLast() {
        Node node = this.header.previous;
        if (node == this.header) {
            throw new NoSuchElementException();
        }
        Object oldValue = node.getValue();
        this.removeNode(node);
        return oldValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List other = (List)obj;
        if (other.size() != this.size()) {
            return false;
        }
        ListIterator<E> it1 = this.listIterator();
        ListIterator it2 = other.listIterator();
        while (it1.hasNext() && it2.hasNext()) {
            E o1 = it1.next();
            Object o2 = it2.next();
            if (o1 != null ? o1.equals(o2) : o2 == null) continue;
            return false;
        }
        return !it1.hasNext() && !it2.hasNext();
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (E e : this) {
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        }
        return hashCode;
    }

    public String toString() {
        if (this.size() == 0) {
            return "[]";
        }
        StringBuilder buf = new StringBuilder(16 * this.size());
        buf.append('[');
        Iterator<E> it = this.iterator();
        boolean hasNext = it.hasNext();
        while (hasNext) {
            E value = it.next();
            buf.append((Object)(value == this ? "(this Collection)" : value));
            hasNext = it.hasNext();
            if (!hasNext) continue;
            buf.append(", ");
        }
        buf.append(']');
        return buf.toString();
    }

    protected boolean isEqualValue(Object value1, Object value2) {
        return value1 == value2 || value1 != null && value1.equals(value2);
    }

    protected void updateNode(Node<E> node, E value) {
        node.setValue(value);
    }

    protected Node<E> createHeaderNode() {
        return new Node();
    }

    protected Node<E> createNode(E value) {
        return new Node<E>(value);
    }

    protected void addNodeBefore(Node<E> node, E value) {
        Node<E> newNode = this.createNode(value);
        this.addNode(newNode, node);
    }

    protected void addNodeAfter(Node<E> node, E value) {
        Node<E> newNode = this.createNode(value);
        this.addNode(newNode, node.next);
    }

    protected void addNode(Node<E> nodeToInsert, Node<E> insertBeforeNode) {
        nodeToInsert.next = insertBeforeNode;
        nodeToInsert.previous = insertBeforeNode.previous;
        insertBeforeNode.previous.next = nodeToInsert;
        insertBeforeNode.previous = nodeToInsert;
        ++this.size;
        ++this.modCount;
    }

    protected void removeNode(Node<E> node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
        --this.size;
        ++this.modCount;
    }

    protected void removeAllNodes() {
        this.header.next = this.header;
        this.header.previous = this.header;
        this.size = 0;
        ++this.modCount;
    }

    protected Node<E> getNode(int index, boolean endMarkerAllowed) throws IndexOutOfBoundsException {
        Node<E> node;
        if (index < 0) {
            throw new IndexOutOfBoundsException("Couldn't get the node: index (" + index + ") less than zero.");
        }
        if (!endMarkerAllowed && index == this.size) {
            throw new IndexOutOfBoundsException("Couldn't get the node: index (" + index + ") is the size of the list.");
        }
        if (index > this.size) {
            throw new IndexOutOfBoundsException("Couldn't get the node: index (" + index + ") greater than the size of the list (" + this.size + ").");
        }
        if (index < this.size / 2) {
            node = this.header.next;
            for (int currentIndex = 0; currentIndex < index; ++currentIndex) {
                node = node.next;
            }
        } else {
            node = this.header;
            for (int currentIndex = this.size; currentIndex > index; --currentIndex) {
                node = node.previous;
            }
        }
        return node;
    }

    protected Iterator<E> createSubListIterator(LinkedSubList<E> subList) {
        return this.createSubListListIterator(subList, 0);
    }

    protected ListIterator<E> createSubListListIterator(LinkedSubList<E> subList, int fromIndex) {
        return new LinkedSubListIterator<E>(subList, fromIndex);
    }

    protected void doWriteObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeInt(this.size());
        for (E e : this) {
            outputStream.writeObject(e);
        }
    }

    protected void doReadObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        this.init();
        int size = inputStream.readInt();
        for (int i = 0; i < size; ++i) {
            this.add(inputStream.readObject());
        }
    }

    protected static class LinkedSubList<E>
    extends AbstractList<E> {
        AbstractLinkedList<E> parent;
        int offset;
        int size;
        int expectedModCount;

        protected LinkedSubList(AbstractLinkedList<E> parent, int fromIndex, int toIndex) {
            if (fromIndex < 0) {
                throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
            }
            if (toIndex > parent.size()) {
                throw new IndexOutOfBoundsException("toIndex = " + toIndex);
            }
            if (fromIndex > toIndex) {
                throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
            }
            this.parent = parent;
            this.offset = fromIndex;
            this.size = toIndex - fromIndex;
            this.expectedModCount = parent.modCount;
        }

        @Override
        public int size() {
            this.checkModCount();
            return this.size;
        }

        @Override
        public E get(int index) {
            this.rangeCheck(index, this.size);
            this.checkModCount();
            return this.parent.get(index + this.offset);
        }

        @Override
        public void add(int index, E obj) {
            this.rangeCheck(index, this.size + 1);
            this.checkModCount();
            this.parent.add(index + this.offset, obj);
            this.expectedModCount = this.parent.modCount;
            ++this.size;
            ++this.modCount;
        }

        @Override
        public E remove(int index) {
            this.rangeCheck(index, this.size);
            this.checkModCount();
            E result = this.parent.remove(index + this.offset);
            this.expectedModCount = this.parent.modCount;
            --this.size;
            ++this.modCount;
            return result;
        }

        @Override
        public boolean addAll(Collection<? extends E> coll) {
            return this.addAll(this.size, coll);
        }

        @Override
        public boolean addAll(int index, Collection<? extends E> coll) {
            this.rangeCheck(index, this.size + 1);
            int cSize = coll.size();
            if (cSize == 0) {
                return false;
            }
            this.checkModCount();
            this.parent.addAll(this.offset + index, coll);
            this.expectedModCount = this.parent.modCount;
            this.size += cSize;
            ++this.modCount;
            return true;
        }

        @Override
        public E set(int index, E obj) {
            this.rangeCheck(index, this.size);
            this.checkModCount();
            return this.parent.set(index + this.offset, obj);
        }

        @Override
        public void clear() {
            this.checkModCount();
            Iterator<E> it = this.iterator();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
        }

        @Override
        public Iterator<E> iterator() {
            this.checkModCount();
            return this.parent.createSubListIterator(this);
        }

        @Override
        public ListIterator<E> listIterator(int index) {
            this.rangeCheck(index, this.size + 1);
            this.checkModCount();
            return this.parent.createSubListListIterator(this, index);
        }

        @Override
        public List<E> subList(int fromIndexInclusive, int toIndexExclusive) {
            return new LinkedSubList<E>(this.parent, fromIndexInclusive + this.offset, toIndexExclusive + this.offset);
        }

        protected void rangeCheck(int index, int beyond) {
            if (index < 0 || index >= beyond) {
                throw new IndexOutOfBoundsException("Index '" + index + "' out of bounds for size '" + this.size + "'");
            }
        }

        protected void checkModCount() {
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    protected static class LinkedSubListIterator<E>
    extends LinkedListIterator<E> {
        protected final LinkedSubList<E> sub;

        protected LinkedSubListIterator(LinkedSubList<E> sub, int startIndex) {
            super(sub.parent, startIndex + sub.offset);
            this.sub = sub;
        }

        @Override
        public boolean hasNext() {
            return this.nextIndex() < this.sub.size;
        }

        @Override
        public boolean hasPrevious() {
            return this.previousIndex() >= 0;
        }

        @Override
        public int nextIndex() {
            return super.nextIndex() - this.sub.offset;
        }

        @Override
        public void add(E obj) {
            super.add(obj);
            this.sub.expectedModCount = this.parent.modCount;
            ++this.sub.size;
        }

        @Override
        public void remove() {
            super.remove();
            this.sub.expectedModCount = this.parent.modCount;
            --this.sub.size;
        }
    }

    protected static class LinkedListIterator<E>
    implements ListIterator<E>,
    OrderedIterator<E> {
        protected final AbstractLinkedList<E> parent;
        protected Node<E> next;
        protected int nextIndex;
        protected Node<E> current;
        protected int expectedModCount;

        protected LinkedListIterator(AbstractLinkedList<E> parent, int fromIndex) throws IndexOutOfBoundsException {
            this.parent = parent;
            this.expectedModCount = parent.modCount;
            this.next = parent.getNode(fromIndex, true);
            this.nextIndex = fromIndex;
        }

        protected void checkModCount() {
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        protected Node<E> getLastNodeReturned() throws IllegalStateException {
            if (this.current == null) {
                throw new IllegalStateException();
            }
            return this.current;
        }

        @Override
        public boolean hasNext() {
            return this.next != this.parent.header;
        }

        @Override
        public E next() {
            this.checkModCount();
            if (!this.hasNext()) {
                throw new NoSuchElementException("No element at index " + this.nextIndex + ".");
            }
            E value = this.next.getValue();
            this.current = this.next;
            this.next = this.next.next;
            ++this.nextIndex;
            return value;
        }

        @Override
        public boolean hasPrevious() {
            return this.next.previous != this.parent.header;
        }

        @Override
        public E previous() {
            this.checkModCount();
            if (!this.hasPrevious()) {
                throw new NoSuchElementException("Already at start of list.");
            }
            this.next = this.next.previous;
            E value = this.next.getValue();
            this.current = this.next;
            --this.nextIndex;
            return value;
        }

        @Override
        public int nextIndex() {
            return this.nextIndex;
        }

        @Override
        public int previousIndex() {
            return this.nextIndex() - 1;
        }

        @Override
        public void remove() {
            this.checkModCount();
            if (this.current == this.next) {
                this.next = this.next.next;
                this.parent.removeNode(this.getLastNodeReturned());
            } else {
                this.parent.removeNode(this.getLastNodeReturned());
                --this.nextIndex;
            }
            this.current = null;
            ++this.expectedModCount;
        }

        @Override
        public void set(E obj) {
            this.checkModCount();
            this.getLastNodeReturned().setValue(obj);
        }

        @Override
        public void add(E obj) {
            this.checkModCount();
            this.parent.addNodeBefore(this.next, obj);
            this.current = null;
            ++this.nextIndex;
            ++this.expectedModCount;
        }
    }

    protected static class Node<E> {
        protected Node<E> previous;
        protected Node<E> next;
        protected E value;

        protected Node() {
            this.previous = this;
            this.next = this;
        }

        protected Node(E value) {
            this.value = value;
        }

        protected Node(Node<E> previous, Node<E> next, E value) {
            this.previous = previous;
            this.next = next;
            this.value = value;
        }

        protected E getValue() {
            return this.value;
        }

        protected void setValue(E value) {
            this.value = value;
        }

        protected Node<E> getPreviousNode() {
            return this.previous;
        }

        protected void setPreviousNode(Node<E> previous) {
            this.previous = previous;
        }

        protected Node<E> getNextNode() {
            return this.next;
        }

        protected void setNextNode(Node<E> next) {
            this.next = next;
        }
    }
}

