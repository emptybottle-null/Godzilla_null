/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.AutoFilter;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public final class XSSFAutoFilter
implements AutoFilter {
    private XSSFSheet _sheet;

    XSSFAutoFilter(XSSFSheet sheet) {
        this._sheet = sheet;
    }
}

