/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XSLFBackground;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xslf.usermodel.XSLFTheme;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackground;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout;
import org.openxmlformats.schemas.presentationml.x2006.main.SldLayoutDocument;

public class XSLFSlideLayout
extends XSLFSheet
implements MasterSheet<XSLFShape, XSLFTextParagraph> {
    private final CTSlideLayout _layout;
    private XSLFSlideMaster _master;

    public XSLFSlideLayout(PackagePart part) throws IOException, XmlException {
        super(part);
        SldLayoutDocument doc = SldLayoutDocument.Factory.parse(this.getPackagePart().getInputStream(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        this._layout = doc.getSldLayout();
    }

    public String getName() {
        return this._layout.getCSld().getName();
    }

    @Override
    @Internal
    public CTSlideLayout getXmlObject() {
        return this._layout;
    }

    @Override
    protected String getRootElementName() {
        return "sldLayout";
    }

    public XSLFSlideMaster getSlideMaster() {
        if (this._master == null) {
            for (POIXMLDocumentPart p : this.getRelations()) {
                if (!(p instanceof XSLFSlideMaster)) continue;
                this._master = (XSLFSlideMaster)p;
            }
        }
        if (this._master == null) {
            throw new IllegalStateException("SlideMaster was not found for " + this);
        }
        return this._master;
    }

    public XSLFSlideMaster getMasterSheet() {
        return this.getSlideMaster();
    }

    @Override
    public XSLFTheme getTheme() {
        return this.getSlideMaster().getTheme();
    }

    @Override
    public boolean getFollowMasterGraphics() {
        return this._layout.getShowMasterSp();
    }

    @Override
    public XSLFBackground getBackground() {
        CTBackground bg = this._layout.getCSld().getBg();
        if (bg != null) {
            return new XSLFBackground(bg, (XSLFSheet)this);
        }
        return this.getMasterSheet().getBackground();
    }

    public void copyLayout(XSLFSlide slide) {
        block3: for (XSLFShape sh : this.getShapes()) {
            XSLFTextShape tsh;
            Placeholder ph;
            if (!(sh instanceof XSLFTextShape) || (ph = (tsh = (XSLFTextShape)sh).getTextType()) == null) continue;
            switch (ph) {
                case DATETIME: 
                case SLIDE_NUMBER: 
                case FOOTER: {
                    continue block3;
                }
            }
            slide.getSpTree().addNewSp().set(tsh.getXmlObject().copy());
        }
    }

    public SlideLayout getType() {
        int ordinal = this._layout.getType().intValue() - 1;
        return SlideLayout.values()[ordinal];
    }

    @Override
    String mapSchemeColor(String schemeColor) {
        return this.mapSchemeColor(this._layout.getClrMapOvr(), schemeColor);
    }
}

