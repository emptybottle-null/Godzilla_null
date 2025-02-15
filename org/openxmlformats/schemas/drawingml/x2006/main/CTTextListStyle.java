/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main;

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
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.w3c.dom.Node;

public interface CTTextListStyle
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTextListStyle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttextliststyleab77type");

    public CTTextParagraphProperties getDefPPr();

    public boolean isSetDefPPr();

    public void setDefPPr(CTTextParagraphProperties var1);

    public CTTextParagraphProperties addNewDefPPr();

    public void unsetDefPPr();

    public CTTextParagraphProperties getLvl1PPr();

    public boolean isSetLvl1PPr();

    public void setLvl1PPr(CTTextParagraphProperties var1);

    public CTTextParagraphProperties addNewLvl1PPr();

    public void unsetLvl1PPr();

    public CTTextParagraphProperties getLvl2PPr();

    public boolean isSetLvl2PPr();

    public void setLvl2PPr(CTTextParagraphProperties var1);

    public CTTextParagraphProperties addNewLvl2PPr();

    public void unsetLvl2PPr();

    public CTTextParagraphProperties getLvl3PPr();

    public boolean isSetLvl3PPr();

    public void setLvl3PPr(CTTextParagraphProperties var1);

    public CTTextParagraphProperties addNewLvl3PPr();

    public void unsetLvl3PPr();

    public CTTextParagraphProperties getLvl4PPr();

    public boolean isSetLvl4PPr();

    public void setLvl4PPr(CTTextParagraphProperties var1);

    public CTTextParagraphProperties addNewLvl4PPr();

    public void unsetLvl4PPr();

    public CTTextParagraphProperties getLvl5PPr();

    public boolean isSetLvl5PPr();

    public void setLvl5PPr(CTTextParagraphProperties var1);

    public CTTextParagraphProperties addNewLvl5PPr();

    public void unsetLvl5PPr();

    public CTTextParagraphProperties getLvl6PPr();

    public boolean isSetLvl6PPr();

    public void setLvl6PPr(CTTextParagraphProperties var1);

    public CTTextParagraphProperties addNewLvl6PPr();

    public void unsetLvl6PPr();

    public CTTextParagraphProperties getLvl7PPr();

    public boolean isSetLvl7PPr();

    public void setLvl7PPr(CTTextParagraphProperties var1);

    public CTTextParagraphProperties addNewLvl7PPr();

    public void unsetLvl7PPr();

    public CTTextParagraphProperties getLvl8PPr();

    public boolean isSetLvl8PPr();

    public void setLvl8PPr(CTTextParagraphProperties var1);

    public CTTextParagraphProperties addNewLvl8PPr();

    public void unsetLvl8PPr();

    public CTTextParagraphProperties getLvl9PPr();

    public boolean isSetLvl9PPr();

    public void setLvl9PPr(CTTextParagraphProperties var1);

    public CTTextParagraphProperties addNewLvl9PPr();

    public void unsetLvl9PPr();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTextListStyle.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTextListStyle newInstance() {
            return (CTTextListStyle)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTextListStyle newInstance(XmlOptions xmlOptions) {
            return (CTTextListStyle)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTextListStyle parse(String string) throws XmlException {
            return (CTTextListStyle)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTextListStyle parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTextListStyle)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTextListStyle parse(File file) throws XmlException, IOException {
            return (CTTextListStyle)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTextListStyle parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextListStyle)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTextListStyle parse(URL uRL) throws XmlException, IOException {
            return (CTTextListStyle)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTextListStyle parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextListStyle)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTextListStyle parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTextListStyle)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTextListStyle parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextListStyle)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTextListStyle parse(Reader reader) throws XmlException, IOException {
            return (CTTextListStyle)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTextListStyle parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextListStyle)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTextListStyle parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTextListStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTextListStyle parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTextListStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTextListStyle parse(Node node) throws XmlException {
            return (CTTextListStyle)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTextListStyle parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTextListStyle)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTextListStyle parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTextListStyle)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTextListStyle parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTextListStyle)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

