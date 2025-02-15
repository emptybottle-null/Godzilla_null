/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;

public class CTOfficeArtExtensionListImpl
extends XmlComplexContentImpl
implements CTOfficeArtExtensionList {
    private static final long serialVersionUID = 1L;
    private static final QName EXT$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "ext");

    public CTOfficeArtExtensionListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTOfficeArtExtension> getExtList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ExtList
            extends AbstractList<CTOfficeArtExtension> {
                ExtList() {
                }

                @Override
                public CTOfficeArtExtension get(int n) {
                    return CTOfficeArtExtensionListImpl.this.getExtArray(n);
                }

                @Override
                public CTOfficeArtExtension set(int n, CTOfficeArtExtension cTOfficeArtExtension) {
                    CTOfficeArtExtension cTOfficeArtExtension2 = CTOfficeArtExtensionListImpl.this.getExtArray(n);
                    CTOfficeArtExtensionListImpl.this.setExtArray(n, cTOfficeArtExtension);
                    return cTOfficeArtExtension2;
                }

                @Override
                public void add(int n, CTOfficeArtExtension cTOfficeArtExtension) {
                    CTOfficeArtExtensionListImpl.this.insertNewExt(n).set(cTOfficeArtExtension);
                }

                @Override
                public CTOfficeArtExtension remove(int n) {
                    CTOfficeArtExtension cTOfficeArtExtension = CTOfficeArtExtensionListImpl.this.getExtArray(n);
                    CTOfficeArtExtensionListImpl.this.removeExt(n);
                    return cTOfficeArtExtension;
                }

                @Override
                public int size() {
                    return CTOfficeArtExtensionListImpl.this.sizeOfExtArray();
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
    public CTOfficeArtExtension[] getExtArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(EXT$0, arrayList);
            CTOfficeArtExtension[] cTOfficeArtExtensionArray = new CTOfficeArtExtension[arrayList.size()];
            arrayList.toArray(cTOfficeArtExtensionArray);
            return cTOfficeArtExtensionArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtension getExtArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtension cTOfficeArtExtension = null;
            cTOfficeArtExtension = (CTOfficeArtExtension)((Object)this.get_store().find_element_user(EXT$0, n));
            if (cTOfficeArtExtension == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTOfficeArtExtension;
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
    public void setExtArray(CTOfficeArtExtension[] cTOfficeArtExtensionArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTOfficeArtExtensionArray, EXT$0);
    }

    @Override
    public void setExtArray(int n, CTOfficeArtExtension cTOfficeArtExtension) {
        this.generatedSetterHelperImpl(cTOfficeArtExtension, EXT$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtension insertNewExt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtension cTOfficeArtExtension = null;
            cTOfficeArtExtension = (CTOfficeArtExtension)((Object)this.get_store().insert_element_user(EXT$0, n));
            return cTOfficeArtExtension;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtension addNewExt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtension cTOfficeArtExtension = null;
            cTOfficeArtExtension = (CTOfficeArtExtension)((Object)this.get_store().add_element_user(EXT$0));
            return cTOfficeArtExtension;
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

