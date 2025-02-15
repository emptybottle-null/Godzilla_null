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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmts;

public class CTNumFmtsImpl
extends XmlComplexContentImpl
implements CTNumFmts {
    private static final long serialVersionUID = 1L;
    private static final QName NUMFMT$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "numFmt");
    private static final QName COUNT$2 = new QName("", "count");

    public CTNumFmtsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTNumFmt> getNumFmtList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class NumFmtList
            extends AbstractList<CTNumFmt> {
                NumFmtList() {
                }

                @Override
                public CTNumFmt get(int n) {
                    return CTNumFmtsImpl.this.getNumFmtArray(n);
                }

                @Override
                public CTNumFmt set(int n, CTNumFmt cTNumFmt) {
                    CTNumFmt cTNumFmt2 = CTNumFmtsImpl.this.getNumFmtArray(n);
                    CTNumFmtsImpl.this.setNumFmtArray(n, cTNumFmt);
                    return cTNumFmt2;
                }

                @Override
                public void add(int n, CTNumFmt cTNumFmt) {
                    CTNumFmtsImpl.this.insertNewNumFmt(n).set(cTNumFmt);
                }

                @Override
                public CTNumFmt remove(int n) {
                    CTNumFmt cTNumFmt = CTNumFmtsImpl.this.getNumFmtArray(n);
                    CTNumFmtsImpl.this.removeNumFmt(n);
                    return cTNumFmt;
                }

                @Override
                public int size() {
                    return CTNumFmtsImpl.this.sizeOfNumFmtArray();
                }
            }
            return new NumFmtList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTNumFmt[] getNumFmtArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(NUMFMT$0, arrayList);
            CTNumFmt[] cTNumFmtArray = new CTNumFmt[arrayList.size()];
            arrayList.toArray(cTNumFmtArray);
            return cTNumFmtArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumFmt getNumFmtArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumFmt cTNumFmt = null;
            cTNumFmt = (CTNumFmt)((Object)this.get_store().find_element_user(NUMFMT$0, n));
            if (cTNumFmt == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTNumFmt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfNumFmtArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NUMFMT$0);
        }
    }

    @Override
    public void setNumFmtArray(CTNumFmt[] cTNumFmtArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTNumFmtArray, NUMFMT$0);
    }

    @Override
    public void setNumFmtArray(int n, CTNumFmt cTNumFmt) {
        this.generatedSetterHelperImpl(cTNumFmt, NUMFMT$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumFmt insertNewNumFmt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumFmt cTNumFmt = null;
            cTNumFmt = (CTNumFmt)((Object)this.get_store().insert_element_user(NUMFMT$0, n));
            return cTNumFmt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumFmt addNewNumFmt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumFmt cTNumFmt = null;
            cTNumFmt = (CTNumFmt)((Object)this.get_store().add_element_user(NUMFMT$0));
            return cTNumFmt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeNumFmt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NUMFMT$0, n);
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

