/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesSlide;

public class CTNotesSlideImpl
extends XmlComplexContentImpl
implements CTNotesSlide {
    private static final long serialVersionUID = 1L;
    private static final QName CSLD$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cSld");
    private static final QName CLRMAPOVR$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "clrMapOvr");
    private static final QName EXTLST$4 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "extLst");
    private static final QName SHOWMASTERSP$6 = new QName("", "showMasterSp");
    private static final QName SHOWMASTERPHANIM$8 = new QName("", "showMasterPhAnim");

    public CTNotesSlideImpl(SchemaType schemaType) {
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
    public CTColorMappingOverride getClrMapOvr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorMappingOverride cTColorMappingOverride = null;
            cTColorMappingOverride = (CTColorMappingOverride)((Object)this.get_store().find_element_user(CLRMAPOVR$2, 0));
            if (cTColorMappingOverride == null) {
                return null;
            }
            return cTColorMappingOverride;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetClrMapOvr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CLRMAPOVR$2) != 0;
        }
    }

    @Override
    public void setClrMapOvr(CTColorMappingOverride cTColorMappingOverride) {
        this.generatedSetterHelperImpl(cTColorMappingOverride, CLRMAPOVR$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorMappingOverride addNewClrMapOvr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorMappingOverride cTColorMappingOverride = null;
            cTColorMappingOverride = (CTColorMappingOverride)((Object)this.get_store().add_element_user(CLRMAPOVR$2));
            return cTColorMappingOverride;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetClrMapOvr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CLRMAPOVR$2, 0);
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
            cTExtensionListModify = (CTExtensionListModify)this.get_store().find_element_user(EXTLST$4, 0);
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
            return this.get_store().count_elements(EXTLST$4) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionListModify cTExtensionListModify) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionListModify, EXTLST$4, 0, (short)1);
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
            cTExtensionListModify = (CTExtensionListModify)this.get_store().add_element_user(EXTLST$4);
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
            this.get_store().remove_element(EXTLST$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getShowMasterSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SHOWMASTERSP$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(SHOWMASTERSP$6));
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
    public XmlBoolean xgetShowMasterSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(SHOWMASTERSP$6));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(SHOWMASTERSP$6);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetShowMasterSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SHOWMASTERSP$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setShowMasterSp(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SHOWMASTERSP$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SHOWMASTERSP$6));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetShowMasterSp(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(SHOWMASTERSP$6));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(SHOWMASTERSP$6));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShowMasterSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SHOWMASTERSP$6);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getShowMasterPhAnim() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SHOWMASTERPHANIM$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(SHOWMASTERPHANIM$8));
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
    public XmlBoolean xgetShowMasterPhAnim() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(SHOWMASTERPHANIM$8));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(SHOWMASTERPHANIM$8);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetShowMasterPhAnim() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SHOWMASTERPHANIM$8) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setShowMasterPhAnim(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SHOWMASTERPHANIM$8));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SHOWMASTERPHANIM$8));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetShowMasterPhAnim(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(SHOWMASTERPHANIM$8));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(SHOWMASTERPHANIM$8));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShowMasterPhAnim() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SHOWMASTERPHANIM$8);
        }
    }
}

