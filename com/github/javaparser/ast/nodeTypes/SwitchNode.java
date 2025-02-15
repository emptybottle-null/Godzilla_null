/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.SwitchEntry;
import java.util.Optional;

public interface SwitchNode {
    public NodeList<SwitchEntry> getEntries();

    public SwitchEntry getEntry(int var1);

    public Expression getSelector();

    public SwitchNode setEntries(NodeList<SwitchEntry> var1);

    public SwitchNode setSelector(Expression var1);

    public boolean remove(Node var1);

    public SwitchNode clone();

    public boolean replace(Node var1, Node var2);

    public Optional<Comment> getComment();

    default public boolean isEmpty() {
        return this.getEntries().isEmpty();
    }
}

