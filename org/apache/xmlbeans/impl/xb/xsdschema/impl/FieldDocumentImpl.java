/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class FieldDocumentImpl
extends XmlComplexContentImpl
implements FieldDocument {
    private static final long serialVersionUID = 1L;
    private static final QName FIELD$0 = new QName("http://www.w3.org/2001/XMLSchema", "field");

    public FieldDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public FieldDocument.Field getField() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            FieldDocument.Field target = null;
            target = (FieldDocument.Field)((Object)this.get_store().find_element_user(FIELD$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setField(FieldDocument.Field field) {
        this.generatedSetterHelperImpl(field, FIELD$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public FieldDocument.Field addNewField() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            FieldDocument.Field target = null;
            target = (FieldDocument.Field)((Object)this.get_store().add_element_user(FIELD$0));
            return target;
        }
    }

    public static class FieldImpl
    extends AnnotatedImpl
    implements FieldDocument.Field {
        private static final long serialVersionUID = 1L;
        private static final QName XPATH$0 = new QName("", "xpath");

        public FieldImpl(SchemaType sType) {
            super(sType);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getXpath() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(XPATH$0));
                if (target == null) {
                    return null;
                }
                return target.getStringValue();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public FieldDocument.Field.Xpath xgetXpath() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                FieldDocument.Field.Xpath target = null;
                target = (FieldDocument.Field.Xpath)((Object)this.get_store().find_attribute_user(XPATH$0));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setXpath(String xpath) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(XPATH$0));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(XPATH$0));
                }
                target.setStringValue(xpath);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetXpath(FieldDocument.Field.Xpath xpath) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                FieldDocument.Field.Xpath target = null;
                target = (FieldDocument.Field.Xpath)((Object)this.get_store().find_attribute_user(XPATH$0));
                if (target == null) {
                    target = (FieldDocument.Field.Xpath)((Object)this.get_store().add_attribute_user(XPATH$0));
                }
                target.set(xpath);
            }
        }

        public static class XpathImpl
        extends JavaStringHolderEx
        implements FieldDocument.Field.Xpath {
            private static final long serialVersionUID = 1L;

            public XpathImpl(SchemaType sType) {
                super(sType, false);
            }

            protected XpathImpl(SchemaType sType, boolean b) {
                super(sType, b);
            }
        }
    }
}

