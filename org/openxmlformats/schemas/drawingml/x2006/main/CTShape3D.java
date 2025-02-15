/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTBevel
 *  org.openxmlformats.schemas.drawingml.x2006.main.STPresetMaterialType
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
import org.openxmlformats.schemas.drawingml.x2006.main.CTBevel;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetMaterialType;
import org.w3c.dom.Node;

public interface CTShape3D
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTShape3D.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctshape3d6783type");

    public CTBevel getBevelT();

    public boolean isSetBevelT();

    public void setBevelT(CTBevel var1);

    public CTBevel addNewBevelT();

    public void unsetBevelT();

    public CTBevel getBevelB();

    public boolean isSetBevelB();

    public void setBevelB(CTBevel var1);

    public CTBevel addNewBevelB();

    public void unsetBevelB();

    public CTColor getExtrusionClr();

    public boolean isSetExtrusionClr();

    public void setExtrusionClr(CTColor var1);

    public CTColor addNewExtrusionClr();

    public void unsetExtrusionClr();

    public CTColor getContourClr();

    public boolean isSetContourClr();

    public void setContourClr(CTColor var1);

    public CTColor addNewContourClr();

    public void unsetContourClr();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public long getZ();

    public STCoordinate xgetZ();

    public boolean isSetZ();

    public void setZ(long var1);

    public void xsetZ(STCoordinate var1);

    public void unsetZ();

    public long getExtrusionH();

    public STPositiveCoordinate xgetExtrusionH();

    public boolean isSetExtrusionH();

    public void setExtrusionH(long var1);

    public void xsetExtrusionH(STPositiveCoordinate var1);

    public void unsetExtrusionH();

    public long getContourW();

    public STPositiveCoordinate xgetContourW();

    public boolean isSetContourW();

    public void setContourW(long var1);

    public void xsetContourW(STPositiveCoordinate var1);

    public void unsetContourW();

    public STPresetMaterialType.Enum getPrstMaterial();

    public STPresetMaterialType xgetPrstMaterial();

    public boolean isSetPrstMaterial();

    public void setPrstMaterial(STPresetMaterialType.Enum var1);

    public void xsetPrstMaterial(STPresetMaterialType var1);

    public void unsetPrstMaterial();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTShape3D.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTShape3D newInstance() {
            return (CTShape3D)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTShape3D newInstance(XmlOptions xmlOptions) {
            return (CTShape3D)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTShape3D parse(String string) throws XmlException {
            return (CTShape3D)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTShape3D parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTShape3D)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTShape3D parse(File file) throws XmlException, IOException {
            return (CTShape3D)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTShape3D parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShape3D)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTShape3D parse(URL uRL) throws XmlException, IOException {
            return (CTShape3D)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTShape3D parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShape3D)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTShape3D parse(InputStream inputStream) throws XmlException, IOException {
            return (CTShape3D)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTShape3D parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShape3D)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTShape3D parse(Reader reader) throws XmlException, IOException {
            return (CTShape3D)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTShape3D parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTShape3D)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTShape3D parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTShape3D)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTShape3D parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTShape3D)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTShape3D parse(Node node) throws XmlException {
            return (CTShape3D)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTShape3D parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTShape3D)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTShape3D parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTShape3D)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTShape3D parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTShape3D)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

