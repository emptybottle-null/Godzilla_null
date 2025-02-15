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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayout;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTx;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.w3c.dom.Node;

public interface CTTitle
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTitle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttitleb54etype");

    public CTTx getTx();

    public boolean isSetTx();

    public void setTx(CTTx var1);

    public CTTx addNewTx();

    public void unsetTx();

    public CTLayout getLayout();

    public boolean isSetLayout();

    public void setLayout(CTLayout var1);

    public CTLayout addNewLayout();

    public void unsetLayout();

    public CTBoolean getOverlay();

    public boolean isSetOverlay();

    public void setOverlay(CTBoolean var1);

    public CTBoolean addNewOverlay();

    public void unsetOverlay();

    public CTShapeProperties getSpPr();

    public boolean isSetSpPr();

    public void setSpPr(CTShapeProperties var1);

    public CTShapeProperties addNewSpPr();

    public void unsetSpPr();

    public CTTextBody getTxPr();

    public boolean isSetTxPr();

    public void setTxPr(CTTextBody var1);

    public CTTextBody addNewTxPr();

    public void unsetTxPr();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTitle.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTitle newInstance() {
            return (CTTitle)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTitle newInstance(XmlOptions xmlOptions) {
            return (CTTitle)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTitle parse(String string) throws XmlException {
            return (CTTitle)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTitle parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTitle)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTitle parse(File file) throws XmlException, IOException {
            return (CTTitle)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTitle parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTitle)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTitle parse(URL uRL) throws XmlException, IOException {
            return (CTTitle)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTitle parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTitle)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTitle parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTitle)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTitle parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTitle)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTitle parse(Reader reader) throws XmlException, IOException {
            return (CTTitle)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTitle parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTitle)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTitle parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTitle)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTitle parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTitle)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTitle parse(Node node) throws XmlException {
            return (CTTitle)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTitle parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTitle)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTitle parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTitle)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTitle parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTitle)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

