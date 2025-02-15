/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetDataSet
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedNames;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetDataSet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames;

public class CTExternalBookImpl
extends XmlComplexContentImpl
implements CTExternalBook {
    private static final long serialVersionUID = 1L;
    private static final QName SHEETNAMES$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetNames");
    private static final QName DEFINEDNAMES$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "definedNames");
    private static final QName SHEETDATASET$4 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetDataSet");
    private static final QName ID$6 = new QName("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "id");

    public CTExternalBookImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalSheetNames getSheetNames() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalSheetNames cTExternalSheetNames = null;
            cTExternalSheetNames = (CTExternalSheetNames)((Object)this.get_store().find_element_user(SHEETNAMES$0, 0));
            if (cTExternalSheetNames == null) {
                return null;
            }
            return cTExternalSheetNames;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSheetNames() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHEETNAMES$0) != 0;
        }
    }

    @Override
    public void setSheetNames(CTExternalSheetNames cTExternalSheetNames) {
        this.generatedSetterHelperImpl(cTExternalSheetNames, SHEETNAMES$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalSheetNames addNewSheetNames() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalSheetNames cTExternalSheetNames = null;
            cTExternalSheetNames = (CTExternalSheetNames)((Object)this.get_store().add_element_user(SHEETNAMES$0));
            return cTExternalSheetNames;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSheetNames() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHEETNAMES$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalDefinedNames getDefinedNames() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalDefinedNames cTExternalDefinedNames = null;
            cTExternalDefinedNames = (CTExternalDefinedNames)((Object)this.get_store().find_element_user(DEFINEDNAMES$2, 0));
            if (cTExternalDefinedNames == null) {
                return null;
            }
            return cTExternalDefinedNames;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDefinedNames() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DEFINEDNAMES$2) != 0;
        }
    }

    @Override
    public void setDefinedNames(CTExternalDefinedNames cTExternalDefinedNames) {
        this.generatedSetterHelperImpl(cTExternalDefinedNames, DEFINEDNAMES$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalDefinedNames addNewDefinedNames() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalDefinedNames cTExternalDefinedNames = null;
            cTExternalDefinedNames = (CTExternalDefinedNames)((Object)this.get_store().add_element_user(DEFINEDNAMES$2));
            return cTExternalDefinedNames;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDefinedNames() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DEFINEDNAMES$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalSheetDataSet getSheetDataSet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalSheetDataSet cTExternalSheetDataSet = null;
            cTExternalSheetDataSet = (CTExternalSheetDataSet)this.get_store().find_element_user(SHEETDATASET$4, 0);
            if (cTExternalSheetDataSet == null) {
                return null;
            }
            return cTExternalSheetDataSet;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSheetDataSet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHEETDATASET$4) != 0;
        }
    }

    @Override
    public void setSheetDataSet(CTExternalSheetDataSet cTExternalSheetDataSet) {
        this.generatedSetterHelperImpl((XmlObject)cTExternalSheetDataSet, SHEETDATASET$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalSheetDataSet addNewSheetDataSet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalSheetDataSet cTExternalSheetDataSet = null;
            cTExternalSheetDataSet = (CTExternalSheetDataSet)this.get_store().add_element_user(SHEETDATASET$4);
            return cTExternalSheetDataSet;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSheetDataSet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHEETDATASET$4, 0);
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

