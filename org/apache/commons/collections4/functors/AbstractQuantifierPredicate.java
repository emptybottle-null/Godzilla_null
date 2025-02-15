/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.FunctorUtils;
import org.apache.commons.collections4.functors.PredicateDecorator;

public abstract class AbstractQuantifierPredicate<T>
implements PredicateDecorator<T>,
Serializable {
    private static final long serialVersionUID = -3094696765038308799L;
    protected final Predicate<? super T>[] iPredicates;

    public AbstractQuantifierPredicate(Predicate<? super T> ... predicates) {
        this.iPredicates = predicates;
    }

    @Override
    public Predicate<? super T>[] getPredicates() {
        return FunctorUtils.copy(this.iPredicates);
    }
}

