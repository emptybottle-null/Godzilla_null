/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import java.util.Collection;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Parameterizable {
    public Collection<String> getParametersNames();

    public boolean isSupported(String var1);
}

