/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation.parser;

import java.io.Serializable;

public interface Node
extends Serializable {
    public void jjtOpen();

    public void jjtClose();

    public void jjtSetParent(Node var1);

    public Node jjtGetParent();

    public void jjtAddChild(Node var1, int var2);

    public Node jjtGetChild(int var1);

    public int jjtGetNumChildren();

    public int getId();
}

