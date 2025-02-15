/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTTwoCellAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STEditAs;

public class CTTwoCellAnchorImpl
extends XmlComplexContentImpl
implements CTTwoCellAnchor {
    private static final long serialVersionUID = 1L;
    private static final QName FROM$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "from");
    private static final QName TO$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "to");
    private static final QName SP$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "sp");
    private static final QName GRPSP$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "grpSp");
    private static final QName GRAPHICFRAME$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "graphicFrame");
    private static final QName CXNSP$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "cxnSp");
    private static final QName PIC$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "pic");
    private static final QName CLIENTDATA$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "clientData");
    private static final QName EDITAS$16 = new QName("", "editAs");

    public CTTwoCellAnchorImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarker getFrom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarker cTMarker = null;
            cTMarker = (CTMarker)((Object)this.get_store().find_element_user(FROM$0, 0));
            if (cTMarker == null) {
                return null;
            }
            return cTMarker;
        }
    }

    @Override
    public void setFrom(CTMarker cTMarker) {
        this.generatedSetterHelperImpl(cTMarker, FROM$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarker addNewFrom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarker cTMarker = null;
            cTMarker = (CTMarker)((Object)this.get_store().add_element_user(FROM$0));
            return cTMarker;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarker getTo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarker cTMarker = null;
            cTMarker = (CTMarker)((Object)this.get_store().find_element_user(TO$2, 0));
            if (cTMarker == null) {
                return null;
            }
            return cTMarker;
        }
    }

    @Override
    public void setTo(CTMarker cTMarker) {
        this.generatedSetterHelperImpl(cTMarker, TO$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMarker addNewTo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMarker cTMarker = null;
            cTMarker = (CTMarker)((Object)this.get_store().add_element_user(TO$2));
            return cTMarker;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShape getSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShape cTShape = null;
            cTShape = (CTShape)((Object)this.get_store().find_element_user(SP$4, 0));
            if (cTShape == null) {
                return null;
            }
            return cTShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SP$4) != 0;
        }
    }

    @Override
    public void setSp(CTShape cTShape) {
        this.generatedSetterHelperImpl(cTShape, SP$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShape addNewSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShape cTShape = null;
            cTShape = (CTShape)((Object)this.get_store().add_element_user(SP$4));
            return cTShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SP$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupShape getGrpSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupShape cTGroupShape = null;
            cTGroupShape = (CTGroupShape)((Object)this.get_store().find_element_user(GRPSP$6, 0));
            if (cTGroupShape == null) {
                return null;
            }
            return cTGroupShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGrpSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRPSP$6) != 0;
        }
    }

    @Override
    public void setGrpSp(CTGroupShape cTGroupShape) {
        this.generatedSetterHelperImpl(cTGroupShape, GRPSP$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupShape addNewGrpSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupShape cTGroupShape = null;
            cTGroupShape = (CTGroupShape)((Object)this.get_store().add_element_user(GRPSP$6));
            return cTGroupShape;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGrpSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRPSP$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGraphicalObjectFrame getGraphicFrame() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGraphicalObjectFrame cTGraphicalObjectFrame = null;
            cTGraphicalObjectFrame = (CTGraphicalObjectFrame)((Object)this.get_store().find_element_user(GRAPHICFRAME$8, 0));
            if (cTGraphicalObjectFrame == null) {
                return null;
            }
            return cTGraphicalObjectFrame;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGraphicFrame() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRAPHICFRAME$8) != 0;
        }
    }

    @Override
    public void setGraphicFrame(CTGraphicalObjectFrame cTGraphicalObjectFrame) {
        this.generatedSetterHelperImpl(cTGraphicalObjectFrame, GRAPHICFRAME$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGraphicalObjectFrame addNewGraphicFrame() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGraphicalObjectFrame cTGraphicalObjectFrame = null;
            cTGraphicalObjectFrame = (CTGraphicalObjectFrame)((Object)this.get_store().add_element_user(GRAPHICFRAME$8));
            return cTGraphicalObjectFrame;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGraphicFrame() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRAPHICFRAME$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnector getCxnSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnector cTConnector = null;
            cTConnector = (CTConnector)((Object)this.get_store().find_element_user(CXNSP$10, 0));
            if (cTConnector == null) {
                return null;
            }
            return cTConnector;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCxnSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CXNSP$10) != 0;
        }
    }

    @Override
    public void setCxnSp(CTConnector cTConnector) {
        this.generatedSetterHelperImpl(cTConnector, CXNSP$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnector addNewCxnSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnector cTConnector = null;
            cTConnector = (CTConnector)((Object)this.get_store().add_element_user(CXNSP$10));
            return cTConnector;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCxnSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CXNSP$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPicture getPic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPicture cTPicture = null;
            cTPicture = (CTPicture)((Object)this.get_store().find_element_user(PIC$12, 0));
            if (cTPicture == null) {
                return null;
            }
            return cTPicture;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PIC$12) != 0;
        }
    }

    @Override
    public void setPic(CTPicture cTPicture) {
        this.generatedSetterHelperImpl(cTPicture, PIC$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPicture addNewPic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPicture cTPicture = null;
            cTPicture = (CTPicture)((Object)this.get_store().add_element_user(PIC$12));
            return cTPicture;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PIC$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAnchorClientData getClientData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAnchorClientData cTAnchorClientData = null;
            cTAnchorClientData = (CTAnchorClientData)((Object)this.get_store().find_element_user(CLIENTDATA$14, 0));
            if (cTAnchorClientData == null) {
                return null;
            }
            return cTAnchorClientData;
        }
    }

    @Override
    public void setClientData(CTAnchorClientData cTAnchorClientData) {
        this.generatedSetterHelperImpl(cTAnchorClientData, CLIENTDATA$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAnchorClientData addNewClientData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAnchorClientData cTAnchorClientData = null;
            cTAnchorClientData = (CTAnchorClientData)((Object)this.get_store().add_element_user(CLIENTDATA$14));
            return cTAnchorClientData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STEditAs.Enum getEditAs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EDITAS$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(EDITAS$16));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STEditAs.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STEditAs xgetEditAs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STEditAs sTEditAs = null;
            sTEditAs = (STEditAs)((Object)this.get_store().find_attribute_user(EDITAS$16));
            if (sTEditAs == null) {
                sTEditAs = (STEditAs)this.get_default_attribute_value(EDITAS$16);
            }
            return sTEditAs;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEditAs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(EDITAS$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setEditAs(STEditAs.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(EDITAS$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(EDITAS$16));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetEditAs(STEditAs sTEditAs) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STEditAs sTEditAs2 = null;
            sTEditAs2 = (STEditAs)((Object)this.get_store().find_attribute_user(EDITAS$16));
            if (sTEditAs2 == null) {
                sTEditAs2 = (STEditAs)((Object)this.get_store().add_attribute_user(EDITAS$16));
            }
            sTEditAs2.set(sTEditAs);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEditAs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(EDITAS$16);
        }
    }
}

