/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.IncludeType
 *  org.etsi.uri.x01903.v13.ReferenceInfoType
 */
package org.etsi.uri.x01903.v13.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.AnyType;
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;
import org.etsi.uri.x01903.v13.GenericTimeStampType;
import org.etsi.uri.x01903.v13.IncludeType;
import org.etsi.uri.x01903.v13.ReferenceInfoType;
import org.w3.x2000.x09.xmldsig.CanonicalizationMethodType;

public class GenericTimeStampTypeImpl
extends XmlComplexContentImpl
implements GenericTimeStampType {
    private static final long serialVersionUID = 1L;
    private static final QName INCLUDE$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "Include");
    private static final QName REFERENCEINFO$2 = new QName("http://uri.etsi.org/01903/v1.3.2#", "ReferenceInfo");
    private static final QName CANONICALIZATIONMETHOD$4 = new QName("http://www.w3.org/2000/09/xmldsig#", "CanonicalizationMethod");
    private static final QName ENCAPSULATEDTIMESTAMP$6 = new QName("http://uri.etsi.org/01903/v1.3.2#", "EncapsulatedTimeStamp");
    private static final QName XMLTIMESTAMP$8 = new QName("http://uri.etsi.org/01903/v1.3.2#", "XMLTimeStamp");
    private static final QName ID$10 = new QName("", "Id");

    public GenericTimeStampTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<IncludeType> getIncludeList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class IncludeList
            extends AbstractList<IncludeType> {
                IncludeList() {
                }

                @Override
                public IncludeType get(int n) {
                    return GenericTimeStampTypeImpl.this.getIncludeArray(n);
                }

                @Override
                public IncludeType set(int n, IncludeType includeType) {
                    IncludeType includeType2 = GenericTimeStampTypeImpl.this.getIncludeArray(n);
                    GenericTimeStampTypeImpl.this.setIncludeArray(n, includeType);
                    return includeType2;
                }

                @Override
                public void add(int n, IncludeType includeType) {
                    GenericTimeStampTypeImpl.this.insertNewInclude(n).set((XmlObject)includeType);
                }

                @Override
                public IncludeType remove(int n) {
                    IncludeType includeType = GenericTimeStampTypeImpl.this.getIncludeArray(n);
                    GenericTimeStampTypeImpl.this.removeInclude(n);
                    return includeType;
                }

                @Override
                public int size() {
                    return GenericTimeStampTypeImpl.this.sizeOfIncludeArray();
                }
            }
            return new IncludeList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public IncludeType[] getIncludeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(INCLUDE$0, arrayList);
            IncludeType[] includeTypeArray = new IncludeType[arrayList.size()];
            arrayList.toArray(includeTypeArray);
            return includeTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public IncludeType getIncludeArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            IncludeType includeType = null;
            includeType = (IncludeType)this.get_store().find_element_user(INCLUDE$0, n);
            if (includeType == null) {
                throw new IndexOutOfBoundsException();
            }
            return includeType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfIncludeArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(INCLUDE$0);
        }
    }

    @Override
    public void setIncludeArray(IncludeType[] includeTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])includeTypeArray, INCLUDE$0);
    }

    @Override
    public void setIncludeArray(int n, IncludeType includeType) {
        this.generatedSetterHelperImpl((XmlObject)includeType, INCLUDE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public IncludeType insertNewInclude(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            IncludeType includeType = null;
            includeType = (IncludeType)this.get_store().insert_element_user(INCLUDE$0, n);
            return includeType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public IncludeType addNewInclude() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            IncludeType includeType = null;
            includeType = (IncludeType)this.get_store().add_element_user(INCLUDE$0);
            return includeType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeInclude(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(INCLUDE$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<ReferenceInfoType> getReferenceInfoList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ReferenceInfoList
            extends AbstractList<ReferenceInfoType> {
                ReferenceInfoList() {
                }

                @Override
                public ReferenceInfoType get(int n) {
                    return GenericTimeStampTypeImpl.this.getReferenceInfoArray(n);
                }

                @Override
                public ReferenceInfoType set(int n, ReferenceInfoType referenceInfoType) {
                    ReferenceInfoType referenceInfoType2 = GenericTimeStampTypeImpl.this.getReferenceInfoArray(n);
                    GenericTimeStampTypeImpl.this.setReferenceInfoArray(n, referenceInfoType);
                    return referenceInfoType2;
                }

                @Override
                public void add(int n, ReferenceInfoType referenceInfoType) {
                    GenericTimeStampTypeImpl.this.insertNewReferenceInfo(n).set((XmlObject)referenceInfoType);
                }

                @Override
                public ReferenceInfoType remove(int n) {
                    ReferenceInfoType referenceInfoType = GenericTimeStampTypeImpl.this.getReferenceInfoArray(n);
                    GenericTimeStampTypeImpl.this.removeReferenceInfo(n);
                    return referenceInfoType;
                }

                @Override
                public int size() {
                    return GenericTimeStampTypeImpl.this.sizeOfReferenceInfoArray();
                }
            }
            return new ReferenceInfoList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public ReferenceInfoType[] getReferenceInfoArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(REFERENCEINFO$2, arrayList);
            ReferenceInfoType[] referenceInfoTypeArray = new ReferenceInfoType[arrayList.size()];
            arrayList.toArray(referenceInfoTypeArray);
            return referenceInfoTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ReferenceInfoType getReferenceInfoArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ReferenceInfoType referenceInfoType = null;
            referenceInfoType = (ReferenceInfoType)this.get_store().find_element_user(REFERENCEINFO$2, n);
            if (referenceInfoType == null) {
                throw new IndexOutOfBoundsException();
            }
            return referenceInfoType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfReferenceInfoArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(REFERENCEINFO$2);
        }
    }

    @Override
    public void setReferenceInfoArray(ReferenceInfoType[] referenceInfoTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])referenceInfoTypeArray, REFERENCEINFO$2);
    }

    @Override
    public void setReferenceInfoArray(int n, ReferenceInfoType referenceInfoType) {
        this.generatedSetterHelperImpl((XmlObject)referenceInfoType, REFERENCEINFO$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ReferenceInfoType insertNewReferenceInfo(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ReferenceInfoType referenceInfoType = null;
            referenceInfoType = (ReferenceInfoType)this.get_store().insert_element_user(REFERENCEINFO$2, n);
            return referenceInfoType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ReferenceInfoType addNewReferenceInfo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ReferenceInfoType referenceInfoType = null;
            referenceInfoType = (ReferenceInfoType)this.get_store().add_element_user(REFERENCEINFO$2);
            return referenceInfoType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeReferenceInfo(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(REFERENCEINFO$2, n);
        }
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
            canonicalizationMethodType = (CanonicalizationMethodType)((Object)this.get_store().find_element_user(CANONICALIZATIONMETHOD$4, 0));
            if (canonicalizationMethodType == null) {
                return null;
            }
            return canonicalizationMethodType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCanonicalizationMethod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CANONICALIZATIONMETHOD$4) != 0;
        }
    }

    @Override
    public void setCanonicalizationMethod(CanonicalizationMethodType canonicalizationMethodType) {
        this.generatedSetterHelperImpl(canonicalizationMethodType, CANONICALIZATIONMETHOD$4, 0, (short)1);
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
            canonicalizationMethodType = (CanonicalizationMethodType)((Object)this.get_store().add_element_user(CANONICALIZATIONMETHOD$4));
            return canonicalizationMethodType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCanonicalizationMethod() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CANONICALIZATIONMETHOD$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<EncapsulatedPKIDataType> getEncapsulatedTimeStampList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class EncapsulatedTimeStampList
            extends AbstractList<EncapsulatedPKIDataType> {
                EncapsulatedTimeStampList() {
                }

                @Override
                public EncapsulatedPKIDataType get(int n) {
                    return GenericTimeStampTypeImpl.this.getEncapsulatedTimeStampArray(n);
                }

                @Override
                public EncapsulatedPKIDataType set(int n, EncapsulatedPKIDataType encapsulatedPKIDataType) {
                    EncapsulatedPKIDataType encapsulatedPKIDataType2 = GenericTimeStampTypeImpl.this.getEncapsulatedTimeStampArray(n);
                    GenericTimeStampTypeImpl.this.setEncapsulatedTimeStampArray(n, encapsulatedPKIDataType);
                    return encapsulatedPKIDataType2;
                }

                @Override
                public void add(int n, EncapsulatedPKIDataType encapsulatedPKIDataType) {
                    GenericTimeStampTypeImpl.this.insertNewEncapsulatedTimeStamp(n).set(encapsulatedPKIDataType);
                }

                @Override
                public EncapsulatedPKIDataType remove(int n) {
                    EncapsulatedPKIDataType encapsulatedPKIDataType = GenericTimeStampTypeImpl.this.getEncapsulatedTimeStampArray(n);
                    GenericTimeStampTypeImpl.this.removeEncapsulatedTimeStamp(n);
                    return encapsulatedPKIDataType;
                }

                @Override
                public int size() {
                    return GenericTimeStampTypeImpl.this.sizeOfEncapsulatedTimeStampArray();
                }
            }
            return new EncapsulatedTimeStampList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public EncapsulatedPKIDataType[] getEncapsulatedTimeStampArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ENCAPSULATEDTIMESTAMP$6, arrayList);
            EncapsulatedPKIDataType[] encapsulatedPKIDataTypeArray = new EncapsulatedPKIDataType[arrayList.size()];
            arrayList.toArray(encapsulatedPKIDataTypeArray);
            return encapsulatedPKIDataTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public EncapsulatedPKIDataType getEncapsulatedTimeStampArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            EncapsulatedPKIDataType encapsulatedPKIDataType = null;
            encapsulatedPKIDataType = (EncapsulatedPKIDataType)((Object)this.get_store().find_element_user(ENCAPSULATEDTIMESTAMP$6, n));
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
    public int sizeOfEncapsulatedTimeStampArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ENCAPSULATEDTIMESTAMP$6);
        }
    }

    @Override
    public void setEncapsulatedTimeStampArray(EncapsulatedPKIDataType[] encapsulatedPKIDataTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(encapsulatedPKIDataTypeArray, ENCAPSULATEDTIMESTAMP$6);
    }

    @Override
    public void setEncapsulatedTimeStampArray(int n, EncapsulatedPKIDataType encapsulatedPKIDataType) {
        this.generatedSetterHelperImpl(encapsulatedPKIDataType, ENCAPSULATEDTIMESTAMP$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public EncapsulatedPKIDataType insertNewEncapsulatedTimeStamp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            EncapsulatedPKIDataType encapsulatedPKIDataType = null;
            encapsulatedPKIDataType = (EncapsulatedPKIDataType)((Object)this.get_store().insert_element_user(ENCAPSULATEDTIMESTAMP$6, n));
            return encapsulatedPKIDataType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public EncapsulatedPKIDataType addNewEncapsulatedTimeStamp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            EncapsulatedPKIDataType encapsulatedPKIDataType = null;
            encapsulatedPKIDataType = (EncapsulatedPKIDataType)((Object)this.get_store().add_element_user(ENCAPSULATEDTIMESTAMP$6));
            return encapsulatedPKIDataType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeEncapsulatedTimeStamp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ENCAPSULATEDTIMESTAMP$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<AnyType> getXMLTimeStampList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class XMLTimeStampList
            extends AbstractList<AnyType> {
                XMLTimeStampList() {
                }

                @Override
                public AnyType get(int n) {
                    return GenericTimeStampTypeImpl.this.getXMLTimeStampArray(n);
                }

                @Override
                public AnyType set(int n, AnyType anyType) {
                    AnyType anyType2 = GenericTimeStampTypeImpl.this.getXMLTimeStampArray(n);
                    GenericTimeStampTypeImpl.this.setXMLTimeStampArray(n, anyType);
                    return anyType2;
                }

                @Override
                public void add(int n, AnyType anyType) {
                    GenericTimeStampTypeImpl.this.insertNewXMLTimeStamp(n).set(anyType);
                }

                @Override
                public AnyType remove(int n) {
                    AnyType anyType = GenericTimeStampTypeImpl.this.getXMLTimeStampArray(n);
                    GenericTimeStampTypeImpl.this.removeXMLTimeStamp(n);
                    return anyType;
                }

                @Override
                public int size() {
                    return GenericTimeStampTypeImpl.this.sizeOfXMLTimeStampArray();
                }
            }
            return new XMLTimeStampList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public AnyType[] getXMLTimeStampArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(XMLTIMESTAMP$8, arrayList);
            AnyType[] anyTypeArray = new AnyType[arrayList.size()];
            arrayList.toArray(anyTypeArray);
            return anyTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnyType getXMLTimeStampArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AnyType anyType = null;
            anyType = (AnyType)((Object)this.get_store().find_element_user(XMLTIMESTAMP$8, n));
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
    public int sizeOfXMLTimeStampArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(XMLTIMESTAMP$8);
        }
    }

    @Override
    public void setXMLTimeStampArray(AnyType[] anyTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(anyTypeArray, XMLTIMESTAMP$8);
    }

    @Override
    public void setXMLTimeStampArray(int n, AnyType anyType) {
        this.generatedSetterHelperImpl(anyType, XMLTIMESTAMP$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnyType insertNewXMLTimeStamp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AnyType anyType = null;
            anyType = (AnyType)((Object)this.get_store().insert_element_user(XMLTIMESTAMP$8, n));
            return anyType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AnyType addNewXMLTimeStamp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            AnyType anyType = null;
            anyType = (AnyType)((Object)this.get_store().add_element_user(XMLTIMESTAMP$8));
            return anyType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeXMLTimeStamp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(XMLTIMESTAMP$8, n);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$10));
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
            xmlID = (XmlID)((Object)this.get_store().find_attribute_user(ID$10));
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
            return this.get_store().find_attribute_user(ID$10) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$10));
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
            xmlID2 = (XmlID)((Object)this.get_store().find_attribute_user(ID$10));
            if (xmlID2 == null) {
                xmlID2 = (XmlID)((Object)this.get_store().add_attribute_user(ID$10));
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
            this.get_store().remove_attribute(ID$10);
        }
    }
}

