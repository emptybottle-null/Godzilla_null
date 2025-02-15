/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.functors;

import java.util.Collection;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.AbstractQuantifierPredicate;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.apache.commons.collections4.functors.FunctorUtils;

public final class OnePredicate<T>
extends AbstractQuantifierPredicate<T> {
    private static final long serialVersionUID = -8125389089924745785L;

    public static <T> Predicate<T> onePredicate(Predicate<? super T> ... predicates) {
        FunctorUtils.validate(predicates);
        if (predicates.length == 0) {
            return FalsePredicate.falsePredicate();
        }
        if (predicates.length == 1) {
            return predicates[0];
        }
        return new OnePredicate<T>(FunctorUtils.copy(predicates));
    }

    public static <T> Predicate<T> onePredicate(Collection<? extends Predicate<? super T>> predicates) {
        Predicate<T>[] preds = FunctorUtils.validate(predicates);
        return new OnePredicate(preds);
    }

    public OnePredicate(Predicate<? super T> ... predicates) {
        super(predicates);
    }

    @Override
    public boolean evaluate(T object) {
        boolean match = false;
        for (Predicate iPredicate : this.iPredicates) {
            if (!iPredicate.evaluate(object)) continue;
            if (match) {
                return false;
            }
            match = true;
        }
        return match;
    }
}

