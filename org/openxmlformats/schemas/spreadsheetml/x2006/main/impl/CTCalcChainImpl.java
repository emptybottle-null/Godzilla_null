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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcChain;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;

public class CTCalcChainImpl
extends XmlComplexContentImpl
implements CTCalcChain {
    private static final long serialVersionUID = 1L;
    private static final QName C$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "c");
    private static final QName EXTLST$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "extLst");

    public CTCalcChainImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTCalcCell> getCList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CList
            extends AbstractList<CTCalcCell> {
                CList() {
                }

                @Override
                public CTCalcCell get(int n) {
                    return CTCalcChainImpl.this.getCArray(n);
                }

                @Override
                public CTCalcCell set(int n, CTCalcCell cTCalcCell) {
                    CTCalcCell cTCalcCell2 = CTCalcChainImpl.this.getCArray(n);
                    CTCalcChainImpl.this.setCArray(n, cTCalcCell);
                    return cTCalcCell2;
                }

                @Override
                public void add(int n, CTCalcCell cTCalcCell) {
                    CTCalcChainImpl.this.insertNewC(n).set(cTCalcCell);
                }

                @Override
                public CTCalcCell remove(int n) {
                    CTCalcCell cTCalcCell = CTCalcChainImpl.this.getCArray(n);
                    CTCalcChainImpl.this.removeC(n);
                    return cTCalcCell;
                }

                @Override
                public int size() {
                    return CTCalcChainImpl.this.sizeOfCArray();
                }
            }
            return new CList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTCalcCell[] getCArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(C$0, arrayList);
            CTCalcCell[] cTCalcCellArray = new CTCalcCell[arrayList.size()];
            arrayList.toArray(cTCalcCellArray);
            return cTCalcCellArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCalcCell getCArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCalcCell cTCalcCell = null;
            cTCalcCell = (CTCalcCell)((Object)this.get_store().find_element_user(C$0, n));
            if (cTCalcCell == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTCalcCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(C$0);
        }
    }

    @Override
    public void setCArray(CTCalcCell[] cTCalcCellArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTCalcCellArray, C$0);
    }

    @Override
    public void setCArray(int n, CTCalcCell cTCalcCell) {
        this.generatedSetterHelperImpl(cTCalcCell, C$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCalcCell insertNewC(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCalcCell cTCalcCell = null;
            cTCalcCell = (CTCalcCell)((Object)this.get_store().insert_element_user(C$0, n));
            return cTCalcCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCalcCell addNewC() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCalcCell cTCalcCell = null;
            cTCalcCell = (CTCalcCell)((Object)this.get_store().add_element_user(C$0));
            return cTCalcCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeC(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(C$0, n);
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

