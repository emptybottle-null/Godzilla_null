/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AllNNI;
import org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.WildcardImpl;

public class AnyDocumentImpl
extends XmlComplexContentImpl
implements AnyDocument {
    private static final long serialVersionUID = 1L;
    private static final QName ANY$0 = new QName("http://www.w3.org/2001/XMLSchema", "any");

    public AnyDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnyDocument.Any getAny() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AnyDocument.Any target = null;
            target = (AnyDocument.Any)((Object)this.get_store().find_element_user(ANY$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setAny(AnyDocument.Any any) {
        this.generatedSetterHelperImpl(any, ANY$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnyDocument.Any addNewAny() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AnyDocument.Any target = null;
            target = (AnyDocument.Any)((Object)this.get_store().add_element_user(ANY$0));
            return target;
        }
    }

    public static class AnyImpl
    extends WildcardImpl
    implements AnyDocument.Any {
        private static final long serialVersionUID = 1L;
        private static final QName MINOCCURS$0 = new QName("", "minOccurs");
        private static final QName MAXOCCURS$2 = new QName("", "maxOccurs");

        public AnyImpl(SchemaType sType) {
            super(sType);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public BigInteger getMinOccurs() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(MINOCCURS$0));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_default_attribute_value(MINOCCURS$0));
                }
                if (target == null) {
                    return null;
                }
                return target.getBigIntegerValue();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public XmlNonNegativeInteger xgetMinOccurs() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlNonNegativeInteger target = null;
                target = (XmlNonNegativeInteger)((Object)this.get_store().find_attribute_user(MINOCCURS$0));
                if (target == null) {
                    target = (XmlNonNegativeInteger)this.get_default_attribute_value(MINOCCURS$0);
                }
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetMinOccurs() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(MINOCCURS$0) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setMinOccurs(BigInteger minOccurs) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(MINOCCURS$0));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(MINOCCURS$0));
                }
                target.setBigIntegerValue(minOccurs);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetMinOccurs(XmlNonNegativeInteger minOccurs) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlNonNegativeInteger target = null;
                target = (XmlNonNegativeInteger)((Object)this.get_store().find_attribute_user(MINOCCURS$0));
                if (target == null) {
                    target = (XmlNonNegativeInteger)((Object)this.get_store().add_attribute_user(MINOCCURS$0));
                }
                target.set(minOccurs);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetMinOccurs() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(MINOCCURS$0);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Object getMaxOccurs() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(MAXOCCURS$2));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_default_attribute_value(MAXOCCURS$2));
                }
                if (target == null) {
                    return null;
                }
                return target.getObjectValue();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public AllNNI xgetMaxOccurs() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                AllNNI target = null;
                target = (AllNNI)((Object)this.get_store().find_attribute_user(MAXOCCURS$2));
                if (target == null) {
                    target = (AllNNI)this.get_default_attribute_value(MAXOCCURS$2);
                }
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetMaxOccurs() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(MAXOCCURS$2) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setMaxOccurs(Object maxOccurs) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(MAXOCCURS$2));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(MAXOCCURS$2));
                }
                target.setObjectValue(maxOccurs);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetMaxOccurs(AllNNI maxOccurs) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                AllNNI target = null;
                target = (AllNNI)((Object)this.get_store().find_attribute_user(MAXOCCURS$2));
                if (target == null) {
                    target = (AllNNI)((Object)this.get_store().add_attribute_user(MAXOCCURS$2));
                }
                target.set(maxOccurs);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetMaxOccurs() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(MAXOCCURS$2);
            }
        }
    }
}

