/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.classrefs;

import org.apache.xmlbeans.impl.jam.JamClassLoader;

public interface JClassRefContext {
    public String getPackageName();

    public String[] getImportSpecs();

    public JamClassLoader getClassLoader();
}

