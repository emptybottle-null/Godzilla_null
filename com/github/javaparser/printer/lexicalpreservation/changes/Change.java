/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation.changes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.printer.concretesyntaxmodel.CsmConditional;
import com.github.javaparser.utils.Utils;

public interface Change {
    default public boolean evaluate(CsmConditional csmConditional, Node node) {
        switch (csmConditional.getCondition()) {
            case FLAG: {
                return csmConditional.getProperties().stream().anyMatch(p -> (Boolean)this.getValue((ObservableProperty)((Object)p), node));
            }
            case IS_NOT_EMPTY: {
                return !Utils.valueIsNullOrEmpty(this.getValue(csmConditional.getProperty(), node));
            }
            case IS_EMPTY: {
                return Utils.valueIsNullOrEmpty(this.getValue(csmConditional.getProperty(), node));
            }
            case IS_PRESENT: {
                return !Utils.valueIsNullOrEmptyStringOrOptional(this.getValue(csmConditional.getProperty(), node));
            }
        }
        throw new UnsupportedOperationException("" + (Object)((Object)csmConditional.getProperty()) + " " + (Object)((Object)csmConditional.getCondition()));
    }

    public Object getValue(ObservableProperty var1, Node var2);
}

