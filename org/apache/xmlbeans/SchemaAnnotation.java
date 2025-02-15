/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.XmlObject;

public interface SchemaAnnotation
extends SchemaComponent {
    public XmlObject[] getApplicationInformation();

    public XmlObject[] getUserInformation();

    public Attribute[] getAttributes();

    public static interface Attribute {
        public QName getName();

        public String getValue();

        public String getValueUri();
    }
}

