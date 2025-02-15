/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.XmlError;

public class XmlValueOutOfRangeException
extends IllegalArgumentException {
    public XmlValueOutOfRangeException() {
    }

    public XmlValueOutOfRangeException(String message) {
        super(message);
    }

    public XmlValueOutOfRangeException(String code, Object[] args) {
        super(XmlError.formattedMessage(code, args));
    }
}

