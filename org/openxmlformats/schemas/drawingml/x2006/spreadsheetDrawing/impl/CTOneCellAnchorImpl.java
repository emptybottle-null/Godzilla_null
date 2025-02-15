/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTOneCellAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;

public class CTOneCellAnchorImpl
extends XmlComplexContentImpl
implements CTOneCellAnchor {
    private static final long serialVersionUID = 1L;
    private static final QName FROM$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "from");
    private static final QName EXT$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "ext");
    private static final QName SP$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "sp");
    private static final QName GRPSP$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "grpSp");
    private static final QName GRAPHICFRAME$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "graphicFrame");
    private static final QName CXNSP$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "cxnSp");
    private static final QName PIC$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "pic");
    private static final QName CLIENTDATA$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "clientData");

    public CTOneCellAnchorImpl(SchemaType schemaType) {
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
    public CTPositiveSize2D getExt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveSize2D cTPositiveSize2D = null;
            cTPositiveSize2D = (CTPositiveSize2D)((Object)this.get_store().find_element_user(EXT$2, 0));
            if (cTPositiveSize2D == null) {
                return null;
            }
            return cTPositiveSize2D;
        }
    }

    @Override
    public void setExt(CTPositiveSize2D cTPositiveSize2D) {
        this.generatedSetterHelperImpl(cTPositiveSize2D, EXT$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveSize2D addNewExt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveSize2D cTPositiveSize2D = null;
            cTPositiveSize2D = (CTPositiveSize2D)((Object)this.get_store().add_element_user(EXT$2));
            return cTPositiveSize2D;
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
}

