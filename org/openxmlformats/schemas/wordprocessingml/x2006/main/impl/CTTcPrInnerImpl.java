/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCellMergeTrackChange
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCellMergeTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTcPrBaseImpl;

public class CTTcPrInnerImpl
extends CTTcPrBaseImpl
implements CTTcPrInner {
    private static final long serialVersionUID = 1L;
    private static final QName CELLINS$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cellIns");
    private static final QName CELLDEL$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cellDel");
    private static final QName CELLMERGE$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cellMerge");

    public CTTcPrInnerImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChange getCellIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChange cTTrackChange = null;
            cTTrackChange = (CTTrackChange)((Object)this.get_store().find_element_user(CELLINS$0, 0));
            if (cTTrackChange == null) {
                return null;
            }
            return cTTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCellIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CELLINS$0) != 0;
        }
    }

    @Override
    public void setCellIns(CTTrackChange cTTrackChange) {
        this.generatedSetterHelperImpl(cTTrackChange, CELLINS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChange addNewCellIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChange cTTrackChange = null;
            cTTrackChange = (CTTrackChange)((Object)this.get_store().add_element_user(CELLINS$0));
            return cTTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCellIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CELLINS$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChange getCellDel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChange cTTrackChange = null;
            cTTrackChange = (CTTrackChange)((Object)this.get_store().find_element_user(CELLDEL$2, 0));
            if (cTTrackChange == null) {
                return null;
            }
            return cTTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCellDel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CELLDEL$2) != 0;
        }
    }

    @Override
    public void setCellDel(CTTrackChange cTTrackChange) {
        this.generatedSetterHelperImpl(cTTrackChange, CELLDEL$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTrackChange addNewCellDel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTrackChange cTTrackChange = null;
            cTTrackChange = (CTTrackChange)((Object)this.get_store().add_element_user(CELLDEL$2));
            return cTTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCellDel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CELLDEL$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCellMergeTrackChange getCellMerge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCellMergeTrackChange cTCellMergeTrackChange = null;
            cTCellMergeTrackChange = (CTCellMergeTrackChange)this.get_store().find_element_user(CELLMERGE$4, 0);
            if (cTCellMergeTrackChange == null) {
                return null;
            }
            return cTCellMergeTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCellMerge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CELLMERGE$4) != 0;
        }
    }

    @Override
    public void setCellMerge(CTCellMergeTrackChange cTCellMergeTrackChange) {
        this.generatedSetterHelperImpl((XmlObject)cTCellMergeTrackChange, CELLMERGE$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCellMergeTrackChange addNewCellMerge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCellMergeTrackChange cTCellMergeTrackChange = null;
            cTCellMergeTrackChange = (CTCellMergeTrackChange)this.get_store().add_element_user(CELLMERGE$4);
            return cTCellMergeTrackChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCellMerge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CELLMERGE$4, 0);
        }
    }
}

