/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.IOException;
import java.io.Writer;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeSystem;

public interface SchemaCodePrinter {
    public void printTypeImpl(Writer var1, SchemaType var2) throws IOException;

    public void printType(Writer var1, SchemaType var2) throws IOException;

    public void printLoader(Writer var1, SchemaTypeSystem var2) throws IOException;
}

