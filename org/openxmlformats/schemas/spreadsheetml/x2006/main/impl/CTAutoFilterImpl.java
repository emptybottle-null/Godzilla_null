/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilterColumn
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortState
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoFilter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilterColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortState;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef;

public class CTAutoFilterImpl
extends XmlComplexContentImpl
implements CTAutoFilter {
    private static final long serialVersionUID = 1L;
    private static final QName FILTERCOLUMN$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "filterColumn");
    private static final QName SORTSTATE$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sortState");
    private static final QName EXTLST$4 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "extLst");
    private static final QName REF$6 = new QName("", "ref");

    public CTAutoFilterImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTFilterColumn> getFilterColumnList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class FilterColumnList
            extends AbstractList<CTFilterColumn> {
                FilterColumnList() {
                }

                @Override
                public CTFilterColumn get(int n) {
                    return CTAutoFilterImpl.this.getFilterColumnArray(n);
                }

                @Override
                public CTFilterColumn set(int n, CTFilterColumn cTFilterColumn) {
                    CTFilterColumn cTFilterColumn2 = CTAutoFilterImpl.this.getFilterColumnArray(n);
                    CTAutoFilterImpl.this.setFilterColumnArray(n, cTFilterColumn);
                    return cTFilterColumn2;
                }

                @Override
                public void add(int n, CTFilterColumn cTFilterColumn) {
                    CTAutoFilterImpl.this.insertNewFilterColumn(n).set((XmlObject)cTFilterColumn);
                }

                @Override
                public CTFilterColumn remove(int n) {
                    CTFilterColumn cTFilterColumn = CTAutoFilterImpl.this.getFilterColumnArray(n);
                    CTAutoFilterImpl.this.removeFilterColumn(n);
                    return cTFilterColumn;
                }

                @Override
                public int size() {
                    return CTAutoFilterImpl.this.sizeOfFilterColumnArray();
                }
            }
            return new FilterColumnList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTFilterColumn[] getFilterColumnArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(FILTERCOLUMN$0, arrayList);
            CTFilterColumn[] cTFilterColumnArray = new CTFilterColumn[arrayList.size()];
            arrayList.toArray(cTFilterColumnArray);
            return cTFilterColumnArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFilterColumn getFilterColumnArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFilterColumn cTFilterColumn = null;
            cTFilterColumn = (CTFilterColumn)this.get_store().find_element_user(FILTERCOLUMN$0, n);
            if (cTFilterColumn == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTFilterColumn;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfFilterColumnArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FILTERCOLUMN$0);
        }
    }

    @Override
    public void setFilterColumnArray(CTFilterColumn[] cTFilterColumnArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTFilterColumnArray, FILTERCOLUMN$0);
    }

    @Override
    public void setFilterColumnArray(int n, CTFilterColumn cTFilterColumn) {
        this.generatedSetterHelperImpl((XmlObject)cTFilterColumn, FILTERCOLUMN$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFilterColumn insertNewFilterColumn(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFilterColumn cTFilterColumn = null;
            cTFilterColumn = (CTFilterColumn)this.get_store().insert_element_user(FILTERCOLUMN$0, n);
            return cTFilterColumn;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFilterColumn addNewFilterColumn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFilterColumn cTFilterColumn = null;
            cTFilterColumn = (CTFilterColumn)this.get_store().add_element_user(FILTERCOLUMN$0);
            return cTFilterColumn;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeFilterColumn(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FILTERCOLUMN$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSortState getSortState() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSortState cTSortState = null;
            cTSortState = (CTSortState)this.get_store().find_element_user(SORTSTATE$2, 0);
            if (cTSortState == null) {
                return null;
            }
            return cTSortState;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSortState() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SORTSTATE$2) != 0;
        }
    }

    @Override
    public void setSortState(CTSortState cTSortState) {
        this.generatedSetterHelperImpl((XmlObject)cTSortState, SORTSTATE$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSortState addNewSortState() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSortState cTSortState = null;
            cTSortState = (CTSortState)this.get_store().add_element_user(SORTSTATE$2);
            return cTSortState;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSortState() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SORTSTATE$2, 0);
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
            cTExtensionList = (CTExtensionList)this.get_store().find_element_user(EXTLST$4, 0);
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
            return this.get_store().count_elements(EXTLST$4) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionList, EXTLST$4, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)this.get_store().add_element_user(EXTLST$4);
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
            this.get_store().remove_element(EXTLST$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REF$6));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STRef xgetRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRef sTRef = null;
            sTRef = (STRef)((Object)this.get_store().find_attribute_user(REF$6));
            return sTRef;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(REF$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRef(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REF$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(REF$6));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRef(STRef sTRef) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRef sTRef2 = null;
            sTRef2 = (STRef)((Object)this.get_store().find_attribute_user(REF$6));
            if (sTRef2 == null) {
                sTRef2 = (STRef)((Object)this.get_store().add_attribute_user(REF$6));
            }
            sTRef2.set(sTRef);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(REF$6);
        }
    }
}

