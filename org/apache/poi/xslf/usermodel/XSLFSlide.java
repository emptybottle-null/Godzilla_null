/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawSlide;
import org.apache.poi.sl.usermodel.Notes;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.xslf.usermodel.XSLFBackground;
import org.apache.poi.xslf.usermodel.XSLFChart;
import org.apache.poi.xslf.usermodel.XSLFComment;
import org.apache.poi.xslf.usermodel.XSLFCommentAuthors;
import org.apache.poi.xslf.usermodel.XSLFComments;
import org.apache.poi.xslf.usermodel.XSLFNotes;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xslf.usermodel.XSLFTheme;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackground;
import org.openxmlformats.schemas.presentationml.x2006.main.CTComment;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlide;
import org.openxmlformats.schemas.presentationml.x2006.main.SldDocument;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public final class XSLFSlide
extends XSLFSheet
implements Slide<XSLFShape, XSLFTextParagraph> {
    private final CTSlide _slide;
    private XSLFSlideLayout _layout;
    private XSLFComments _comments;
    private XSLFCommentAuthors _commentAuthors;
    private XSLFNotes _notes;

    XSLFSlide() {
        this._slide = XSLFSlide.prototype();
    }

    XSLFSlide(PackagePart part) throws IOException, XmlException {
        super(part);
        Document _doc;
        try {
            _doc = DocumentHelper.readDocument(this.getPackagePart().getInputStream());
        } catch (SAXException e) {
            throw new IOException(e);
        }
        SldDocument doc = SldDocument.Factory.parse(_doc, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        this._slide = doc.getSld();
    }

    private static CTSlide prototype() {
        CTSlide ctSlide = CTSlide.Factory.newInstance();
        CTCommonSlideData cSld = ctSlide.addNewCSld();
        CTGroupShape spTree = cSld.addNewSpTree();
        CTGroupShapeNonVisual nvGrpSpPr = spTree.addNewNvGrpSpPr();
        CTNonVisualDrawingProps cnvPr = nvGrpSpPr.addNewCNvPr();
        cnvPr.setId(1L);
        cnvPr.setName("");
        nvGrpSpPr.addNewCNvGrpSpPr();
        nvGrpSpPr.addNewNvPr();
        CTGroupShapeProperties grpSpr = spTree.addNewGrpSpPr();
        CTGroupTransform2D xfrm = grpSpr.addNewXfrm();
        CTPoint2D off = xfrm.addNewOff();
        off.setX(0L);
        off.setY(0L);
        CTPositiveSize2D ext = xfrm.addNewExt();
        ext.setCx(0L);
        ext.setCy(0L);
        CTPoint2D choff = xfrm.addNewChOff();
        choff.setX(0L);
        choff.setY(0L);
        CTPositiveSize2D chExt = xfrm.addNewChExt();
        chExt.setCx(0L);
        chExt.setCy(0L);
        ctSlide.addNewClrMapOvr().addNewMasterClrMapping();
        return ctSlide;
    }

    @Override
    public CTSlide getXmlObject() {
        return this._slide;
    }

    @Override
    protected String getRootElementName() {
        return "sld";
    }

    protected void removeChartRelation(XSLFChart chart) {
        this.removeRelation(chart);
    }

    protected void removeLayoutRelation(XSLFSlideLayout layout) {
        this.removeRelation(layout, false);
    }

    public XSLFSlideLayout getMasterSheet() {
        return this.getSlideLayout();
    }

    public XSLFSlideLayout getSlideLayout() {
        if (this._layout == null) {
            for (POIXMLDocumentPart p : this.getRelations()) {
                if (!(p instanceof XSLFSlideLayout)) continue;
                this._layout = (XSLFSlideLayout)p;
            }
        }
        if (this._layout == null) {
            throw new IllegalArgumentException("SlideLayout was not found for " + this);
        }
        return this._layout;
    }

    public XSLFSlideMaster getSlideMaster() {
        return this.getSlideLayout().getSlideMaster();
    }

    public XSLFComments getCommentsPart() {
        if (this._comments == null) {
            for (POIXMLDocumentPart p : this.getRelations()) {
                if (!(p instanceof XSLFComments)) continue;
                this._comments = (XSLFComments)p;
                break;
            }
        }
        return this._comments;
    }

    public XSLFCommentAuthors getCommentAuthorsPart() {
        if (this._commentAuthors == null) {
            for (POIXMLDocumentPart p : this.getRelations()) {
                if (!(p instanceof XSLFCommentAuthors)) continue;
                this._commentAuthors = (XSLFCommentAuthors)p;
                return this._commentAuthors;
            }
            for (POIXMLDocumentPart p : this.getSlideShow().getRelations()) {
                if (!(p instanceof XSLFCommentAuthors)) continue;
                this._commentAuthors = (XSLFCommentAuthors)p;
                return this._commentAuthors;
            }
        }
        return null;
    }

    @Override
    public List<XSLFComment> getComments() {
        ArrayList<XSLFComment> comments = new ArrayList<XSLFComment>();
        XSLFComments xComments = this.getCommentsPart();
        XSLFCommentAuthors xAuthors = this.getCommentAuthorsPart();
        if (xComments != null) {
            for (CTComment xc : xComments.getCTCommentsList().getCmArray()) {
                comments.add(new XSLFComment(xc, xAuthors));
            }
        }
        return comments;
    }

    public XSLFNotes getNotes() {
        if (this._notes == null) {
            for (POIXMLDocumentPart p : this.getRelations()) {
                if (!(p instanceof XSLFNotes)) continue;
                this._notes = (XSLFNotes)p;
            }
        }
        if (this._notes == null) {
            return null;
        }
        return this._notes;
    }

    @Override
    public String getTitle() {
        XSLFTextShape txt = this.getTextShapeByType(Placeholder.TITLE);
        return txt == null ? null : txt.getText();
    }

    @Override
    public XSLFTheme getTheme() {
        return this.getSlideLayout().getSlideMaster().getTheme();
    }

    @Override
    public XSLFBackground getBackground() {
        CTBackground bg = this._slide.getCSld().getBg();
        if (bg != null) {
            return new XSLFBackground(bg, (XSLFSheet)this);
        }
        return this.getMasterSheet().getBackground();
    }

    @Override
    public boolean getFollowMasterGraphics() {
        return this._slide.getShowMasterSp();
    }

    public void setFollowMasterGraphics(boolean value) {
        this._slide.setShowMasterSp(value);
    }

    @Override
    public boolean getFollowMasterObjects() {
        return this.getFollowMasterGraphics();
    }

    @Override
    public void setFollowMasterObjects(boolean follow) {
        this.setFollowMasterGraphics(follow);
    }

    @Override
    public XSLFSlide importContent(XSLFSheet src) {
        super.importContent(src);
        if (!(src instanceof XSLFSlide)) {
            return this;
        }
        CTBackground bgOther = ((XSLFSlide)src)._slide.getCSld().getBg();
        if (bgOther == null) {
            return this;
        }
        CTBackground bgThis = this._slide.getCSld().getBg();
        if (bgThis != null) {
            if (bgThis.isSetBgPr() && bgThis.getBgPr().isSetBlipFill()) {
                String oldId = bgThis.getBgPr().getBlipFill().getBlip().getEmbed();
                this.removeRelation(oldId);
            }
            this._slide.getCSld().unsetBg();
        }
        bgThis = (CTBackground)this._slide.getCSld().addNewBg().set(bgOther);
        if (bgOther.isSetBgPr() && bgOther.getBgPr().isSetBlipFill()) {
            String idOther = bgOther.getBgPr().getBlipFill().getBlip().getEmbed();
            String idThis = this.importBlip(idOther, src);
            bgThis.getBgPr().getBlipFill().getBlip().setEmbed(idThis);
        }
        return this;
    }

    @Override
    public boolean getFollowMasterBackground() {
        return false;
    }

    @Override
    @NotImplemented
    public void setFollowMasterBackground(boolean follow) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getFollowMasterColourScheme() {
        return false;
    }

    @Override
    @NotImplemented
    public void setFollowMasterColourScheme(boolean follow) {
        throw new UnsupportedOperationException();
    }

    @Override
    @NotImplemented
    public void setNotes(Notes<XSLFShape, XSLFTextParagraph> notes) {
        assert (notes instanceof XSLFNotes);
    }

    @Override
    public int getSlideNumber() {
        int idx = this.getSlideShow().getSlides().indexOf(this);
        return idx == -1 ? idx : idx + 1;
    }

    @Override
    public void draw(Graphics2D graphics) {
        DrawFactory drawFact = DrawFactory.getInstance(graphics);
        DrawSlide draw = drawFact.getDrawable(this);
        draw.draw(graphics);
    }

    @Override
    public boolean getDisplayPlaceholder(Placeholder placeholder) {
        return false;
    }

    @Override
    public void setHidden(boolean hidden) {
        CTSlide sld = this.getXmlObject();
        if (hidden) {
            sld.setShow(false);
        } else if (sld.isSetShow()) {
            sld.unsetShow();
        }
    }

    @Override
    public boolean isHidden() {
        CTSlide sld = this.getXmlObject();
        return sld.isSetShow() && !sld.getShow();
    }

    @Override
    public String getSlideName() {
        CTCommonSlideData cSld = this.getXmlObject().getCSld();
        return cSld.isSetName() ? cSld.getName() : "Slide" + this.getSlideNumber();
    }

    @Override
    String mapSchemeColor(String schemeColor) {
        return this.mapSchemeColor(this._slide.getClrMapOvr(), schemeColor);
    }
}

