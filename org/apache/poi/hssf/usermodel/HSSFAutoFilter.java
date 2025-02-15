/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.AutoFilter;

public final class HSSFAutoFilter
implements AutoFilter {
    private HSSFSheet _sheet;

    HSSFAutoFilter(HSSFSheet sheet) {
        this._sheet = sheet;
    }
}

