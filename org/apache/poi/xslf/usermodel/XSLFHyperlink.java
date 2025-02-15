/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.net.URI;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.sl.usermodel.Hyperlink;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.xslf.usermodel.XSLFRelation;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHyperlink;

public class XSLFHyperlink
implements Hyperlink<XSLFShape, XSLFTextParagraph> {
    private final XSLFSheet _sheet;
    private final CTHyperlink _link;

    XSLFHyperlink(CTHyperlink link, XSLFSheet sheet) {
        this._sheet = sheet;
        this._link = link;
    }

    @Internal
    public CTHyperlink getXmlObject() {
        return this._link;
    }

    @Override
    public void setAddress(String address) {
        this.linkToUrl(address);
    }

    @Override
    public String getAddress() {
        String id = this._link.getId();
        if (id == null || id.isEmpty()) {
            return this._link.getAction();
        }
        PackageRelationship rel = this._sheet.getPackagePart().getRelationship(id);
        if (rel == null) {
            return null;
        }
        URI targetURI = rel.getTargetURI();
        return targetURI == null ? null : targetURI.toASCIIString();
    }

    @Override
    public String getLabel() {
        return this._link.getTooltip();
    }

    @Override
    public void setLabel(String label) {
        this._link.setTooltip(label);
    }

    @Override
    public HyperlinkType getType() {
        String action = this._link.getAction();
        if (action == null) {
            action = "";
        }
        if (action.equals("ppaction://hlinksldjump") || action.startsWith("ppaction://hlinkshowjump")) {
            return HyperlinkType.DOCUMENT;
        }
        String address = this.getAddress();
        if (address == null) {
            address = "";
        }
        if (address.startsWith("mailto:")) {
            return HyperlinkType.EMAIL;
        }
        return HyperlinkType.URL;
    }

    @Override
    @Deprecated
    @Removal(version="4.2")
    public HyperlinkType getTypeEnum() {
        return this.getType();
    }

    @Override
    public void linkToEmail(String emailAddress) {
        this.linkToExternal("mailto:" + emailAddress);
        this.setLabel(emailAddress);
    }

    @Override
    public void linkToUrl(String url) {
        this.linkToExternal(url);
        this.setLabel(url);
    }

    private void linkToExternal(String url) {
        PackagePart thisPP = this._sheet.getPackagePart();
        if (this._link.isSetId() && !this._link.getId().isEmpty()) {
            thisPP.removeRelationship(this._link.getId());
        }
        PackageRelationship rel = thisPP.addExternalRelationship(url, XSLFRelation.HYPERLINK.getRelation());
        this._link.setId(rel.getId());
        if (this._link.isSetAction()) {
            this._link.unsetAction();
        }
    }

    @Override
    public void linkToSlide(Slide<XSLFShape, XSLFTextParagraph> slide) {
        if (this._link.isSetId() && !this._link.getId().isEmpty()) {
            this._sheet.getPackagePart().removeRelationship(this._link.getId());
        }
        POIXMLDocumentPart.RelationPart rp = this._sheet.addRelation(null, XSLFRelation.SLIDE, (XSLFSheet)((Object)slide));
        this._link.setId(rp.getRelationship().getId());
        this._link.setAction("ppaction://hlinksldjump");
    }

    @Override
    public void linkToNextSlide() {
        this.linkToRelativeSlide("nextslide");
    }

    @Override
    public void linkToPreviousSlide() {
        this.linkToRelativeSlide("previousslide");
    }

    @Override
    public void linkToFirstSlide() {
        this.linkToRelativeSlide("firstslide");
    }

    @Override
    public void linkToLastSlide() {
        this.linkToRelativeSlide("lastslide");
    }

    void copy(XSLFHyperlink src) {
        switch (src.getType()) {
            case EMAIL: 
            case URL: {
                this.linkToExternal(src.getAddress());
                break;
            }
            case DOCUMENT: {
                String idSrc = src._link.getId();
                if (idSrc == null || idSrc.isEmpty()) {
                    this.linkToRelativeSlide(src.getAddress());
                    break;
                }
                POIXMLDocumentPart pp = src._sheet.getRelationById(idSrc);
                if (pp == null) break;
                POIXMLDocumentPart.RelationPart rp = this._sheet.addRelation(null, XSLFRelation.SLIDE, pp);
                this._link.setId(rp.getRelationship().getId());
                this._link.setAction(src._link.getAction());
                break;
            }
            default: {
                return;
            }
        }
        this.setLabel(src.getLabel());
    }

    private void linkToRelativeSlide(String jump) {
        PackagePart thisPP = this._sheet.getPackagePart();
        if (this._link.isSetId() && !this._link.getId().isEmpty()) {
            thisPP.removeRelationship(this._link.getId());
        }
        this._link.setId("");
        this._link.setAction((jump.startsWith("ppaction") ? "" : "ppaction://hlinkshowjump?jump=") + jump);
    }
}

