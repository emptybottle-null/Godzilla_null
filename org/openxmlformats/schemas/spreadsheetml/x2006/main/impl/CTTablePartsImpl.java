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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTablePart;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableParts;

public class CTTablePartsImpl
extends XmlComplexContentImpl
implements CTTableParts {
    private static final long serialVersionUID = 1L;
    private static final QName TABLEPART$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "tablePart");
    private static final QName COUNT$2 = new QName("", "count");

    public CTTablePartsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTablePart> getTablePartList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TablePartList
            extends AbstractList<CTTablePart> {
                TablePartList() {
                }

                @Override
                public CTTablePart get(int n) {
                    return CTTablePartsImpl.this.getTablePartArray(n);
                }

                @Override
                public CTTablePart set(int n, CTTablePart cTTablePart) {
                    CTTablePart cTTablePart2 = CTTablePartsImpl.this.getTablePartArray(n);
                    CTTablePartsImpl.this.setTablePartArray(n, cTTablePart);
                    return cTTablePart2;
                }

                @Override
                public void add(int n, CTTablePart cTTablePart) {
                    CTTablePartsImpl.this.insertNewTablePart(n).set(cTTablePart);
                }

                @Override
                public CTTablePart remove(int n) {
                    CTTablePart cTTablePart = CTTablePartsImpl.this.getTablePartArray(n);
                    CTTablePartsImpl.this.removeTablePart(n);
                    return cTTablePart;
                }

                @Override
                public int size() {
                    return CTTablePartsImpl.this.sizeOfTablePartArray();
                }
            }
            return new TablePartList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTablePart[] getTablePartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TABLEPART$0, arrayList);
            CTTablePart[] cTTablePartArray = new CTTablePart[arrayList.size()];
            arrayList.toArray(cTTablePartArray);
            return cTTablePartArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePart getTablePartArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePart cTTablePart = null;
            cTTablePart = (CTTablePart)((Object)this.get_store().find_element_user(TABLEPART$0, n));
            if (cTTablePart == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTablePart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTablePartArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TABLEPART$0);
        }
    }

    @Override
    public void setTablePartArray(CTTablePart[] cTTablePartArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTablePartArray, TABLEPART$0);
    }

    @Override
    public void setTablePartArray(int n, CTTablePart cTTablePart) {
        this.generatedSetterHelperImpl(cTTablePart, TABLEPART$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePart insertNewTablePart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePart cTTablePart = null;
            cTTablePart = (CTTablePart)((Object)this.get_store().insert_element_user(TABLEPART$0, n));
            return cTTablePart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTablePart addNewTablePart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTablePart cTTablePart = null;
            cTTablePart = (CTTablePart)((Object)this.get_store().add_element_user(TABLEPART$0));
            return cTTablePart;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTablePart(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TABLEPART$0, n);
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

