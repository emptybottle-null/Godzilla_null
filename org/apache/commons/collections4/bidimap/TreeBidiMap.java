/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.bidimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;
import org.apache.commons.collections4.keyvalue.UnmodifiableMapEntry;

public class TreeBidiMap<K extends Comparable<K>, V extends Comparable<V>>
implements OrderedBidiMap<K, V>,
Serializable {
    private static final long serialVersionUID = 721969328361807L;
    private transient Node<K, V>[] rootNode = new Node[2];
    private transient int nodeCount = 0;
    private transient int modifications = 0;
    private transient Set<K> keySet;
    private transient Set<V> valuesSet;
    private transient Set<Map.Entry<K, V>> entrySet;
    private transient Inverse inverse = null;

    public TreeBidiMap() {
    }

    public TreeBidiMap(Map<? extends K, ? extends V> map) {
        this();
        this.putAll(map);
    }

    @Override
    public int size() {
        return this.nodeCount;
    }

    @Override
    public boolean isEmpty() {
        return this.nodeCount == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        TreeBidiMap.checkKey(key);
        return this.lookupKey(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        TreeBidiMap.checkValue(value);
        return this.lookupValue(value) != null;
    }

    @Override
    public V get(Object key) {
        TreeBidiMap.checkKey(key);
        Node<K, V> node = this.lookupKey(key);
        return (V)(node == null ? null : node.getValue());
    }

    @Override
    public V put(K key, V value) {
        Object result = this.get(key);
        this.doPut(key, value);
        return (V)result;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<K, V> e : map.entrySet()) {
            this.put((K)((Comparable)e.getKey()), (V)((Comparable)e.getValue()));
        }
    }

    @Override
    public V remove(Object key) {
        return this.doRemoveKey(key);
    }

    @Override
    public void clear() {
        this.modify();
        this.nodeCount = 0;
        this.rootNode[DataElement.KEY.ordinal()] = null;
        this.rootNode[DataElement.VALUE.ordinal()] = null;
    }

    @Override
    public K getKey(Object value) {
        TreeBidiMap.checkValue(value);
        Node<K, V> node = this.lookupValue(value);
        return (K)(node == null ? null : node.getKey());
    }

    @Override
    public K removeValue(Object value) {
        return this.doRemoveValue(value);
    }

    @Override
    public K firstKey() {
        if (this.nodeCount == 0) {
            throw new NoSuchElementException("Map is empty");
        }
        return (K)this.leastNode(this.rootNode[DataElement.KEY.ordinal()], DataElement.KEY).getKey();
    }

    @Override
    public K lastKey() {
        if (this.nodeCount == 0) {
            throw new NoSuchElementException("Map is empty");
        }
        return (K)this.greatestNode(this.rootNode[DataElement.KEY.ordinal()], DataElement.KEY).getKey();
    }

    @Override
    public K nextKey(K key) {
        TreeBidiMap.checkKey(key);
        Node<K, V> node = this.nextGreater(this.lookupKey(key), DataElement.KEY);
        return (K)(node == null ? null : node.getKey());
    }

    @Override
    public K previousKey(K key) {
        TreeBidiMap.checkKey(key);
        Node<K, V> node = this.nextSmaller(this.lookupKey(key), DataElement.KEY);
        return (K)(node == null ? null : node.getKey());
    }

    @Override
    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new KeyView(DataElement.KEY);
        }
        return this.keySet;
    }

    @Override
    public Set<V> values() {
        if (this.valuesSet == null) {
            this.valuesSet = new ValueView(DataElement.KEY);
        }
        return this.valuesSet;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntryView();
        }
        return this.entrySet;
    }

    @Override
    public OrderedMapIterator<K, V> mapIterator() {
        if (this.isEmpty()) {
            return EmptyOrderedMapIterator.emptyOrderedMapIterator();
        }
        return new ViewMapIterator(DataElement.KEY);
    }

    @Override
    public OrderedBidiMap<V, K> inverseBidiMap() {
        if (this.inverse == null) {
            this.inverse = new Inverse();
        }
        return this.inverse;
    }

    @Override
    public boolean equals(Object obj) {
        return this.doEquals(obj, DataElement.KEY);
    }

    @Override
    public int hashCode() {
        return this.doHashCode(DataElement.KEY);
    }

    public String toString() {
        return this.doToString(DataElement.KEY);
    }

    private void doPut(K key, V value) {
        block6: {
            TreeBidiMap.checkKeyAndValue(key, value);
            this.doRemoveKey(key);
            this.doRemoveValue(value);
            Node node = this.rootNode[DataElement.KEY.ordinal()];
            if (node == null) {
                Node<K, V> root = new Node<K, V>(key, value);
                this.rootNode[DataElement.KEY.ordinal()] = root;
                this.rootNode[DataElement.VALUE.ordinal()] = root;
                this.grow();
            } else {
                while (true) {
                    int cmp;
                    if ((cmp = TreeBidiMap.compare(key, node.getKey())) == 0) {
                        throw new IllegalArgumentException("Cannot store a duplicate key (\"" + key + "\") in this Map");
                    }
                    if (cmp < 0) {
                        if (node.getLeft(DataElement.KEY) != null) {
                            node = node.getLeft(DataElement.KEY);
                            continue;
                        }
                        Node<K, V> newNode = new Node<K, V>(key, value);
                        this.insertValue(newNode);
                        node.setLeft((Node)newNode, DataElement.KEY);
                        ((Node)newNode).setParent(node, DataElement.KEY);
                        this.doRedBlackInsert(newNode, DataElement.KEY);
                        this.grow();
                        break block6;
                    }
                    if (node.getRight(DataElement.KEY) == null) break;
                    node = node.getRight(DataElement.KEY);
                }
                Node<K, V> newNode = new Node<K, V>(key, value);
                this.insertValue(newNode);
                node.setRight((Node)newNode, DataElement.KEY);
                ((Node)newNode).setParent(node, DataElement.KEY);
                this.doRedBlackInsert(newNode, DataElement.KEY);
                this.grow();
            }
        }
    }

    private V doRemoveKey(Object key) {
        Node<K, V> node = this.lookupKey(key);
        if (node == null) {
            return null;
        }
        this.doRedBlackDelete(node);
        return (V)node.getValue();
    }

    private K doRemoveValue(Object value) {
        Node<K, V> node = this.lookupValue(value);
        if (node == null) {
            return null;
        }
        this.doRedBlackDelete(node);
        return (K)node.getKey();
    }

    private <T extends Comparable<T>> Node<K, V> lookup(Object data, DataElement dataElement) {
        Node rval = null;
        Node node = this.rootNode[dataElement.ordinal()];
        while (node != null) {
            int cmp = TreeBidiMap.compare((Comparable)data, (Comparable)node.getData(dataElement));
            if (cmp == 0) {
                rval = node;
                break;
            }
            node = cmp < 0 ? node.getLeft(dataElement) : node.getRight(dataElement);
        }
        return rval;
    }

    private Node<K, V> lookupKey(Object key) {
        return this.lookup(key, DataElement.KEY);
    }

    private Node<K, V> lookupValue(Object value) {
        return this.lookup(value, DataElement.VALUE);
    }

    private Node<K, V> nextGreater(Node<K, V> node, DataElement dataElement) {
        Node rval;
        if (node == null) {
            rval = null;
        } else if (node.getRight(dataElement) != null) {
            rval = this.leastNode(node.getRight(dataElement), dataElement);
        } else {
            Node parent = node.getParent(dataElement);
            Node child = node;
            while (parent != null && child == parent.getRight(dataElement)) {
                child = parent;
                parent = parent.getParent(dataElement);
            }
            rval = parent;
        }
        return rval;
    }

    private Node<K, V> nextSmaller(Node<K, V> node, DataElement dataElement) {
        Node rval;
        if (node == null) {
            rval = null;
        } else if (node.getLeft(dataElement) != null) {
            rval = this.greatestNode(node.getLeft(dataElement), dataElement);
        } else {
            Node parent = node.getParent(dataElement);
            Node child = node;
            while (parent != null && child == parent.getLeft(dataElement)) {
                child = parent;
                parent = parent.getParent(dataElement);
            }
            rval = parent;
        }
        return rval;
    }

    private static <T extends Comparable<T>> int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }

    private Node<K, V> leastNode(Node<K, V> node, DataElement dataElement) {
        Node rval = node;
        if (rval != null) {
            while (rval.getLeft(dataElement) != null) {
                rval = rval.getLeft(dataElement);
            }
        }
        return rval;
    }

    private Node<K, V> greatestNode(Node<K, V> node, DataElement dataElement) {
        Node rval = node;
        if (rval != null) {
            while (rval.getRight(dataElement) != null) {
                rval = rval.getRight(dataElement);
            }
        }
        return rval;
    }

    private void copyColor(Node<K, V> from, Node<K, V> to, DataElement dataElement) {
        if (to != null) {
            if (from == null) {
                ((Node)to).setBlack(dataElement);
            } else {
                ((Node)to).copyColor((Node)from, dataElement);
            }
        }
    }

    private static boolean isRed(Node<?, ?> node, DataElement dataElement) {
        return node != null && ((Node)node).isRed(dataElement);
    }

    private static boolean isBlack(Node<?, ?> node, DataElement dataElement) {
        return node == null || ((Node)node).isBlack(dataElement);
    }

    private static void makeRed(Node<?, ?> node, DataElement dataElement) {
        if (node != null) {
            ((Node)node).setRed(dataElement);
        }
    }

    private static void makeBlack(Node<?, ?> node, DataElement dataElement) {
        if (node != null) {
            ((Node)node).setBlack(dataElement);
        }
    }

    private Node<K, V> getGrandParent(Node<K, V> node, DataElement dataElement) {
        return this.getParent(this.getParent(node, dataElement), dataElement);
    }

    private Node<K, V> getParent(Node<K, V> node, DataElement dataElement) {
        return node == null ? null : ((Node)node).getParent(dataElement);
    }

    private Node<K, V> getRightChild(Node<K, V> node, DataElement dataElement) {
        return node == null ? null : ((Node)node).getRight(dataElement);
    }

    private Node<K, V> getLeftChild(Node<K, V> node, DataElement dataElement) {
        return node == null ? null : ((Node)node).getLeft(dataElement);
    }

    private void rotateLeft(Node<K, V> node, DataElement dataElement) {
        Node rightChild = ((Node)node).getRight(dataElement);
        ((Node)node).setRight((Node)rightChild.getLeft(dataElement), dataElement);
        if (rightChild.getLeft(dataElement) != null) {
            ((Node)rightChild.getLeft(dataElement)).setParent((Node)node, dataElement);
        }
        rightChild.setParent((Node)((Node)node).getParent(dataElement), dataElement);
        if (((Node)node).getParent(dataElement) == null) {
            this.rootNode[dataElement.ordinal()] = rightChild;
        } else if (((Node)((Node)node).getParent(dataElement)).getLeft(dataElement) == node) {
            ((Node)((Node)node).getParent(dataElement)).setLeft(rightChild, dataElement);
        } else {
            ((Node)((Node)node).getParent(dataElement)).setRight(rightChild, dataElement);
        }
        rightChild.setLeft((Node)node, dataElement);
        ((Node)node).setParent(rightChild, dataElement);
    }

    private void rotateRight(Node<K, V> node, DataElement dataElement) {
        Node leftChild = ((Node)node).getLeft(dataElement);
        ((Node)node).setLeft((Node)leftChild.getRight(dataElement), dataElement);
        if (leftChild.getRight(dataElement) != null) {
            ((Node)leftChild.getRight(dataElement)).setParent((Node)node, dataElement);
        }
        leftChild.setParent((Node)((Node)node).getParent(dataElement), dataElement);
        if (((Node)node).getParent(dataElement) == null) {
            this.rootNode[dataElement.ordinal()] = leftChild;
        } else if (((Node)((Node)node).getParent(dataElement)).getRight(dataElement) == node) {
            ((Node)((Node)node).getParent(dataElement)).setRight(leftChild, dataElement);
        } else {
            ((Node)((Node)node).getParent(dataElement)).setLeft(leftChild, dataElement);
        }
        leftChild.setRight((Node)node, dataElement);
        ((Node)node).setParent(leftChild, dataElement);
    }

    private void doRedBlackInsert(Node<K, V> insertedNode, DataElement dataElement) {
        Node<K, V> currentNode = insertedNode;
        TreeBidiMap.makeRed(currentNode, dataElement);
        while (currentNode != null && currentNode != this.rootNode[dataElement.ordinal()] && TreeBidiMap.isRed(((Node)currentNode).getParent(dataElement), dataElement)) {
            Node<K, V> y;
            if (((Node)currentNode).isLeftChild(dataElement)) {
                y = this.getRightChild(this.getGrandParent(currentNode, dataElement), dataElement);
                if (TreeBidiMap.isRed(y, dataElement)) {
                    TreeBidiMap.makeBlack(this.getParent(currentNode, dataElement), dataElement);
                    TreeBidiMap.makeBlack(y, dataElement);
                    TreeBidiMap.makeRed(this.getGrandParent(currentNode, dataElement), dataElement);
                    currentNode = this.getGrandParent(currentNode, dataElement);
                    continue;
                }
                if (((Node)currentNode).isRightChild(dataElement)) {
                    currentNode = this.getParent(currentNode, dataElement);
                    this.rotateLeft(currentNode, dataElement);
                }
                TreeBidiMap.makeBlack(this.getParent(currentNode, dataElement), dataElement);
                TreeBidiMap.makeRed(this.getGrandParent(currentNode, dataElement), dataElement);
                if (this.getGrandParent(currentNode, dataElement) == null) continue;
                this.rotateRight(this.getGrandParent(currentNode, dataElement), dataElement);
                continue;
            }
            y = this.getLeftChild(this.getGrandParent(currentNode, dataElement), dataElement);
            if (TreeBidiMap.isRed(y, dataElement)) {
                TreeBidiMap.makeBlack(this.getParent(currentNode, dataElement), dataElement);
                TreeBidiMap.makeBlack(y, dataElement);
                TreeBidiMap.makeRed(this.getGrandParent(currentNode, dataElement), dataElement);
                currentNode = this.getGrandParent(currentNode, dataElement);
                continue;
            }
            if (((Node)currentNode).isLeftChild(dataElement)) {
                currentNode = this.getParent(currentNode, dataElement);
                this.rotateRight(currentNode, dataElement);
            }
            TreeBidiMap.makeBlack(this.getParent(currentNode, dataElement), dataElement);
            TreeBidiMap.makeRed(this.getGrandParent(currentNode, dataElement), dataElement);
            if (this.getGrandParent(currentNode, dataElement) == null) continue;
            this.rotateLeft(this.getGrandParent(currentNode, dataElement), dataElement);
        }
        TreeBidiMap.makeBlack(this.rootNode[dataElement.ordinal()], dataElement);
    }

    private void doRedBlackDelete(Node<K, V> deletedNode) {
        for (DataElement dataElement : DataElement.values()) {
            Node replacement;
            if (((Node)deletedNode).getLeft(dataElement) != null && ((Node)deletedNode).getRight(dataElement) != null) {
                this.swapPosition(this.nextGreater(deletedNode, dataElement), deletedNode, dataElement);
            }
            Node node = replacement = ((Node)deletedNode).getLeft(dataElement) != null ? ((Node)deletedNode).getLeft(dataElement) : ((Node)deletedNode).getRight(dataElement);
            if (replacement != null) {
                replacement.setParent((Node)((Node)deletedNode).getParent(dataElement), dataElement);
                if (((Node)deletedNode).getParent(dataElement) == null) {
                    this.rootNode[dataElement.ordinal()] = replacement;
                } else if (deletedNode == ((Node)((Node)deletedNode).getParent(dataElement)).getLeft(dataElement)) {
                    ((Node)((Node)deletedNode).getParent(dataElement)).setLeft(replacement, dataElement);
                } else {
                    ((Node)((Node)deletedNode).getParent(dataElement)).setRight(replacement, dataElement);
                }
                ((Node)deletedNode).setLeft(null, dataElement);
                ((Node)deletedNode).setRight(null, dataElement);
                ((Node)deletedNode).setParent(null, dataElement);
                if (!TreeBidiMap.isBlack(deletedNode, dataElement)) continue;
                this.doRedBlackDeleteFixup(replacement, dataElement);
                continue;
            }
            if (((Node)deletedNode).getParent(dataElement) == null) {
                this.rootNode[dataElement.ordinal()] = null;
                continue;
            }
            if (TreeBidiMap.isBlack(deletedNode, dataElement)) {
                this.doRedBlackDeleteFixup(deletedNode, dataElement);
            }
            if (((Node)deletedNode).getParent(dataElement) == null) continue;
            if (deletedNode == ((Node)((Node)deletedNode).getParent(dataElement)).getLeft(dataElement)) {
                ((Node)((Node)deletedNode).getParent(dataElement)).setLeft(null, dataElement);
            } else {
                ((Node)((Node)deletedNode).getParent(dataElement)).setRight(null, dataElement);
            }
            ((Node)deletedNode).setParent(null, dataElement);
        }
        this.shrink();
    }

    private void doRedBlackDeleteFixup(Node<K, V> replacementNode, DataElement dataElement) {
        Node<K, V> currentNode = replacementNode;
        while (currentNode != this.rootNode[dataElement.ordinal()] && TreeBidiMap.isBlack(currentNode, dataElement)) {
            Node<K, V> siblingNode;
            if (((Node)currentNode).isLeftChild(dataElement)) {
                siblingNode = this.getRightChild(this.getParent(currentNode, dataElement), dataElement);
                if (TreeBidiMap.isRed(siblingNode, dataElement)) {
                    TreeBidiMap.makeBlack(siblingNode, dataElement);
                    TreeBidiMap.makeRed(this.getParent(currentNode, dataElement), dataElement);
                    this.rotateLeft(this.getParent(currentNode, dataElement), dataElement);
                    siblingNode = this.getRightChild(this.getParent(currentNode, dataElement), dataElement);
                }
                if (TreeBidiMap.isBlack(this.getLeftChild(siblingNode, dataElement), dataElement) && TreeBidiMap.isBlack(this.getRightChild(siblingNode, dataElement), dataElement)) {
                    TreeBidiMap.makeRed(siblingNode, dataElement);
                    currentNode = this.getParent(currentNode, dataElement);
                    continue;
                }
                if (TreeBidiMap.isBlack(this.getRightChild(siblingNode, dataElement), dataElement)) {
                    TreeBidiMap.makeBlack(this.getLeftChild(siblingNode, dataElement), dataElement);
                    TreeBidiMap.makeRed(siblingNode, dataElement);
                    this.rotateRight(siblingNode, dataElement);
                    siblingNode = this.getRightChild(this.getParent(currentNode, dataElement), dataElement);
                }
                this.copyColor(this.getParent(currentNode, dataElement), siblingNode, dataElement);
                TreeBidiMap.makeBlack(this.getParent(currentNode, dataElement), dataElement);
                TreeBidiMap.makeBlack(this.getRightChild(siblingNode, dataElement), dataElement);
                this.rotateLeft(this.getParent(currentNode, dataElement), dataElement);
                currentNode = this.rootNode[dataElement.ordinal()];
                continue;
            }
            siblingNode = this.getLeftChild(this.getParent(currentNode, dataElement), dataElement);
            if (TreeBidiMap.isRed(siblingNode, dataElement)) {
                TreeBidiMap.makeBlack(siblingNode, dataElement);
                TreeBidiMap.makeRed(this.getParent(currentNode, dataElement), dataElement);
                this.rotateRight(this.getParent(currentNode, dataElement), dataElement);
                siblingNode = this.getLeftChild(this.getParent(currentNode, dataElement), dataElement);
            }
            if (TreeBidiMap.isBlack(this.getRightChild(siblingNode, dataElement), dataElement) && TreeBidiMap.isBlack(this.getLeftChild(siblingNode, dataElement), dataElement)) {
                TreeBidiMap.makeRed(siblingNode, dataElement);
                currentNode = this.getParent(currentNode, dataElement);
                continue;
            }
            if (TreeBidiMap.isBlack(this.getLeftChild(siblingNode, dataElement), dataElement)) {
                TreeBidiMap.makeBlack(this.getRightChild(siblingNode, dataElement), dataElement);
                TreeBidiMap.makeRed(siblingNode, dataElement);
                this.rotateLeft(siblingNode, dataElement);
                siblingNode = this.getLeftChild(this.getParent(currentNode, dataElement), dataElement);
            }
            this.copyColor(this.getParent(currentNode, dataElement), siblingNode, dataElement);
            TreeBidiMap.makeBlack(this.getParent(currentNode, dataElement), dataElement);
            TreeBidiMap.makeBlack(this.getLeftChild(siblingNode, dataElement), dataElement);
            this.rotateRight(this.getParent(currentNode, dataElement), dataElement);
            currentNode = this.rootNode[dataElement.ordinal()];
        }
        TreeBidiMap.makeBlack(currentNode, dataElement);
    }

    private void swapPosition(Node<K, V> x, Node<K, V> y, DataElement dataElement) {
        boolean yWasLeftChild;
        Node xFormerParent = ((Node)x).getParent(dataElement);
        Node xFormerLeftChild = ((Node)x).getLeft(dataElement);
        Node xFormerRightChild = ((Node)x).getRight(dataElement);
        Node yFormerParent = ((Node)y).getParent(dataElement);
        Node yFormerLeftChild = ((Node)y).getLeft(dataElement);
        Node yFormerRightChild = ((Node)y).getRight(dataElement);
        boolean xWasLeftChild = ((Node)x).getParent(dataElement) != null && x == ((Node)((Node)x).getParent(dataElement)).getLeft(dataElement);
        boolean bl = yWasLeftChild = ((Node)y).getParent(dataElement) != null && y == ((Node)((Node)y).getParent(dataElement)).getLeft(dataElement);
        if (x == yFormerParent) {
            ((Node)x).setParent((Node)y, dataElement);
            if (yWasLeftChild) {
                ((Node)y).setLeft((Node)x, dataElement);
                ((Node)y).setRight(xFormerRightChild, dataElement);
            } else {
                ((Node)y).setRight((Node)x, dataElement);
                ((Node)y).setLeft(xFormerLeftChild, dataElement);
            }
        } else {
            ((Node)x).setParent(yFormerParent, dataElement);
            if (yFormerParent != null) {
                if (yWasLeftChild) {
                    yFormerParent.setLeft((Node)x, dataElement);
                } else {
                    yFormerParent.setRight((Node)x, dataElement);
                }
            }
            ((Node)y).setLeft(xFormerLeftChild, dataElement);
            ((Node)y).setRight(xFormerRightChild, dataElement);
        }
        if (y == xFormerParent) {
            ((Node)y).setParent((Node)x, dataElement);
            if (xWasLeftChild) {
                ((Node)x).setLeft((Node)y, dataElement);
                ((Node)x).setRight(yFormerRightChild, dataElement);
            } else {
                ((Node)x).setRight((Node)y, dataElement);
                ((Node)x).setLeft(yFormerLeftChild, dataElement);
            }
        } else {
            ((Node)y).setParent(xFormerParent, dataElement);
            if (xFormerParent != null) {
                if (xWasLeftChild) {
                    xFormerParent.setLeft((Node)y, dataElement);
                } else {
                    xFormerParent.setRight((Node)y, dataElement);
                }
            }
            ((Node)x).setLeft(yFormerLeftChild, dataElement);
            ((Node)x).setRight(yFormerRightChild, dataElement);
        }
        if (((Node)x).getLeft(dataElement) != null) {
            ((Node)((Node)x).getLeft(dataElement)).setParent((Node)x, dataElement);
        }
        if (((Node)x).getRight(dataElement) != null) {
            ((Node)((Node)x).getRight(dataElement)).setParent((Node)x, dataElement);
        }
        if (((Node)y).getLeft(dataElement) != null) {
            ((Node)((Node)y).getLeft(dataElement)).setParent((Node)y, dataElement);
        }
        if (((Node)y).getRight(dataElement) != null) {
            ((Node)((Node)y).getRight(dataElement)).setParent((Node)y, dataElement);
        }
        ((Node)x).swapColors((Node)y, dataElement);
        if (this.rootNode[dataElement.ordinal()] == x) {
            this.rootNode[dataElement.ordinal()] = y;
        } else if (this.rootNode[dataElement.ordinal()] == y) {
            this.rootNode[dataElement.ordinal()] = x;
        }
    }

    private static void checkNonNullComparable(Object o, DataElement dataElement) {
        if (o == null) {
            throw new NullPointerException((Object)((Object)dataElement) + " cannot be null");
        }
        if (!(o instanceof Comparable)) {
            throw new ClassCastException((Object)((Object)dataElement) + " must be Comparable");
        }
    }

    private static void checkKey(Object key) {
        TreeBidiMap.checkNonNullComparable(key, DataElement.KEY);
    }

    private static void checkValue(Object value) {
        TreeBidiMap.checkNonNullComparable(value, DataElement.VALUE);
    }

    private static void checkKeyAndValue(Object key, Object value) {
        TreeBidiMap.checkKey(key);
        TreeBidiMap.checkValue(value);
    }

    private void modify() {
        ++this.modifications;
    }

    private void grow() {
        this.modify();
        ++this.nodeCount;
    }

    private void shrink() {
        this.modify();
        --this.nodeCount;
    }

    private void insertValue(Node<K, V> newNode) throws IllegalArgumentException {
        block4: {
            Node node = this.rootNode[DataElement.VALUE.ordinal()];
            while (true) {
                int cmp;
                if ((cmp = TreeBidiMap.compare(newNode.getValue(), node.getValue())) == 0) {
                    throw new IllegalArgumentException("Cannot store a duplicate value (\"" + ((Node)newNode).getData(DataElement.VALUE) + "\") in this Map");
                }
                if (cmp < 0) {
                    if (node.getLeft(DataElement.VALUE) != null) {
                        node = node.getLeft(DataElement.VALUE);
                        continue;
                    }
                    node.setLeft((Node)newNode, DataElement.VALUE);
                    ((Node)newNode).setParent(node, DataElement.VALUE);
                    this.doRedBlackInsert(newNode, DataElement.VALUE);
                    break block4;
                }
                if (node.getRight(DataElement.VALUE) == null) break;
                node = node.getRight(DataElement.VALUE);
            }
            node.setRight((Node)newNode, DataElement.VALUE);
            ((Node)newNode).setParent(node, DataElement.VALUE);
            this.doRedBlackInsert(newNode, DataElement.VALUE);
        }
    }

    private boolean doEquals(Object obj, DataElement dataElement) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map other = (Map)obj;
        if (other.size() != this.size()) {
            return false;
        }
        if (this.nodeCount > 0) {
            try {
                MapIterator<?, ?> it = this.getMapIterator(dataElement);
                while (it.hasNext()) {
                    Object key = it.next();
                    Object value = it.getValue();
                    if (value.equals(other.get(key))) continue;
                    return false;
                }
            } catch (ClassCastException ex) {
                return false;
            } catch (NullPointerException ex) {
                return false;
            }
        }
        return true;
    }

    private int doHashCode(DataElement dataElement) {
        int total = 0;
        if (this.nodeCount > 0) {
            MapIterator<?, ?> it = this.getMapIterator(dataElement);
            while (it.hasNext()) {
                Object key = it.next();
                Object value = it.getValue();
                total += key.hashCode() ^ value.hashCode();
            }
        }
        return total;
    }

    private String doToString(DataElement dataElement) {
        if (this.nodeCount == 0) {
            return "{}";
        }
        StringBuilder buf = new StringBuilder(this.nodeCount * 32);
        buf.append('{');
        MapIterator<?, ?> it = this.getMapIterator(dataElement);
        boolean hasNext = it.hasNext();
        while (hasNext) {
            Object key = it.next();
            Object value = it.getValue();
            buf.append((Object)(key == this ? "(this Map)" : key)).append('=').append((Object)(value == this ? "(this Map)" : value));
            hasNext = it.hasNext();
            if (!hasNext) continue;
            buf.append(", ");
        }
        buf.append('}');
        return buf.toString();
    }

    private MapIterator<?, ?> getMapIterator(DataElement dataElement) {
        switch (dataElement) {
            case KEY: {
                return new ViewMapIterator(DataElement.KEY);
            }
            case VALUE: {
                return new InverseViewMapIterator(DataElement.VALUE);
            }
        }
        throw new IllegalArgumentException();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.rootNode = new Node[2];
        int size = stream.readInt();
        for (int i = 0; i < size; ++i) {
            Comparable k = (Comparable)stream.readObject();
            Comparable v = (Comparable)stream.readObject();
            this.put((K)k, (V)v);
        }
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(this.size());
        for (Map.Entry<K, V> entry : this.entrySet()) {
            stream.writeObject(entry.getKey());
            stream.writeObject(entry.getValue());
        }
    }

    class Inverse
    implements OrderedBidiMap<V, K> {
        private Set<V> inverseKeySet;
        private Set<K> inverseValuesSet;
        private Set<Map.Entry<V, K>> inverseEntrySet;

        Inverse() {
        }

        @Override
        public int size() {
            return TreeBidiMap.this.size();
        }

        @Override
        public boolean isEmpty() {
            return TreeBidiMap.this.isEmpty();
        }

        @Override
        public K get(Object key) {
            return TreeBidiMap.this.getKey(key);
        }

        @Override
        public V getKey(Object value) {
            return TreeBidiMap.this.get(value);
        }

        @Override
        public boolean containsKey(Object key) {
            return TreeBidiMap.this.containsValue(key);
        }

        @Override
        public boolean containsValue(Object value) {
            return TreeBidiMap.this.containsKey(value);
        }

        @Override
        public V firstKey() {
            if (TreeBidiMap.this.nodeCount == 0) {
                throw new NoSuchElementException("Map is empty");
            }
            return TreeBidiMap.this.leastNode(TreeBidiMap.this.rootNode[DataElement.VALUE.ordinal()], DataElement.VALUE).getValue();
        }

        @Override
        public V lastKey() {
            if (TreeBidiMap.this.nodeCount == 0) {
                throw new NoSuchElementException("Map is empty");
            }
            return TreeBidiMap.this.greatestNode(TreeBidiMap.this.rootNode[DataElement.VALUE.ordinal()], DataElement.VALUE).getValue();
        }

        @Override
        public V nextKey(V key) {
            TreeBidiMap.checkKey(key);
            Node node = TreeBidiMap.this.nextGreater(TreeBidiMap.this.lookup(key, DataElement.VALUE), DataElement.VALUE);
            return node == null ? null : node.getValue();
        }

        @Override
        public V previousKey(V key) {
            TreeBidiMap.checkKey(key);
            Node node = TreeBidiMap.this.nextSmaller(TreeBidiMap.this.lookup(key, DataElement.VALUE), DataElement.VALUE);
            return node == null ? null : node.getValue();
        }

        @Override
        public K put(V key, K value) {
            Object result = this.get(key);
            TreeBidiMap.this.doPut(value, key);
            return result;
        }

        @Override
        public void putAll(Map<? extends V, ? extends K> map) {
            for (Map.Entry e : map.entrySet()) {
                this.put((V)((Comparable)e.getKey()), (K)((Comparable)e.getValue()));
            }
        }

        @Override
        public K remove(Object key) {
            return TreeBidiMap.this.removeValue(key);
        }

        @Override
        public V removeValue(Object value) {
            return TreeBidiMap.this.remove(value);
        }

        @Override
        public void clear() {
            TreeBidiMap.this.clear();
        }

        @Override
        public Set<V> keySet() {
            if (this.inverseKeySet == null) {
                this.inverseKeySet = new ValueView(DataElement.VALUE);
            }
            return this.inverseKeySet;
        }

        @Override
        public Set<K> values() {
            if (this.inverseValuesSet == null) {
                this.inverseValuesSet = new KeyView(DataElement.VALUE);
            }
            return this.inverseValuesSet;
        }

        @Override
        public Set<Map.Entry<V, K>> entrySet() {
            if (this.inverseEntrySet == null) {
                this.inverseEntrySet = new InverseEntryView();
            }
            return this.inverseEntrySet;
        }

        @Override
        public OrderedMapIterator<V, K> mapIterator() {
            if (this.isEmpty()) {
                return EmptyOrderedMapIterator.emptyOrderedMapIterator();
            }
            return new InverseViewMapIterator(DataElement.VALUE);
        }

        @Override
        public OrderedBidiMap<K, V> inverseBidiMap() {
            return TreeBidiMap.this;
        }

        @Override
        public boolean equals(Object obj) {
            return TreeBidiMap.this.doEquals(obj, DataElement.VALUE);
        }

        @Override
        public int hashCode() {
            return TreeBidiMap.this.doHashCode(DataElement.VALUE);
        }

        public String toString() {
            return TreeBidiMap.this.doToString(DataElement.VALUE);
        }
    }

    static class Node<K extends Comparable<K>, V extends Comparable<V>>
    implements Map.Entry<K, V>,
    KeyValue<K, V> {
        private final K key;
        private final V value;
        private final Node<K, V>[] leftNode;
        private final Node<K, V>[] rightNode;
        private final Node<K, V>[] parentNode;
        private final boolean[] blackColor;
        private int hashcodeValue;
        private boolean calculatedHashCode;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.leftNode = new Node[2];
            this.rightNode = new Node[2];
            this.parentNode = new Node[2];
            this.blackColor = new boolean[]{true, true};
            this.calculatedHashCode = false;
        }

        private Object getData(DataElement dataElement) {
            switch (dataElement) {
                case KEY: {
                    return this.getKey();
                }
                case VALUE: {
                    return this.getValue();
                }
            }
            throw new IllegalArgumentException();
        }

        private void setLeft(Node<K, V> node, DataElement dataElement) {
            this.leftNode[dataElement.ordinal()] = node;
        }

        private Node<K, V> getLeft(DataElement dataElement) {
            return this.leftNode[dataElement.ordinal()];
        }

        private void setRight(Node<K, V> node, DataElement dataElement) {
            this.rightNode[dataElement.ordinal()] = node;
        }

        private Node<K, V> getRight(DataElement dataElement) {
            return this.rightNode[dataElement.ordinal()];
        }

        private void setParent(Node<K, V> node, DataElement dataElement) {
            this.parentNode[dataElement.ordinal()] = node;
        }

        private Node<K, V> getParent(DataElement dataElement) {
            return this.parentNode[dataElement.ordinal()];
        }

        private void swapColors(Node<K, V> node, DataElement dataElement) {
            int n = dataElement.ordinal();
            this.blackColor[n] = this.blackColor[n] ^ node.blackColor[dataElement.ordinal()];
            int n2 = dataElement.ordinal();
            node.blackColor[n2] = node.blackColor[n2] ^ this.blackColor[dataElement.ordinal()];
            int n3 = dataElement.ordinal();
            this.blackColor[n3] = this.blackColor[n3] ^ node.blackColor[dataElement.ordinal()];
        }

        private boolean isBlack(DataElement dataElement) {
            return this.blackColor[dataElement.ordinal()];
        }

        private boolean isRed(DataElement dataElement) {
            return !this.blackColor[dataElement.ordinal()];
        }

        private void setBlack(DataElement dataElement) {
            this.blackColor[dataElement.ordinal()] = true;
        }

        private void setRed(DataElement dataElement) {
            this.blackColor[dataElement.ordinal()] = false;
        }

        private void copyColor(Node<K, V> node, DataElement dataElement) {
            this.blackColor[dataElement.ordinal()] = node.blackColor[dataElement.ordinal()];
        }

        private boolean isLeftChild(DataElement dataElement) {
            return this.parentNode[dataElement.ordinal()] != null && this.parentNode[dataElement.ordinal()].leftNode[dataElement.ordinal()] == this;
        }

        private boolean isRightChild(DataElement dataElement) {
            return this.parentNode[dataElement.ordinal()] != null && this.parentNode[dataElement.ordinal()].rightNode[dataElement.ordinal()] == this;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V ignored) throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Map.Entry.setValue is not supported");
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry e = (Map.Entry)obj;
            return this.getKey().equals(e.getKey()) && this.getValue().equals(e.getValue());
        }

        @Override
        public int hashCode() {
            if (!this.calculatedHashCode) {
                this.hashcodeValue = this.getKey().hashCode() ^ this.getValue().hashCode();
                this.calculatedHashCode = true;
            }
            return this.hashcodeValue;
        }
    }

    class InverseViewMapEntryIterator
    extends ViewIterator
    implements OrderedIterator<Map.Entry<V, K>> {
        InverseViewMapEntryIterator() {
            super(DataElement.VALUE);
        }

        @Override
        public Map.Entry<V, K> next() {
            return this.createEntry(this.navigateNext());
        }

        @Override
        public Map.Entry<V, K> previous() {
            return this.createEntry(this.navigatePrevious());
        }

        private Map.Entry<V, K> createEntry(Node<K, V> node) {
            return new UnmodifiableMapEntry<Object, Object>(node.getValue(), node.getKey());
        }
    }

    class ViewMapEntryIterator
    extends ViewIterator
    implements OrderedIterator<Map.Entry<K, V>> {
        ViewMapEntryIterator() {
            super(DataElement.KEY);
        }

        @Override
        public Map.Entry<K, V> next() {
            return this.navigateNext();
        }

        @Override
        public Map.Entry<K, V> previous() {
            return this.navigatePrevious();
        }
    }

    class InverseViewMapIterator
    extends ViewIterator
    implements OrderedMapIterator<V, K> {
        public InverseViewMapIterator(DataElement orderType) {
            super(orderType);
        }

        @Override
        public V getKey() {
            if (this.lastReturnedNode == null) {
                throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
            }
            return this.lastReturnedNode.getValue();
        }

        @Override
        public K getValue() {
            if (this.lastReturnedNode == null) {
                throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
            }
            return this.lastReturnedNode.getKey();
        }

        @Override
        public K setValue(K obj) {
            throw new UnsupportedOperationException();
        }

        @Override
        public V next() {
            return this.navigateNext().getValue();
        }

        @Override
        public V previous() {
            return this.navigatePrevious().getValue();
        }
    }

    class ViewMapIterator
    extends ViewIterator
    implements OrderedMapIterator<K, V> {
        ViewMapIterator(DataElement orderType) {
            super(orderType);
        }

        @Override
        public K getKey() {
            if (this.lastReturnedNode == null) {
                throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
            }
            return this.lastReturnedNode.getKey();
        }

        @Override
        public V getValue() {
            if (this.lastReturnedNode == null) {
                throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
            }
            return this.lastReturnedNode.getValue();
        }

        @Override
        public V setValue(V obj) {
            throw new UnsupportedOperationException();
        }

        @Override
        public K next() {
            return this.navigateNext().getKey();
        }

        @Override
        public K previous() {
            return this.navigatePrevious().getKey();
        }
    }

    abstract class ViewIterator {
        private final DataElement orderType;
        Node<K, V> lastReturnedNode;
        private Node<K, V> nextNode;
        private Node<K, V> previousNode;
        private int expectedModifications;

        ViewIterator(DataElement orderType) {
            this.orderType = orderType;
            this.expectedModifications = TreeBidiMap.this.modifications;
            this.nextNode = TreeBidiMap.this.leastNode(TreeBidiMap.this.rootNode[orderType.ordinal()], orderType);
            this.lastReturnedNode = null;
            this.previousNode = null;
        }

        public final boolean hasNext() {
            return this.nextNode != null;
        }

        protected Node<K, V> navigateNext() {
            if (this.nextNode == null) {
                throw new NoSuchElementException();
            }
            if (TreeBidiMap.this.modifications != this.expectedModifications) {
                throw new ConcurrentModificationException();
            }
            this.lastReturnedNode = this.nextNode;
            this.previousNode = this.nextNode;
            this.nextNode = TreeBidiMap.this.nextGreater(this.nextNode, this.orderType);
            return this.lastReturnedNode;
        }

        public boolean hasPrevious() {
            return this.previousNode != null;
        }

        protected Node<K, V> navigatePrevious() {
            if (this.previousNode == null) {
                throw new NoSuchElementException();
            }
            if (TreeBidiMap.this.modifications != this.expectedModifications) {
                throw new ConcurrentModificationException();
            }
            this.nextNode = this.lastReturnedNode;
            if (this.nextNode == null) {
                this.nextNode = TreeBidiMap.this.nextGreater(this.previousNode, this.orderType);
            }
            this.lastReturnedNode = this.previousNode;
            this.previousNode = TreeBidiMap.this.nextSmaller(this.previousNode, this.orderType);
            return this.lastReturnedNode;
        }

        public final void remove() {
            if (this.lastReturnedNode == null) {
                throw new IllegalStateException();
            }
            if (TreeBidiMap.this.modifications != this.expectedModifications) {
                throw new ConcurrentModificationException();
            }
            TreeBidiMap.this.doRedBlackDelete(this.lastReturnedNode);
            ++this.expectedModifications;
            this.lastReturnedNode = null;
            this.previousNode = this.nextNode == null ? TreeBidiMap.this.greatestNode(TreeBidiMap.this.rootNode[this.orderType.ordinal()], this.orderType) : TreeBidiMap.this.nextSmaller(this.nextNode, this.orderType);
        }
    }

    class InverseEntryView
    extends View<Map.Entry<V, K>> {
        InverseEntryView() {
            super(DataElement.VALUE);
        }

        @Override
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry)obj;
            Object value = entry.getValue();
            Node node = TreeBidiMap.this.lookupValue(entry.getKey());
            return node != null && node.getKey().equals(value);
        }

        @Override
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry)obj;
            Object value = entry.getValue();
            Node node = TreeBidiMap.this.lookupValue(entry.getKey());
            if (node != null && node.getKey().equals(value)) {
                TreeBidiMap.this.doRedBlackDelete(node);
                return true;
            }
            return false;
        }

        @Override
        public Iterator<Map.Entry<V, K>> iterator() {
            return new InverseViewMapEntryIterator();
        }
    }

    class EntryView
    extends View<Map.Entry<K, V>> {
        EntryView() {
            super(DataElement.KEY);
        }

        @Override
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry)obj;
            Object value = entry.getValue();
            Node node = TreeBidiMap.this.lookupKey(entry.getKey());
            return node != null && node.getValue().equals(value);
        }

        @Override
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry)obj;
            Object value = entry.getValue();
            Node node = TreeBidiMap.this.lookupKey(entry.getKey());
            if (node != null && node.getValue().equals(value)) {
                TreeBidiMap.this.doRedBlackDelete(node);
                return true;
            }
            return false;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new ViewMapEntryIterator();
        }
    }

    class ValueView
    extends View<V> {
        public ValueView(DataElement orderType) {
            super(orderType);
        }

        @Override
        public Iterator<V> iterator() {
            return new InverseViewMapIterator(this.orderType);
        }

        @Override
        public boolean contains(Object obj) {
            TreeBidiMap.checkNonNullComparable(obj, DataElement.VALUE);
            return TreeBidiMap.this.lookupValue(obj) != null;
        }

        @Override
        public boolean remove(Object o) {
            return TreeBidiMap.this.doRemoveValue(o) != null;
        }
    }

    class KeyView
    extends View<K> {
        public KeyView(DataElement orderType) {
            super(orderType);
        }

        @Override
        public Iterator<K> iterator() {
            return new ViewMapIterator(this.orderType);
        }

        @Override
        public boolean contains(Object obj) {
            TreeBidiMap.checkNonNullComparable(obj, DataElement.KEY);
            return TreeBidiMap.this.lookupKey(obj) != null;
        }

        @Override
        public boolean remove(Object o) {
            return TreeBidiMap.this.doRemoveKey(o) != null;
        }
    }

    abstract class View<E>
    extends AbstractSet<E> {
        final DataElement orderType;

        View(DataElement orderType) {
            this.orderType = orderType;
        }

        @Override
        public int size() {
            return TreeBidiMap.this.size();
        }

        @Override
        public void clear() {
            TreeBidiMap.this.clear();
        }
    }

    static enum DataElement {
        KEY("key"),
        VALUE("value");

        private final String description;

        private DataElement(String description) {
            this.description = description;
        }

        public String toString() {
            return this.description;
        }
    }
}

