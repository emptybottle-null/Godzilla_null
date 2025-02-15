/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class SelectorDocumentImpl
extends XmlComplexContentImpl
implements SelectorDocument {
    private static final long serialVersionUID = 1L;
    private static final QName SELECTOR$0 = new QName("http://www.w3.org/2001/XMLSchema", "selector");

    public SelectorDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SelectorDocument.Selector getSelector() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SelectorDocument.Selector target = null;
            target = (SelectorDocument.Selector)((Object)this.get_store().find_element_user(SELECTOR$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setSelector(SelectorDocument.Selector selector) {
        this.generatedSetterHelperImpl(selector, SELECTOR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SelectorDocument.Selector addNewSelector() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SelectorDocument.Selector target = null;
            target = (SelectorDocument.Selector)((Object)this.get_store().add_element_user(SELECTOR$0));
            return target;
        }
    }

    public static class SelectorImpl
    extends AnnotatedImpl
    implements SelectorDocument.Selector {
        private static final long serialVersionUID = 1L;
        private static final QName XPATH$0 = new QName("", "xpath");

        public SelectorImpl(SchemaType sType) {
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
        public SelectorDocument.Selector.Xpath xgetXpath() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SelectorDocument.Selector.Xpath target = null;
                target = (SelectorDocument.Selector.Xpath)((Object)this.get_store().find_attribute_user(XPATH$0));
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
        public void xsetXpath(SelectorDocument.Selector.Xpath xpath) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SelectorDocument.Selector.Xpath target = null;
                target = (SelectorDocument.Selector.Xpath)((Object)this.get_store().find_attribute_user(XPATH$0));
                if (target == null) {
                    target = (SelectorDocument.Selector.Xpath)((Object)this.get_store().add_attribute_user(XPATH$0));
                }
                target.set(xpath);
            }
        }

        public static class XpathImpl
        extends JavaStringHolderEx
        implements SelectorDocument.Selector.Xpath {
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

