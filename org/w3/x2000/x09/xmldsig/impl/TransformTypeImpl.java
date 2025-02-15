/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.w3.x2000.x09.xmldsig.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.w3.x2000.x09.xmldsig.TransformType;

public class TransformTypeImpl
extends XmlComplexContentImpl
implements TransformType {
    private static final long serialVersionUID = 1L;
    private static final QName XPATH$0 = new QName("http://www.w3.org/2000/09/xmldsig#", "XPath");
    private static final QName ALGORITHM$2 = new QName("", "Algorithm");

    public TransformTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<String> getXPathList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class XPathList
            extends AbstractList<String> {
                XPathList() {
                }

                @Override
                public String get(int n) {
                    return TransformTypeImpl.this.getXPathArray(n);
                }

                @Override
                public String set(int n, String string) {
                    String string2 = TransformTypeImpl.this.getXPathArray(n);
                    TransformTypeImpl.this.setXPathArray(n, string);
                    return string2;
                }

                @Override
                public void add(int n, String string) {
                    TransformTypeImpl.this.insertXPath(n, string);
                }

                @Override
                public String remove(int n) {
                    String string = TransformTypeImpl.this.getXPathArray(n);
                    TransformTypeImpl.this.removeXPath(n);
                    return string;
                }

                @Override
                public int size() {
                    return TransformTypeImpl.this.sizeOfXPathArray();
                }
            }
            return new XPathList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public String[] getXPathArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(XPATH$0, arrayList);
            String[] stringArray = new String[arrayList.size()];
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                stringArray[i] = ((SimpleValue)arrayList.get(i)).getStringValue();
            }
            return stringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getXPathArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(XPATH$0, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XmlString> xgetXPathList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class XPathList
            extends AbstractList<XmlString> {
                XPathList() {
                }

                @Override
                public XmlString get(int n) {
                    return TransformTypeImpl.this.xgetXPathArray(n);
                }

                @Override
                public XmlString set(int n, XmlString xmlString) {
                    XmlString xmlString2 = TransformTypeImpl.this.xgetXPathArray(n);
                    TransformTypeImpl.this.xsetXPathArray(n, xmlString);
                    return xmlString2;
                }

                @Override
                public void add(int n, XmlString xmlString) {
                    TransformTypeImpl.this.insertNewXPath(n).set(xmlString);
                }

                @Override
                public XmlString remove(int n) {
                    XmlString xmlString = TransformTypeImpl.this.xgetXPathArray(n);
                    TransformTypeImpl.this.removeXPath(n);
                    return xmlString;
                }

                @Override
                public int size() {
                    return TransformTypeImpl.this.sizeOfXPathArray();
                }
            }
            return new XPathList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XmlString[] xgetXPathArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(XPATH$0, arrayList);
            XmlString[] xmlStringArray = new XmlString[arrayList.size()];
            arrayList.toArray(xmlStringArray);
            return xmlStringArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetXPathArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_element_user(XPATH$0, n));
            if (xmlString == null) {
                throw new IndexOutOfBoundsException();
            }
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfXPathArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(XPATH$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setXPathArray(String[] stringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(stringArray, XPATH$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setXPathArray(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(XPATH$0, n));
            if (simpleValue == null) {
                throw new IndexOutOfBoundsException();
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetXPathArray(XmlString[] xmlStringArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.arraySetterHelper(xmlStringArray, XPATH$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetXPathArray(int n, XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_element_user(XPATH$0, n));
            if (xmlString2 == null) {
                throw new IndexOutOfBoundsException();
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertXPath(int n, String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = (SimpleValue)((Object)this.get_store().insert_element_user(XPATH$0, n));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addXPath(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(XPATH$0));
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString insertNewXPath(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().insert_element_user(XPATH$0, n));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString addNewXPath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().add_element_user(XPATH$0));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeXPath(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(XPATH$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getAlgorithm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGORITHM$2));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlAnyURI xgetAlgorithm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI xmlAnyURI = null;
            xmlAnyURI = (XmlAnyURI)((Object)this.get_store().find_attribute_user(ALGORITHM$2));
            return xmlAnyURI;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAlgorithm(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGORITHM$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALGORITHM$2));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAlgorithm(XmlAnyURI xmlAnyURI) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI xmlAnyURI2 = null;
            xmlAnyURI2 = (XmlAnyURI)((Object)this.get_store().find_attribute_user(ALGORITHM$2));
            if (xmlAnyURI2 == null) {
                xmlAnyURI2 = (XmlAnyURI)((Object)this.get_store().add_attribute_user(ALGORITHM$2));
            }
            xmlAnyURI2.set(xmlAnyURI);
        }
    }
}

