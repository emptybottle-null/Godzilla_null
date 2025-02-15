/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.list;

import java.util.AbstractList;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.OrderedIterator;

public class TreeList<E>
extends AbstractList<E> {
    private AVLNode<E> root;
    private int size;

    public TreeList() {
    }

    public TreeList(Collection<? extends E> coll) {
        if (!coll.isEmpty()) {
            this.root = new AVLNode(coll);
            this.size = coll.size();
        }
    }

    @Override
    public E get(int index) {
        this.checkInterval(index, 0, this.size() - 1);
        return this.root.get(index).getValue();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return this.listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator() {
        return this.listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int fromIndex) {
        this.checkInterval(fromIndex, 0, this.size());
        return new TreeListIterator(this, fromIndex);
    }

    @Override
    public int indexOf(Object object) {
        if (this.root == null) {
            return -1;
        }
        return this.root.indexOf(object, ((AVLNode)this.root).relativePosition);
    }

    @Override
    public boolean contains(Object object) {
        return this.indexOf(object) >= 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size()];
        if (this.root != null) {
            this.root.toArray(array, ((AVLNode)this.root).relativePosition);
        }
        return array;
    }

    @Override
    public void add(int index, E obj) {
        ++this.modCount;
        this.checkInterval(index, 0, this.size());
        this.root = this.root == null ? new AVLNode(index, obj, null, null) : this.root.insert(index, obj);
        ++this.size;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        this.modCount += c.size();
        AVLNode cTree = new AVLNode(c);
        this.root = this.root == null ? cTree : ((AVLNode)this.root).addAll(cTree, this.size);
        this.size += c.size();
        return true;
    }

    @Override
    public E set(int index, E obj) {
        this.checkInterval(index, 0, this.size() - 1);
        AVLNode<E> node = this.root.get(index);
        Object result = ((AVLNode)node).value;
        node.setValue(obj);
        return (E)result;
    }

    @Override
    public E remove(int index) {
        ++this.modCount;
        this.checkInterval(index, 0, this.size() - 1);
        E result = this.get(index);
        this.root = this.root.remove(index);
        --this.size;
        return result;
    }

    @Override
    public void clear() {
        ++this.modCount;
        this.root = null;
        this.size = 0;
    }

    private void checkInterval(int index, int startIndex, int endIndex) {
        if (index < startIndex || index > endIndex) {
            throw new IndexOutOfBoundsException("Invalid index:" + index + ", size=" + this.size());
        }
    }

    static class TreeListIterator<E>
    implements ListIterator<E>,
    OrderedIterator<E> {
        private final TreeList<E> parent;
        private AVLNode<E> next;
        private int nextIndex;
        private AVLNode<E> current;
        private int currentIndex;
        private int expectedModCount;

        protected TreeListIterator(TreeList<E> parent, int fromIndex) throws IndexOutOfBoundsException {
            this.parent = parent;
            this.expectedModCount = ((TreeList)parent).modCount;
            this.next = ((TreeList)parent).root == null ? null : ((TreeList)parent).root.get(fromIndex);
            this.nextIndex = fromIndex;
            this.currentIndex = -1;
        }

        protected void checkModCount() {
            if (((TreeList)this.parent).modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasNext() {
            return this.nextIndex < this.parent.size();
        }

        @Override
        public E next() {
            this.checkModCount();
            if (!this.hasNext()) {
                throw new NoSuchElementException("No element at index " + this.nextIndex + ".");
            }
            if (this.next == null) {
                this.next = ((TreeList)this.parent).root.get(this.nextIndex);
            }
            E value = this.next.getValue();
            this.current = this.next;
            this.currentIndex = this.nextIndex++;
            this.next = this.next.next();
            return value;
        }

        @Override
        public boolean hasPrevious() {
            return this.nextIndex > 0;
        }

        @Override
        public E previous() {
            this.checkModCount();
            if (!this.hasPrevious()) {
                throw new NoSuchElementException("Already at start of list.");
            }
            this.next = this.next == null ? ((TreeList)this.parent).root.get(this.nextIndex - 1) : this.next.previous();
            E value = this.next.getValue();
            this.current = this.next;
            this.currentIndex = --this.nextIndex;
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
            if (this.currentIndex == -1) {
                throw new IllegalStateException();
            }
            this.parent.remove(this.currentIndex);
            if (this.nextIndex != this.currentIndex) {
                --this.nextIndex;
            }
            this.next = null;
            this.current = null;
            this.currentIndex = -1;
            ++this.expectedModCount;
        }

        @Override
        public void set(E obj) {
            this.checkModCount();
            if (this.current == null) {
                throw new IllegalStateException();
            }
            this.current.setValue(obj);
        }

        @Override
        public void add(E obj) {
            this.checkModCount();
            this.parent.add(this.nextIndex, obj);
            this.current = null;
            this.currentIndex = -1;
            ++this.nextIndex;
            ++this.expectedModCount;
        }
    }

    static class AVLNode<E> {
        private AVLNode<E> left;
        private boolean leftIsPrevious;
        private AVLNode<E> right;
        private boolean rightIsNext;
        private int height;
        private int relativePosition;
        private E value;

        private AVLNode(int relativePosition, E obj, AVLNode<E> rightFollower, AVLNode<E> leftFollower) {
            this.relativePosition = relativePosition;
            this.value = obj;
            this.rightIsNext = true;
            this.leftIsPrevious = true;
            this.right = rightFollower;
            this.left = leftFollower;
        }

        private AVLNode(Collection<? extends E> coll) {
            this(coll.iterator(), 0, coll.size() - 1, 0, null, null);
        }

        private AVLNode(Iterator<? extends E> iterator, int start, int end, int absolutePositionOfParent, AVLNode<E> prev, AVLNode<E> next) {
            int mid = start + (end - start) / 2;
            if (start < mid) {
                this.left = new AVLNode<E>(iterator, start, mid - 1, mid, prev, this);
            } else {
                this.leftIsPrevious = true;
                this.left = prev;
            }
            this.value = iterator.next();
            this.relativePosition = mid - absolutePositionOfParent;
            if (mid < end) {
                this.right = new AVLNode<E>(iterator, mid + 1, end, mid, this, next);
            } else {
                this.rightIsNext = true;
                this.right = next;
            }
            this.recalcHeight();
        }

        E getValue() {
            return this.value;
        }

        void setValue(E obj) {
            this.value = obj;
        }

        AVLNode<E> get(int index) {
            AVLNode<E> nextNode;
            int indexRelativeToMe = index - this.relativePosition;
            if (indexRelativeToMe == 0) {
                return this;
            }
            AVLNode<E> aVLNode = nextNode = indexRelativeToMe < 0 ? this.getLeftSubTree() : this.getRightSubTree();
            if (nextNode == null) {
                return null;
            }
            return nextNode.get(indexRelativeToMe);
        }

        int indexOf(Object object, int index) {
            int result;
            if (this.getLeftSubTree() != null && (result = this.left.indexOf(object, index + this.left.relativePosition)) != -1) {
                return result;
            }
            if (this.value == null ? this.value == object : this.value.equals(object)) {
                return index;
            }
            if (this.getRightSubTree() != null) {
                return this.right.indexOf(object, index + this.right.relativePosition);
            }
            return -1;
        }

        void toArray(Object[] array, int index) {
            array[index] = this.value;
            if (this.getLeftSubTree() != null) {
                this.left.toArray(array, index + this.left.relativePosition);
            }
            if (this.getRightSubTree() != null) {
                this.right.toArray(array, index + this.right.relativePosition);
            }
        }

        AVLNode<E> next() {
            if (this.rightIsNext || this.right == null) {
                return this.right;
            }
            return super.min();
        }

        AVLNode<E> previous() {
            if (this.leftIsPrevious || this.left == null) {
                return this.left;
            }
            return super.max();
        }

        AVLNode<E> insert(int index, E obj) {
            int indexRelativeToMe = index - this.relativePosition;
            if (indexRelativeToMe <= 0) {
                return this.insertOnLeft(indexRelativeToMe, obj);
            }
            return this.insertOnRight(indexRelativeToMe, obj);
        }

        private AVLNode<E> insertOnLeft(int indexRelativeToMe, E obj) {
            if (this.getLeftSubTree() == null) {
                this.setLeft(new AVLNode<E>(-1, obj, this, this.left), null);
            } else {
                this.setLeft(this.left.insert(indexRelativeToMe, obj), null);
            }
            if (this.relativePosition >= 0) {
                ++this.relativePosition;
            }
            AVLNode<E> ret = this.balance();
            this.recalcHeight();
            return ret;
        }

        private AVLNode<E> insertOnRight(int indexRelativeToMe, E obj) {
            if (this.getRightSubTree() == null) {
                this.setRight(new AVLNode<E>(1, obj, this.right, this), null);
            } else {
                this.setRight(this.right.insert(indexRelativeToMe, obj), null);
            }
            if (this.relativePosition < 0) {
                --this.relativePosition;
            }
            AVLNode<E> ret = this.balance();
            this.recalcHeight();
            return ret;
        }

        private AVLNode<E> getLeftSubTree() {
            return this.leftIsPrevious ? null : this.left;
        }

        private AVLNode<E> getRightSubTree() {
            return this.rightIsNext ? null : this.right;
        }

        private AVLNode<E> max() {
            return this.getRightSubTree() == null ? this : super.max();
        }

        private AVLNode<E> min() {
            return this.getLeftSubTree() == null ? this : super.min();
        }

        AVLNode<E> remove(int index) {
            int indexRelativeToMe = index - this.relativePosition;
            if (indexRelativeToMe == 0) {
                return this.removeSelf();
            }
            if (indexRelativeToMe > 0) {
                this.setRight(this.right.remove(indexRelativeToMe), this.right.right);
                if (this.relativePosition < 0) {
                    ++this.relativePosition;
                }
            } else {
                this.setLeft(this.left.remove(indexRelativeToMe), this.left.left);
                if (this.relativePosition > 0) {
                    --this.relativePosition;
                }
            }
            this.recalcHeight();
            return this.balance();
        }

        private AVLNode<E> removeMax() {
            if (this.getRightSubTree() == null) {
                return this.removeSelf();
            }
            this.setRight(super.removeMax(), this.right.right);
            if (this.relativePosition < 0) {
                ++this.relativePosition;
            }
            this.recalcHeight();
            return this.balance();
        }

        private AVLNode<E> removeMin() {
            if (this.getLeftSubTree() == null) {
                return this.removeSelf();
            }
            this.setLeft(super.removeMin(), this.left.left);
            if (this.relativePosition > 0) {
                --this.relativePosition;
            }
            this.recalcHeight();
            return this.balance();
        }

        private AVLNode<E> removeSelf() {
            if (this.getRightSubTree() == null && this.getLeftSubTree() == null) {
                return null;
            }
            if (this.getRightSubTree() == null) {
                if (this.relativePosition > 0) {
                    this.left.relativePosition += this.relativePosition;
                }
                super.setRight(null, this.right);
                return this.left;
            }
            if (this.getLeftSubTree() == null) {
                this.right.relativePosition = this.right.relativePosition + (this.relativePosition - (this.relativePosition < 0 ? 0 : 1));
                super.setLeft(null, this.left);
                return this.right;
            }
            if (this.heightRightMinusLeft() > 0) {
                AVLNode<E> rightMin = super.min();
                this.value = rightMin.value;
                if (this.leftIsPrevious) {
                    this.left = rightMin.left;
                }
                this.right = super.removeMin();
                if (this.relativePosition < 0) {
                    ++this.relativePosition;
                }
            } else {
                AVLNode<E> leftMax = super.max();
                this.value = leftMax.value;
                if (this.rightIsNext) {
                    this.right = leftMax.right;
                }
                AVLNode<E> leftPrevious = this.left.left;
                this.left = super.removeMax();
                if (this.left == null) {
                    this.left = leftPrevious;
                    this.leftIsPrevious = true;
                }
                if (this.relativePosition > 0) {
                    --this.relativePosition;
                }
            }
            this.recalcHeight();
            return this;
        }

        private AVLNode<E> balance() {
            switch (this.heightRightMinusLeft()) {
                case -1: 
                case 0: 
                case 1: {
                    return this;
                }
                case -2: {
                    if (super.heightRightMinusLeft() > 0) {
                        this.setLeft(super.rotateLeft(), null);
                    }
                    return this.rotateRight();
                }
                case 2: {
                    if (super.heightRightMinusLeft() < 0) {
                        this.setRight(super.rotateRight(), null);
                    }
                    return this.rotateLeft();
                }
            }
            throw new RuntimeException("tree inconsistent!");
        }

        private int getOffset(AVLNode<E> node) {
            if (node == null) {
                return 0;
            }
            return node.relativePosition;
        }

        private int setOffset(AVLNode<E> node, int newOffest) {
            if (node == null) {
                return 0;
            }
            int oldOffset = this.getOffset(node);
            node.relativePosition = newOffest;
            return oldOffset;
        }

        private void recalcHeight() {
            this.height = Math.max(this.getLeftSubTree() == null ? -1 : this.getLeftSubTree().height, this.getRightSubTree() == null ? -1 : this.getRightSubTree().height) + 1;
        }

        private int getHeight(AVLNode<E> node) {
            return node == null ? -1 : node.height;
        }

        private int heightRightMinusLeft() {
            return this.getHeight(this.getRightSubTree()) - this.getHeight(this.getLeftSubTree());
        }

        private AVLNode<E> rotateLeft() {
            AVLNode<E> newTop = this.right;
            AVLNode<E> movedNode = super.getLeftSubTree();
            int newTopPosition = this.relativePosition + this.getOffset(newTop);
            int myNewPosition = -newTop.relativePosition;
            int movedPosition = this.getOffset(newTop) + this.getOffset(movedNode);
            this.setRight(movedNode, newTop);
            super.setLeft(this, null);
            this.setOffset(newTop, newTopPosition);
            this.setOffset(this, myNewPosition);
            this.setOffset(movedNode, movedPosition);
            return newTop;
        }

        private AVLNode<E> rotateRight() {
            AVLNode<E> newTop = this.left;
            AVLNode<E> movedNode = super.getRightSubTree();
            int newTopPosition = this.relativePosition + this.getOffset(newTop);
            int myNewPosition = -newTop.relativePosition;
            int movedPosition = this.getOffset(newTop) + this.getOffset(movedNode);
            this.setLeft(movedNode, newTop);
            super.setRight(this, null);
            this.setOffset(newTop, newTopPosition);
            this.setOffset(this, myNewPosition);
            this.setOffset(movedNode, movedPosition);
            return newTop;
        }

        private void setLeft(AVLNode<E> node, AVLNode<E> previous) {
            this.leftIsPrevious = node == null;
            this.left = this.leftIsPrevious ? previous : node;
            this.recalcHeight();
        }

        private void setRight(AVLNode<E> node, AVLNode<E> next) {
            this.rightIsNext = node == null;
            this.right = this.rightIsNext ? next : node;
            this.recalcHeight();
        }

        private AVLNode<E> addAll(AVLNode<E> otherTree, int currentSize) {
            AVLNode<E> maxNode = this.max();
            AVLNode<E> otherTreeMin = super.min();
            if (otherTree.height > this.height) {
                AVLNode<E> leftSubTree = this.removeMax();
                ArrayDeque<AVLNode<E>> sAncestors = new ArrayDeque<AVLNode<E>>();
                AVLNode<E> s = otherTree;
                int sAbsolutePosition = s.relativePosition + currentSize;
                int sParentAbsolutePosition = 0;
                while (s != null && s.height > this.getHeight(leftSubTree)) {
                    sParentAbsolutePosition = sAbsolutePosition;
                    sAncestors.push(s);
                    s = s.left;
                    if (s == null) continue;
                    sAbsolutePosition += s.relativePosition;
                }
                super.setLeft(leftSubTree, null);
                super.setRight(s, otherTreeMin);
                if (leftSubTree != null) {
                    super.setRight(null, maxNode);
                    leftSubTree.relativePosition -= currentSize - 1;
                }
                if (s != null) {
                    super.setLeft(null, maxNode);
                    s.relativePosition = sAbsolutePosition - currentSize + 1;
                }
                maxNode.relativePosition = currentSize - 1 - sParentAbsolutePosition;
                otherTree.relativePosition += currentSize;
                s = maxNode;
                while (!sAncestors.isEmpty()) {
                    AVLNode sAncestor = (AVLNode)sAncestors.pop();
                    sAncestor.setLeft(s, null);
                    s = sAncestor.balance();
                }
                return s;
            }
            otherTree = super.removeMin();
            ArrayDeque<AVLNode<E>> sAncestors = new ArrayDeque<AVLNode<E>>();
            AVLNode<E> s = this;
            int sAbsolutePosition = s.relativePosition;
            int sParentAbsolutePosition = 0;
            while (s != null && s.height > this.getHeight(otherTree)) {
                sParentAbsolutePosition = sAbsolutePosition;
                sAncestors.push(s);
                s = s.right;
                if (s == null) continue;
                sAbsolutePosition += s.relativePosition;
            }
            super.setRight(otherTree, null);
            super.setLeft(s, maxNode);
            if (otherTree != null) {
                super.setLeft(null, otherTreeMin);
                ++otherTree.relativePosition;
            }
            if (s != null) {
                super.setRight(null, otherTreeMin);
                s.relativePosition = sAbsolutePosition - currentSize;
            }
            otherTreeMin.relativePosition = currentSize - sParentAbsolutePosition;
            s = otherTreeMin;
            while (!sAncestors.isEmpty()) {
                AVLNode sAncestor = (AVLNode)sAncestors.pop();
                sAncestor.setRight(s, null);
                s = sAncestor.balance();
            }
            return s;
        }

        public String toString() {
            return "AVLNode(" + this.relativePosition + ',' + (this.left != null) + ',' + this.value + ',' + (this.getRightSubTree() != null) + ", faedelung " + this.rightIsNext + " )";
        }
    }
}

