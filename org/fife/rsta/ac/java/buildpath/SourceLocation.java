/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.buildpath;

import java.io.IOException;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.rjc.ast.CompilationUnit;

public interface SourceLocation {
    public CompilationUnit getCompilationUnit(ClassFile var1) throws IOException;

    public String getLocationAsString();
}

