/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import org.springframework.core.annotation.AbstractMergedAnnotation;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.lang.Nullable;

final class MissingMergedAnnotation<A extends Annotation>
extends AbstractMergedAnnotation<A> {
    private static final MissingMergedAnnotation<?> INSTANCE = new MissingMergedAnnotation();

    private MissingMergedAnnotation() {
    }

    @Override
    public Class<A> getType() {
        throw new NoSuchElementException("Unable to get type for missing annotation");
    }

    @Override
    public boolean isPresent() {
        return false;
    }

    @Override
    @Nullable
    public Object getSource() {
        return null;
    }

    @Override
    @Nullable
    public MergedAnnotation<?> getMetaSource() {
        return null;
    }

    @Override
    public MergedAnnotation<?> getRoot() {
        return this;
    }

    @Override
    public List<Class<? extends Annotation>> getMetaTypes() {
        return Collections.emptyList();
    }

    @Override
    public int getDistance() {
        return -1;
    }

    @Override
    public int getAggregateIndex() {
        return -1;
    }

    @Override
    public boolean hasNonDefaultValue(String attributeName) {
        throw new NoSuchElementException("Unable to check non-default value for missing annotation");
    }

    @Override
    public boolean hasDefaultValue(String attributeName) {
        throw new NoSuchElementException("Unable to check default value for missing annotation");
    }

    @Override
    public <T> Optional<T> getValue(String attributeName, Class<T> type) {
        return Optional.empty();
    }

    @Override
    public <T> Optional<T> getDefaultValue(@Nullable String attributeName, Class<T> type) {
        return Optional.empty();
    }

    @Override
    public MergedAnnotation<A> filterAttributes(Predicate<String> predicate) {
        return this;
    }

    @Override
    public MergedAnnotation<A> withNonMergedAttributes() {
        return this;
    }

    @Override
    public AnnotationAttributes asAnnotationAttributes(MergedAnnotation.Adapt ... adaptations) {
        return new AnnotationAttributes();
    }

    @Override
    public Map<String, Object> asMap(MergedAnnotation.Adapt ... adaptations) {
        return Collections.emptyMap();
    }

    @Override
    public <T extends Map<String, Object>> T asMap(Function<MergedAnnotation<?>, T> factory, MergedAnnotation.Adapt ... adaptations) {
        return (T)((Map)factory.apply(this));
    }

    public String toString() {
        return "(missing)";
    }

    @Override
    public <T extends Annotation> MergedAnnotation<T> getAnnotation(String attributeName, Class<T> type) throws NoSuchElementException {
        throw new NoSuchElementException("Unable to get attribute value for missing annotation");
    }

    @Override
    public <T extends Annotation> MergedAnnotation<T>[] getAnnotationArray(String attributeName, Class<T> type) throws NoSuchElementException {
        throw new NoSuchElementException("Unable to get attribute value for missing annotation");
    }

    @Override
    protected <T> T getAttributeValue(String attributeName, Class<T> type) {
        throw new NoSuchElementException("Unable to get attribute value for missing annotation");
    }

    @Override
    protected A createSynthesized() {
        throw new NoSuchElementException("Unable to synthesize missing annotation");
    }

    static <A extends Annotation> MergedAnnotation<A> getInstance() {
        return INSTANCE;
    }
}

