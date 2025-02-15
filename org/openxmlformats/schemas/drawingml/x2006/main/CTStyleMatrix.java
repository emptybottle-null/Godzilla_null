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
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBackgroundFillStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList;
import org.w3c.dom.Node;

public interface CTStyleMatrix
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTStyleMatrix.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctstylematrix1903type");

    public CTFillStyleList getFillStyleLst();

    public void setFillStyleLst(CTFillStyleList var1);

    public CTFillStyleList addNewFillStyleLst();

    public CTLineStyleList getLnStyleLst();

    public void setLnStyleLst(CTLineStyleList var1);

    public CTLineStyleList addNewLnStyleLst();

    public CTEffectStyleList getEffectStyleLst();

    public void setEffectStyleLst(CTEffectStyleList var1);

    public CTEffectStyleList addNewEffectStyleLst();

    public CTBackgroundFillStyleList getBgFillStyleLst();

    public void setBgFillStyleLst(CTBackgroundFillStyleList var1);

    public CTBackgroundFillStyleList addNewBgFillStyleLst();

    public String getName();

    public XmlString xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(XmlString var1);

    public void unsetName();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTStyleMatrix.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTStyleMatrix newInstance() {
            return (CTStyleMatrix)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTStyleMatrix newInstance(XmlOptions xmlOptions) {
            return (CTStyleMatrix)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTStyleMatrix parse(String string) throws XmlException {
            return (CTStyleMatrix)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTStyleMatrix parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTStyleMatrix)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTStyleMatrix parse(File file) throws XmlException, IOException {
            return (CTStyleMatrix)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTStyleMatrix parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStyleMatrix)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTStyleMatrix parse(URL uRL) throws XmlException, IOException {
            return (CTStyleMatrix)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTStyleMatrix parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStyleMatrix)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTStyleMatrix parse(InputStream inputStream) throws XmlException, IOException {
            return (CTStyleMatrix)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTStyleMatrix parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStyleMatrix)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTStyleMatrix parse(Reader reader) throws XmlException, IOException {
            return (CTStyleMatrix)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTStyleMatrix parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStyleMatrix)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTStyleMatrix parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTStyleMatrix)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTStyleMatrix parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTStyleMatrix)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTStyleMatrix parse(Node node) throws XmlException {
            return (CTStyleMatrix)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTStyleMatrix parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTStyleMatrix)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTStyleMatrix parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTStyleMatrix)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTStyleMatrix parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTStyleMatrix)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

