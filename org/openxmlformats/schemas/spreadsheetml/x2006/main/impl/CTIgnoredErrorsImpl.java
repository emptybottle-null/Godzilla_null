/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredError;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredErrors;

public class CTIgnoredErrorsImpl
extends XmlComplexContentImpl
implements CTIgnoredErrors {
    private static final long serialVersionUID = 1L;
    private static final QName IGNOREDERROR$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "ignoredError");
    private static final QName EXTLST$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "extLst");

    public CTIgnoredErrorsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTIgnoredError> getIgnoredErrorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class IgnoredErrorList
            extends AbstractList<CTIgnoredError> {
                IgnoredErrorList() {
                }

                @Override
                public CTIgnoredError get(int n) {
                    return CTIgnoredErrorsImpl.this.getIgnoredErrorArray(n);
                }

                @Override
                public CTIgnoredError set(int n, CTIgnoredError cTIgnoredError) {
                    CTIgnoredError cTIgnoredError2 = CTIgnoredErrorsImpl.this.getIgnoredErrorArray(n);
                    CTIgnoredErrorsImpl.this.setIgnoredErrorArray(n, cTIgnoredError);
                    return cTIgnoredError2;
                }

                @Override
                public void add(int n, CTIgnoredError cTIgnoredError) {
                    CTIgnoredErrorsImpl.this.insertNewIgnoredError(n).set(cTIgnoredError);
                }

                @Override
                public CTIgnoredError remove(int n) {
                    CTIgnoredError cTIgnoredError = CTIgnoredErrorsImpl.this.getIgnoredErrorArray(n);
                    CTIgnoredErrorsImpl.this.removeIgnoredError(n);
                    return cTIgnoredError;
                }

                @Override
                public int size() {
                    return CTIgnoredErrorsImpl.this.sizeOfIgnoredErrorArray();
                }
            }
            return new IgnoredErrorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTIgnoredError[] getIgnoredErrorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(IGNOREDERROR$0, arrayList);
            CTIgnoredError[] cTIgnoredErrorArray = new CTIgnoredError[arrayList.size()];
            arrayList.toArray(cTIgnoredErrorArray);
            return cTIgnoredErrorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTIgnoredError getIgnoredErrorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTIgnoredError cTIgnoredError = null;
            cTIgnoredError = (CTIgnoredError)((Object)this.get_store().find_element_user(IGNOREDERROR$0, n));
            if (cTIgnoredError == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTIgnoredError;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfIgnoredErrorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(IGNOREDERROR$0);
        }
    }

    @Override
    public void setIgnoredErrorArray(CTIgnoredError[] cTIgnoredErrorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTIgnoredErrorArray, IGNOREDERROR$0);
    }

    @Override
    public void setIgnoredErrorArray(int n, CTIgnoredError cTIgnoredError) {
        this.generatedSetterHelperImpl(cTIgnoredError, IGNOREDERROR$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTIgnoredError insertNewIgnoredError(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTIgnoredError cTIgnoredError = null;
            cTIgnoredError = (CTIgnoredError)((Object)this.get_store().insert_element_user(IGNOREDERROR$0, n));
            return cTIgnoredError;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTIgnoredError addNewIgnoredError() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTIgnoredError cTIgnoredError = null;
            cTIgnoredError = (CTIgnoredError)((Object)this.get_store().add_element_user(IGNOREDERROR$0));
            return cTIgnoredError;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeIgnoredError(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(IGNOREDERROR$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)this.get_store().find_element_user(EXTLST$2, 0);
            if (cTExtensionList == null) {
                return null;
            }
            return cTExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTLST$2) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionList, EXTLST$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)this.get_store().add_element_user(EXTLST$2);
            return cTExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTLST$2, 0);
        }
    }
}

