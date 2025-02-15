/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel.section;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.SectionType;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFSheet;
import org.apache.poi.xdgf.usermodel.section.CharacterSection;
import org.apache.poi.xdgf.usermodel.section.GenericSection;
import org.apache.poi.xdgf.usermodel.section.GeometrySection;
import org.apache.poi.xdgf.util.ObjectFactory;

public abstract class XDGFSection {
    static final ObjectFactory<XDGFSection, SectionType> _sectionTypes = new ObjectFactory();
    protected SectionType _section;
    protected XDGFSheet _containingSheet;
    protected Map<String, XDGFCell> _cells = new HashMap<String, XDGFCell>();

    public static XDGFSection load(SectionType section, XDGFSheet containingSheet) {
        return _sectionTypes.load(section.getN(), section, containingSheet);
    }

    public XDGFSection(SectionType section, XDGFSheet containingSheet) {
        this._section = section;
        this._containingSheet = containingSheet;
        for (CellType cell : section.getCellArray()) {
            this._cells.put(cell.getN(), new XDGFCell(cell));
        }
    }

    @Internal
    public SectionType getXmlObject() {
        return this._section;
    }

    public String toString() {
        return "<Section type=" + this._section.getN() + " from " + this._containingSheet + ">";
    }

    public abstract void setupMaster(XDGFSection var1);

    static {
        try {
            _sectionTypes.put("LineGradient", GenericSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("FillGradient", GenericSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("Character", CharacterSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("Paragraph", GenericSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("Tabs", GenericSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("Scratch", GenericSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("Connection", GenericSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("ConnectionABCD", GenericSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("Field", GenericSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("Control", GenericSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("Geometry", GeometrySection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("Actions", GenericSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("Layer", GenericSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("User", GenericSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("Property", GenericSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("Hyperlink", GenericSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("Reviewer", GenericSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("Annotation", GenericSection.class, SectionType.class, XDGFSheet.class);
            _sectionTypes.put("ActionTag", GenericSection.class, SectionType.class, XDGFSheet.class);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new POIXMLException("Internal error");
        }
    }
}

