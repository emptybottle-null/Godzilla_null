/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.dom4j.IllegalAddException;
import org.dom4j.Node;
import org.dom4j.tree.AbstractBranch;

public class ContentListFacade<T extends Node>
extends AbstractList<T> {
    private List<T> branchContent;
    private AbstractBranch branch;

    public ContentListFacade(AbstractBranch branch, List<T> branchContent) {
        this.branch = branch;
        this.branchContent = branchContent;
    }

    @Override
    public boolean add(T node) {
        this.branch.childAdded((Node)node);
        return this.branchContent.add(node);
    }

    @Override
    public void add(int index, T node) {
        this.branch.childAdded((Node)node);
        this.branchContent.add(index, node);
    }

    @Override
    public T set(int index, T node) {
        this.branch.childAdded((Node)node);
        return (T)((Node)this.branchContent.set(index, node));
    }

    @Override
    public boolean remove(Object object) {
        this.branch.childRemoved(this.asNode(object));
        return this.branchContent.remove(object);
    }

    @Override
    public T remove(int index) {
        Node node = (Node)this.branchContent.remove(index);
        if (node != null) {
            this.branch.childRemoved(node);
        }
        return (T)node;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        int count = this.branchContent.size();
        Iterator<T> iter = collection.iterator();
        while (iter.hasNext()) {
            this.add((T)((Node)iter.next()));
            ++count;
        }
        return count == this.branchContent.size();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        int count = this.branchContent.size();
        Iterator<T> iter = collection.iterator();
        while (iter.hasNext()) {
            this.add(index++, (T)((Node)iter.next()));
            --count;
        }
        return count == this.branchContent.size();
    }

    @Override
    public void clear() {
        for (Node node : this) {
            this.branch.childRemoved(node);
        }
        this.branchContent.clear();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object object : c) {
            this.branch.childRemoved(this.asNode(object));
        }
        return this.branchContent.removeAll(c);
    }

    @Override
    public int size() {
        return this.branchContent.size();
    }

    @Override
    public boolean isEmpty() {
        return this.branchContent.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.branchContent.contains(o);
    }

    @Override
    public Object[] toArray() {
        return this.branchContent.toArray();
    }

    @Override
    public Object[] toArray(Object[] a) {
        return this.branchContent.toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.branchContent.containsAll(c);
    }

    @Override
    public T get(int index) {
        return (T)((Node)this.branchContent.get(index));
    }

    @Override
    public int indexOf(Object o) {
        return this.branchContent.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.branchContent.lastIndexOf(o);
    }

    protected Node asNode(Object object) {
        if (object instanceof Node) {
            return (Node)object;
        }
        throw new IllegalAddException("This list must contain instances of Node. Invalid type: " + object);
    }

    protected List<T> getBackingList() {
        return this.branchContent;
    }
}

