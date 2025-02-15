/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.util.Internal;

public class XDGFCell {
    CellType _cell;

    public static Boolean maybeGetBoolean(Map<String, XDGFCell> cells, String name) {
        XDGFCell cell = cells.get(name);
        if (cell == null) {
            return null;
        }
        if (cell.getValue().equals("0")) {
            return false;
        }
        if (cell.getValue().equals("1")) {
            return true;
        }
        throw new POIXMLException("Invalid boolean value for '" + cell.getName() + "'");
    }

    public static Double maybeGetDouble(Map<String, XDGFCell> cells, String name) {
        XDGFCell cell = cells.get(name);
        if (cell != null) {
            return XDGFCell.parseDoubleValue(cell._cell);
        }
        return null;
    }

    public static Integer maybeGetInteger(Map<String, XDGFCell> cells, String name) {
        XDGFCell cell = cells.get(name);
        if (cell != null) {
            return XDGFCell.parseIntegerValue(cell._cell);
        }
        return null;
    }

    public static String maybeGetString(Map<String, XDGFCell> cells, String name) {
        XDGFCell cell = cells.get(name);
        if (cell != null) {
            String v = cell._cell.getV();
            if (v.equals("Themed")) {
                return null;
            }
            return v;
        }
        return null;
    }

    public static Double parseDoubleValue(CellType cell) {
        try {
            return Double.parseDouble(cell.getV());
        } catch (NumberFormatException e) {
            if (cell.getV().equals("Themed")) {
                return null;
            }
            throw new POIXMLException("Invalid float value for '" + cell.getN() + "': " + e);
        }
    }

    public static Integer parseIntegerValue(CellType cell) {
        try {
            return Integer.parseInt(cell.getV());
        } catch (NumberFormatException e) {
            if (cell.getV().equals("Themed")) {
                return null;
            }
            throw new POIXMLException("Invalid integer value for '" + cell.getN() + "': " + e);
        }
    }

    public static Double parseVLength(CellType cell) {
        try {
            return Double.parseDouble(cell.getV());
        } catch (NumberFormatException e) {
            if (cell.getV().equals("Themed")) {
                return null;
            }
            throw new POIXMLException("Invalid float value for '" + cell.getN() + "': " + e);
        }
    }

    public XDGFCell(CellType cell) {
        this._cell = cell;
    }

    @Internal
    protected CellType getXmlObject() {
        return this._cell;
    }

    public String getName() {
        return this._cell.getN();
    }

    public String getValue() {
        return this._cell.getV();
    }

    public String getFormula() {
        return this._cell.getF();
    }

    public String getError() {
        return this._cell.getE();
    }
}

