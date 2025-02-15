/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaField;

public interface TypeStoreVisitor {
    public boolean visit(QName var1);

    public int get_elementflags();

    public String get_default_text();

    public SchemaField get_schema_field();
}

