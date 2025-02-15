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
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.STGuid;

public class CTTableStyleListImpl
extends XmlComplexContentImpl
implements CTTableStyleList {
    private static final long serialVersionUID = 1L;
    private static final QName TBLSTYLE$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tblStyle");
    private static final QName DEF$2 = new QName("", "def");

    public CTTableStyleListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTableStyle> getTblStyleList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TblStyleList
            extends AbstractList<CTTableStyle> {
                TblStyleList() {
                }

                @Override
                public CTTableStyle get(int n) {
                    return CTTableStyleListImpl.this.getTblStyleArray(n);
                }

                @Override
                public CTTableStyle set(int n, CTTableStyle cTTableStyle) {
                    CTTableStyle cTTableStyle2 = CTTableStyleListImpl.this.getTblStyleArray(n);
                    CTTableStyleListImpl.this.setTblStyleArray(n, cTTableStyle);
                    return cTTableStyle2;
                }

                @Override
                public void add(int n, CTTableStyle cTTableStyle) {
                    CTTableStyleListImpl.this.insertNewTblStyle(n).set(cTTableStyle);
                }

                @Override
                public CTTableStyle remove(int n) {
                    CTTableStyle cTTableStyle = CTTableStyleListImpl.this.getTblStyleArray(n);
                    CTTableStyleListImpl.this.removeTblStyle(n);
                    return cTTableStyle;
                }

                @Override
                public int size() {
                    return CTTableStyleListImpl.this.sizeOfTblStyleArray();
                }
            }
            return new TblStyleList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTableStyle[] getTblStyleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TBLSTYLE$0, arrayList);
            CTTableStyle[] cTTableStyleArray = new CTTableStyle[arrayList.size()];
            arrayList.toArray(cTTableStyleArray);
            return cTTableStyleArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableStyle getTblStyleArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableStyle cTTableStyle = null;
            cTTableStyle = (CTTableStyle)((Object)this.get_store().find_element_user(TBLSTYLE$0, n));
            if (cTTableStyle == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTableStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTblStyleArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLSTYLE$0);
        }
    }

    @Override
    public void setTblStyleArray(CTTableStyle[] cTTableStyleArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTableStyleArray, TBLSTYLE$0);
    }

    @Override
    public void setTblStyleArray(int n, CTTableStyle cTTableStyle) {
        this.generatedSetterHelperImpl(cTTableStyle, TBLSTYLE$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableStyle insertNewTblStyle(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableStyle cTTableStyle = null;
            cTTableStyle = (CTTableStyle)((Object)this.get_store().insert_element_user(TBLSTYLE$0, n));
            return cTTableStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableStyle addNewTblStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableStyle cTTableStyle = null;
            cTTableStyle = (CTTableStyle)((Object)this.get_store().add_element_user(TBLSTYLE$0));
            return cTTableStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTblStyle(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLSTYLE$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getDef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DEF$2));
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
    public STGuid xgetDef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGuid sTGuid = null;
            sTGuid = (STGuid)((Object)this.get_store().find_attribute_user(DEF$2));
            return sTGuid;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDef(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DEF$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DEF$2));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDef(STGuid sTGuid) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGuid sTGuid2 = null;
            sTGuid2 = (STGuid)((Object)this.get_store().find_attribute_user(DEF$2));
            if (sTGuid2 == null) {
                sTGuid2 = (STGuid)((Object)this.get_store().add_attribute_user(DEF$2));
            }
            sTGuid2.set(sTGuid);
        }
    }
}

