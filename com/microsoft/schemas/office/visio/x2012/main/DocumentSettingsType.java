/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.visio.x2012.main.AttachedToolbarsType
 *  com.microsoft.schemas.office.visio.x2012.main.CustomMenusFileType
 *  com.microsoft.schemas.office.visio.x2012.main.CustomToolbarsFileType
 *  com.microsoft.schemas.office.visio.x2012.main.DynamicGridEnabledType
 *  com.microsoft.schemas.office.visio.x2012.main.GlueSettingsType
 *  com.microsoft.schemas.office.visio.x2012.main.ProtectBkgndsType
 *  com.microsoft.schemas.office.visio.x2012.main.ProtectMastersType
 *  com.microsoft.schemas.office.visio.x2012.main.ProtectShapesType
 *  com.microsoft.schemas.office.visio.x2012.main.ProtectStylesType
 *  com.microsoft.schemas.office.visio.x2012.main.SnapAnglesType
 *  com.microsoft.schemas.office.visio.x2012.main.SnapExtensionsType
 *  com.microsoft.schemas.office.visio.x2012.main.SnapSettingsType
 */
package com.microsoft.schemas.office.visio.x2012.main;

import com.microsoft.schemas.office.visio.x2012.main.AttachedToolbarsType;
import com.microsoft.schemas.office.visio.x2012.main.CustomMenusFileType;
import com.microsoft.schemas.office.visio.x2012.main.CustomToolbarsFileType;
import com.microsoft.schemas.office.visio.x2012.main.DynamicGridEnabledType;
import com.microsoft.schemas.office.visio.x2012.main.GlueSettingsType;
import com.microsoft.schemas.office.visio.x2012.main.ProtectBkgndsType;
import com.microsoft.schemas.office.visio.x2012.main.ProtectMastersType;
import com.microsoft.schemas.office.visio.x2012.main.ProtectShapesType;
import com.microsoft.schemas.office.visio.x2012.main.ProtectStylesType;
import com.microsoft.schemas.office.visio.x2012.main.SnapAnglesType;
import com.microsoft.schemas.office.visio.x2012.main.SnapExtensionsType;
import com.microsoft.schemas.office.visio.x2012.main.SnapSettingsType;
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
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface DocumentSettingsType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(DocumentSettingsType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("documentsettingstype945btype");

    public GlueSettingsType getGlueSettings();

    public boolean isSetGlueSettings();

    public void setGlueSettings(GlueSettingsType var1);

    public GlueSettingsType addNewGlueSettings();

    public void unsetGlueSettings();

    public SnapSettingsType getSnapSettings();

    public boolean isSetSnapSettings();

    public void setSnapSettings(SnapSettingsType var1);

    public SnapSettingsType addNewSnapSettings();

    public void unsetSnapSettings();

    public SnapExtensionsType getSnapExtensions();

    public boolean isSetSnapExtensions();

    public void setSnapExtensions(SnapExtensionsType var1);

    public SnapExtensionsType addNewSnapExtensions();

    public void unsetSnapExtensions();

    public SnapAnglesType getSnapAngles();

    public boolean isSetSnapAngles();

    public void setSnapAngles(SnapAnglesType var1);

    public SnapAnglesType addNewSnapAngles();

    public void unsetSnapAngles();

    public DynamicGridEnabledType getDynamicGridEnabled();

    public boolean isSetDynamicGridEnabled();

    public void setDynamicGridEnabled(DynamicGridEnabledType var1);

    public DynamicGridEnabledType addNewDynamicGridEnabled();

    public void unsetDynamicGridEnabled();

    public ProtectStylesType getProtectStyles();

    public boolean isSetProtectStyles();

    public void setProtectStyles(ProtectStylesType var1);

    public ProtectStylesType addNewProtectStyles();

    public void unsetProtectStyles();

    public ProtectShapesType getProtectShapes();

    public boolean isSetProtectShapes();

    public void setProtectShapes(ProtectShapesType var1);

    public ProtectShapesType addNewProtectShapes();

    public void unsetProtectShapes();

    public ProtectMastersType getProtectMasters();

    public boolean isSetProtectMasters();

    public void setProtectMasters(ProtectMastersType var1);

    public ProtectMastersType addNewProtectMasters();

    public void unsetProtectMasters();

    public ProtectBkgndsType getProtectBkgnds();

    public boolean isSetProtectBkgnds();

    public void setProtectBkgnds(ProtectBkgndsType var1);

    public ProtectBkgndsType addNewProtectBkgnds();

    public void unsetProtectBkgnds();

    public CustomMenusFileType getCustomMenusFile();

    public boolean isSetCustomMenusFile();

    public void setCustomMenusFile(CustomMenusFileType var1);

    public CustomMenusFileType addNewCustomMenusFile();

    public void unsetCustomMenusFile();

    public CustomToolbarsFileType getCustomToolbarsFile();

    public boolean isSetCustomToolbarsFile();

    public void setCustomToolbarsFile(CustomToolbarsFileType var1);

    public CustomToolbarsFileType addNewCustomToolbarsFile();

    public void unsetCustomToolbarsFile();

    public AttachedToolbarsType getAttachedToolbars();

    public boolean isSetAttachedToolbars();

    public void setAttachedToolbars(AttachedToolbarsType var1);

    public AttachedToolbarsType addNewAttachedToolbars();

    public void unsetAttachedToolbars();

    public long getTopPage();

    public XmlUnsignedInt xgetTopPage();

    public boolean isSetTopPage();

    public void setTopPage(long var1);

    public void xsetTopPage(XmlUnsignedInt var1);

    public void unsetTopPage();

    public long getDefaultTextStyle();

    public XmlUnsignedInt xgetDefaultTextStyle();

    public boolean isSetDefaultTextStyle();

    public void setDefaultTextStyle(long var1);

    public void xsetDefaultTextStyle(XmlUnsignedInt var1);

    public void unsetDefaultTextStyle();

    public long getDefaultLineStyle();

    public XmlUnsignedInt xgetDefaultLineStyle();

    public boolean isSetDefaultLineStyle();

    public void setDefaultLineStyle(long var1);

    public void xsetDefaultLineStyle(XmlUnsignedInt var1);

    public void unsetDefaultLineStyle();

    public long getDefaultFillStyle();

    public XmlUnsignedInt xgetDefaultFillStyle();

    public boolean isSetDefaultFillStyle();

    public void setDefaultFillStyle(long var1);

    public void xsetDefaultFillStyle(XmlUnsignedInt var1);

    public void unsetDefaultFillStyle();

    public long getDefaultGuideStyle();

    public XmlUnsignedInt xgetDefaultGuideStyle();

    public boolean isSetDefaultGuideStyle();

    public void setDefaultGuideStyle(long var1);

    public void xsetDefaultGuideStyle(XmlUnsignedInt var1);

    public void unsetDefaultGuideStyle();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(DocumentSettingsType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static DocumentSettingsType newInstance() {
            return (DocumentSettingsType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static DocumentSettingsType newInstance(XmlOptions xmlOptions) {
            return (DocumentSettingsType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static DocumentSettingsType parse(String string) throws XmlException {
            return (DocumentSettingsType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static DocumentSettingsType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (DocumentSettingsType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static DocumentSettingsType parse(File file) throws XmlException, IOException {
            return (DocumentSettingsType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static DocumentSettingsType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (DocumentSettingsType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static DocumentSettingsType parse(URL uRL) throws XmlException, IOException {
            return (DocumentSettingsType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static DocumentSettingsType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (DocumentSettingsType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static DocumentSettingsType parse(InputStream inputStream) throws XmlException, IOException {
            return (DocumentSettingsType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static DocumentSettingsType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (DocumentSettingsType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static DocumentSettingsType parse(Reader reader) throws XmlException, IOException {
            return (DocumentSettingsType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static DocumentSettingsType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (DocumentSettingsType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static DocumentSettingsType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (DocumentSettingsType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static DocumentSettingsType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (DocumentSettingsType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static DocumentSettingsType parse(Node node) throws XmlException {
            return (DocumentSettingsType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static DocumentSettingsType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (DocumentSettingsType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static DocumentSettingsType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (DocumentSettingsType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static DocumentSettingsType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (DocumentSettingsType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

