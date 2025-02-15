/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XSLFBackground;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackground;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles;
import org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument;

public class XSLFSlideMaster
extends XSLFSheet
implements MasterSheet<XSLFShape, XSLFTextParagraph> {
    private CTSlideMaster _slide;
    private Map<String, XSLFSlideLayout> _layouts;

    protected XSLFSlideMaster(PackagePart part) throws IOException, XmlException {
        super(part);
        SldMasterDocument doc = SldMasterDocument.Factory.parse(this.getPackagePart().getInputStream(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        this._slide = doc.getSldMaster();
    }

    @Override
    public CTSlideMaster getXmlObject() {
        return this._slide;
    }

    @Override
    protected String getRootElementName() {
        return "sldMaster";
    }

    public XSLFSlideMaster getMasterSheet() {
        return null;
    }

    private Map<String, XSLFSlideLayout> getLayouts() {
        if (this._layouts == null) {
            this._layouts = new HashMap<String, XSLFSlideLayout>();
            for (POIXMLDocumentPart p : this.getRelations()) {
                if (!(p instanceof XSLFSlideLayout)) continue;
                XSLFSlideLayout layout = (XSLFSlideLayout)p;
                this._layouts.put(layout.getName().toLowerCase(Locale.ROOT), layout);
            }
        }
        return this._layouts;
    }

    public XSLFSlideLayout[] getSlideLayouts() {
        return this.getLayouts().values().toArray(new XSLFSlideLayout[this._layouts.size()]);
    }

    public XSLFSlideLayout getLayout(SlideLayout type) {
        for (XSLFSlideLayout layout : this.getLayouts().values()) {
            if (layout.getType() != type) continue;
            return layout;
        }
        return null;
    }

    public XSLFSlideLayout getLayout(String name) {
        return this.getLayouts().get(name.toLowerCase(Locale.ROOT));
    }

    protected CTTextListStyle getTextProperties(Placeholder textType) {
        CTTextListStyle props;
        CTSlideMasterTextStyles txStyles = this.getXmlObject().getTxStyles();
        switch (textType) {
            case TITLE: 
            case CENTERED_TITLE: 
            case SUBTITLE: {
                props = txStyles.getTitleStyle();
                break;
            }
            case BODY: {
                props = txStyles.getBodyStyle();
                break;
            }
            default: {
                props = txStyles.getOtherStyle();
            }
        }
        return props;
    }

    @Override
    public XSLFBackground getBackground() {
        CTBackground bg = this._slide.getCSld().getBg();
        if (bg != null) {
            return new XSLFBackground(bg, (XSLFSheet)this);
        }
        return null;
    }

    @Override
    boolean isSupportTheme() {
        return true;
    }

    @Override
    String mapSchemeColor(String schemeColor) {
        String masterColor = this.mapSchemeColor(this._slide.getClrMap(), schemeColor);
        return masterColor == null ? schemeColor : masterColor;
    }
}

