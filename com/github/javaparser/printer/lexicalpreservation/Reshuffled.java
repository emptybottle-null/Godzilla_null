/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import com.github.javaparser.printer.concretesyntaxmodel.CsmMix;
import com.github.javaparser.printer.lexicalpreservation.DifferenceElement;

public class Reshuffled
implements DifferenceElement {
    private final CsmMix previousOrder;
    private final CsmMix nextOrder;

    Reshuffled(CsmMix previousOrder, CsmMix nextOrder) {
        this.previousOrder = previousOrder;
        this.nextOrder = nextOrder;
    }

    public String toString() {
        return "Reshuffled{" + this.nextOrder + ", previous=" + this.previousOrder + '}';
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Reshuffled that = (Reshuffled)o;
        if (!this.previousOrder.equals(that.previousOrder)) {
            return false;
        }
        return this.nextOrder.equals(that.nextOrder);
    }

    public int hashCode() {
        int result = this.previousOrder.hashCode();
        result = 31 * result + this.nextOrder.hashCode();
        return result;
    }

    @Override
    public CsmMix getElement() {
        return this.nextOrder;
    }

    public CsmMix getPreviousOrder() {
        return this.previousOrder;
    }

    public CsmMix getNextOrder() {
        return this.nextOrder;
    }

    @Override
    public boolean isAdded() {
        return false;
    }

    @Override
    public boolean isRemoved() {
        return false;
    }
}

