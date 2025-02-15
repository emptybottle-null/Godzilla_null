/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTExtension
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtension;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;

public class CTExtensionListImpl
extends XmlComplexContentImpl
implements CTExtensionList {
    private static final long serialVersionUID = 1L;
    private static final QName EXT$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "ext");

    public CTExtensionListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTExtension> getExtList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ExtList
            extends AbstractList<CTExtension> {
                ExtList() {
                }

                @Override
                public CTExtension get(int n) {
                    return CTExtensionListImpl.this.getExtArray(n);
                }

                @Override
                public CTExtension set(int n, CTExtension cTExtension) {
                    CTExtension cTExtension2 = CTExtensionListImpl.this.getExtArray(n);
                    CTExtensionListImpl.this.setExtArray(n, cTExtension);
                    return cTExtension2;
                }

                @Override
                public void add(int n, CTExtension cTExtension) {
                    CTExtensionListImpl.this.insertNewExt(n).set((XmlObject)cTExtension);
                }

                @Override
                public CTExtension remove(int n) {
                    CTExtension cTExtension = CTExtensionListImpl.this.getExtArray(n);
                    CTExtensionListImpl.this.removeExt(n);
                    return cTExtension;
                }

                @Override
                public int size() {
                    return CTExtensionListImpl.this.sizeOfExtArray();
                }
            }
            return new ExtList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTExtension[] getExtArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(EXT$0, arrayList);
            CTExtension[] cTExtensionArray = new CTExtension[arrayList.size()];
            arrayList.toArray(cTExtensionArray);
            return cTExtensionArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtension getExtArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtension cTExtension = null;
            cTExtension = (CTExtension)this.get_store().find_element_user(EXT$0, n);
            if (cTExtension == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTExtension;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfExtArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXT$0);
        }
    }

    @Override
    public void setExtArray(CTExtension[] cTExtensionArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTExtensionArray, EXT$0);
    }

    @Override
    public void setExtArray(int n, CTExtension cTExtension) {
        this.generatedSetterHelperImpl((XmlObject)cTExtension, EXT$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtension insertNewExt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtension cTExtension = null;
            cTExtension = (CTExtension)this.get_store().insert_element_user(EXT$0, n);
            return cTExtension;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtension addNewExt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtension cTExtension = null;
            cTExtension = (CTExtension)this.get_store().add_element_user(EXT$0);
            return cTExtension;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeExt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXT$0, n);
        }
    }
}

