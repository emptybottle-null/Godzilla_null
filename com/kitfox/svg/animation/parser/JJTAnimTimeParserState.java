/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation.parser;

import com.kitfox.svg.animation.parser.Node;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JJTAnimTimeParserState
implements Serializable {
    private List<Node> nodes = new ArrayList<Node>();
    private List<Integer> marks = new ArrayList<Integer>();
    private int sp = 0;
    private int mk = 0;
    private boolean node_created;

    public boolean nodeCreated() {
        return this.node_created;
    }

    public void reset() {
        this.nodes.clear();
        this.marks.clear();
        this.sp = 0;
        this.mk = 0;
    }

    public Node rootNode() {
        return this.nodes.get(0);
    }

    public void pushNode(Node n) {
        this.nodes.add(n);
        ++this.sp;
    }

    public Node popNode() {
        --this.sp;
        if (this.sp < this.mk) {
            this.mk = this.marks.remove(this.marks.size() - 1);
        }
        return this.nodes.remove(this.nodes.size() - 1);
    }

    public Node peekNode() {
        return this.nodes.get(this.nodes.size() - 1);
    }

    public int nodeArity() {
        return this.sp - this.mk;
    }

    public void clearNodeScope(Node n) {
        while (this.sp > this.mk) {
            this.popNode();
        }
        this.mk = this.marks.remove(this.marks.size() - 1);
    }

    public void openNodeScope(Node n) {
        this.marks.add(this.mk);
        this.mk = this.sp;
        n.jjtOpen();
    }

    public void closeNodeScope(Node n, int numIn) {
        this.mk = this.marks.remove(this.marks.size() - 1);
        int num = numIn;
        while (num-- > 0) {
            Node c = this.popNode();
            c.jjtSetParent(n);
            n.jjtAddChild(c, num);
        }
        n.jjtClose();
        this.pushNode(n);
        this.node_created = true;
    }

    public void closeNodeScope(Node n, boolean condition) {
        if (condition) {
            int a = this.nodeArity();
            this.mk = this.marks.remove(this.marks.size() - 1);
            while (a-- > 0) {
                Node c = this.popNode();
                c.jjtSetParent(n);
                n.jjtAddChild(c, a);
            }
            n.jjtClose();
            this.pushNode(n);
            this.node_created = true;
        } else {
            this.mk = this.marks.remove(this.marks.size() - 1);
            this.node_created = false;
        }
    }
}

