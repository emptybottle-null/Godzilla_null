/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.office.CTRegroupTable
 *  com.microsoft.schemas.office.office.CTRules
 */
package com.microsoft.schemas.office.office.impl;

import com.microsoft.schemas.office.office.CTIdMap;
import com.microsoft.schemas.office.office.CTRegroupTable;
import com.microsoft.schemas.office.office.CTRules;
import com.microsoft.schemas.office.office.CTShapeLayout;
import com.microsoft.schemas.vml.STExt;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTShapeLayoutImpl
extends XmlComplexContentImpl
implements CTShapeLayout {
    private static final long serialVersionUID = 1L;
    private static final QName IDMAP$0 = new QName("urn:schemas-microsoft-com:office:office", "idmap");
    private static final QName REGROUPTABLE$2 = new QName("urn:schemas-microsoft-com:office:office", "regrouptable");
    private static final QName RULES$4 = new QName("urn:schemas-microsoft-com:office:office", "rules");
    private static final QName EXT$6 = new QName("urn:schemas-microsoft-com:vml", "ext");

    public CTShapeLayoutImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTIdMap getIdmap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTIdMap cTIdMap = null;
            cTIdMap = (CTIdMap)((Object)this.get_store().find_element_user(IDMAP$0, 0));
            if (cTIdMap == null) {
                return null;
            }
            return cTIdMap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetIdmap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(IDMAP$0) != 0;
        }
    }

    @Override
    public void setIdmap(CTIdMap cTIdMap) {
        this.generatedSetterHelperImpl(cTIdMap, IDMAP$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTIdMap addNewIdmap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTIdMap cTIdMap = null;
            cTIdMap = (CTIdMap)((Object)this.get_store().add_element_user(IDMAP$0));
            return cTIdMap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetIdmap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(IDMAP$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRegroupTable getRegrouptable() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRegroupTable cTRegroupTable = null;
            cTRegroupTable = (CTRegroupTable)this.get_store().find_element_user(REGROUPTABLE$2, 0);
            if (cTRegroupTable == null) {
                return null;
            }
            return cTRegroupTable;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRegrouptable() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(REGROUPTABLE$2) != 0;
        }
    }

    @Override
    public void setRegrouptable(CTRegroupTable cTRegroupTable) {
        this.generatedSetterHelperImpl((XmlObject)cTRegroupTable, REGROUPTABLE$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRegroupTable addNewRegrouptable() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRegroupTable cTRegroupTable = null;
            cTRegroupTable = (CTRegroupTable)this.get_store().add_element_user(REGROUPTABLE$2);
            return cTRegroupTable;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRegrouptable() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(REGROUPTABLE$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRules getRules() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRules cTRules = null;
            cTRules = (CTRules)this.get_store().find_element_user(RULES$4, 0);
            if (cTRules == null) {
                return null;
            }
            return cTRules;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRules() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RULES$4) != 0;
        }
    }

    @Override
    public void setRules(CTRules cTRules) {
        this.generatedSetterHelperImpl((XmlObject)cTRules, RULES$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRules addNewRules() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRules cTRules = null;
            cTRules = (CTRules)this.get_store().add_element_user(RULES$4);
            return cTRules;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRules() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RULES$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STExt.Enum getExt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EXT$6));
            if (simpleValue == null) {
                return null;
            }
            return (STExt.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STExt xgetExt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STExt sTExt = null;
            sTExt = (STExt)((Object)this.get_store().find_attribute_user(EXT$6));
            return sTExt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(EXT$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setExt(STExt.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EXT$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(EXT$6));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetExt(STExt sTExt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STExt sTExt2 = null;
            sTExt2 = (STExt)((Object)this.get_store().find_attribute_user(EXT$6));
            if (sTExt2 == null) {
                sTExt2 = (STExt)((Object)this.get_store().add_attribute_user(EXT$6));
            }
            sTExt2.set(sTExt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(EXT$6);
        }
    }
}

