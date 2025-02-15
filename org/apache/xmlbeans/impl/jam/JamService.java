/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam;

import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JamClassIterator;
import org.apache.xmlbeans.impl.jam.JamClassLoader;

public interface JamService {
    public JamClassLoader getClassLoader();

    public String[] getClassNames();

    public JamClassIterator getClasses();

    public JClass[] getAllClasses();
}

