/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTFlatText
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTPresetTextShape
 *  org.openxmlformats.schemas.drawingml.x2006.main.STTextColumnCount
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFlatText;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetTextShape;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNoAutofit;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextShapeAutofit;
import org.openxmlformats.schemas.drawingml.x2006.main.STAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextColumnCount;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextHorzOverflowType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVertOverflowType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVerticalType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextWrappingType;

public class CTTextBodyPropertiesImpl
extends XmlComplexContentImpl
implements CTTextBodyProperties {
    private static final long serialVersionUID = 1L;
    private static final QName PRSTTXWARP$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "prstTxWarp");
    private static final QName NOAUTOFIT$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "noAutofit");
    private static final QName NORMAUTOFIT$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "normAutofit");
    private static final QName SPAUTOFIT$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "spAutoFit");
    private static final QName SCENE3D$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "scene3d");
    private static final QName SP3D$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "sp3d");
    private static final QName FLATTX$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "flatTx");
    private static final QName EXTLST$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");
    private static final QName ROT$16 = new QName("", "rot");
    private static final QName SPCFIRSTLASTPARA$18 = new QName("", "spcFirstLastPara");
    private static final QName VERTOVERFLOW$20 = new QName("", "vertOverflow");
    private static final QName HORZOVERFLOW$22 = new QName("", "horzOverflow");
    private static final QName VERT$24 = new QName("", "vert");
    private static final QName WRAP$26 = new QName("", "wrap");
    private static final QName LINS$28 = new QName("", "lIns");
    private static final QName TINS$30 = new QName("", "tIns");
    private static final QName RINS$32 = new QName("", "rIns");
    private static final QName BINS$34 = new QName("", "bIns");
    private static final QName NUMCOL$36 = new QName("", "numCol");
    private static final QName SPCCOL$38 = new QName("", "spcCol");
    private static final QName RTLCOL$40 = new QName("", "rtlCol");
    private static final QName FROMWORDART$42 = new QName("", "fromWordArt");
    private static final QName ANCHOR$44 = new QName("", "anchor");
    private static final QName ANCHORCTR$46 = new QName("", "anchorCtr");
    private static final QName FORCEAA$48 = new QName("", "forceAA");
    private static final QName UPRIGHT$50 = new QName("", "upright");
    private static final QName COMPATLNSPC$52 = new QName("", "compatLnSpc");

    public CTTextBodyPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresetTextShape getPrstTxWarp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresetTextShape cTPresetTextShape = null;
            cTPresetTextShape = (CTPresetTextShape)this.get_store().find_element_user(PRSTTXWARP$0, 0);
            if (cTPresetTextShape == null) {
                return null;
            }
            return cTPresetTextShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPrstTxWarp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PRSTTXWARP$0) != 0;
        }
    }

    @Override
    public void setPrstTxWarp(CTPresetTextShape cTPresetTextShape) {
        this.generatedSetterHelperImpl((XmlObject)cTPresetTextShape, PRSTTXWARP$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresetTextShape addNewPrstTxWarp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresetTextShape cTPresetTextShape = null;
            cTPresetTextShape = (CTPresetTextShape)this.get_store().add_element_user(PRSTTXWARP$0);
            return cTPresetTextShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPrstTxWarp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PRSTTXWARP$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextNoAutofit getNoAutofit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextNoAutofit cTTextNoAutofit = null;
            cTTextNoAutofit = (CTTextNoAutofit)((Object)this.get_store().find_element_user(NOAUTOFIT$2, 0));
            if (cTTextNoAutofit == null) {
                return null;
            }
            return cTTextNoAutofit;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNoAutofit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOAUTOFIT$2) != 0;
        }
    }

    @Override
    public void setNoAutofit(CTTextNoAutofit cTTextNoAutofit) {
        this.generatedSetterHelperImpl(cTTextNoAutofit, NOAUTOFIT$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextNoAutofit addNewNoAutofit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextNoAutofit cTTextNoAutofit = null;
            cTTextNoAutofit = (CTTextNoAutofit)((Object)this.get_store().add_element_user(NOAUTOFIT$2));
            return cTTextNoAutofit;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNoAutofit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOAUTOFIT$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextNormalAutofit getNormAutofit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextNormalAutofit cTTextNormalAutofit = null;
            cTTextNormalAutofit = (CTTextNormalAutofit)((Object)this.get_store().find_element_user(NORMAUTOFIT$4, 0));
            if (cTTextNormalAutofit == null) {
                return null;
            }
            return cTTextNormalAutofit;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNormAutofit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NORMAUTOFIT$4) != 0;
        }
    }

    @Override
    public void setNormAutofit(CTTextNormalAutofit cTTextNormalAutofit) {
        this.generatedSetterHelperImpl(cTTextNormalAutofit, NORMAUTOFIT$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextNormalAutofit addNewNormAutofit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextNormalAutofit cTTextNormalAutofit = null;
            cTTextNormalAutofit = (CTTextNormalAutofit)((Object)this.get_store().add_element_user(NORMAUTOFIT$4));
            return cTTextNormalAutofit;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNormAutofit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NORMAUTOFIT$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextShapeAutofit getSpAutoFit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextShapeAutofit cTTextShapeAutofit = null;
            cTTextShapeAutofit = (CTTextShapeAutofit)((Object)this.get_store().find_element_user(SPAUTOFIT$6, 0));
            if (cTTextShapeAutofit == null) {
                return null;
            }
            return cTTextShapeAutofit;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpAutoFit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SPAUTOFIT$6) != 0;
        }
    }

    @Override
    public void setSpAutoFit(CTTextShapeAutofit cTTextShapeAutofit) {
        this.generatedSetterHelperImpl(cTTextShapeAutofit, SPAUTOFIT$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextShapeAutofit addNewSpAutoFit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextShapeAutofit cTTextShapeAutofit = null;
            cTTextShapeAutofit = (CTTextShapeAutofit)((Object)this.get_store().add_element_user(SPAUTOFIT$6));
            return cTTextShapeAutofit;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpAutoFit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SPAUTOFIT$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScene3D getScene3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScene3D cTScene3D = null;
            cTScene3D = (CTScene3D)((Object)this.get_store().find_element_user(SCENE3D$8, 0));
            if (cTScene3D == null) {
                return null;
            }
            return cTScene3D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetScene3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCENE3D$8) != 0;
        }
    }

    @Override
    public void setScene3D(CTScene3D cTScene3D) {
        this.generatedSetterHelperImpl(cTScene3D, SCENE3D$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScene3D addNewScene3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScene3D cTScene3D = null;
            cTScene3D = (CTScene3D)((Object)this.get_store().add_element_user(SCENE3D$8));
            return cTScene3D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetScene3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCENE3D$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShape3D getSp3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShape3D cTShape3D = null;
            cTShape3D = (CTShape3D)((Object)this.get_store().find_element_user(SP3D$10, 0));
            if (cTShape3D == null) {
                return null;
            }
            return cTShape3D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSp3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SP3D$10) != 0;
        }
    }

    @Override
    public void setSp3D(CTShape3D cTShape3D) {
        this.generatedSetterHelperImpl(cTShape3D, SP3D$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShape3D addNewSp3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShape3D cTShape3D = null;
            cTShape3D = (CTShape3D)((Object)this.get_store().add_element_user(SP3D$10));
            return cTShape3D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSp3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SP3D$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFlatText getFlatTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFlatText cTFlatText = null;
            cTFlatText = (CTFlatText)this.get_store().find_element_user(FLATTX$12, 0);
            if (cTFlatText == null) {
                return null;
            }
            return cTFlatText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFlatTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FLATTX$12) != 0;
        }
    }

    @Override
    public void setFlatTx(CTFlatText cTFlatText) {
        this.generatedSetterHelperImpl((XmlObject)cTFlatText, FLATTX$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFlatText addNewFlatTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFlatText cTFlatText = null;
            cTFlatText = (CTFlatText)this.get_store().add_element_user(FLATTX$12);
            return cTFlatText;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFlatTx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FLATTX$12, 0);
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
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$14, 0));
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
            return this.get_store().count_elements(EXTLST$14) != 0;
        }
    }

    @Override
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$14, 0, (short)1);
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
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$14));
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
            this.get_store().remove_element(EXTLST$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getRot() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ROT$16));
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
    public STAngle xgetRot() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAngle sTAngle = null;
            sTAngle = (STAngle)((Object)this.get_store().find_attribute_user(ROT$16));
            return sTAngle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRot() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ROT$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRot(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ROT$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ROT$16));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRot(STAngle sTAngle) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAngle sTAngle2 = null;
            sTAngle2 = (STAngle)((Object)this.get_store().find_attribute_user(ROT$16));
            if (sTAngle2 == null) {
                sTAngle2 = (STAngle)((Object)this.get_store().add_attribute_user(ROT$16));
            }
            sTAngle2.set(sTAngle);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRot() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ROT$16);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getSpcFirstLastPara() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SPCFIRSTLASTPARA$18));
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
    public XmlBoolean xgetSpcFirstLastPara() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(SPCFIRSTLASTPARA$18));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpcFirstLastPara() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SPCFIRSTLASTPARA$18) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSpcFirstLastPara(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SPCFIRSTLASTPARA$18));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SPCFIRSTLASTPARA$18));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSpcFirstLastPara(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(SPCFIRSTLASTPARA$18));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(SPCFIRSTLASTPARA$18));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpcFirstLastPara() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SPCFIRSTLASTPARA$18);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextVertOverflowType.Enum getVertOverflow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VERTOVERFLOW$20));
            if (simpleValue == null) {
                return null;
            }
            return (STTextVertOverflowType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextVertOverflowType xgetVertOverflow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextVertOverflowType sTTextVertOverflowType = null;
            sTTextVertOverflowType = (STTextVertOverflowType)((Object)this.get_store().find_attribute_user(VERTOVERFLOW$20));
            return sTTextVertOverflowType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetVertOverflow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(VERTOVERFLOW$20) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVertOverflow(STTextVertOverflowType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VERTOVERFLOW$20));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(VERTOVERFLOW$20));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVertOverflow(STTextVertOverflowType sTTextVertOverflowType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextVertOverflowType sTTextVertOverflowType2 = null;
            sTTextVertOverflowType2 = (STTextVertOverflowType)((Object)this.get_store().find_attribute_user(VERTOVERFLOW$20));
            if (sTTextVertOverflowType2 == null) {
                sTTextVertOverflowType2 = (STTextVertOverflowType)((Object)this.get_store().add_attribute_user(VERTOVERFLOW$20));
            }
            sTTextVertOverflowType2.set(sTTextVertOverflowType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetVertOverflow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(VERTOVERFLOW$20);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextHorzOverflowType.Enum getHorzOverflow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HORZOVERFLOW$22));
            if (simpleValue == null) {
                return null;
            }
            return (STTextHorzOverflowType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextHorzOverflowType xgetHorzOverflow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextHorzOverflowType sTTextHorzOverflowType = null;
            sTTextHorzOverflowType = (STTextHorzOverflowType)((Object)this.get_store().find_attribute_user(HORZOVERFLOW$22));
            return sTTextHorzOverflowType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHorzOverflow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(HORZOVERFLOW$22) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHorzOverflow(STTextHorzOverflowType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HORZOVERFLOW$22));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HORZOVERFLOW$22));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHorzOverflow(STTextHorzOverflowType sTTextHorzOverflowType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextHorzOverflowType sTTextHorzOverflowType2 = null;
            sTTextHorzOverflowType2 = (STTextHorzOverflowType)((Object)this.get_store().find_attribute_user(HORZOVERFLOW$22));
            if (sTTextHorzOverflowType2 == null) {
                sTTextHorzOverflowType2 = (STTextHorzOverflowType)((Object)this.get_store().add_attribute_user(HORZOVERFLOW$22));
            }
            sTTextHorzOverflowType2.set(sTTextHorzOverflowType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHorzOverflow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(HORZOVERFLOW$22);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextVerticalType.Enum getVert() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VERT$24));
            if (simpleValue == null) {
                return null;
            }
            return (STTextVerticalType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextVerticalType xgetVert() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextVerticalType sTTextVerticalType = null;
            sTTextVerticalType = (STTextVerticalType)((Object)this.get_store().find_attribute_user(VERT$24));
            return sTTextVerticalType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetVert() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(VERT$24) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVert(STTextVerticalType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VERT$24));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(VERT$24));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVert(STTextVerticalType sTTextVerticalType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextVerticalType sTTextVerticalType2 = null;
            sTTextVerticalType2 = (STTextVerticalType)((Object)this.get_store().find_attribute_user(VERT$24));
            if (sTTextVerticalType2 == null) {
                sTTextVerticalType2 = (STTextVerticalType)((Object)this.get_store().add_attribute_user(VERT$24));
            }
            sTTextVerticalType2.set(sTTextVerticalType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetVert() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(VERT$24);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextWrappingType.Enum getWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(WRAP$26));
            if (simpleValue == null) {
                return null;
            }
            return (STTextWrappingType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextWrappingType xgetWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextWrappingType sTTextWrappingType = null;
            sTTextWrappingType = (STTextWrappingType)((Object)this.get_store().find_attribute_user(WRAP$26));
            return sTTextWrappingType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(WRAP$26) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setWrap(STTextWrappingType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(WRAP$26));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(WRAP$26));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetWrap(STTextWrappingType sTTextWrappingType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextWrappingType sTTextWrappingType2 = null;
            sTTextWrappingType2 = (STTextWrappingType)((Object)this.get_store().find_attribute_user(WRAP$26));
            if (sTTextWrappingType2 == null) {
                sTTextWrappingType2 = (STTextWrappingType)((Object)this.get_store().add_attribute_user(WRAP$26));
            }
            sTTextWrappingType2.set(sTTextWrappingType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWrap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(WRAP$26);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getLIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LINS$28));
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
    public STCoordinate32 xgetLIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate32 = null;
            sTCoordinate32 = (STCoordinate32)((Object)this.get_store().find_attribute_user(LINS$28));
            return sTCoordinate32;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(LINS$28) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLIns(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LINS$28));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LINS$28));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLIns(STCoordinate32 sTCoordinate32) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate322 = null;
            sTCoordinate322 = (STCoordinate32)((Object)this.get_store().find_attribute_user(LINS$28));
            if (sTCoordinate322 == null) {
                sTCoordinate322 = (STCoordinate32)((Object)this.get_store().add_attribute_user(LINS$28));
            }
            sTCoordinate322.set(sTCoordinate32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(LINS$28);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getTIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TINS$30));
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
    public STCoordinate32 xgetTIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate32 = null;
            sTCoordinate32 = (STCoordinate32)((Object)this.get_store().find_attribute_user(TINS$30));
            return sTCoordinate32;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TINS$30) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTIns(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TINS$30));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TINS$30));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTIns(STCoordinate32 sTCoordinate32) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate322 = null;
            sTCoordinate322 = (STCoordinate32)((Object)this.get_store().find_attribute_user(TINS$30));
            if (sTCoordinate322 == null) {
                sTCoordinate322 = (STCoordinate32)((Object)this.get_store().add_attribute_user(TINS$30));
            }
            sTCoordinate322.set(sTCoordinate32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TINS$30);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getRIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RINS$32));
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
    public STCoordinate32 xgetRIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate32 = null;
            sTCoordinate32 = (STCoordinate32)((Object)this.get_store().find_attribute_user(RINS$32));
            return sTCoordinate32;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(RINS$32) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRIns(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RINS$32));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RINS$32));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRIns(STCoordinate32 sTCoordinate32) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate322 = null;
            sTCoordinate322 = (STCoordinate32)((Object)this.get_store().find_attribute_user(RINS$32));
            if (sTCoordinate322 == null) {
                sTCoordinate322 = (STCoordinate32)((Object)this.get_store().add_attribute_user(RINS$32));
            }
            sTCoordinate322.set(sTCoordinate32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(RINS$32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getBIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BINS$34));
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
    public STCoordinate32 xgetBIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate32 = null;
            sTCoordinate32 = (STCoordinate32)((Object)this.get_store().find_attribute_user(BINS$34));
            return sTCoordinate32;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(BINS$34) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBIns(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BINS$34));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BINS$34));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBIns(STCoordinate32 sTCoordinate32) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate322 = null;
            sTCoordinate322 = (STCoordinate32)((Object)this.get_store().find_attribute_user(BINS$34));
            if (sTCoordinate322 == null) {
                sTCoordinate322 = (STCoordinate32)((Object)this.get_store().add_attribute_user(BINS$34));
            }
            sTCoordinate322.set(sTCoordinate32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBIns() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(BINS$34);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getNumCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NUMCOL$36));
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
    public STTextColumnCount xgetNumCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextColumnCount sTTextColumnCount = null;
            sTTextColumnCount = (STTextColumnCount)this.get_store().find_attribute_user(NUMCOL$36);
            return sTTextColumnCount;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNumCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(NUMCOL$36) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNumCol(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NUMCOL$36));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(NUMCOL$36));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetNumCol(STTextColumnCount sTTextColumnCount) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextColumnCount sTTextColumnCount2 = null;
            sTTextColumnCount2 = (STTextColumnCount)this.get_store().find_attribute_user(NUMCOL$36);
            if (sTTextColumnCount2 == null) {
                sTTextColumnCount2 = (STTextColumnCount)this.get_store().add_attribute_user(NUMCOL$36);
            }
            sTTextColumnCount2.set((XmlObject)sTTextColumnCount);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNumCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(NUMCOL$36);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getSpcCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SPCCOL$38));
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
    public STPositiveCoordinate32 xgetSpcCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate32 sTPositiveCoordinate32 = null;
            sTPositiveCoordinate32 = (STPositiveCoordinate32)((Object)this.get_store().find_attribute_user(SPCCOL$38));
            return sTPositiveCoordinate32;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpcCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SPCCOL$38) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSpcCol(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SPCCOL$38));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SPCCOL$38));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSpcCol(STPositiveCoordinate32 sTPositiveCoordinate32) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate32 sTPositiveCoordinate322 = null;
            sTPositiveCoordinate322 = (STPositiveCoordinate32)((Object)this.get_store().find_attribute_user(SPCCOL$38));
            if (sTPositiveCoordinate322 == null) {
                sTPositiveCoordinate322 = (STPositiveCoordinate32)((Object)this.get_store().add_attribute_user(SPCCOL$38));
            }
            sTPositiveCoordinate322.set(sTPositiveCoordinate32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpcCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SPCCOL$38);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getRtlCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RTLCOL$40));
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
    public XmlBoolean xgetRtlCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(RTLCOL$40));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRtlCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(RTLCOL$40) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRtlCol(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RTLCOL$40));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RTLCOL$40));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRtlCol(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(RTLCOL$40));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(RTLCOL$40));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRtlCol() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(RTLCOL$40);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getFromWordArt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FROMWORDART$42));
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
    public XmlBoolean xgetFromWordArt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(FROMWORDART$42));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFromWordArt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FROMWORDART$42) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFromWordArt(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FROMWORDART$42));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FROMWORDART$42));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFromWordArt(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(FROMWORDART$42));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(FROMWORDART$42));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFromWordArt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FROMWORDART$42);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextAnchoringType.Enum getAnchor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ANCHOR$44));
            if (simpleValue == null) {
                return null;
            }
            return (STTextAnchoringType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextAnchoringType xgetAnchor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextAnchoringType sTTextAnchoringType = null;
            sTTextAnchoringType = (STTextAnchoringType)((Object)this.get_store().find_attribute_user(ANCHOR$44));
            return sTTextAnchoringType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAnchor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ANCHOR$44) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAnchor(STTextAnchoringType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ANCHOR$44));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ANCHOR$44));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAnchor(STTextAnchoringType sTTextAnchoringType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextAnchoringType sTTextAnchoringType2 = null;
            sTTextAnchoringType2 = (STTextAnchoringType)((Object)this.get_store().find_attribute_user(ANCHOR$44));
            if (sTTextAnchoringType2 == null) {
                sTTextAnchoringType2 = (STTextAnchoringType)((Object)this.get_store().add_attribute_user(ANCHOR$44));
            }
            sTTextAnchoringType2.set(sTTextAnchoringType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAnchor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ANCHOR$44);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getAnchorCtr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ANCHORCTR$46));
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
    public XmlBoolean xgetAnchorCtr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(ANCHORCTR$46));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAnchorCtr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ANCHORCTR$46) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAnchorCtr(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ANCHORCTR$46));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ANCHORCTR$46));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAnchorCtr(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(ANCHORCTR$46));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(ANCHORCTR$46));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAnchorCtr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ANCHORCTR$46);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getForceAA() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FORCEAA$48));
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
    public XmlBoolean xgetForceAA() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(FORCEAA$48));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetForceAA() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FORCEAA$48) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setForceAA(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FORCEAA$48));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FORCEAA$48));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetForceAA(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(FORCEAA$48));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(FORCEAA$48));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetForceAA() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FORCEAA$48);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getUpright() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(UPRIGHT$50));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(UPRIGHT$50));
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
    public XmlBoolean xgetUpright() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(UPRIGHT$50));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(UPRIGHT$50);
            }
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUpright() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(UPRIGHT$50) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setUpright(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(UPRIGHT$50));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(UPRIGHT$50));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUpright(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(UPRIGHT$50));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(UPRIGHT$50));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUpright() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(UPRIGHT$50);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getCompatLnSpc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COMPATLNSPC$52));
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
    public XmlBoolean xgetCompatLnSpc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(COMPATLNSPC$52));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCompatLnSpc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(COMPATLNSPC$52) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCompatLnSpc(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COMPATLNSPC$52));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(COMPATLNSPC$52));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCompatLnSpc(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(COMPATLNSPC$52));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(COMPATLNSPC$52));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCompatLnSpc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(COMPATLNSPC$52);
        }
    }
}

