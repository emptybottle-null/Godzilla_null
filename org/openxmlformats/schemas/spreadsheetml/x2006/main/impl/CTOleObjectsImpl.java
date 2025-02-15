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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects;

public class CTOleObjectsImpl
extends XmlComplexContentImpl
implements CTOleObjects {
    private static final long serialVersionUID = 1L;
    private static final QName OLEOBJECT$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "oleObject");

    public CTOleObjectsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTOleObject> getOleObjectList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class OleObjectList
            extends AbstractList<CTOleObject> {
                OleObjectList() {
                }

                @Override
                public CTOleObject get(int n) {
                    return CTOleObjectsImpl.this.getOleObjectArray(n);
                }

                @Override
                public CTOleObject set(int n, CTOleObject cTOleObject) {
                    CTOleObject cTOleObject2 = CTOleObjectsImpl.this.getOleObjectArray(n);
                    CTOleObjectsImpl.this.setOleObjectArray(n, cTOleObject);
                    return cTOleObject2;
                }

                @Override
                public void add(int n, CTOleObject cTOleObject) {
                    CTOleObjectsImpl.this.insertNewOleObject(n).set(cTOleObject);
                }

                @Override
                public CTOleObject remove(int n) {
                    CTOleObject cTOleObject = CTOleObjectsImpl.this.getOleObjectArray(n);
                    CTOleObjectsImpl.this.removeOleObject(n);
                    return cTOleObject;
                }

                @Override
                public int size() {
                    return CTOleObjectsImpl.this.sizeOfOleObjectArray();
                }
            }
            return new OleObjectList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTOleObject[] getOleObjectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(OLEOBJECT$0, arrayList);
            CTOleObject[] cTOleObjectArray = new CTOleObject[arrayList.size()];
            arrayList.toArray(cTOleObjectArray);
            return cTOleObjectArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOleObject getOleObjectArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOleObject cTOleObject = null;
            cTOleObject = (CTOleObject)((Object)this.get_store().find_element_user(OLEOBJECT$0, n));
            if (cTOleObject == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTOleObject;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfOleObjectArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OLEOBJECT$0);
        }
    }

    @Override
    public void setOleObjectArray(CTOleObject[] cTOleObjectArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTOleObjectArray, OLEOBJECT$0);
    }

    @Override
    public void setOleObjectArray(int n, CTOleObject cTOleObject) {
        this.generatedSetterHelperImpl(cTOleObject, OLEOBJECT$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOleObject insertNewOleObject(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOleObject cTOleObject = null;
            cTOleObject = (CTOleObject)((Object)this.get_store().insert_element_user(OLEOBJECT$0, n));
            return cTOleObject;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOleObject addNewOleObject() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOleObject cTOleObject = null;
            cTOleObject = (CTOleObject)((Object)this.get_store().add_element_user(OLEOBJECT$0));
            return cTOleObject;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeOleObject(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OLEOBJECT$0, n);
        }
    }
}

