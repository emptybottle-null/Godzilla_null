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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookView;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookViews;

public class CTBookViewsImpl
extends XmlComplexContentImpl
implements CTBookViews {
    private static final long serialVersionUID = 1L;
    private static final QName WORKBOOKVIEW$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "workbookView");

    public CTBookViewsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTBookView> getWorkbookViewList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class WorkbookViewList
            extends AbstractList<CTBookView> {
                WorkbookViewList() {
                }

                @Override
                public CTBookView get(int n) {
                    return CTBookViewsImpl.this.getWorkbookViewArray(n);
                }

                @Override
                public CTBookView set(int n, CTBookView cTBookView) {
                    CTBookView cTBookView2 = CTBookViewsImpl.this.getWorkbookViewArray(n);
                    CTBookViewsImpl.this.setWorkbookViewArray(n, cTBookView);
                    return cTBookView2;
                }

                @Override
                public void add(int n, CTBookView cTBookView) {
                    CTBookViewsImpl.this.insertNewWorkbookView(n).set(cTBookView);
                }

                @Override
                public CTBookView remove(int n) {
                    CTBookView cTBookView = CTBookViewsImpl.this.getWorkbookViewArray(n);
                    CTBookViewsImpl.this.removeWorkbookView(n);
                    return cTBookView;
                }

                @Override
                public int size() {
                    return CTBookViewsImpl.this.sizeOfWorkbookViewArray();
                }
            }
            return new WorkbookViewList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTBookView[] getWorkbookViewArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(WORKBOOKVIEW$0, arrayList);
            CTBookView[] cTBookViewArray = new CTBookView[arrayList.size()];
            arrayList.toArray(cTBookViewArray);
            return cTBookViewArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBookView getWorkbookViewArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBookView cTBookView = null;
            cTBookView = (CTBookView)((Object)this.get_store().find_element_user(WORKBOOKVIEW$0, n));
            if (cTBookView == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTBookView;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfWorkbookViewArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WORKBOOKVIEW$0);
        }
    }

    @Override
    public void setWorkbookViewArray(CTBookView[] cTBookViewArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTBookViewArray, WORKBOOKVIEW$0);
    }

    @Override
    public void setWorkbookViewArray(int n, CTBookView cTBookView) {
        this.generatedSetterHelperImpl(cTBookView, WORKBOOKVIEW$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBookView insertNewWorkbookView(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBookView cTBookView = null;
            cTBookView = (CTBookView)((Object)this.get_store().insert_element_user(WORKBOOKVIEW$0, n));
            return cTBookView;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBookView addNewWorkbookView() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBookView cTBookView = null;
            cTBookView = (CTBookView)((Object)this.get_store().add_element_user(WORKBOOKVIEW$0));
            return cTBookView;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeWorkbookView(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WORKBOOKVIEW$0, n);
        }
    }
}

