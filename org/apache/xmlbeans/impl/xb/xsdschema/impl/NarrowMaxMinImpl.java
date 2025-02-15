/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.impl.values.JavaIntegerHolderEx;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AllNNI;
import org.apache.xmlbeans.impl.xb.xsdschema.NarrowMaxMin;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.LocalElementImpl;

public class NarrowMaxMinImpl
extends LocalElementImpl
implements NarrowMaxMin {
    private static final long serialVersionUID = 1L;

    public NarrowMaxMinImpl(SchemaType sType) {
        super(sType);
    }

    public static class MaxOccursImpl
    extends XmlUnionImpl
    implements NarrowMaxMin.MaxOccurs,
    XmlNonNegativeInteger,
    AllNNI.Member {
        private static final long serialVersionUID = 1L;

        public MaxOccursImpl(SchemaType sType) {
            super(sType, false);
        }

        protected MaxOccursImpl(SchemaType sType, boolean b) {
            super(sType, b);
        }
    }

    public static class MinOccursImpl
    extends JavaIntegerHolderEx
    implements NarrowMaxMin.MinOccurs {
        private static final long serialVersionUID = 1L;

        public MinOccursImpl(SchemaType sType) {
            super(sType, false);
        }

        protected MinOccursImpl(SchemaType sType, boolean b) {
            super(sType, b);
        }
    }
}

