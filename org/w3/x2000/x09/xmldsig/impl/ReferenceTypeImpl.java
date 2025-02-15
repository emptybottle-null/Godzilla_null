/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.w3.x2000.x09.xmldsig.TransformsType
 */
package org.w3.x2000.x09.xmldsig.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.w3.x2000.x09.xmldsig.DigestMethodType;
import org.w3.x2000.x09.xmldsig.DigestValueType;
import org.w3.x2000.x09.xmldsig.ReferenceType;
import org.w3.x2000.x09.xmldsig.TransformsType;

public class ReferenceTypeImpl
extends XmlComplexContentImpl
implements ReferenceType {
    private static final long serialVersionUID = 1L;
    private static final QName TRANSFORMS$0 = new QName("http://www.w3.org/2000/09/xmldsig#", "Transforms");
    private static final QName DIGESTMETHOD$2 = new QName("http://www.w3.org/2000/09/xmldsig#", "DigestMethod");
    private static final QName DIGESTVALUE$4 = new QName("http://www.w3.org/2000/09/xmldsig#", "DigestValue");
    private static final QName ID$6 = new QName("", "Id");
    private static final QName URI$8 = new QName("", "URI");
    private static final QName TYPE$10 = new QName("", "Type");

    public ReferenceTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TransformsType getTransforms() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TransformsType transformsType = null;
            transformsType = (TransformsType)this.get_store().find_element_user(TRANSFORMS$0, 0);
            if (transformsType == null) {
                return null;
            }
            return transformsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTransforms() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TRANSFORMS$0) != 0;
        }
    }

    @Override
    public void setTransforms(TransformsType transformsType) {
        this.generatedSetterHelperImpl((XmlObject)transformsType, TRANSFORMS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TransformsType addNewTransforms() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            TransformsType transformsType = null;
            transformsType = (TransformsType)this.get_store().add_element_user(TRANSFORMS$0);
            return transformsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTransforms() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TRANSFORMS$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DigestMethodType getDigestMethod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DigestMethodType digestMethodType = null;
            digestMethodType = (DigestMethodType)((Object)this.get_store().find_element_user(DIGESTMETHOD$2, 0));
            if (digestMethodType == null) {
                return null;
            }
            return digestMethodType;
        }
    }

    @Override
    public void setDigestMethod(DigestMethodType digestMethodType) {
        this.generatedSetterHelperImpl(digestMethodType, DIGESTMETHOD$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DigestMethodType addNewDigestMethod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DigestMethodType digestMethodType = null;
            digestMethodType = (DigestMethodType)((Object)this.get_store().add_element_user(DIGESTMETHOD$2));
            return digestMethodType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public byte[] getDigestValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DIGESTVALUE$4, 0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getByteArrayValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DigestValueType xgetDigestValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DigestValueType digestValueType = null;
            digestValueType = (DigestValueType)((Object)this.get_store().find_element_user(DIGESTVALUE$4, 0));
            return digestValueType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDigestValue(byte[] byArray) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_element_user(DIGESTVALUE$4, 0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_element_user(DIGESTVALUE$4));
            }
            simpleValue.setByteArrayValue(byArray);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDigestValue(DigestValueType digestValueType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DigestValueType digestValueType2 = null;
            digestValueType2 = (DigestValueType)((Object)this.get_store().find_element_user(DIGESTVALUE$4, 0));
            if (digestValueType2 == null) {
                digestValueType2 = (DigestValueType)((Object)this.get_store().add_element_user(DIGESTVALUE$4));
            }
            digestValueType2.set(digestValueType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$6));
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
    public XmlID xgetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlID xmlID = null;
            xmlID = (XmlID)((Object)this.get_store().find_attribute_user(ID$6));
            return xmlID;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ID$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setId(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$6));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetId(XmlID xmlID) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlID xmlID2 = null;
            xmlID2 = (XmlID)((Object)this.get_store().find_attribute_user(ID$6));
            if (xmlID2 == null) {
                xmlID2 = (XmlID)((Object)this.get_store().add_attribute_user(ID$6));
            }
            xmlID2.set(xmlID);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ID$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getURI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(URI$8));
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
    public XmlAnyURI xgetURI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI xmlAnyURI = null;
            xmlAnyURI = (XmlAnyURI)((Object)this.get_store().find_attribute_user(URI$8));
            return xmlAnyURI;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetURI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(URI$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setURI(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(URI$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(URI$8));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetURI(XmlAnyURI xmlAnyURI) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI xmlAnyURI2 = null;
            xmlAnyURI2 = (XmlAnyURI)((Object)this.get_store().find_attribute_user(URI$8));
            if (xmlAnyURI2 == null) {
                xmlAnyURI2 = (XmlAnyURI)((Object)this.get_store().add_attribute_user(URI$8));
            }
            xmlAnyURI2.set(xmlAnyURI);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetURI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(URI$8);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$10));
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
    public XmlAnyURI xgetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI xmlAnyURI = null;
            xmlAnyURI = (XmlAnyURI)((Object)this.get_store().find_attribute_user(TYPE$10));
            return xmlAnyURI;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TYPE$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setType(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TYPE$10));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetType(XmlAnyURI xmlAnyURI) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI xmlAnyURI2 = null;
            xmlAnyURI2 = (XmlAnyURI)((Object)this.get_store().find_attribute_user(TYPE$10));
            if (xmlAnyURI2 == null) {
                xmlAnyURI2 = (XmlAnyURI)((Object)this.get_store().add_attribute_user(TYPE$10));
            }
            xmlAnyURI2.set(xmlAnyURI);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TYPE$10);
        }
    }
}

