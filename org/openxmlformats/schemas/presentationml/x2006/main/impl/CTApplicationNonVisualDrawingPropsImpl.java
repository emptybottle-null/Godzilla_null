/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAudioCD
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTAudioFile
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTEmbeddedWAVAudioFile
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTQuickTimeFile
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTVideoFile
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAudioCD;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAudioFile;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEmbeddedWAVAudioFile;
import org.openxmlformats.schemas.drawingml.x2006.main.CTQuickTimeFile;
import org.openxmlformats.schemas.drawingml.x2006.main.CTVideoFile;
import org.openxmlformats.schemas.presentationml.x2006.main.CTApplicationNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCustomerDataList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPlaceholder;

public class CTApplicationNonVisualDrawingPropsImpl
extends XmlComplexContentImpl
implements CTApplicationNonVisualDrawingProps {
    private static final long serialVersionUID = 1L;
    private static final QName PH$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "ph");
    private static final QName AUDIOCD$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "audioCd");
    private static final QName WAVAUDIOFILE$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "wavAudioFile");
    private static final QName AUDIOFILE$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "audioFile");
    private static final QName VIDEOFILE$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "videoFile");
    private static final QName QUICKTIMEFILE$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "quickTimeFile");
    private static final QName CUSTDATALST$12 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "custDataLst");
    private static final QName EXTLST$14 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "extLst");
    private static final QName ISPHOTO$16 = new QName("", "isPhoto");
    private static final QName USERDRAWN$18 = new QName("", "userDrawn");

    public CTApplicationNonVisualDrawingPropsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPlaceholder getPh() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPlaceholder cTPlaceholder = null;
            cTPlaceholder = (CTPlaceholder)((Object)this.get_store().find_element_user(PH$0, 0));
            if (cTPlaceholder == null) {
                return null;
            }
            return cTPlaceholder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPh() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PH$0) != 0;
        }
    }

    @Override
    public void setPh(CTPlaceholder cTPlaceholder) {
        this.generatedSetterHelperImpl(cTPlaceholder, PH$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPlaceholder addNewPh() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPlaceholder cTPlaceholder = null;
            cTPlaceholder = (CTPlaceholder)((Object)this.get_store().add_element_user(PH$0));
            return cTPlaceholder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPh() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PH$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAudioCD getAudioCd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAudioCD cTAudioCD = null;
            cTAudioCD = (CTAudioCD)this.get_store().find_element_user(AUDIOCD$2, 0);
            if (cTAudioCD == null) {
                return null;
            }
            return cTAudioCD;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAudioCd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AUDIOCD$2) != 0;
        }
    }

    @Override
    public void setAudioCd(CTAudioCD cTAudioCD) {
        this.generatedSetterHelperImpl((XmlObject)cTAudioCD, AUDIOCD$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAudioCD addNewAudioCd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAudioCD cTAudioCD = null;
            cTAudioCD = (CTAudioCD)this.get_store().add_element_user(AUDIOCD$2);
            return cTAudioCD;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAudioCd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AUDIOCD$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmbeddedWAVAudioFile getWavAudioFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmbeddedWAVAudioFile cTEmbeddedWAVAudioFile = null;
            cTEmbeddedWAVAudioFile = (CTEmbeddedWAVAudioFile)this.get_store().find_element_user(WAVAUDIOFILE$4, 0);
            if (cTEmbeddedWAVAudioFile == null) {
                return null;
            }
            return cTEmbeddedWAVAudioFile;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWavAudioFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WAVAUDIOFILE$4) != 0;
        }
    }

    @Override
    public void setWavAudioFile(CTEmbeddedWAVAudioFile cTEmbeddedWAVAudioFile) {
        this.generatedSetterHelperImpl((XmlObject)cTEmbeddedWAVAudioFile, WAVAUDIOFILE$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmbeddedWAVAudioFile addNewWavAudioFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmbeddedWAVAudioFile cTEmbeddedWAVAudioFile = null;
            cTEmbeddedWAVAudioFile = (CTEmbeddedWAVAudioFile)this.get_store().add_element_user(WAVAUDIOFILE$4);
            return cTEmbeddedWAVAudioFile;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWavAudioFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WAVAUDIOFILE$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAudioFile getAudioFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAudioFile cTAudioFile = null;
            cTAudioFile = (CTAudioFile)this.get_store().find_element_user(AUDIOFILE$6, 0);
            if (cTAudioFile == null) {
                return null;
            }
            return cTAudioFile;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAudioFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AUDIOFILE$6) != 0;
        }
    }

    @Override
    public void setAudioFile(CTAudioFile cTAudioFile) {
        this.generatedSetterHelperImpl((XmlObject)cTAudioFile, AUDIOFILE$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAudioFile addNewAudioFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAudioFile cTAudioFile = null;
            cTAudioFile = (CTAudioFile)this.get_store().add_element_user(AUDIOFILE$6);
            return cTAudioFile;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAudioFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AUDIOFILE$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVideoFile getVideoFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVideoFile cTVideoFile = null;
            cTVideoFile = (CTVideoFile)this.get_store().find_element_user(VIDEOFILE$8, 0);
            if (cTVideoFile == null) {
                return null;
            }
            return cTVideoFile;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetVideoFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VIDEOFILE$8) != 0;
        }
    }

    @Override
    public void setVideoFile(CTVideoFile cTVideoFile) {
        this.generatedSetterHelperImpl((XmlObject)cTVideoFile, VIDEOFILE$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVideoFile addNewVideoFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVideoFile cTVideoFile = null;
            cTVideoFile = (CTVideoFile)this.get_store().add_element_user(VIDEOFILE$8);
            return cTVideoFile;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetVideoFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VIDEOFILE$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTQuickTimeFile getQuickTimeFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTQuickTimeFile cTQuickTimeFile = null;
            cTQuickTimeFile = (CTQuickTimeFile)this.get_store().find_element_user(QUICKTIMEFILE$10, 0);
            if (cTQuickTimeFile == null) {
                return null;
            }
            return cTQuickTimeFile;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetQuickTimeFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(QUICKTIMEFILE$10) != 0;
        }
    }

    @Override
    public void setQuickTimeFile(CTQuickTimeFile cTQuickTimeFile) {
        this.generatedSetterHelperImpl((XmlObject)cTQuickTimeFile, QUICKTIMEFILE$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTQuickTimeFile addNewQuickTimeFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTQuickTimeFile cTQuickTimeFile = null;
            cTQuickTimeFile = (CTQuickTimeFile)this.get_store().add_element_user(QUICKTIMEFILE$10);
            return cTQuickTimeFile;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetQuickTimeFile() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(QUICKTIMEFILE$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomerDataList getCustDataLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomerDataList cTCustomerDataList = null;
            cTCustomerDataList = (CTCustomerDataList)((Object)this.get_store().find_element_user(CUSTDATALST$12, 0));
            if (cTCustomerDataList == null) {
                return null;
            }
            return cTCustomerDataList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCustDataLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTDATALST$12) != 0;
        }
    }

    @Override
    public void setCustDataLst(CTCustomerDataList cTCustomerDataList) {
        this.generatedSetterHelperImpl(cTCustomerDataList, CUSTDATALST$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomerDataList addNewCustDataLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomerDataList cTCustomerDataList = null;
            cTCustomerDataList = (CTCustomerDataList)((Object)this.get_store().add_element_user(CUSTDATALST$12));
            return cTCustomerDataList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCustDataLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTDATALST$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)this.get_store().find_element_user(EXTLST$14, 0);
            if (cTExtensionList == null) {
                return null;
            }
            return cTExtensionList;
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
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionList, EXTLST$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)this.get_store().add_element_user(EXTLST$14);
            return cTExtensionList;
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
    public boolean getIsPhoto() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ISPHOTO$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(ISPHOTO$16));
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
    public XmlBoolean xgetIsPhoto() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(ISPHOTO$16));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(ISPHOTO$16);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetIsPhoto() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ISPHOTO$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setIsPhoto(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ISPHOTO$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ISPHOTO$16));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetIsPhoto(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(ISPHOTO$16));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(ISPHOTO$16));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetIsPhoto() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ISPHOTO$16);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getUserDrawn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(USERDRAWN$18));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(USERDRAWN$18));
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
    public XmlBoolean xgetUserDrawn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(USERDRAWN$18));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(USERDRAWN$18);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUserDrawn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(USERDRAWN$18) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUserDrawn(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(USERDRAWN$18));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(USERDRAWN$18));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUserDrawn(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(USERDRAWN$18));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(USERDRAWN$18));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUserDrawn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(USERDRAWN$18);
        }
    }
}

