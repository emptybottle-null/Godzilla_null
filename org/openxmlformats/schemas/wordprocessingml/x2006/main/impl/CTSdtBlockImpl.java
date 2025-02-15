/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtContentBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtEndPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr;

public class CTSdtBlockImpl
extends XmlComplexContentImpl
implements CTSdtBlock {
    private static final long serialVersionUID = 1L;
    private static final QName SDTPR$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sdtPr");
    private static final QName SDTENDPR$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sdtEndPr");
    private static final QName SDTCONTENT$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sdtContent");

    public CTSdtBlockImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtPr getSdtPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtPr cTSdtPr = null;
            cTSdtPr = (CTSdtPr)((Object)this.get_store().find_element_user(SDTPR$0, 0));
            if (cTSdtPr == null) {
                return null;
            }
            return cTSdtPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSdtPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SDTPR$0) != 0;
        }
    }

    @Override
    public void setSdtPr(CTSdtPr cTSdtPr) {
        this.generatedSetterHelperImpl(cTSdtPr, SDTPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtPr addNewSdtPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtPr cTSdtPr = null;
            cTSdtPr = (CTSdtPr)((Object)this.get_store().add_element_user(SDTPR$0));
            return cTSdtPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSdtPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SDTPR$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtEndPr getSdtEndPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtEndPr cTSdtEndPr = null;
            cTSdtEndPr = (CTSdtEndPr)((Object)this.get_store().find_element_user(SDTENDPR$2, 0));
            if (cTSdtEndPr == null) {
                return null;
            }
            return cTSdtEndPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSdtEndPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SDTENDPR$2) != 0;
        }
    }

    @Override
    public void setSdtEndPr(CTSdtEndPr cTSdtEndPr) {
        this.generatedSetterHelperImpl(cTSdtEndPr, SDTENDPR$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtEndPr addNewSdtEndPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtEndPr cTSdtEndPr = null;
            cTSdtEndPr = (CTSdtEndPr)((Object)this.get_store().add_element_user(SDTENDPR$2));
            return cTSdtEndPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSdtEndPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SDTENDPR$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtContentBlock getSdtContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtContentBlock cTSdtContentBlock = null;
            cTSdtContentBlock = (CTSdtContentBlock)((Object)this.get_store().find_element_user(SDTCONTENT$4, 0));
            if (cTSdtContentBlock == null) {
                return null;
            }
            return cTSdtContentBlock;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSdtContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SDTCONTENT$4) != 0;
        }
    }

    @Override
    public void setSdtContent(CTSdtContentBlock cTSdtContentBlock) {
        this.generatedSetterHelperImpl(cTSdtContentBlock, SDTCONTENT$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSdtContentBlock addNewSdtContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSdtContentBlock cTSdtContentBlock = null;
            cTSdtContentBlock = (CTSdtContentBlock)((Object)this.get_store().add_element_user(SDTCONTENT$4));
            return cTSdtContentBlock;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSdtContent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SDTCONTENT$4, 0);
        }
    }
}

