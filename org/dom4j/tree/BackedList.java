/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Node;
import org.dom4j.tree.AbstractBranch;

public class BackedList<T extends Node>
extends ArrayList<T> {
    private List<Node> branchContent;
    private AbstractBranch branch;

    public BackedList(AbstractBranch branch, List<Node> branchContent) {
        this(branch, branchContent, branchContent.size());
    }

    public BackedList(AbstractBranch branch, List<Node> branchContent, int capacity) {
        super(capacity);
        this.branch = branch;
        this.branchContent = branchContent;
    }

    public BackedList(AbstractBranch branch, List<Node> branchContent, List<T> initialContent) {
        super(initialContent);
        this.branch = branch;
        this.branchContent = branchContent;
    }

    @Override
    public boolean add(T node) {
        this.branch.addNode((Node)node);
        return super.add(node);
    }

    @Override
    public void add(int index, T node) {
        int size = this.size();
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index value: " + index + " is less than zero");
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("Index value: " + index + " cannot be greater than the size: " + size);
        }
        int realIndex = size == 0 ? this.branchContent.size() : (index < size ? this.branchContent.indexOf(this.get(index)) : this.branchContent.indexOf(this.get(size - 1)) + 1);
        this.branch.addNode(realIndex, (Node)node);
        super.add(index, node);
    }

    @Override
    public T set(int index, T node) {
        int realIndex = this.branchContent.indexOf(this.get(index));
        if (realIndex < 0) {
            int n = realIndex = index == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (realIndex < this.branchContent.size()) {
            this.branch.removeNode((Node)this.get(index));
            this.branch.addNode(realIndex, (Node)node);
        } else {
            this.branch.removeNode((Node)this.get(index));
            this.branch.addNode((Node)node);
        }
        this.branch.childAdded((Node)node);
        return (T)((Node)super.set(index, node));
    }

    @Override
    public boolean remove(Object object) {
        if (object instanceof Node) {
            this.branch.removeNode((Node)object);
        }
        return super.remove(object);
    }

    @Override
    public T remove(int index) {
        Node node = (Node)super.remove(index);
        if (node != null) {
            this.branch.removeNode(node);
        }
        return (T)node;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        this.ensureCapacity(this.size() + collection.size());
        int count = this.size();
        Iterator<T> iter = collection.iterator();
        while (iter.hasNext()) {
            this.add((T)((Node)iter.next()));
            --count;
        }
        return count != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        this.ensureCapacity(this.size() + collection.size());
        int count = this.size();
        Iterator<T> iter = collection.iterator();
        while (iter.hasNext()) {
            this.add(index++, (T)((Node)iter.next()));
            --count;
        }
        return count != 0;
    }

    @Override
    public void clear() {
        for (Node node : this) {
            this.branchContent.remove(node);
            this.branch.childRemoved(node);
        }
        super.clear();
    }

    public void addLocal(T node) {
        super.add(node);
    }
}

