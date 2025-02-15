/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.SectionType;
import com.microsoft.schemas.office.visio.x2012.main.SheetType;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.exceptions.XDGFException;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFDocument;
import org.apache.poi.xdgf.usermodel.XDGFStyleSheet;
import org.apache.poi.xdgf.usermodel.section.CharacterSection;
import org.apache.poi.xdgf.usermodel.section.GeometrySection;
import org.apache.poi.xdgf.usermodel.section.XDGFSection;
import org.apache.xmlbeans.XmlObject;

public abstract class XDGFSheet {
    protected XDGFDocument _document;
    protected SheetType _sheet;
    protected Map<String, XDGFCell> _cells = new HashMap<String, XDGFCell>();
    protected Map<String, XDGFSection> _sections = new HashMap<String, XDGFSection>();
    protected SortedMap<Long, GeometrySection> _geometry = new TreeMap<Long, GeometrySection>();
    protected CharacterSection _character;

    public XDGFSheet(SheetType sheet, XDGFDocument document) {
        try {
            this._sheet = sheet;
            this._document = document;
            for (CellType cellType : sheet.getCellArray()) {
                if (this._cells.containsKey(cellType.getN())) {
                    throw new POIXMLException("Unexpected duplicate cell " + cellType.getN());
                }
                this._cells.put(cellType.getN(), new XDGFCell(cellType));
            }
            for (XmlObject xmlObject : sheet.getSectionArray()) {
                String name = xmlObject.getN();
                if (name.equals("Geometry")) {
                    this._geometry.put(xmlObject.getIX(), new GeometrySection((SectionType)xmlObject, this));
                    continue;
                }
                if (name.equals("Character")) {
                    this._character = new CharacterSection((SectionType)xmlObject, this);
                    continue;
                }
                this._sections.put(name, XDGFSection.load((SectionType)xmlObject, this));
            }
        } catch (POIXMLException e) {
            throw XDGFException.wrap(this.toString(), e);
        }
    }

    abstract SheetType getXmlObject();

    public XDGFDocument getDocument() {
        return this._document;
    }

    public XDGFCell getCell(String cellName) {
        return this._cells.get(cellName);
    }

    public XDGFSection getSection(String sectionName) {
        return this._sections.get(sectionName);
    }

    public XDGFStyleSheet getLineStyle() {
        if (!this._sheet.isSetLineStyle()) {
            return null;
        }
        return this._document.getStyleById(this._sheet.getLineStyle());
    }

    public XDGFStyleSheet getFillStyle() {
        if (!this._sheet.isSetFillStyle()) {
            return null;
        }
        return this._document.getStyleById(this._sheet.getFillStyle());
    }

    public XDGFStyleSheet getTextStyle() {
        if (!this._sheet.isSetTextStyle()) {
            return null;
        }
        return this._document.getStyleById(this._sheet.getTextStyle());
    }

    public Color getFontColor() {
        Color fontColor;
        if (this._character != null && (fontColor = this._character.getFontColor()) != null) {
            return fontColor;
        }
        XDGFStyleSheet style = this.getTextStyle();
        if (style != null) {
            return style.getFontColor();
        }
        return null;
    }

    public Double getFontSize() {
        Double fontSize;
        if (this._character != null && (fontSize = this._character.getFontSize()) != null) {
            return fontSize;
        }
        XDGFStyleSheet style = this.getTextStyle();
        if (style != null) {
            return style.getFontSize();
        }
        return null;
    }

    public Integer getLineCap() {
        Integer lineCap = XDGFCell.maybeGetInteger(this._cells, "LineCap");
        if (lineCap != null) {
            return lineCap;
        }
        XDGFStyleSheet style = this.getLineStyle();
        if (style != null) {
            return style.getLineCap();
        }
        return null;
    }

    public Color getLineColor() {
        String lineColor = XDGFCell.maybeGetString(this._cells, "LineColor");
        if (lineColor != null) {
            return Color.decode(lineColor);
        }
        XDGFStyleSheet style = this.getLineStyle();
        if (style != null) {
            return style.getLineColor();
        }
        return null;
    }

    public Integer getLinePattern() {
        Integer linePattern = XDGFCell.maybeGetInteger(this._cells, "LinePattern");
        if (linePattern != null) {
            return linePattern;
        }
        XDGFStyleSheet style = this.getLineStyle();
        if (style != null) {
            return style.getLinePattern();
        }
        return null;
    }

    public Double getLineWeight() {
        Double lineWeight = XDGFCell.maybeGetDouble(this._cells, "LineWeight");
        if (lineWeight != null) {
            return lineWeight;
        }
        XDGFStyleSheet style = this.getLineStyle();
        if (style != null) {
            return style.getLineWeight();
        }
        return null;
    }
}

