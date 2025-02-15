/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xmlconfig.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xmlconfig.NamespaceList;
import org.apache.xmlbeans.impl.xb.xmlconfig.NamespacePrefixList;
import org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig;

public class NsconfigImpl
extends XmlComplexContentImpl
implements Nsconfig {
    private static final long serialVersionUID = 1L;
    private static final QName PACKAGE$0 = new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "package");
    private static final QName PREFIX$2 = new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "prefix");
    private static final QName SUFFIX$4 = new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "suffix");
    private static final QName URI$6 = new QName("", "uri");
    private static final QName URIPREFIX$8 = new QName("", "uriprefix");

    public NsconfigImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getPackage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_element_user(PACKAGE$0, 0));
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
    public XmlString xgetPackage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString target = null;
            target = (XmlString)((Object)this.get_store().find_element_user(PACKAGE$0, 0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPackage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PACKAGE$0) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPackage(String xpackage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_element_user(PACKAGE$0, 0));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_element_user(PACKAGE$0));
            }
            target.setStringValue(xpackage);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPackage(XmlString xpackage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString target = null;
            target = (XmlString)((Object)this.get_store().find_element_user(PACKAGE$0, 0));
            if (target == null) {
                target = (XmlString)((Object)this.get_store().add_element_user(PACKAGE$0));
            }
            target.set(xpackage);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPackage() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PACKAGE$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getPrefix() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_element_user(PREFIX$2, 0));
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
    public XmlString xgetPrefix() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString target = null;
            target = (XmlString)((Object)this.get_store().find_element_user(PREFIX$2, 0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPrefix() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PREFIX$2) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPrefix(String prefix) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_element_user(PREFIX$2, 0));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_element_user(PREFIX$2));
            }
            target.setStringValue(prefix);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPrefix(XmlString prefix) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString target = null;
            target = (XmlString)((Object)this.get_store().find_element_user(PREFIX$2, 0));
            if (target == null) {
                target = (XmlString)((Object)this.get_store().add_element_user(PREFIX$2));
            }
            target.set(prefix);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPrefix() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PREFIX$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getSuffix() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_element_user(SUFFIX$4, 0));
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
    public XmlString xgetSuffix() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString target = null;
            target = (XmlString)((Object)this.get_store().find_element_user(SUFFIX$4, 0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSuffix() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SUFFIX$4) != 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSuffix(String suffix) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_element_user(SUFFIX$4, 0));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_element_user(SUFFIX$4));
            }
            target.setStringValue(suffix);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSuffix(XmlString suffix) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString target = null;
            target = (XmlString)((Object)this.get_store().find_element_user(SUFFIX$4, 0));
            if (target == null) {
                target = (XmlString)((Object)this.get_store().add_element_user(SUFFIX$4));
            }
            target.set(suffix);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSuffix() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SUFFIX$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getUri() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(URI$6));
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
    public NamespaceList xgetUri() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NamespaceList target = null;
            target = (NamespaceList)((Object)this.get_store().find_attribute_user(URI$6));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUri() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(URI$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUri(Object uri) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(URI$6));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(URI$6));
            }
            target.setObjectValue(uri);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUri(NamespaceList uri) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NamespaceList target = null;
            target = (NamespaceList)((Object)this.get_store().find_attribute_user(URI$6));
            if (target == null) {
                target = (NamespaceList)((Object)this.get_store().add_attribute_user(URI$6));
            }
            target.set(uri);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUri() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(URI$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List getUriprefix() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(URIPREFIX$8));
            if (target == null) {
                return null;
            }
            return target.getListValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NamespacePrefixList xgetUriprefix() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NamespacePrefixList target = null;
            target = (NamespacePrefixList)((Object)this.get_store().find_attribute_user(URIPREFIX$8));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUriprefix() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(URIPREFIX$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUriprefix(List uriprefix) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(URIPREFIX$8));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(URIPREFIX$8));
            }
            target.setListValue(uriprefix);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUriprefix(NamespacePrefixList uriprefix) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NamespacePrefixList target = null;
            target = (NamespacePrefixList)((Object)this.get_store().find_attribute_user(URIPREFIX$8));
            if (target == null) {
                target = (NamespacePrefixList)((Object)this.get_store().add_attribute_user(URIPREFIX$8));
            }
            target.set(uriprefix);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUriprefix() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(URIPREFIX$8);
        }
    }
}

