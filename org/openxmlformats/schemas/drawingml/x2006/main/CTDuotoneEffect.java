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
import java.util.List;
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

public interface CTDuotoneEffect
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTDuotoneEffect.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctduotoneeffectae52type");

    public List<CTScRgbColor> getScrgbClrList();

    @Deprecated
    public CTScRgbColor[] getScrgbClrArray();

    public CTScRgbColor getScrgbClrArray(int var1);

    public int sizeOfScrgbClrArray();

    public void setScrgbClrArray(CTScRgbColor[] var1);

    public void setScrgbClrArray(int var1, CTScRgbColor var2);

    public CTScRgbColor insertNewScrgbClr(int var1);

    public CTScRgbColor addNewScrgbClr();

    public void removeScrgbClr(int var1);

    public List<CTSRgbColor> getSrgbClrList();

    @Deprecated
    public CTSRgbColor[] getSrgbClrArray();

    public CTSRgbColor getSrgbClrArray(int var1);

    public int sizeOfSrgbClrArray();

    public void setSrgbClrArray(CTSRgbColor[] var1);

    public void setSrgbClrArray(int var1, CTSRgbColor var2);

    public CTSRgbColor insertNewSrgbClr(int var1);

    public CTSRgbColor addNewSrgbClr();

    public void removeSrgbClr(int var1);

    public List<CTHslColor> getHslClrList();

    @Deprecated
    public CTHslColor[] getHslClrArray();

    public CTHslColor getHslClrArray(int var1);

    public int sizeOfHslClrArray();

    public void setHslClrArray(CTHslColor[] var1);

    public void setHslClrArray(int var1, CTHslColor var2);

    public CTHslColor insertNewHslClr(int var1);

    public CTHslColor addNewHslClr();

    public void removeHslClr(int var1);

    public List<CTSystemColor> getSysClrList();

    @Deprecated
    public CTSystemColor[] getSysClrArray();

    public CTSystemColor getSysClrArray(int var1);

    public int sizeOfSysClrArray();

    public void setSysClrArray(CTSystemColor[] var1);

    public void setSysClrArray(int var1, CTSystemColor var2);

    public CTSystemColor insertNewSysClr(int var1);

    public CTSystemColor addNewSysClr();

    public void removeSysClr(int var1);

    public List<CTSchemeColor> getSchemeClrList();

    @Deprecated
    public CTSchemeColor[] getSchemeClrArray();

    public CTSchemeColor getSchemeClrArray(int var1);

    public int sizeOfSchemeClrArray();

    public void setSchemeClrArray(CTSchemeColor[] var1);

    public void setSchemeClrArray(int var1, CTSchemeColor var2);

    public CTSchemeColor insertNewSchemeClr(int var1);

    public CTSchemeColor addNewSchemeClr();

    public void removeSchemeClr(int var1);

    public List<CTPresetColor> getPrstClrList();

    @Deprecated
    public CTPresetColor[] getPrstClrArray();

    public CTPresetColor getPrstClrArray(int var1);

    public int sizeOfPrstClrArray();

    public void setPrstClrArray(CTPresetColor[] var1);

    public void setPrstClrArray(int var1, CTPresetColor var2);

    public CTPresetColor insertNewPrstClr(int var1);

    public CTPresetColor addNewPrstClr();

    public void removePrstClr(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTDuotoneEffect.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTDuotoneEffect newInstance() {
            return (CTDuotoneEffect)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTDuotoneEffect newInstance(XmlOptions xmlOptions) {
            return (CTDuotoneEffect)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTDuotoneEffect parse(String string) throws XmlException {
            return (CTDuotoneEffect)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTDuotoneEffect parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTDuotoneEffect)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTDuotoneEffect parse(File file) throws XmlException, IOException {
            return (CTDuotoneEffect)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTDuotoneEffect parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDuotoneEffect)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTDuotoneEffect parse(URL uRL) throws XmlException, IOException {
            return (CTDuotoneEffect)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTDuotoneEffect parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDuotoneEffect)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTDuotoneEffect parse(InputStream inputStream) throws XmlException, IOException {
            return (CTDuotoneEffect)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTDuotoneEffect parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDuotoneEffect)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTDuotoneEffect parse(Reader reader) throws XmlException, IOException {
            return (CTDuotoneEffect)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTDuotoneEffect parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDuotoneEffect)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTDuotoneEffect parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTDuotoneEffect)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTDuotoneEffect parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTDuotoneEffect)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTDuotoneEffect parse(Node node) throws XmlException {
            return (CTDuotoneEffect)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTDuotoneEffect parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTDuotoneEffect)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTDuotoneEffect parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTDuotoneEffect)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTDuotoneEffect parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTDuotoneEffect)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

