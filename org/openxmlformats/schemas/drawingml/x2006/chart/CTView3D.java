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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDepthPercent;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTHPercent;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPerspective;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRotX;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRotY;
import org.w3c.dom.Node;

public interface CTView3D
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTView3D.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctview3daf66type");

    public CTRotX getRotX();

    public boolean isSetRotX();

    public void setRotX(CTRotX var1);

    public CTRotX addNewRotX();

    public void unsetRotX();

    public CTHPercent getHPercent();

    public boolean isSetHPercent();

    public void setHPercent(CTHPercent var1);

    public CTHPercent addNewHPercent();

    public void unsetHPercent();

    public CTRotY getRotY();

    public boolean isSetRotY();

    public void setRotY(CTRotY var1);

    public CTRotY addNewRotY();

    public void unsetRotY();

    public CTDepthPercent getDepthPercent();

    public boolean isSetDepthPercent();

    public void setDepthPercent(CTDepthPercent var1);

    public CTDepthPercent addNewDepthPercent();

    public void unsetDepthPercent();

    public CTBoolean getRAngAx();

    public boolean isSetRAngAx();

    public void setRAngAx(CTBoolean var1);

    public CTBoolean addNewRAngAx();

    public void unsetRAngAx();

    public CTPerspective getPerspective();

    public boolean isSetPerspective();

    public void setPerspective(CTPerspective var1);

    public CTPerspective addNewPerspective();

    public void unsetPerspective();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTView3D.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTView3D newInstance() {
            return (CTView3D)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTView3D newInstance(XmlOptions xmlOptions) {
            return (CTView3D)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTView3D parse(String string) throws XmlException {
            return (CTView3D)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTView3D parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTView3D)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTView3D parse(File file) throws XmlException, IOException {
            return (CTView3D)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTView3D parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTView3D)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTView3D parse(URL uRL) throws XmlException, IOException {
            return (CTView3D)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTView3D parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTView3D)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTView3D parse(InputStream inputStream) throws XmlException, IOException {
            return (CTView3D)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTView3D parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTView3D)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTView3D parse(Reader reader) throws XmlException, IOException {
            return (CTView3D)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTView3D parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTView3D)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTView3D parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTView3D)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTView3D parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTView3D)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTView3D parse(Node node) throws XmlException {
            return (CTView3D)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTView3D parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTView3D)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTView3D parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTView3D)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTView3D parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTView3D)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

