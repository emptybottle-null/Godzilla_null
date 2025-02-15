/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.NameIdentifier;
import org.apache.poi.ss.formula.SheetIdentifier;

public class SheetRangeIdentifier
extends SheetIdentifier {
    public NameIdentifier _lastSheetIdentifier;

    public SheetRangeIdentifier(String bookName, NameIdentifier firstSheetIdentifier, NameIdentifier lastSheetIdentifier) {
        super(bookName, firstSheetIdentifier);
        this._lastSheetIdentifier = lastSheetIdentifier;
    }

    public NameIdentifier getFirstSheetIdentifier() {
        return super.getSheetIdentifier();
    }

    public NameIdentifier getLastSheetIdentifier() {
        return this._lastSheetIdentifier;
    }

    @Override
    protected void asFormulaString(StringBuilder sb) {
        super.asFormulaString(sb);
        sb.append(':');
        if (this._lastSheetIdentifier.isQuoted()) {
            sb.append("'").append(this._lastSheetIdentifier.getName()).append("'");
        } else {
            sb.append(this._lastSheetIdentifier.getName());
        }
    }
}

