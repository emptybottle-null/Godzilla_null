/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class NotationDocumentImpl
extends XmlComplexContentImpl
implements NotationDocument {
    private static final long serialVersionUID = 1L;
    private static final QName NOTATION$0 = new QName("http://www.w3.org/2001/XMLSchema", "notation");

    public NotationDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NotationDocument.Notation getNotation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NotationDocument.Notation target = null;
            target = (NotationDocument.Notation)((Object)this.get_store().find_element_user(NOTATION$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setNotation(NotationDocument.Notation notation) {
        this.generatedSetterHelperImpl(notation, NOTATION$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NotationDocument.Notation addNewNotation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NotationDocument.Notation target = null;
            target = (NotationDocument.Notation)((Object)this.get_store().add_element_user(NOTATION$0));
            return target;
        }
    }

    public static class NotationImpl
    extends AnnotatedImpl
    implements NotationDocument.Notation {
        private static final long serialVersionUID = 1L;
        private static final QName NAME$0 = new QName("", "name");
        private static final QName PUBLIC$2 = new QName("", "public");
        private static final QName SYSTEM$4 = new QName("", "system");

        public NotationImpl(SchemaType sType) {
            super(sType);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getName() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$0));
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
        public XmlNCName xgetName() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlNCName target = null;
                target = (XmlNCName)((Object)this.get_store().find_attribute_user(NAME$0));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setName(String name) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$0));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$0));
                }
                target.setStringValue(name);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetName(XmlNCName name) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlNCName target = null;
                target = (XmlNCName)((Object)this.get_store().find_attribute_user(NAME$0));
                if (target == null) {
                    target = (XmlNCName)((Object)this.get_store().add_attribute_user(NAME$0));
                }
                target.set(name);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getPublic() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(PUBLIC$2));
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
        public Public xgetPublic() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                Public target = null;
                target = (Public)((Object)this.get_store().find_attribute_user(PUBLIC$2));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetPublic() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(PUBLIC$2) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setPublic(String xpublic) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(PUBLIC$2));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(PUBLIC$2));
                }
                target.setStringValue(xpublic);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetPublic(Public xpublic) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                Public target = null;
                target = (Public)((Object)this.get_store().find_attribute_user(PUBLIC$2));
                if (target == null) {
                    target = (Public)((Object)this.get_store().add_attribute_user(PUBLIC$2));
                }
                target.set(xpublic);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetPublic() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(PUBLIC$2);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public String getSystem() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(SYSTEM$4));
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
        public XmlAnyURI xgetSystem() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlAnyURI target = null;
                target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(SYSTEM$4));
                return target;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public boolean isSetSystem() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                return this.get_store().find_attribute_user(SYSTEM$4) != null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void setSystem(String system) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                SimpleValue target = null;
                target = (SimpleValue)((Object)this.get_store().find_attribute_user(SYSTEM$4));
                if (target == null) {
                    target = (SimpleValue)((Object)this.get_store().add_attribute_user(SYSTEM$4));
                }
                target.setStringValue(system);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void xsetSystem(XmlAnyURI system) {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                XmlAnyURI target = null;
                target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(SYSTEM$4));
                if (target == null) {
                    target = (XmlAnyURI)((Object)this.get_store().add_attribute_user(SYSTEM$4));
                }
                target.set(system);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void unsetSystem() {
            Object object = this.monitor();
            synchronized (object) {
                this.check_orphaned();
                this.get_store().remove_attribute(SYSTEM$4);
            }
        }
    }
}

