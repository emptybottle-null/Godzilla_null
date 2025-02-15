/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBlipBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletColorFollowText;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizeFollowText;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePercent;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePoint;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletTypefaceFollowText;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNoBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAlignType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontAlignType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextIndent;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextIndentLevelType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextMargin;

public class CTTextParagraphPropertiesImpl
extends XmlComplexContentImpl
implements CTTextParagraphProperties {
    private static final long serialVersionUID = 1L;
    private static final QName LNSPC$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lnSpc");
    private static final QName SPCBEF$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "spcBef");
    private static final QName SPCAFT$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "spcAft");
    private static final QName BUCLRTX$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "buClrTx");
    private static final QName BUCLR$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "buClr");
    private static final QName BUSZTX$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "buSzTx");
    private static final QName BUSZPCT$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "buSzPct");
    private static final QName BUSZPTS$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "buSzPts");
    private static final QName BUFONTTX$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "buFontTx");
    private static final QName BUFONT$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "buFont");
    private static final QName BUNONE$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "buNone");
    private static final QName BUAUTONUM$22 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "buAutoNum");
    private static final QName BUCHAR$24 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "buChar");
    private static final QName BUBLIP$26 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "buBlip");
    private static final QName TABLST$28 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tabLst");
    private static final QName DEFRPR$30 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "defRPr");
    private static final QName EXTLST$32 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");
    private static final QName MARL$34 = new QName("", "marL");
    private static final QName MARR$36 = new QName("", "marR");
    private static final QName LVL$38 = new QName("", "lvl");
    private static final QName INDENT$40 = new QName("", "indent");
    private static final QName ALGN$42 = new QName("", "algn");
    private static final QName DEFTABSZ$44 = new QName("", "defTabSz");
    private static final QName RTL$46 = new QName("", "rtl");
    private static final QName EALNBRK$48 = new QName("", "eaLnBrk");
    private static final QName FONTALGN$50 = new QName("", "fontAlgn");
    private static final QName LATINLNBRK$52 = new QName("", "latinLnBrk");
    private static final QName HANGINGPUNCT$54 = new QName("", "hangingPunct");

    public CTTextParagraphPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextSpacing getLnSpc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextSpacing cTTextSpacing = null;
            cTTextSpacing = (CTTextSpacing)((Object)this.get_store().find_element_user(LNSPC$0, 0));
            if (cTTextSpacing == null) {
                return null;
            }
            return cTTextSpacing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLnSpc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LNSPC$0) != 0;
        }
    }

    @Override
    public void setLnSpc(CTTextSpacing cTTextSpacing) {
        this.generatedSetterHelperImpl(cTTextSpacing, LNSPC$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextSpacing addNewLnSpc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextSpacing cTTextSpacing = null;
            cTTextSpacing = (CTTextSpacing)((Object)this.get_store().add_element_user(LNSPC$0));
            return cTTextSpacing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLnSpc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LNSPC$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextSpacing getSpcBef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextSpacing cTTextSpacing = null;
            cTTextSpacing = (CTTextSpacing)((Object)this.get_store().find_element_user(SPCBEF$2, 0));
            if (cTTextSpacing == null) {
                return null;
            }
            return cTTextSpacing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpcBef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SPCBEF$2) != 0;
        }
    }

    @Override
    public void setSpcBef(CTTextSpacing cTTextSpacing) {
        this.generatedSetterHelperImpl(cTTextSpacing, SPCBEF$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextSpacing addNewSpcBef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextSpacing cTTextSpacing = null;
            cTTextSpacing = (CTTextSpacing)((Object)this.get_store().add_element_user(SPCBEF$2));
            return cTTextSpacing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpcBef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SPCBEF$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextSpacing getSpcAft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextSpacing cTTextSpacing = null;
            cTTextSpacing = (CTTextSpacing)((Object)this.get_store().find_element_user(SPCAFT$4, 0));
            if (cTTextSpacing == null) {
                return null;
            }
            return cTTextSpacing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpcAft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SPCAFT$4) != 0;
        }
    }

    @Override
    public void setSpcAft(CTTextSpacing cTTextSpacing) {
        this.generatedSetterHelperImpl(cTTextSpacing, SPCAFT$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextSpacing addNewSpcAft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextSpacing cTTextSpacing = null;
            cTTextSpacing = (CTTextSpacing)((Object)this.get_store().add_element_user(SPCAFT$4));
            return cTTextSpacing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpcAft() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SPCAFT$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBulletColorFollowText getBuClrTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBulletColorFollowText cTTextBulletColorFollowText = null;
            cTTextBulletColorFollowText = (CTTextBulletColorFollowText)((Object)this.get_store().find_element_user(BUCLRTX$6, 0));
            if (cTTextBulletColorFollowText == null) {
                return null;
            }
            return cTTextBulletColorFollowText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBuClrTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BUCLRTX$6) != 0;
        }
    }

    @Override
    public void setBuClrTx(CTTextBulletColorFollowText cTTextBulletColorFollowText) {
        this.generatedSetterHelperImpl(cTTextBulletColorFollowText, BUCLRTX$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBulletColorFollowText addNewBuClrTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBulletColorFollowText cTTextBulletColorFollowText = null;
            cTTextBulletColorFollowText = (CTTextBulletColorFollowText)((Object)this.get_store().add_element_user(BUCLRTX$6));
            return cTTextBulletColorFollowText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBuClrTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BUCLRTX$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColor getBuClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColor cTColor = null;
            cTColor = (CTColor)((Object)this.get_store().find_element_user(BUCLR$8, 0));
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
    public boolean isSetBuClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BUCLR$8) != 0;
        }
    }

    @Override
    public void setBuClr(CTColor cTColor) {
        this.generatedSetterHelperImpl(cTColor, BUCLR$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColor addNewBuClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColor cTColor = null;
            cTColor = (CTColor)((Object)this.get_store().add_element_user(BUCLR$8));
            return cTColor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBuClr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BUCLR$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBulletSizeFollowText getBuSzTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBulletSizeFollowText cTTextBulletSizeFollowText = null;
            cTTextBulletSizeFollowText = (CTTextBulletSizeFollowText)((Object)this.get_store().find_element_user(BUSZTX$10, 0));
            if (cTTextBulletSizeFollowText == null) {
                return null;
            }
            return cTTextBulletSizeFollowText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBuSzTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BUSZTX$10) != 0;
        }
    }

    @Override
    public void setBuSzTx(CTTextBulletSizeFollowText cTTextBulletSizeFollowText) {
        this.generatedSetterHelperImpl(cTTextBulletSizeFollowText, BUSZTX$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBulletSizeFollowText addNewBuSzTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBulletSizeFollowText cTTextBulletSizeFollowText = null;
            cTTextBulletSizeFollowText = (CTTextBulletSizeFollowText)((Object)this.get_store().add_element_user(BUSZTX$10));
            return cTTextBulletSizeFollowText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBuSzTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BUSZTX$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBulletSizePercent getBuSzPct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBulletSizePercent cTTextBulletSizePercent = null;
            cTTextBulletSizePercent = (CTTextBulletSizePercent)((Object)this.get_store().find_element_user(BUSZPCT$12, 0));
            if (cTTextBulletSizePercent == null) {
                return null;
            }
            return cTTextBulletSizePercent;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBuSzPct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BUSZPCT$12) != 0;
        }
    }

    @Override
    public void setBuSzPct(CTTextBulletSizePercent cTTextBulletSizePercent) {
        this.generatedSetterHelperImpl(cTTextBulletSizePercent, BUSZPCT$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBulletSizePercent addNewBuSzPct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBulletSizePercent cTTextBulletSizePercent = null;
            cTTextBulletSizePercent = (CTTextBulletSizePercent)((Object)this.get_store().add_element_user(BUSZPCT$12));
            return cTTextBulletSizePercent;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBuSzPct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BUSZPCT$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBulletSizePoint getBuSzPts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBulletSizePoint cTTextBulletSizePoint = null;
            cTTextBulletSizePoint = (CTTextBulletSizePoint)((Object)this.get_store().find_element_user(BUSZPTS$14, 0));
            if (cTTextBulletSizePoint == null) {
                return null;
            }
            return cTTextBulletSizePoint;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBuSzPts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BUSZPTS$14) != 0;
        }
    }

    @Override
    public void setBuSzPts(CTTextBulletSizePoint cTTextBulletSizePoint) {
        this.generatedSetterHelperImpl(cTTextBulletSizePoint, BUSZPTS$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBulletSizePoint addNewBuSzPts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBulletSizePoint cTTextBulletSizePoint = null;
            cTTextBulletSizePoint = (CTTextBulletSizePoint)((Object)this.get_store().add_element_user(BUSZPTS$14));
            return cTTextBulletSizePoint;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBuSzPts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BUSZPTS$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBulletTypefaceFollowText getBuFontTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBulletTypefaceFollowText cTTextBulletTypefaceFollowText = null;
            cTTextBulletTypefaceFollowText = (CTTextBulletTypefaceFollowText)((Object)this.get_store().find_element_user(BUFONTTX$16, 0));
            if (cTTextBulletTypefaceFollowText == null) {
                return null;
            }
            return cTTextBulletTypefaceFollowText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBuFontTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BUFONTTX$16) != 0;
        }
    }

    @Override
    public void setBuFontTx(CTTextBulletTypefaceFollowText cTTextBulletTypefaceFollowText) {
        this.generatedSetterHelperImpl(cTTextBulletTypefaceFollowText, BUFONTTX$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBulletTypefaceFollowText addNewBuFontTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBulletTypefaceFollowText cTTextBulletTypefaceFollowText = null;
            cTTextBulletTypefaceFollowText = (CTTextBulletTypefaceFollowText)((Object)this.get_store().add_element_user(BUFONTTX$16));
            return cTTextBulletTypefaceFollowText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBuFontTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BUFONTTX$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextFont getBuFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextFont cTTextFont = null;
            cTTextFont = (CTTextFont)((Object)this.get_store().find_element_user(BUFONT$18, 0));
            if (cTTextFont == null) {
                return null;
            }
            return cTTextFont;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBuFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BUFONT$18) != 0;
        }
    }

    @Override
    public void setBuFont(CTTextFont cTTextFont) {
        this.generatedSetterHelperImpl(cTTextFont, BUFONT$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextFont addNewBuFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextFont cTTextFont = null;
            cTTextFont = (CTTextFont)((Object)this.get_store().add_element_user(BUFONT$18));
            return cTTextFont;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBuFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BUFONT$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextNoBullet getBuNone() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextNoBullet cTTextNoBullet = null;
            cTTextNoBullet = (CTTextNoBullet)((Object)this.get_store().find_element_user(BUNONE$20, 0));
            if (cTTextNoBullet == null) {
                return null;
            }
            return cTTextNoBullet;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBuNone() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BUNONE$20) != 0;
        }
    }

    @Override
    public void setBuNone(CTTextNoBullet cTTextNoBullet) {
        this.generatedSetterHelperImpl(cTTextNoBullet, BUNONE$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextNoBullet addNewBuNone() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextNoBullet cTTextNoBullet = null;
            cTTextNoBullet = (CTTextNoBullet)((Object)this.get_store().add_element_user(BUNONE$20));
            return cTTextNoBullet;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBuNone() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BUNONE$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextAutonumberBullet getBuAutoNum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextAutonumberBullet cTTextAutonumberBullet = null;
            cTTextAutonumberBullet = (CTTextAutonumberBullet)((Object)this.get_store().find_element_user(BUAUTONUM$22, 0));
            if (cTTextAutonumberBullet == null) {
                return null;
            }
            return cTTextAutonumberBullet;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBuAutoNum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BUAUTONUM$22) != 0;
        }
    }

    @Override
    public void setBuAutoNum(CTTextAutonumberBullet cTTextAutonumberBullet) {
        this.generatedSetterHelperImpl(cTTextAutonumberBullet, BUAUTONUM$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextAutonumberBullet addNewBuAutoNum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextAutonumberBullet cTTextAutonumberBullet = null;
            cTTextAutonumberBullet = (CTTextAutonumberBullet)((Object)this.get_store().add_element_user(BUAUTONUM$22));
            return cTTextAutonumberBullet;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBuAutoNum() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BUAUTONUM$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextCharBullet getBuChar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextCharBullet cTTextCharBullet = null;
            cTTextCharBullet = (CTTextCharBullet)((Object)this.get_store().find_element_user(BUCHAR$24, 0));
            if (cTTextCharBullet == null) {
                return null;
            }
            return cTTextCharBullet;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBuChar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BUCHAR$24) != 0;
        }
    }

    @Override
    public void setBuChar(CTTextCharBullet cTTextCharBullet) {
        this.generatedSetterHelperImpl(cTTextCharBullet, BUCHAR$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextCharBullet addNewBuChar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextCharBullet cTTextCharBullet = null;
            cTTextCharBullet = (CTTextCharBullet)((Object)this.get_store().add_element_user(BUCHAR$24));
            return cTTextCharBullet;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBuChar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BUCHAR$24, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBlipBullet getBuBlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBlipBullet cTTextBlipBullet = null;
            cTTextBlipBullet = (CTTextBlipBullet)((Object)this.get_store().find_element_user(BUBLIP$26, 0));
            if (cTTextBlipBullet == null) {
                return null;
            }
            return cTTextBlipBullet;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBuBlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BUBLIP$26) != 0;
        }
    }

    @Override
    public void setBuBlip(CTTextBlipBullet cTTextBlipBullet) {
        this.generatedSetterHelperImpl(cTTextBlipBullet, BUBLIP$26, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBlipBullet addNewBuBlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBlipBullet cTTextBlipBullet = null;
            cTTextBlipBullet = (CTTextBlipBullet)((Object)this.get_store().add_element_user(BUBLIP$26));
            return cTTextBlipBullet;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBuBlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BUBLIP$26, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextTabStopList getTabLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextTabStopList cTTextTabStopList = null;
            cTTextTabStopList = (CTTextTabStopList)((Object)this.get_store().find_element_user(TABLST$28, 0));
            if (cTTextTabStopList == null) {
                return null;
            }
            return cTTextTabStopList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTabLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TABLST$28) != 0;
        }
    }

    @Override
    public void setTabLst(CTTextTabStopList cTTextTabStopList) {
        this.generatedSetterHelperImpl(cTTextTabStopList, TABLST$28, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextTabStopList addNewTabLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextTabStopList cTTextTabStopList = null;
            cTTextTabStopList = (CTTextTabStopList)((Object)this.get_store().add_element_user(TABLST$28));
            return cTTextTabStopList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTabLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TABLST$28, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextCharacterProperties getDefRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextCharacterProperties cTTextCharacterProperties = null;
            cTTextCharacterProperties = (CTTextCharacterProperties)((Object)this.get_store().find_element_user(DEFRPR$30, 0));
            if (cTTextCharacterProperties == null) {
                return null;
            }
            return cTTextCharacterProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDefRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DEFRPR$30) != 0;
        }
    }

    @Override
    public void setDefRPr(CTTextCharacterProperties cTTextCharacterProperties) {
        this.generatedSetterHelperImpl(cTTextCharacterProperties, DEFRPR$30, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextCharacterProperties addNewDefRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextCharacterProperties cTTextCharacterProperties = null;
            cTTextCharacterProperties = (CTTextCharacterProperties)((Object)this.get_store().add_element_user(DEFRPR$30));
            return cTTextCharacterProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDefRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DEFRPR$30, 0);
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
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$32, 0));
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
            return this.get_store().count_elements(EXTLST$32) != 0;
        }
    }

    @Override
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$32, 0, (short)1);
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
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$32));
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
            this.get_store().remove_element(EXTLST$32, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getMarL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MARL$34));
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
    public STTextMargin xgetMarL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextMargin sTTextMargin = null;
            sTTextMargin = (STTextMargin)((Object)this.get_store().find_attribute_user(MARL$34));
            return sTTextMargin;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMarL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MARL$34) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMarL(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MARL$34));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MARL$34));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMarL(STTextMargin sTTextMargin) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextMargin sTTextMargin2 = null;
            sTTextMargin2 = (STTextMargin)((Object)this.get_store().find_attribute_user(MARL$34));
            if (sTTextMargin2 == null) {
                sTTextMargin2 = (STTextMargin)((Object)this.get_store().add_attribute_user(MARL$34));
            }
            sTTextMargin2.set(sTTextMargin);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMarL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MARL$34);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getMarR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MARR$36));
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
    public STTextMargin xgetMarR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextMargin sTTextMargin = null;
            sTTextMargin = (STTextMargin)((Object)this.get_store().find_attribute_user(MARR$36));
            return sTTextMargin;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMarR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MARR$36) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMarR(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MARR$36));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MARR$36));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMarR(STTextMargin sTTextMargin) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextMargin sTTextMargin2 = null;
            sTTextMargin2 = (STTextMargin)((Object)this.get_store().find_attribute_user(MARR$36));
            if (sTTextMargin2 == null) {
                sTTextMargin2 = (STTextMargin)((Object)this.get_store().add_attribute_user(MARR$36));
            }
            sTTextMargin2.set(sTTextMargin);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMarR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MARR$36);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getLvl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LVL$38));
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
    public STTextIndentLevelType xgetLvl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextIndentLevelType sTTextIndentLevelType = null;
            sTTextIndentLevelType = (STTextIndentLevelType)((Object)this.get_store().find_attribute_user(LVL$38));
            return sTTextIndentLevelType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLvl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(LVL$38) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLvl(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LVL$38));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LVL$38));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLvl(STTextIndentLevelType sTTextIndentLevelType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextIndentLevelType sTTextIndentLevelType2 = null;
            sTTextIndentLevelType2 = (STTextIndentLevelType)((Object)this.get_store().find_attribute_user(LVL$38));
            if (sTTextIndentLevelType2 == null) {
                sTTextIndentLevelType2 = (STTextIndentLevelType)((Object)this.get_store().add_attribute_user(LVL$38));
            }
            sTTextIndentLevelType2.set(sTTextIndentLevelType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLvl() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(LVL$38);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getIndent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(INDENT$40));
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
    public STTextIndent xgetIndent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextIndent sTTextIndent = null;
            sTTextIndent = (STTextIndent)((Object)this.get_store().find_attribute_user(INDENT$40));
            return sTTextIndent;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetIndent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(INDENT$40) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setIndent(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(INDENT$40));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(INDENT$40));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetIndent(STTextIndent sTTextIndent) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextIndent sTTextIndent2 = null;
            sTTextIndent2 = (STTextIndent)((Object)this.get_store().find_attribute_user(INDENT$40));
            if (sTTextIndent2 == null) {
                sTTextIndent2 = (STTextIndent)((Object)this.get_store().add_attribute_user(INDENT$40));
            }
            sTTextIndent2.set(sTTextIndent);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetIndent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(INDENT$40);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextAlignType.Enum getAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGN$42));
            if (simpleValue == null) {
                return null;
            }
            return (STTextAlignType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextAlignType xgetAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextAlignType sTTextAlignType = null;
            sTTextAlignType = (STTextAlignType)((Object)this.get_store().find_attribute_user(ALGN$42));
            return sTTextAlignType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ALGN$42) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAlgn(STTextAlignType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGN$42));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALGN$42));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAlgn(STTextAlignType sTTextAlignType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextAlignType sTTextAlignType2 = null;
            sTTextAlignType2 = (STTextAlignType)((Object)this.get_store().find_attribute_user(ALGN$42));
            if (sTTextAlignType2 == null) {
                sTTextAlignType2 = (STTextAlignType)((Object)this.get_store().add_attribute_user(ALGN$42));
            }
            sTTextAlignType2.set(sTTextAlignType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ALGN$42);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getDefTabSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFTABSZ$44));
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
    public STCoordinate32 xgetDefTabSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate32 = null;
            sTCoordinate32 = (STCoordinate32)((Object)this.get_store().find_attribute_user(DEFTABSZ$44));
            return sTCoordinate32;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDefTabSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DEFTABSZ$44) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDefTabSz(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DEFTABSZ$44));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DEFTABSZ$44));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDefTabSz(STCoordinate32 sTCoordinate32) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate322 = null;
            sTCoordinate322 = (STCoordinate32)((Object)this.get_store().find_attribute_user(DEFTABSZ$44));
            if (sTCoordinate322 == null) {
                sTCoordinate322 = (STCoordinate32)((Object)this.get_store().add_attribute_user(DEFTABSZ$44));
            }
            sTCoordinate322.set(sTCoordinate32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDefTabSz() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DEFTABSZ$44);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RTL$46));
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
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(RTL$46));
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
            return this.get_store().find_attribute_user(RTL$46) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RTL$46));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RTL$46));
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
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(RTL$46));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(RTL$46));
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
            this.get_store().remove_attribute(RTL$46);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getEaLnBrk() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EALNBRK$48));
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
    public XmlBoolean xgetEaLnBrk() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(EALNBRK$48));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEaLnBrk() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(EALNBRK$48) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setEaLnBrk(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EALNBRK$48));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(EALNBRK$48));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetEaLnBrk(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(EALNBRK$48));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(EALNBRK$48));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEaLnBrk() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(EALNBRK$48);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextFontAlignType.Enum getFontAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FONTALGN$50));
            if (simpleValue == null) {
                return null;
            }
            return (STTextFontAlignType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextFontAlignType xgetFontAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextFontAlignType sTTextFontAlignType = null;
            sTTextFontAlignType = (STTextFontAlignType)((Object)this.get_store().find_attribute_user(FONTALGN$50));
            return sTTextFontAlignType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFontAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FONTALGN$50) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFontAlgn(STTextFontAlignType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FONTALGN$50));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FONTALGN$50));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFontAlgn(STTextFontAlignType sTTextFontAlignType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextFontAlignType sTTextFontAlignType2 = null;
            sTTextFontAlignType2 = (STTextFontAlignType)((Object)this.get_store().find_attribute_user(FONTALGN$50));
            if (sTTextFontAlignType2 == null) {
                sTTextFontAlignType2 = (STTextFontAlignType)((Object)this.get_store().add_attribute_user(FONTALGN$50));
            }
            sTTextFontAlignType2.set(sTTextFontAlignType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFontAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FONTALGN$50);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getLatinLnBrk() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LATINLNBRK$52));
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
    public XmlBoolean xgetLatinLnBrk() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(LATINLNBRK$52));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLatinLnBrk() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(LATINLNBRK$52) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLatinLnBrk(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LATINLNBRK$52));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LATINLNBRK$52));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLatinLnBrk(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(LATINLNBRK$52));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(LATINLNBRK$52));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLatinLnBrk() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(LATINLNBRK$52);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getHangingPunct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HANGINGPUNCT$54));
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
    public XmlBoolean xgetHangingPunct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(HANGINGPUNCT$54));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHangingPunct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(HANGINGPUNCT$54) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHangingPunct(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HANGINGPUNCT$54));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HANGINGPUNCT$54));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHangingPunct(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(HANGINGPUNCT$54));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(HANGINGPUNCT$54));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHangingPunct() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(HANGINGPUNCT$54);
        }
    }
}

