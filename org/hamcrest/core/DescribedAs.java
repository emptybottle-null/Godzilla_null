/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.core;

import java.util.regex.Pattern;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class DescribedAs<T>
extends BaseMatcher<T> {
    private final String descriptionTemplate;
    private final Matcher<T> matcher;
    private final Object[] values;
    private static final Pattern ARG_PATTERN = Pattern.compile("%([0-9]+)");

    public DescribedAs(String descriptionTemplate, Matcher<T> matcher, Object[] values) {
        this.descriptionTemplate = descriptionTemplate;
        this.matcher = matcher;
        this.values = (Object[])values.clone();
    }

    @Override
    public boolean matches(Object o) {
        return this.matcher.matches(o);
    }

    @Override
    public void describeTo(Description description) {
        java.util.regex.Matcher arg = ARG_PATTERN.matcher(this.descriptionTemplate);
        int textStart = 0;
        while (arg.find()) {
            description.appendText(this.descriptionTemplate.substring(textStart, arg.start()));
            description.appendValue(this.values[Integer.parseInt(arg.group(1))]);
            textStart = arg.end();
        }
        if (textStart < this.descriptionTemplate.length()) {
            description.appendText(this.descriptionTemplate.substring(textStart));
        }
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        this.matcher.describeMismatch(item, description);
    }

    @Factory
    public static <T> Matcher<T> describedAs(String description, Matcher<T> matcher, Object ... values) {
        return new DescribedAs<T>(description, matcher, values);
    }
}

