/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReference;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences;

public class CTExternalReferencesImpl
extends XmlComplexContentImpl
implements CTExternalReferences {
    private static final long serialVersionUID = 1L;
    private static final QName EXTERNALREFERENCE$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "externalReference");

    public CTExternalReferencesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTExternalReference> getExternalReferenceList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ExternalReferenceList
            extends AbstractList<CTExternalReference> {
                ExternalReferenceList() {
                }

                @Override
                public CTExternalReference get(int n) {
                    return CTExternalReferencesImpl.this.getExternalReferenceArray(n);
                }

                @Override
                public CTExternalReference set(int n, CTExternalReference cTExternalReference) {
                    CTExternalReference cTExternalReference2 = CTExternalReferencesImpl.this.getExternalReferenceArray(n);
                    CTExternalReferencesImpl.this.setExternalReferenceArray(n, cTExternalReference);
                    return cTExternalReference2;
                }

                @Override
                public void add(int n, CTExternalReference cTExternalReference) {
                    CTExternalReferencesImpl.this.insertNewExternalReference(n).set(cTExternalReference);
                }

                @Override
                public CTExternalReference remove(int n) {
                    CTExternalReference cTExternalReference = CTExternalReferencesImpl.this.getExternalReferenceArray(n);
                    CTExternalReferencesImpl.this.removeExternalReference(n);
                    return cTExternalReference;
                }

                @Override
                public int size() {
                    return CTExternalReferencesImpl.this.sizeOfExternalReferenceArray();
                }
            }
            return new ExternalReferenceList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTExternalReference[] getExternalReferenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(EXTERNALREFERENCE$0, arrayList);
            CTExternalReference[] cTExternalReferenceArray = new CTExternalReference[arrayList.size()];
            arrayList.toArray(cTExternalReferenceArray);
            return cTExternalReferenceArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalReference getExternalReferenceArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalReference cTExternalReference = null;
            cTExternalReference = (CTExternalReference)((Object)this.get_store().find_element_user(EXTERNALREFERENCE$0, n));
            if (cTExternalReference == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTExternalReference;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfExternalReferenceArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTERNALREFERENCE$0);
        }
    }

    @Override
    public void setExternalReferenceArray(CTExternalReference[] cTExternalReferenceArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTExternalReferenceArray, EXTERNALREFERENCE$0);
    }

    @Override
    public void setExternalReferenceArray(int n, CTExternalReference cTExternalReference) {
        this.generatedSetterHelperImpl(cTExternalReference, EXTERNALREFERENCE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalReference insertNewExternalReference(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalReference cTExternalReference = null;
            cTExternalReference = (CTExternalReference)((Object)this.get_store().insert_element_user(EXTERNALREFERENCE$0, n));
            return cTExternalReference;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalReference addNewExternalReference() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalReference cTExternalReference = null;
            cTExternalReference = (CTExternalReference)((Object)this.get_store().add_element_user(EXTERNALREFERENCE$0));
            return cTExternalReference;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeExternalReference(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTERNALREFERENCE$0, n);
        }
    }
}

