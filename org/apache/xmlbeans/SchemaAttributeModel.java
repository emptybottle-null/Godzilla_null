/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaLocalAttribute;

public interface SchemaAttributeModel {
    public static final int NONE = 0;
    public static final int STRICT = 1;
    public static final int LAX = 2;
    public static final int SKIP = 3;

    public SchemaLocalAttribute[] getAttributes();

    public SchemaLocalAttribute getAttribute(QName var1);

    public QNameSet getWildcardSet();

    public int getWildcardProcess();
}

