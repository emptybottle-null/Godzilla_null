/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.tool;

import java.util.Map;
import org.apache.xmlbeans.SchemaTypeSystem;

public interface SchemaCompilerExtension {
    public void schemaCompilerExtension(SchemaTypeSystem var1, Map var2);

    public String getExtensionName();
}

