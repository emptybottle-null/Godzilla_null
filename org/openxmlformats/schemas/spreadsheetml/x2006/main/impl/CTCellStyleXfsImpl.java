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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyleXfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXf;

public class CTCellStyleXfsImpl
extends XmlComplexContentImpl
implements CTCellStyleXfs {
    private static final long serialVersionUID = 1L;
    private static final QName XF$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "xf");
    private static final QName COUNT$2 = new QName("", "count");

    public CTCellStyleXfsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTXf> getXfList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class XfList
            extends AbstractList<CTXf> {
                XfList() {
                }

                @Override
                public CTXf get(int n) {
                    return CTCellStyleXfsImpl.this.getXfArray(n);
                }

                @Override
                public CTXf set(int n, CTXf cTXf) {
                    CTXf cTXf2 = CTCellStyleXfsImpl.this.getXfArray(n);
                    CTCellStyleXfsImpl.this.setXfArray(n, cTXf);
                    return cTXf2;
                }

                @Override
                public void add(int n, CTXf cTXf) {
                    CTCellStyleXfsImpl.this.insertNewXf(n).set(cTXf);
                }

                @Override
                public CTXf remove(int n) {
                    CTXf cTXf = CTCellStyleXfsImpl.this.getXfArray(n);
                    CTCellStyleXfsImpl.this.removeXf(n);
                    return cTXf;
                }

                @Override
                public int size() {
                    return CTCellStyleXfsImpl.this.sizeOfXfArray();
                }
            }
            return new XfList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTXf[] getXfArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(XF$0, arrayList);
            CTXf[] cTXfArray = new CTXf[arrayList.size()];
            arrayList.toArray(cTXfArray);
            return cTXfArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTXf getXfArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTXf cTXf = null;
            cTXf = (CTXf)((Object)this.get_store().find_element_user(XF$0, n));
            if (cTXf == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTXf;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfXfArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(XF$0);
        }
    }

    @Override
    public void setXfArray(CTXf[] cTXfArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTXfArray, XF$0);
    }

    @Override
    public void setXfArray(int n, CTXf cTXf) {
        this.generatedSetterHelperImpl(cTXf, XF$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTXf insertNewXf(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTXf cTXf = null;
            cTXf = (CTXf)((Object)this.get_store().insert_element_user(XF$0, n));
            return cTXf;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTXf addNewXf() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTXf cTXf = null;
            cTXf = (CTXf)((Object)this.get_store().add_element_user(XF$0));
            return cTXf;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeXf(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(XF$0, n);
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

