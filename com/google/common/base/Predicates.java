/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.CommonPattern;
import com.google.common.base.Function;
import com.google.common.base.JdkPattern;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Platform;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
public final class Predicates {
    private Predicates() {
    }

    @GwtCompatible(serializable=true)
    public static <T> Predicate<T> alwaysTrue() {
        return ObjectPredicate.ALWAYS_TRUE.withNarrowedType();
    }

    @GwtCompatible(serializable=true)
    public static <T> Predicate<T> alwaysFalse() {
        return ObjectPredicate.ALWAYS_FALSE.withNarrowedType();
    }

    @GwtCompatible(serializable=true)
    public static <T> Predicate<T> isNull() {
        return ObjectPredicate.IS_NULL.withNarrowedType();
    }

    @GwtCompatible(serializable=true)
    public static <T> Predicate<T> notNull() {
        return ObjectPredicate.NOT_NULL.withNarrowedType();
    }

    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return new NotPredicate<T>(predicate);
    }

    public static <T> Predicate<T> and(Iterable<? extends Predicate<? super T>> components) {
        return new AndPredicate(Predicates.defensiveCopy(components));
    }

    @SafeVarargs
    public static <T> Predicate<T> and(Predicate<? super T> ... components) {
        return new AndPredicate(Predicates.defensiveCopy(components));
    }

    public static <T> Predicate<T> and(Predicate<? super T> first, Predicate<? super T> second) {
        return new AndPredicate(Predicates.asList(Preconditions.checkNotNull(first), Preconditions.checkNotNull(second)));
    }

    public static <T> Predicate<T> or(Iterable<? extends Predicate<? super T>> components) {
        return new OrPredicate(Predicates.defensiveCopy(components));
    }

    @SafeVarargs
    public static <T> Predicate<T> or(Predicate<? super T> ... components) {
        return new OrPredicate(Predicates.defensiveCopy(components));
    }

    public static <T> Predicate<T> or(Predicate<? super T> first, Predicate<? super T> second) {
        return new OrPredicate(Predicates.asList(Preconditions.checkNotNull(first), Preconditions.checkNotNull(second)));
    }

    public static <T> Predicate<T> equalTo(@Nullable T target) {
        return target == null ? Predicates.isNull() : new IsEqualToPredicate(target);
    }

    @GwtIncompatible
    public static Predicate<Object> instanceOf(Class<?> clazz) {
        return new InstanceOfPredicate(clazz);
    }

    @GwtIncompatible
    @Beta
    public static Predicate<Class<?>> subtypeOf(Class<?> clazz) {
        return new SubtypeOfPredicate(clazz);
    }

    public static <T> Predicate<T> in(Collection<? extends T> target) {
        return new InPredicate(target);
    }

    public static <A, B> Predicate<A> compose(Predicate<B> predicate, Function<A, ? extends B> function) {
        return new CompositionPredicate(predicate, function);
    }

    @GwtIncompatible
    public static Predicate<CharSequence> containsPattern(String pattern) {
        return new ContainsPatternFromStringPredicate(pattern);
    }

    @GwtIncompatible(value="java.util.regex.Pattern")
    public static Predicate<CharSequence> contains(Pattern pattern) {
        return new ContainsPatternPredicate(new JdkPattern(pattern));
    }

    private static String toStringHelper(String methodName, Iterable<?> components) {
        StringBuilder builder = new StringBuilder("Predicates.").append(methodName).append('(');
        boolean first = true;
        for (Object o : components) {
            if (!first) {
                builder.append(',');
            }
            builder.append(o);
            first = false;
        }
        return builder.append(')').toString();
    }

    private static <T> List<Predicate<? super T>> asList(Predicate<? super T> first, Predicate<? super T> second) {
        return Arrays.asList(first, second);
    }

    private static <T> List<T> defensiveCopy(T ... array) {
        return Predicates.defensiveCopy(Arrays.asList(array));
    }

    static <T> List<T> defensiveCopy(Iterable<T> iterable) {
        ArrayList<T> list = new ArrayList<T>();
        for (T element : iterable) {
            list.add(Preconditions.checkNotNull(element));
        }
        return list;
    }

    @GwtIncompatible
    private static class ContainsPatternFromStringPredicate
    extends ContainsPatternPredicate {
        private static final long serialVersionUID = 0L;

        ContainsPatternFromStringPredicate(String string) {
            super(Platform.compilePattern(string));
        }

        @Override
        public String toString() {
            return "Predicates.containsPattern(" + this.pattern.pattern() + ")";
        }
    }

    @GwtIncompatible
    private static class ContainsPatternPredicate
    implements Predicate<CharSequence>,
    Serializable {
        final CommonPattern pattern;
        private static final long serialVersionUID = 0L;

        ContainsPatternPredicate(CommonPattern pattern) {
            this.pattern = Preconditions.checkNotNull(pattern);
        }

        @Override
        public boolean apply(CharSequence t) {
            return this.pattern.matcher(t).find();
        }

        public int hashCode() {
            return Objects.hashCode(this.pattern.pattern(), this.pattern.flags());
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj instanceof ContainsPatternPredicate) {
                ContainsPatternPredicate that = (ContainsPatternPredicate)obj;
                return Objects.equal(this.pattern.pattern(), that.pattern.pattern()) && this.pattern.flags() == that.pattern.flags();
            }
            return false;
        }

        public String toString() {
            String patternString = MoreObjects.toStringHelper(this.pattern).add("pattern", this.pattern.pattern()).add("pattern.flags", this.pattern.flags()).toString();
            return "Predicates.contains(" + patternString + ")";
        }
    }

    private static class CompositionPredicate<A, B>
    implements Predicate<A>,
    Serializable {
        final Predicate<B> p;
        final Function<A, ? extends B> f;
        private static final long serialVersionUID = 0L;

        private CompositionPredicate(Predicate<B> p, Function<A, ? extends B> f) {
            this.p = Preconditions.checkNotNull(p);
            this.f = Preconditions.checkNotNull(f);
        }

        @Override
        public boolean apply(@Nullable A a) {
            return this.p.apply(this.f.apply(a));
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj instanceof CompositionPredicate) {
                CompositionPredicate that = (CompositionPredicate)obj;
                return this.f.equals(that.f) && this.p.equals(that.p);
            }
            return false;
        }

        public int hashCode() {
            return this.f.hashCode() ^ this.p.hashCode();
        }

        public String toString() {
            return this.p + "(" + this.f + ")";
        }
    }

    private static class InPredicate<T>
    implements Predicate<T>,
    Serializable {
        private final Collection<?> target;
        private static final long serialVersionUID = 0L;

        private InPredicate(Collection<?> target) {
            this.target = Preconditions.checkNotNull(target);
        }

        @Override
        public boolean apply(@Nullable T t) {
            try {
                return this.target.contains(t);
            } catch (ClassCastException | NullPointerException e) {
                return false;
            }
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj instanceof InPredicate) {
                InPredicate that = (InPredicate)obj;
                return this.target.equals(that.target);
            }
            return false;
        }

        public int hashCode() {
            return this.target.hashCode();
        }

        public String toString() {
            return "Predicates.in(" + this.target + ")";
        }
    }

    @GwtIncompatible
    private static class SubtypeOfPredicate
    implements Predicate<Class<?>>,
    Serializable {
        private final Class<?> clazz;
        private static final long serialVersionUID = 0L;

        private SubtypeOfPredicate(Class<?> clazz) {
            this.clazz = Preconditions.checkNotNull(clazz);
        }

        @Override
        public boolean apply(Class<?> input) {
            return this.clazz.isAssignableFrom(input);
        }

        public int hashCode() {
            return this.clazz.hashCode();
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj instanceof SubtypeOfPredicate) {
                SubtypeOfPredicate that = (SubtypeOfPredicate)obj;
                return this.clazz == that.clazz;
            }
            return false;
        }

        public String toString() {
            return "Predicates.subtypeOf(" + this.clazz.getName() + ")";
        }
    }

    @GwtIncompatible
    private static class InstanceOfPredicate
    implements Predicate<Object>,
    Serializable {
        private final Class<?> clazz;
        private static final long serialVersionUID = 0L;

        private InstanceOfPredicate(Class<?> clazz) {
            this.clazz = Preconditions.checkNotNull(clazz);
        }

        @Override
        public boolean apply(@Nullable Object o) {
            return this.clazz.isInstance(o);
        }

        public int hashCode() {
            return this.clazz.hashCode();
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj instanceof InstanceOfPredicate) {
                InstanceOfPredicate that = (InstanceOfPredicate)obj;
                return this.clazz == that.clazz;
            }
            return false;
        }

        public String toString() {
            return "Predicates.instanceOf(" + this.clazz.getName() + ")";
        }
    }

    private static class IsEqualToPredicate<T>
    implements Predicate<T>,
    Serializable {
        private final T target;
        private static final long serialVersionUID = 0L;

        private IsEqualToPredicate(T target) {
            this.target = target;
        }

        @Override
        public boolean apply(T t) {
            return this.target.equals(t);
        }

        public int hashCode() {
            return this.target.hashCode();
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj instanceof IsEqualToPredicate) {
                IsEqualToPredicate that = (IsEqualToPredicate)obj;
                return this.target.equals(that.target);
            }
            return false;
        }

        public String toString() {
            return "Predicates.equalTo(" + this.target + ")";
        }
    }

    private static class OrPredicate<T>
    implements Predicate<T>,
    Serializable {
        private final List<? extends Predicate<? super T>> components;
        private static final long serialVersionUID = 0L;

        private OrPredicate(List<? extends Predicate<? super T>> components) {
            this.components = components;
        }

        @Override
        public boolean apply(@Nullable T t) {
            for (int i = 0; i < this.components.size(); ++i) {
                if (!this.components.get(i).apply(t)) continue;
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.components.hashCode() + 87855567;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj instanceof OrPredicate) {
                OrPredicate that = (OrPredicate)obj;
                return this.components.equals(that.components);
            }
            return false;
        }

        public String toString() {
            return Predicates.toStringHelper("or", this.components);
        }
    }

    private static class AndPredicate<T>
    implements Predicate<T>,
    Serializable {
        private final List<? extends Predicate<? super T>> components;
        private static final long serialVersionUID = 0L;

        private AndPredicate(List<? extends Predicate<? super T>> components) {
            this.components = components;
        }

        @Override
        public boolean apply(@Nullable T t) {
            for (int i = 0; i < this.components.size(); ++i) {
                if (this.components.get(i).apply(t)) continue;
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.components.hashCode() + 306654252;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj instanceof AndPredicate) {
                AndPredicate that = (AndPredicate)obj;
                return this.components.equals(that.components);
            }
            return false;
        }

        public String toString() {
            return Predicates.toStringHelper("and", this.components);
        }
    }

    private static class NotPredicate<T>
    implements Predicate<T>,
    Serializable {
        final Predicate<T> predicate;
        private static final long serialVersionUID = 0L;

        NotPredicate(Predicate<T> predicate) {
            this.predicate = Preconditions.checkNotNull(predicate);
        }

        @Override
        public boolean apply(@Nullable T t) {
            return !this.predicate.apply(t);
        }

        public int hashCode() {
            return ~this.predicate.hashCode();
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj instanceof NotPredicate) {
                NotPredicate that = (NotPredicate)obj;
                return this.predicate.equals(that.predicate);
            }
            return false;
        }

        public String toString() {
            return "Predicates.not(" + this.predicate + ")";
        }
    }

    static enum ObjectPredicate implements Predicate<Object>
    {
        ALWAYS_TRUE{

            @Override
            public boolean apply(@Nullable Object o) {
                return true;
            }

            public String toString() {
                return "Predicates.alwaysTrue()";
            }
        }
        ,
        ALWAYS_FALSE{

            @Override
            public boolean apply(@Nullable Object o) {
                return false;
            }

            public String toString() {
                return "Predicates.alwaysFalse()";
            }
        }
        ,
        IS_NULL{

            @Override
            public boolean apply(@Nullable Object o) {
                return o == null;
            }

            public String toString() {
                return "Predicates.isNull()";
            }
        }
        ,
        NOT_NULL{

            @Override
            public boolean apply(@Nullable Object o) {
                return o != null;
            }

            public String toString() {
                return "Predicates.notNull()";
            }
        };


        <T> Predicate<T> withNarrowedType() {
            return this;
        }
    }
}

