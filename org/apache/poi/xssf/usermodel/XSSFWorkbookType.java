/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.xssf.usermodel.XSSFRelation;

public enum XSSFWorkbookType {
    XLSX(XSSFRelation.WORKBOOK.getContentType(), "xlsx"),
    XLSM(XSSFRelation.MACROS_WORKBOOK.getContentType(), "xlsm");

    private final String _contentType;
    private final String _extension;

    private XSSFWorkbookType(String contentType, String extension) {
        this._contentType = contentType;
        this._extension = extension;
    }

    public String getContentType() {
        return this._contentType;
    }

    public String getExtension() {
        return this._extension;
    }
}

