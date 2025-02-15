/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.OutputStream;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet;
import org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument;

public class XSLFTheme
extends POIXMLDocumentPart {
    private CTOfficeStyleSheet _theme;

    XSLFTheme() {
        this._theme = CTOfficeStyleSheet.Factory.newInstance();
    }

    public XSLFTheme(PackagePart part) throws IOException, XmlException {
        super(part);
        ThemeDocument doc = ThemeDocument.Factory.parse(this.getPackagePart().getInputStream(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        this._theme = doc.getTheme();
    }

    public void importTheme(XSLFTheme theme) {
        this._theme = theme.getXmlObject();
    }

    public String getName() {
        return this._theme.getName();
    }

    public void setName(String name) {
        this._theme.setName(name);
    }

    @Internal
    public CTColor getCTColor(String name) {
        CTBaseStyles elems = this._theme.getThemeElements();
        CTColorScheme scheme = elems == null ? null : elems.getClrScheme();
        return XSLFTheme.getMapColor(name, scheme);
    }

    private static CTColor getMapColor(String mapName, CTColorScheme scheme) {
        if (mapName == null || scheme == null) {
            return null;
        }
        switch (mapName) {
            case "accent1": {
                return scheme.getAccent1();
            }
            case "accent2": {
                return scheme.getAccent2();
            }
            case "accent3": {
                return scheme.getAccent3();
            }
            case "accent4": {
                return scheme.getAccent4();
            }
            case "accent5": {
                return scheme.getAccent5();
            }
            case "accent6": {
                return scheme.getAccent6();
            }
            case "dk1": {
                return scheme.getDk1();
            }
            case "dk2": {
                return scheme.getDk2();
            }
            case "folHlink": {
                return scheme.getFolHlink();
            }
            case "hlink": {
                return scheme.getHlink();
            }
            case "lt1": {
                return scheme.getLt1();
            }
            case "lt2": {
                return scheme.getLt2();
            }
        }
        return null;
    }

    @Internal
    public CTOfficeStyleSheet getXmlObject() {
        return this._theme;
    }

    @Override
    protected final void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "theme"));
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        this.getXmlObject().save(out, xmlOptions);
        out.close();
    }

    public String getMajorFont() {
        return this._theme.getThemeElements().getFontScheme().getMajorFont().getLatin().getTypeface();
    }

    public String getMinorFont() {
        return this._theme.getThemeElements().getFontScheme().getMinorFont().getLatin().getTypeface();
    }
}

