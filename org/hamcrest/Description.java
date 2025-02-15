/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest;

import org.hamcrest.SelfDescribing;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Description {
    public static final Description NONE = new NullDescription();

    public Description appendText(String var1);

    public Description appendDescriptionOf(SelfDescribing var1);

    public Description appendValue(Object var1);

    public <T> Description appendValueList(String var1, String var2, String var3, T ... var4);

    public <T> Description appendValueList(String var1, String var2, String var3, Iterable<T> var4);

    public Description appendList(String var1, String var2, String var3, Iterable<? extends SelfDescribing> var4);

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class NullDescription
    implements Description {
        @Override
        public Description appendDescriptionOf(SelfDescribing value) {
            return this;
        }

        @Override
        public Description appendList(String start, String separator, String end, Iterable<? extends SelfDescribing> values) {
            return this;
        }

        @Override
        public Description appendText(String text) {
            return this;
        }

        @Override
        public Description appendValue(Object value) {
            return this;
        }

        @Override
        public <T> Description appendValueList(String start, String separator, String end, T ... values) {
            return this;
        }

        @Override
        public <T> Description appendValueList(String start, String separator, String end, Iterable<T> values) {
            return this;
        }

        public String toString() {
            return "";
        }
    }
}

