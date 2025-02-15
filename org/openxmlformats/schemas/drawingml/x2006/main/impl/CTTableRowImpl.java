/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCell;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableRow;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate;

public class CTTableRowImpl
extends XmlComplexContentImpl
implements CTTableRow {
    private static final long serialVersionUID = 1L;
    private static final QName TC$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tc");
    private static final QName EXTLST$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");
    private static final QName H$4 = new QName("", "h");

    public CTTableRowImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTableCell> getTcList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TcList
            extends AbstractList<CTTableCell> {
                TcList() {
                }

                @Override
                public CTTableCell get(int n) {
                    return CTTableRowImpl.this.getTcArray(n);
                }

                @Override
                public CTTableCell set(int n, CTTableCell cTTableCell) {
                    CTTableCell cTTableCell2 = CTTableRowImpl.this.getTcArray(n);
                    CTTableRowImpl.this.setTcArray(n, cTTableCell);
                    return cTTableCell2;
                }

                @Override
                public void add(int n, CTTableCell cTTableCell) {
                    CTTableRowImpl.this.insertNewTc(n).set(cTTableCell);
                }

                @Override
                public CTTableCell remove(int n) {
                    CTTableCell cTTableCell = CTTableRowImpl.this.getTcArray(n);
                    CTTableRowImpl.this.removeTc(n);
                    return cTTableCell;
                }

                @Override
                public int size() {
                    return CTTableRowImpl.this.sizeOfTcArray();
                }
            }
            return new TcList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTableCell[] getTcArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TC$0, arrayList);
            CTTableCell[] cTTableCellArray = new CTTableCell[arrayList.size()];
            arrayList.toArray(cTTableCellArray);
            return cTTableCellArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableCell getTcArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableCell cTTableCell = null;
            cTTableCell = (CTTableCell)((Object)this.get_store().find_element_user(TC$0, n));
            if (cTTableCell == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTableCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTcArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TC$0);
        }
    }

    @Override
    public void setTcArray(CTTableCell[] cTTableCellArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTableCellArray, TC$0);
    }

    @Override
    public void setTcArray(int n, CTTableCell cTTableCell) {
        this.generatedSetterHelperImpl(cTTableCell, TC$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableCell insertNewTc(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableCell cTTableCell = null;
            cTTableCell = (CTTableCell)((Object)this.get_store().insert_element_user(TC$0, n));
            return cTTableCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableCell addNewTc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableCell cTTableCell = null;
            cTTableCell = (CTTableCell)((Object)this.get_store().add_element_user(TC$0));
            return cTTableCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTc(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TC$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$2, 0));
            if (cTOfficeArtExtensionList == null) {
                return null;
            }
            return cTOfficeArtExtensionList;
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
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$2));
            return cTOfficeArtExtensionList;
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(H$4));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCoordinate xgetH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate sTCoordinate = null;
            sTCoordinate = (STCoordinate)((Object)this.get_store().find_attribute_user(H$4));
            return sTCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setH(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(H$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(H$4));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetH(STCoordinate sTCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate sTCoordinate2 = null;
            sTCoordinate2 = (STCoordinate)((Object)this.get_store().find_attribute_user(H$4));
            if (sTCoordinate2 == null) {
                sTCoordinate2 = (STCoordinate)((Object)this.get_store().add_attribute_user(H$4));
            }
            sTCoordinate2.set(sTCoordinate);
        }
    }
}

