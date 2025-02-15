/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast;

import com.github.javaparser.HasParentNode;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.observer.AstObserver;
import com.github.javaparser.ast.observer.Observable;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.InternalProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NodeList<N extends Node>
implements List<N>,
Iterable<N>,
HasParentNode<NodeList<N>>,
Visitable,
Observable {
    @InternalProperty
    private final List<N> innerList = new ArrayList<N>(0);
    private Node parentNode;
    private final List<AstObserver> observers = new ArrayList<AstObserver>();

    public NodeList() {
        this.parentNode = null;
    }

    public NodeList(Collection<N> n) {
        this.addAll((Collection<? extends N>)n);
    }

    @SafeVarargs
    public NodeList(N ... n) {
        this.addAll((Collection<? extends N>)Arrays.asList(n));
    }

    @Override
    public boolean add(N node) {
        this.notifyElementAdded(this.innerList.size(), (Node)node);
        this.own(node);
        return this.innerList.add(node);
    }

    private void own(N node) {
        if (node == null) {
            return;
        }
        this.setAsParentNodeOf((Node)node);
    }

    public boolean remove(Node node) {
        int index = this.innerList.indexOf(node);
        if (index != -1) {
            this.notifyElementRemoved(index, node);
            node.setParentNode(null);
        }
        return this.innerList.remove(node);
    }

    public N removeFirst() {
        return (N)this.remove(0);
    }

    public N removeLast() {
        return (N)this.remove(this.innerList.size() - 1);
    }

    @SafeVarargs
    public static <X extends Node> NodeList<X> nodeList(X ... nodes) {
        NodeList nodeList = new NodeList();
        Collections.addAll(nodeList, nodes);
        return nodeList;
    }

    public static <X extends Node> NodeList<X> nodeList(Collection<X> nodes) {
        NodeList<X> nodeList = new NodeList<X>();
        nodeList.addAll(nodes);
        return nodeList;
    }

    public static <X extends Node> NodeList<X> nodeList(NodeList<X> nodes) {
        NodeList<X> nodeList = new NodeList<X>();
        nodeList.addAll(nodes);
        return nodeList;
    }

    @Override
    public boolean contains(N node) {
        return this.innerList.contains(node);
    }

    @Override
    public int size() {
        return this.innerList.size();
    }

    @Override
    public N get(int i) {
        return (N)((Node)this.innerList.get(i));
    }

    @Override
    public Iterator<N> iterator() {
        return new NodeListIterator(this.innerList);
    }

    @Override
    public N set(int index, N element) {
        if (index < 0 || index >= this.innerList.size()) {
            throw new IllegalArgumentException("Illegal index. The index should be between 0 and " + this.innerList.size() + " excluded. It is instead " + index);
        }
        if (element == this.innerList.get(index)) {
            return element;
        }
        this.notifyElementReplaced(index, (Node)element);
        ((Node)this.innerList.get(index)).setParentNode(null);
        this.setAsParentNodeOf((Node)element);
        return (N)((Node)this.innerList.set(index, element));
    }

    @Override
    public N remove(int index) {
        this.notifyElementRemoved(index, (Node)this.innerList.get(index));
        Node remove = (Node)this.innerList.remove(index);
        if (remove != null) {
            remove.setParentNode(null);
        }
        return (N)remove;
    }

    @Override
    public boolean isEmpty() {
        return this.innerList.isEmpty();
    }

    @Override
    public void sort(Comparator<? super N> comparator) {
        this.innerList.sort(comparator);
    }

    public void addAll(NodeList<N> otherList) {
        for (Node node : otherList) {
            this.add((N)node);
        }
    }

    @Override
    public void add(int index, N node) {
        this.notifyElementAdded(index, (Node)node);
        this.own(node);
        this.innerList.add(index, node);
    }

    public NodeList<N> addFirst(N node) {
        this.add(0, node);
        return this;
    }

    public NodeList<N> addLast(N node) {
        this.add(node);
        return this;
    }

    public NodeList<N> addAfter(N node, N afterThisNode) {
        int i = this.indexOf(afterThisNode);
        if (i == -1) {
            throw new IllegalArgumentException("Can't find node to insert after.");
        }
        this.add(i + 1, node);
        return this;
    }

    public NodeList<N> addBefore(N node, N beforeThisNode) {
        int i = this.indexOf(beforeThisNode);
        if (i == -1) {
            throw new IllegalArgumentException("Can't find node to insert before.");
        }
        this.add(i, node);
        return this;
    }

    public Optional<N> getFirst() {
        if (this.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.get(0));
    }

    public Optional<N> getLast() {
        if (this.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.get(this.size() - 1));
    }

    @Override
    public Optional<Node> getParentNode() {
        return Optional.ofNullable(this.parentNode);
    }

    @Override
    public NodeList<N> setParentNode(Node parentNode) {
        this.parentNode = parentNode;
        this.setAsParentNodeOf(this.innerList);
        return this;
    }

    @Override
    public Node getParentNodeForChildren() {
        return this.parentNode;
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    @Override
    public void forEach(Consumer<? super N> action) {
        this.innerList.forEach(action);
    }

    @Override
    public boolean contains(Object o) {
        return this.innerList.contains(o);
    }

    @Override
    public Object[] toArray() {
        return this.innerList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.innerList.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof Node) {
            return this.remove((Node)o);
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.innerList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends N> c) {
        c.forEach(this::add);
        return !c.isEmpty();
    }

    @Override
    public boolean addAll(int index, Collection<? extends N> c) {
        for (Node e : c) {
            this.add(index++, (N)e);
        }
        return !c.isEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object e : c) {
            changed = this.remove(e) || changed;
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (Object e : this.stream().filter(it -> !c.contains(it)).toArray()) {
            if (c.contains(e)) continue;
            changed = this.remove(e) || changed;
        }
        return changed;
    }

    @Override
    public void replaceAll(UnaryOperator<N> operator2) {
        for (int i = 0; i < this.size(); ++i) {
            this.set(i, (N)((Node)operator2.apply(this.get(i))));
        }
    }

    @Override
    public boolean removeIf(Predicate<? super N> filter) {
        boolean changed = false;
        for (Object e : this.stream().filter(filter).toArray()) {
            changed = this.remove(e) || changed;
        }
        return changed;
    }

    @Override
    public void clear() {
        while (!this.isEmpty()) {
            this.remove(0);
        }
    }

    @Override
    public boolean equals(Object o) {
        return this.innerList.equals(o);
    }

    @Override
    public int hashCode() {
        return this.innerList.hashCode();
    }

    @Override
    public int indexOf(Object o) {
        return this.innerList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.innerList.lastIndexOf(o);
    }

    @Override
    public ListIterator<N> listIterator() {
        return new NodeListIterator(this.innerList);
    }

    @Override
    public ListIterator<N> listIterator(int index) {
        return new NodeListIterator(this.innerList, index);
    }

    @Override
    public Stream<N> parallelStream() {
        return this.innerList.parallelStream();
    }

    @Override
    public List<N> subList(int fromIndex, int toIndex) {
        return this.innerList.subList(fromIndex, toIndex);
    }

    @Override
    public Spliterator<N> spliterator() {
        return this.innerList.spliterator();
    }

    private void notifyElementAdded(int index, Node nodeAddedOrRemoved) {
        this.observers.forEach((? super T o) -> o.listChange(this, AstObserver.ListChangeType.ADDITION, index, nodeAddedOrRemoved));
    }

    private void notifyElementRemoved(int index, Node nodeAddedOrRemoved) {
        this.observers.forEach((? super T o) -> o.listChange(this, AstObserver.ListChangeType.REMOVAL, index, nodeAddedOrRemoved));
    }

    private void notifyElementReplaced(int index, Node nodeAddedOrRemoved) {
        this.observers.forEach((? super T o) -> o.listReplacement(this, index, (Node)this.get(index), nodeAddedOrRemoved));
    }

    @Override
    public void unregister(AstObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void register(AstObserver observer) {
        if (!this.observers.contains(observer)) {
            this.observers.add(observer);
        }
    }

    @Override
    public boolean isRegistered(AstObserver observer) {
        return this.observers.contains(observer);
    }

    public boolean replace(N old, N replacement) {
        int i = this.indexOf(old);
        if (i == -1) {
            return false;
        }
        this.set(i, replacement);
        return true;
    }

    public boolean isNonEmpty() {
        return !this.isEmpty();
    }

    public void ifNonEmpty(Consumer<? super NodeList<N>> consumer) {
        if (this.isNonEmpty()) {
            consumer.accept(this);
        }
    }

    public static <T extends Node> Collector<T, NodeList<T>, NodeList<T>> toNodeList() {
        return Collector.of(NodeList::new, NodeList::add, (left, right) -> {
            left.addAll((NodeList)right);
            return left;
        }, new Collector.Characteristics[0]);
    }

    private void setAsParentNodeOf(List<? extends Node> childNodes) {
        if (childNodes != null) {
            for (HasParentNode hasParentNode : childNodes) {
                hasParentNode.setParentNode(this.getParentNodeForChildren());
            }
        }
    }

    private void setAsParentNodeOf(Node childNode) {
        if (childNode != null) {
            childNode.setParentNode(this.getParentNodeForChildren());
        }
    }

    public String toString() {
        return this.innerList.stream().map(Node::toString).collect(Collectors.joining(", ", "[", "]"));
    }

    protected class NodeListIterator
    implements ListIterator<N> {
        ListIterator<N> iterator;
        N current = null;

        public NodeListIterator(List<N> list) {
            this.iterator = list.listIterator();
        }

        public NodeListIterator(List<N> list, int index) {
            this.iterator = list.listIterator(index);
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public N next() {
            this.current = (Node)this.iterator.next();
            return this.current;
        }

        @Override
        public boolean hasPrevious() {
            return this.iterator.hasPrevious();
        }

        @Override
        public N previous() {
            this.current = (Node)this.iterator.previous();
            return this.current;
        }

        @Override
        public int nextIndex() {
            return this.iterator.nextIndex();
        }

        @Override
        public int previousIndex() {
            return this.iterator.previousIndex();
        }

        @Override
        public void remove() {
            int index = NodeList.this.innerList.indexOf(this.current);
            if (index != -1) {
                NodeList.this.notifyElementRemoved(index, this.current);
                ((Node)this.current).setParentNode(null);
            }
            this.iterator.remove();
        }

        @Override
        public void set(N n) {
            int index = NodeList.this.innerList.indexOf(this.current);
            if (index < 0 || index >= NodeList.this.innerList.size()) {
                throw new IllegalArgumentException("Illegal index. The index should be between 0 and " + NodeList.this.innerList.size() + " excluded. It is instead " + index);
            }
            if (n != NodeList.this.innerList.get(index)) {
                NodeList.this.notifyElementReplaced(index, n);
                ((Node)NodeList.this.innerList.get(index)).setParentNode(null);
                NodeList.this.setAsParentNodeOf(n);
                this.iterator.set(n);
            }
        }

        @Override
        public void add(N n) {
            NodeList.this.notifyElementAdded(NodeList.this.innerList.size(), n);
            NodeList.this.own(n);
            this.iterator.add(n);
        }

        @Override
        public void forEachRemaining(Consumer<? super N> action) {
            this.iterator.forEachRemaining(action);
        }
    }
}

