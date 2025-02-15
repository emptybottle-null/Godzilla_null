/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeListIterator
implements Iterator<Node> {
    private final NodeList nodeList;
    private int index = 0;

    public NodeListIterator(Node node) {
        if (node == null) {
            throw new NullPointerException("Node must not be null.");
        }
        this.nodeList = node.getChildNodes();
    }

    public NodeListIterator(NodeList nodeList) {
        if (nodeList == null) {
            throw new NullPointerException("NodeList must not be null.");
        }
        this.nodeList = nodeList;
    }

    @Override
    public boolean hasNext() {
        return this.nodeList != null && this.index < this.nodeList.getLength();
    }

    @Override
    public Node next() {
        if (this.nodeList != null && this.index < this.nodeList.getLength()) {
            return this.nodeList.item(this.index++);
        }
        throw new NoSuchElementException("underlying nodeList has no more elements");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() method not supported for a NodeListIterator.");
    }
}

