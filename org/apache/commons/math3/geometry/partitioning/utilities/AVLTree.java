/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning.utilities;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class AVLTree<T extends Comparable<T>> {
    private Node top = null;

    public void insert(T element) {
        if (element != null) {
            if (this.top == null) {
                this.top = new Node(this, element, null);
            } else {
                this.top.insert(element);
            }
        }
    }

    public boolean delete(T element) {
        if (element != null) {
            for (Node node = this.getNotSmaller(element); node != null; node = node.getNext()) {
                if (node.element == element) {
                    node.delete();
                    return true;
                }
                if (node.element.compareTo(element) <= 0) continue;
                return false;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return this.top == null;
    }

    public int size() {
        return this.top == null ? 0 : this.top.size();
    }

    public Node getSmallest() {
        return this.top == null ? null : this.top.getSmallest();
    }

    public Node getLargest() {
        return this.top == null ? null : this.top.getLargest();
    }

    public Node getNotSmaller(T reference) {
        Node candidate = null;
        Node node = this.top;
        while (node != null) {
            if (node.element.compareTo(reference) < 0) {
                if (node.right == null) {
                    return candidate;
                }
                node = node.right;
                continue;
            }
            candidate = node;
            if (node.left == null) {
                return candidate;
            }
            node = node.left;
        }
        return null;
    }

    public Node getNotLarger(T reference) {
        Node candidate = null;
        Node node = this.top;
        while (node != null) {
            if (node.element.compareTo(reference) > 0) {
                if (node.left == null) {
                    return candidate;
                }
                node = node.left;
                continue;
            }
            candidate = node;
            if (node.right == null) {
                return candidate;
            }
            node = node.right;
        }
        return null;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Node {
        private T element;
        private Node left;
        private Node right;
        private Node parent;
        private Skew skew;
        final /* synthetic */ AVLTree this$0;

        Node(T element, Node parent) {
            this.this$0 = var1_1;
            this.element = element;
            this.left = null;
            this.right = null;
            this.parent = parent;
            this.skew = Skew.BALANCED;
        }

        public T getElement() {
            return this.element;
        }

        int size() {
            return 1 + (this.left == null ? 0 : this.left.size()) + (this.right == null ? 0 : this.right.size());
        }

        Node getSmallest() {
            Node node = this;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        Node getLargest() {
            Node node = this;
            while (node.right != null) {
                node = node.right;
            }
            return node;
        }

        public Node getPrevious() {
            Node node;
            if (this.left != null && (node = this.left.getLargest()) != null) {
                return node;
            }
            node = this;
            while (node.parent != null) {
                if (node != node.parent.left) {
                    return node.parent;
                }
                node = node.parent;
            }
            return null;
        }

        public Node getNext() {
            Node node;
            if (this.right != null && (node = this.right.getSmallest()) != null) {
                return node;
            }
            node = this;
            while (node.parent != null) {
                if (node != node.parent.right) {
                    return node.parent;
                }
                node = node.parent;
            }
            return null;
        }

        boolean insert(T newElement) {
            if (newElement.compareTo(this.element) < 0) {
                if (this.left == null) {
                    this.left = new Node(this.this$0, newElement, this);
                    return this.rebalanceLeftGrown();
                }
                return this.left.insert(newElement) ? this.rebalanceLeftGrown() : false;
            }
            if (this.right == null) {
                this.right = new Node(this.this$0, newElement, this);
                return this.rebalanceRightGrown();
            }
            return this.right.insert(newElement) ? this.rebalanceRightGrown() : false;
        }

        public void delete() {
            if (this.parent == null && this.left == null && this.right == null) {
                this.element = null;
                this.this$0.top = null;
            } else {
                Node child;
                boolean leftShrunk;
                Node node;
                if (this.left == null && this.right == null) {
                    node = this;
                    this.element = null;
                    leftShrunk = node == node.parent.left;
                    child = null;
                } else {
                    node = this.left != null ? this.left.getLargest() : this.right.getSmallest();
                    this.element = node.element;
                    leftShrunk = node == node.parent.left;
                    child = node.left != null ? node.left : node.right;
                }
                node = node.parent;
                if (leftShrunk) {
                    node.left = child;
                } else {
                    node.right = child;
                }
                if (child != null) {
                    child.parent = node;
                }
                while (leftShrunk ? node.rebalanceLeftShrunk() : node.rebalanceRightShrunk()) {
                    if (node.parent == null) {
                        return;
                    }
                    leftShrunk = node == node.parent.left;
                    node = node.parent;
                }
            }
        }

        private boolean rebalanceLeftGrown() {
            switch (this.skew) {
                case LEFT_HIGH: {
                    if (this.left.skew == Skew.LEFT_HIGH) {
                        this.rotateCW();
                        this.skew = Skew.BALANCED;
                        this.right.skew = Skew.BALANCED;
                    } else {
                        Skew s = this.left.right.skew;
                        this.left.rotateCCW();
                        this.rotateCW();
                        switch (s) {
                            case LEFT_HIGH: {
                                this.left.skew = Skew.BALANCED;
                                this.right.skew = Skew.RIGHT_HIGH;
                                break;
                            }
                            case RIGHT_HIGH: {
                                this.left.skew = Skew.LEFT_HIGH;
                                this.right.skew = Skew.BALANCED;
                                break;
                            }
                            default: {
                                this.left.skew = Skew.BALANCED;
                                this.right.skew = Skew.BALANCED;
                            }
                        }
                        this.skew = Skew.BALANCED;
                    }
                    return false;
                }
                case RIGHT_HIGH: {
                    this.skew = Skew.BALANCED;
                    return false;
                }
            }
            this.skew = Skew.LEFT_HIGH;
            return true;
        }

        private boolean rebalanceRightGrown() {
            switch (this.skew) {
                case LEFT_HIGH: {
                    this.skew = Skew.BALANCED;
                    return false;
                }
                case RIGHT_HIGH: {
                    if (this.right.skew == Skew.RIGHT_HIGH) {
                        this.rotateCCW();
                        this.skew = Skew.BALANCED;
                        this.left.skew = Skew.BALANCED;
                    } else {
                        Skew s = this.right.left.skew;
                        this.right.rotateCW();
                        this.rotateCCW();
                        switch (s) {
                            case LEFT_HIGH: {
                                this.left.skew = Skew.BALANCED;
                                this.right.skew = Skew.RIGHT_HIGH;
                                break;
                            }
                            case RIGHT_HIGH: {
                                this.left.skew = Skew.LEFT_HIGH;
                                this.right.skew = Skew.BALANCED;
                                break;
                            }
                            default: {
                                this.left.skew = Skew.BALANCED;
                                this.right.skew = Skew.BALANCED;
                            }
                        }
                        this.skew = Skew.BALANCED;
                    }
                    return false;
                }
            }
            this.skew = Skew.RIGHT_HIGH;
            return true;
        }

        private boolean rebalanceLeftShrunk() {
            switch (this.skew) {
                case LEFT_HIGH: {
                    this.skew = Skew.BALANCED;
                    return true;
                }
                case RIGHT_HIGH: {
                    if (this.right.skew == Skew.RIGHT_HIGH) {
                        this.rotateCCW();
                        this.skew = Skew.BALANCED;
                        this.left.skew = Skew.BALANCED;
                        return true;
                    }
                    if (this.right.skew == Skew.BALANCED) {
                        this.rotateCCW();
                        this.skew = Skew.LEFT_HIGH;
                        this.left.skew = Skew.RIGHT_HIGH;
                        return false;
                    }
                    Skew s = this.right.left.skew;
                    this.right.rotateCW();
                    this.rotateCCW();
                    switch (s) {
                        case LEFT_HIGH: {
                            this.left.skew = Skew.BALANCED;
                            this.right.skew = Skew.RIGHT_HIGH;
                            break;
                        }
                        case RIGHT_HIGH: {
                            this.left.skew = Skew.LEFT_HIGH;
                            this.right.skew = Skew.BALANCED;
                            break;
                        }
                        default: {
                            this.left.skew = Skew.BALANCED;
                            this.right.skew = Skew.BALANCED;
                        }
                    }
                    this.skew = Skew.BALANCED;
                    return true;
                }
            }
            this.skew = Skew.RIGHT_HIGH;
            return false;
        }

        private boolean rebalanceRightShrunk() {
            switch (this.skew) {
                case RIGHT_HIGH: {
                    this.skew = Skew.BALANCED;
                    return true;
                }
                case LEFT_HIGH: {
                    if (this.left.skew == Skew.LEFT_HIGH) {
                        this.rotateCW();
                        this.skew = Skew.BALANCED;
                        this.right.skew = Skew.BALANCED;
                        return true;
                    }
                    if (this.left.skew == Skew.BALANCED) {
                        this.rotateCW();
                        this.skew = Skew.RIGHT_HIGH;
                        this.right.skew = Skew.LEFT_HIGH;
                        return false;
                    }
                    Skew s = this.left.right.skew;
                    this.left.rotateCCW();
                    this.rotateCW();
                    switch (s) {
                        case LEFT_HIGH: {
                            this.left.skew = Skew.BALANCED;
                            this.right.skew = Skew.RIGHT_HIGH;
                            break;
                        }
                        case RIGHT_HIGH: {
                            this.left.skew = Skew.LEFT_HIGH;
                            this.right.skew = Skew.BALANCED;
                            break;
                        }
                        default: {
                            this.left.skew = Skew.BALANCED;
                            this.right.skew = Skew.BALANCED;
                        }
                    }
                    this.skew = Skew.BALANCED;
                    return true;
                }
            }
            this.skew = Skew.LEFT_HIGH;
            return false;
        }

        private void rotateCW() {
            Object tmpElt = this.element;
            this.element = this.left.element;
            this.left.element = tmpElt;
            Node tmpNode = this.left;
            this.left = tmpNode.left;
            tmpNode.left = tmpNode.right;
            tmpNode.right = this.right;
            this.right = tmpNode;
            if (this.left != null) {
                this.left.parent = this;
            }
            if (this.right.right != null) {
                this.right.right.parent = this.right;
            }
        }

        private void rotateCCW() {
            Object tmpElt = this.element;
            this.element = this.right.element;
            this.right.element = tmpElt;
            Node tmpNode = this.right;
            this.right = tmpNode.right;
            tmpNode.right = tmpNode.left;
            tmpNode.left = this.left;
            this.left = tmpNode;
            if (this.right != null) {
                this.right.parent = this;
            }
            if (this.left.left != null) {
                this.left.left.parent = this.left;
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static enum Skew {
        LEFT_HIGH,
        RIGHT_HIGH,
        BALANCED;

    }
}

