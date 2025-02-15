/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;

public class CTTcPrBaseImpl
extends XmlComplexContentImpl
implements CTTcPrBase {
    private static final long serialVersionUID = 1L;
    private static final QName CNFSTYLE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cnfStyle");
    private static final QName TCW$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tcW");
    private static final QName GRIDSPAN$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "gridSpan");
    private static final QName HMERGE$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hMerge");
    private static final QName VMERGE$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "vMerge");
    private static final QName TCBORDERS$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tcBorders");
    private static final QName SHD$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "shd");
    private static final QName NOWRAP$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "noWrap");
    private static final QName TCMAR$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tcMar");
    private static final QName TEXTDIRECTION$18 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "textDirection");
    private static final QName TCFITTEXT$20 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tcFitText");
    private static final QName VALIGN$22 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "vAlign");
    private static final QName HIDEMARK$24 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hideMark");

    public CTTcPrBaseImpl(SchemaType schemaType) {
        super(schemaType);
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
            cTCnf = (CTCnf)this.get_store().find_element_user(CNFSTYLE$0, 0);
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
            return this.get_store().count_elements(CNFSTYLE$0) != 0;
        }
    }

    @Override
    public void setCnfStyle(CTCnf cTCnf) {
        this.generatedSetterHelperImpl((XmlObject)cTCnf, CNFSTYLE$0, 0, (short)1);
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
            cTCnf = (CTCnf)this.get_store().add_element_user(CNFSTYLE$0);
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
            this.get_store().remove_element(CNFSTYLE$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth getTcW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().find_element_user(TCW$2, 0));
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
    public boolean isSetTcW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TCW$2) != 0;
        }
    }

    @Override
    public void setTcW(CTTblWidth cTTblWidth) {
        this.generatedSetterHelperImpl(cTTblWidth, TCW$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblWidth addNewTcW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblWidth cTTblWidth = null;
            cTTblWidth = (CTTblWidth)((Object)this.get_store().add_element_user(TCW$2));
            return cTTblWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTcW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TCW$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber getGridSpan() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().find_element_user(GRIDSPAN$4, 0));
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
    public boolean isSetGridSpan() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRIDSPAN$4) != 0;
        }
    }

    @Override
    public void setGridSpan(CTDecimalNumber cTDecimalNumber) {
        this.generatedSetterHelperImpl(cTDecimalNumber, GRIDSPAN$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDecimalNumber addNewGridSpan() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDecimalNumber cTDecimalNumber = null;
            cTDecimalNumber = (CTDecimalNumber)((Object)this.get_store().add_element_user(GRIDSPAN$4));
            return cTDecimalNumber;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGridSpan() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRIDSPAN$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHMerge getHMerge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHMerge cTHMerge = null;
            cTHMerge = (CTHMerge)((Object)this.get_store().find_element_user(HMERGE$6, 0));
            if (cTHMerge == null) {
                return null;
            }
            return cTHMerge;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHMerge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HMERGE$6) != 0;
        }
    }

    @Override
    public void setHMerge(CTHMerge cTHMerge) {
        this.generatedSetterHelperImpl(cTHMerge, HMERGE$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHMerge addNewHMerge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHMerge cTHMerge = null;
            cTHMerge = (CTHMerge)((Object)this.get_store().add_element_user(HMERGE$6));
            return cTHMerge;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHMerge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HMERGE$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVMerge getVMerge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVMerge cTVMerge = null;
            cTVMerge = (CTVMerge)((Object)this.get_store().find_element_user(VMERGE$8, 0));
            if (cTVMerge == null) {
                return null;
            }
            return cTVMerge;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetVMerge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VMERGE$8) != 0;
        }
    }

    @Override
    public void setVMerge(CTVMerge cTVMerge) {
        this.generatedSetterHelperImpl(cTVMerge, VMERGE$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVMerge addNewVMerge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVMerge cTVMerge = null;
            cTVMerge = (CTVMerge)((Object)this.get_store().add_element_user(VMERGE$8));
            return cTVMerge;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetVMerge() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VMERGE$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTcBorders getTcBorders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTcBorders cTTcBorders = null;
            cTTcBorders = (CTTcBorders)((Object)this.get_store().find_element_user(TCBORDERS$10, 0));
            if (cTTcBorders == null) {
                return null;
            }
            return cTTcBorders;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTcBorders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TCBORDERS$10) != 0;
        }
    }

    @Override
    public void setTcBorders(CTTcBorders cTTcBorders) {
        this.generatedSetterHelperImpl(cTTcBorders, TCBORDERS$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTcBorders addNewTcBorders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTcBorders cTTcBorders = null;
            cTTcBorders = (CTTcBorders)((Object)this.get_store().add_element_user(TCBORDERS$10));
            return cTTcBorders;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTcBorders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TCBORDERS$10, 0);
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
            cTShd = (CTShd)((Object)this.get_store().find_element_user(SHD$12, 0));
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
            return this.get_store().count_elements(SHD$12) != 0;
        }
    }

    @Override
    public void setShd(CTShd cTShd) {
        this.generatedSetterHelperImpl(cTShd, SHD$12, 0, (short)1);
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
            cTShd = (CTShd)((Object)this.get_store().add_element_user(SHD$12));
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
            this.get_store().remove_element(SHD$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getNoWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(NOWRAP$14, 0));
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
    public boolean isSetNoWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOWRAP$14) != 0;
        }
    }

    @Override
    public void setNoWrap(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, NOWRAP$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewNoWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(NOWRAP$14));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNoWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOWRAP$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTcMar getTcMar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTcMar cTTcMar = null;
            cTTcMar = (CTTcMar)this.get_store().find_element_user(TCMAR$16, 0);
            if (cTTcMar == null) {
                return null;
            }
            return cTTcMar;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTcMar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TCMAR$16) != 0;
        }
    }

    @Override
    public void setTcMar(CTTcMar cTTcMar) {
        this.generatedSetterHelperImpl((XmlObject)cTTcMar, TCMAR$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTcMar addNewTcMar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTcMar cTTcMar = null;
            cTTcMar = (CTTcMar)this.get_store().add_element_user(TCMAR$16);
            return cTTcMar;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTcMar() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TCMAR$16, 0);
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
            cTTextDirection = (CTTextDirection)this.get_store().find_element_user(TEXTDIRECTION$18, 0);
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
            return this.get_store().count_elements(TEXTDIRECTION$18) != 0;
        }
    }

    @Override
    public void setTextDirection(CTTextDirection cTTextDirection) {
        this.generatedSetterHelperImpl((XmlObject)cTTextDirection, TEXTDIRECTION$18, 0, (short)1);
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
            cTTextDirection = (CTTextDirection)this.get_store().add_element_user(TEXTDIRECTION$18);
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
            this.get_store().remove_element(TEXTDIRECTION$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getTcFitText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(TCFITTEXT$20, 0));
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
    public boolean isSetTcFitText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TCFITTEXT$20) != 0;
        }
    }

    @Override
    public void setTcFitText(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, TCFITTEXT$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewTcFitText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(TCFITTEXT$20));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTcFitText() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TCFITTEXT$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVerticalJc getVAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVerticalJc cTVerticalJc = null;
            cTVerticalJc = (CTVerticalJc)((Object)this.get_store().find_element_user(VALIGN$22, 0));
            if (cTVerticalJc == null) {
                return null;
            }
            return cTVerticalJc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetVAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VALIGN$22) != 0;
        }
    }

    @Override
    public void setVAlign(CTVerticalJc cTVerticalJc) {
        this.generatedSetterHelperImpl(cTVerticalJc, VALIGN$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTVerticalJc addNewVAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTVerticalJc cTVerticalJc = null;
            cTVerticalJc = (CTVerticalJc)((Object)this.get_store().add_element_user(VALIGN$22));
            return cTVerticalJc;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetVAlign() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VALIGN$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getHideMark() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(HIDEMARK$24, 0));
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
    public boolean isSetHideMark() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HIDEMARK$24) != 0;
        }
    }

    @Override
    public void setHideMark(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, HIDEMARK$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewHideMark() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(HIDEMARK$24));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHideMark() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HIDEMARK$24, 0);
        }
    }
}

