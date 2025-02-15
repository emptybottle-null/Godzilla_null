/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShortHexNumber
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShortHexNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblCellMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;

public class CTTblPrExBaseImpl
extends XmlComplexContentImpl
implements CTTblPrExBase {
    private static final long serialVersionUID = 1L;
    private static final QName TBLW$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblW");
    private static final QName JC$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "jc");
    private static final QName TBLCELLSPACING$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblCellSpacing");
    private static final QName TBLIND$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblInd");
    private static final QName TBLBORDERS$8 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblBorders");
    private static final QName SHD$10 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "shd");
    private static final QName TBLLAYOUT$12 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblLayout");
    private static final QName TBLCELLMAR$14 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblCellMar");
    private static final QName TBLLOOK$16 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblLook");

    public CTTblPrExBaseImpl(SchemaType schemaType) {
        super(schemaType);
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
            cTTblWidth = (CTTblWidth)((Object)this.get_store().find_element_user(TBLW$0, 0));
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
            return this.get_store().count_elements(TBLW$0) != 0;
        }
    }

    @Override
    public void setTblW(CTTblWidth cTTblWidth) {
        this.generatedSetterHelperImpl(cTTblWidth, TBLW$0, 0, (short)1);
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
            cTTblWidth = (CTTblWidth)((Object)this.get_store().add_element_user(TBLW$0));
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
            this.get_store().remove_element(TBLW$0, 0);
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
            cTJc = (CTJc)((Object)this.get_store().find_element_user(JC$2, 0));
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
            return this.get_store().count_elements(JC$2) != 0;
        }
    }

    @Override
    public void setJc(CTJc cTJc) {
        this.generatedSetterHelperImpl(cTJc, JC$2, 0, (short)1);
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
            cTJc = (CTJc)((Object)this.get_store().add_element_user(JC$2));
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
            this.get_store().remove_element(JC$2, 0);
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
            cTTblWidth = (CTTblWidth)((Object)this.get_store().find_element_user(TBLCELLSPACING$4, 0));
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
            return this.get_store().count_elements(TBLCELLSPACING$4) != 0;
        }
    }

    @Override
    public void setTblCellSpacing(CTTblWidth cTTblWidth) {
        this.generatedSetterHelperImpl(cTTblWidth, TBLCELLSPACING$4, 0, (short)1);
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
            cTTblWidth = (CTTblWidth)((Object)this.get_store().add_element_user(TBLCELLSPACING$4));
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
            this.get_store().remove_element(TBLCELLSPACING$4, 0);
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
            cTTblWidth = (CTTblWidth)((Object)this.get_store().find_element_user(TBLIND$6, 0));
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
            return this.get_store().count_elements(TBLIND$6) != 0;
        }
    }

    @Override
    public void setTblInd(CTTblWidth cTTblWidth) {
        this.generatedSetterHelperImpl(cTTblWidth, TBLIND$6, 0, (short)1);
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
            cTTblWidth = (CTTblWidth)((Object)this.get_store().add_element_user(TBLIND$6));
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
            this.get_store().remove_element(TBLIND$6, 0);
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
            cTTblBorders = (CTTblBorders)((Object)this.get_store().find_element_user(TBLBORDERS$8, 0));
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
            return this.get_store().count_elements(TBLBORDERS$8) != 0;
        }
    }

    @Override
    public void setTblBorders(CTTblBorders cTTblBorders) {
        this.generatedSetterHelperImpl(cTTblBorders, TBLBORDERS$8, 0, (short)1);
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
            cTTblBorders = (CTTblBorders)((Object)this.get_store().add_element_user(TBLBORDERS$8));
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
            this.get_store().remove_element(TBLBORDERS$8, 0);
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
            cTShd = (CTShd)((Object)this.get_store().find_element_user(SHD$10, 0));
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
            return this.get_store().count_elements(SHD$10) != 0;
        }
    }

    @Override
    public void setShd(CTShd cTShd) {
        this.generatedSetterHelperImpl(cTShd, SHD$10, 0, (short)1);
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
            cTShd = (CTShd)((Object)this.get_store().add_element_user(SHD$10));
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
            this.get_store().remove_element(SHD$10, 0);
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
            cTTblLayoutType = (CTTblLayoutType)((Object)this.get_store().find_element_user(TBLLAYOUT$12, 0));
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
            return this.get_store().count_elements(TBLLAYOUT$12) != 0;
        }
    }

    @Override
    public void setTblLayout(CTTblLayoutType cTTblLayoutType) {
        this.generatedSetterHelperImpl(cTTblLayoutType, TBLLAYOUT$12, 0, (short)1);
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
            cTTblLayoutType = (CTTblLayoutType)((Object)this.get_store().add_element_user(TBLLAYOUT$12));
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
            this.get_store().remove_element(TBLLAYOUT$12, 0);
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
            cTTblCellMar = (CTTblCellMar)((Object)this.get_store().find_element_user(TBLCELLMAR$14, 0));
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
            return this.get_store().count_elements(TBLCELLMAR$14) != 0;
        }
    }

    @Override
    public void setTblCellMar(CTTblCellMar cTTblCellMar) {
        this.generatedSetterHelperImpl(cTTblCellMar, TBLCELLMAR$14, 0, (short)1);
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
            cTTblCellMar = (CTTblCellMar)((Object)this.get_store().add_element_user(TBLCELLMAR$14));
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
            this.get_store().remove_element(TBLCELLMAR$14, 0);
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
            cTShortHexNumber = (CTShortHexNumber)this.get_store().find_element_user(TBLLOOK$16, 0);
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
            return this.get_store().count_elements(TBLLOOK$16) != 0;
        }
    }

    @Override
    public void setTblLook(CTShortHexNumber cTShortHexNumber) {
        this.generatedSetterHelperImpl((XmlObject)cTShortHexNumber, TBLLOOK$16, 0, (short)1);
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
            cTShortHexNumber = (CTShortHexNumber)this.get_store().add_element_user(TBLLOOK$16);
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
            this.get_store().remove_element(TBLLOOK$16, 0);
        }
    }
}

