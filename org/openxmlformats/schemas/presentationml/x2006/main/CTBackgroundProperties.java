/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.presentationml.x2006.main;

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
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList;
import org.w3c.dom.Node;

public interface CTBackgroundProperties
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTBackgroundProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctbackgroundpropertiesb184type");

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

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public boolean getShadeToTitle();

    public XmlBoolean xgetShadeToTitle();

    public boolean isSetShadeToTitle();

    public void setShadeToTitle(boolean var1);

    public void xsetShadeToTitle(XmlBoolean var1);

    public void unsetShadeToTitle();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTBackgroundProperties.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTBackgroundProperties newInstance() {
            return (CTBackgroundProperties)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTBackgroundProperties newInstance(XmlOptions xmlOptions) {
            return (CTBackgroundProperties)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTBackgroundProperties parse(String string) throws XmlException {
            return (CTBackgroundProperties)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTBackgroundProperties parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTBackgroundProperties)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTBackgroundProperties parse(File file) throws XmlException, IOException {
            return (CTBackgroundProperties)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTBackgroundProperties parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBackgroundProperties)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTBackgroundProperties parse(URL uRL) throws XmlException, IOException {
            return (CTBackgroundProperties)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTBackgroundProperties parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBackgroundProperties)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTBackgroundProperties parse(InputStream inputStream) throws XmlException, IOException {
            return (CTBackgroundProperties)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTBackgroundProperties parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBackgroundProperties)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTBackgroundProperties parse(Reader reader) throws XmlException, IOException {
            return (CTBackgroundProperties)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTBackgroundProperties parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBackgroundProperties)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTBackgroundProperties parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTBackgroundProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTBackgroundProperties parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTBackgroundProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTBackgroundProperties parse(Node node) throws XmlException {
            return (CTBackgroundProperties)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTBackgroundProperties parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTBackgroundProperties)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTBackgroundProperties parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTBackgroundProperties)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTBackgroundProperties parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTBackgroundProperties)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

