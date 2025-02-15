/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.rule.pattern;

import org.dom4j.Node;
import org.dom4j.rule.Pattern;

public class NodeTypePattern
implements Pattern {
    public static final NodeTypePattern ANY_ATTRIBUTE = new NodeTypePattern(2);
    public static final NodeTypePattern ANY_COMMENT = new NodeTypePattern(8);
    public static final NodeTypePattern ANY_DOCUMENT = new NodeTypePattern(9);
    public static final NodeTypePattern ANY_ELEMENT = new NodeTypePattern(1);
    public static final NodeTypePattern ANY_PROCESSING_INSTRUCTION = new NodeTypePattern(7);
    public static final NodeTypePattern ANY_TEXT = new NodeTypePattern(3);
    private short nodeType;

    public NodeTypePattern(short nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public boolean matches(Node node) {
        return node.getNodeType() == this.nodeType;
    }

    @Override
    public double getPriority() {
        return 0.5;
    }

    @Override
    public Pattern[] getUnionPatterns() {
        return null;
    }

    @Override
    public short getMatchType() {
        return this.nodeType;
    }

    @Override
    public String getMatchesNodeName() {
        return null;
    }
}

