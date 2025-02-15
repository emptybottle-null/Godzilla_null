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
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode;
import org.w3c.dom.Node;

public interface CTGroupShapeProperties
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTGroupShapeProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctgroupshapeproperties8690type");

    public CTGroupTransform2D getXfrm();

    public boolean isSetXfrm();

    public void setXfrm(CTGroupTransform2D var1);

    public CTGroupTransform2D addNewXfrm();

    public void unsetXfrm();

    public CTNoFillProperties getNoFill();

    public boolean isSetNoFill();

    public void setNoFill(CTNoFillProperties var1);

    public CTNoFillProperties addNewNoFill();

    public void unsetNoFill();

    public CTSolidColorFillProperties getSolidFill();

    public boolean isSetSolidFill();

    public void setSolidFill(CTSolidColorFillProperties var1);

    public CTSolidColorFillProperties addNewSolidFill();

    public void unsetSolidFill();

    public CTGradientFillProperties getGradFill();

    public boolean isSetGradFill();

    public void setGradFill(CTGradientFillProperties var1);

    public CTGradientFillProperties addNewGradFill();

    public void unsetGradFill();

    public CTBlipFillProperties getBlipFill();

    public boolean isSetBlipFill();

    public void setBlipFill(CTBlipFillProperties var1);

    public CTBlipFillProperties addNewBlipFill();

    public void unsetBlipFill();

    public CTPatternFillProperties getPattFill();

    public boolean isSetPattFill();

    public void setPattFill(CTPatternFillProperties var1);

    public CTPatternFillProperties addNewPattFill();

    public void unsetPattFill();

    public CTGroupFillProperties getGrpFill();

    public boolean isSetGrpFill();

    public void setGrpFill(CTGroupFillProperties var1);

    public CTGroupFillProperties addNewGrpFill();

    public void unsetGrpFill();

    public CTEffectList getEffectLst();

    public boolean isSetEffectLst();

    public void setEffectLst(CTEffectList var1);

    public CTEffectList addNewEffectLst();

    public void unsetEffectLst();

    public CTEffectContainer getEffectDag();

    public boolean isSetEffectDag();

    public void setEffectDag(CTEffectContainer var1);

    public CTEffectContainer addNewEffectDag();

    public void unsetEffectDag();

    public CTScene3D getScene3D();

    public boolean isSetScene3D();

    public void setScene3D(CTScene3D var1);

    public CTScene3D addNewScene3D();

    public void unsetScene3D();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public STBlackWhiteMode.Enum getBwMode();

    public STBlackWhiteMode xgetBwMode();

    public boolean isSetBwMode();

    public void setBwMode(STBlackWhiteMode.Enum var1);

    public void xsetBwMode(STBlackWhiteMode var1);

    public void unsetBwMode();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTGroupShapeProperties.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTGroupShapeProperties newInstance() {
            return (CTGroupShapeProperties)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTGroupShapeProperties newInstance(XmlOptions xmlOptions) {
            return (CTGroupShapeProperties)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTGroupShapeProperties parse(String string) throws XmlException {
            return (CTGroupShapeProperties)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTGroupShapeProperties parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTGroupShapeProperties)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTGroupShapeProperties parse(File file) throws XmlException, IOException {
            return (CTGroupShapeProperties)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTGroupShapeProperties parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGroupShapeProperties)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTGroupShapeProperties parse(URL uRL) throws XmlException, IOException {
            return (CTGroupShapeProperties)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTGroupShapeProperties parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGroupShapeProperties)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTGroupShapeProperties parse(InputStream inputStream) throws XmlException, IOException {
            return (CTGroupShapeProperties)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTGroupShapeProperties parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGroupShapeProperties)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTGroupShapeProperties parse(Reader reader) throws XmlException, IOException {
            return (CTGroupShapeProperties)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTGroupShapeProperties parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTGroupShapeProperties)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTGroupShapeProperties parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTGroupShapeProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTGroupShapeProperties parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTGroupShapeProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTGroupShapeProperties parse(Node node) throws XmlException {
            return (CTGroupShapeProperties)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTGroupShapeProperties parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTGroupShapeProperties)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTGroupShapeProperties parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTGroupShapeProperties)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTGroupShapeProperties parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTGroupShapeProperties)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

