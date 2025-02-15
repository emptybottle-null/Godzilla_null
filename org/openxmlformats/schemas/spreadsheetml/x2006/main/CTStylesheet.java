/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyles
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorders;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyleXfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyles;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellXfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFills;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFonts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyles;
import org.w3c.dom.Node;

public interface CTStylesheet
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTStylesheet.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctstylesheet4257type");

    public CTNumFmts getNumFmts();

    public boolean isSetNumFmts();

    public void setNumFmts(CTNumFmts var1);

    public CTNumFmts addNewNumFmts();

    public void unsetNumFmts();

    public CTFonts getFonts();

    public boolean isSetFonts();

    public void setFonts(CTFonts var1);

    public CTFonts addNewFonts();

    public void unsetFonts();

    public CTFills getFills();

    public boolean isSetFills();

    public void setFills(CTFills var1);

    public CTFills addNewFills();

    public void unsetFills();

    public CTBorders getBorders();

    public boolean isSetBorders();

    public void setBorders(CTBorders var1);

    public CTBorders addNewBorders();

    public void unsetBorders();

    public CTCellStyleXfs getCellStyleXfs();

    public boolean isSetCellStyleXfs();

    public void setCellStyleXfs(CTCellStyleXfs var1);

    public CTCellStyleXfs addNewCellStyleXfs();

    public void unsetCellStyleXfs();

    public CTCellXfs getCellXfs();

    public boolean isSetCellXfs();

    public void setCellXfs(CTCellXfs var1);

    public CTCellXfs addNewCellXfs();

    public void unsetCellXfs();

    public CTCellStyles getCellStyles();

    public boolean isSetCellStyles();

    public void setCellStyles(CTCellStyles var1);

    public CTCellStyles addNewCellStyles();

    public void unsetCellStyles();

    public CTDxfs getDxfs();

    public boolean isSetDxfs();

    public void setDxfs(CTDxfs var1);

    public CTDxfs addNewDxfs();

    public void unsetDxfs();

    public CTTableStyles getTableStyles();

    public boolean isSetTableStyles();

    public void setTableStyles(CTTableStyles var1);

    public CTTableStyles addNewTableStyles();

    public void unsetTableStyles();

    public CTColors getColors();

    public boolean isSetColors();

    public void setColors(CTColors var1);

    public CTColors addNewColors();

    public void unsetColors();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTStylesheet.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTStylesheet newInstance() {
            return (CTStylesheet)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTStylesheet newInstance(XmlOptions xmlOptions) {
            return (CTStylesheet)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTStylesheet parse(String string) throws XmlException {
            return (CTStylesheet)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTStylesheet parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTStylesheet)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTStylesheet parse(File file) throws XmlException, IOException {
            return (CTStylesheet)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTStylesheet parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStylesheet)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTStylesheet parse(URL uRL) throws XmlException, IOException {
            return (CTStylesheet)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTStylesheet parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStylesheet)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTStylesheet parse(InputStream inputStream) throws XmlException, IOException {
            return (CTStylesheet)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTStylesheet parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStylesheet)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTStylesheet parse(Reader reader) throws XmlException, IOException {
            return (CTStylesheet)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTStylesheet parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStylesheet)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTStylesheet parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTStylesheet)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTStylesheet parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTStylesheet)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTStylesheet parse(Node node) throws XmlException {
            return (CTStylesheet)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTStylesheet parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTStylesheet)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTStylesheet parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTStylesheet)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTStylesheet parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTStylesheet)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, null);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, xmlOptions);
        }

        private Factory() {
        }
    }
}

