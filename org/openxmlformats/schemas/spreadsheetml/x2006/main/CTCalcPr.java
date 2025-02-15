/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STRefMode
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

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
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCalcMode;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STRefMode;
import org.w3c.dom.Node;

public interface CTCalcPr
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCalcPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcalcprd480type");

    public long getCalcId();

    public XmlUnsignedInt xgetCalcId();

    public boolean isSetCalcId();

    public void setCalcId(long var1);

    public void xsetCalcId(XmlUnsignedInt var1);

    public void unsetCalcId();

    public STCalcMode.Enum getCalcMode();

    public STCalcMode xgetCalcMode();

    public boolean isSetCalcMode();

    public void setCalcMode(STCalcMode.Enum var1);

    public void xsetCalcMode(STCalcMode var1);

    public void unsetCalcMode();

    public boolean getFullCalcOnLoad();

    public XmlBoolean xgetFullCalcOnLoad();

    public boolean isSetFullCalcOnLoad();

    public void setFullCalcOnLoad(boolean var1);

    public void xsetFullCalcOnLoad(XmlBoolean var1);

    public void unsetFullCalcOnLoad();

    public STRefMode.Enum getRefMode();

    public STRefMode xgetRefMode();

    public boolean isSetRefMode();

    public void setRefMode(STRefMode.Enum var1);

    public void xsetRefMode(STRefMode var1);

    public void unsetRefMode();

    public boolean getIterate();

    public XmlBoolean xgetIterate();

    public boolean isSetIterate();

    public void setIterate(boolean var1);

    public void xsetIterate(XmlBoolean var1);

    public void unsetIterate();

    public long getIterateCount();

    public XmlUnsignedInt xgetIterateCount();

    public boolean isSetIterateCount();

    public void setIterateCount(long var1);

    public void xsetIterateCount(XmlUnsignedInt var1);

    public void unsetIterateCount();

    public double getIterateDelta();

    public XmlDouble xgetIterateDelta();

    public boolean isSetIterateDelta();

    public void setIterateDelta(double var1);

    public void xsetIterateDelta(XmlDouble var1);

    public void unsetIterateDelta();

    public boolean getFullPrecision();

    public XmlBoolean xgetFullPrecision();

    public boolean isSetFullPrecision();

    public void setFullPrecision(boolean var1);

    public void xsetFullPrecision(XmlBoolean var1);

    public void unsetFullPrecision();

    public boolean getCalcCompleted();

    public XmlBoolean xgetCalcCompleted();

    public boolean isSetCalcCompleted();

    public void setCalcCompleted(boolean var1);

    public void xsetCalcCompleted(XmlBoolean var1);

    public void unsetCalcCompleted();

    public boolean getCalcOnSave();

    public XmlBoolean xgetCalcOnSave();

    public boolean isSetCalcOnSave();

    public void setCalcOnSave(boolean var1);

    public void xsetCalcOnSave(XmlBoolean var1);

    public void unsetCalcOnSave();

    public boolean getConcurrentCalc();

    public XmlBoolean xgetConcurrentCalc();

    public boolean isSetConcurrentCalc();

    public void setConcurrentCalc(boolean var1);

    public void xsetConcurrentCalc(XmlBoolean var1);

    public void unsetConcurrentCalc();

    public long getConcurrentManualCount();

    public XmlUnsignedInt xgetConcurrentManualCount();

    public boolean isSetConcurrentManualCount();

    public void setConcurrentManualCount(long var1);

    public void xsetConcurrentManualCount(XmlUnsignedInt var1);

    public void unsetConcurrentManualCount();

    public boolean getForceFullCalc();

    public XmlBoolean xgetForceFullCalc();

    public boolean isSetForceFullCalc();

    public void setForceFullCalc(boolean var1);

    public void xsetForceFullCalc(XmlBoolean var1);

    public void unsetForceFullCalc();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCalcPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCalcPr newInstance() {
            return (CTCalcPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCalcPr newInstance(XmlOptions xmlOptions) {
            return (CTCalcPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCalcPr parse(String string) throws XmlException {
            return (CTCalcPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCalcPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCalcPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCalcPr parse(File file) throws XmlException, IOException {
            return (CTCalcPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCalcPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCalcPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCalcPr parse(URL uRL) throws XmlException, IOException {
            return (CTCalcPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCalcPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCalcPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCalcPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCalcPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCalcPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCalcPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCalcPr parse(Reader reader) throws XmlException, IOException {
            return (CTCalcPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCalcPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCalcPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCalcPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCalcPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCalcPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCalcPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCalcPr parse(Node node) throws XmlException {
            return (CTCalcPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCalcPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCalcPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCalcPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCalcPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCalcPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCalcPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

