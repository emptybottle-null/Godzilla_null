/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class ListDocumentImpl
extends XmlComplexContentImpl
implements ListDocument {
    private static final long serialVersionUID = 1L;
    private static final QName LIST$0 = new QName("http://www.w3.org/2001/XMLSchema", "list");

    public ListDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ListDocument.List getList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ListDocument.List target = null;
            target = (ListDocument.List)((Object)this.get_store().find_element_user(LIST$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setList(ListDocument.List list) {
        this.generatedSetterHelperImpl(list, LIST$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ListDocument.List addNewList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ListDocument.List target = null;
            target = (ListDocument.List)((Object)this.get_store().add_element_user(LIST$0));
            return target;
        }
    }

    public static class ListImpl
    extends AnnotatedImpl
    implements ListDocument.List {
        private static final long serialVersionUID = 1L;
        private static final QName SIMPLETYPE$0 = new QName("http://www.w3.org/2001/XMLSchema", "simpleType");
        private static final QName ITEMTYPE$2 = new QName("", "itemType");

        public ListImpl(SchemaType sType) {
            super(sType);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public LocalSimpleType getSimpleType() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                LocalSimpleType target = null;
                target = (LocalSimpleType)((Object)this.get_store().find_element_user(SIMPLETYPE$0, 0));
                if (target == null) {
                    return null;
                }
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetSimpleType() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().count_elements(SIMPLETYPE$0) != 0;
            }
        }

        @Override
        public void setSimpleType(LocalSimpleType simpleType) {
            this.generatedSetterHelperImpl(simpleType, SIMPLETYPE$0, 0, (short)1);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public LocalSimpleType addNewSimpleType() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                LocalSimpleType target = null;
                target = (LocalSimpleType)((Object)this.get_store().add_element_user(SIMPLETYPE$0));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetSimpleType() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_element(SIMPLETYPE$0, 0);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public QName getItemType() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(ITEMTYPE$2));
                if (target == null) {
                    return null;
                }
                return target.getQNameValue();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlQName xgetItemType() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlQName target = null;
                target = (XmlQName)((Object)this.get_store().find_attribute_user(ITEMTYPE$2));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetItemType() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(ITEMTYPE$2) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setItemType(QName itemType) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(ITEMTYPE$2));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(ITEMTYPE$2));
                }
                target.setQNameValue(itemType);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetItemType(XmlQName itemType) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlQName target = null;
                target = (XmlQName)((Object)this.get_store().find_attribute_user(ITEMTYPE$2));
                if (target == null) {
                    target = (XmlQName)((Object)this.get_store().add_attribute_user(ITEMTYPE$2));
                }
                target.set(itemType);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetItemType() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(ITEMTYPE$2);
            }
        }
    }
}

