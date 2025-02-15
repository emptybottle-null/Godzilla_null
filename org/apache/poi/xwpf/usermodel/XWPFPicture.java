/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;

public class XWPFPicture {
    private CTPicture ctPic;
    private String description;
    private XWPFRun run;

    public XWPFPicture(CTPicture ctPic, XWPFRun run) {
        this.run = run;
        this.ctPic = ctPic;
        this.description = ctPic.getNvPicPr().getCNvPr().getDescr();
    }

    public void setPictureReference(PackageRelationship rel) {
        this.ctPic.getBlipFill().getBlip().setEmbed(rel.getId());
    }

    public CTPicture getCTPicture() {
        return this.ctPic;
    }

    public XWPFPictureData getPictureData() {
        POIXMLDocumentPart relatedPart;
        CTBlipFillProperties blipProps = this.ctPic.getBlipFill();
        if (blipProps == null || !blipProps.isSetBlip()) {
            return null;
        }
        String blipId = blipProps.getBlip().getEmbed();
        POIXMLDocumentPart part = this.run.getParent().getPart();
        if (part != null && (relatedPart = part.getRelationById(blipId)) instanceof XWPFPictureData) {
            return (XWPFPictureData)relatedPart;
        }
        return null;
    }

    public double getWidth() {
        return Units.toPoints(this.ctPic.getSpPr().getXfrm().getExt().getCx());
    }

    public double getDepth() {
        return Units.toPoints(this.ctPic.getSpPr().getXfrm().getExt().getCy());
    }

    public String getDescription() {
        return this.description;
    }
}

