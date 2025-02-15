/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.visitor;

import com.github.javaparser.ast.Node;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class TreeVisitor {
    public void visitLeavesFirst(Node node) {
        for (Node child : node.getChildNodes()) {
            this.visitLeavesFirst(child);
        }
        this.process(node);
    }

    public void visitPreOrder(Node node) {
        this.process(node);
        new ArrayList<Node>(node.getChildNodes()).forEach(this::visitPreOrder);
    }

    public void visitPostOrder(Node node) {
        new ArrayList<Node>(node.getChildNodes()).forEach(this::visitPostOrder);
        this.process(node);
    }

    public void visitBreadthFirst(Node node) {
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.offer(node);
        while (queue.size() > 0) {
            Node head = (Node)queue.peek();
            for (Node child : head.getChildNodes()) {
                queue.offer(child);
            }
            this.process((Node)queue.poll());
        }
    }

    public abstract void process(Node var1);

    public void visitDirectChildren(Node node) {
        new ArrayList<Node>(node.getChildNodes()).forEach(this::process);
    }
}

