/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShortHexNumber
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblOverlap
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShortHexNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblCellMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblOverlap;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;

public class CTTblPrBaseImpl
extends XmlComplexContentImpl
implements CTTblPrBase {
    private static final long serialVersionUID = 1L;
    private static final QName TBLSTYLE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblStyle");
    private static final QName TBLPPR$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblpPr");
    private static final QName TBLOVERLAP$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblOverlap");
    private static final QName BIDIVISUAL$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bidiVisual");
    private static final QName TBLSTYLEROWBANDSIZE$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblStyleRowBandSize");
    private static final QName TBLSTYLECOLBANDSIZE$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblStyleColBandSize");
    private static final QName TBLW$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblW");
    private static final QName JC$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "jc");
    private static final QName TBLCELLSPACING$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblCellSpacing");
    private static final QName TBLIND$18 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblInd");
    private static final QName TBLBORDERS$20 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblBorders");
    private static final QName SHD$22 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "shd");
    private static final QName TBLLAYOUT$24 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblLayout");
    private static final QName TBLCELLMAR$26 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblCellMar");
    private static final QName TBLLOOK$28 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblLook");

    public CTTblPrBaseImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getTblStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(TBLSTYLE$0, 0));
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
    public boolean isSetTblStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLSTYLE$0) != 0;
        }
    }

    @Override
    public void setTblStyle(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, TBLSTYLE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewTblStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(TBLSTYLE$0));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLSTYLE$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblPPr getTblpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblPPr cTTblPPr = null;
            cTTblPPr = (CTTblPPr)this.get_store().find_element_user(TBLPPR$2, 0);
            if (cTTblPPr == null) {
                return null;
            }
            return cTTblPPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTblpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLPPR$2) != 0;
        }
    }

    @Override
    public void setTblpPr(CTTblPPr cTTblPPr) {
        this.generatedSetterHelperImpl((XmlObject)cTTblPPr, TBLPPR$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblPPr addNewTblpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblPPr cTTblPPr = null;
            cTTblPPr = (CTTblPPr)this.get_store().add_element_user(TBLPPR$2);
            return cTTblPPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLPPR$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblOverlap getTblOverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblOverlap cTTblOverlap = null;
            cTTblOverlap = (CTTblOverlap)this.get_store().find_element_user(TBLOVERLAP$4, 0);
            if (cTTblOverlap == null) {
                return null;
            }
            return cTTblOverlap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTblOverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLOVERLAP$4) != 0;
        }
    }

    @Override
    public void setTblOverlap(CTTblOverlap cTTblOverlap) {
        this.generatedSetterHelperImpl((XmlObject)cTTblOverlap, TBLOVERLAP$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblOverlap addNewTblOverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblOverlap cTTblOverlap = null;
            cTTblOverlap = (CTTblOverlap)this.get_store().add_element_user(TBLOVERLAP$4);
            return cTTblOverlap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblOverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLOVERLAP$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getBidiVisual() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(BIDIVISUAL$6, 0));
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
    public boolean isSetBidiVisual() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BIDIVISUAL$6) != 0;
        }
    }

    @Override
    public void setBidiVisual(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, BIDIVISUAL$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewBidiVisual() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(BIDIVISUAL$6));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBidiVisual() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BIDIVISUAL$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getTblStyleRowBandSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(TBLSTYLEROWBANDSIZE$8, 0));
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
    public boolean isSetTblStyleRowBandSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLSTYLEROWBANDSIZE$8) != 0;
        }
    }

    @Override
    public void setTblStyleRowBandSize(CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, TBLSTYLEROWBANDSIZE$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewTblStyleRowBandSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(TBLSTYLEROWBANDSIZE$8));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblStyleRowBandSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLSTYLEROWBANDSIZE$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getTblStyleColBandSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(TBLSTYLECOLBANDSIZE$10, 0));
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
    public boolean isSetTblStyleColBandSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLSTYLECOLBANDSIZE$10) != 0;
        }
    }

    @Override
    public void setTblStyleColBandSize(CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, TBLSTYLECOLBANDSIZE$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewTblStyleColBandSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(TBLSTYLECOLBANDSIZE$10));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblStyleColBandSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLSTYLECOLBANDSIZE$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth getTblW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().find_element_user(TBLW$12, 0));
            if (cTTblWidth == null) {
                return null;
            }
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTblW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLW$12) != 0;
        }
    }

    @Override
    public void setTblW(CTTblWidth cTTblWidth) {
        this.generatedSetterHelperImpl(cTTblWidth, TBLW$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth addNewTblW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().add_element_user(TBLW$12));
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLW$12, 0);
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
            cTJc = (CTJc)((Object)this.get_store().find_element_user(JC$14, 0));
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
            return this.get_store().count_elements(JC$14) != 0;
        }
    }

    @Override
    public void setJc(CTJc cTJc) {
        this.generatedSetterHelperImpl(cTJc, JC$14, 0, (short)1);
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
            cTJc = (CTJc)((Object)this.get_store().add_element_user(JC$14));
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
            this.get_store().remove_element(JC$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth getTblCellSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().find_element_user(TBLCELLSPACING$16, 0));
            if (cTTblWidth == null) {
                return null;
            }
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTblCellSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLCELLSPACING$16) != 0;
        }
    }

    @Override
    public void setTblCellSpacing(CTTblWidth cTTblWidth) {
        this.generatedSetterHelperImpl(cTTblWidth, TBLCELLSPACING$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth addNewTblCellSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().add_element_user(TBLCELLSPACING$16));
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblCellSpacing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLCELLSPACING$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth getTblInd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().find_element_user(TBLIND$18, 0));
            if (cTTblWidth == null) {
                return null;
            }
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTblInd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLIND$18) != 0;
        }
    }

    @Override
    public void setTblInd(CTTblWidth cTTblWidth) {
        this.generatedSetterHelperImpl(cTTblWidth, TBLIND$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth addNewTblInd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().add_element_user(TBLIND$18));
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblInd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLIND$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblBorders getTblBorders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblBorders cTTblBorders = null;
            cTTblBorders = (CTTblBorders)((Object)this.get_store().find_element_user(TBLBORDERS$20, 0));
            if (cTTblBorders == null) {
                return null;
            }
            return cTTblBorders;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTblBorders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLBORDERS$20) != 0;
        }
    }

    @Override
    public void setTblBorders(CTTblBorders cTTblBorders) {
        this.generatedSetterHelperImpl(cTTblBorders, TBLBORDERS$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblBorders addNewTblBorders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblBorders cTTblBorders = null;
            cTTblBorders = (CTTblBorders)((Object)this.get_store().add_element_user(TBLBORDERS$20));
            return cTTblBorders;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblBorders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLBORDERS$20, 0);
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
            cTShd = (CTShd)((Object)this.get_store().find_element_user(SHD$22, 0));
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
            return this.get_store().count_elements(SHD$22) != 0;
        }
    }

    @Override
    public void setShd(CTShd cTShd) {
        this.generatedSetterHelperImpl(cTShd, SHD$22, 0, (short)1);
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
            cTShd = (CTShd)((Object)this.get_store().add_element_user(SHD$22));
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
            this.get_store().remove_element(SHD$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblLayoutType getTblLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblLayoutType cTTblLayoutType = null;
            cTTblLayoutType = (CTTblLayoutType)((Object)this.get_store().find_element_user(TBLLAYOUT$24, 0));
            if (cTTblLayoutType == null) {
                return null;
            }
            return cTTblLayoutType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTblLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLLAYOUT$24) != 0;
        }
    }

    @Override
    public void setTblLayout(CTTblLayoutType cTTblLayoutType) {
        this.generatedSetterHelperImpl(cTTblLayoutType, TBLLAYOUT$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblLayoutType addNewTblLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblLayoutType cTTblLayoutType = null;
            cTTblLayoutType = (CTTblLayoutType)((Object)this.get_store().add_element_user(TBLLAYOUT$24));
            return cTTblLayoutType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblLayout() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLLAYOUT$24, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblCellMar getTblCellMar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblCellMar cTTblCellMar = null;
            cTTblCellMar = (CTTblCellMar)((Object)this.get_store().find_element_user(TBLCELLMAR$26, 0));
            if (cTTblCellMar == null) {
                return null;
            }
            return cTTblCellMar;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTblCellMar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLCELLMAR$26) != 0;
        }
    }

    @Override
    public void setTblCellMar(CTTblCellMar cTTblCellMar) {
        this.generatedSetterHelperImpl(cTTblCellMar, TBLCELLMAR$26, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblCellMar addNewTblCellMar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblCellMar cTTblCellMar = null;
            cTTblCellMar = (CTTblCellMar)((Object)this.get_store().add_element_user(TBLCELLMAR$26));
            return cTTblCellMar;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblCellMar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLCELLMAR$26, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShortHexNumber getTblLook() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShortHexNumber cTShortHexNumber = null;
            cTShortHexNumber = (CTShortHexNumber)this.get_store().find_element_user(TBLLOOK$28, 0);
            if (cTShortHexNumber == null) {
                return null;
            }
            return cTShortHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTblLook() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLLOOK$28) != 0;
        }
    }

    @Override
    public void setTblLook(CTShortHexNumber cTShortHexNumber) {
        this.generatedSetterHelperImpl((XmlObject)cTShortHexNumber, TBLLOOK$28, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShortHexNumber addNewTblLook() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShortHexNumber cTShortHexNumber = null;
            cTShortHexNumber = (CTShortHexNumber)this.get_store().add_element_user(TBLLOOK$28);
            return cTShortHexNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblLook() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLLOOK$28, 0);
        }
    }
}

