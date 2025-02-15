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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.STDrawingElementId;
import org.w3c.dom.Node;

public interface CTNonVisualDrawingProps
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTNonVisualDrawingProps.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctnonvisualdrawingprops8fb0type");

    public CTHyperlink getHlinkClick();

    public boolean isSetHlinkClick();

    public void setHlinkClick(CTHyperlink var1);

    public CTHyperlink addNewHlinkClick();

    public void unsetHlinkClick();

    public CTHyperlink getHlinkHover();

    public boolean isSetHlinkHover();

    public void setHlinkHover(CTHyperlink var1);

    public CTHyperlink addNewHlinkHover();

    public void unsetHlinkHover();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public long getId();

    public STDrawingElementId xgetId();

    public void setId(long var1);

    public void xsetId(STDrawingElementId var1);

    public String getName();

    public XmlString xgetName();

    public void setName(String var1);

    public void xsetName(XmlString var1);

    public String getDescr();

    public XmlString xgetDescr();

    public boolean isSetDescr();

    public void setDescr(String var1);

    public void xsetDescr(XmlString var1);

    public void unsetDescr();

    public boolean getHidden();

    public XmlBoolean xgetHidden();

    public boolean isSetHidden();

    public void setHidden(boolean var1);

    public void xsetHidden(XmlBoolean var1);

    public void unsetHidden();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTNonVisualDrawingProps.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTNonVisualDrawingProps newInstance() {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTNonVisualDrawingProps newInstance(XmlOptions xmlOptions) {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTNonVisualDrawingProps parse(String string) throws XmlException {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTNonVisualDrawingProps parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTNonVisualDrawingProps parse(File file) throws XmlException, IOException {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTNonVisualDrawingProps parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTNonVisualDrawingProps parse(URL uRL) throws XmlException, IOException {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTNonVisualDrawingProps parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTNonVisualDrawingProps parse(InputStream inputStream) throws XmlException, IOException {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTNonVisualDrawingProps parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTNonVisualDrawingProps parse(Reader reader) throws XmlException, IOException {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTNonVisualDrawingProps parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTNonVisualDrawingProps parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTNonVisualDrawingProps parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTNonVisualDrawingProps parse(Node node) throws XmlException {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTNonVisualDrawingProps parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTNonVisualDrawingProps parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTNonVisualDrawingProps parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTNonVisualDrawingProps)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

