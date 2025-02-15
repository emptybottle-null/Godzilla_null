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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCol;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCols;

public class CTColsImpl
extends XmlComplexContentImpl
implements CTCols {
    private static final long serialVersionUID = 1L;
    private static final QName COL$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "col");

    public CTColsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTCol> getColList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ColList
            extends AbstractList<CTCol> {
                ColList() {
                }

                @Override
                public CTCol get(int n) {
                    return CTColsImpl.this.getColArray(n);
                }

                @Override
                public CTCol set(int n, CTCol cTCol) {
                    CTCol cTCol2 = CTColsImpl.this.getColArray(n);
                    CTColsImpl.this.setColArray(n, cTCol);
                    return cTCol2;
                }

                @Override
                public void add(int n, CTCol cTCol) {
                    CTColsImpl.this.insertNewCol(n).set(cTCol);
                }

                @Override
                public CTCol remove(int n) {
                    CTCol cTCol = CTColsImpl.this.getColArray(n);
                    CTColsImpl.this.removeCol(n);
                    return cTCol;
                }

                @Override
                public int size() {
                    return CTColsImpl.this.sizeOfColArray();
                }
            }
            return new ColList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTCol[] getColArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COL$0, arrayList);
            CTCol[] cTColArray = new CTCol[arrayList.size()];
            arrayList.toArray(cTColArray);
            return cTColArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCol getColArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCol cTCol = null;
            cTCol = (CTCol)((Object)this.get_store().find_element_user(COL$0, n));
            if (cTCol == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTCol;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfColArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COL$0);
        }
    }

    @Override
    public void setColArray(CTCol[] cTColArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTColArray, COL$0);
    }

    @Override
    public void setColArray(int n, CTCol cTCol) {
        this.generatedSetterHelperImpl(cTCol, COL$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCol insertNewCol(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCol cTCol = null;
            cTCol = (CTCol)((Object)this.get_store().insert_element_user(COL$0, n));
            return cTCol;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCol addNewCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCol cTCol = null;
            cTCol = (CTCol)((Object)this.get_store().add_element_user(COL$0));
            return cTCol;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCol(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COL$0, n);
        }
    }
}

