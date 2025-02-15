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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells;

public class CTSingleXmlCellsImpl
extends XmlComplexContentImpl
implements CTSingleXmlCells {
    private static final long serialVersionUID = 1L;
    private static final QName SINGLEXMLCELL$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "singleXmlCell");

    public CTSingleXmlCellsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSingleXmlCell> getSingleXmlCellList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SingleXmlCellList
            extends AbstractList<CTSingleXmlCell> {
                SingleXmlCellList() {
                }

                @Override
                public CTSingleXmlCell get(int n) {
                    return CTSingleXmlCellsImpl.this.getSingleXmlCellArray(n);
                }

                @Override
                public CTSingleXmlCell set(int n, CTSingleXmlCell cTSingleXmlCell) {
                    CTSingleXmlCell cTSingleXmlCell2 = CTSingleXmlCellsImpl.this.getSingleXmlCellArray(n);
                    CTSingleXmlCellsImpl.this.setSingleXmlCellArray(n, cTSingleXmlCell);
                    return cTSingleXmlCell2;
                }

                @Override
                public void add(int n, CTSingleXmlCell cTSingleXmlCell) {
                    CTSingleXmlCellsImpl.this.insertNewSingleXmlCell(n).set(cTSingleXmlCell);
                }

                @Override
                public CTSingleXmlCell remove(int n) {
                    CTSingleXmlCell cTSingleXmlCell = CTSingleXmlCellsImpl.this.getSingleXmlCellArray(n);
                    CTSingleXmlCellsImpl.this.removeSingleXmlCell(n);
                    return cTSingleXmlCell;
                }

                @Override
                public int size() {
                    return CTSingleXmlCellsImpl.this.sizeOfSingleXmlCellArray();
                }
            }
            return new SingleXmlCellList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSingleXmlCell[] getSingleXmlCellArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SINGLEXMLCELL$0, arrayList);
            CTSingleXmlCell[] cTSingleXmlCellArray = new CTSingleXmlCell[arrayList.size()];
            arrayList.toArray(cTSingleXmlCellArray);
            return cTSingleXmlCellArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSingleXmlCell getSingleXmlCellArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSingleXmlCell cTSingleXmlCell = null;
            cTSingleXmlCell = (CTSingleXmlCell)((Object)this.get_store().find_element_user(SINGLEXMLCELL$0, n));
            if (cTSingleXmlCell == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSingleXmlCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSingleXmlCellArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SINGLEXMLCELL$0);
        }
    }

    @Override
    public void setSingleXmlCellArray(CTSingleXmlCell[] cTSingleXmlCellArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSingleXmlCellArray, SINGLEXMLCELL$0);
    }

    @Override
    public void setSingleXmlCellArray(int n, CTSingleXmlCell cTSingleXmlCell) {
        this.generatedSetterHelperImpl(cTSingleXmlCell, SINGLEXMLCELL$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSingleXmlCell insertNewSingleXmlCell(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSingleXmlCell cTSingleXmlCell = null;
            cTSingleXmlCell = (CTSingleXmlCell)((Object)this.get_store().insert_element_user(SINGLEXMLCELL$0, n));
            return cTSingleXmlCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSingleXmlCell addNewSingleXmlCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSingleXmlCell cTSingleXmlCell = null;
            cTSingleXmlCell = (CTSingleXmlCell)((Object)this.get_store().add_element_user(SINGLEXMLCELL$0));
            return cTSingleXmlCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSingleXmlCell(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SINGLEXMLCELL$0, n);
        }
    }
}

