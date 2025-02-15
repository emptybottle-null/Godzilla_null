/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.provider;

import java.io.File;
import java.io.IOException;
import org.apache.xmlbeans.impl.jam.annotation.JavadocTagParser;
import org.apache.xmlbeans.impl.jam.provider.JamClassBuilder;
import org.apache.xmlbeans.impl.jam.provider.JamLogger;
import org.apache.xmlbeans.impl.jam.provider.ResourcePath;
import org.apache.xmlbeans.impl.jam.visitor.MVisitor;

public interface JamServiceContext
extends JamLogger {
    public ResourcePath getInputClasspath();

    public ResourcePath getInputSourcepath();

    public ResourcePath getToolClasspath();

    public String getProperty(String var1);

    public MVisitor getInitializer();

    public ClassLoader[] getReflectionClassLoaders();

    public File[] getSourceFiles() throws IOException;

    public String[] getAllClassnames() throws IOException;

    public JamLogger getLogger();

    public JamClassBuilder getBaseBuilder();

    public JavadocTagParser getTagParser();

    public boolean is14WarningsEnabled();
}

