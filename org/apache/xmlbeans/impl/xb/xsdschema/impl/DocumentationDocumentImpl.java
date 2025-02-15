/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlLanguage;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;

public class DocumentationDocumentImpl
extends XmlComplexContentImpl
implements DocumentationDocument {
    private static final long serialVersionUID = 1L;
    private static final QName DOCUMENTATION$0 = new QName("http://www.w3.org/2001/XMLSchema", "documentation");

    public DocumentationDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DocumentationDocument.Documentation getDocumentation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DocumentationDocument.Documentation target = null;
            target = (DocumentationDocument.Documentation)((Object)this.get_store().find_element_user(DOCUMENTATION$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setDocumentation(DocumentationDocument.Documentation documentation) {
        this.generatedSetterHelperImpl(documentation, DOCUMENTATION$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DocumentationDocument.Documentation addNewDocumentation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DocumentationDocument.Documentation target = null;
            target = (DocumentationDocument.Documentation)((Object)this.get_store().add_element_user(DOCUMENTATION$0));
            return target;
        }
    }

    public static class DocumentationImpl
    extends XmlComplexContentImpl
    implements DocumentationDocument.Documentation {
        private static final long serialVersionUID = 1L;
        private static final QName SOURCE$0 = new QName("", "source");
        private static final QName LANG$2 = new QName("http://www.w3.org/XML/1998/namespace", "lang");

        public DocumentationImpl(SchemaType sType) {
            super(sType);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getSource() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(SOURCE$0));
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
        public XmlAnyURI xgetSource() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlAnyURI target = null;
                target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(SOURCE$0));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetSource() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(SOURCE$0) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setSource(String source) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(SOURCE$0));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(SOURCE$0));
                }
                target.setStringValue(source);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetSource(XmlAnyURI source) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlAnyURI target = null;
                target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(SOURCE$0));
                if (target == null) {
                    target = (XmlAnyURI)((Object)this.get_store().add_attribute_user(SOURCE$0));
                }
                target.set(source);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetSource() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(SOURCE$0);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getLang() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(LANG$2));
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
        public XmlLanguage xgetLang() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlLanguage target = null;
                target = (XmlLanguage)((Object)this.get_store().find_attribute_user(LANG$2));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetLang() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(LANG$2) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setLang(String lang) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(LANG$2));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(LANG$2));
                }
                target.setStringValue(lang);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetLang(XmlLanguage lang) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlLanguage target = null;
                target = (XmlLanguage)((Object)this.get_store().find_attribute_user(LANG$2));
                if (target == null) {
                    target = (XmlLanguage)((Object)this.get_store().add_attribute_user(LANG$2));
                }
                target.set(lang);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetLang() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(LANG$2);
            }
        }
    }
}

