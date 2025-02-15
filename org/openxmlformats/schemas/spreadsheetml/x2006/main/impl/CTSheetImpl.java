/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSheetState;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;

public class CTSheetImpl
extends XmlComplexContentImpl
implements CTSheet {
    private static final long serialVersionUID = 1L;
    private static final QName NAME$0 = new QName("", "name");
    private static final QName SHEETID$2 = new QName("", "sheetId");
    private static final QName STATE$4 = new QName("", "state");
    private static final QName ID$6 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "id");

    public CTSheetImpl(SchemaType schemaType) {
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
    public long getSheetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SHEETID$2));
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
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(SHEETID$2));
            return xmlUnsignedInt;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SHEETID$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SHEETID$2));
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
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(SHEETID$2));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(SHEETID$2));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STSheetState.Enum getState() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STATE$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(STATE$4));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STSheetState.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STSheetState xgetState() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSheetState sTSheetState = null;
            sTSheetState = (STSheetState)((Object)this.get_store().find_attribute_user(STATE$4));
            if (sTSheetState == null) {
                sTSheetState = (STSheetState)this.get_default_attribute_value(STATE$4);
            }
            return sTSheetState;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetState() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(STATE$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setState(STSheetState.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STATE$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STATE$4));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetState(STSheetState sTSheetState) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSheetState sTSheetState2 = null;
            sTSheetState2 = (STSheetState)((Object)this.get_store().find_attribute_user(STATE$4));
            if (sTSheetState2 == null) {
                sTSheetState2 = (STSheetState)((Object)this.get_store().add_attribute_user(STATE$4));
            }
            sTSheetState2.set(sTSheetState);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetState() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(STATE$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$6));
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
    public STRelationshipId xgetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRelationshipId sTRelationshipId = null;
            sTRelationshipId = (STRelationshipId)((Object)this.get_store().find_attribute_user(ID$6));
            return sTRelationshipId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setId(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$6));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetId(STRelationshipId sTRelationshipId) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STRelationshipId sTRelationshipId2 = null;
            sTRelationshipId2 = (STRelationshipId)((Object)this.get_store().find_attribute_user(ID$6));
            if (sTRelationshipId2 == null) {
                sTRelationshipId2 = (STRelationshipId)((Object)this.get_store().add_attribute_user(ID$6));
            }
            sTRelationshipId2.set(sTRelationshipId);
        }
    }
}

