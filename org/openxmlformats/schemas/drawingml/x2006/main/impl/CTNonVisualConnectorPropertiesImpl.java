/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTConnectorLocking
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnection;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectorLocking;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;

public class CTNonVisualConnectorPropertiesImpl
extends XmlComplexContentImpl
implements CTNonVisualConnectorProperties {
    private static final long serialVersionUID = 1L;
    private static final QName CXNSPLOCKS$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "cxnSpLocks");
    private static final QName STCXN$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "stCxn");
    private static final QName ENDCXN$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "endCxn");
    private static final QName EXTLST$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");

    public CTNonVisualConnectorPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnectorLocking getCxnSpLocks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnectorLocking cTConnectorLocking = null;
            cTConnectorLocking = (CTConnectorLocking)this.get_store().find_element_user(CXNSPLOCKS$0, 0);
            if (cTConnectorLocking == null) {
                return null;
            }
            return cTConnectorLocking;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCxnSpLocks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CXNSPLOCKS$0) != 0;
        }
    }

    @Override
    public void setCxnSpLocks(CTConnectorLocking cTConnectorLocking) {
        this.generatedSetterHelperImpl((XmlObject)cTConnectorLocking, CXNSPLOCKS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnectorLocking addNewCxnSpLocks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnectorLocking cTConnectorLocking = null;
            cTConnectorLocking = (CTConnectorLocking)this.get_store().add_element_user(CXNSPLOCKS$0);
            return cTConnectorLocking;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCxnSpLocks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CXNSPLOCKS$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnection getStCxn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnection cTConnection = null;
            cTConnection = (CTConnection)((Object)this.get_store().find_element_user(STCXN$2, 0));
            if (cTConnection == null) {
                return null;
            }
            return cTConnection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStCxn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STCXN$2) != 0;
        }
    }

    @Override
    public void setStCxn(CTConnection cTConnection) {
        this.generatedSetterHelperImpl(cTConnection, STCXN$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnection addNewStCxn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnection cTConnection = null;
            cTConnection = (CTConnection)((Object)this.get_store().add_element_user(STCXN$2));
            return cTConnection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStCxn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STCXN$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnection getEndCxn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnection cTConnection = null;
            cTConnection = (CTConnection)((Object)this.get_store().find_element_user(ENDCXN$4, 0));
            if (cTConnection == null) {
                return null;
            }
            return cTConnection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEndCxn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ENDCXN$4) != 0;
        }
    }

    @Override
    public void setEndCxn(CTConnection cTConnection) {
        this.generatedSetterHelperImpl(cTConnection, ENDCXN$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnection addNewEndCxn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnection cTConnection = null;
            cTConnection = (CTConnection)((Object)this.get_store().add_element_user(ENDCXN$4));
            return cTConnection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEndCxn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ENDCXN$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$6, 0));
            if (cTOfficeArtExtensionList == null) {
                return null;
            }
            return cTOfficeArtExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTLST$6) != 0;
        }
    }

    @Override
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$6));
            return cTOfficeArtExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTLST$6, 0);
        }
    }
}

