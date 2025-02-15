/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Matcher<T>
extends SelfDescribing {
    public boolean matches(Object var1);

    public void describeMismatch(Object var1, Description var2);

    @Deprecated
    public void _dont_implement_Matcher___instead_extend_BaseMatcher_();
}

