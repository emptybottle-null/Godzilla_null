/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTOneCellAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTTwoCellAnchor;
import org.w3c.dom.Node;

public interface CTDrawing
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTDrawing.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctdrawing2748type");

    public List<CTTwoCellAnchor> getTwoCellAnchorList();

    @Deprecated
    public CTTwoCellAnchor[] getTwoCellAnchorArray();

    public CTTwoCellAnchor getTwoCellAnchorArray(int var1);

    public int sizeOfTwoCellAnchorArray();

    public void setTwoCellAnchorArray(CTTwoCellAnchor[] var1);

    public void setTwoCellAnchorArray(int var1, CTTwoCellAnchor var2);

    public CTTwoCellAnchor insertNewTwoCellAnchor(int var1);

    public CTTwoCellAnchor addNewTwoCellAnchor();

    public void removeTwoCellAnchor(int var1);

    public List<CTOneCellAnchor> getOneCellAnchorList();

    @Deprecated
    public CTOneCellAnchor[] getOneCellAnchorArray();

    public CTOneCellAnchor getOneCellAnchorArray(int var1);

    public int sizeOfOneCellAnchorArray();

    public void setOneCellAnchorArray(CTOneCellAnchor[] var1);

    public void setOneCellAnchorArray(int var1, CTOneCellAnchor var2);

    public CTOneCellAnchor insertNewOneCellAnchor(int var1);

    public CTOneCellAnchor addNewOneCellAnchor();

    public void removeOneCellAnchor(int var1);

    public List<CTAbsoluteAnchor> getAbsoluteAnchorList();

    @Deprecated
    public CTAbsoluteAnchor[] getAbsoluteAnchorArray();

    public CTAbsoluteAnchor getAbsoluteAnchorArray(int var1);

    public int sizeOfAbsoluteAnchorArray();

    public void setAbsoluteAnchorArray(CTAbsoluteAnchor[] var1);

    public void setAbsoluteAnchorArray(int var1, CTAbsoluteAnchor var2);

    public CTAbsoluteAnchor insertNewAbsoluteAnchor(int var1);

    public CTAbsoluteAnchor addNewAbsoluteAnchor();

    public void removeAbsoluteAnchor(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTDrawing.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTDrawing newInstance() {
            return (CTDrawing)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTDrawing newInstance(XmlOptions xmlOptions) {
            return (CTDrawing)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTDrawing parse(String string) throws XmlException {
            return (CTDrawing)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTDrawing parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTDrawing)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTDrawing parse(File file) throws XmlException, IOException {
            return (CTDrawing)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTDrawing parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDrawing)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTDrawing parse(URL uRL) throws XmlException, IOException {
            return (CTDrawing)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTDrawing parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDrawing)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTDrawing parse(InputStream inputStream) throws XmlException, IOException {
            return (CTDrawing)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTDrawing parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDrawing)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTDrawing parse(Reader reader) throws XmlException, IOException {
            return (CTDrawing)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTDrawing parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDrawing)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTDrawing parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTDrawing)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTDrawing parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTDrawing)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTDrawing parse(Node node) throws XmlException {
            return (CTDrawing)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTDrawing parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTDrawing)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTDrawing parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTDrawing)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTDrawing parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTDrawing)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

