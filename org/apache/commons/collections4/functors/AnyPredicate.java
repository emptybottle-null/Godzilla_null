/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.util.Collection;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.AbstractQuantifierPredicate;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.apache.commons.collections4.functors.FunctorUtils;

public final class AnyPredicate<T>
extends AbstractQuantifierPredicate<T> {
    private static final long serialVersionUID = 7429999530934647542L;

    public static <T> Predicate<T> anyPredicate(Predicate<? super T> ... predicates) {
        FunctorUtils.validate(predicates);
        if (predicates.length == 0) {
            return FalsePredicate.falsePredicate();
        }
        if (predicates.length == 1) {
            return predicates[0];
        }
        return new AnyPredicate<T>(FunctorUtils.copy(predicates));
    }

    public static <T> Predicate<T> anyPredicate(Collection<? extends Predicate<? super T>> predicates) {
        Predicate<T>[] preds = FunctorUtils.validate(predicates);
        if (preds.length == 0) {
            return FalsePredicate.falsePredicate();
        }
        if (preds.length == 1) {
            return preds[0];
        }
        return new AnyPredicate(preds);
    }

    public AnyPredicate(Predicate<? super T> ... predicates) {
        super(predicates);
    }

    @Override
    public boolean evaluate(T object) {
        for (Predicate iPredicate : this.iPredicates) {
            if (!iPredicate.evaluate(object)) continue;
            return true;
        }
        return false;
    }
}

