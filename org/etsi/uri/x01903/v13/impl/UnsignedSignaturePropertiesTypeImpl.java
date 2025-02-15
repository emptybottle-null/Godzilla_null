/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.CounterSignatureType
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
import org.etsi.uri.x01903.v13.CertificateValuesType;
import org.etsi.uri.x01903.v13.CompleteCertificateRefsType;
import org.etsi.uri.x01903.v13.CompleteRevocationRefsType;
import org.etsi.uri.x01903.v13.CounterSignatureType;
import org.etsi.uri.x01903.v13.RevocationValuesType;
import org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType;
import org.etsi.uri.x01903.v13.XAdESTimeStampType;

public class UnsignedSignaturePropertiesTypeImpl
extends XmlComplexContentImpl
implements UnsignedSignaturePropertiesType {
    private static final long serialVersionUID = 1L;
    private static final QName COUNTERSIGNATURE$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "CounterSignature");
    private static final QName SIGNATURETIMESTAMP$2 = new QName("http://uri.etsi.org/01903/v1.3.2#", "SignatureTimeStamp");
    private static final QName COMPLETECERTIFICATEREFS$4 = new QName("http://uri.etsi.org/01903/v1.3.2#", "CompleteCertificateRefs");
    private static final QName COMPLETEREVOCATIONREFS$6 = new QName("http://uri.etsi.org/01903/v1.3.2#", "CompleteRevocationRefs");
    private static final QName ATTRIBUTECERTIFICATEREFS$8 = new QName("http://uri.etsi.org/01903/v1.3.2#", "AttributeCertificateRefs");
    private static final QName ATTRIBUTEREVOCATIONREFS$10 = new QName("http://uri.etsi.org/01903/v1.3.2#", "AttributeRevocationRefs");
    private static final QName SIGANDREFSTIMESTAMP$12 = new QName("http://uri.etsi.org/01903/v1.3.2#", "SigAndRefsTimeStamp");
    private static final QName REFSONLYTIMESTAMP$14 = new QName("http://uri.etsi.org/01903/v1.3.2#", "RefsOnlyTimeStamp");
    private static final QName CERTIFICATEVALUES$16 = new QName("http://uri.etsi.org/01903/v1.3.2#", "CertificateValues");
    private static final QName REVOCATIONVALUES$18 = new QName("http://uri.etsi.org/01903/v1.3.2#", "RevocationValues");
    private static final QName ATTRAUTHORITIESCERTVALUES$20 = new QName("http://uri.etsi.org/01903/v1.3.2#", "AttrAuthoritiesCertValues");
    private static final QName ATTRIBUTEREVOCATIONVALUES$22 = new QName("http://uri.etsi.org/01903/v1.3.2#", "AttributeRevocationValues");
    private static final QName ARCHIVETIMESTAMP$24 = new QName("http://uri.etsi.org/01903/v1.3.2#", "ArchiveTimeStamp");
    private static final QName ID$26 = new QName("", "Id");

    public UnsignedSignaturePropertiesTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CounterSignatureType> getCounterSignatureList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CounterSignatureList
            extends AbstractList<CounterSignatureType> {
                CounterSignatureList() {
                }

                @Override
                public CounterSignatureType get(int n) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getCounterSignatureArray(n);
                }

                @Override
                public CounterSignatureType set(int n, CounterSignatureType counterSignatureType) {
                    CounterSignatureType counterSignatureType2 = UnsignedSignaturePropertiesTypeImpl.this.getCounterSignatureArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.setCounterSignatureArray(n, counterSignatureType);
                    return counterSignatureType2;
                }

                @Override
                public void add(int n, CounterSignatureType counterSignatureType) {
                    UnsignedSignaturePropertiesTypeImpl.this.insertNewCounterSignature(n).set((XmlObject)counterSignatureType);
                }

                @Override
                public CounterSignatureType remove(int n) {
                    CounterSignatureType counterSignatureType = UnsignedSignaturePropertiesTypeImpl.this.getCounterSignatureArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.removeCounterSignature(n);
                    return counterSignatureType;
                }

                @Override
                public int size() {
                    return UnsignedSignaturePropertiesTypeImpl.this.sizeOfCounterSignatureArray();
                }
            }
            return new CounterSignatureList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CounterSignatureType[] getCounterSignatureArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COUNTERSIGNATURE$0, arrayList);
            CounterSignatureType[] counterSignatureTypeArray = new CounterSignatureType[arrayList.size()];
            arrayList.toArray(counterSignatureTypeArray);
            return counterSignatureTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CounterSignatureType getCounterSignatureArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CounterSignatureType counterSignatureType = null;
            counterSignatureType = (CounterSignatureType)this.get_store().find_element_user(COUNTERSIGNATURE$0, n);
            if (counterSignatureType == null) {
                throw new IndexOutOfBoundsException();
            }
            return counterSignatureType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCounterSignatureArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COUNTERSIGNATURE$0);
        }
    }

    @Override
    public void setCounterSignatureArray(CounterSignatureType[] counterSignatureTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])counterSignatureTypeArray, COUNTERSIGNATURE$0);
    }

    @Override
    public void setCounterSignatureArray(int n, CounterSignatureType counterSignatureType) {
        this.generatedSetterHelperImpl((XmlObject)counterSignatureType, COUNTERSIGNATURE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CounterSignatureType insertNewCounterSignature(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CounterSignatureType counterSignatureType = null;
            counterSignatureType = (CounterSignatureType)this.get_store().insert_element_user(COUNTERSIGNATURE$0, n);
            return counterSignatureType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CounterSignatureType addNewCounterSignature() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CounterSignatureType counterSignatureType = null;
            counterSignatureType = (CounterSignatureType)this.get_store().add_element_user(COUNTERSIGNATURE$0);
            return counterSignatureType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCounterSignature(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COUNTERSIGNATURE$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XAdESTimeStampType> getSignatureTimeStampList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SignatureTimeStampList
            extends AbstractList<XAdESTimeStampType> {
                SignatureTimeStampList() {
                }

                @Override
                public XAdESTimeStampType get(int n) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getSignatureTimeStampArray(n);
                }

                @Override
                public XAdESTimeStampType set(int n, XAdESTimeStampType xAdESTimeStampType) {
                    XAdESTimeStampType xAdESTimeStampType2 = UnsignedSignaturePropertiesTypeImpl.this.getSignatureTimeStampArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.setSignatureTimeStampArray(n, xAdESTimeStampType);
                    return xAdESTimeStampType2;
                }

                @Override
                public void add(int n, XAdESTimeStampType xAdESTimeStampType) {
                    UnsignedSignaturePropertiesTypeImpl.this.insertNewSignatureTimeStamp(n).set(xAdESTimeStampType);
                }

                @Override
                public XAdESTimeStampType remove(int n) {
                    XAdESTimeStampType xAdESTimeStampType = UnsignedSignaturePropertiesTypeImpl.this.getSignatureTimeStampArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.removeSignatureTimeStamp(n);
                    return xAdESTimeStampType;
                }

                @Override
                public int size() {
                    return UnsignedSignaturePropertiesTypeImpl.this.sizeOfSignatureTimeStampArray();
                }
            }
            return new SignatureTimeStampList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XAdESTimeStampType[] getSignatureTimeStampArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SIGNATURETIMESTAMP$2, arrayList);
            XAdESTimeStampType[] xAdESTimeStampTypeArray = new XAdESTimeStampType[arrayList.size()];
            arrayList.toArray(xAdESTimeStampTypeArray);
            return xAdESTimeStampTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XAdESTimeStampType getSignatureTimeStampArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XAdESTimeStampType xAdESTimeStampType = null;
            xAdESTimeStampType = (XAdESTimeStampType)((Object)this.get_store().find_element_user(SIGNATURETIMESTAMP$2, n));
            if (xAdESTimeStampType == null) {
                throw new IndexOutOfBoundsException();
            }
            return xAdESTimeStampType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSignatureTimeStampArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIGNATURETIMESTAMP$2);
        }
    }

    @Override
    public void setSignatureTimeStampArray(XAdESTimeStampType[] xAdESTimeStampTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(xAdESTimeStampTypeArray, SIGNATURETIMESTAMP$2);
    }

    @Override
    public void setSignatureTimeStampArray(int n, XAdESTimeStampType xAdESTimeStampType) {
        this.generatedSetterHelperImpl(xAdESTimeStampType, SIGNATURETIMESTAMP$2, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XAdESTimeStampType insertNewSignatureTimeStamp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XAdESTimeStampType xAdESTimeStampType = null;
            xAdESTimeStampType = (XAdESTimeStampType)((Object)this.get_store().insert_element_user(SIGNATURETIMESTAMP$2, n));
            return xAdESTimeStampType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XAdESTimeStampType addNewSignatureTimeStamp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XAdESTimeStampType xAdESTimeStampType = null;
            xAdESTimeStampType = (XAdESTimeStampType)((Object)this.get_store().add_element_user(SIGNATURETIMESTAMP$2));
            return xAdESTimeStampType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSignatureTimeStamp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIGNATURETIMESTAMP$2, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CompleteCertificateRefsType> getCompleteCertificateRefsList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CompleteCertificateRefsList
            extends AbstractList<CompleteCertificateRefsType> {
                CompleteCertificateRefsList() {
                }

                @Override
                public CompleteCertificateRefsType get(int n) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getCompleteCertificateRefsArray(n);
                }

                @Override
                public CompleteCertificateRefsType set(int n, CompleteCertificateRefsType completeCertificateRefsType) {
                    CompleteCertificateRefsType completeCertificateRefsType2 = UnsignedSignaturePropertiesTypeImpl.this.getCompleteCertificateRefsArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.setCompleteCertificateRefsArray(n, completeCertificateRefsType);
                    return completeCertificateRefsType2;
                }

                @Override
                public void add(int n, CompleteCertificateRefsType completeCertificateRefsType) {
                    UnsignedSignaturePropertiesTypeImpl.this.insertNewCompleteCertificateRefs(n).set(completeCertificateRefsType);
                }

                @Override
                public CompleteCertificateRefsType remove(int n) {
                    CompleteCertificateRefsType completeCertificateRefsType = UnsignedSignaturePropertiesTypeImpl.this.getCompleteCertificateRefsArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.removeCompleteCertificateRefs(n);
                    return completeCertificateRefsType;
                }

                @Override
                public int size() {
                    return UnsignedSignaturePropertiesTypeImpl.this.sizeOfCompleteCertificateRefsArray();
                }
            }
            return new CompleteCertificateRefsList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CompleteCertificateRefsType[] getCompleteCertificateRefsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COMPLETECERTIFICATEREFS$4, arrayList);
            CompleteCertificateRefsType[] completeCertificateRefsTypeArray = new CompleteCertificateRefsType[arrayList.size()];
            arrayList.toArray(completeCertificateRefsTypeArray);
            return completeCertificateRefsTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CompleteCertificateRefsType getCompleteCertificateRefsArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CompleteCertificateRefsType completeCertificateRefsType = null;
            completeCertificateRefsType = (CompleteCertificateRefsType)((Object)this.get_store().find_element_user(COMPLETECERTIFICATEREFS$4, n));
            if (completeCertificateRefsType == null) {
                throw new IndexOutOfBoundsException();
            }
            return completeCertificateRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCompleteCertificateRefsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COMPLETECERTIFICATEREFS$4);
        }
    }

    @Override
    public void setCompleteCertificateRefsArray(CompleteCertificateRefsType[] completeCertificateRefsTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(completeCertificateRefsTypeArray, COMPLETECERTIFICATEREFS$4);
    }

    @Override
    public void setCompleteCertificateRefsArray(int n, CompleteCertificateRefsType completeCertificateRefsType) {
        this.generatedSetterHelperImpl(completeCertificateRefsType, COMPLETECERTIFICATEREFS$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CompleteCertificateRefsType insertNewCompleteCertificateRefs(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CompleteCertificateRefsType completeCertificateRefsType = null;
            completeCertificateRefsType = (CompleteCertificateRefsType)((Object)this.get_store().insert_element_user(COMPLETECERTIFICATEREFS$4, n));
            return completeCertificateRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CompleteCertificateRefsType addNewCompleteCertificateRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CompleteCertificateRefsType completeCertificateRefsType = null;
            completeCertificateRefsType = (CompleteCertificateRefsType)((Object)this.get_store().add_element_user(COMPLETECERTIFICATEREFS$4));
            return completeCertificateRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCompleteCertificateRefs(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COMPLETECERTIFICATEREFS$4, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CompleteRevocationRefsType> getCompleteRevocationRefsList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CompleteRevocationRefsList
            extends AbstractList<CompleteRevocationRefsType> {
                CompleteRevocationRefsList() {
                }

                @Override
                public CompleteRevocationRefsType get(int n) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getCompleteRevocationRefsArray(n);
                }

                @Override
                public CompleteRevocationRefsType set(int n, CompleteRevocationRefsType completeRevocationRefsType) {
                    CompleteRevocationRefsType completeRevocationRefsType2 = UnsignedSignaturePropertiesTypeImpl.this.getCompleteRevocationRefsArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.setCompleteRevocationRefsArray(n, completeRevocationRefsType);
                    return completeRevocationRefsType2;
                }

                @Override
                public void add(int n, CompleteRevocationRefsType completeRevocationRefsType) {
                    UnsignedSignaturePropertiesTypeImpl.this.insertNewCompleteRevocationRefs(n).set(completeRevocationRefsType);
                }

                @Override
                public CompleteRevocationRefsType remove(int n) {
                    CompleteRevocationRefsType completeRevocationRefsType = UnsignedSignaturePropertiesTypeImpl.this.getCompleteRevocationRefsArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.removeCompleteRevocationRefs(n);
                    return completeRevocationRefsType;
                }

                @Override
                public int size() {
                    return UnsignedSignaturePropertiesTypeImpl.this.sizeOfCompleteRevocationRefsArray();
                }
            }
            return new CompleteRevocationRefsList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CompleteRevocationRefsType[] getCompleteRevocationRefsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COMPLETEREVOCATIONREFS$6, arrayList);
            CompleteRevocationRefsType[] completeRevocationRefsTypeArray = new CompleteRevocationRefsType[arrayList.size()];
            arrayList.toArray(completeRevocationRefsTypeArray);
            return completeRevocationRefsTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CompleteRevocationRefsType getCompleteRevocationRefsArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CompleteRevocationRefsType completeRevocationRefsType = null;
            completeRevocationRefsType = (CompleteRevocationRefsType)((Object)this.get_store().find_element_user(COMPLETEREVOCATIONREFS$6, n));
            if (completeRevocationRefsType == null) {
                throw new IndexOutOfBoundsException();
            }
            return completeRevocationRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCompleteRevocationRefsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COMPLETEREVOCATIONREFS$6);
        }
    }

    @Override
    public void setCompleteRevocationRefsArray(CompleteRevocationRefsType[] completeRevocationRefsTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(completeRevocationRefsTypeArray, COMPLETEREVOCATIONREFS$6);
    }

    @Override
    public void setCompleteRevocationRefsArray(int n, CompleteRevocationRefsType completeRevocationRefsType) {
        this.generatedSetterHelperImpl(completeRevocationRefsType, COMPLETEREVOCATIONREFS$6, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CompleteRevocationRefsType insertNewCompleteRevocationRefs(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CompleteRevocationRefsType completeRevocationRefsType = null;
            completeRevocationRefsType = (CompleteRevocationRefsType)((Object)this.get_store().insert_element_user(COMPLETEREVOCATIONREFS$6, n));
            return completeRevocationRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CompleteRevocationRefsType addNewCompleteRevocationRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CompleteRevocationRefsType completeRevocationRefsType = null;
            completeRevocationRefsType = (CompleteRevocationRefsType)((Object)this.get_store().add_element_user(COMPLETEREVOCATIONREFS$6));
            return completeRevocationRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCompleteRevocationRefs(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COMPLETEREVOCATIONREFS$6, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CompleteCertificateRefsType> getAttributeCertificateRefsList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AttributeCertificateRefsList
            extends AbstractList<CompleteCertificateRefsType> {
                AttributeCertificateRefsList() {
                }

                @Override
                public CompleteCertificateRefsType get(int n) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getAttributeCertificateRefsArray(n);
                }

                @Override
                public CompleteCertificateRefsType set(int n, CompleteCertificateRefsType completeCertificateRefsType) {
                    CompleteCertificateRefsType completeCertificateRefsType2 = UnsignedSignaturePropertiesTypeImpl.this.getAttributeCertificateRefsArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.setAttributeCertificateRefsArray(n, completeCertificateRefsType);
                    return completeCertificateRefsType2;
                }

                @Override
                public void add(int n, CompleteCertificateRefsType completeCertificateRefsType) {
                    UnsignedSignaturePropertiesTypeImpl.this.insertNewAttributeCertificateRefs(n).set(completeCertificateRefsType);
                }

                @Override
                public CompleteCertificateRefsType remove(int n) {
                    CompleteCertificateRefsType completeCertificateRefsType = UnsignedSignaturePropertiesTypeImpl.this.getAttributeCertificateRefsArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.removeAttributeCertificateRefs(n);
                    return completeCertificateRefsType;
                }

                @Override
                public int size() {
                    return UnsignedSignaturePropertiesTypeImpl.this.sizeOfAttributeCertificateRefsArray();
                }
            }
            return new AttributeCertificateRefsList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CompleteCertificateRefsType[] getAttributeCertificateRefsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ATTRIBUTECERTIFICATEREFS$8, arrayList);
            CompleteCertificateRefsType[] completeCertificateRefsTypeArray = new CompleteCertificateRefsType[arrayList.size()];
            arrayList.toArray(completeCertificateRefsTypeArray);
            return completeCertificateRefsTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CompleteCertificateRefsType getAttributeCertificateRefsArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CompleteCertificateRefsType completeCertificateRefsType = null;
            completeCertificateRefsType = (CompleteCertificateRefsType)((Object)this.get_store().find_element_user(ATTRIBUTECERTIFICATEREFS$8, n));
            if (completeCertificateRefsType == null) {
                throw new IndexOutOfBoundsException();
            }
            return completeCertificateRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAttributeCertificateRefsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ATTRIBUTECERTIFICATEREFS$8);
        }
    }

    @Override
    public void setAttributeCertificateRefsArray(CompleteCertificateRefsType[] completeCertificateRefsTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(completeCertificateRefsTypeArray, ATTRIBUTECERTIFICATEREFS$8);
    }

    @Override
    public void setAttributeCertificateRefsArray(int n, CompleteCertificateRefsType completeCertificateRefsType) {
        this.generatedSetterHelperImpl(completeCertificateRefsType, ATTRIBUTECERTIFICATEREFS$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CompleteCertificateRefsType insertNewAttributeCertificateRefs(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CompleteCertificateRefsType completeCertificateRefsType = null;
            completeCertificateRefsType = (CompleteCertificateRefsType)((Object)this.get_store().insert_element_user(ATTRIBUTECERTIFICATEREFS$8, n));
            return completeCertificateRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CompleteCertificateRefsType addNewAttributeCertificateRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CompleteCertificateRefsType completeCertificateRefsType = null;
            completeCertificateRefsType = (CompleteCertificateRefsType)((Object)this.get_store().add_element_user(ATTRIBUTECERTIFICATEREFS$8));
            return completeCertificateRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAttributeCertificateRefs(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ATTRIBUTECERTIFICATEREFS$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CompleteRevocationRefsType> getAttributeRevocationRefsList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AttributeRevocationRefsList
            extends AbstractList<CompleteRevocationRefsType> {
                AttributeRevocationRefsList() {
                }

                @Override
                public CompleteRevocationRefsType get(int n) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getAttributeRevocationRefsArray(n);
                }

                @Override
                public CompleteRevocationRefsType set(int n, CompleteRevocationRefsType completeRevocationRefsType) {
                    CompleteRevocationRefsType completeRevocationRefsType2 = UnsignedSignaturePropertiesTypeImpl.this.getAttributeRevocationRefsArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.setAttributeRevocationRefsArray(n, completeRevocationRefsType);
                    return completeRevocationRefsType2;
                }

                @Override
                public void add(int n, CompleteRevocationRefsType completeRevocationRefsType) {
                    UnsignedSignaturePropertiesTypeImpl.this.insertNewAttributeRevocationRefs(n).set(completeRevocationRefsType);
                }

                @Override
                public CompleteRevocationRefsType remove(int n) {
                    CompleteRevocationRefsType completeRevocationRefsType = UnsignedSignaturePropertiesTypeImpl.this.getAttributeRevocationRefsArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.removeAttributeRevocationRefs(n);
                    return completeRevocationRefsType;
                }

                @Override
                public int size() {
                    return UnsignedSignaturePropertiesTypeImpl.this.sizeOfAttributeRevocationRefsArray();
                }
            }
            return new AttributeRevocationRefsList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CompleteRevocationRefsType[] getAttributeRevocationRefsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ATTRIBUTEREVOCATIONREFS$10, arrayList);
            CompleteRevocationRefsType[] completeRevocationRefsTypeArray = new CompleteRevocationRefsType[arrayList.size()];
            arrayList.toArray(completeRevocationRefsTypeArray);
            return completeRevocationRefsTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CompleteRevocationRefsType getAttributeRevocationRefsArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CompleteRevocationRefsType completeRevocationRefsType = null;
            completeRevocationRefsType = (CompleteRevocationRefsType)((Object)this.get_store().find_element_user(ATTRIBUTEREVOCATIONREFS$10, n));
            if (completeRevocationRefsType == null) {
                throw new IndexOutOfBoundsException();
            }
            return completeRevocationRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAttributeRevocationRefsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ATTRIBUTEREVOCATIONREFS$10);
        }
    }

    @Override
    public void setAttributeRevocationRefsArray(CompleteRevocationRefsType[] completeRevocationRefsTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(completeRevocationRefsTypeArray, ATTRIBUTEREVOCATIONREFS$10);
    }

    @Override
    public void setAttributeRevocationRefsArray(int n, CompleteRevocationRefsType completeRevocationRefsType) {
        this.generatedSetterHelperImpl(completeRevocationRefsType, ATTRIBUTEREVOCATIONREFS$10, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CompleteRevocationRefsType insertNewAttributeRevocationRefs(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CompleteRevocationRefsType completeRevocationRefsType = null;
            completeRevocationRefsType = (CompleteRevocationRefsType)((Object)this.get_store().insert_element_user(ATTRIBUTEREVOCATIONREFS$10, n));
            return completeRevocationRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CompleteRevocationRefsType addNewAttributeRevocationRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CompleteRevocationRefsType completeRevocationRefsType = null;
            completeRevocationRefsType = (CompleteRevocationRefsType)((Object)this.get_store().add_element_user(ATTRIBUTEREVOCATIONREFS$10));
            return completeRevocationRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAttributeRevocationRefs(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ATTRIBUTEREVOCATIONREFS$10, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XAdESTimeStampType> getSigAndRefsTimeStampList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SigAndRefsTimeStampList
            extends AbstractList<XAdESTimeStampType> {
                SigAndRefsTimeStampList() {
                }

                @Override
                public XAdESTimeStampType get(int n) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getSigAndRefsTimeStampArray(n);
                }

                @Override
                public XAdESTimeStampType set(int n, XAdESTimeStampType xAdESTimeStampType) {
                    XAdESTimeStampType xAdESTimeStampType2 = UnsignedSignaturePropertiesTypeImpl.this.getSigAndRefsTimeStampArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.setSigAndRefsTimeStampArray(n, xAdESTimeStampType);
                    return xAdESTimeStampType2;
                }

                @Override
                public void add(int n, XAdESTimeStampType xAdESTimeStampType) {
                    UnsignedSignaturePropertiesTypeImpl.this.insertNewSigAndRefsTimeStamp(n).set(xAdESTimeStampType);
                }

                @Override
                public XAdESTimeStampType remove(int n) {
                    XAdESTimeStampType xAdESTimeStampType = UnsignedSignaturePropertiesTypeImpl.this.getSigAndRefsTimeStampArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.removeSigAndRefsTimeStamp(n);
                    return xAdESTimeStampType;
                }

                @Override
                public int size() {
                    return UnsignedSignaturePropertiesTypeImpl.this.sizeOfSigAndRefsTimeStampArray();
                }
            }
            return new SigAndRefsTimeStampList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XAdESTimeStampType[] getSigAndRefsTimeStampArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SIGANDREFSTIMESTAMP$12, arrayList);
            XAdESTimeStampType[] xAdESTimeStampTypeArray = new XAdESTimeStampType[arrayList.size()];
            arrayList.toArray(xAdESTimeStampTypeArray);
            return xAdESTimeStampTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XAdESTimeStampType getSigAndRefsTimeStampArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XAdESTimeStampType xAdESTimeStampType = null;
            xAdESTimeStampType = (XAdESTimeStampType)((Object)this.get_store().find_element_user(SIGANDREFSTIMESTAMP$12, n));
            if (xAdESTimeStampType == null) {
                throw new IndexOutOfBoundsException();
            }
            return xAdESTimeStampType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSigAndRefsTimeStampArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIGANDREFSTIMESTAMP$12);
        }
    }

    @Override
    public void setSigAndRefsTimeStampArray(XAdESTimeStampType[] xAdESTimeStampTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(xAdESTimeStampTypeArray, SIGANDREFSTIMESTAMP$12);
    }

    @Override
    public void setSigAndRefsTimeStampArray(int n, XAdESTimeStampType xAdESTimeStampType) {
        this.generatedSetterHelperImpl(xAdESTimeStampType, SIGANDREFSTIMESTAMP$12, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XAdESTimeStampType insertNewSigAndRefsTimeStamp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XAdESTimeStampType xAdESTimeStampType = null;
            xAdESTimeStampType = (XAdESTimeStampType)((Object)this.get_store().insert_element_user(SIGANDREFSTIMESTAMP$12, n));
            return xAdESTimeStampType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XAdESTimeStampType addNewSigAndRefsTimeStamp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XAdESTimeStampType xAdESTimeStampType = null;
            xAdESTimeStampType = (XAdESTimeStampType)((Object)this.get_store().add_element_user(SIGANDREFSTIMESTAMP$12));
            return xAdESTimeStampType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSigAndRefsTimeStamp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIGANDREFSTIMESTAMP$12, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XAdESTimeStampType> getRefsOnlyTimeStampList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RefsOnlyTimeStampList
            extends AbstractList<XAdESTimeStampType> {
                RefsOnlyTimeStampList() {
                }

                @Override
                public XAdESTimeStampType get(int n) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getRefsOnlyTimeStampArray(n);
                }

                @Override
                public XAdESTimeStampType set(int n, XAdESTimeStampType xAdESTimeStampType) {
                    XAdESTimeStampType xAdESTimeStampType2 = UnsignedSignaturePropertiesTypeImpl.this.getRefsOnlyTimeStampArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.setRefsOnlyTimeStampArray(n, xAdESTimeStampType);
                    return xAdESTimeStampType2;
                }

                @Override
                public void add(int n, XAdESTimeStampType xAdESTimeStampType) {
                    UnsignedSignaturePropertiesTypeImpl.this.insertNewRefsOnlyTimeStamp(n).set(xAdESTimeStampType);
                }

                @Override
                public XAdESTimeStampType remove(int n) {
                    XAdESTimeStampType xAdESTimeStampType = UnsignedSignaturePropertiesTypeImpl.this.getRefsOnlyTimeStampArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.removeRefsOnlyTimeStamp(n);
                    return xAdESTimeStampType;
                }

                @Override
                public int size() {
                    return UnsignedSignaturePropertiesTypeImpl.this.sizeOfRefsOnlyTimeStampArray();
                }
            }
            return new RefsOnlyTimeStampList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XAdESTimeStampType[] getRefsOnlyTimeStampArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(REFSONLYTIMESTAMP$14, arrayList);
            XAdESTimeStampType[] xAdESTimeStampTypeArray = new XAdESTimeStampType[arrayList.size()];
            arrayList.toArray(xAdESTimeStampTypeArray);
            return xAdESTimeStampTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XAdESTimeStampType getRefsOnlyTimeStampArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XAdESTimeStampType xAdESTimeStampType = null;
            xAdESTimeStampType = (XAdESTimeStampType)((Object)this.get_store().find_element_user(REFSONLYTIMESTAMP$14, n));
            if (xAdESTimeStampType == null) {
                throw new IndexOutOfBoundsException();
            }
            return xAdESTimeStampType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRefsOnlyTimeStampArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(REFSONLYTIMESTAMP$14);
        }
    }

    @Override
    public void setRefsOnlyTimeStampArray(XAdESTimeStampType[] xAdESTimeStampTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(xAdESTimeStampTypeArray, REFSONLYTIMESTAMP$14);
    }

    @Override
    public void setRefsOnlyTimeStampArray(int n, XAdESTimeStampType xAdESTimeStampType) {
        this.generatedSetterHelperImpl(xAdESTimeStampType, REFSONLYTIMESTAMP$14, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XAdESTimeStampType insertNewRefsOnlyTimeStamp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XAdESTimeStampType xAdESTimeStampType = null;
            xAdESTimeStampType = (XAdESTimeStampType)((Object)this.get_store().insert_element_user(REFSONLYTIMESTAMP$14, n));
            return xAdESTimeStampType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XAdESTimeStampType addNewRefsOnlyTimeStamp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XAdESTimeStampType xAdESTimeStampType = null;
            xAdESTimeStampType = (XAdESTimeStampType)((Object)this.get_store().add_element_user(REFSONLYTIMESTAMP$14));
            return xAdESTimeStampType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRefsOnlyTimeStamp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(REFSONLYTIMESTAMP$14, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CertificateValuesType> getCertificateValuesList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CertificateValuesList
            extends AbstractList<CertificateValuesType> {
                CertificateValuesList() {
                }

                @Override
                public CertificateValuesType get(int n) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getCertificateValuesArray(n);
                }

                @Override
                public CertificateValuesType set(int n, CertificateValuesType certificateValuesType) {
                    CertificateValuesType certificateValuesType2 = UnsignedSignaturePropertiesTypeImpl.this.getCertificateValuesArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.setCertificateValuesArray(n, certificateValuesType);
                    return certificateValuesType2;
                }

                @Override
                public void add(int n, CertificateValuesType certificateValuesType) {
                    UnsignedSignaturePropertiesTypeImpl.this.insertNewCertificateValues(n).set(certificateValuesType);
                }

                @Override
                public CertificateValuesType remove(int n) {
                    CertificateValuesType certificateValuesType = UnsignedSignaturePropertiesTypeImpl.this.getCertificateValuesArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.removeCertificateValues(n);
                    return certificateValuesType;
                }

                @Override
                public int size() {
                    return UnsignedSignaturePropertiesTypeImpl.this.sizeOfCertificateValuesArray();
                }
            }
            return new CertificateValuesList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CertificateValuesType[] getCertificateValuesArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CERTIFICATEVALUES$16, arrayList);
            CertificateValuesType[] certificateValuesTypeArray = new CertificateValuesType[arrayList.size()];
            arrayList.toArray(certificateValuesTypeArray);
            return certificateValuesTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CertificateValuesType getCertificateValuesArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CertificateValuesType certificateValuesType = null;
            certificateValuesType = (CertificateValuesType)((Object)this.get_store().find_element_user(CERTIFICATEVALUES$16, n));
            if (certificateValuesType == null) {
                throw new IndexOutOfBoundsException();
            }
            return certificateValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCertificateValuesArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CERTIFICATEVALUES$16);
        }
    }

    @Override
    public void setCertificateValuesArray(CertificateValuesType[] certificateValuesTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(certificateValuesTypeArray, CERTIFICATEVALUES$16);
    }

    @Override
    public void setCertificateValuesArray(int n, CertificateValuesType certificateValuesType) {
        this.generatedSetterHelperImpl(certificateValuesType, CERTIFICATEVALUES$16, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CertificateValuesType insertNewCertificateValues(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CertificateValuesType certificateValuesType = null;
            certificateValuesType = (CertificateValuesType)((Object)this.get_store().insert_element_user(CERTIFICATEVALUES$16, n));
            return certificateValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CertificateValuesType addNewCertificateValues() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CertificateValuesType certificateValuesType = null;
            certificateValuesType = (CertificateValuesType)((Object)this.get_store().add_element_user(CERTIFICATEVALUES$16));
            return certificateValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCertificateValues(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CERTIFICATEVALUES$16, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<RevocationValuesType> getRevocationValuesList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RevocationValuesList
            extends AbstractList<RevocationValuesType> {
                RevocationValuesList() {
                }

                @Override
                public RevocationValuesType get(int n) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getRevocationValuesArray(n);
                }

                @Override
                public RevocationValuesType set(int n, RevocationValuesType revocationValuesType) {
                    RevocationValuesType revocationValuesType2 = UnsignedSignaturePropertiesTypeImpl.this.getRevocationValuesArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.setRevocationValuesArray(n, revocationValuesType);
                    return revocationValuesType2;
                }

                @Override
                public void add(int n, RevocationValuesType revocationValuesType) {
                    UnsignedSignaturePropertiesTypeImpl.this.insertNewRevocationValues(n).set(revocationValuesType);
                }

                @Override
                public RevocationValuesType remove(int n) {
                    RevocationValuesType revocationValuesType = UnsignedSignaturePropertiesTypeImpl.this.getRevocationValuesArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.removeRevocationValues(n);
                    return revocationValuesType;
                }

                @Override
                public int size() {
                    return UnsignedSignaturePropertiesTypeImpl.this.sizeOfRevocationValuesArray();
                }
            }
            return new RevocationValuesList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public RevocationValuesType[] getRevocationValuesArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(REVOCATIONVALUES$18, arrayList);
            RevocationValuesType[] revocationValuesTypeArray = new RevocationValuesType[arrayList.size()];
            arrayList.toArray(revocationValuesTypeArray);
            return revocationValuesTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public RevocationValuesType getRevocationValuesArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            RevocationValuesType revocationValuesType = null;
            revocationValuesType = (RevocationValuesType)((Object)this.get_store().find_element_user(REVOCATIONVALUES$18, n));
            if (revocationValuesType == null) {
                throw new IndexOutOfBoundsException();
            }
            return revocationValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRevocationValuesArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(REVOCATIONVALUES$18);
        }
    }

    @Override
    public void setRevocationValuesArray(RevocationValuesType[] revocationValuesTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(revocationValuesTypeArray, REVOCATIONVALUES$18);
    }

    @Override
    public void setRevocationValuesArray(int n, RevocationValuesType revocationValuesType) {
        this.generatedSetterHelperImpl(revocationValuesType, REVOCATIONVALUES$18, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public RevocationValuesType insertNewRevocationValues(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            RevocationValuesType revocationValuesType = null;
            revocationValuesType = (RevocationValuesType)((Object)this.get_store().insert_element_user(REVOCATIONVALUES$18, n));
            return revocationValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public RevocationValuesType addNewRevocationValues() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            RevocationValuesType revocationValuesType = null;
            revocationValuesType = (RevocationValuesType)((Object)this.get_store().add_element_user(REVOCATIONVALUES$18));
            return revocationValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRevocationValues(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(REVOCATIONVALUES$18, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CertificateValuesType> getAttrAuthoritiesCertValuesList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AttrAuthoritiesCertValuesList
            extends AbstractList<CertificateValuesType> {
                AttrAuthoritiesCertValuesList() {
                }

                @Override
                public CertificateValuesType get(int n) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getAttrAuthoritiesCertValuesArray(n);
                }

                @Override
                public CertificateValuesType set(int n, CertificateValuesType certificateValuesType) {
                    CertificateValuesType certificateValuesType2 = UnsignedSignaturePropertiesTypeImpl.this.getAttrAuthoritiesCertValuesArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.setAttrAuthoritiesCertValuesArray(n, certificateValuesType);
                    return certificateValuesType2;
                }

                @Override
                public void add(int n, CertificateValuesType certificateValuesType) {
                    UnsignedSignaturePropertiesTypeImpl.this.insertNewAttrAuthoritiesCertValues(n).set(certificateValuesType);
                }

                @Override
                public CertificateValuesType remove(int n) {
                    CertificateValuesType certificateValuesType = UnsignedSignaturePropertiesTypeImpl.this.getAttrAuthoritiesCertValuesArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.removeAttrAuthoritiesCertValues(n);
                    return certificateValuesType;
                }

                @Override
                public int size() {
                    return UnsignedSignaturePropertiesTypeImpl.this.sizeOfAttrAuthoritiesCertValuesArray();
                }
            }
            return new AttrAuthoritiesCertValuesList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CertificateValuesType[] getAttrAuthoritiesCertValuesArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ATTRAUTHORITIESCERTVALUES$20, arrayList);
            CertificateValuesType[] certificateValuesTypeArray = new CertificateValuesType[arrayList.size()];
            arrayList.toArray(certificateValuesTypeArray);
            return certificateValuesTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CertificateValuesType getAttrAuthoritiesCertValuesArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CertificateValuesType certificateValuesType = null;
            certificateValuesType = (CertificateValuesType)((Object)this.get_store().find_element_user(ATTRAUTHORITIESCERTVALUES$20, n));
            if (certificateValuesType == null) {
                throw new IndexOutOfBoundsException();
            }
            return certificateValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAttrAuthoritiesCertValuesArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ATTRAUTHORITIESCERTVALUES$20);
        }
    }

    @Override
    public void setAttrAuthoritiesCertValuesArray(CertificateValuesType[] certificateValuesTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(certificateValuesTypeArray, ATTRAUTHORITIESCERTVALUES$20);
    }

    @Override
    public void setAttrAuthoritiesCertValuesArray(int n, CertificateValuesType certificateValuesType) {
        this.generatedSetterHelperImpl(certificateValuesType, ATTRAUTHORITIESCERTVALUES$20, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CertificateValuesType insertNewAttrAuthoritiesCertValues(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CertificateValuesType certificateValuesType = null;
            certificateValuesType = (CertificateValuesType)((Object)this.get_store().insert_element_user(ATTRAUTHORITIESCERTVALUES$20, n));
            return certificateValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CertificateValuesType addNewAttrAuthoritiesCertValues() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CertificateValuesType certificateValuesType = null;
            certificateValuesType = (CertificateValuesType)((Object)this.get_store().add_element_user(ATTRAUTHORITIESCERTVALUES$20));
            return certificateValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAttrAuthoritiesCertValues(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ATTRAUTHORITIESCERTVALUES$20, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<RevocationValuesType> getAttributeRevocationValuesList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class AttributeRevocationValuesList
            extends AbstractList<RevocationValuesType> {
                AttributeRevocationValuesList() {
                }

                @Override
                public RevocationValuesType get(int n) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getAttributeRevocationValuesArray(n);
                }

                @Override
                public RevocationValuesType set(int n, RevocationValuesType revocationValuesType) {
                    RevocationValuesType revocationValuesType2 = UnsignedSignaturePropertiesTypeImpl.this.getAttributeRevocationValuesArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.setAttributeRevocationValuesArray(n, revocationValuesType);
                    return revocationValuesType2;
                }

                @Override
                public void add(int n, RevocationValuesType revocationValuesType) {
                    UnsignedSignaturePropertiesTypeImpl.this.insertNewAttributeRevocationValues(n).set(revocationValuesType);
                }

                @Override
                public RevocationValuesType remove(int n) {
                    RevocationValuesType revocationValuesType = UnsignedSignaturePropertiesTypeImpl.this.getAttributeRevocationValuesArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.removeAttributeRevocationValues(n);
                    return revocationValuesType;
                }

                @Override
                public int size() {
                    return UnsignedSignaturePropertiesTypeImpl.this.sizeOfAttributeRevocationValuesArray();
                }
            }
            return new AttributeRevocationValuesList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public RevocationValuesType[] getAttributeRevocationValuesArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ATTRIBUTEREVOCATIONVALUES$22, arrayList);
            RevocationValuesType[] revocationValuesTypeArray = new RevocationValuesType[arrayList.size()];
            arrayList.toArray(revocationValuesTypeArray);
            return revocationValuesTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public RevocationValuesType getAttributeRevocationValuesArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            RevocationValuesType revocationValuesType = null;
            revocationValuesType = (RevocationValuesType)((Object)this.get_store().find_element_user(ATTRIBUTEREVOCATIONVALUES$22, n));
            if (revocationValuesType == null) {
                throw new IndexOutOfBoundsException();
            }
            return revocationValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfAttributeRevocationValuesArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ATTRIBUTEREVOCATIONVALUES$22);
        }
    }

    @Override
    public void setAttributeRevocationValuesArray(RevocationValuesType[] revocationValuesTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(revocationValuesTypeArray, ATTRIBUTEREVOCATIONVALUES$22);
    }

    @Override
    public void setAttributeRevocationValuesArray(int n, RevocationValuesType revocationValuesType) {
        this.generatedSetterHelperImpl(revocationValuesType, ATTRIBUTEREVOCATIONVALUES$22, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public RevocationValuesType insertNewAttributeRevocationValues(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            RevocationValuesType revocationValuesType = null;
            revocationValuesType = (RevocationValuesType)((Object)this.get_store().insert_element_user(ATTRIBUTEREVOCATIONVALUES$22, n));
            return revocationValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public RevocationValuesType addNewAttributeRevocationValues() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            RevocationValuesType revocationValuesType = null;
            revocationValuesType = (RevocationValuesType)((Object)this.get_store().add_element_user(ATTRIBUTEREVOCATIONVALUES$22));
            return revocationValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAttributeRevocationValues(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ATTRIBUTEREVOCATIONVALUES$22, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<XAdESTimeStampType> getArchiveTimeStampList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ArchiveTimeStampList
            extends AbstractList<XAdESTimeStampType> {
                ArchiveTimeStampList() {
                }

                @Override
                public XAdESTimeStampType get(int n) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getArchiveTimeStampArray(n);
                }

                @Override
                public XAdESTimeStampType set(int n, XAdESTimeStampType xAdESTimeStampType) {
                    XAdESTimeStampType xAdESTimeStampType2 = UnsignedSignaturePropertiesTypeImpl.this.getArchiveTimeStampArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.setArchiveTimeStampArray(n, xAdESTimeStampType);
                    return xAdESTimeStampType2;
                }

                @Override
                public void add(int n, XAdESTimeStampType xAdESTimeStampType) {
                    UnsignedSignaturePropertiesTypeImpl.this.insertNewArchiveTimeStamp(n).set(xAdESTimeStampType);
                }

                @Override
                public XAdESTimeStampType remove(int n) {
                    XAdESTimeStampType xAdESTimeStampType = UnsignedSignaturePropertiesTypeImpl.this.getArchiveTimeStampArray(n);
                    UnsignedSignaturePropertiesTypeImpl.this.removeArchiveTimeStamp(n);
                    return xAdESTimeStampType;
                }

                @Override
                public int size() {
                    return UnsignedSignaturePropertiesTypeImpl.this.sizeOfArchiveTimeStampArray();
                }
            }
            return new ArchiveTimeStampList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public XAdESTimeStampType[] getArchiveTimeStampArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ARCHIVETIMESTAMP$24, arrayList);
            XAdESTimeStampType[] xAdESTimeStampTypeArray = new XAdESTimeStampType[arrayList.size()];
            arrayList.toArray(xAdESTimeStampTypeArray);
            return xAdESTimeStampTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XAdESTimeStampType getArchiveTimeStampArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XAdESTimeStampType xAdESTimeStampType = null;
            xAdESTimeStampType = (XAdESTimeStampType)((Object)this.get_store().find_element_user(ARCHIVETIMESTAMP$24, n));
            if (xAdESTimeStampType == null) {
                throw new IndexOutOfBoundsException();
            }
            return xAdESTimeStampType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfArchiveTimeStampArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ARCHIVETIMESTAMP$24);
        }
    }

    @Override
    public void setArchiveTimeStampArray(XAdESTimeStampType[] xAdESTimeStampTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(xAdESTimeStampTypeArray, ARCHIVETIMESTAMP$24);
    }

    @Override
    public void setArchiveTimeStampArray(int n, XAdESTimeStampType xAdESTimeStampType) {
        this.generatedSetterHelperImpl(xAdESTimeStampType, ARCHIVETIMESTAMP$24, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XAdESTimeStampType insertNewArchiveTimeStamp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XAdESTimeStampType xAdESTimeStampType = null;
            xAdESTimeStampType = (XAdESTimeStampType)((Object)this.get_store().insert_element_user(ARCHIVETIMESTAMP$24, n));
            return xAdESTimeStampType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XAdESTimeStampType addNewArchiveTimeStamp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XAdESTimeStampType xAdESTimeStampType = null;
            xAdESTimeStampType = (XAdESTimeStampType)((Object)this.get_store().add_element_user(ARCHIVETIMESTAMP$24));
            return xAdESTimeStampType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeArchiveTimeStamp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ARCHIVETIMESTAMP$24, n);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$26));
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
            xmlID = (XmlID)((Object)this.get_store().find_attribute_user(ID$26));
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
            return this.get_store().find_attribute_user(ID$26) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$26));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$26));
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
            xmlID2 = (XmlID)((Object)this.get_store().find_attribute_user(ID$26));
            if (xmlID2 == null) {
                xmlID2 = (XmlID)((Object)this.get_store().add_attribute_user(ID$26));
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
            this.get_store().remove_attribute(ID$26);
        }
    }
}

