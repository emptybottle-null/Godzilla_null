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
import org.openxmlformats.schemas.drawingml.x2006.main.CTTable;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableGrid;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableRow;

public class CTTableImpl
extends XmlComplexContentImpl
implements CTTable {
    private static final long serialVersionUID = 1L;
    private static final QName TBLPR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tblPr");
    private static final QName TBLGRID$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tblGrid");
    private static final QName TR$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tr");

    public CTTableImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableProperties getTblPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableProperties cTTableProperties = null;
            cTTableProperties = (CTTableProperties)((Object)this.get_store().find_element_user(TBLPR$0, 0));
            if (cTTableProperties == null) {
                return null;
            }
            return cTTableProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTblPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLPR$0) != 0;
        }
    }

    @Override
    public void setTblPr(CTTableProperties cTTableProperties) {
        this.generatedSetterHelperImpl(cTTableProperties, TBLPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableProperties addNewTblPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableProperties cTTableProperties = null;
            cTTableProperties = (CTTableProperties)((Object)this.get_store().add_element_user(TBLPR$0));
            return cTTableProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLPR$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableGrid getTblGrid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableGrid cTTableGrid = null;
            cTTableGrid = (CTTableGrid)((Object)this.get_store().find_element_user(TBLGRID$2, 0));
            if (cTTableGrid == null) {
                return null;
            }
            return cTTableGrid;
        }
    }

    @Override
    public void setTblGrid(CTTableGrid cTTableGrid) {
        this.generatedSetterHelperImpl(cTTableGrid, TBLGRID$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableGrid addNewTblGrid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableGrid cTTableGrid = null;
            cTTableGrid = (CTTableGrid)((Object)this.get_store().add_element_user(TBLGRID$2));
            return cTTableGrid;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTableRow> getTrList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TrList
            extends AbstractList<CTTableRow> {
                TrList() {
                }

                @Override
                public CTTableRow get(int n) {
                    return CTTableImpl.this.getTrArray(n);
                }

                @Override
                public CTTableRow set(int n, CTTableRow cTTableRow) {
                    CTTableRow cTTableRow2 = CTTableImpl.this.getTrArray(n);
                    CTTableImpl.this.setTrArray(n, cTTableRow);
                    return cTTableRow2;
                }

                @Override
                public void add(int n, CTTableRow cTTableRow) {
                    CTTableImpl.this.insertNewTr(n).set(cTTableRow);
                }

                @Override
                public CTTableRow remove(int n) {
                    CTTableRow cTTableRow = CTTableImpl.this.getTrArray(n);
                    CTTableImpl.this.removeTr(n);
                    return cTTableRow;
                }

                @Override
                public int size() {
                    return CTTableImpl.this.sizeOfTrArray();
                }
            }
            return new TrList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTableRow[] getTrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TR$4, arrayList);
            CTTableRow[] cTTableRowArray = new CTTableRow[arrayList.size()];
            arrayList.toArray(cTTableRowArray);
            return cTTableRowArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableRow getTrArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableRow cTTableRow = null;
            cTTableRow = (CTTableRow)((Object)this.get_store().find_element_user(TR$4, n));
            if (cTTableRow == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTableRow;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTrArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TR$4);
        }
    }

    @Override
    public void setTrArray(CTTableRow[] cTTableRowArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTableRowArray, TR$4);
    }

    @Override
    public void setTrArray(int n, CTTableRow cTTableRow) {
        this.generatedSetterHelperImpl(cTTableRow, TR$4, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableRow insertNewTr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableRow cTTableRow = null;
            cTTableRow = (CTTableRow)((Object)this.get_store().insert_element_user(TR$4, n));
            return cTTableRow;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableRow addNewTr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableRow cTTableRow = null;
            cTTableRow = (CTTableRow)((Object)this.get_store().add_element_user(TR$4));
            return cTTableRow;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTr(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TR$4, n);
        }
    }
}

