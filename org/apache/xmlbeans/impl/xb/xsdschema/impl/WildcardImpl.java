/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.xb.xsdschema.NamespaceList;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class WildcardImpl
extends AnnotatedImpl
implements Wildcard {
    private static final long serialVersionUID = 1L;
    private static final QName NAMESPACE$0 = new QName("", "namespace");
    private static final QName PROCESSCONTENTS$2 = new QName("", "processContents");

    public WildcardImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getNamespace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAMESPACE$0));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_default_attribute_value(NAMESPACE$0));
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
    public NamespaceList xgetNamespace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NamespaceList target = null;
            target = (NamespaceList)((Object)this.get_store().find_attribute_user(NAMESPACE$0));
            if (target == null) {
                target = (NamespaceList)this.get_default_attribute_value(NAMESPACE$0);
            }
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
    public void setNamespace(Object namespace) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAMESPACE$0));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(NAMESPACE$0));
            }
            target.setObjectValue(namespace);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetNamespace(NamespaceList namespace) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NamespaceList target = null;
            target = (NamespaceList)((Object)this.get_store().find_attribute_user(NAMESPACE$0));
            if (target == null) {
                target = (NamespaceList)((Object)this.get_store().add_attribute_user(NAMESPACE$0));
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
    public Wildcard.ProcessContents.Enum getProcessContents() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(PROCESSCONTENTS$2));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_default_attribute_value(PROCESSCONTENTS$2));
            }
            if (target == null) {
                return null;
            }
            return (Wildcard.ProcessContents.Enum)target.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Wildcard.ProcessContents xgetProcessContents() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Wildcard.ProcessContents target = null;
            target = (Wildcard.ProcessContents)((Object)this.get_store().find_attribute_user(PROCESSCONTENTS$2));
            if (target == null) {
                target = (Wildcard.ProcessContents)this.get_default_attribute_value(PROCESSCONTENTS$2);
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetProcessContents() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(PROCESSCONTENTS$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setProcessContents(Wildcard.ProcessContents.Enum processContents) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(PROCESSCONTENTS$2));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(PROCESSCONTENTS$2));
            }
            target.setEnumValue(processContents);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetProcessContents(Wildcard.ProcessContents processContents) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Wildcard.ProcessContents target = null;
            target = (Wildcard.ProcessContents)((Object)this.get_store().find_attribute_user(PROCESSCONTENTS$2));
            if (target == null) {
                target = (Wildcard.ProcessContents)((Object)this.get_store().add_attribute_user(PROCESSCONTENTS$2));
            }
            target.set(processContents);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetProcessContents() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(PROCESSCONTENTS$2);
        }
    }

    public static class ProcessContentsImpl
    extends JavaStringEnumerationHolderEx
    implements Wildcard.ProcessContents {
        private static final long serialVersionUID = 1L;

        public ProcessContentsImpl(SchemaType sType) {
            super(sType, false);
        }

        protected ProcessContentsImpl(SchemaType sType, boolean b) {
            super(sType, b);
        }
    }
}

