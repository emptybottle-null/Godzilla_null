/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;

public class CTExternalDefinedNameImpl
extends XmlComplexContentImpl
implements CTExternalDefinedName {
    private static final long serialVersionUID = 1L;
    private static final QName NAME$0 = new QName("", "name");
    private static final QName REFERSTO$2 = new QName("", "refersTo");
    private static final QName SHEETID$4 = new QName("", "sheetId");

    public CTExternalDefinedNameImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$0));
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
    public STXstring xgetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring = null;
            sTXstring = (STXstring)((Object)this.get_store().find_attribute_user(NAME$0));
            return sTXstring;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setName(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$0));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetName(STXstring sTXstring) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring2 = null;
            sTXstring2 = (STXstring)((Object)this.get_store().find_attribute_user(NAME$0));
            if (sTXstring2 == null) {
                sTXstring2 = (STXstring)((Object)this.get_store().add_attribute_user(NAME$0));
            }
            sTXstring2.set(sTXstring);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getRefersTo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REFERSTO$2));
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
    public STXstring xgetRefersTo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring = null;
            sTXstring = (STXstring)((Object)this.get_store().find_attribute_user(REFERSTO$2));
            return sTXstring;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRefersTo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(REFERSTO$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRefersTo(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REFERSTO$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(REFERSTO$2));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRefersTo(STXstring sTXstring) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring2 = null;
            sTXstring2 = (STXstring)((Object)this.get_store().find_attribute_user(REFERSTO$2));
            if (sTXstring2 == null) {
                sTXstring2 = (STXstring)((Object)this.get_store().add_attribute_user(REFERSTO$2));
            }
            sTXstring2.set(sTXstring);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRefersTo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(REFERSTO$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getSheetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SHEETID$4));
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
    public XmlUnsignedInt xgetSheetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(SHEETID$4));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSheetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SHEETID$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSheetId(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SHEETID$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SHEETID$4));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSheetId(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(SHEETID$4));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(SHEETID$4));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSheetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SHEETID$4);
        }
    }
}

