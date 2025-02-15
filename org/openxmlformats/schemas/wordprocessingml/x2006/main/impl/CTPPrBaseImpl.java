/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextboxTightWrap
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabs;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextAlignment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextboxTightWrap;

public class CTPPrBaseImpl
extends XmlComplexContentImpl
implements CTPPrBase {
    private static final long serialVersionUID = 1L;
    private static final QName PSTYLE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pStyle");
    private static final QName KEEPNEXT$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "keepNext");
    private static final QName KEEPLINES$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "keepLines");
    private static final QName PAGEBREAKBEFORE$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pageBreakBefore");
    private static final QName FRAMEPR$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "framePr");
    private static final QName WIDOWCONTROL$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "widowControl");
    private static final QName NUMPR$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "numPr");
    private static final QName SUPPRESSLINENUMBERS$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "suppressLineNumbers");
    private static final QName PBDR$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pBdr");
    private static final QName SHD$18 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "shd");
    private static final QName TABS$20 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tabs");
    private static final QName SUPPRESSAUTOHYPHENS$22 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "suppressAutoHyphens");
    private static final QName KINSOKU$24 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "kinsoku");
    private static final QName WORDWRAP$26 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "wordWrap");
    private static final QName OVERFLOWPUNCT$28 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "overflowPunct");
    private static final QName TOPLINEPUNCT$30 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "topLinePunct");
    private static final QName AUTOSPACEDE$32 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "autoSpaceDE");
    private static final QName AUTOSPACEDN$34 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "autoSpaceDN");
    private static final QName BIDI$36 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bidi");
    private static final QName ADJUSTRIGHTIND$38 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "adjustRightInd");
    private static final QName SNAPTOGRID$40 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "snapToGrid");
    private static final QName SPACING$42 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "spacing");
    private static final QName IND$44 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ind");
    private static final QName CONTEXTUALSPACING$46 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "contextualSpacing");
    private static final QName MIRRORINDENTS$48 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "mirrorIndents");
    private static final QName SUPPRESSOVERLAP$50 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "suppressOverlap");
    private static final QName JC$52 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "jc");
    private static final QName TEXTDIRECTION$54 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "textDirection");
    private static final QName TEXTALIGNMENT$56 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "textAlignment");
    private static final QName TEXTBOXTIGHTWRAP$58 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "textboxTightWrap");
    private static final QName OUTLINELVL$60 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "outlineLvl");
    private static final QName DIVID$62 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "divId");
    private static final QName CNFSTYLE$64 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cnfStyle");

    public CTPPrBaseImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getPStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(PSTYLE$0, 0));
            if (cTString == null) {
                return null;
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PSTYLE$0) != 0;
        }
    }

    @Override
    public void setPStyle(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, PSTYLE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewPStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(PSTYLE$0));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PSTYLE$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getKeepNext() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(KEEPNEXT$2, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetKeepNext() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(KEEPNEXT$2) != 0;
        }
    }

    @Override
    public void setKeepNext(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, KEEPNEXT$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewKeepNext() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(KEEPNEXT$2));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetKeepNext() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(KEEPNEXT$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getKeepLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(KEEPLINES$4, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetKeepLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(KEEPLINES$4) != 0;
        }
    }

    @Override
    public void setKeepLines(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, KEEPLINES$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewKeepLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(KEEPLINES$4));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetKeepLines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(KEEPLINES$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getPageBreakBefore() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(PAGEBREAKBEFORE$6, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPageBreakBefore() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PAGEBREAKBEFORE$6) != 0;
        }
    }

    @Override
    public void setPageBreakBefore(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, PAGEBREAKBEFORE$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewPageBreakBefore() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(PAGEBREAKBEFORE$6));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPageBreakBefore() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PAGEBREAKBEFORE$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFramePr getFramePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFramePr cTFramePr = null;
            cTFramePr = (CTFramePr)this.get_store().find_element_user(FRAMEPR$8, 0);
            if (cTFramePr == null) {
                return null;
            }
            return cTFramePr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFramePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FRAMEPR$8) != 0;
        }
    }

    @Override
    public void setFramePr(CTFramePr cTFramePr) {
        this.generatedSetterHelperImpl((XmlObject)cTFramePr, FRAMEPR$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFramePr addNewFramePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFramePr cTFramePr = null;
            cTFramePr = (CTFramePr)this.get_store().add_element_user(FRAMEPR$8);
            return cTFramePr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFramePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FRAMEPR$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getWidowControl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(WIDOWCONTROL$10, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWidowControl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WIDOWCONTROL$10) != 0;
        }
    }

    @Override
    public void setWidowControl(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, WIDOWCONTROL$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewWidowControl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(WIDOWCONTROL$10));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWidowControl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WIDOWCONTROL$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumPr getNumPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumPr cTNumPr = null;
            cTNumPr = (CTNumPr)((Object)this.get_store().find_element_user(NUMPR$12, 0));
            if (cTNumPr == null) {
                return null;
            }
            return cTNumPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNumPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NUMPR$12) != 0;
        }
    }

    @Override
    public void setNumPr(CTNumPr cTNumPr) {
        this.generatedSetterHelperImpl(cTNumPr, NUMPR$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumPr addNewNumPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumPr cTNumPr = null;
            cTNumPr = (CTNumPr)((Object)this.get_store().add_element_user(NUMPR$12));
            return cTNumPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNumPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NUMPR$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getSuppressLineNumbers() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SUPPRESSLINENUMBERS$14, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSuppressLineNumbers() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SUPPRESSLINENUMBERS$14) != 0;
        }
    }

    @Override
    public void setSuppressLineNumbers(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SUPPRESSLINENUMBERS$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewSuppressLineNumbers() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SUPPRESSLINENUMBERS$14));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSuppressLineNumbers() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SUPPRESSLINENUMBERS$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPBdr getPBdr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPBdr cTPBdr = null;
            cTPBdr = (CTPBdr)((Object)this.get_store().find_element_user(PBDR$16, 0));
            if (cTPBdr == null) {
                return null;
            }
            return cTPBdr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPBdr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PBDR$16) != 0;
        }
    }

    @Override
    public void setPBdr(CTPBdr cTPBdr) {
        this.generatedSetterHelperImpl(cTPBdr, PBDR$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPBdr addNewPBdr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPBdr cTPBdr = null;
            cTPBdr = (CTPBdr)((Object)this.get_store().add_element_user(PBDR$16));
            return cTPBdr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPBdr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PBDR$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShd getShd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShd cTShd = null;
            cTShd = (CTShd)((Object)this.get_store().find_element_user(SHD$18, 0));
            if (cTShd == null) {
                return null;
            }
            return cTShd;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetShd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHD$18) != 0;
        }
    }

    @Override
    public void setShd(CTShd cTShd) {
        this.generatedSetterHelperImpl(cTShd, SHD$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShd addNewShd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShd cTShd = null;
            cTShd = (CTShd)((Object)this.get_store().add_element_user(SHD$18));
            return cTShd;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHD$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTabs getTabs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTabs cTTabs = null;
            cTTabs = (CTTabs)((Object)this.get_store().find_element_user(TABS$20, 0));
            if (cTTabs == null) {
                return null;
            }
            return cTTabs;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTabs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TABS$20) != 0;
        }
    }

    @Override
    public void setTabs(CTTabs cTTabs) {
        this.generatedSetterHelperImpl(cTTabs, TABS$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTabs addNewTabs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTabs cTTabs = null;
            cTTabs = (CTTabs)((Object)this.get_store().add_element_user(TABS$20));
            return cTTabs;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTabs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TABS$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getSuppressAutoHyphens() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SUPPRESSAUTOHYPHENS$22, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSuppressAutoHyphens() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SUPPRESSAUTOHYPHENS$22) != 0;
        }
    }

    @Override
    public void setSuppressAutoHyphens(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SUPPRESSAUTOHYPHENS$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewSuppressAutoHyphens() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SUPPRESSAUTOHYPHENS$22));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSuppressAutoHyphens() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SUPPRESSAUTOHYPHENS$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getKinsoku() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(KINSOKU$24, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
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
            return this.get_store().count_elements(KINSOKU$24) != 0;
        }
    }

    @Override
    public void setKinsoku(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, KINSOKU$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewKinsoku() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(KINSOKU$24));
            return cTOnOff;
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
            this.get_store().remove_element(KINSOKU$24, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getWordWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(WORDWRAP$26, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWordWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WORDWRAP$26) != 0;
        }
    }

    @Override
    public void setWordWrap(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, WORDWRAP$26, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewWordWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(WORDWRAP$26));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWordWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WORDWRAP$26, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getOverflowPunct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(OVERFLOWPUNCT$28, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOverflowPunct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OVERFLOWPUNCT$28) != 0;
        }
    }

    @Override
    public void setOverflowPunct(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, OVERFLOWPUNCT$28, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewOverflowPunct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(OVERFLOWPUNCT$28));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOverflowPunct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OVERFLOWPUNCT$28, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getTopLinePunct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(TOPLINEPUNCT$30, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTopLinePunct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TOPLINEPUNCT$30) != 0;
        }
    }

    @Override
    public void setTopLinePunct(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, TOPLINEPUNCT$30, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewTopLinePunct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(TOPLINEPUNCT$30));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTopLinePunct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TOPLINEPUNCT$30, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getAutoSpaceDE() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(AUTOSPACEDE$32, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAutoSpaceDE() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AUTOSPACEDE$32) != 0;
        }
    }

    @Override
    public void setAutoSpaceDE(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, AUTOSPACEDE$32, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewAutoSpaceDE() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(AUTOSPACEDE$32));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAutoSpaceDE() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AUTOSPACEDE$32, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getAutoSpaceDN() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(AUTOSPACEDN$34, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAutoSpaceDN() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AUTOSPACEDN$34) != 0;
        }
    }

    @Override
    public void setAutoSpaceDN(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, AUTOSPACEDN$34, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewAutoSpaceDN() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(AUTOSPACEDN$34));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAutoSpaceDN() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AUTOSPACEDN$34, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getBidi() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(BIDI$36, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBidi() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BIDI$36) != 0;
        }
    }

    @Override
    public void setBidi(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, BIDI$36, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewBidi() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(BIDI$36));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBidi() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BIDI$36, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getAdjustRightInd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(ADJUSTRIGHTIND$38, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAdjustRightInd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ADJUSTRIGHTIND$38) != 0;
        }
    }

    @Override
    public void setAdjustRightInd(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, ADJUSTRIGHTIND$38, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewAdjustRightInd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(ADJUSTRIGHTIND$38));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAdjustRightInd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ADJUSTRIGHTIND$38, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getSnapToGrid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SNAPTOGRID$40, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSnapToGrid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SNAPTOGRID$40) != 0;
        }
    }

    @Override
    public void setSnapToGrid(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SNAPTOGRID$40, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewSnapToGrid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SNAPTOGRID$40));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSnapToGrid() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SNAPTOGRID$40, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSpacing getSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSpacing cTSpacing = null;
            cTSpacing = (CTSpacing)((Object)this.get_store().find_element_user(SPACING$42, 0));
            if (cTSpacing == null) {
                return null;
            }
            return cTSpacing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SPACING$42) != 0;
        }
    }

    @Override
    public void setSpacing(CTSpacing cTSpacing) {
        this.generatedSetterHelperImpl(cTSpacing, SPACING$42, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSpacing addNewSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSpacing cTSpacing = null;
            cTSpacing = (CTSpacing)((Object)this.get_store().add_element_user(SPACING$42));
            return cTSpacing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SPACING$42, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTInd getInd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTInd cTInd = null;
            cTInd = (CTInd)((Object)this.get_store().find_element_user(IND$44, 0));
            if (cTInd == null) {
                return null;
            }
            return cTInd;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetInd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(IND$44) != 0;
        }
    }

    @Override
    public void setInd(CTInd cTInd) {
        this.generatedSetterHelperImpl(cTInd, IND$44, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTInd addNewInd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTInd cTInd = null;
            cTInd = (CTInd)((Object)this.get_store().add_element_user(IND$44));
            return cTInd;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetInd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(IND$44, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getContextualSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(CONTEXTUALSPACING$46, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetContextualSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CONTEXTUALSPACING$46) != 0;
        }
    }

    @Override
    public void setContextualSpacing(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, CONTEXTUALSPACING$46, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewContextualSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(CONTEXTUALSPACING$46));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetContextualSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CONTEXTUALSPACING$46, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getMirrorIndents() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(MIRRORINDENTS$48, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMirrorIndents() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MIRRORINDENTS$48) != 0;
        }
    }

    @Override
    public void setMirrorIndents(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, MIRRORINDENTS$48, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewMirrorIndents() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(MIRRORINDENTS$48));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMirrorIndents() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MIRRORINDENTS$48, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getSuppressOverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SUPPRESSOVERLAP$50, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSuppressOverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SUPPRESSOVERLAP$50) != 0;
        }
    }

    @Override
    public void setSuppressOverlap(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SUPPRESSOVERLAP$50, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewSuppressOverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SUPPRESSOVERLAP$50));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSuppressOverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SUPPRESSOVERLAP$50, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTJc getJc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTJc cTJc = null;
            cTJc = (CTJc)((Object)this.get_store().find_element_user(JC$52, 0));
            if (cTJc == null) {
                return null;
            }
            return cTJc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetJc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(JC$52) != 0;
        }
    }

    @Override
    public void setJc(CTJc cTJc) {
        this.generatedSetterHelperImpl(cTJc, JC$52, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTJc addNewJc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTJc cTJc = null;
            cTJc = (CTJc)((Object)this.get_store().add_element_user(JC$52));
            return cTJc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetJc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(JC$52, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextDirection getTextDirection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextDirection cTTextDirection = null;
            cTTextDirection = (CTTextDirection)this.get_store().find_element_user(TEXTDIRECTION$54, 0);
            if (cTTextDirection == null) {
                return null;
            }
            return cTTextDirection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTextDirection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TEXTDIRECTION$54) != 0;
        }
    }

    @Override
    public void setTextDirection(CTTextDirection cTTextDirection) {
        this.generatedSetterHelperImpl((XmlObject)cTTextDirection, TEXTDIRECTION$54, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextDirection addNewTextDirection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextDirection cTTextDirection = null;
            cTTextDirection = (CTTextDirection)this.get_store().add_element_user(TEXTDIRECTION$54);
            return cTTextDirection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTextDirection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TEXTDIRECTION$54, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextAlignment getTextAlignment() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextAlignment cTTextAlignment = null;
            cTTextAlignment = (CTTextAlignment)((Object)this.get_store().find_element_user(TEXTALIGNMENT$56, 0));
            if (cTTextAlignment == null) {
                return null;
            }
            return cTTextAlignment;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTextAlignment() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TEXTALIGNMENT$56) != 0;
        }
    }

    @Override
    public void setTextAlignment(CTTextAlignment cTTextAlignment) {
        this.generatedSetterHelperImpl(cTTextAlignment, TEXTALIGNMENT$56, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextAlignment addNewTextAlignment() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextAlignment cTTextAlignment = null;
            cTTextAlignment = (CTTextAlignment)((Object)this.get_store().add_element_user(TEXTALIGNMENT$56));
            return cTTextAlignment;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTextAlignment() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TEXTALIGNMENT$56, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextboxTightWrap getTextboxTightWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextboxTightWrap cTTextboxTightWrap = null;
            cTTextboxTightWrap = (CTTextboxTightWrap)this.get_store().find_element_user(TEXTBOXTIGHTWRAP$58, 0);
            if (cTTextboxTightWrap == null) {
                return null;
            }
            return cTTextboxTightWrap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTextboxTightWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TEXTBOXTIGHTWRAP$58) != 0;
        }
    }

    @Override
    public void setTextboxTightWrap(CTTextboxTightWrap cTTextboxTightWrap) {
        this.generatedSetterHelperImpl((XmlObject)cTTextboxTightWrap, TEXTBOXTIGHTWRAP$58, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextboxTightWrap addNewTextboxTightWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextboxTightWrap cTTextboxTightWrap = null;
            cTTextboxTightWrap = (CTTextboxTightWrap)this.get_store().add_element_user(TEXTBOXTIGHTWRAP$58);
            return cTTextboxTightWrap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTextboxTightWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TEXTBOXTIGHTWRAP$58, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getOutlineLvl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(OUTLINELVL$60, 0));
            if (cTDecimalNumber == null) {
                return null;
            }
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOutlineLvl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OUTLINELVL$60) != 0;
        }
    }

    @Override
    public void setOutlineLvl(CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, OUTLINELVL$60, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewOutlineLvl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(OUTLINELVL$60));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOutlineLvl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OUTLINELVL$60, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getDivId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(DIVID$62, 0));
            if (cTDecimalNumber == null) {
                return null;
            }
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDivId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DIVID$62) != 0;
        }
    }

    @Override
    public void setDivId(CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, DIVID$62, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewDivId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(DIVID$62));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDivId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DIVID$62, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCnf getCnfStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCnf cTCnf = null;
            cTCnf = (CTCnf)this.get_store().find_element_user(CNFSTYLE$64, 0);
            if (cTCnf == null) {
                return null;
            }
            return cTCnf;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCnfStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CNFSTYLE$64) != 0;
        }
    }

    @Override
    public void setCnfStyle(CTCnf cTCnf) {
        this.generatedSetterHelperImpl((XmlObject)cTCnf, CNFSTYLE$64, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCnf addNewCnfStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCnf cTCnf = null;
            cTCnf = (CTCnf)this.get_store().add_element_user(CNFSTYLE$64);
            return cTCnf;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCnfStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CNFSTYLE$64, 0);
        }
    }
}

