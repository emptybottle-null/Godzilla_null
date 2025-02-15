/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;

public class CTHyperlinkImpl
extends XmlComplexContentImpl
implements CTHyperlink {
    private static final long serialVersionUID = 1L;
    private static final QName REF$0 = new QName("", "ref");
    private static final QName ID$2 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "id");
    private static final QName LOCATION$4 = new QName("", "location");
    private static final QName TOOLTIP$6 = new QName("", "tooltip");
    private static final QName DISPLAY$8 = new QName("", "display");

    public CTHyperlinkImpl(SchemaType schemaType) {
        super(schemaType);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REF$0));
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
            sTRef = (STRef)((Object)this.get_store().find_attribute_user(REF$0));
            return sTRef;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REF$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(REF$0));
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
            sTRef2 = (STRef)((Object)this.get_store().find_attribute_user(REF$0));
            if (sTRef2 == null) {
                sTRef2 = (STRef)((Object)this.get_store().add_attribute_user(REF$0));
            }
            sTRef2.set(sTRef);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$2));
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
            sTRelationshipId = (STRelationshipId)((Object)this.get_store().find_attribute_user(ID$2));
            return sTRelationshipId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ID$2) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$2));
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
            sTRelationshipId2 = (STRelationshipId)((Object)this.get_store().find_attribute_user(ID$2));
            if (sTRelationshipId2 == null) {
                sTRelationshipId2 = (STRelationshipId)((Object)this.get_store().add_attribute_user(ID$2));
            }
            sTRelationshipId2.set(sTRelationshipId);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ID$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getLocation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LOCATION$4));
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
    public STXstring xgetLocation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring = null;
            sTXstring = (STXstring)((Object)this.get_store().find_attribute_user(LOCATION$4));
            return sTXstring;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLocation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(LOCATION$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLocation(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LOCATION$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LOCATION$4));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLocation(STXstring sTXstring) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring2 = null;
            sTXstring2 = (STXstring)((Object)this.get_store().find_attribute_user(LOCATION$4));
            if (sTXstring2 == null) {
                sTXstring2 = (STXstring)((Object)this.get_store().add_attribute_user(LOCATION$4));
            }
            sTXstring2.set(sTXstring);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLocation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(LOCATION$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getTooltip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TOOLTIP$6));
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
    public STXstring xgetTooltip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring = null;
            sTXstring = (STXstring)((Object)this.get_store().find_attribute_user(TOOLTIP$6));
            return sTXstring;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTooltip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TOOLTIP$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTooltip(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TOOLTIP$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TOOLTIP$6));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTooltip(STXstring sTXstring) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring2 = null;
            sTXstring2 = (STXstring)((Object)this.get_store().find_attribute_user(TOOLTIP$6));
            if (sTXstring2 == null) {
                sTXstring2 = (STXstring)((Object)this.get_store().add_attribute_user(TOOLTIP$6));
            }
            sTXstring2.set(sTXstring);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTooltip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TOOLTIP$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getDisplay() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISPLAY$8));
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
    public STXstring xgetDisplay() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring = null;
            sTXstring = (STXstring)((Object)this.get_store().find_attribute_user(DISPLAY$8));
            return sTXstring;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDisplay() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DISPLAY$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDisplay(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISPLAY$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DISPLAY$8));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDisplay(STXstring sTXstring) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STXstring sTXstring2 = null;
            sTXstring2 = (STXstring)((Object)this.get_store().find_attribute_user(DISPLAY$8));
            if (sTXstring2 == null) {
                sTXstring2 = (STXstring)((Object)this.get_store().add_attribute_user(DISPLAY$8));
            }
            sTXstring2.set(sTXstring);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDisplay() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DISPLAY$8);
        }
    }
}

