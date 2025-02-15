/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumns;

public class CTTableColumnsImpl
extends XmlComplexContentImpl
implements CTTableColumns {
    private static final long serialVersionUID = 1L;
    private static final QName TABLECOLUMN$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "tableColumn");
    private static final QName COUNT$2 = new QName("", "count");

    public CTTableColumnsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTableColumn> getTableColumnList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TableColumnList
            extends AbstractList<CTTableColumn> {
                TableColumnList() {
                }

                @Override
                public CTTableColumn get(int n) {
                    return CTTableColumnsImpl.this.getTableColumnArray(n);
                }

                @Override
                public CTTableColumn set(int n, CTTableColumn cTTableColumn) {
                    CTTableColumn cTTableColumn2 = CTTableColumnsImpl.this.getTableColumnArray(n);
                    CTTableColumnsImpl.this.setTableColumnArray(n, cTTableColumn);
                    return cTTableColumn2;
                }

                @Override
                public void add(int n, CTTableColumn cTTableColumn) {
                    CTTableColumnsImpl.this.insertNewTableColumn(n).set(cTTableColumn);
                }

                @Override
                public CTTableColumn remove(int n) {
                    CTTableColumn cTTableColumn = CTTableColumnsImpl.this.getTableColumnArray(n);
                    CTTableColumnsImpl.this.removeTableColumn(n);
                    return cTTableColumn;
                }

                @Override
                public int size() {
                    return CTTableColumnsImpl.this.sizeOfTableColumnArray();
                }
            }
            return new TableColumnList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTableColumn[] getTableColumnArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TABLECOLUMN$0, arrayList);
            CTTableColumn[] cTTableColumnArray = new CTTableColumn[arrayList.size()];
            arrayList.toArray(cTTableColumnArray);
            return cTTableColumnArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableColumn getTableColumnArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableColumn cTTableColumn = null;
            cTTableColumn = (CTTableColumn)((Object)this.get_store().find_element_user(TABLECOLUMN$0, n));
            if (cTTableColumn == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTableColumn;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTableColumnArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TABLECOLUMN$0);
        }
    }

    @Override
    public void setTableColumnArray(CTTableColumn[] cTTableColumnArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTableColumnArray, TABLECOLUMN$0);
    }

    @Override
    public void setTableColumnArray(int n, CTTableColumn cTTableColumn) {
        this.generatedSetterHelperImpl(cTTableColumn, TABLECOLUMN$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableColumn insertNewTableColumn(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableColumn cTTableColumn = null;
            cTTableColumn = (CTTableColumn)((Object)this.get_store().insert_element_user(TABLECOLUMN$0, n));
            return cTTableColumn;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableColumn addNewTableColumn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableColumn cTTableColumn = null;
            cTTableColumn = (CTTableColumn)((Object)this.get_store().add_element_user(TABLECOLUMN$0));
            return cTTableColumn;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTableColumn(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TABLECOLUMN$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COUNT$2));
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
    public XmlUnsignedInt xgetCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(COUNT$2));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(COUNT$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCount(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COUNT$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(COUNT$2));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCount(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(COUNT$2));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(COUNT$2));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(COUNT$2);
        }
    }
}

