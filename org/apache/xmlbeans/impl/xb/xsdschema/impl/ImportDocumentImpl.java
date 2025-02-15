/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class ImportDocumentImpl
extends XmlComplexContentImpl
implements ImportDocument {
    private static final long serialVersionUID = 1L;
    private static final QName IMPORT$0 = new QName("http://www.w3.org/2001/XMLSchema", "import");

    public ImportDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ImportDocument.Import getImport() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ImportDocument.Import target = null;
            target = (ImportDocument.Import)((Object)this.get_store().find_element_user(IMPORT$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setImport(ImportDocument.Import ximport) {
        this.generatedSetterHelperImpl(ximport, IMPORT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ImportDocument.Import addNewImport() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ImportDocument.Import target = null;
            target = (ImportDocument.Import)((Object)this.get_store().add_element_user(IMPORT$0));
            return target;
        }
    }

    public static class ImportImpl
    extends AnnotatedImpl
    implements ImportDocument.Import {
        private static final long serialVersionUID = 1L;
        private static final QName NAMESPACE$0 = new QName("", "namespace");
        private static final QName SCHEMALOCATION$2 = new QName("", "schemaLocation");

        public ImportImpl(SchemaType sType) {
            super(sType);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getNamespace() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAMESPACE$0));
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
        public XmlAnyURI xgetNamespace() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlAnyURI target = null;
                target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(NAMESPACE$0));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetNamespace() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(NAMESPACE$0) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setNamespace(String namespace) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAMESPACE$0));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(NAMESPACE$0));
                }
                target.setStringValue(namespace);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetNamespace(XmlAnyURI namespace) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlAnyURI target = null;
                target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(NAMESPACE$0));
                if (target == null) {
                    target = (XmlAnyURI)((Object)this.get_store().add_attribute_user(NAMESPACE$0));
                }
                target.set(namespace);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetNamespace() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(NAMESPACE$0);
            }
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
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(SCHEMALOCATION$2));
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
                target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(SCHEMALOCATION$2));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetSchemaLocation() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(SCHEMALOCATION$2) != null;
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
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(SCHEMALOCATION$2));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(SCHEMALOCATION$2));
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
                target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(SCHEMALOCATION$2));
                if (target == null) {
                    target = (XmlAnyURI)((Object)this.get_store().add_attribute_user(SCHEMALOCATION$2));
                }
                target.set(schemaLocation);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetSchemaLocation() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(SCHEMALOCATION$2);
            }
        }
    }
}

