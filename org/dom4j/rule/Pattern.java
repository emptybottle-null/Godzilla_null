/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.rule;

import org.dom4j.Node;
import org.dom4j.NodeFilter;

public interface Pattern
extends NodeFilter {
    public static final short ANY_NODE = 0;
    public static final short NONE = 9999;
    public static final short NUMBER_OF_TYPES = 14;
    public static final double DEFAULT_PRIORITY = 0.5;

    @Override
    public boolean matches(Node var1);

    public double getPriority();

    public Pattern[] getUnionPatterns();

    public short getMatchType();

    public String getMatchesNodeName();
}

