/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.values.XmlListImpl;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.DerivationSet;

public class DerivationSetImpl
extends XmlUnionImpl
implements DerivationSet,
DerivationSet.Member,
DerivationSet.Member2 {
    private static final long serialVersionUID = 1L;

    public DerivationSetImpl(SchemaType sType) {
        super(sType, false);
    }

    protected DerivationSetImpl(SchemaType sType, boolean b) {
        super(sType, b);
    }

    public static class MemberImpl2
    extends XmlListImpl
    implements DerivationSet.Member2 {
        private static final long serialVersionUID = 1L;

        public MemberImpl2(SchemaType sType) {
            super(sType, false);
        }

        protected MemberImpl2(SchemaType sType, boolean b) {
            super(sType, b);
        }
    }

    public static class MemberImpl
    extends JavaStringEnumerationHolderEx
    implements DerivationSet.Member {
        private static final long serialVersionUID = 1L;

        public MemberImpl(SchemaType sType) {
            super(sType, false);
        }

        protected MemberImpl(SchemaType sType, boolean b) {
            super(sType, b);
        }
    }
}

