/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDashStopList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinBevel;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinMiterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinRound;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetLineDashProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineCap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineWidth;
import org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment;

public class CTLinePropertiesImpl
extends XmlComplexContentImpl
implements CTLineProperties {
    private static final long serialVersionUID = 1L;
    private static final QName NOFILL$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "noFill");
    private static final QName SOLIDFILL$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "solidFill");
    private static final QName GRADFILL$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "gradFill");
    private static final QName PATTFILL$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "pattFill");
    private static final QName PRSTDASH$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "prstDash");
    private static final QName CUSTDASH$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "custDash");
    private static final QName ROUND$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "round");
    private static final QName BEVEL$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "bevel");
    private static final QName MITER$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "miter");
    private static final QName HEADEND$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "headEnd");
    private static final QName TAILEND$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tailEnd");
    private static final QName EXTLST$22 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");
    private static final QName W$24 = new QName("", "w");
    private static final QName CAP$26 = new QName("", "cap");
    private static final QName CMPD$28 = new QName("", "cmpd");
    private static final QName ALGN$30 = new QName("", "algn");

    public CTLinePropertiesImpl(SchemaType schemaType) {
        super(schemaType);
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
            cTNoFillProperties = (CTNoFillProperties)((Object)this.get_store().find_element_user(NOFILL$0, 0));
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
            return this.get_store().count_elements(NOFILL$0) != 0;
        }
    }

    @Override
    public void setNoFill(CTNoFillProperties cTNoFillProperties) {
        this.generatedSetterHelperImpl(cTNoFillProperties, NOFILL$0, 0, (short)1);
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
            cTNoFillProperties = (CTNoFillProperties)((Object)this.get_store().add_element_user(NOFILL$0));
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
            this.get_store().remove_element(NOFILL$0, 0);
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
            cTSolidColorFillProperties = (CTSolidColorFillProperties)((Object)this.get_store().find_element_user(SOLIDFILL$2, 0));
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
            return this.get_store().count_elements(SOLIDFILL$2) != 0;
        }
    }

    @Override
    public void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties) {
        this.generatedSetterHelperImpl(cTSolidColorFillProperties, SOLIDFILL$2, 0, (short)1);
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
            cTSolidColorFillProperties = (CTSolidColorFillProperties)((Object)this.get_store().add_element_user(SOLIDFILL$2));
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
            this.get_store().remove_element(SOLIDFILL$2, 0);
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
            cTGradientFillProperties = (CTGradientFillProperties)((Object)this.get_store().find_element_user(GRADFILL$4, 0));
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
            return this.get_store().count_elements(GRADFILL$4) != 0;
        }
    }

    @Override
    public void setGradFill(CTGradientFillProperties cTGradientFillProperties) {
        this.generatedSetterHelperImpl(cTGradientFillProperties, GRADFILL$4, 0, (short)1);
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
            cTGradientFillProperties = (CTGradientFillProperties)((Object)this.get_store().add_element_user(GRADFILL$4));
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
            this.get_store().remove_element(GRADFILL$4, 0);
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
            cTPatternFillProperties = (CTPatternFillProperties)((Object)this.get_store().find_element_user(PATTFILL$6, 0));
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
            return this.get_store().count_elements(PATTFILL$6) != 0;
        }
    }

    @Override
    public void setPattFill(CTPatternFillProperties cTPatternFillProperties) {
        this.generatedSetterHelperImpl(cTPatternFillProperties, PATTFILL$6, 0, (short)1);
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
            cTPatternFillProperties = (CTPatternFillProperties)((Object)this.get_store().add_element_user(PATTFILL$6));
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
            this.get_store().remove_element(PATTFILL$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresetLineDashProperties getPrstDash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresetLineDashProperties cTPresetLineDashProperties = null;
            cTPresetLineDashProperties = (CTPresetLineDashProperties)((Object)this.get_store().find_element_user(PRSTDASH$8, 0));
            if (cTPresetLineDashProperties == null) {
                return null;
            }
            return cTPresetLineDashProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPrstDash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PRSTDASH$8) != 0;
        }
    }

    @Override
    public void setPrstDash(CTPresetLineDashProperties cTPresetLineDashProperties) {
        this.generatedSetterHelperImpl(cTPresetLineDashProperties, PRSTDASH$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPresetLineDashProperties addNewPrstDash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPresetLineDashProperties cTPresetLineDashProperties = null;
            cTPresetLineDashProperties = (CTPresetLineDashProperties)((Object)this.get_store().add_element_user(PRSTDASH$8));
            return cTPresetLineDashProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPrstDash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PRSTDASH$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDashStopList getCustDash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDashStopList cTDashStopList = null;
            cTDashStopList = (CTDashStopList)((Object)this.get_store().find_element_user(CUSTDASH$10, 0));
            if (cTDashStopList == null) {
                return null;
            }
            return cTDashStopList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCustDash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTDASH$10) != 0;
        }
    }

    @Override
    public void setCustDash(CTDashStopList cTDashStopList) {
        this.generatedSetterHelperImpl(cTDashStopList, CUSTDASH$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDashStopList addNewCustDash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDashStopList cTDashStopList = null;
            cTDashStopList = (CTDashStopList)((Object)this.get_store().add_element_user(CUSTDASH$10));
            return cTDashStopList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCustDash() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTDASH$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineJoinRound getRound() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineJoinRound cTLineJoinRound = null;
            cTLineJoinRound = (CTLineJoinRound)((Object)this.get_store().find_element_user(ROUND$12, 0));
            if (cTLineJoinRound == null) {
                return null;
            }
            return cTLineJoinRound;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRound() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ROUND$12) != 0;
        }
    }

    @Override
    public void setRound(CTLineJoinRound cTLineJoinRound) {
        this.generatedSetterHelperImpl(cTLineJoinRound, ROUND$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineJoinRound addNewRound() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineJoinRound cTLineJoinRound = null;
            cTLineJoinRound = (CTLineJoinRound)((Object)this.get_store().add_element_user(ROUND$12));
            return cTLineJoinRound;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRound() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ROUND$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineJoinBevel getBevel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineJoinBevel cTLineJoinBevel = null;
            cTLineJoinBevel = (CTLineJoinBevel)((Object)this.get_store().find_element_user(BEVEL$14, 0));
            if (cTLineJoinBevel == null) {
                return null;
            }
            return cTLineJoinBevel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBevel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BEVEL$14) != 0;
        }
    }

    @Override
    public void setBevel(CTLineJoinBevel cTLineJoinBevel) {
        this.generatedSetterHelperImpl(cTLineJoinBevel, BEVEL$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineJoinBevel addNewBevel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineJoinBevel cTLineJoinBevel = null;
            cTLineJoinBevel = (CTLineJoinBevel)((Object)this.get_store().add_element_user(BEVEL$14));
            return cTLineJoinBevel;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBevel() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BEVEL$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineJoinMiterProperties getMiter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineJoinMiterProperties cTLineJoinMiterProperties = null;
            cTLineJoinMiterProperties = (CTLineJoinMiterProperties)((Object)this.get_store().find_element_user(MITER$16, 0));
            if (cTLineJoinMiterProperties == null) {
                return null;
            }
            return cTLineJoinMiterProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMiter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MITER$16) != 0;
        }
    }

    @Override
    public void setMiter(CTLineJoinMiterProperties cTLineJoinMiterProperties) {
        this.generatedSetterHelperImpl(cTLineJoinMiterProperties, MITER$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineJoinMiterProperties addNewMiter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineJoinMiterProperties cTLineJoinMiterProperties = null;
            cTLineJoinMiterProperties = (CTLineJoinMiterProperties)((Object)this.get_store().add_element_user(MITER$16));
            return cTLineJoinMiterProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMiter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MITER$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineEndProperties getHeadEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineEndProperties cTLineEndProperties = null;
            cTLineEndProperties = (CTLineEndProperties)((Object)this.get_store().find_element_user(HEADEND$18, 0));
            if (cTLineEndProperties == null) {
                return null;
            }
            return cTLineEndProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHeadEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HEADEND$18) != 0;
        }
    }

    @Override
    public void setHeadEnd(CTLineEndProperties cTLineEndProperties) {
        this.generatedSetterHelperImpl(cTLineEndProperties, HEADEND$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineEndProperties addNewHeadEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineEndProperties cTLineEndProperties = null;
            cTLineEndProperties = (CTLineEndProperties)((Object)this.get_store().add_element_user(HEADEND$18));
            return cTLineEndProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHeadEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HEADEND$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineEndProperties getTailEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineEndProperties cTLineEndProperties = null;
            cTLineEndProperties = (CTLineEndProperties)((Object)this.get_store().find_element_user(TAILEND$20, 0));
            if (cTLineEndProperties == null) {
                return null;
            }
            return cTLineEndProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTailEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TAILEND$20) != 0;
        }
    }

    @Override
    public void setTailEnd(CTLineEndProperties cTLineEndProperties) {
        this.generatedSetterHelperImpl(cTLineEndProperties, TAILEND$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLineEndProperties addNewTailEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLineEndProperties cTLineEndProperties = null;
            cTLineEndProperties = (CTLineEndProperties)((Object)this.get_store().add_element_user(TAILEND$20));
            return cTLineEndProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTailEnd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TAILEND$20, 0);
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
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$22, 0));
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
            return this.get_store().count_elements(EXTLST$22) != 0;
        }
    }

    @Override
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$22, 0, (short)1);
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
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$22));
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
            this.get_store().remove_element(EXTLST$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(W$24));
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
    public STLineWidth xgetW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLineWidth sTLineWidth = null;
            sTLineWidth = (STLineWidth)((Object)this.get_store().find_attribute_user(W$24));
            return sTLineWidth;
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
            return this.get_store().find_attribute_user(W$24) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setW(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(W$24));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(W$24));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetW(STLineWidth sTLineWidth) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLineWidth sTLineWidth2 = null;
            sTLineWidth2 = (STLineWidth)((Object)this.get_store().find_attribute_user(W$24));
            if (sTLineWidth2 == null) {
                sTLineWidth2 = (STLineWidth)((Object)this.get_store().add_attribute_user(W$24));
            }
            sTLineWidth2.set(sTLineWidth);
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
            this.get_store().remove_attribute(W$24);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLineCap.Enum getCap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CAP$26));
            if (simpleValue == null) {
                return null;
            }
            return (STLineCap.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLineCap xgetCap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLineCap sTLineCap = null;
            sTLineCap = (STLineCap)((Object)this.get_store().find_attribute_user(CAP$26));
            return sTLineCap;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CAP$26) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCap(STLineCap.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CAP$26));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CAP$26));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCap(STLineCap sTLineCap) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLineCap sTLineCap2 = null;
            sTLineCap2 = (STLineCap)((Object)this.get_store().find_attribute_user(CAP$26));
            if (sTLineCap2 == null) {
                sTLineCap2 = (STLineCap)((Object)this.get_store().add_attribute_user(CAP$26));
            }
            sTLineCap2.set(sTLineCap);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CAP$26);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCompoundLine.Enum getCmpd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CMPD$28));
            if (simpleValue == null) {
                return null;
            }
            return (STCompoundLine.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCompoundLine xgetCmpd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCompoundLine sTCompoundLine = null;
            sTCompoundLine = (STCompoundLine)((Object)this.get_store().find_attribute_user(CMPD$28));
            return sTCompoundLine;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCmpd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CMPD$28) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCmpd(STCompoundLine.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CMPD$28));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CMPD$28));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCmpd(STCompoundLine sTCompoundLine) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCompoundLine sTCompoundLine2 = null;
            sTCompoundLine2 = (STCompoundLine)((Object)this.get_store().find_attribute_user(CMPD$28));
            if (sTCompoundLine2 == null) {
                sTCompoundLine2 = (STCompoundLine)((Object)this.get_store().add_attribute_user(CMPD$28));
            }
            sTCompoundLine2.set(sTCompoundLine);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCmpd() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CMPD$28);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPenAlignment.Enum getAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGN$30));
            if (simpleValue == null) {
                return null;
            }
            return (STPenAlignment.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPenAlignment xgetAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPenAlignment sTPenAlignment = null;
            sTPenAlignment = (STPenAlignment)((Object)this.get_store().find_attribute_user(ALGN$30));
            return sTPenAlignment;
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
            return this.get_store().find_attribute_user(ALGN$30) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAlgn(STPenAlignment.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGN$30));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALGN$30));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAlgn(STPenAlignment sTPenAlignment) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPenAlignment sTPenAlignment2 = null;
            sTPenAlignment2 = (STPenAlignment)((Object)this.get_store().find_attribute_user(ALGN$30));
            if (sTPenAlignment2 == null) {
                sTPenAlignment2 = (STPenAlignment)((Object)this.get_store().add_attribute_user(ALGN$30));
            }
            sTPenAlignment2.set(sTPenAlignment);
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
            this.get_store().remove_attribute(ALGN$30);
        }
    }
}

