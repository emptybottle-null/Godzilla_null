/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MatcherAssert {
    public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
        MatcherAssert.assertThat("", actual, matcher);
    }

    public static <T> void assertThat(String reason, T actual, Matcher<? super T> matcher) {
        if (!matcher.matches(actual)) {
            StringDescription description = new StringDescription();
            description.appendText(reason).appendText("\nExpected: ").appendDescriptionOf(matcher).appendText("\n     but: ");
            matcher.describeMismatch(actual, description);
            throw new AssertionError((Object)((Object)description).toString());
        }
    }

    public static void assertThat(String reason, boolean assertion) {
        if (!assertion) {
            throw new AssertionError((Object)reason);
        }
    }
}

