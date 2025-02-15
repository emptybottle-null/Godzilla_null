/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.PageSheetType;
import org.apache.poi.xdgf.usermodel.XDGFDocument;
import org.apache.poi.xdgf.usermodel.XDGFSheet;

public class XDGFPageSheet
extends XDGFSheet {
    PageSheetType _pageSheet;

    public XDGFPageSheet(PageSheetType sheet, XDGFDocument document) {
        super(sheet, document);
        this._pageSheet = sheet;
    }

    @Override
    PageSheetType getXmlObject() {
        return this._pageSheet;
    }
}

