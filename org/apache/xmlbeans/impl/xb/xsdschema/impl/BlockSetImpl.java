/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.values.XmlListImpl;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.BlockSet;

public class BlockSetImpl
extends XmlUnionImpl
implements BlockSet,
BlockSet.Member,
BlockSet.Member2 {
    private static final long serialVersionUID = 1L;

    public BlockSetImpl(SchemaType sType) {
        super(sType, false);
    }

    protected BlockSetImpl(SchemaType sType, boolean b) {
        super(sType, b);
    }

    public static class MemberImpl2
    extends XmlListImpl
    implements BlockSet.Member2 {
        private static final long serialVersionUID = 1L;

        public MemberImpl2(SchemaType sType) {
            super(sType, false);
        }

        protected MemberImpl2(SchemaType sType, boolean b) {
            super(sType, b);
        }

        public static class ItemImpl
        extends JavaStringEnumerationHolderEx
        implements BlockSet.Member2.Item {
            private static final long serialVersionUID = 1L;

            public ItemImpl(SchemaType sType) {
                super(sType, false);
            }

            protected ItemImpl(SchemaType sType, boolean b) {
                super(sType, b);
            }
        }
    }

    public static class MemberImpl
    extends JavaStringEnumerationHolderEx
    implements BlockSet.Member {
        private static final long serialVersionUID = 1L;

        public MemberImpl(SchemaType sType) {
            super(sType, false);
        }

        protected MemberImpl(SchemaType sType, boolean b) {
            super(sType, b);
        }
    }
}

