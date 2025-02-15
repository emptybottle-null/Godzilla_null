/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayoutIdList
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTransition
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayoutIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTransition;

public class CTSlideMasterImpl
extends XmlComplexContentImpl
implements CTSlideMaster {
    private static final long serialVersionUID = 1L;
    private static final QName CSLD$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cSld");
    private static final QName CLRMAP$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "clrMap");
    private static final QName SLDLAYOUTIDLST$4 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sldLayoutIdLst");
    private static final QName TRANSITION$6 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "transition");
    private static final QName TIMING$8 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "timing");
    private static final QName HF$10 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "hf");
    private static final QName TXSTYLES$12 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "txStyles");
    private static final QName EXTLST$14 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "extLst");
    private static final QName PRESERVE$16 = new QName("", "preserve");

    public CTSlideMasterImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCommonSlideData getCSld() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCommonSlideData cTCommonSlideData = null;
            cTCommonSlideData = (CTCommonSlideData)((Object)this.get_store().find_element_user(CSLD$0, 0));
            if (cTCommonSlideData == null) {
                return null;
            }
            return cTCommonSlideData;
        }
    }

    @Override
    public void setCSld(CTCommonSlideData cTCommonSlideData) {
        this.generatedSetterHelperImpl(cTCommonSlideData, CSLD$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCommonSlideData addNewCSld() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCommonSlideData cTCommonSlideData = null;
            cTCommonSlideData = (CTCommonSlideData)((Object)this.get_store().add_element_user(CSLD$0));
            return cTCommonSlideData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorMapping getClrMap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorMapping cTColorMapping = null;
            cTColorMapping = (CTColorMapping)((Object)this.get_store().find_element_user(CLRMAP$2, 0));
            if (cTColorMapping == null) {
                return null;
            }
            return cTColorMapping;
        }
    }

    @Override
    public void setClrMap(CTColorMapping cTColorMapping) {
        this.generatedSetterHelperImpl(cTColorMapping, CLRMAP$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorMapping addNewClrMap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorMapping cTColorMapping = null;
            cTColorMapping = (CTColorMapping)((Object)this.get_store().add_element_user(CLRMAP$2));
            return cTColorMapping;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideLayoutIdList getSldLayoutIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideLayoutIdList cTSlideLayoutIdList = null;
            cTSlideLayoutIdList = (CTSlideLayoutIdList)this.get_store().find_element_user(SLDLAYOUTIDLST$4, 0);
            if (cTSlideLayoutIdList == null) {
                return null;
            }
            return cTSlideLayoutIdList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSldLayoutIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SLDLAYOUTIDLST$4) != 0;
        }
    }

    @Override
    public void setSldLayoutIdLst(CTSlideLayoutIdList cTSlideLayoutIdList) {
        this.generatedSetterHelperImpl((XmlObject)cTSlideLayoutIdList, SLDLAYOUTIDLST$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideLayoutIdList addNewSldLayoutIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideLayoutIdList cTSlideLayoutIdList = null;
            cTSlideLayoutIdList = (CTSlideLayoutIdList)this.get_store().add_element_user(SLDLAYOUTIDLST$4);
            return cTSlideLayoutIdList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSldLayoutIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SLDLAYOUTIDLST$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideTransition getTransition() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideTransition cTSlideTransition = null;
            cTSlideTransition = (CTSlideTransition)this.get_store().find_element_user(TRANSITION$6, 0);
            if (cTSlideTransition == null) {
                return null;
            }
            return cTSlideTransition;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTransition() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TRANSITION$6) != 0;
        }
    }

    @Override
    public void setTransition(CTSlideTransition cTSlideTransition) {
        this.generatedSetterHelperImpl((XmlObject)cTSlideTransition, TRANSITION$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideTransition addNewTransition() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideTransition cTSlideTransition = null;
            cTSlideTransition = (CTSlideTransition)this.get_store().add_element_user(TRANSITION$6);
            return cTSlideTransition;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTransition() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TRANSITION$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideTiming getTiming() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideTiming cTSlideTiming = null;
            cTSlideTiming = (CTSlideTiming)this.get_store().find_element_user(TIMING$8, 0);
            if (cTSlideTiming == null) {
                return null;
            }
            return cTSlideTiming;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTiming() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TIMING$8) != 0;
        }
    }

    @Override
    public void setTiming(CTSlideTiming cTSlideTiming) {
        this.generatedSetterHelperImpl((XmlObject)cTSlideTiming, TIMING$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideTiming addNewTiming() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideTiming cTSlideTiming = null;
            cTSlideTiming = (CTSlideTiming)this.get_store().add_element_user(TIMING$8);
            return cTSlideTiming;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTiming() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TIMING$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHeaderFooter getHf() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHeaderFooter cTHeaderFooter = null;
            cTHeaderFooter = (CTHeaderFooter)((Object)this.get_store().find_element_user(HF$10, 0));
            if (cTHeaderFooter == null) {
                return null;
            }
            return cTHeaderFooter;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHf() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HF$10) != 0;
        }
    }

    @Override
    public void setHf(CTHeaderFooter cTHeaderFooter) {
        this.generatedSetterHelperImpl(cTHeaderFooter, HF$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHeaderFooter addNewHf() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHeaderFooter cTHeaderFooter = null;
            cTHeaderFooter = (CTHeaderFooter)((Object)this.get_store().add_element_user(HF$10));
            return cTHeaderFooter;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHf() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HF$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideMasterTextStyles getTxStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideMasterTextStyles cTSlideMasterTextStyles = null;
            cTSlideMasterTextStyles = (CTSlideMasterTextStyles)((Object)this.get_store().find_element_user(TXSTYLES$12, 0));
            if (cTSlideMasterTextStyles == null) {
                return null;
            }
            return cTSlideMasterTextStyles;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTxStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TXSTYLES$12) != 0;
        }
    }

    @Override
    public void setTxStyles(CTSlideMasterTextStyles cTSlideMasterTextStyles) {
        this.generatedSetterHelperImpl(cTSlideMasterTextStyles, TXSTYLES$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideMasterTextStyles addNewTxStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideMasterTextStyles cTSlideMasterTextStyles = null;
            cTSlideMasterTextStyles = (CTSlideMasterTextStyles)((Object)this.get_store().add_element_user(TXSTYLES$12));
            return cTSlideMasterTextStyles;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTxStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TXSTYLES$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionListModify getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionListModify cTExtensionListModify = null;
            cTExtensionListModify = (CTExtensionListModify)this.get_store().find_element_user(EXTLST$14, 0);
            if (cTExtensionListModify == null) {
                return null;
            }
            return cTExtensionListModify;
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
            return this.get_store().count_elements(EXTLST$14) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionListModify cTExtensionListModify) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionListModify, EXTLST$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionListModify addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionListModify cTExtensionListModify = null;
            cTExtensionListModify = (CTExtensionListModify)this.get_store().add_element_user(EXTLST$14);
            return cTExtensionListModify;
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
            this.get_store().remove_element(EXTLST$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getPreserve() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PRESERVE$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(PRESERVE$16));
            }
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetPreserve() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(PRESERVE$16));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(PRESERVE$16);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPreserve() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(PRESERVE$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPreserve(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PRESERVE$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(PRESERVE$16));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPreserve(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(PRESERVE$16));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(PRESERVE$16));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPreserve() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(PRESERVE$16);
        }
    }
}

