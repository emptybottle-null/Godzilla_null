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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames;

public class CTExternalSheetNamesImpl
extends XmlComplexContentImpl
implements CTExternalSheetNames {
    private static final long serialVersionUID = 1L;
    private static final QName SHEETNAME$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetName");

    public CTExternalSheetNamesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTExternalSheetName> getSheetNameList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SheetNameList
            extends AbstractList<CTExternalSheetName> {
                SheetNameList() {
                }

                @Override
                public CTExternalSheetName get(int n) {
                    return CTExternalSheetNamesImpl.this.getSheetNameArray(n);
                }

                @Override
                public CTExternalSheetName set(int n, CTExternalSheetName cTExternalSheetName) {
                    CTExternalSheetName cTExternalSheetName2 = CTExternalSheetNamesImpl.this.getSheetNameArray(n);
                    CTExternalSheetNamesImpl.this.setSheetNameArray(n, cTExternalSheetName);
                    return cTExternalSheetName2;
                }

                @Override
                public void add(int n, CTExternalSheetName cTExternalSheetName) {
                    CTExternalSheetNamesImpl.this.insertNewSheetName(n).set(cTExternalSheetName);
                }

                @Override
                public CTExternalSheetName remove(int n) {
                    CTExternalSheetName cTExternalSheetName = CTExternalSheetNamesImpl.this.getSheetNameArray(n);
                    CTExternalSheetNamesImpl.this.removeSheetName(n);
                    return cTExternalSheetName;
                }

                @Override
                public int size() {
                    return CTExternalSheetNamesImpl.this.sizeOfSheetNameArray();
                }
            }
            return new SheetNameList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTExternalSheetName[] getSheetNameArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SHEETNAME$0, arrayList);
            CTExternalSheetName[] cTExternalSheetNameArray = new CTExternalSheetName[arrayList.size()];
            arrayList.toArray(cTExternalSheetNameArray);
            return cTExternalSheetNameArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalSheetName getSheetNameArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalSheetName cTExternalSheetName = null;
            cTExternalSheetName = (CTExternalSheetName)((Object)this.get_store().find_element_user(SHEETNAME$0, n));
            if (cTExternalSheetName == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTExternalSheetName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSheetNameArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHEETNAME$0);
        }
    }

    @Override
    public void setSheetNameArray(CTExternalSheetName[] cTExternalSheetNameArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTExternalSheetNameArray, SHEETNAME$0);
    }

    @Override
    public void setSheetNameArray(int n, CTExternalSheetName cTExternalSheetName) {
        this.generatedSetterHelperImpl(cTExternalSheetName, SHEETNAME$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalSheetName insertNewSheetName(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalSheetName cTExternalSheetName = null;
            cTExternalSheetName = (CTExternalSheetName)((Object)this.get_store().insert_element_user(SHEETNAME$0, n));
            return cTExternalSheetName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalSheetName addNewSheetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalSheetName cTExternalSheetName = null;
            cTExternalSheetName = (CTExternalSheetName)((Object)this.get_store().add_element_user(SHEETNAME$0));
            return cTExternalSheetName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSheetName(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHEETNAME$0, n);
        }
    }
}

