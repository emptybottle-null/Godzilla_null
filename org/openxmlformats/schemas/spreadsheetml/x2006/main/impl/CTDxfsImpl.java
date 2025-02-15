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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxfs;

public class CTDxfsImpl
extends XmlComplexContentImpl
implements CTDxfs {
    private static final long serialVersionUID = 1L;
    private static final QName DXF$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "dxf");
    private static final QName COUNT$2 = new QName("", "count");

    public CTDxfsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTDxf> getDxfList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DxfList
            extends AbstractList<CTDxf> {
                DxfList() {
                }

                @Override
                public CTDxf get(int n) {
                    return CTDxfsImpl.this.getDxfArray(n);
                }

                @Override
                public CTDxf set(int n, CTDxf cTDxf) {
                    CTDxf cTDxf2 = CTDxfsImpl.this.getDxfArray(n);
                    CTDxfsImpl.this.setDxfArray(n, cTDxf);
                    return cTDxf2;
                }

                @Override
                public void add(int n, CTDxf cTDxf) {
                    CTDxfsImpl.this.insertNewDxf(n).set(cTDxf);
                }

                @Override
                public CTDxf remove(int n) {
                    CTDxf cTDxf = CTDxfsImpl.this.getDxfArray(n);
                    CTDxfsImpl.this.removeDxf(n);
                    return cTDxf;
                }

                @Override
                public int size() {
                    return CTDxfsImpl.this.sizeOfDxfArray();
                }
            }
            return new DxfList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTDxf[] getDxfArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DXF$0, arrayList);
            CTDxf[] cTDxfArray = new CTDxf[arrayList.size()];
            arrayList.toArray(cTDxfArray);
            return cTDxfArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDxf getDxfArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDxf cTDxf = null;
            cTDxf = (CTDxf)((Object)this.get_store().find_element_user(DXF$0, n));
            if (cTDxf == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTDxf;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDxfArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DXF$0);
        }
    }

    @Override
    public void setDxfArray(CTDxf[] cTDxfArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTDxfArray, DXF$0);
    }

    @Override
    public void setDxfArray(int n, CTDxf cTDxf) {
        this.generatedSetterHelperImpl(cTDxf, DXF$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDxf insertNewDxf(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDxf cTDxf = null;
            cTDxf = (CTDxf)((Object)this.get_store().insert_element_user(DXF$0, n));
            return cTDxf;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDxf addNewDxf() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDxf cTDxf = null;
            cTDxf = (CTDxf)((Object)this.get_store().add_element_user(DXF$0));
            return cTDxf;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDxf(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DXF$0, n);
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

