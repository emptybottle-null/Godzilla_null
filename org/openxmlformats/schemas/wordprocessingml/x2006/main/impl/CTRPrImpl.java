/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFitText
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPrChange
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextEffect
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFitText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHighlight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextEffect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextScale;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalAlignRun;

public class CTRPrImpl
extends XmlComplexContentImpl
implements CTRPr {
    private static final long serialVersionUID = 1L;
    private static final QName RSTYLE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rStyle");
    private static final QName RFONTS$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rFonts");
    private static final QName B$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "b");
    private static final QName BCS$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bCs");
    private static final QName I$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "i");
    private static final QName ICS$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "iCs");
    private static final QName CAPS$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "caps");
    private static final QName SMALLCAPS$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "smallCaps");
    private static final QName STRIKE$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "strike");
    private static final QName DSTRIKE$18 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dstrike");
    private static final QName OUTLINE$20 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "outline");
    private static final QName SHADOW$22 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "shadow");
    private static final QName EMBOSS$24 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "emboss");
    private static final QName IMPRINT$26 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "imprint");
    private static final QName NOPROOF$28 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "noProof");
    private static final QName SNAPTOGRID$30 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "snapToGrid");
    private static final QName VANISH$32 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "vanish");
    private static final QName WEBHIDDEN$34 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "webHidden");
    private static final QName COLOR$36 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "color");
    private static final QName SPACING$38 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "spacing");
    private static final QName W$40 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "w");
    private static final QName KERN$42 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "kern");
    private static final QName POSITION$44 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "position");
    private static final QName SZ$46 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sz");
    private static final QName SZCS$48 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "szCs");
    private static final QName HIGHLIGHT$50 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "highlight");
    private static final QName U$52 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "u");
    private static final QName EFFECT$54 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "effect");
    private static final QName BDR$56 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bdr");
    private static final QName SHD$58 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "shd");
    private static final QName FITTEXT$60 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "fitText");
    private static final QName VERTALIGN$62 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "vertAlign");
    private static final QName RTL$64 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rtl");
    private static final QName CS$66 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cs");
    private static final QName EM$68 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "em");
    private static final QName LANG$70 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "lang");
    private static final QName EASTASIANLAYOUT$72 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "eastAsianLayout");
    private static final QName SPECVANISH$74 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "specVanish");
    private static final QName OMATH$76 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "oMath");
    private static final QName RPRCHANGE$78 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rPrChange");

    public CTRPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getRStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(RSTYLE$0, 0));
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
    public boolean isSetRStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RSTYLE$0) != 0;
        }
    }

    @Override
    public void setRStyle(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, RSTYLE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewRStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(RSTYLE$0));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RSTYLE$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFonts getRFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFonts cTFonts = null;
            cTFonts = (CTFonts)((Object)this.get_store().find_element_user(RFONTS$2, 0));
            if (cTFonts == null) {
                return null;
            }
            return cTFonts;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RFONTS$2) != 0;
        }
    }

    @Override
    public void setRFonts(CTFonts cTFonts) {
        this.generatedSetterHelperImpl(cTFonts, RFONTS$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFonts addNewRFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFonts cTFonts = null;
            cTFonts = (CTFonts)((Object)this.get_store().add_element_user(RFONTS$2));
            return cTFonts;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RFONTS$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(B$4, 0));
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
    public boolean isSetB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(B$4) != 0;
        }
    }

    @Override
    public void setB(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, B$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(B$4));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(B$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getBCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(BCS$6, 0));
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
    public boolean isSetBCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BCS$6) != 0;
        }
    }

    @Override
    public void setBCs(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, BCS$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewBCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(BCS$6));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BCS$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(I$8, 0));
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
    public boolean isSetI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(I$8) != 0;
        }
    }

    @Override
    public void setI(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, I$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(I$8));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(I$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getICs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(ICS$10, 0));
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
    public boolean isSetICs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ICS$10) != 0;
        }
    }

    @Override
    public void setICs(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, ICS$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewICs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(ICS$10));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetICs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ICS$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getCaps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(CAPS$12, 0));
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
    public boolean isSetCaps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CAPS$12) != 0;
        }
    }

    @Override
    public void setCaps(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, CAPS$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewCaps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(CAPS$12));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCaps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CAPS$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getSmallCaps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SMALLCAPS$14, 0));
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
    public boolean isSetSmallCaps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SMALLCAPS$14) != 0;
        }
    }

    @Override
    public void setSmallCaps(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SMALLCAPS$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewSmallCaps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SMALLCAPS$14));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSmallCaps() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SMALLCAPS$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getStrike() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(STRIKE$16, 0));
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
    public boolean isSetStrike() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STRIKE$16) != 0;
        }
    }

    @Override
    public void setStrike(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, STRIKE$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewStrike() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(STRIKE$16));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStrike() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STRIKE$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getDstrike() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(DSTRIKE$18, 0));
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
    public boolean isSetDstrike() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DSTRIKE$18) != 0;
        }
    }

    @Override
    public void setDstrike(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, DSTRIKE$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewDstrike() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(DSTRIKE$18));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDstrike() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DSTRIKE$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getOutline() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(OUTLINE$20, 0));
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
    public boolean isSetOutline() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OUTLINE$20) != 0;
        }
    }

    @Override
    public void setOutline(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, OUTLINE$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewOutline() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(OUTLINE$20));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOutline() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OUTLINE$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getShadow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SHADOW$22, 0));
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
    public boolean isSetShadow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHADOW$22) != 0;
        }
    }

    @Override
    public void setShadow(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SHADOW$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewShadow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SHADOW$22));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShadow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHADOW$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getEmboss() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(EMBOSS$24, 0));
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
    public boolean isSetEmboss() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EMBOSS$24) != 0;
        }
    }

    @Override
    public void setEmboss(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, EMBOSS$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewEmboss() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(EMBOSS$24));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEmboss() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EMBOSS$24, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getImprint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(IMPRINT$26, 0));
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
    public boolean isSetImprint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(IMPRINT$26) != 0;
        }
    }

    @Override
    public void setImprint(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, IMPRINT$26, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewImprint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(IMPRINT$26));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetImprint() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(IMPRINT$26, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getNoProof() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(NOPROOF$28, 0));
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
    public boolean isSetNoProof() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOPROOF$28) != 0;
        }
    }

    @Override
    public void setNoProof(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, NOPROOF$28, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewNoProof() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(NOPROOF$28));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNoProof() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOPROOF$28, 0);
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
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SNAPTOGRID$30, 0));
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
            return this.get_store().count_elements(SNAPTOGRID$30) != 0;
        }
    }

    @Override
    public void setSnapToGrid(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SNAPTOGRID$30, 0, (short)1);
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
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SNAPTOGRID$30));
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
            this.get_store().remove_element(SNAPTOGRID$30, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getVanish() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(VANISH$32, 0));
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
    public boolean isSetVanish() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VANISH$32) != 0;
        }
    }

    @Override
    public void setVanish(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, VANISH$32, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewVanish() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(VANISH$32));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetVanish() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VANISH$32, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getWebHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(WEBHIDDEN$34, 0));
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
    public boolean isSetWebHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WEBHIDDEN$34) != 0;
        }
    }

    @Override
    public void setWebHidden(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, WEBHIDDEN$34, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewWebHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(WEBHIDDEN$34));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWebHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WEBHIDDEN$34, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColor getColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColor cTColor = null;
            cTColor = (CTColor)((Object)this.get_store().find_element_user(COLOR$36, 0));
            if (cTColor == null) {
                return null;
            }
            return cTColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COLOR$36) != 0;
        }
    }

    @Override
    public void setColor(CTColor cTColor) {
        this.generatedSetterHelperImpl(cTColor, COLOR$36, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColor addNewColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColor cTColor = null;
            cTColor = (CTColor)((Object)this.get_store().add_element_user(COLOR$36));
            return cTColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetColor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COLOR$36, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSignedTwipsMeasure getSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSignedTwipsMeasure cTSignedTwipsMeasure = null;
            cTSignedTwipsMeasure = (CTSignedTwipsMeasure)((Object)this.get_store().find_element_user(SPACING$38, 0));
            if (cTSignedTwipsMeasure == null) {
                return null;
            }
            return cTSignedTwipsMeasure;
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
            return this.get_store().count_elements(SPACING$38) != 0;
        }
    }

    @Override
    public void setSpacing(CTSignedTwipsMeasure cTSignedTwipsMeasure) {
        this.generatedSetterHelperImpl(cTSignedTwipsMeasure, SPACING$38, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSignedTwipsMeasure addNewSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSignedTwipsMeasure cTSignedTwipsMeasure = null;
            cTSignedTwipsMeasure = (CTSignedTwipsMeasure)((Object)this.get_store().add_element_user(SPACING$38));
            return cTSignedTwipsMeasure;
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
            this.get_store().remove_element(SPACING$38, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextScale getW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextScale cTTextScale = null;
            cTTextScale = (CTTextScale)((Object)this.get_store().find_element_user(W$40, 0));
            if (cTTextScale == null) {
                return null;
            }
            return cTTextScale;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(W$40) != 0;
        }
    }

    @Override
    public void setW(CTTextScale cTTextScale) {
        this.generatedSetterHelperImpl(cTTextScale, W$40, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextScale addNewW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextScale cTTextScale = null;
            cTTextScale = (CTTextScale)((Object)this.get_store().add_element_user(W$40));
            return cTTextScale;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(W$40, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHpsMeasure getKern() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHpsMeasure cTHpsMeasure = null;
            cTHpsMeasure = (CTHpsMeasure)((Object)this.get_store().find_element_user(KERN$42, 0));
            if (cTHpsMeasure == null) {
                return null;
            }
            return cTHpsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetKern() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(KERN$42) != 0;
        }
    }

    @Override
    public void setKern(CTHpsMeasure cTHpsMeasure) {
        this.generatedSetterHelperImpl(cTHpsMeasure, KERN$42, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHpsMeasure addNewKern() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHpsMeasure cTHpsMeasure = null;
            cTHpsMeasure = (CTHpsMeasure)((Object)this.get_store().add_element_user(KERN$42));
            return cTHpsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetKern() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(KERN$42, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSignedHpsMeasure getPosition() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSignedHpsMeasure cTSignedHpsMeasure = null;
            cTSignedHpsMeasure = (CTSignedHpsMeasure)((Object)this.get_store().find_element_user(POSITION$44, 0));
            if (cTSignedHpsMeasure == null) {
                return null;
            }
            return cTSignedHpsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPosition() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(POSITION$44) != 0;
        }
    }

    @Override
    public void setPosition(CTSignedHpsMeasure cTSignedHpsMeasure) {
        this.generatedSetterHelperImpl(cTSignedHpsMeasure, POSITION$44, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSignedHpsMeasure addNewPosition() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSignedHpsMeasure cTSignedHpsMeasure = null;
            cTSignedHpsMeasure = (CTSignedHpsMeasure)((Object)this.get_store().add_element_user(POSITION$44));
            return cTSignedHpsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPosition() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(POSITION$44, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHpsMeasure getSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHpsMeasure cTHpsMeasure = null;
            cTHpsMeasure = (CTHpsMeasure)((Object)this.get_store().find_element_user(SZ$46, 0));
            if (cTHpsMeasure == null) {
                return null;
            }
            return cTHpsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SZ$46) != 0;
        }
    }

    @Override
    public void setSz(CTHpsMeasure cTHpsMeasure) {
        this.generatedSetterHelperImpl(cTHpsMeasure, SZ$46, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHpsMeasure addNewSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHpsMeasure cTHpsMeasure = null;
            cTHpsMeasure = (CTHpsMeasure)((Object)this.get_store().add_element_user(SZ$46));
            return cTHpsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SZ$46, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHpsMeasure getSzCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHpsMeasure cTHpsMeasure = null;
            cTHpsMeasure = (CTHpsMeasure)((Object)this.get_store().find_element_user(SZCS$48, 0));
            if (cTHpsMeasure == null) {
                return null;
            }
            return cTHpsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSzCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SZCS$48) != 0;
        }
    }

    @Override
    public void setSzCs(CTHpsMeasure cTHpsMeasure) {
        this.generatedSetterHelperImpl(cTHpsMeasure, SZCS$48, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHpsMeasure addNewSzCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHpsMeasure cTHpsMeasure = null;
            cTHpsMeasure = (CTHpsMeasure)((Object)this.get_store().add_element_user(SZCS$48));
            return cTHpsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSzCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SZCS$48, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHighlight getHighlight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHighlight cTHighlight = null;
            cTHighlight = (CTHighlight)((Object)this.get_store().find_element_user(HIGHLIGHT$50, 0));
            if (cTHighlight == null) {
                return null;
            }
            return cTHighlight;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHighlight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HIGHLIGHT$50) != 0;
        }
    }

    @Override
    public void setHighlight(CTHighlight cTHighlight) {
        this.generatedSetterHelperImpl(cTHighlight, HIGHLIGHT$50, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHighlight addNewHighlight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHighlight cTHighlight = null;
            cTHighlight = (CTHighlight)((Object)this.get_store().add_element_user(HIGHLIGHT$50));
            return cTHighlight;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHighlight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HIGHLIGHT$50, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnderline getU() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnderline cTUnderline = null;
            cTUnderline = (CTUnderline)((Object)this.get_store().find_element_user(U$52, 0));
            if (cTUnderline == null) {
                return null;
            }
            return cTUnderline;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetU() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(U$52) != 0;
        }
    }

    @Override
    public void setU(CTUnderline cTUnderline) {
        this.generatedSetterHelperImpl(cTUnderline, U$52, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnderline addNewU() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnderline cTUnderline = null;
            cTUnderline = (CTUnderline)((Object)this.get_store().add_element_user(U$52));
            return cTUnderline;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetU() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(U$52, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextEffect getEffect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextEffect cTTextEffect = null;
            cTTextEffect = (CTTextEffect)this.get_store().find_element_user(EFFECT$54, 0);
            if (cTTextEffect == null) {
                return null;
            }
            return cTTextEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEffect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EFFECT$54) != 0;
        }
    }

    @Override
    public void setEffect(CTTextEffect cTTextEffect) {
        this.generatedSetterHelperImpl((XmlObject)cTTextEffect, EFFECT$54, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextEffect addNewEffect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextEffect cTTextEffect = null;
            cTTextEffect = (CTTextEffect)this.get_store().add_element_user(EFFECT$54);
            return cTTextEffect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEffect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EFFECT$54, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder getBdr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)((Object)this.get_store().find_element_user(BDR$56, 0));
            if (cTBorder == null) {
                return null;
            }
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBdr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BDR$56) != 0;
        }
    }

    @Override
    public void setBdr(CTBorder cTBorder) {
        this.generatedSetterHelperImpl(cTBorder, BDR$56, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder addNewBdr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)((Object)this.get_store().add_element_user(BDR$56));
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBdr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BDR$56, 0);
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
            cTShd = (CTShd)((Object)this.get_store().find_element_user(SHD$58, 0));
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
            return this.get_store().count_elements(SHD$58) != 0;
        }
    }

    @Override
    public void setShd(CTShd cTShd) {
        this.generatedSetterHelperImpl(cTShd, SHD$58, 0, (short)1);
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
            cTShd = (CTShd)((Object)this.get_store().add_element_user(SHD$58));
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
            this.get_store().remove_element(SHD$58, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFitText getFitText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFitText cTFitText = null;
            cTFitText = (CTFitText)this.get_store().find_element_user(FITTEXT$60, 0);
            if (cTFitText == null) {
                return null;
            }
            return cTFitText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFitText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FITTEXT$60) != 0;
        }
    }

    @Override
    public void setFitText(CTFitText cTFitText) {
        this.generatedSetterHelperImpl((XmlObject)cTFitText, FITTEXT$60, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFitText addNewFitText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFitText cTFitText = null;
            cTFitText = (CTFitText)this.get_store().add_element_user(FITTEXT$60);
            return cTFitText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFitText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FITTEXT$60, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVerticalAlignRun getVertAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVerticalAlignRun cTVerticalAlignRun = null;
            cTVerticalAlignRun = (CTVerticalAlignRun)((Object)this.get_store().find_element_user(VERTALIGN$62, 0));
            if (cTVerticalAlignRun == null) {
                return null;
            }
            return cTVerticalAlignRun;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetVertAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VERTALIGN$62) != 0;
        }
    }

    @Override
    public void setVertAlign(CTVerticalAlignRun cTVerticalAlignRun) {
        this.generatedSetterHelperImpl(cTVerticalAlignRun, VERTALIGN$62, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVerticalAlignRun addNewVertAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVerticalAlignRun cTVerticalAlignRun = null;
            cTVerticalAlignRun = (CTVerticalAlignRun)((Object)this.get_store().add_element_user(VERTALIGN$62));
            return cTVerticalAlignRun;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetVertAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VERTALIGN$62, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getRtl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(RTL$64, 0));
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
    public boolean isSetRtl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RTL$64) != 0;
        }
    }

    @Override
    public void setRtl(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, RTL$64, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewRtl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(RTL$64));
            return cTOnOff;
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
            this.get_store().remove_element(RTL$64, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(CS$66, 0));
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
    public boolean isSetCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CS$66) != 0;
        }
    }

    @Override
    public void setCs(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, CS$66, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(CS$66));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CS$66, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEm getEm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEm cTEm = null;
            cTEm = (CTEm)((Object)this.get_store().find_element_user(EM$68, 0));
            if (cTEm == null) {
                return null;
            }
            return cTEm;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EM$68) != 0;
        }
    }

    @Override
    public void setEm(CTEm cTEm) {
        this.generatedSetterHelperImpl(cTEm, EM$68, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEm addNewEm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEm cTEm = null;
            cTEm = (CTEm)((Object)this.get_store().add_element_user(EM$68));
            return cTEm;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEm() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EM$68, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLanguage getLang() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLanguage cTLanguage = null;
            cTLanguage = (CTLanguage)((Object)this.get_store().find_element_user(LANG$70, 0));
            if (cTLanguage == null) {
                return null;
            }
            return cTLanguage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLang() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LANG$70) != 0;
        }
    }

    @Override
    public void setLang(CTLanguage cTLanguage) {
        this.generatedSetterHelperImpl(cTLanguage, LANG$70, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLanguage addNewLang() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLanguage cTLanguage = null;
            cTLanguage = (CTLanguage)((Object)this.get_store().add_element_user(LANG$70));
            return cTLanguage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLang() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LANG$70, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEastAsianLayout getEastAsianLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEastAsianLayout cTEastAsianLayout = null;
            cTEastAsianLayout = (CTEastAsianLayout)this.get_store().find_element_user(EASTASIANLAYOUT$72, 0);
            if (cTEastAsianLayout == null) {
                return null;
            }
            return cTEastAsianLayout;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEastAsianLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EASTASIANLAYOUT$72) != 0;
        }
    }

    @Override
    public void setEastAsianLayout(CTEastAsianLayout cTEastAsianLayout) {
        this.generatedSetterHelperImpl((XmlObject)cTEastAsianLayout, EASTASIANLAYOUT$72, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEastAsianLayout addNewEastAsianLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEastAsianLayout cTEastAsianLayout = null;
            cTEastAsianLayout = (CTEastAsianLayout)this.get_store().add_element_user(EASTASIANLAYOUT$72);
            return cTEastAsianLayout;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEastAsianLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EASTASIANLAYOUT$72, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getSpecVanish() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SPECVANISH$74, 0));
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
    public boolean isSetSpecVanish() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SPECVANISH$74) != 0;
        }
    }

    @Override
    public void setSpecVanish(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SPECVANISH$74, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewSpecVanish() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SPECVANISH$74));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpecVanish() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SPECVANISH$74, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getOMath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(OMATH$76, 0));
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
    public boolean isSetOMath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OMATH$76) != 0;
        }
    }

    @Override
    public void setOMath(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, OMATH$76, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewOMath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(OMATH$76));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOMath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OMATH$76, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRPrChange getRPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRPrChange cTRPrChange = null;
            cTRPrChange = (CTRPrChange)this.get_store().find_element_user(RPRCHANGE$78, 0);
            if (cTRPrChange == null) {
                return null;
            }
            return cTRPrChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RPRCHANGE$78) != 0;
        }
    }

    @Override
    public void setRPrChange(CTRPrChange cTRPrChange) {
        this.generatedSetterHelperImpl((XmlObject)cTRPrChange, RPRCHANGE$78, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRPrChange addNewRPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRPrChange cTRPrChange = null;
            cTRPrChange = (CTRPrChange)this.get_store().add_element_user(RPRCHANGE$78);
            return cTRPrChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RPRCHANGE$78, 0);
        }
    }
}

