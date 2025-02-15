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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets;

public class CTSheetsImpl
extends XmlComplexContentImpl
implements CTSheets {
    private static final long serialVersionUID = 1L;
    private static final QName SHEET$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheet");

    public CTSheetsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSheet> getSheetList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SheetList
            extends AbstractList<CTSheet> {
                SheetList() {
                }

                @Override
                public CTSheet get(int n) {
                    return CTSheetsImpl.this.getSheetArray(n);
                }

                @Override
                public CTSheet set(int n, CTSheet cTSheet) {
                    CTSheet cTSheet2 = CTSheetsImpl.this.getSheetArray(n);
                    CTSheetsImpl.this.setSheetArray(n, cTSheet);
                    return cTSheet2;
                }

                @Override
                public void add(int n, CTSheet cTSheet) {
                    CTSheetsImpl.this.insertNewSheet(n).set(cTSheet);
                }

                @Override
                public CTSheet remove(int n) {
                    CTSheet cTSheet = CTSheetsImpl.this.getSheetArray(n);
                    CTSheetsImpl.this.removeSheet(n);
                    return cTSheet;
                }

                @Override
                public int size() {
                    return CTSheetsImpl.this.sizeOfSheetArray();
                }
            }
            return new SheetList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSheet[] getSheetArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SHEET$0, arrayList);
            CTSheet[] cTSheetArray = new CTSheet[arrayList.size()];
            arrayList.toArray(cTSheetArray);
            return cTSheetArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheet getSheetArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheet cTSheet = null;
            cTSheet = (CTSheet)((Object)this.get_store().find_element_user(SHEET$0, n));
            if (cTSheet == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSheet;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSheetArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHEET$0);
        }
    }

    @Override
    public void setSheetArray(CTSheet[] cTSheetArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSheetArray, SHEET$0);
    }

    @Override
    public void setSheetArray(int n, CTSheet cTSheet) {
        this.generatedSetterHelperImpl(cTSheet, SHEET$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheet insertNewSheet(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheet cTSheet = null;
            cTSheet = (CTSheet)((Object)this.get_store().insert_element_user(SHEET$0, n));
            return cTSheet;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheet addNewSheet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheet cTSheet = null;
            cTSheet = (CTSheet)((Object)this.get_store().add_element_user(SHEET$0));
            return cTSheet;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSheet(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHEET$0, n);
        }
    }
}

