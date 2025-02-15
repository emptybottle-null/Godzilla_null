/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.etsi.uri.x01903.v13.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CRLValuesType;
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;

public class CRLValuesTypeImpl
extends XmlComplexContentImpl
implements CRLValuesType {
    private static final long serialVersionUID = 1L;
    private static final QName ENCAPSULATEDCRLVALUE$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "EncapsulatedCRLValue");

    public CRLValuesTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<EncapsulatedPKIDataType> getEncapsulatedCRLValueList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class EncapsulatedCRLValueList
            extends AbstractList<EncapsulatedPKIDataType> {
                EncapsulatedCRLValueList() {
                }

                @Override
                public EncapsulatedPKIDataType get(int n) {
                    return CRLValuesTypeImpl.this.getEncapsulatedCRLValueArray(n);
                }

                @Override
                public EncapsulatedPKIDataType set(int n, EncapsulatedPKIDataType encapsulatedPKIDataType) {
                    EncapsulatedPKIDataType encapsulatedPKIDataType2 = CRLValuesTypeImpl.this.getEncapsulatedCRLValueArray(n);
                    CRLValuesTypeImpl.this.setEncapsulatedCRLValueArray(n, encapsulatedPKIDataType);
                    return encapsulatedPKIDataType2;
                }

                @Override
                public void add(int n, EncapsulatedPKIDataType encapsulatedPKIDataType) {
                    CRLValuesTypeImpl.this.insertNewEncapsulatedCRLValue(n).set(encapsulatedPKIDataType);
                }

                @Override
                public EncapsulatedPKIDataType remove(int n) {
                    EncapsulatedPKIDataType encapsulatedPKIDataType = CRLValuesTypeImpl.this.getEncapsulatedCRLValueArray(n);
                    CRLValuesTypeImpl.this.removeEncapsulatedCRLValue(n);
                    return encapsulatedPKIDataType;
                }

                @Override
                public int size() {
                    return CRLValuesTypeImpl.this.sizeOfEncapsulatedCRLValueArray();
                }
            }
            return new EncapsulatedCRLValueList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public EncapsulatedPKIDataType[] getEncapsulatedCRLValueArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ENCAPSULATEDCRLVALUE$0, arrayList);
            EncapsulatedPKIDataType[] encapsulatedPKIDataTypeArray = new EncapsulatedPKIDataType[arrayList.size()];
            arrayList.toArray(encapsulatedPKIDataTypeArray);
            return encapsulatedPKIDataTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public EncapsulatedPKIDataType getEncapsulatedCRLValueArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            EncapsulatedPKIDataType encapsulatedPKIDataType = null;
            encapsulatedPKIDataType = (EncapsulatedPKIDataType)((Object)this.get_store().find_element_user(ENCAPSULATEDCRLVALUE$0, n));
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
    public int sizeOfEncapsulatedCRLValueArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ENCAPSULATEDCRLVALUE$0);
        }
    }

    @Override
    public void setEncapsulatedCRLValueArray(EncapsulatedPKIDataType[] encapsulatedPKIDataTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(encapsulatedPKIDataTypeArray, ENCAPSULATEDCRLVALUE$0);
    }

    @Override
    public void setEncapsulatedCRLValueArray(int n, EncapsulatedPKIDataType encapsulatedPKIDataType) {
        this.generatedSetterHelperImpl(encapsulatedPKIDataType, ENCAPSULATEDCRLVALUE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public EncapsulatedPKIDataType insertNewEncapsulatedCRLValue(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            EncapsulatedPKIDataType encapsulatedPKIDataType = null;
            encapsulatedPKIDataType = (EncapsulatedPKIDataType)((Object)this.get_store().insert_element_user(ENCAPSULATEDCRLVALUE$0, n));
            return encapsulatedPKIDataType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public EncapsulatedPKIDataType addNewEncapsulatedCRLValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            EncapsulatedPKIDataType encapsulatedPKIDataType = null;
            encapsulatedPKIDataType = (EncapsulatedPKIDataType)((Object)this.get_store().add_element_user(ENCAPSULATEDCRLVALUE$0));
            return encapsulatedPKIDataType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeEncapsulatedCRLValue(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ENCAPSULATEDCRLVALUE$0, n);
        }
    }
}

