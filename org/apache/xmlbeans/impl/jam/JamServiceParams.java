/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam;

import java.io.File;
import java.io.PrintWriter;
import org.apache.xmlbeans.impl.jam.JamClassLoader;
import org.apache.xmlbeans.impl.jam.annotation.JavadocTagParser;
import org.apache.xmlbeans.impl.jam.provider.JamClassBuilder;
import org.apache.xmlbeans.impl.jam.provider.JamLogger;
import org.apache.xmlbeans.impl.jam.visitor.MVisitor;

public interface JamServiceParams {
    public void includeSourceFile(File var1);

    public void includeSourcePattern(File[] var1, String var2);

    public void excludeSourcePattern(File[] var1, String var2);

    public void includeClassPattern(File[] var1, String var2);

    public void excludeClassPattern(File[] var1, String var2);

    public void includeSourceFile(File[] var1, File var2);

    public void excludeSourceFile(File[] var1, File var2);

    public void includeClassFile(File[] var1, File var2);

    public void excludeClassFile(File[] var1, File var2);

    public void includeClass(String var1);

    public void excludeClass(String var1);

    public void addSourcepath(File var1);

    public void addClasspath(File var1);

    public void setLoggerWriter(PrintWriter var1);

    public void setJamLogger(JamLogger var1);

    public void setVerbose(Class var1);

    public void setShowWarnings(boolean var1);

    public void setParentClassLoader(JamClassLoader var1);

    public void addToolClasspath(File var1);

    public void setPropertyInitializer(MVisitor var1);

    public void addInitializer(MVisitor var1);

    public void setJavadocTagParser(JavadocTagParser var1);

    public void setUseSystemClasspath(boolean var1);

    public void addClassBuilder(JamClassBuilder var1);

    public void addClassLoader(ClassLoader var1);

    public void setProperty(String var1, String var2);

    public void set14WarningsEnabled(boolean var1);

    public void setVerbose(boolean var1);
}

