/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import org.apache.xmlbeans.impl.jam.JamClassLoader;
import org.apache.xmlbeans.impl.jam.annotation.AnnotationProxy;
import org.apache.xmlbeans.impl.jam.provider.JamLogger;

public interface ElementContext
extends JamLogger {
    public JamLogger getLogger();

    public JamClassLoader getClassLoader();

    public AnnotationProxy createAnnotationProxy(String var1);
}

