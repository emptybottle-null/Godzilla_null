/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTCustomShowList
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTHandoutMasterIdList
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTKinsoku
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTModifyVerifier
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTSmartTags
 *  org.openxmlformats.schemas.presentationml.x2006.main.STBookmarkIdSeed
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCustomShowList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCustomerDataList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHandoutMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTKinsoku;
import org.openxmlformats.schemas.presentationml.x2006.main.CTModifyVerifier;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSmartTags;
import org.openxmlformats.schemas.presentationml.x2006.main.STBookmarkIdSeed;

public class CTPresentationImpl
extends XmlComplexContentImpl
implements CTPresentation {
    private static final long serialVersionUID = 1L;
    private static final QName SLDMASTERIDLST$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sldMasterIdLst");
    private static final QName NOTESMASTERIDLST$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "notesMasterIdLst");
    private static final QName HANDOUTMASTERIDLST$4 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "handoutMasterIdLst");
    private static final QName SLDIDLST$6 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sldIdLst");
    private static final QName SLDSZ$8 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sldSz");
    private static final QName NOTESSZ$10 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "notesSz");
    private static final QName SMARTTAGS$12 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "smartTags");
    private static final QName EMBEDDEDFONTLST$14 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "embeddedFontLst");
    private static final QName CUSTSHOWLST$16 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "custShowLst");
    private static final QName PHOTOALBUM$18 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "photoAlbum");
    private static final QName CUSTDATALST$20 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "custDataLst");
    private static final QName KINSOKU$22 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "kinsoku");
    private static final QName DEFAULTTEXTSTYLE$24 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "defaultTextStyle");
    private static final QName MODIFYVERIFIER$26 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "modifyVerifier");
    private static final QName EXTLST$28 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "extLst");
    private static final QName SERVERZOOM$30 = new QName("", "serverZoom");
    private static final QName FIRSTSLIDENUM$32 = new QName("", "firstSlideNum");
    private static final QName SHOWSPECIALPLSONTITLESLD$34 = new QName("", "showSpecialPlsOnTitleSld");
    private static final QName RTL$36 = new QName("", "rtl");
    private static final QName REMOVEPERSONALINFOONSAVE$38 = new QName("", "removePersonalInfoOnSave");
    private static final QName COMPATMODE$40 = new QName("", "compatMode");
    private static final QName STRICTFIRSTANDLASTCHARS$42 = new QName("", "strictFirstAndLastChars");
    private static final QName EMBEDTRUETYPEFONTS$44 = new QName("", "embedTrueTypeFonts");
    private static final QName SAVESUBSETFONTS$46 = new QName("", "saveSubsetFonts");
    private static final QName AUTOCOMPRESSPICTURES$48 = new QName("", "autoCompressPictures");
    private static final QName BOOKMARKIDSEED$50 = new QName("", "bookmarkIdSeed");

    public CTPresentationImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideMasterIdList getSldMasterIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideMasterIdList cTSlideMasterIdList = null;
            cTSlideMasterIdList = (CTSlideMasterIdList)((Object)this.get_store().find_element_user(SLDMASTERIDLST$0, 0));
            if (cTSlideMasterIdList == null) {
                return null;
            }
            return cTSlideMasterIdList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSldMasterIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SLDMASTERIDLST$0) != 0;
        }
    }

    @Override
    public void setSldMasterIdLst(CTSlideMasterIdList cTSlideMasterIdList) {
        this.generatedSetterHelperImpl(cTSlideMasterIdList, SLDMASTERIDLST$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideMasterIdList addNewSldMasterIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideMasterIdList cTSlideMasterIdList = null;
            cTSlideMasterIdList = (CTSlideMasterIdList)((Object)this.get_store().add_element_user(SLDMASTERIDLST$0));
            return cTSlideMasterIdList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSldMasterIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SLDMASTERIDLST$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNotesMasterIdList getNotesMasterIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNotesMasterIdList cTNotesMasterIdList = null;
            cTNotesMasterIdList = (CTNotesMasterIdList)((Object)this.get_store().find_element_user(NOTESMASTERIDLST$2, 0));
            if (cTNotesMasterIdList == null) {
                return null;
            }
            return cTNotesMasterIdList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNotesMasterIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOTESMASTERIDLST$2) != 0;
        }
    }

    @Override
    public void setNotesMasterIdLst(CTNotesMasterIdList cTNotesMasterIdList) {
        this.generatedSetterHelperImpl(cTNotesMasterIdList, NOTESMASTERIDLST$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNotesMasterIdList addNewNotesMasterIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNotesMasterIdList cTNotesMasterIdList = null;
            cTNotesMasterIdList = (CTNotesMasterIdList)((Object)this.get_store().add_element_user(NOTESMASTERIDLST$2));
            return cTNotesMasterIdList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNotesMasterIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOTESMASTERIDLST$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHandoutMasterIdList getHandoutMasterIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHandoutMasterIdList cTHandoutMasterIdList = null;
            cTHandoutMasterIdList = (CTHandoutMasterIdList)this.get_store().find_element_user(HANDOUTMASTERIDLST$4, 0);
            if (cTHandoutMasterIdList == null) {
                return null;
            }
            return cTHandoutMasterIdList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHandoutMasterIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HANDOUTMASTERIDLST$4) != 0;
        }
    }

    @Override
    public void setHandoutMasterIdLst(CTHandoutMasterIdList cTHandoutMasterIdList) {
        this.generatedSetterHelperImpl((XmlObject)cTHandoutMasterIdList, HANDOUTMASTERIDLST$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHandoutMasterIdList addNewHandoutMasterIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHandoutMasterIdList cTHandoutMasterIdList = null;
            cTHandoutMasterIdList = (CTHandoutMasterIdList)this.get_store().add_element_user(HANDOUTMASTERIDLST$4);
            return cTHandoutMasterIdList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHandoutMasterIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HANDOUTMASTERIDLST$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideIdList getSldIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideIdList cTSlideIdList = null;
            cTSlideIdList = (CTSlideIdList)((Object)this.get_store().find_element_user(SLDIDLST$6, 0));
            if (cTSlideIdList == null) {
                return null;
            }
            return cTSlideIdList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSldIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SLDIDLST$6) != 0;
        }
    }

    @Override
    public void setSldIdLst(CTSlideIdList cTSlideIdList) {
        this.generatedSetterHelperImpl(cTSlideIdList, SLDIDLST$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideIdList addNewSldIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideIdList cTSlideIdList = null;
            cTSlideIdList = (CTSlideIdList)((Object)this.get_store().add_element_user(SLDIDLST$6));
            return cTSlideIdList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSldIdLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SLDIDLST$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideSize getSldSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideSize cTSlideSize = null;
            cTSlideSize = (CTSlideSize)((Object)this.get_store().find_element_user(SLDSZ$8, 0));
            if (cTSlideSize == null) {
                return null;
            }
            return cTSlideSize;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSldSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SLDSZ$8) != 0;
        }
    }

    @Override
    public void setSldSz(CTSlideSize cTSlideSize) {
        this.generatedSetterHelperImpl(cTSlideSize, SLDSZ$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideSize addNewSldSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideSize cTSlideSize = null;
            cTSlideSize = (CTSlideSize)((Object)this.get_store().add_element_user(SLDSZ$8));
            return cTSlideSize;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSldSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SLDSZ$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveSize2D getNotesSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveSize2D cTPositiveSize2D = null;
            cTPositiveSize2D = (CTPositiveSize2D)((Object)this.get_store().find_element_user(NOTESSZ$10, 0));
            if (cTPositiveSize2D == null) {
                return null;
            }
            return cTPositiveSize2D;
        }
    }

    @Override
    public void setNotesSz(CTPositiveSize2D cTPositiveSize2D) {
        this.generatedSetterHelperImpl(cTPositiveSize2D, NOTESSZ$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveSize2D addNewNotesSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveSize2D cTPositiveSize2D = null;
            cTPositiveSize2D = (CTPositiveSize2D)((Object)this.get_store().add_element_user(NOTESSZ$10));
            return cTPositiveSize2D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSmartTags getSmartTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSmartTags cTSmartTags = null;
            cTSmartTags = (CTSmartTags)this.get_store().find_element_user(SMARTTAGS$12, 0);
            if (cTSmartTags == null) {
                return null;
            }
            return cTSmartTags;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSmartTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SMARTTAGS$12) != 0;
        }
    }

    @Override
    public void setSmartTags(CTSmartTags cTSmartTags) {
        this.generatedSetterHelperImpl((XmlObject)cTSmartTags, SMARTTAGS$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSmartTags addNewSmartTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSmartTags cTSmartTags = null;
            cTSmartTags = (CTSmartTags)this.get_store().add_element_user(SMARTTAGS$12);
            return cTSmartTags;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSmartTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SMARTTAGS$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmbeddedFontList getEmbeddedFontLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmbeddedFontList cTEmbeddedFontList = null;
            cTEmbeddedFontList = (CTEmbeddedFontList)((Object)this.get_store().find_element_user(EMBEDDEDFONTLST$14, 0));
            if (cTEmbeddedFontList == null) {
                return null;
            }
            return cTEmbeddedFontList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEmbeddedFontLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EMBEDDEDFONTLST$14) != 0;
        }
    }

    @Override
    public void setEmbeddedFontLst(CTEmbeddedFontList cTEmbeddedFontList) {
        this.generatedSetterHelperImpl(cTEmbeddedFontList, EMBEDDEDFONTLST$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEmbeddedFontList addNewEmbeddedFontLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEmbeddedFontList cTEmbeddedFontList = null;
            cTEmbeddedFontList = (CTEmbeddedFontList)((Object)this.get_store().add_element_user(EMBEDDEDFONTLST$14));
            return cTEmbeddedFontList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEmbeddedFontLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EMBEDDEDFONTLST$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomShowList getCustShowLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomShowList cTCustomShowList = null;
            cTCustomShowList = (CTCustomShowList)this.get_store().find_element_user(CUSTSHOWLST$16, 0);
            if (cTCustomShowList == null) {
                return null;
            }
            return cTCustomShowList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCustShowLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTSHOWLST$16) != 0;
        }
    }

    @Override
    public void setCustShowLst(CTCustomShowList cTCustomShowList) {
        this.generatedSetterHelperImpl((XmlObject)cTCustomShowList, CUSTSHOWLST$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomShowList addNewCustShowLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomShowList cTCustomShowList = null;
            cTCustomShowList = (CTCustomShowList)this.get_store().add_element_user(CUSTSHOWLST$16);
            return cTCustomShowList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCustShowLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTSHOWLST$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPhotoAlbum getPhotoAlbum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPhotoAlbum cTPhotoAlbum = null;
            cTPhotoAlbum = (CTPhotoAlbum)this.get_store().find_element_user(PHOTOALBUM$18, 0);
            if (cTPhotoAlbum == null) {
                return null;
            }
            return cTPhotoAlbum;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPhotoAlbum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PHOTOALBUM$18) != 0;
        }
    }

    @Override
    public void setPhotoAlbum(CTPhotoAlbum cTPhotoAlbum) {
        this.generatedSetterHelperImpl((XmlObject)cTPhotoAlbum, PHOTOALBUM$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPhotoAlbum addNewPhotoAlbum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPhotoAlbum cTPhotoAlbum = null;
            cTPhotoAlbum = (CTPhotoAlbum)this.get_store().add_element_user(PHOTOALBUM$18);
            return cTPhotoAlbum;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPhotoAlbum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PHOTOALBUM$18, 0);
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
            cTCustomerDataList = (CTCustomerDataList)((Object)this.get_store().find_element_user(CUSTDATALST$20, 0));
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
            return this.get_store().count_elements(CUSTDATALST$20) != 0;
        }
    }

    @Override
    public void setCustDataLst(CTCustomerDataList cTCustomerDataList) {
        this.generatedSetterHelperImpl(cTCustomerDataList, CUSTDATALST$20, 0, (short)1);
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
            cTCustomerDataList = (CTCustomerDataList)((Object)this.get_store().add_element_user(CUSTDATALST$20));
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
            this.get_store().remove_element(CUSTDATALST$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTKinsoku getKinsoku() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTKinsoku cTKinsoku = null;
            cTKinsoku = (CTKinsoku)this.get_store().find_element_user(KINSOKU$22, 0);
            if (cTKinsoku == null) {
                return null;
            }
            return cTKinsoku;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetKinsoku() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(KINSOKU$22) != 0;
        }
    }

    @Override
    public void setKinsoku(CTKinsoku cTKinsoku) {
        this.generatedSetterHelperImpl((XmlObject)cTKinsoku, KINSOKU$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTKinsoku addNewKinsoku() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTKinsoku cTKinsoku = null;
            cTKinsoku = (CTKinsoku)this.get_store().add_element_user(KINSOKU$22);
            return cTKinsoku;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetKinsoku() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(KINSOKU$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextListStyle getDefaultTextStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextListStyle cTTextListStyle = null;
            cTTextListStyle = (CTTextListStyle)((Object)this.get_store().find_element_user(DEFAULTTEXTSTYLE$24, 0));
            if (cTTextListStyle == null) {
                return null;
            }
            return cTTextListStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDefaultTextStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DEFAULTTEXTSTYLE$24) != 0;
        }
    }

    @Override
    public void setDefaultTextStyle(CTTextListStyle cTTextListStyle) {
        this.generatedSetterHelperImpl(cTTextListStyle, DEFAULTTEXTSTYLE$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextListStyle addNewDefaultTextStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextListStyle cTTextListStyle = null;
            cTTextListStyle = (CTTextListStyle)((Object)this.get_store().add_element_user(DEFAULTTEXTSTYLE$24));
            return cTTextListStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDefaultTextStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DEFAULTTEXTSTYLE$24, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTModifyVerifier getModifyVerifier() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTModifyVerifier cTModifyVerifier = null;
            cTModifyVerifier = (CTModifyVerifier)this.get_store().find_element_user(MODIFYVERIFIER$26, 0);
            if (cTModifyVerifier == null) {
                return null;
            }
            return cTModifyVerifier;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetModifyVerifier() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MODIFYVERIFIER$26) != 0;
        }
    }

    @Override
    public void setModifyVerifier(CTModifyVerifier cTModifyVerifier) {
        this.generatedSetterHelperImpl((XmlObject)cTModifyVerifier, MODIFYVERIFIER$26, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTModifyVerifier addNewModifyVerifier() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTModifyVerifier cTModifyVerifier = null;
            cTModifyVerifier = (CTModifyVerifier)this.get_store().add_element_user(MODIFYVERIFIER$26);
            return cTModifyVerifier;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetModifyVerifier() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MODIFYVERIFIER$26, 0);
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
            cTExtensionList = (CTExtensionList)this.get_store().find_element_user(EXTLST$28, 0);
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
            return this.get_store().count_elements(EXTLST$28) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionList, EXTLST$28, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)this.get_store().add_element_user(EXTLST$28);
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
            this.get_store().remove_element(EXTLST$28, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getServerZoom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SERVERZOOM$30));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(SERVERZOOM$30));
            }
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPercentage xgetServerZoom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage = null;
            sTPercentage = (STPercentage)((Object)this.get_store().find_attribute_user(SERVERZOOM$30));
            if (sTPercentage == null) {
                sTPercentage = (STPercentage)this.get_default_attribute_value(SERVERZOOM$30);
            }
            return sTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetServerZoom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SERVERZOOM$30) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setServerZoom(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SERVERZOOM$30));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SERVERZOOM$30));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetServerZoom(STPercentage sTPercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage2 = null;
            sTPercentage2 = (STPercentage)((Object)this.get_store().find_attribute_user(SERVERZOOM$30));
            if (sTPercentage2 == null) {
                sTPercentage2 = (STPercentage)((Object)this.get_store().add_attribute_user(SERVERZOOM$30));
            }
            sTPercentage2.set(sTPercentage);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetServerZoom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SERVERZOOM$30);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getFirstSlideNum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FIRSTSLIDENUM$32));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(FIRSTSLIDENUM$32));
            }
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlInt xgetFirstSlideNum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInt xmlInt = null;
            xmlInt = (XmlInt)((Object)this.get_store().find_attribute_user(FIRSTSLIDENUM$32));
            if (xmlInt == null) {
                xmlInt = (XmlInt)this.get_default_attribute_value(FIRSTSLIDENUM$32);
            }
            return xmlInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFirstSlideNum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FIRSTSLIDENUM$32) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFirstSlideNum(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FIRSTSLIDENUM$32));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FIRSTSLIDENUM$32));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFirstSlideNum(XmlInt xmlInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlInt xmlInt2 = null;
            xmlInt2 = (XmlInt)((Object)this.get_store().find_attribute_user(FIRSTSLIDENUM$32));
            if (xmlInt2 == null) {
                xmlInt2 = (XmlInt)((Object)this.get_store().add_attribute_user(FIRSTSLIDENUM$32));
            }
            xmlInt2.set(xmlInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFirstSlideNum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FIRSTSLIDENUM$32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getShowSpecialPlsOnTitleSld() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SHOWSPECIALPLSONTITLESLD$34));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(SHOWSPECIALPLSONTITLESLD$34));
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
    public XmlBoolean xgetShowSpecialPlsOnTitleSld() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(SHOWSPECIALPLSONTITLESLD$34));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(SHOWSPECIALPLSONTITLESLD$34);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetShowSpecialPlsOnTitleSld() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SHOWSPECIALPLSONTITLESLD$34) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setShowSpecialPlsOnTitleSld(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SHOWSPECIALPLSONTITLESLD$34));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SHOWSPECIALPLSONTITLESLD$34));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetShowSpecialPlsOnTitleSld(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(SHOWSPECIALPLSONTITLESLD$34));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(SHOWSPECIALPLSONTITLESLD$34));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShowSpecialPlsOnTitleSld() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SHOWSPECIALPLSONTITLESLD$34);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getRtl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RTL$36));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(RTL$36));
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
    public XmlBoolean xgetRtl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(RTL$36));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(RTL$36);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRtl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(RTL$36) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRtl(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RTL$36));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RTL$36));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRtl(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(RTL$36));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(RTL$36));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRtl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(RTL$36);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getRemovePersonalInfoOnSave() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REMOVEPERSONALINFOONSAVE$38));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(REMOVEPERSONALINFOONSAVE$38));
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
    public XmlBoolean xgetRemovePersonalInfoOnSave() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(REMOVEPERSONALINFOONSAVE$38));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(REMOVEPERSONALINFOONSAVE$38);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRemovePersonalInfoOnSave() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(REMOVEPERSONALINFOONSAVE$38) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRemovePersonalInfoOnSave(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(REMOVEPERSONALINFOONSAVE$38));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(REMOVEPERSONALINFOONSAVE$38));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRemovePersonalInfoOnSave(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(REMOVEPERSONALINFOONSAVE$38));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(REMOVEPERSONALINFOONSAVE$38));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRemovePersonalInfoOnSave() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(REMOVEPERSONALINFOONSAVE$38);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getCompatMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COMPATMODE$40));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(COMPATMODE$40));
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
    public XmlBoolean xgetCompatMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(COMPATMODE$40));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(COMPATMODE$40);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCompatMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(COMPATMODE$40) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCompatMode(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COMPATMODE$40));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(COMPATMODE$40));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCompatMode(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(COMPATMODE$40));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(COMPATMODE$40));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCompatMode() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(COMPATMODE$40);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getStrictFirstAndLastChars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STRICTFIRSTANDLASTCHARS$42));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(STRICTFIRSTANDLASTCHARS$42));
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
    public XmlBoolean xgetStrictFirstAndLastChars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(STRICTFIRSTANDLASTCHARS$42));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(STRICTFIRSTANDLASTCHARS$42);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStrictFirstAndLastChars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(STRICTFIRSTANDLASTCHARS$42) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStrictFirstAndLastChars(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STRICTFIRSTANDLASTCHARS$42));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STRICTFIRSTANDLASTCHARS$42));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStrictFirstAndLastChars(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(STRICTFIRSTANDLASTCHARS$42));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(STRICTFIRSTANDLASTCHARS$42));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStrictFirstAndLastChars() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(STRICTFIRSTANDLASTCHARS$42);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getEmbedTrueTypeFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EMBEDTRUETYPEFONTS$44));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(EMBEDTRUETYPEFONTS$44));
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
    public XmlBoolean xgetEmbedTrueTypeFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(EMBEDTRUETYPEFONTS$44));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(EMBEDTRUETYPEFONTS$44);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEmbedTrueTypeFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(EMBEDTRUETYPEFONTS$44) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setEmbedTrueTypeFonts(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EMBEDTRUETYPEFONTS$44));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(EMBEDTRUETYPEFONTS$44));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetEmbedTrueTypeFonts(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(EMBEDTRUETYPEFONTS$44));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(EMBEDTRUETYPEFONTS$44));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEmbedTrueTypeFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(EMBEDTRUETYPEFONTS$44);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getSaveSubsetFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SAVESUBSETFONTS$46));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(SAVESUBSETFONTS$46));
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
    public XmlBoolean xgetSaveSubsetFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(SAVESUBSETFONTS$46));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(SAVESUBSETFONTS$46);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSaveSubsetFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SAVESUBSETFONTS$46) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSaveSubsetFonts(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SAVESUBSETFONTS$46));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SAVESUBSETFONTS$46));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSaveSubsetFonts(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(SAVESUBSETFONTS$46));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(SAVESUBSETFONTS$46));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSaveSubsetFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SAVESUBSETFONTS$46);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getAutoCompressPictures() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(AUTOCOMPRESSPICTURES$48));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(AUTOCOMPRESSPICTURES$48));
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
    public XmlBoolean xgetAutoCompressPictures() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(AUTOCOMPRESSPICTURES$48));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(AUTOCOMPRESSPICTURES$48);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAutoCompressPictures() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(AUTOCOMPRESSPICTURES$48) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAutoCompressPictures(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(AUTOCOMPRESSPICTURES$48));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(AUTOCOMPRESSPICTURES$48));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAutoCompressPictures(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(AUTOCOMPRESSPICTURES$48));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(AUTOCOMPRESSPICTURES$48));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAutoCompressPictures() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(AUTOCOMPRESSPICTURES$48);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getBookmarkIdSeed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BOOKMARKIDSEED$50));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(BOOKMARKIDSEED$50));
            }
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBookmarkIdSeed xgetBookmarkIdSeed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBookmarkIdSeed sTBookmarkIdSeed = null;
            sTBookmarkIdSeed = (STBookmarkIdSeed)this.get_store().find_attribute_user(BOOKMARKIDSEED$50);
            if (sTBookmarkIdSeed == null) {
                sTBookmarkIdSeed = (STBookmarkIdSeed)this.get_default_attribute_value(BOOKMARKIDSEED$50);
            }
            return sTBookmarkIdSeed;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBookmarkIdSeed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(BOOKMARKIDSEED$50) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBookmarkIdSeed(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BOOKMARKIDSEED$50));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BOOKMARKIDSEED$50));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBookmarkIdSeed(STBookmarkIdSeed sTBookmarkIdSeed) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBookmarkIdSeed sTBookmarkIdSeed2 = null;
            sTBookmarkIdSeed2 = (STBookmarkIdSeed)this.get_store().find_attribute_user(BOOKMARKIDSEED$50);
            if (sTBookmarkIdSeed2 == null) {
                sTBookmarkIdSeed2 = (STBookmarkIdSeed)this.get_store().add_attribute_user(BOOKMARKIDSEED$50);
            }
            sTBookmarkIdSeed2.set((XmlObject)sTBookmarkIdSeed);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBookmarkIdSeed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(BOOKMARKIDSEED$50);
        }
    }
}

