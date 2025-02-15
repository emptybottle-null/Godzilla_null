/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.printer.concretesyntaxmodel.CsmElement;
import com.github.javaparser.printer.lexicalpreservation.Added;
import com.github.javaparser.printer.lexicalpreservation.Kept;
import com.github.javaparser.printer.lexicalpreservation.Removed;

public interface DifferenceElement {
    public static DifferenceElement added(CsmElement element) {
        return new Added(element);
    }

    public static DifferenceElement removed(CsmElement element) {
        return new Removed(element);
    }

    public static DifferenceElement kept(CsmElement element) {
        return new Kept(element);
    }

    public CsmElement getElement();

    public boolean isAdded();

    public boolean isRemoved();
}

