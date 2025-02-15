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
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontCollection;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;
import org.openxmlformats.schemas.drawingml.x2006.main.STOnOffStyleType;
import org.w3c.dom.Node;

public interface CTTableStyleTextStyle
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTableStyleTextStyle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttablestyletextstylec932type");

    public CTFontCollection getFont();

    public boolean isSetFont();

    public void setFont(CTFontCollection var1);

    public CTFontCollection addNewFont();

    public void unsetFont();

    public CTFontReference getFontRef();

    public boolean isSetFontRef();

    public void setFontRef(CTFontReference var1);

    public CTFontReference addNewFontRef();

    public void unsetFontRef();

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

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public STOnOffStyleType.Enum getB();

    public STOnOffStyleType xgetB();

    public boolean isSetB();

    public void setB(STOnOffStyleType.Enum var1);

    public void xsetB(STOnOffStyleType var1);

    public void unsetB();

    public STOnOffStyleType.Enum getI();

    public STOnOffStyleType xgetI();

    public boolean isSetI();

    public void setI(STOnOffStyleType.Enum var1);

    public void xsetI(STOnOffStyleType var1);

    public void unsetI();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTableStyleTextStyle.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTableStyleTextStyle newInstance() {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTableStyleTextStyle newInstance(XmlOptions xmlOptions) {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTableStyleTextStyle parse(String string) throws XmlException {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTableStyleTextStyle parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTableStyleTextStyle parse(File file) throws XmlException, IOException {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTableStyleTextStyle parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTableStyleTextStyle parse(URL uRL) throws XmlException, IOException {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTableStyleTextStyle parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTableStyleTextStyle parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTableStyleTextStyle parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTableStyleTextStyle parse(Reader reader) throws XmlException, IOException {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTableStyleTextStyle parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTableStyleTextStyle parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTableStyleTextStyle parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTableStyleTextStyle parse(Node node) throws XmlException {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTableStyleTextStyle parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTableStyleTextStyle parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTableStyleTextStyle parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTableStyleTextStyle)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

