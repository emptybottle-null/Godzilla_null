/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBarType
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTErrDir
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTErrValType
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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDouble;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBarType;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrDir;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrValType;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.w3c.dom.Node;

public interface CTErrBars
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTErrBars.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cterrbarsa201type");

    public CTErrDir getErrDir();

    public boolean isSetErrDir();

    public void setErrDir(CTErrDir var1);

    public CTErrDir addNewErrDir();

    public void unsetErrDir();

    public CTErrBarType getErrBarType();

    public void setErrBarType(CTErrBarType var1);

    public CTErrBarType addNewErrBarType();

    public CTErrValType getErrValType();

    public void setErrValType(CTErrValType var1);

    public CTErrValType addNewErrValType();

    public CTBoolean getNoEndCap();

    public boolean isSetNoEndCap();

    public void setNoEndCap(CTBoolean var1);

    public CTBoolean addNewNoEndCap();

    public void unsetNoEndCap();

    public CTNumDataSource getPlus();

    public boolean isSetPlus();

    public void setPlus(CTNumDataSource var1);

    public CTNumDataSource addNewPlus();

    public void unsetPlus();

    public CTNumDataSource getMinus();

    public boolean isSetMinus();

    public void setMinus(CTNumDataSource var1);

    public CTNumDataSource addNewMinus();

    public void unsetMinus();

    public CTDouble getVal();

    public boolean isSetVal();

    public void setVal(CTDouble var1);

    public CTDouble addNewVal();

    public void unsetVal();

    public CTShapeProperties getSpPr();

    public boolean isSetSpPr();

    public void setSpPr(CTShapeProperties var1);

    public CTShapeProperties addNewSpPr();

    public void unsetSpPr();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTErrBars.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTErrBars newInstance() {
            return (CTErrBars)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTErrBars newInstance(XmlOptions xmlOptions) {
            return (CTErrBars)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTErrBars parse(String string) throws XmlException {
            return (CTErrBars)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTErrBars parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTErrBars)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTErrBars parse(File file) throws XmlException, IOException {
            return (CTErrBars)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTErrBars parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTErrBars)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTErrBars parse(URL uRL) throws XmlException, IOException {
            return (CTErrBars)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTErrBars parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTErrBars)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTErrBars parse(InputStream inputStream) throws XmlException, IOException {
            return (CTErrBars)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTErrBars parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTErrBars)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTErrBars parse(Reader reader) throws XmlException, IOException {
            return (CTErrBars)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTErrBars parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTErrBars)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTErrBars parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTErrBars)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTErrBars parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTErrBars)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTErrBars parse(Node node) throws XmlException {
            return (CTErrBars)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTErrBars parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTErrBars)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTErrBars parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTErrBars)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTErrBars parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTErrBars)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

