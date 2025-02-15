/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4;

import java.util.Collection;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.AllPredicate;
import org.apache.commons.collections4.functors.AndPredicate;
import org.apache.commons.collections4.functors.AnyPredicate;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.ExceptionPredicate;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.apache.commons.collections4.functors.IdentityPredicate;
import org.apache.commons.collections4.functors.InstanceofPredicate;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.functors.NonePredicate;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.apache.commons.collections4.functors.NotPredicate;
import org.apache.commons.collections4.functors.NullIsExceptionPredicate;
import org.apache.commons.collections4.functors.NullIsFalsePredicate;
import org.apache.commons.collections4.functors.NullIsTruePredicate;
import org.apache.commons.collections4.functors.NullPredicate;
import org.apache.commons.collections4.functors.OnePredicate;
import org.apache.commons.collections4.functors.OrPredicate;
import org.apache.commons.collections4.functors.TransformedPredicate;
import org.apache.commons.collections4.functors.TransformerPredicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.apache.commons.collections4.functors.UniquePredicate;

public class PredicateUtils {
    private PredicateUtils() {
    }

    public static <T> Predicate<T> exceptionPredicate() {
        return ExceptionPredicate.exceptionPredicate();
    }

    public static <T> Predicate<T> truePredicate() {
        return TruePredicate.truePredicate();
    }

    public static <T> Predicate<T> falsePredicate() {
        return FalsePredicate.falsePredicate();
    }

    public static <T> Predicate<T> nullPredicate() {
        return NullPredicate.nullPredicate();
    }

    public static <T> Predicate<T> notNullPredicate() {
        return NotNullPredicate.notNullPredicate();
    }

    public static <T> Predicate<T> equalPredicate(T value) {
        return EqualPredicate.equalPredicate(value);
    }

    public static <T> Predicate<T> identityPredicate(T value) {
        return IdentityPredicate.identityPredicate(value);
    }

    public static Predicate<Object> instanceofPredicate(Class<?> type) {
        return InstanceofPredicate.instanceOfPredicate(type);
    }

    public static <T> Predicate<T> uniquePredicate() {
        return UniquePredicate.uniquePredicate();
    }

    public static <T> Predicate<T> invokerPredicate(String methodName) {
        return PredicateUtils.asPredicate(InvokerTransformer.invokerTransformer(methodName));
    }

    public static <T> Predicate<T> invokerPredicate(String methodName, Class<?>[] paramTypes, Object[] args) {
        return PredicateUtils.asPredicate(InvokerTransformer.invokerTransformer(methodName, paramTypes, args));
    }

    public static <T> Predicate<T> andPredicate(Predicate<? super T> predicate1, Predicate<? super T> predicate2) {
        return AndPredicate.andPredicate(predicate1, predicate2);
    }

    public static <T> Predicate<T> allPredicate(Predicate<? super T> ... predicates) {
        return AllPredicate.allPredicate(predicates);
    }

    public static <T> Predicate<T> allPredicate(Collection<? extends Predicate<? super T>> predicates) {
        return AllPredicate.allPredicate(predicates);
    }

    public static <T> Predicate<T> orPredicate(Predicate<? super T> predicate1, Predicate<? super T> predicate2) {
        return OrPredicate.orPredicate(predicate1, predicate2);
    }

    public static <T> Predicate<T> anyPredicate(Predicate<? super T> ... predicates) {
        return AnyPredicate.anyPredicate(predicates);
    }

    public static <T> Predicate<T> anyPredicate(Collection<? extends Predicate<? super T>> predicates) {
        return AnyPredicate.anyPredicate(predicates);
    }

    public static <T> Predicate<T> eitherPredicate(Predicate<? super T> predicate1, Predicate<? super T> predicate2) {
        Predicate<T> onePredicate = PredicateUtils.onePredicate(predicate1, predicate2);
        return onePredicate;
    }

    public static <T> Predicate<T> onePredicate(Predicate<? super T> ... predicates) {
        return OnePredicate.onePredicate(predicates);
    }

    public static <T> Predicate<T> onePredicate(Collection<? extends Predicate<? super T>> predicates) {
        return OnePredicate.onePredicate(predicates);
    }

    public static <T> Predicate<T> neitherPredicate(Predicate<? super T> predicate1, Predicate<? super T> predicate2) {
        Predicate<T> nonePredicate = PredicateUtils.nonePredicate(predicate1, predicate2);
        return nonePredicate;
    }

    public static <T> Predicate<T> nonePredicate(Predicate<? super T> ... predicates) {
        return NonePredicate.nonePredicate(predicates);
    }

    public static <T> Predicate<T> nonePredicate(Collection<? extends Predicate<? super T>> predicates) {
        return NonePredicate.nonePredicate(predicates);
    }

    public static <T> Predicate<T> notPredicate(Predicate<? super T> predicate) {
        return NotPredicate.notPredicate(predicate);
    }

    public static <T> Predicate<T> asPredicate(Transformer<? super T, Boolean> transformer) {
        return TransformerPredicate.transformerPredicate(transformer);
    }

    public static <T> Predicate<T> nullIsExceptionPredicate(Predicate<? super T> predicate) {
        return NullIsExceptionPredicate.nullIsExceptionPredicate(predicate);
    }

    public static <T> Predicate<T> nullIsFalsePredicate(Predicate<? super T> predicate) {
        return NullIsFalsePredicate.nullIsFalsePredicate(predicate);
    }

    public static <T> Predicate<T> nullIsTruePredicate(Predicate<? super T> predicate) {
        return NullIsTruePredicate.nullIsTruePredicate(predicate);
    }

    public static <T> Predicate<T> transformedPredicate(Transformer<? super T, ? extends T> transformer, Predicate<? super T> predicate) {
        return TransformedPredicate.transformedPredicate(transformer, predicate);
    }
}

