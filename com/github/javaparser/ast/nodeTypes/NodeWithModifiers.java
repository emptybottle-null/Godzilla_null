/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public interface NodeWithModifiers<N extends Node> {
    public NodeList<Modifier> getModifiers();

    public N setModifiers(NodeList<Modifier> var1);

    default public N addModifier(Modifier.Keyword ... newModifiers) {
        NodeList<Modifier> existingModifiers = new NodeList<Modifier>((Collection<Modifier>)this.getModifiers());
        for (Modifier.Keyword newModifier : newModifiers) {
            boolean alreadyPresent = existingModifiers.stream().anyMatch(m -> m.getKeyword() == newModifier);
            if (alreadyPresent) continue;
            existingModifiers.add(new Modifier(newModifier));
        }
        this.setModifiers(existingModifiers);
        return (N)((Node)((Object)this));
    }

    default public N removeModifier(Modifier.Keyword ... modifiersToRemove) {
        List<Modifier.Keyword> modifiersToRemoveAsList = Arrays.asList(modifiersToRemove);
        NodeList<Modifier> remaining = this.getModifiers().stream().filter(existingModifier -> !modifiersToRemoveAsList.contains((Object)existingModifier.getKeyword())).collect(NodeList.toNodeList());
        this.setModifiers(remaining);
        return (N)((Node)((Object)this));
    }

    default public N setModifier(Modifier.Keyword m, boolean set) {
        if (set) {
            return this.addModifier(m);
        }
        return this.removeModifier(m);
    }

    default public boolean hasModifier(Modifier.Keyword modifier) {
        for (Modifier m : this.getModifiers()) {
            if (m.getKeyword() != modifier) continue;
            return true;
        }
        return false;
    }

    default public N setModifiers(Modifier.Keyword ... modifiers) {
        return this.setModifiers(Arrays.stream(modifiers).map(Modifier::new).collect(NodeList.toNodeList()));
    }

    default public AccessSpecifier getAccessSpecifier() {
        for (Modifier modifier : this.getModifiers()) {
            switch (modifier.getKeyword()) {
                case PUBLIC: {
                    return AccessSpecifier.PUBLIC;
                }
                case PROTECTED: {
                    return AccessSpecifier.PROTECTED;
                }
                case PRIVATE: {
                    return AccessSpecifier.PRIVATE;
                }
            }
        }
        return AccessSpecifier.PACKAGE_PRIVATE;
    }
}

