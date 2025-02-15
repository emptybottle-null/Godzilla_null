/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdownload.impl;

import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry;

public class DownloadedSchemaEntryImpl
extends XmlComplexContentImpl
implements DownloadedSchemaEntry {
    private static final long serialVersionUID = 1L;
    private static final QName FILENAME$0 = new QName("http://www.bea.com/2003/01/xmlbean/xsdownload", "filename");
    private static final QName SHA1$2 = new QName("http://www.bea.com/2003/01/xmlbean/xsdownload", "sha1");
    private static final QName SCHEMALOCATION$4 = new QName("http://www.bea.com/2003/01/xmlbean/xsdownload", "schemaLocation");
    private static final QName NAMESPACE$6 = new QName("http://www.bea.com/2003/01/xmlbean/xsdownload", "namespace");

    public DownloadedSchemaEntryImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getFilename() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_element_user(FILENAME$0, 0));
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
    public XmlToken xgetFilename() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlToken target = null;
            target = (XmlToken)((Object)this.get_store().find_element_user(FILENAME$0, 0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFilename(String filename) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_element_user(FILENAME$0, 0));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_element_user(FILENAME$0));
            }
            target.setStringValue(filename);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFilename(XmlToken filename) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlToken target = null;
            target = (XmlToken)((Object)this.get_store().find_element_user(FILENAME$0, 0));
            if (target == null) {
                target = (XmlToken)((Object)this.get_store().add_element_user(FILENAME$0));
            }
            target.set(filename);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getSha1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_element_user(SHA1$2, 0));
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
    public XmlToken xgetSha1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlToken target = null;
            target = (XmlToken)((Object)this.get_store().find_element_user(SHA1$2, 0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSha1(String sha1) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_element_user(SHA1$2, 0));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_element_user(SHA1$2));
            }
            target.setStringValue(sha1);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSha1(XmlToken sha1) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlToken target = null;
            target = (XmlToken)((Object)this.get_store().find_element_user(SHA1$2, 0));
            if (target == null) {
                target = (XmlToken)((Object)this.get_store().add_element_user(SHA1$2));
            }
            target.set(sha1);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String[] getSchemaLocationArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(SCHEMALOCATION$4, targetList);
            String[] result = new String[targetList.size()];
            int len = targetList.size();
            for (int i = 0; i < len; ++i) {
                result[i] = ((SimpleValue)targetList.get(i)).getStringValue();
            }
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getSchemaLocationArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_element_user(SCHEMALOCATION$4, i));
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            return target.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlAnyURI[] xgetSchemaLocationArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList targetList = new ArrayList();
            this.get_store().find_all_element_users(SCHEMALOCATION$4, targetList);
            XmlAnyURI[] result = new XmlAnyURI[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlAnyURI xgetSchemaLocationArray(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI target = null;
            target = (XmlAnyURI)((Object)this.get_store().find_element_user(SCHEMALOCATION$4, i));
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSchemaLocationArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCHEMALOCATION$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSchemaLocationArray(String[] schemaLocationArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(schemaLocationArray, SCHEMALOCATION$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSchemaLocationArray(int i, String schemaLocation) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_element_user(SCHEMALOCATION$4, i));
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(schemaLocation);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSchemaLocationArray(XmlAnyURI[] schemaLocationArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(schemaLocationArray, SCHEMALOCATION$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSchemaLocationArray(int i, XmlAnyURI schemaLocation) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI target = null;
            target = (XmlAnyURI)((Object)this.get_store().find_element_user(SCHEMALOCATION$4, i));
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(schemaLocation);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertSchemaLocation(int i, String schemaLocation) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = (SimpleValue)((Object)this.get_store().insert_element_user(SCHEMALOCATION$4, i));
            target.setStringValue(schemaLocation);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addSchemaLocation(String schemaLocation) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().add_element_user(SCHEMALOCATION$4));
            target.setStringValue(schemaLocation);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlAnyURI insertNewSchemaLocation(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI target = null;
            target = (XmlAnyURI)((Object)this.get_store().insert_element_user(SCHEMALOCATION$4, i));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlAnyURI addNewSchemaLocation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI target = null;
            target = (XmlAnyURI)((Object)this.get_store().add_element_user(SCHEMALOCATION$4));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSchemaLocation(int i) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCHEMALOCATION$4, i);
        }
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
            target = (SimpleValue)((Object)this.get_store().find_element_user(NAMESPACE$6, 0));
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
            target = (XmlAnyURI)((Object)this.get_store().find_element_user(NAMESPACE$6, 0));
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
            return this.get_store().count_elements(NAMESPACE$6) != 0;
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
            target = (SimpleValue)((Object)this.get_store().find_element_user(NAMESPACE$6, 0));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_element_user(NAMESPACE$6));
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
            target = (XmlAnyURI)((Object)this.get_store().find_element_user(NAMESPACE$6, 0));
            if (target == null) {
                target = (XmlAnyURI)((Object)this.get_store().add_element_user(NAMESPACE$6));
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
            this.get_store().remove_element(NAMESPACE$6, 0);
        }
    }
}

