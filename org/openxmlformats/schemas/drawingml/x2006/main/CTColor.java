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
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;
import org.w3c.dom.Node;

public interface CTColor
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTColor.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcolorb114type");

    public CTScRgbColor getScrgbClr();

    public boolean isSetScrgbClr();

    public void setScrgbClr(CTScRgbColor var1);

    public CTScRgbColor addNewScrgbClr();

    public void unsetScrgbClr();

    public CTSRgbColor getSrgbClr();

    public boolean isSetSrgbClr();

    public void setSrgbClr(CTSRgbColor var1);

    public CTSRgbColor addNewSrgbClr();

    public void unsetSrgbClr();

    public CTHslColor getHslClr();

    public boolean isSetHslClr();

    public void setHslClr(CTHslColor var1);

    public CTHslColor addNewHslClr();

    public void unsetHslClr();

    public CTSystemColor getSysClr();

    public boolean isSetSysClr();

    public void setSysClr(CTSystemColor var1);

    public CTSystemColor addNewSysClr();

    public void unsetSysClr();

    public CTSchemeColor getSchemeClr();

    public boolean isSetSchemeClr();

    public void setSchemeClr(CTSchemeColor var1);

    public CTSchemeColor addNewSchemeClr();

    public void unsetSchemeClr();

    public CTPresetColor getPrstClr();

    public boolean isSetPrstClr();

    public void setPrstClr(CTPresetColor var1);

    public CTPresetColor addNewPrstClr();

    public void unsetPrstClr();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTColor.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTColor newInstance() {
            return (CTColor)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTColor newInstance(XmlOptions xmlOptions) {
            return (CTColor)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTColor parse(String string) throws XmlException {
            return (CTColor)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTColor parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTColor)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTColor parse(File file) throws XmlException, IOException {
            return (CTColor)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTColor parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColor)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTColor parse(URL uRL) throws XmlException, IOException {
            return (CTColor)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTColor parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColor)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTColor parse(InputStream inputStream) throws XmlException, IOException {
            return (CTColor)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTColor parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColor)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTColor parse(Reader reader) throws XmlException, IOException {
            return (CTColor)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTColor parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTColor)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTColor parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTColor)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTColor parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTColor)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTColor parse(Node node) throws XmlException {
            return (CTColor)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTColor parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTColor)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTColor parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTColor)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTColor parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTColor)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

