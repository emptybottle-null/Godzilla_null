/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class IncludeDocumentImpl
extends XmlComplexContentImpl
implements IncludeDocument {
    private static final long serialVersionUID = 1L;
    private static final QName INCLUDE$0 = new QName("http://www.w3.org/2001/XMLSchema", "include");

    public IncludeDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public IncludeDocument.Include getInclude() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            IncludeDocument.Include target = null;
            target = (IncludeDocument.Include)((Object)this.get_store().find_element_user(INCLUDE$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setInclude(IncludeDocument.Include include) {
        this.generatedSetterHelperImpl(include, INCLUDE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public IncludeDocument.Include addNewInclude() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            IncludeDocument.Include target = null;
            target = (IncludeDocument.Include)((Object)this.get_store().add_element_user(INCLUDE$0));
            return target;
        }
    }

    public static class IncludeImpl
    extends AnnotatedImpl
    implements IncludeDocument.Include {
        private static final long serialVersionUID = 1L;
        private static final QName SCHEMALOCATION$0 = new QName("", "schemaLocation");

        public IncludeImpl(SchemaType sType) {
            super(sType);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getSchemaLocation() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(SCHEMALOCATION$0));
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
        public XmlAnyURI xgetSchemaLocation() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlAnyURI target = null;
                target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(SCHEMALOCATION$0));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setSchemaLocation(String schemaLocation) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(SCHEMALOCATION$0));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(SCHEMALOCATION$0));
                }
                target.setStringValue(schemaLocation);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetSchemaLocation(XmlAnyURI schemaLocation) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlAnyURI target = null;
                target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(SCHEMALOCATION$0));
                if (target == null) {
                    target = (XmlAnyURI)((Object)this.get_store().add_attribute_user(SCHEMALOCATION$0));
                }
                target.set(schemaLocation);
            }
        }
    }
}

