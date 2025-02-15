/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel.section;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import com.microsoft.schemas.office.visio.x2012.main.SectionType;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFSheet;
import org.apache.poi.xdgf.usermodel.section.XDGFSection;

public class CharacterSection
extends XDGFSection {
    Double _fontSize;
    Color _fontColor;
    Map<String, XDGFCell> _characterCells = new HashMap<String, XDGFCell>();

    public CharacterSection(SectionType section, XDGFSheet containingSheet) {
        super(section, containingSheet);
        RowType row = section.getRowArray(0);
        for (CellType cell : row.getCellArray()) {
            this._characterCells.put(cell.getN(), new XDGFCell(cell));
        }
        this._fontSize = XDGFCell.maybeGetDouble(this._characterCells, "Size");
        String tmpColor = XDGFCell.maybeGetString(this._characterCells, "Color");
        if (tmpColor != null) {
            this._fontColor = Color.decode(tmpColor);
        }
    }

    public Double getFontSize() {
        return this._fontSize;
    }

    public Color getFontColor() {
        return this._fontColor;
    }

    @Override
    public void setupMaster(XDGFSection section) {
    }
}

