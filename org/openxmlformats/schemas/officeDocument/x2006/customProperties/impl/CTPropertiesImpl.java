/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperties;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;

public class CTPropertiesImpl
extends XmlComplexContentImpl
implements CTProperties {
    private static final long serialVersionUID = 1L;
    private static final QName PROPERTY$0 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/custom-properties", "property");

    public CTPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTProperty> getPropertyList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class PropertyList
            extends AbstractList<CTProperty> {
                PropertyList() {
                }

                @Override
                public CTProperty get(int n) {
                    return CTPropertiesImpl.this.getPropertyArray(n);
                }

                @Override
                public CTProperty set(int n, CTProperty cTProperty) {
                    CTProperty cTProperty2 = CTPropertiesImpl.this.getPropertyArray(n);
                    CTPropertiesImpl.this.setPropertyArray(n, cTProperty);
                    return cTProperty2;
                }

                @Override
                public void add(int n, CTProperty cTProperty) {
                    CTPropertiesImpl.this.insertNewProperty(n).set(cTProperty);
                }

                @Override
                public CTProperty remove(int n) {
                    CTProperty cTProperty = CTPropertiesImpl.this.getPropertyArray(n);
                    CTPropertiesImpl.this.removeProperty(n);
                    return cTProperty;
                }

                @Override
                public int size() {
                    return CTPropertiesImpl.this.sizeOfPropertyArray();
                }
            }
            return new PropertyList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTProperty[] getPropertyArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(PROPERTY$0, arrayList);
            CTProperty[] cTPropertyArray = new CTProperty[arrayList.size()];
            arrayList.toArray(cTPropertyArray);
            return cTPropertyArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTProperty getPropertyArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTProperty cTProperty = null;
            cTProperty = (CTProperty)((Object)this.get_store().find_element_user(PROPERTY$0, n));
            if (cTProperty == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfPropertyArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PROPERTY$0);
        }
    }

    @Override
    public void setPropertyArray(CTProperty[] cTPropertyArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTPropertyArray, PROPERTY$0);
    }

    @Override
    public void setPropertyArray(int n, CTProperty cTProperty) {
        this.generatedSetterHelperImpl(cTProperty, PROPERTY$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTProperty insertNewProperty(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTProperty cTProperty = null;
            cTProperty = (CTProperty)((Object)this.get_store().insert_element_user(PROPERTY$0, n));
            return cTProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTProperty addNewProperty() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTProperty cTProperty = null;
            cTProperty = (CTProperty)((Object)this.get_store().add_element_user(PROPERTY$0));
            return cTProperty;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeProperty(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PROPERTY$0, n);
        }
    }
}

