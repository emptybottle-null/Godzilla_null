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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetView;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetViews;

public class CTSheetViewsImpl
extends XmlComplexContentImpl
implements CTSheetViews {
    private static final long serialVersionUID = 1L;
    private static final QName SHEETVIEW$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetView");
    private static final QName EXTLST$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "extLst");

    public CTSheetViewsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSheetView> getSheetViewList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SheetViewList
            extends AbstractList<CTSheetView> {
                SheetViewList() {
                }

                @Override
                public CTSheetView get(int n) {
                    return CTSheetViewsImpl.this.getSheetViewArray(n);
                }

                @Override
                public CTSheetView set(int n, CTSheetView cTSheetView) {
                    CTSheetView cTSheetView2 = CTSheetViewsImpl.this.getSheetViewArray(n);
                    CTSheetViewsImpl.this.setSheetViewArray(n, cTSheetView);
                    return cTSheetView2;
                }

                @Override
                public void add(int n, CTSheetView cTSheetView) {
                    CTSheetViewsImpl.this.insertNewSheetView(n).set(cTSheetView);
                }

                @Override
                public CTSheetView remove(int n) {
                    CTSheetView cTSheetView = CTSheetViewsImpl.this.getSheetViewArray(n);
                    CTSheetViewsImpl.this.removeSheetView(n);
                    return cTSheetView;
                }

                @Override
                public int size() {
                    return CTSheetViewsImpl.this.sizeOfSheetViewArray();
                }
            }
            return new SheetViewList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSheetView[] getSheetViewArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SHEETVIEW$0, arrayList);
            CTSheetView[] cTSheetViewArray = new CTSheetView[arrayList.size()];
            arrayList.toArray(cTSheetViewArray);
            return cTSheetViewArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetView getSheetViewArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetView cTSheetView = null;
            cTSheetView = (CTSheetView)((Object)this.get_store().find_element_user(SHEETVIEW$0, n));
            if (cTSheetView == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSheetView;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSheetViewArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHEETVIEW$0);
        }
    }

    @Override
    public void setSheetViewArray(CTSheetView[] cTSheetViewArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSheetViewArray, SHEETVIEW$0);
    }

    @Override
    public void setSheetViewArray(int n, CTSheetView cTSheetView) {
        this.generatedSetterHelperImpl(cTSheetView, SHEETVIEW$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetView insertNewSheetView(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetView cTSheetView = null;
            cTSheetView = (CTSheetView)((Object)this.get_store().insert_element_user(SHEETVIEW$0, n));
            return cTSheetView;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetView addNewSheetView() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetView cTSheetView = null;
            cTSheetView = (CTSheetView)((Object)this.get_store().add_element_user(SHEETVIEW$0));
            return cTSheetView;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSheetView(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHEETVIEW$0, n);
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

