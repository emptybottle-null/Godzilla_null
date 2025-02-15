/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.STFixedAngle
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
import org.apache.xmlbeans.XmlBoolean;
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
import org.openxmlformats.schemas.drawingml.x2006.main.STFixedAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveFixedAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.STRectAlignment;
import org.w3c.dom.Node;

public interface CTOuterShadowEffect
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTOuterShadowEffect.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctoutershadoweffect7b5dtype");

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

    public long getBlurRad();

    public STPositiveCoordinate xgetBlurRad();

    public boolean isSetBlurRad();

    public void setBlurRad(long var1);

    public void xsetBlurRad(STPositiveCoordinate var1);

    public void unsetBlurRad();

    public long getDist();

    public STPositiveCoordinate xgetDist();

    public boolean isSetDist();

    public void setDist(long var1);

    public void xsetDist(STPositiveCoordinate var1);

    public void unsetDist();

    public int getDir();

    public STPositiveFixedAngle xgetDir();

    public boolean isSetDir();

    public void setDir(int var1);

    public void xsetDir(STPositiveFixedAngle var1);

    public void unsetDir();

    public int getSx();

    public STPercentage xgetSx();

    public boolean isSetSx();

    public void setSx(int var1);

    public void xsetSx(STPercentage var1);

    public void unsetSx();

    public int getSy();

    public STPercentage xgetSy();

    public boolean isSetSy();

    public void setSy(int var1);

    public void xsetSy(STPercentage var1);

    public void unsetSy();

    public int getKx();

    public STFixedAngle xgetKx();

    public boolean isSetKx();

    public void setKx(int var1);

    public void xsetKx(STFixedAngle var1);

    public void unsetKx();

    public int getKy();

    public STFixedAngle xgetKy();

    public boolean isSetKy();

    public void setKy(int var1);

    public void xsetKy(STFixedAngle var1);

    public void unsetKy();

    public STRectAlignment.Enum getAlgn();

    public STRectAlignment xgetAlgn();

    public boolean isSetAlgn();

    public void setAlgn(STRectAlignment.Enum var1);

    public void xsetAlgn(STRectAlignment var1);

    public void unsetAlgn();

    public boolean getRotWithShape();

    public XmlBoolean xgetRotWithShape();

    public boolean isSetRotWithShape();

    public void setRotWithShape(boolean var1);

    public void xsetRotWithShape(XmlBoolean var1);

    public void unsetRotWithShape();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTOuterShadowEffect.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTOuterShadowEffect newInstance() {
            return (CTOuterShadowEffect)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTOuterShadowEffect newInstance(XmlOptions xmlOptions) {
            return (CTOuterShadowEffect)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTOuterShadowEffect parse(String string) throws XmlException {
            return (CTOuterShadowEffect)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTOuterShadowEffect parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTOuterShadowEffect)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTOuterShadowEffect parse(File file) throws XmlException, IOException {
            return (CTOuterShadowEffect)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTOuterShadowEffect parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOuterShadowEffect)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTOuterShadowEffect parse(URL uRL) throws XmlException, IOException {
            return (CTOuterShadowEffect)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTOuterShadowEffect parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOuterShadowEffect)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTOuterShadowEffect parse(InputStream inputStream) throws XmlException, IOException {
            return (CTOuterShadowEffect)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTOuterShadowEffect parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOuterShadowEffect)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTOuterShadowEffect parse(Reader reader) throws XmlException, IOException {
            return (CTOuterShadowEffect)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTOuterShadowEffect parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTOuterShadowEffect)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTOuterShadowEffect parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTOuterShadowEffect)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTOuterShadowEffect parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTOuterShadowEffect)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTOuterShadowEffect parse(Node node) throws XmlException {
            return (CTOuterShadowEffect)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTOuterShadowEffect parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTOuterShadowEffect)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTOuterShadowEffect parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTOuterShadowEffect)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTOuterShadowEffect parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTOuterShadowEffect)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

