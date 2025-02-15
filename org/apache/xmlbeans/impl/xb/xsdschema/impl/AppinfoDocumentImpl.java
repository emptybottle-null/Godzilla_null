/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument;

public class AppinfoDocumentImpl
extends XmlComplexContentImpl
implements AppinfoDocument {
    private static final long serialVersionUID = 1L;
    private static final QName APPINFO$0 = new QName("http://www.w3.org/2001/XMLSchema", "appinfo");

    public AppinfoDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AppinfoDocument.Appinfo getAppinfo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AppinfoDocument.Appinfo target = null;
            target = (AppinfoDocument.Appinfo)((Object)this.get_store().find_element_user(APPINFO$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setAppinfo(AppinfoDocument.Appinfo appinfo) {
        this.generatedSetterHelperImpl(appinfo, APPINFO$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AppinfoDocument.Appinfo addNewAppinfo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AppinfoDocument.Appinfo target = null;
            target = (AppinfoDocument.Appinfo)((Object)this.get_store().add_element_user(APPINFO$0));
            return target;
        }
    }

    public static class AppinfoImpl
    extends XmlComplexContentImpl
    implements AppinfoDocument.Appinfo {
        private static final long serialVersionUID = 1L;
        private static final QName SOURCE$0 = new QName("", "source");

        public AppinfoImpl(SchemaType sType) {
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
    }
}

