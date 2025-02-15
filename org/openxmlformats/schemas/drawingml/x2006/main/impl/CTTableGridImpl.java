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
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCol;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableGrid;

public class CTTableGridImpl
extends XmlComplexContentImpl
implements CTTableGrid {
    private static final long serialVersionUID = 1L;
    private static final QName GRIDCOL$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "gridCol");

    public CTTableGridImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTableCol> getGridColList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class GridColList
            extends AbstractList<CTTableCol> {
                GridColList() {
                }

                @Override
                public CTTableCol get(int n) {
                    return CTTableGridImpl.this.getGridColArray(n);
                }

                @Override
                public CTTableCol set(int n, CTTableCol cTTableCol) {
                    CTTableCol cTTableCol2 = CTTableGridImpl.this.getGridColArray(n);
                    CTTableGridImpl.this.setGridColArray(n, cTTableCol);
                    return cTTableCol2;
                }

                @Override
                public void add(int n, CTTableCol cTTableCol) {
                    CTTableGridImpl.this.insertNewGridCol(n).set(cTTableCol);
                }

                @Override
                public CTTableCol remove(int n) {
                    CTTableCol cTTableCol = CTTableGridImpl.this.getGridColArray(n);
                    CTTableGridImpl.this.removeGridCol(n);
                    return cTTableCol;
                }

                @Override
                public int size() {
                    return CTTableGridImpl.this.sizeOfGridColArray();
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
    public CTTableCol[] getGridColArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(GRIDCOL$0, arrayList);
            CTTableCol[] cTTableColArray = new CTTableCol[arrayList.size()];
            arrayList.toArray(cTTableColArray);
            return cTTableColArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableCol getGridColArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableCol cTTableCol = null;
            cTTableCol = (CTTableCol)((Object)this.get_store().find_element_user(GRIDCOL$0, n));
            if (cTTableCol == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTableCol;
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
    public void setGridColArray(CTTableCol[] cTTableColArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTableColArray, GRIDCOL$0);
    }

    @Override
    public void setGridColArray(int n, CTTableCol cTTableCol) {
        this.generatedSetterHelperImpl(cTTableCol, GRIDCOL$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableCol insertNewGridCol(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableCol cTTableCol = null;
            cTTableCol = (CTTableCol)((Object)this.get_store().insert_element_user(GRIDCOL$0, n));
            return cTTableCol;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableCol addNewGridCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableCol cTTableCol = null;
            cTTableCol = (CTTableCol)((Object)this.get_store().add_element_user(GRIDCOL$0));
            return cTTableCol;
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

