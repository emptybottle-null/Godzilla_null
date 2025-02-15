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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D;
import org.w3c.dom.Node;

public interface CTEffectStyleItem
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTEffectStyleItem.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cteffectstyleitem05c4type");

    public CTEffectList getEffectLst();

    public boolean isSetEffectLst();

    public void setEffectLst(CTEffectList var1);

    public CTEffectList addNewEffectLst();

    public void unsetEffectLst();

    public CTEffectContainer getEffectDag();

    public boolean isSetEffectDag();

    public void setEffectDag(CTEffectContainer var1);

    public CTEffectContainer addNewEffectDag();

    public void unsetEffectDag();

    public CTScene3D getScene3D();

    public boolean isSetScene3D();

    public void setScene3D(CTScene3D var1);

    public CTScene3D addNewScene3D();

    public void unsetScene3D();

    public CTShape3D getSp3D();

    public boolean isSetSp3D();

    public void setSp3D(CTShape3D var1);

    public CTShape3D addNewSp3D();

    public void unsetSp3D();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTEffectStyleItem.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTEffectStyleItem newInstance() {
            return (CTEffectStyleItem)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTEffectStyleItem newInstance(XmlOptions xmlOptions) {
            return (CTEffectStyleItem)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTEffectStyleItem parse(String string) throws XmlException {
            return (CTEffectStyleItem)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTEffectStyleItem parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTEffectStyleItem)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTEffectStyleItem parse(File file) throws XmlException, IOException {
            return (CTEffectStyleItem)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTEffectStyleItem parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEffectStyleItem)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTEffectStyleItem parse(URL uRL) throws XmlException, IOException {
            return (CTEffectStyleItem)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTEffectStyleItem parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEffectStyleItem)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTEffectStyleItem parse(InputStream inputStream) throws XmlException, IOException {
            return (CTEffectStyleItem)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTEffectStyleItem parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEffectStyleItem)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTEffectStyleItem parse(Reader reader) throws XmlException, IOException {
            return (CTEffectStyleItem)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTEffectStyleItem parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEffectStyleItem)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTEffectStyleItem parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTEffectStyleItem)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTEffectStyleItem parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTEffectStyleItem)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTEffectStyleItem parse(Node node) throws XmlException {
            return (CTEffectStyleItem)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTEffectStyleItem parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTEffectStyleItem)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTEffectStyleItem parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTEffectStyleItem)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTEffectStyleItem parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTEffectStyleItem)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

