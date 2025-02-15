/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.FacetImpl;

public class WhiteSpaceDocumentImpl
extends XmlComplexContentImpl
implements WhiteSpaceDocument {
    private static final long serialVersionUID = 1L;
    private static final QName WHITESPACE$0 = new QName("http://www.w3.org/2001/XMLSchema", "whiteSpace");

    public WhiteSpaceDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public WhiteSpaceDocument.WhiteSpace getWhiteSpace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            WhiteSpaceDocument.WhiteSpace target = null;
            target = (WhiteSpaceDocument.WhiteSpace)((Object)this.get_store().find_element_user(WHITESPACE$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setWhiteSpace(WhiteSpaceDocument.WhiteSpace whiteSpace) {
        this.generatedSetterHelperImpl(whiteSpace, WHITESPACE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public WhiteSpaceDocument.WhiteSpace addNewWhiteSpace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            WhiteSpaceDocument.WhiteSpace target = null;
            target = (WhiteSpaceDocument.WhiteSpace)((Object)this.get_store().add_element_user(WHITESPACE$0));
            return target;
        }
    }

    public static class WhiteSpaceImpl
    extends FacetImpl
    implements WhiteSpaceDocument.WhiteSpace {
        private static final long serialVersionUID = 1L;

        public WhiteSpaceImpl(SchemaType sType) {
            super(sType);
        }

        public static class ValueImpl
        extends JavaStringEnumerationHolderEx
        implements WhiteSpaceDocument.WhiteSpace.Value {
            private static final long serialVersionUID = 1L;

            public ValueImpl(SchemaType sType) {
                super(sType, false);
            }

            protected ValueImpl(SchemaType sType, boolean b) {
                super(sType, b);
            }
        }
    }
}

