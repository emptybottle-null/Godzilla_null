/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFEndnotes;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFFootnotes;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFSettings;
import org.apache.poi.xwpf.usermodel.XWPFStyles;

public final class XWPFRelation
extends POIXMLRelation {
    private static final Map<String, XWPFRelation> _table = new HashMap<String, XWPFRelation>();
    public static final XWPFRelation DOCUMENT = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument", "/word/document.xml");
    public static final XWPFRelation TEMPLATE = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.template.main+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument", "/word/document.xml");
    public static final XWPFRelation MACRO_DOCUMENT = new XWPFRelation("application/vnd.ms-word.document.macroEnabled.main+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument", "/word/document.xml");
    public static final XWPFRelation MACRO_TEMPLATE_DOCUMENT = new XWPFRelation("application/vnd.ms-word.template.macroEnabledTemplate.main+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument", "/word/document.xml");
    public static final XWPFRelation GLOSSARY_DOCUMENT = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.document.glossary+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/glossaryDocument", "/word/glossary/document.xml");
    public static final XWPFRelation NUMBERING = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.numbering+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/numbering", "/word/numbering.xml", XWPFNumbering::new, XWPFNumbering::new);
    public static final XWPFRelation FONT_TABLE = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.fontTable+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/fontTable", "/word/fontTable.xml");
    public static final XWPFRelation SETTINGS = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.settings+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/settings", "/word/settings.xml", XWPFSettings::new, XWPFSettings::new);
    public static final XWPFRelation STYLES = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.styles+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/styles", "/word/styles.xml", XWPFStyles::new, XWPFStyles::new);
    public static final XWPFRelation WEB_SETTINGS = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.webSettings+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/webSettings", "/word/webSettings.xml");
    public static final XWPFRelation HEADER = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.header+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/header", "/word/header#.xml", XWPFHeader::new, XWPFHeader::new);
    public static final XWPFRelation FOOTER = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.footer+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/footer", "/word/footer#.xml", XWPFFooter::new, XWPFFooter::new);
    public static final XWPFRelation THEME = new XWPFRelation("application/vnd.openxmlformats-officedocument.theme+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/theme", "/word/theme/theme#.xml");
    public static final XWPFRelation WORKBOOK = new XWPFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/package", "/word/embeddings/Microsoft_Excel_Worksheet#.xlsx", XSSFWorkbook::new, XSSFWorkbook::new);
    public static final XWPFRelation CHART = new XWPFRelation("application/vnd.openxmlformats-officedocument.drawingml.chart+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/chart", "/word/charts/chart#.xml", XWPFChart::new, XWPFChart::new);
    public static final XWPFRelation HYPERLINK = new XWPFRelation(null, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/hyperlink", null);
    public static final XWPFRelation COMMENT = new XWPFRelation(null, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/comments", null);
    public static final XWPFRelation FOOTNOTE = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.footnotes+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/footnotes", "/word/footnotes.xml", XWPFFootnotes::new, XWPFFootnotes::new);
    public static final XWPFRelation ENDNOTE = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.endnotes+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/endnotes", "/word/endnotes.xml", XWPFEndnotes::new, XWPFEndnotes::new);
    public static final XWPFRelation IMAGE_EMF = new XWPFRelation("image/x-emf", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image", "/word/media/image#.emf", XWPFPictureData::new, XWPFPictureData::new);
    public static final XWPFRelation IMAGE_WMF = new XWPFRelation("image/x-wmf", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image", "/word/media/image#.wmf", XWPFPictureData::new, XWPFPictureData::new);
    public static final XWPFRelation IMAGE_PICT = new XWPFRelation("image/pict", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image", "/word/media/image#.pict", XWPFPictureData::new, XWPFPictureData::new);
    public static final XWPFRelation IMAGE_JPEG = new XWPFRelation("image/jpeg", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image", "/word/media/image#.jpeg", XWPFPictureData::new, XWPFPictureData::new);
    public static final XWPFRelation IMAGE_PNG = new XWPFRelation("image/png", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image", "/word/media/image#.png", XWPFPictureData::new, XWPFPictureData::new);
    public static final XWPFRelation IMAGE_DIB = new XWPFRelation("image/dib", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image", "/word/media/image#.dib", XWPFPictureData::new, XWPFPictureData::new);
    public static final XWPFRelation IMAGE_GIF = new XWPFRelation("image/gif", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image", "/word/media/image#.gif", XWPFPictureData::new, XWPFPictureData::new);
    public static final XWPFRelation IMAGE_TIFF = new XWPFRelation("image/tiff", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image", "/word/media/image#.tiff", XWPFPictureData::new, XWPFPictureData::new);
    public static final XWPFRelation IMAGE_EPS = new XWPFRelation("image/x-eps", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image", "/word/media/image#.eps", XWPFPictureData::new, XWPFPictureData::new);
    public static final XWPFRelation IMAGE_BMP = new XWPFRelation("image/x-ms-bmp", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image", "/word/media/image#.bmp", XWPFPictureData::new, XWPFPictureData::new);
    public static final XWPFRelation IMAGE_WPG = new XWPFRelation("image/x-wpg", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image", "/word/media/image#.wpg", XWPFPictureData::new, XWPFPictureData::new);
    public static final XWPFRelation IMAGES = new XWPFRelation(null, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image", null, XWPFPictureData::new, XWPFPictureData::new);

    private XWPFRelation(String type, String rel, String defaultName) {
        super(type, rel, defaultName);
        _table.put(rel, this);
    }

    private XWPFRelation(String type, String rel, String defaultName, POIXMLRelation.NoArgConstructor noArgConstructor, POIXMLRelation.PackagePartConstructor packagePartConstructor) {
        super(type, rel, defaultName, noArgConstructor, packagePartConstructor, null);
        _table.put(rel, this);
    }

    private XWPFRelation(String type, String rel, String defaultName, POIXMLRelation.NoArgConstructor noArgConstructor, POIXMLRelation.ParentPartConstructor parentPartConstructor) {
        super(type, rel, defaultName, noArgConstructor, null, parentPartConstructor);
        _table.put(rel, this);
    }

    public static XWPFRelation getInstance(String rel) {
        return _table.get(rel);
    }
}

