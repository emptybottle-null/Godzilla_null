/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.w3.x2000.x09.xmldsig.SignatureMethodType
 */
package org.w3.x2000.x09.xmldsig.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.w3.x2000.x09.xmldsig.CanonicalizationMethodType;
import org.w3.x2000.x09.xmldsig.ReferenceType;
import org.w3.x2000.x09.xmldsig.SignatureMethodType;
import org.w3.x2000.x09.xmldsig.SignedInfoType;

public class SignedInfoTypeImpl
extends XmlComplexContentImpl
implements SignedInfoType {
    private static final long serialVersionUID = 1L;
    private static final QName CANONICALIZATIONMETHOD$0 = new QName("http://www.w3.org/2000/09/xmldsig#", "CanonicalizationMethod");
    private static final QName SIGNATUREMETHOD$2 = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureMethod");
    private static final QName REFERENCE$4 = new QName("http://www.w3.org/2000/09/xmldsig#", "Reference");
    private static final QName ID$6 = new QName("", "Id");

    public SignedInfoTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CanonicalizationMethodType getCanonicalizationMethod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CanonicalizationMethodType canonicalizationMethodType = null;
            canonicalizationMethodType = (CanonicalizationMethodType)((Object)this.get_store().find_element_user(CANONICALIZATIONMETHOD$0, 0));
            if (canonicalizationMethodType == null) {
                return null;
            }
            return canonicalizationMethodType;
        }
    }

    @Override
    public void setCanonicalizationMethod(CanonicalizationMethodType canonicalizationMethodType) {
        this.generatedSetterHelperImpl(canonicalizationMethodType, CANONICALIZATIONMETHOD$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CanonicalizationMethodType addNewCanonicalizationMethod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CanonicalizationMethodType canonicalizationMethodType = null;
            canonicalizationMethodType = (CanonicalizationMethodType)((Object)this.get_store().add_element_user(CANONICALIZATIONMETHOD$0));
            return canonicalizationMethodType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignatureMethodType getSignatureMethod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignatureMethodType signatureMethodType = null;
            signatureMethodType = (SignatureMethodType)this.get_store().find_element_user(SIGNATUREMETHOD$2, 0);
            if (signatureMethodType == null) {
                return null;
            }
            return signatureMethodType;
        }
    }

    @Override
    public void setSignatureMethod(SignatureMethodType signatureMethodType) {
        this.generatedSetterHelperImpl((XmlObject)signatureMethodType, SIGNATUREMETHOD$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignatureMethodType addNewSignatureMethod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignatureMethodType signatureMethodType = null;
            signatureMethodType = (SignatureMethodType)this.get_store().add_element_user(SIGNATUREMETHOD$2);
            return signatureMethodType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<ReferenceType> getReferenceList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ReferenceList
            extends AbstractList<ReferenceType> {
                ReferenceList() {
                }

                @Override
                public ReferenceType get(int n) {
                    return SignedInfoTypeImpl.this.getReferenceArray(n);
                }

                @Override
                public ReferenceType set(int n, ReferenceType referenceType) {
                    ReferenceType referenceType2 = SignedInfoTypeImpl.this.getReferenceArray(n);
                    SignedInfoTypeImpl.this.setReferenceArray(n, referenceType);
                    return referenceType2;
                }

                @Override
                public void add(int n, ReferenceType referenceType) {
                    SignedInfoTypeImpl.this.insertNewReference(n).set(referenceType);
                }

                @Override
                public ReferenceType remove(int n) {
                    ReferenceType referenceType = SignedInfoTypeImpl.this.getReferenceArray(n);
                    SignedInfoTypeImpl.this.removeReference(n);
                    return referenceType;
                }

                @Override
                public int size() {
                    return SignedInfoTypeImpl.this.sizeOfReferenceArray();
                }
            }
            return new ReferenceList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public ReferenceType[] getReferenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(REFERENCE$4, arrayList);
            ReferenceType[] referenceTypeArray = new ReferenceType[arrayList.size()];
            arrayList.toArray(referenceTypeArray);
            return referenceTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ReferenceType getReferenceArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ReferenceType referenceType = null;
            referenceType = (ReferenceType)((Object)this.get_store().find_element_user(REFERENCE$4, n));
            if (referenceType == null) {
                throw new IndexOutOfBoundsException();
            }
            return referenceType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfReferenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(REFERENCE$4);
        }
    }

    @Override
    public void setReferenceArray(ReferenceType[] referenceTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(referenceTypeArray, REFERENCE$4);
    }

    @Override
    public void setReferenceArray(int n, ReferenceType referenceType) {
        this.generatedSetterHelperImpl(referenceType, REFERENCE$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ReferenceType insertNewReference(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ReferenceType referenceType = null;
            referenceType = (ReferenceType)((Object)this.get_store().insert_element_user(REFERENCE$4, n));
            return referenceType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ReferenceType addNewReference() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ReferenceType referenceType = null;
            referenceType = (ReferenceType)((Object)this.get_store().add_element_user(REFERENCE$4));
            return referenceType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeReference(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(REFERENCE$4, n);
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
}

