/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest;

import java.util.Arrays;
import java.util.Iterator;
import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.hamcrest.internal.ArrayIterator;
import org.hamcrest.internal.SelfDescribingValueIterator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class BaseDescription
implements Description {
    @Override
    public Description appendText(String text) {
        this.append(text);
        return this;
    }

    @Override
    public Description appendDescriptionOf(SelfDescribing value) {
        value.describeTo(this);
        return this;
    }

    @Override
    public Description appendValue(Object value) {
        if (value == null) {
            this.append("null");
        } else if (value instanceof String) {
            this.toJavaSyntax((String)value);
        } else if (value instanceof Character) {
            this.append('\"');
            this.toJavaSyntax(((Character)value).charValue());
            this.append('\"');
        } else if (value instanceof Short) {
            this.append('<');
            this.append(this.descriptionOf(value));
            this.append("s>");
        } else if (value instanceof Long) {
            this.append('<');
            this.append(this.descriptionOf(value));
            this.append("L>");
        } else if (value instanceof Float) {
            this.append('<');
            this.append(this.descriptionOf(value));
            this.append("F>");
        } else if (value.getClass().isArray()) {
            this.appendValueList("[", ", ", "]", new ArrayIterator(value));
        } else {
            this.append('<');
            this.append(this.descriptionOf(value));
            this.append('>');
        }
        return this;
    }

    private String descriptionOf(Object value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            return value.getClass().getName() + "@" + Integer.toHexString(value.hashCode());
        }
    }

    @Override
    public <T> Description appendValueList(String start, String separator, String end, T ... values) {
        return this.appendValueList(start, separator, end, (Iterable<T>)Arrays.asList(values));
    }

    @Override
    public <T> Description appendValueList(String start, String separator, String end, Iterable<T> values) {
        return this.appendValueList(start, separator, end, values.iterator());
    }

    private <T> Description appendValueList(String start, String separator, String end, Iterator<T> values) {
        return this.appendList(start, separator, end, new SelfDescribingValueIterator<T>(values));
    }

    @Override
    public Description appendList(String start, String separator, String end, Iterable<? extends SelfDescribing> values) {
        return this.appendList(start, separator, end, values.iterator());
    }

    private Description appendList(String start, String separator, String end, Iterator<? extends SelfDescribing> i) {
        boolean separate = false;
        this.append(start);
        while (i.hasNext()) {
            if (separate) {
                this.append(separator);
            }
            this.appendDescriptionOf(i.next());
            separate = true;
        }
        this.append(end);
        return this;
    }

    protected void append(String str) {
        for (int i = 0; i < str.length(); ++i) {
            this.append(str.charAt(i));
        }
    }

    protected abstract void append(char var1);

    private void toJavaSyntax(String unformatted) {
        this.append('\"');
        for (int i = 0; i < unformatted.length(); ++i) {
            this.toJavaSyntax(unformatted.charAt(i));
        }
        this.append('\"');
    }

    private void toJavaSyntax(char ch) {
        switch (ch) {
            case '\"': {
                this.append("\\\"");
                break;
            }
            case '\n': {
                this.append("\\n");
                break;
            }
            case '\r': {
                this.append("\\r");
                break;
            }
            case '\t': {
                this.append("\\t");
                break;
            }
            default: {
                this.append(ch);
            }
        }
    }
}

