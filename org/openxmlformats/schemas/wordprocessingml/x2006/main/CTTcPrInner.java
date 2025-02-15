/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCellMergeTrackChange
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCellMergeTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;
import org.w3c.dom.Node;

public interface CTTcPrInner
extends CTTcPrBase {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTcPrInner.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttcprinnerc56dtype");

    public CTTrackChange getCellIns();

    public boolean isSetCellIns();

    public void setCellIns(CTTrackChange var1);

    public CTTrackChange addNewCellIns();

    public void unsetCellIns();

    public CTTrackChange getCellDel();

    public boolean isSetCellDel();

    public void setCellDel(CTTrackChange var1);

    public CTTrackChange addNewCellDel();

    public void unsetCellDel();

    public CTCellMergeTrackChange getCellMerge();

    public boolean isSetCellMerge();

    public void setCellMerge(CTCellMergeTrackChange var1);

    public CTCellMergeTrackChange addNewCellMerge();

    public void unsetCellMerge();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTcPrInner.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTcPrInner newInstance() {
            return (CTTcPrInner)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTcPrInner newInstance(XmlOptions xmlOptions) {
            return (CTTcPrInner)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTcPrInner parse(String string) throws XmlException {
            return (CTTcPrInner)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTcPrInner parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTcPrInner)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTcPrInner parse(File file) throws XmlException, IOException {
            return (CTTcPrInner)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTcPrInner parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTcPrInner)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTcPrInner parse(URL uRL) throws XmlException, IOException {
            return (CTTcPrInner)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTcPrInner parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTcPrInner)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTcPrInner parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTcPrInner)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTcPrInner parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTcPrInner)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTcPrInner parse(Reader reader) throws XmlException, IOException {
            return (CTTcPrInner)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTcPrInner parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTcPrInner)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTcPrInner parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTcPrInner)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTcPrInner parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTcPrInner)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTcPrInner parse(Node node) throws XmlException {
            return (CTTcPrInner)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTcPrInner parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTcPrInner)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTcPrInner parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTcPrInner)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTcPrInner parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTcPrInner)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

