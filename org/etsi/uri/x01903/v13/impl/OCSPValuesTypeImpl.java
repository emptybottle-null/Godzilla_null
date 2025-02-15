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
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;
import org.etsi.uri.x01903.v13.OCSPValuesType;

public class OCSPValuesTypeImpl
extends XmlComplexContentImpl
implements OCSPValuesType {
    private static final long serialVersionUID = 1L;
    private static final QName ENCAPSULATEDOCSPVALUE$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "EncapsulatedOCSPValue");

    public OCSPValuesTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<EncapsulatedPKIDataType> getEncapsulatedOCSPValueList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class EncapsulatedOCSPValueList
            extends AbstractList<EncapsulatedPKIDataType> {
                EncapsulatedOCSPValueList() {
                }

                @Override
                public EncapsulatedPKIDataType get(int n) {
                    return OCSPValuesTypeImpl.this.getEncapsulatedOCSPValueArray(n);
                }

                @Override
                public EncapsulatedPKIDataType set(int n, EncapsulatedPKIDataType encapsulatedPKIDataType) {
                    EncapsulatedPKIDataType encapsulatedPKIDataType2 = OCSPValuesTypeImpl.this.getEncapsulatedOCSPValueArray(n);
                    OCSPValuesTypeImpl.this.setEncapsulatedOCSPValueArray(n, encapsulatedPKIDataType);
                    return encapsulatedPKIDataType2;
                }

                @Override
                public void add(int n, EncapsulatedPKIDataType encapsulatedPKIDataType) {
                    OCSPValuesTypeImpl.this.insertNewEncapsulatedOCSPValue(n).set(encapsulatedPKIDataType);
                }

                @Override
                public EncapsulatedPKIDataType remove(int n) {
                    EncapsulatedPKIDataType encapsulatedPKIDataType = OCSPValuesTypeImpl.this.getEncapsulatedOCSPValueArray(n);
                    OCSPValuesTypeImpl.this.removeEncapsulatedOCSPValue(n);
                    return encapsulatedPKIDataType;
                }

                @Override
                public int size() {
                    return OCSPValuesTypeImpl.this.sizeOfEncapsulatedOCSPValueArray();
                }
            }
            return new EncapsulatedOCSPValueList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public EncapsulatedPKIDataType[] getEncapsulatedOCSPValueArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ENCAPSULATEDOCSPVALUE$0, arrayList);
            EncapsulatedPKIDataType[] encapsulatedPKIDataTypeArray = new EncapsulatedPKIDataType[arrayList.size()];
            arrayList.toArray(encapsulatedPKIDataTypeArray);
            return encapsulatedPKIDataTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public EncapsulatedPKIDataType getEncapsulatedOCSPValueArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            EncapsulatedPKIDataType encapsulatedPKIDataType = null;
            encapsulatedPKIDataType = (EncapsulatedPKIDataType)((Object)this.get_store().find_element_user(ENCAPSULATEDOCSPVALUE$0, n));
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
    public int sizeOfEncapsulatedOCSPValueArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ENCAPSULATEDOCSPVALUE$0);
        }
    }

    @Override
    public void setEncapsulatedOCSPValueArray(EncapsulatedPKIDataType[] encapsulatedPKIDataTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(encapsulatedPKIDataTypeArray, ENCAPSULATEDOCSPVALUE$0);
    }

    @Override
    public void setEncapsulatedOCSPValueArray(int n, EncapsulatedPKIDataType encapsulatedPKIDataType) {
        this.generatedSetterHelperImpl(encapsulatedPKIDataType, ENCAPSULATEDOCSPVALUE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public EncapsulatedPKIDataType insertNewEncapsulatedOCSPValue(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            EncapsulatedPKIDataType encapsulatedPKIDataType = null;
            encapsulatedPKIDataType = (EncapsulatedPKIDataType)((Object)this.get_store().insert_element_user(ENCAPSULATEDOCSPVALUE$0, n));
            return encapsulatedPKIDataType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public EncapsulatedPKIDataType addNewEncapsulatedOCSPValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            EncapsulatedPKIDataType encapsulatedPKIDataType = null;
            encapsulatedPKIDataType = (EncapsulatedPKIDataType)((Object)this.get_store().add_element_user(ENCAPSULATEDOCSPVALUE$0));
            return encapsulatedPKIDataType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeEncapsulatedOCSPValue(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ENCAPSULATEDOCSPVALUE$0, n);
        }
    }
}

