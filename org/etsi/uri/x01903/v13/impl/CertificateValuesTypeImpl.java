/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.etsi.uri.x01903.v13.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.AnyType;
import org.etsi.uri.x01903.v13.CertificateValuesType;
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;

public class CertificateValuesTypeImpl
extends XmlComplexContentImpl
implements CertificateValuesType {
    private static final long serialVersionUID = 1L;
    private static final QName ENCAPSULATEDX509CERTIFICATE$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "EncapsulatedX509Certificate");
    private static final QName OTHERCERTIFICATE$2 = new QName("http://uri.etsi.org/01903/v1.3.2#", "OtherCertificate");
    private static final QName ID$4 = new QName("", "Id");

    public CertificateValuesTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<EncapsulatedPKIDataType> getEncapsulatedX509CertificateList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class EncapsulatedX509CertificateList
            extends AbstractList<EncapsulatedPKIDataType> {
                EncapsulatedX509CertificateList() {
                }

                @Override
                public EncapsulatedPKIDataType get(int n) {
                    return CertificateValuesTypeImpl.this.getEncapsulatedX509CertificateArray(n);
                }

                @Override
                public EncapsulatedPKIDataType set(int n, EncapsulatedPKIDataType encapsulatedPKIDataType) {
                    EncapsulatedPKIDataType encapsulatedPKIDataType2 = CertificateValuesTypeImpl.this.getEncapsulatedX509CertificateArray(n);
                    CertificateValuesTypeImpl.this.setEncapsulatedX509CertificateArray(n, encapsulatedPKIDataType);
                    return encapsulatedPKIDataType2;
                }

                @Override
                public void add(int n, EncapsulatedPKIDataType encapsulatedPKIDataType) {
                    CertificateValuesTypeImpl.this.insertNewEncapsulatedX509Certificate(n).set(encapsulatedPKIDataType);
                }

                @Override
                public EncapsulatedPKIDataType remove(int n) {
                    EncapsulatedPKIDataType encapsulatedPKIDataType = CertificateValuesTypeImpl.this.getEncapsulatedX509CertificateArray(n);
                    CertificateValuesTypeImpl.this.removeEncapsulatedX509Certificate(n);
                    return encapsulatedPKIDataType;
                }

                @Override
                public int size() {
                    return CertificateValuesTypeImpl.this.sizeOfEncapsulatedX509CertificateArray();
                }
            }
            return new EncapsulatedX509CertificateList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public EncapsulatedPKIDataType[] getEncapsulatedX509CertificateArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ENCAPSULATEDX509CERTIFICATE$0, arrayList);
            EncapsulatedPKIDataType[] encapsulatedPKIDataTypeArray = new EncapsulatedPKIDataType[arrayList.size()];
            arrayList.toArray(encapsulatedPKIDataTypeArray);
            return encapsulatedPKIDataTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public EncapsulatedPKIDataType getEncapsulatedX509CertificateArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            EncapsulatedPKIDataType encapsulatedPKIDataType = null;
            encapsulatedPKIDataType = (EncapsulatedPKIDataType)((Object)this.get_store().find_element_user(ENCAPSULATEDX509CERTIFICATE$0, n));
            if (encapsulatedPKIDataType == null) {
                throw new IndexOutOfBoundsException();
            }
            return encapsulatedPKIDataType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfEncapsulatedX509CertificateArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ENCAPSULATEDX509CERTIFICATE$0);
        }
    }

    @Override
    public void setEncapsulatedX509CertificateArray(EncapsulatedPKIDataType[] encapsulatedPKIDataTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(encapsulatedPKIDataTypeArray, ENCAPSULATEDX509CERTIFICATE$0);
    }

    @Override
    public void setEncapsulatedX509CertificateArray(int n, EncapsulatedPKIDataType encapsulatedPKIDataType) {
        this.generatedSetterHelperImpl(encapsulatedPKIDataType, ENCAPSULATEDX509CERTIFICATE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public EncapsulatedPKIDataType insertNewEncapsulatedX509Certificate(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            EncapsulatedPKIDataType encapsulatedPKIDataType = null;
            encapsulatedPKIDataType = (EncapsulatedPKIDataType)((Object)this.get_store().insert_element_user(ENCAPSULATEDX509CERTIFICATE$0, n));
            return encapsulatedPKIDataType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public EncapsulatedPKIDataType addNewEncapsulatedX509Certificate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            EncapsulatedPKIDataType encapsulatedPKIDataType = null;
            encapsulatedPKIDataType = (EncapsulatedPKIDataType)((Object)this.get_store().add_element_user(ENCAPSULATEDX509CERTIFICATE$0));
            return encapsulatedPKIDataType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeEncapsulatedX509Certificate(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ENCAPSULATEDX509CERTIFICATE$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<AnyType> getOtherCertificateList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class OtherCertificateList
            extends AbstractList<AnyType> {
                OtherCertificateList() {
                }

                @Override
                public AnyType get(int n) {
                    return CertificateValuesTypeImpl.this.getOtherCertificateArray(n);
                }

                @Override
                public AnyType set(int n, AnyType anyType) {
                    AnyType anyType2 = CertificateValuesTypeImpl.this.getOtherCertificateArray(n);
                    CertificateValuesTypeImpl.this.setOtherCertificateArray(n, anyType);
                    return anyType2;
                }

                @Override
                public void add(int n, AnyType anyType) {
                    CertificateValuesTypeImpl.this.insertNewOtherCertificate(n).set(anyType);
                }

                @Override
                public AnyType remove(int n) {
                    AnyType anyType = CertificateValuesTypeImpl.this.getOtherCertificateArray(n);
                    CertificateValuesTypeImpl.this.removeOtherCertificate(n);
                    return anyType;
                }

                @Override
                public int size() {
                    return CertificateValuesTypeImpl.this.sizeOfOtherCertificateArray();
                }
            }
            return new OtherCertificateList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public AnyType[] getOtherCertificateArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(OTHERCERTIFICATE$2, arrayList);
            AnyType[] anyTypeArray = new AnyType[arrayList.size()];
            arrayList.toArray(anyTypeArray);
            return anyTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnyType getOtherCertificateArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AnyType anyType = null;
            anyType = (AnyType)((Object)this.get_store().find_element_user(OTHERCERTIFICATE$2, n));
            if (anyType == null) {
                throw new IndexOutOfBoundsException();
            }
            return anyType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfOtherCertificateArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OTHERCERTIFICATE$2);
        }
    }

    @Override
    public void setOtherCertificateArray(AnyType[] anyTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(anyTypeArray, OTHERCERTIFICATE$2);
    }

    @Override
    public void setOtherCertificateArray(int n, AnyType anyType) {
        this.generatedSetterHelperImpl(anyType, OTHERCERTIFICATE$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnyType insertNewOtherCertificate(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AnyType anyType = null;
            anyType = (AnyType)((Object)this.get_store().insert_element_user(OTHERCERTIFICATE$2, n));
            return anyType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnyType addNewOtherCertificate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AnyType anyType = null;
            anyType = (AnyType)((Object)this.get_store().add_element_user(OTHERCERTIFICATE$2));
            return anyType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeOtherCertificate(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OTHERCERTIFICATE$2, n);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$4));
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
            xmlID = (XmlID)((Object)this.get_store().find_attribute_user(ID$4));
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
            return this.get_store().find_attribute_user(ID$4) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$4));
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
            xmlID2 = (XmlID)((Object)this.get_store().find_attribute_user(ID$4));
            if (xmlID2 == null) {
                xmlID2 = (XmlID)((Object)this.get_store().add_attribute_user(ID$4));
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
            this.get_store().remove_attribute(ID$4);
        }
    }
}

