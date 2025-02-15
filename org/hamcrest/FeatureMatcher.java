/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.internal.ReflectiveTypeFinder;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class FeatureMatcher<T, U>
extends TypeSafeDiagnosingMatcher<T> {
    private static final ReflectiveTypeFinder TYPE_FINDER = new ReflectiveTypeFinder("featureValueOf", 1, 0);
    private final Matcher<? super U> subMatcher;
    private final String featureDescription;
    private final String featureName;

    public FeatureMatcher(Matcher<? super U> subMatcher, String featureDescription, String featureName) {
        super(TYPE_FINDER);
        this.subMatcher = subMatcher;
        this.featureDescription = featureDescription;
        this.featureName = featureName;
    }

    protected abstract U featureValueOf(T var1);

    @Override
    protected boolean matchesSafely(T actual, Description mismatch) {
        U featureValue = this.featureValueOf(actual);
        if (!this.subMatcher.matches(featureValue)) {
            mismatch.appendText(this.featureName).appendText(" ");
            this.subMatcher.describeMismatch(featureValue, mismatch);
            return false;
        }
        return true;
    }

    @Override
    public final void describeTo(Description description) {
        description.appendText(this.featureDescription).appendText(" ").appendDescriptionOf(this.subMatcher);
    }
}

