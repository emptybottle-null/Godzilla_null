/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.EmptyExpression;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NodeVisitor;
import org.mozilla.javascript.ast.Scope;

public abstract class AstNode
extends Node
implements Comparable<AstNode> {
    protected int position = -1;
    protected int length = 1;
    protected AstNode parent;
    private static Map<Integer, String> operatorNames = new HashMap<Integer, String>();

    public AstNode() {
        super(-1);
    }

    public AstNode(int pos) {
        this();
        this.position = pos;
    }

    public AstNode(int pos, int len) {
        this();
        this.position = pos;
        this.length = len;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getAbsolutePosition() {
        int pos = this.position;
        for (AstNode parent = this.parent; parent != null; parent = parent.getParent()) {
            pos += parent.getPosition();
        }
        return pos;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setBounds(int position, int end) {
        this.setPosition(position);
        this.setLength(end - position);
    }

    public void setRelative(int parentPosition) {
        this.position -= parentPosition;
    }

    public AstNode getParent() {
        return this.parent;
    }

    public void setParent(AstNode parent) {
        if (parent == this.parent) {
            return;
        }
        if (this.parent != null) {
            this.setRelative(-this.parent.getPosition());
        }
        this.parent = parent;
        if (parent != null) {
            this.setRelative(parent.getPosition());
        }
    }

    public void addChild(AstNode kid) {
        this.assertNotNull(kid);
        int end = kid.getPosition() + kid.getLength();
        this.setLength(end - this.getPosition());
        this.addChildToBack(kid);
        kid.setParent(this);
    }

    public AstRoot getAstRoot() {
        AstNode parent;
        for (parent = this; parent != null && !(parent instanceof AstRoot); parent = parent.getParent()) {
        }
        return (AstRoot)parent;
    }

    public abstract String toSource(int var1);

    public String toSource() {
        return this.toSource(0);
    }

    public String makeIndent(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; ++i) {
            sb.append("  ");
        }
        return sb.toString();
    }

    public String shortName() {
        String classname = this.getClass().getName();
        int last = classname.lastIndexOf(".");
        return classname.substring(last + 1);
    }

    public static String operatorToString(int op) {
        String result = operatorNames.get(op);
        if (result == null) {
            throw new IllegalArgumentException("Invalid operator: " + op);
        }
        return result;
    }

    public abstract void visit(NodeVisitor var1);

    @Override
    public boolean hasSideEffects() {
        switch (this.getType()) {
            case -1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 30: 
            case 31: 
            case 35: 
            case 37: 
            case 38: 
            case 50: 
            case 51: 
            case 56: 
            case 57: 
            case 64: 
            case 68: 
            case 69: 
            case 70: 
            case 72: 
            case 81: 
            case 82: 
            case 90: 
            case 91: 
            case 92: 
            case 93: 
            case 94: 
            case 95: 
            case 96: 
            case 97: 
            case 98: 
            case 99: 
            case 100: 
            case 101: 
            case 106: 
            case 107: 
            case 109: 
            case 110: 
            case 111: 
            case 112: 
            case 113: 
            case 114: 
            case 117: 
            case 118: 
            case 119: 
            case 120: 
            case 121: 
            case 122: 
            case 123: 
            case 124: 
            case 125: 
            case 129: 
            case 130: 
            case 131: 
            case 132: 
            case 134: 
            case 135: 
            case 139: 
            case 140: 
            case 141: 
            case 142: 
            case 153: 
            case 154: 
            case 158: 
            case 159: {
                return true;
            }
        }
        return false;
    }

    protected void assertNotNull(Object arg) {
        if (arg == null) {
            throw new IllegalArgumentException("arg cannot be null");
        }
    }

    protected <T extends AstNode> void printList(List<T> items, StringBuilder sb) {
        int max = items.size();
        int count = 0;
        for (AstNode item : items) {
            sb.append(item.toSource(0));
            if (count++ < max - 1) {
                sb.append(", ");
                continue;
            }
            if (!(item instanceof EmptyExpression)) continue;
            sb.append(",");
        }
    }

    public static RuntimeException codeBug() throws RuntimeException {
        throw Kit.codeBug();
    }

    public FunctionNode getEnclosingFunction() {
        AstNode parent;
        for (parent = this.getParent(); parent != null && !(parent instanceof FunctionNode); parent = parent.getParent()) {
        }
        return (FunctionNode)parent;
    }

    public Scope getEnclosingScope() {
        AstNode parent;
        for (parent = this.getParent(); parent != null && !(parent instanceof Scope); parent = parent.getParent()) {
        }
        return (Scope)parent;
    }

    @Override
    public int compareTo(AstNode other) {
        int len2;
        int abs2;
        if (this.equals(other)) {
            return 0;
        }
        int abs1 = this.getAbsolutePosition();
        if (abs1 < (abs2 = other.getAbsolutePosition())) {
            return -1;
        }
        if (abs2 < abs1) {
            return 1;
        }
        int len1 = this.getLength();
        if (len1 < (len2 = other.getLength())) {
            return -1;
        }
        if (len2 < len1) {
            return 1;
        }
        return this.hashCode() - other.hashCode();
    }

    public int depth() {
        return this.parent == null ? 0 : 1 + this.parent.depth();
    }

    @Override
    public int getLineno() {
        if (this.lineno != -1) {
            return this.lineno;
        }
        if (this.parent != null) {
            return this.parent.getLineno();
        }
        return -1;
    }

    public String debugPrint() {
        DebugPrintVisitor dpv = new DebugPrintVisitor(new StringBuilder(1000));
        this.visit(dpv);
        return dpv.toString();
    }

    static {
        operatorNames.put(52, "in");
        operatorNames.put(32, "typeof");
        operatorNames.put(53, "instanceof");
        operatorNames.put(31, "delete");
        operatorNames.put(89, ",");
        operatorNames.put(103, ":");
        operatorNames.put(104, "||");
        operatorNames.put(105, "&&");
        operatorNames.put(106, "++");
        operatorNames.put(107, "--");
        operatorNames.put(9, "|");
        operatorNames.put(10, "^");
        operatorNames.put(11, "&");
        operatorNames.put(12, "==");
        operatorNames.put(13, "!=");
        operatorNames.put(14, "<");
        operatorNames.put(16, ">");
        operatorNames.put(15, "<=");
        operatorNames.put(17, ">=");
        operatorNames.put(18, "<<");
        operatorNames.put(19, ">>");
        operatorNames.put(20, ">>>");
        operatorNames.put(21, "+");
        operatorNames.put(22, "-");
        operatorNames.put(23, "*");
        operatorNames.put(24, "/");
        operatorNames.put(25, "%");
        operatorNames.put(26, "!");
        operatorNames.put(27, "~");
        operatorNames.put(28, "+");
        operatorNames.put(29, "-");
        operatorNames.put(46, "===");
        operatorNames.put(47, "!==");
        operatorNames.put(90, "=");
        operatorNames.put(91, "|=");
        operatorNames.put(93, "&=");
        operatorNames.put(94, "<<=");
        operatorNames.put(95, ">>=");
        operatorNames.put(96, ">>>=");
        operatorNames.put(97, "+=");
        operatorNames.put(98, "-=");
        operatorNames.put(99, "*=");
        operatorNames.put(100, "/=");
        operatorNames.put(101, "%=");
        operatorNames.put(92, "^=");
        operatorNames.put(126, "void");
    }

    protected static class DebugPrintVisitor
    implements NodeVisitor {
        private StringBuilder buffer;
        private static final int DEBUG_INDENT = 2;

        public DebugPrintVisitor(StringBuilder buf) {
            this.buffer = buf;
        }

        public String toString() {
            return this.buffer.toString();
        }

        private String makeIndent(int depth) {
            StringBuilder sb = new StringBuilder(2 * depth);
            for (int i = 0; i < 2 * depth; ++i) {
                sb.append(" ");
            }
            return sb.toString();
        }

        @Override
        public boolean visit(AstNode node) {
            int tt = node.getType();
            String name = Token.typeToName(tt);
            this.buffer.append(node.getAbsolutePosition()).append("\t");
            this.buffer.append(this.makeIndent(node.depth()));
            this.buffer.append(name).append(" ");
            this.buffer.append(node.getPosition()).append(" ");
            this.buffer.append(node.getLength());
            if (tt == 39) {
                this.buffer.append(" ").append(((Name)node).getIdentifier());
            }
            this.buffer.append("\n");
            return true;
        }
    }

    public static class PositionComparator
    implements Comparator<AstNode>,
    Serializable {
        private static final long serialVersionUID = 1L;

        @Override
        public int compare(AstNode n1, AstNode n2) {
            return n1.position - n2.position;
        }
    }
}

