/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;

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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDouble;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayoutMode;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayoutTarget;
import org.w3c.dom.Node;

public interface CTManualLayout
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTManualLayout.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctmanuallayout872ctype");

    public CTLayoutTarget getLayoutTarget();

    public boolean isSetLayoutTarget();

    public void setLayoutTarget(CTLayoutTarget var1);

    public CTLayoutTarget addNewLayoutTarget();

    public void unsetLayoutTarget();

    public CTLayoutMode getXMode();

    public boolean isSetXMode();

    public void setXMode(CTLayoutMode var1);

    public CTLayoutMode addNewXMode();

    public void unsetXMode();

    public CTLayoutMode getYMode();

    public boolean isSetYMode();

    public void setYMode(CTLayoutMode var1);

    public CTLayoutMode addNewYMode();

    public void unsetYMode();

    public CTLayoutMode getWMode();

    public boolean isSetWMode();

    public void setWMode(CTLayoutMode var1);

    public CTLayoutMode addNewWMode();

    public void unsetWMode();

    public CTLayoutMode getHMode();

    public boolean isSetHMode();

    public void setHMode(CTLayoutMode var1);

    public CTLayoutMode addNewHMode();

    public void unsetHMode();

    public CTDouble getX();

    public boolean isSetX();

    public void setX(CTDouble var1);

    public CTDouble addNewX();

    public void unsetX();

    public CTDouble getY();

    public boolean isSetY();

    public void setY(CTDouble var1);

    public CTDouble addNewY();

    public void unsetY();

    public CTDouble getW();

    public boolean isSetW();

    public void setW(CTDouble var1);

    public CTDouble addNewW();

    public void unsetW();

    public CTDouble getH();

    public boolean isSetH();

    public void setH(CTDouble var1);

    public CTDouble addNewH();

    public void unsetH();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTManualLayout.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTManualLayout newInstance() {
            return (CTManualLayout)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTManualLayout newInstance(XmlOptions xmlOptions) {
            return (CTManualLayout)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTManualLayout parse(String string) throws XmlException {
            return (CTManualLayout)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTManualLayout parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTManualLayout)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTManualLayout parse(File file) throws XmlException, IOException {
            return (CTManualLayout)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTManualLayout parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTManualLayout)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTManualLayout parse(URL uRL) throws XmlException, IOException {
            return (CTManualLayout)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTManualLayout parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTManualLayout)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTManualLayout parse(InputStream inputStream) throws XmlException, IOException {
            return (CTManualLayout)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTManualLayout parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTManualLayout)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTManualLayout parse(Reader reader) throws XmlException, IOException {
            return (CTManualLayout)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTManualLayout parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTManualLayout)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTManualLayout parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTManualLayout)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTManualLayout parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTManualLayout)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTManualLayout parse(Node node) throws XmlException {
            return (CTManualLayout)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTManualLayout parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTManualLayout)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTManualLayout parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTManualLayout)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTManualLayout parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTManualLayout)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

