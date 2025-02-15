/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType;
import com.microsoft.schemas.office.visio.x2012.main.StyleSheetType;
import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.usermodel.XDGFStyleSheet;

public class XDGFDocument {
    protected VisioDocumentType _document;
    Map<Long, XDGFStyleSheet> _styleSheets = new HashMap<Long, XDGFStyleSheet>();
    long _defaultFillStyle;
    long _defaultGuideStyle;
    long _defaultLineStyle;
    long _defaultTextStyle;

    public XDGFDocument(VisioDocumentType document) {
        this._document = document;
        if (!this._document.isSetDocumentSettings()) {
            throw new POIXMLException("Document settings not found");
        }
        DocumentSettingsType docSettings = this._document.getDocumentSettings();
        if (docSettings.isSetDefaultFillStyle()) {
            this._defaultFillStyle = docSettings.getDefaultFillStyle();
        }
        if (docSettings.isSetDefaultGuideStyle()) {
            this._defaultGuideStyle = docSettings.getDefaultGuideStyle();
        }
        if (docSettings.isSetDefaultLineStyle()) {
            this._defaultLineStyle = docSettings.getDefaultLineStyle();
        }
        if (docSettings.isSetDefaultTextStyle()) {
            this._defaultTextStyle = docSettings.getDefaultTextStyle();
        }
        if (this._document.isSetStyleSheets()) {
            for (StyleSheetType styleSheet : this._document.getStyleSheets().getStyleSheetArray()) {
                this._styleSheets.put(styleSheet.getID(), new XDGFStyleSheet(styleSheet, this));
            }
        }
    }

    @Internal
    public VisioDocumentType getXmlObject() {
        return this._document;
    }

    public XDGFStyleSheet getStyleById(long id) {
        return this._styleSheets.get(id);
    }

    public XDGFStyleSheet getDefaultFillStyle() {
        XDGFStyleSheet style = this.getStyleById(this._defaultFillStyle);
        if (style == null) {
            throw new POIXMLException("No default fill style found!");
        }
        return style;
    }

    public XDGFStyleSheet getDefaultGuideStyle() {
        XDGFStyleSheet style = this.getStyleById(this._defaultGuideStyle);
        if (style == null) {
            throw new POIXMLException("No default guide style found!");
        }
        return style;
    }

    public XDGFStyleSheet getDefaultLineStyle() {
        XDGFStyleSheet style = this.getStyleById(this._defaultLineStyle);
        if (style == null) {
            throw new POIXMLException("No default line style found!");
        }
        return style;
    }

    public XDGFStyleSheet getDefaultTextStyle() {
        XDGFStyleSheet style = this.getStyleById(this._defaultTextStyle);
        if (style == null) {
            throw new POIXMLException("No default text style found!");
        }
        return style;
    }
}

