/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridCol;

public class CTTblGridBaseImpl
extends XmlComplexContentImpl
implements CTTblGridBase {
    private static final long serialVersionUID = 1L;
    private static final QName GRIDCOL$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "gridCol");

    public CTTblGridBaseImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTblGridCol> getGridColList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GridColList
            extends AbstractList<CTTblGridCol> {
                GridColList() {
                }

                @Override
                public CTTblGridCol get(int n) {
                    return CTTblGridBaseImpl.this.getGridColArray(n);
                }

                @Override
                public CTTblGridCol set(int n, CTTblGridCol cTTblGridCol) {
                    CTTblGridCol cTTblGridCol2 = CTTblGridBaseImpl.this.getGridColArray(n);
                    CTTblGridBaseImpl.this.setGridColArray(n, cTTblGridCol);
                    return cTTblGridCol2;
                }

                @Override
                public void add(int n, CTTblGridCol cTTblGridCol) {
                    CTTblGridBaseImpl.this.insertNewGridCol(n).set(cTTblGridCol);
                }

                @Override
                public CTTblGridCol remove(int n) {
                    CTTblGridCol cTTblGridCol = CTTblGridBaseImpl.this.getGridColArray(n);
                    CTTblGridBaseImpl.this.removeGridCol(n);
                    return cTTblGridCol;
                }

                @Override
                public int size() {
                    return CTTblGridBaseImpl.this.sizeOfGridColArray();
                }
            }
            return new GridColList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTblGridCol[] getGridColArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GRIDCOL$0, arrayList);
            CTTblGridCol[] cTTblGridColArray = new CTTblGridCol[arrayList.size()];
            arrayList.toArray(cTTblGridColArray);
            return cTTblGridColArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblGridCol getGridColArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblGridCol cTTblGridCol = null;
            cTTblGridCol = (CTTblGridCol)((Object)this.get_store().find_element_user(GRIDCOL$0, n));
            if (cTTblGridCol == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTblGridCol;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfGridColArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRIDCOL$0);
        }
    }

    @Override
    public void setGridColArray(CTTblGridCol[] cTTblGridColArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTblGridColArray, GRIDCOL$0);
    }

    @Override
    public void setGridColArray(int n, CTTblGridCol cTTblGridCol) {
        this.generatedSetterHelperImpl(cTTblGridCol, GRIDCOL$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblGridCol insertNewGridCol(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblGridCol cTTblGridCol = null;
            cTTblGridCol = (CTTblGridCol)((Object)this.get_store().insert_element_user(GRIDCOL$0, n));
            return cTTblGridCol;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblGridCol addNewGridCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblGridCol cTTblGridCol = null;
            cTTblGridCol = (CTTblGridCol)((Object)this.get_store().add_element_user(GRIDCOL$0));
            return cTTblGridCol;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeGridCol(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRIDCOL$0, n);
        }
    }
}

