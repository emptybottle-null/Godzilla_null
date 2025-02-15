/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExternalData;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

public class CTExternalDataImpl
extends XmlComplexContentImpl
implements CTExternalData {
    private static final long serialVersionUID = 1L;
    private static final QName AUTOUPDATE$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "autoUpdate");
    private static final QName ID$2 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "id");

    public CTExternalDataImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getAutoUpdate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(AUTOUPDATE$0, 0));
            if (cTBoolean == null) {
                return null;
            }
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAutoUpdate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AUTOUPDATE$0) != 0;
        }
    }

    @Override
    public void setAutoUpdate(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, AUTOUPDATE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewAutoUpdate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(AUTOUPDATE$0));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAutoUpdate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AUTOUPDATE$0, 0);
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
}

