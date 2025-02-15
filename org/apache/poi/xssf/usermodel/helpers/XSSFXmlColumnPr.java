/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.helpers;

import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXmlColumnPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXmlDataType;

public class XSSFXmlColumnPr {
    private XSSFTable table;
    private XSSFTableColumn tableColumn;
    private CTXmlColumnPr ctXmlColumnPr;

    @Internal
    public XSSFXmlColumnPr(XSSFTableColumn tableColumn, CTXmlColumnPr ctXmlColumnPr) {
        this.table = tableColumn.getTable();
        this.tableColumn = tableColumn;
        this.ctXmlColumnPr = ctXmlColumnPr;
    }

    @Deprecated
    @Removal(version="4.2")
    public XSSFXmlColumnPr(XSSFTable table, CTTableColumn ctTableColum, CTXmlColumnPr ctXmlColumnPr) {
        this.table = table;
        this.tableColumn = table.getColumns().get(table.findColumnIndex(ctTableColum.getName()));
        this.ctXmlColumnPr = ctXmlColumnPr;
    }

    public XSSFTableColumn getTableColumn() {
        return this.tableColumn;
    }

    public long getMapId() {
        return this.ctXmlColumnPr.getMapId();
    }

    public String getXPath() {
        return this.ctXmlColumnPr.getXpath();
    }

    @Deprecated
    @Removal(version="4.2")
    public long getId() {
        return this.tableColumn.getId();
    }

    public String getLocalXPath() {
        StringBuilder localXPath = new StringBuilder();
        int numberOfCommonXPathAxis = this.table.getCommonXpath().split("/").length - 1;
        String[] xPathTokens = this.ctXmlColumnPr.getXpath().split("/");
        for (int i = numberOfCommonXPathAxis; i < xPathTokens.length; ++i) {
            localXPath.append("/" + xPathTokens[i]);
        }
        return localXPath.toString();
    }

    public STXmlDataType.Enum getXmlDataType() {
        return this.ctXmlColumnPr.getXmlDataType();
    }
}

