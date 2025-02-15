/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.StyleSheetType;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.usermodel.XDGFDocument;
import org.apache.poi.xdgf.usermodel.XDGFSheet;

public class XDGFStyleSheet
extends XDGFSheet {
    public XDGFStyleSheet(StyleSheetType styleSheet, XDGFDocument document) {
        super(styleSheet, document);
    }

    @Override
    @Internal
    public StyleSheetType getXmlObject() {
        return (StyleSheetType)this._sheet;
    }
}

