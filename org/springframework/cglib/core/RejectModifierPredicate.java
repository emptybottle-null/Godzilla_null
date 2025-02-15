/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import java.lang.reflect.Member;
import org.springframework.cglib.core.Predicate;

public class RejectModifierPredicate
implements Predicate {
    private int rejectMask;

    public RejectModifierPredicate(int rejectMask) {
        this.rejectMask = rejectMask;
    }

    public boolean evaluate(Object arg) {
        return (((Member)arg).getModifiers() & this.rejectMask) == 0;
    }
}

