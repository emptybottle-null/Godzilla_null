/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.common.XMLChar;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;

public class XmlNCNameImpl
extends JavaStringHolderEx
implements XmlNCName {
    public XmlNCNameImpl() {
        super(XmlNCName.type, false);
    }

    public XmlNCNameImpl(SchemaType type, boolean complex) {
        super(type, complex);
    }

    public static void validateLexical(String v, ValidationContext context) {
        if (!XMLChar.isValidNCName(v)) {
            context.invalid("NCName", new Object[]{v});
            return;
        }
    }
}

