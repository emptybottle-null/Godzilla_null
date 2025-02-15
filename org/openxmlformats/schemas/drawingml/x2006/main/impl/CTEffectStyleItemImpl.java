/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D;

public class CTEffectStyleItemImpl
extends XmlComplexContentImpl
implements CTEffectStyleItem {
    private static final long serialVersionUID = 1L;
    private static final QName EFFECTLST$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "effectLst");
    private static final QName EFFECTDAG$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "effectDag");
    private static final QName SCENE3D$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "scene3d");
    private static final QName SP3D$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "sp3d");

    public CTEffectStyleItemImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectList getEffectLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectList cTEffectList = null;
            cTEffectList = (CTEffectList)((Object)this.get_store().find_element_user(EFFECTLST$0, 0));
            if (cTEffectList == null) {
                return null;
            }
            return cTEffectList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEffectLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EFFECTLST$0) != 0;
        }
    }

    @Override
    public void setEffectLst(CTEffectList cTEffectList) {
        this.generatedSetterHelperImpl(cTEffectList, EFFECTLST$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectList addNewEffectLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectList cTEffectList = null;
            cTEffectList = (CTEffectList)((Object)this.get_store().add_element_user(EFFECTLST$0));
            return cTEffectList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEffectLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EFFECTLST$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectContainer getEffectDag() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectContainer cTEffectContainer = null;
            cTEffectContainer = (CTEffectContainer)((Object)this.get_store().find_element_user(EFFECTDAG$2, 0));
            if (cTEffectContainer == null) {
                return null;
            }
            return cTEffectContainer;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEffectDag() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EFFECTDAG$2) != 0;
        }
    }

    @Override
    public void setEffectDag(CTEffectContainer cTEffectContainer) {
        this.generatedSetterHelperImpl(cTEffectContainer, EFFECTDAG$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectContainer addNewEffectDag() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectContainer cTEffectContainer = null;
            cTEffectContainer = (CTEffectContainer)((Object)this.get_store().add_element_user(EFFECTDAG$2));
            return cTEffectContainer;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEffectDag() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EFFECTDAG$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScene3D getScene3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScene3D cTScene3D = null;
            cTScene3D = (CTScene3D)((Object)this.get_store().find_element_user(SCENE3D$4, 0));
            if (cTScene3D == null) {
                return null;
            }
            return cTScene3D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetScene3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCENE3D$4) != 0;
        }
    }

    @Override
    public void setScene3D(CTScene3D cTScene3D) {
        this.generatedSetterHelperImpl(cTScene3D, SCENE3D$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScene3D addNewScene3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScene3D cTScene3D = null;
            cTScene3D = (CTScene3D)((Object)this.get_store().add_element_user(SCENE3D$4));
            return cTScene3D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetScene3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCENE3D$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShape3D getSp3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShape3D cTShape3D = null;
            cTShape3D = (CTShape3D)((Object)this.get_store().find_element_user(SP3D$6, 0));
            if (cTShape3D == null) {
                return null;
            }
            return cTShape3D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSp3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SP3D$6) != 0;
        }
    }

    @Override
    public void setSp3D(CTShape3D cTShape3D) {
        this.generatedSetterHelperImpl(cTShape3D, SP3D$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShape3D addNewSp3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShape3D cTShape3D = null;
            cTShape3D = (CTShape3D)((Object)this.get_store().add_element_user(SP3D$6));
            return cTShape3D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSp3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SP3D$6, 0);
        }
    }
}

