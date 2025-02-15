/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTCell3D
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCell3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextHorzOverflowType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVerticalType;

public class CTTableCellPropertiesImpl
extends XmlComplexContentImpl
implements CTTableCellProperties {
    private static final long serialVersionUID = 1L;
    private static final QName LNL$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lnL");
    private static final QName LNR$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lnR");
    private static final QName LNT$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lnT");
    private static final QName LNB$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lnB");
    private static final QName LNTLTOBR$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lnTlToBr");
    private static final QName LNBLTOTR$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lnBlToTr");
    private static final QName CELL3D$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "cell3D");
    private static final QName NOFILL$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "noFill");
    private static final QName SOLIDFILL$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "solidFill");
    private static final QName GRADFILL$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "gradFill");
    private static final QName BLIPFILL$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "blipFill");
    private static final QName PATTFILL$22 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "pattFill");
    private static final QName GRPFILL$24 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "grpFill");
    private static final QName EXTLST$26 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");
    private static final QName MARL$28 = new QName("", "marL");
    private static final QName MARR$30 = new QName("", "marR");
    private static final QName MART$32 = new QName("", "marT");
    private static final QName MARB$34 = new QName("", "marB");
    private static final QName VERT$36 = new QName("", "vert");
    private static final QName ANCHOR$38 = new QName("", "anchor");
    private static final QName ANCHORCTR$40 = new QName("", "anchorCtr");
    private static final QName HORZOVERFLOW$42 = new QName("", "horzOverflow");

    public CTTableCellPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineProperties getLnL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineProperties cTLineProperties = null;
            cTLineProperties = (CTLineProperties)((Object)this.get_store().find_element_user(LNL$0, 0));
            if (cTLineProperties == null) {
                return null;
            }
            return cTLineProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLnL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LNL$0) != 0;
        }
    }

    @Override
    public void setLnL(CTLineProperties cTLineProperties) {
        this.generatedSetterHelperImpl(cTLineProperties, LNL$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineProperties addNewLnL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineProperties cTLineProperties = null;
            cTLineProperties = (CTLineProperties)((Object)this.get_store().add_element_user(LNL$0));
            return cTLineProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLnL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LNL$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineProperties getLnR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineProperties cTLineProperties = null;
            cTLineProperties = (CTLineProperties)((Object)this.get_store().find_element_user(LNR$2, 0));
            if (cTLineProperties == null) {
                return null;
            }
            return cTLineProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLnR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LNR$2) != 0;
        }
    }

    @Override
    public void setLnR(CTLineProperties cTLineProperties) {
        this.generatedSetterHelperImpl(cTLineProperties, LNR$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineProperties addNewLnR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineProperties cTLineProperties = null;
            cTLineProperties = (CTLineProperties)((Object)this.get_store().add_element_user(LNR$2));
            return cTLineProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLnR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LNR$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineProperties getLnT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineProperties cTLineProperties = null;
            cTLineProperties = (CTLineProperties)((Object)this.get_store().find_element_user(LNT$4, 0));
            if (cTLineProperties == null) {
                return null;
            }
            return cTLineProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLnT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LNT$4) != 0;
        }
    }

    @Override
    public void setLnT(CTLineProperties cTLineProperties) {
        this.generatedSetterHelperImpl(cTLineProperties, LNT$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineProperties addNewLnT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineProperties cTLineProperties = null;
            cTLineProperties = (CTLineProperties)((Object)this.get_store().add_element_user(LNT$4));
            return cTLineProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLnT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LNT$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineProperties getLnB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineProperties cTLineProperties = null;
            cTLineProperties = (CTLineProperties)((Object)this.get_store().find_element_user(LNB$6, 0));
            if (cTLineProperties == null) {
                return null;
            }
            return cTLineProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLnB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LNB$6) != 0;
        }
    }

    @Override
    public void setLnB(CTLineProperties cTLineProperties) {
        this.generatedSetterHelperImpl(cTLineProperties, LNB$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineProperties addNewLnB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineProperties cTLineProperties = null;
            cTLineProperties = (CTLineProperties)((Object)this.get_store().add_element_user(LNB$6));
            return cTLineProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLnB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LNB$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineProperties getLnTlToBr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineProperties cTLineProperties = null;
            cTLineProperties = (CTLineProperties)((Object)this.get_store().find_element_user(LNTLTOBR$8, 0));
            if (cTLineProperties == null) {
                return null;
            }
            return cTLineProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLnTlToBr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LNTLTOBR$8) != 0;
        }
    }

    @Override
    public void setLnTlToBr(CTLineProperties cTLineProperties) {
        this.generatedSetterHelperImpl(cTLineProperties, LNTLTOBR$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineProperties addNewLnTlToBr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineProperties cTLineProperties = null;
            cTLineProperties = (CTLineProperties)((Object)this.get_store().add_element_user(LNTLTOBR$8));
            return cTLineProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLnTlToBr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LNTLTOBR$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineProperties getLnBlToTr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineProperties cTLineProperties = null;
            cTLineProperties = (CTLineProperties)((Object)this.get_store().find_element_user(LNBLTOTR$10, 0));
            if (cTLineProperties == null) {
                return null;
            }
            return cTLineProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLnBlToTr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LNBLTOTR$10) != 0;
        }
    }

    @Override
    public void setLnBlToTr(CTLineProperties cTLineProperties) {
        this.generatedSetterHelperImpl(cTLineProperties, LNBLTOTR$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineProperties addNewLnBlToTr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineProperties cTLineProperties = null;
            cTLineProperties = (CTLineProperties)((Object)this.get_store().add_element_user(LNBLTOTR$10));
            return cTLineProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLnBlToTr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LNBLTOTR$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCell3D getCell3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCell3D cTCell3D = null;
            cTCell3D = (CTCell3D)this.get_store().find_element_user(CELL3D$12, 0);
            if (cTCell3D == null) {
                return null;
            }
            return cTCell3D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCell3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CELL3D$12) != 0;
        }
    }

    @Override
    public void setCell3D(CTCell3D cTCell3D) {
        this.generatedSetterHelperImpl((XmlObject)cTCell3D, CELL3D$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCell3D addNewCell3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCell3D cTCell3D = null;
            cTCell3D = (CTCell3D)this.get_store().add_element_user(CELL3D$12);
            return cTCell3D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCell3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CELL3D$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNoFillProperties getNoFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNoFillProperties cTNoFillProperties = null;
            cTNoFillProperties = (CTNoFillProperties)((Object)this.get_store().find_element_user(NOFILL$14, 0));
            if (cTNoFillProperties == null) {
                return null;
            }
            return cTNoFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNoFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NOFILL$14) != 0;
        }
    }

    @Override
    public void setNoFill(CTNoFillProperties cTNoFillProperties) {
        this.generatedSetterHelperImpl(cTNoFillProperties, NOFILL$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNoFillProperties addNewNoFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNoFillProperties cTNoFillProperties = null;
            cTNoFillProperties = (CTNoFillProperties)((Object)this.get_store().add_element_user(NOFILL$14));
            return cTNoFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNoFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NOFILL$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSolidColorFillProperties getSolidFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSolidColorFillProperties cTSolidColorFillProperties = null;
            cTSolidColorFillProperties = (CTSolidColorFillProperties)((Object)this.get_store().find_element_user(SOLIDFILL$16, 0));
            if (cTSolidColorFillProperties == null) {
                return null;
            }
            return cTSolidColorFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSolidFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SOLIDFILL$16) != 0;
        }
    }

    @Override
    public void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties) {
        this.generatedSetterHelperImpl(cTSolidColorFillProperties, SOLIDFILL$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSolidColorFillProperties addNewSolidFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSolidColorFillProperties cTSolidColorFillProperties = null;
            cTSolidColorFillProperties = (CTSolidColorFillProperties)((Object)this.get_store().add_element_user(SOLIDFILL$16));
            return cTSolidColorFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSolidFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SOLIDFILL$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGradientFillProperties getGradFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGradientFillProperties cTGradientFillProperties = null;
            cTGradientFillProperties = (CTGradientFillProperties)((Object)this.get_store().find_element_user(GRADFILL$18, 0));
            if (cTGradientFillProperties == null) {
                return null;
            }
            return cTGradientFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGradFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRADFILL$18) != 0;
        }
    }

    @Override
    public void setGradFill(CTGradientFillProperties cTGradientFillProperties) {
        this.generatedSetterHelperImpl(cTGradientFillProperties, GRADFILL$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGradientFillProperties addNewGradFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGradientFillProperties cTGradientFillProperties = null;
            cTGradientFillProperties = (CTGradientFillProperties)((Object)this.get_store().add_element_user(GRADFILL$18));
            return cTGradientFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGradFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRADFILL$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlipFillProperties getBlipFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlipFillProperties cTBlipFillProperties = null;
            cTBlipFillProperties = (CTBlipFillProperties)((Object)this.get_store().find_element_user(BLIPFILL$20, 0));
            if (cTBlipFillProperties == null) {
                return null;
            }
            return cTBlipFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBlipFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BLIPFILL$20) != 0;
        }
    }

    @Override
    public void setBlipFill(CTBlipFillProperties cTBlipFillProperties) {
        this.generatedSetterHelperImpl(cTBlipFillProperties, BLIPFILL$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlipFillProperties addNewBlipFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlipFillProperties cTBlipFillProperties = null;
            cTBlipFillProperties = (CTBlipFillProperties)((Object)this.get_store().add_element_user(BLIPFILL$20));
            return cTBlipFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBlipFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BLIPFILL$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPatternFillProperties getPattFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPatternFillProperties cTPatternFillProperties = null;
            cTPatternFillProperties = (CTPatternFillProperties)((Object)this.get_store().find_element_user(PATTFILL$22, 0));
            if (cTPatternFillProperties == null) {
                return null;
            }
            return cTPatternFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPattFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PATTFILL$22) != 0;
        }
    }

    @Override
    public void setPattFill(CTPatternFillProperties cTPatternFillProperties) {
        this.generatedSetterHelperImpl(cTPatternFillProperties, PATTFILL$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPatternFillProperties addNewPattFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPatternFillProperties cTPatternFillProperties = null;
            cTPatternFillProperties = (CTPatternFillProperties)((Object)this.get_store().add_element_user(PATTFILL$22));
            return cTPatternFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPattFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PATTFILL$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupFillProperties getGrpFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupFillProperties cTGroupFillProperties = null;
            cTGroupFillProperties = (CTGroupFillProperties)((Object)this.get_store().find_element_user(GRPFILL$24, 0));
            if (cTGroupFillProperties == null) {
                return null;
            }
            return cTGroupFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGrpFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRPFILL$24) != 0;
        }
    }

    @Override
    public void setGrpFill(CTGroupFillProperties cTGroupFillProperties) {
        this.generatedSetterHelperImpl(cTGroupFillProperties, GRPFILL$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupFillProperties addNewGrpFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupFillProperties cTGroupFillProperties = null;
            cTGroupFillProperties = (CTGroupFillProperties)((Object)this.get_store().add_element_user(GRPFILL$24));
            return cTGroupFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGrpFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRPFILL$24, 0);
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
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$26, 0));
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
            return this.get_store().count_elements(EXTLST$26) != 0;
        }
    }

    @Override
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$26, 0, (short)1);
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
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$26));
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
            this.get_store().remove_element(EXTLST$26, 0);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MARL$28));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(MARL$28));
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
    public STCoordinate32 xgetMarL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate32 = null;
            sTCoordinate32 = (STCoordinate32)((Object)this.get_store().find_attribute_user(MARL$28));
            if (sTCoordinate32 == null) {
                sTCoordinate32 = (STCoordinate32)this.get_default_attribute_value(MARL$28);
            }
            return sTCoordinate32;
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
            return this.get_store().find_attribute_user(MARL$28) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MARL$28));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MARL$28));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMarL(STCoordinate32 sTCoordinate32) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate322 = null;
            sTCoordinate322 = (STCoordinate32)((Object)this.get_store().find_attribute_user(MARL$28));
            if (sTCoordinate322 == null) {
                sTCoordinate322 = (STCoordinate32)((Object)this.get_store().add_attribute_user(MARL$28));
            }
            sTCoordinate322.set(sTCoordinate32);
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
            this.get_store().remove_attribute(MARL$28);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MARR$30));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(MARR$30));
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
    public STCoordinate32 xgetMarR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate32 = null;
            sTCoordinate32 = (STCoordinate32)((Object)this.get_store().find_attribute_user(MARR$30));
            if (sTCoordinate32 == null) {
                sTCoordinate32 = (STCoordinate32)this.get_default_attribute_value(MARR$30);
            }
            return sTCoordinate32;
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
            return this.get_store().find_attribute_user(MARR$30) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MARR$30));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MARR$30));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMarR(STCoordinate32 sTCoordinate32) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate322 = null;
            sTCoordinate322 = (STCoordinate32)((Object)this.get_store().find_attribute_user(MARR$30));
            if (sTCoordinate322 == null) {
                sTCoordinate322 = (STCoordinate32)((Object)this.get_store().add_attribute_user(MARR$30));
            }
            sTCoordinate322.set(sTCoordinate32);
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
            this.get_store().remove_attribute(MARR$30);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getMarT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MART$32));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(MART$32));
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
    public STCoordinate32 xgetMarT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate32 = null;
            sTCoordinate32 = (STCoordinate32)((Object)this.get_store().find_attribute_user(MART$32));
            if (sTCoordinate32 == null) {
                sTCoordinate32 = (STCoordinate32)this.get_default_attribute_value(MART$32);
            }
            return sTCoordinate32;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMarT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MART$32) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMarT(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MART$32));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MART$32));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMarT(STCoordinate32 sTCoordinate32) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate322 = null;
            sTCoordinate322 = (STCoordinate32)((Object)this.get_store().find_attribute_user(MART$32));
            if (sTCoordinate322 == null) {
                sTCoordinate322 = (STCoordinate32)((Object)this.get_store().add_attribute_user(MART$32));
            }
            sTCoordinate322.set(sTCoordinate32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMarT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MART$32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getMarB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MARB$34));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(MARB$34));
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
    public STCoordinate32 xgetMarB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate32 = null;
            sTCoordinate32 = (STCoordinate32)((Object)this.get_store().find_attribute_user(MARB$34));
            if (sTCoordinate32 == null) {
                sTCoordinate32 = (STCoordinate32)this.get_default_attribute_value(MARB$34);
            }
            return sTCoordinate32;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMarB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(MARB$34) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setMarB(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(MARB$34));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(MARB$34));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetMarB(STCoordinate32 sTCoordinate32) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate322 = null;
            sTCoordinate322 = (STCoordinate32)((Object)this.get_store().find_attribute_user(MARB$34));
            if (sTCoordinate322 == null) {
                sTCoordinate322 = (STCoordinate32)((Object)this.get_store().add_attribute_user(MARB$34));
            }
            sTCoordinate322.set(sTCoordinate32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMarB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(MARB$34);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VERT$36));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(VERT$36));
            }
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
            sTTextVerticalType = (STTextVerticalType)((Object)this.get_store().find_attribute_user(VERT$36));
            if (sTTextVerticalType == null) {
                sTTextVerticalType = (STTextVerticalType)this.get_default_attribute_value(VERT$36);
            }
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
            return this.get_store().find_attribute_user(VERT$36) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VERT$36));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(VERT$36));
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
            sTTextVerticalType2 = (STTextVerticalType)((Object)this.get_store().find_attribute_user(VERT$36));
            if (sTTextVerticalType2 == null) {
                sTTextVerticalType2 = (STTextVerticalType)((Object)this.get_store().add_attribute_user(VERT$36));
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
            this.get_store().remove_attribute(VERT$36);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ANCHOR$38));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(ANCHOR$38));
            }
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
            sTTextAnchoringType = (STTextAnchoringType)((Object)this.get_store().find_attribute_user(ANCHOR$38));
            if (sTTextAnchoringType == null) {
                sTTextAnchoringType = (STTextAnchoringType)this.get_default_attribute_value(ANCHOR$38);
            }
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
            return this.get_store().find_attribute_user(ANCHOR$38) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ANCHOR$38));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ANCHOR$38));
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
            sTTextAnchoringType2 = (STTextAnchoringType)((Object)this.get_store().find_attribute_user(ANCHOR$38));
            if (sTTextAnchoringType2 == null) {
                sTTextAnchoringType2 = (STTextAnchoringType)((Object)this.get_store().add_attribute_user(ANCHOR$38));
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
            this.get_store().remove_attribute(ANCHOR$38);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ANCHORCTR$40));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(ANCHORCTR$40));
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
    public XmlBoolean xgetAnchorCtr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(ANCHORCTR$40));
            if (xmlBoolean == null) {
                xmlBoolean = (XmlBoolean)this.get_default_attribute_value(ANCHORCTR$40);
            }
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
            return this.get_store().find_attribute_user(ANCHORCTR$40) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ANCHORCTR$40));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ANCHORCTR$40));
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
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(ANCHORCTR$40));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(ANCHORCTR$40));
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
            this.get_store().remove_attribute(ANCHORCTR$40);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HORZOVERFLOW$42));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(HORZOVERFLOW$42));
            }
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
            sTTextHorzOverflowType = (STTextHorzOverflowType)((Object)this.get_store().find_attribute_user(HORZOVERFLOW$42));
            if (sTTextHorzOverflowType == null) {
                sTTextHorzOverflowType = (STTextHorzOverflowType)this.get_default_attribute_value(HORZOVERFLOW$42);
            }
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
            return this.get_store().find_attribute_user(HORZOVERFLOW$42) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HORZOVERFLOW$42));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HORZOVERFLOW$42));
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
            sTTextHorzOverflowType2 = (STTextHorzOverflowType)((Object)this.get_store().find_attribute_user(HORZOVERFLOW$42));
            if (sTTextHorzOverflowType2 == null) {
                sTTextHorzOverflowType2 = (STTextHorzOverflowType)((Object)this.get_store().add_attribute_user(HORZOVERFLOW$42));
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
            this.get_store().remove_attribute(HORZOVERFLOW$42);
        }
    }
}

