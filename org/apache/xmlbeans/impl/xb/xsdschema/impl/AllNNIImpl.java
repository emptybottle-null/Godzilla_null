/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AllNNI;

public class AllNNIImpl
extends XmlUnionImpl
implements AllNNI,
XmlNonNegativeInteger,
AllNNI.Member {
    private static final long serialVersionUID = 1L;

    public AllNNIImpl(SchemaType sType) {
        super(sType, false);
    }

    protected AllNNIImpl(SchemaType sType, boolean b) {
        super(sType, b);
    }

    public static class MemberImpl
    extends JavaStringEnumerationHolderEx
    implements AllNNI.Member {
        private static final long serialVersionUID = 1L;

        public MemberImpl(SchemaType sType) {
            super(sType, false);
        }

        protected MemberImpl(SchemaType sType, boolean b) {
            super(sType, b);
        }
    }
}

