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
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.STGuid;
import org.w3c.dom.Node;

public interface CTTableProperties
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTableProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttableproperties3512type");

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

    public CTTableStyle getTableStyle();

    public boolean isSetTableStyle();

    public void setTableStyle(CTTableStyle var1);

    public CTTableStyle addNewTableStyle();

    public void unsetTableStyle();

    public String getTableStyleId();

    public STGuid xgetTableStyleId();

    public boolean isSetTableStyleId();

    public void setTableStyleId(String var1);

    public void xsetTableStyleId(STGuid var1);

    public void unsetTableStyleId();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public boolean getRtl();

    public XmlBoolean xgetRtl();

    public boolean isSetRtl();

    public void setRtl(boolean var1);

    public void xsetRtl(XmlBoolean var1);

    public void unsetRtl();

    public boolean getFirstRow();

    public XmlBoolean xgetFirstRow();

    public boolean isSetFirstRow();

    public void setFirstRow(boolean var1);

    public void xsetFirstRow(XmlBoolean var1);

    public void unsetFirstRow();

    public boolean getFirstCol();

    public XmlBoolean xgetFirstCol();

    public boolean isSetFirstCol();

    public void setFirstCol(boolean var1);

    public void xsetFirstCol(XmlBoolean var1);

    public void unsetFirstCol();

    public boolean getLastRow();

    public XmlBoolean xgetLastRow();

    public boolean isSetLastRow();

    public void setLastRow(boolean var1);

    public void xsetLastRow(XmlBoolean var1);

    public void unsetLastRow();

    public boolean getLastCol();

    public XmlBoolean xgetLastCol();

    public boolean isSetLastCol();

    public void setLastCol(boolean var1);

    public void xsetLastCol(XmlBoolean var1);

    public void unsetLastCol();

    public boolean getBandRow();

    public XmlBoolean xgetBandRow();

    public boolean isSetBandRow();

    public void setBandRow(boolean var1);

    public void xsetBandRow(XmlBoolean var1);

    public void unsetBandRow();

    public boolean getBandCol();

    public XmlBoolean xgetBandCol();

    public boolean isSetBandCol();

    public void setBandCol(boolean var1);

    public void xsetBandCol(XmlBoolean var1);

    public void unsetBandCol();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTableProperties.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTableProperties newInstance() {
            return (CTTableProperties)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTableProperties newInstance(XmlOptions xmlOptions) {
            return (CTTableProperties)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTableProperties parse(String string) throws XmlException {
            return (CTTableProperties)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTableProperties parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTableProperties)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTableProperties parse(File file) throws XmlException, IOException {
            return (CTTableProperties)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTableProperties parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableProperties)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTableProperties parse(URL uRL) throws XmlException, IOException {
            return (CTTableProperties)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTableProperties parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableProperties)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTableProperties parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTableProperties)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTableProperties parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableProperties)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTableProperties parse(Reader reader) throws XmlException, IOException {
            return (CTTableProperties)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTableProperties parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableProperties)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTableProperties parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTableProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTableProperties parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTableProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTableProperties parse(Node node) throws XmlException {
            return (CTTableProperties)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTableProperties parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTableProperties)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTableProperties parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTableProperties)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTableProperties parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTableProperties)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

