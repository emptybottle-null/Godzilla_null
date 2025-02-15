/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPicBullet
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPicBullet;
import org.w3c.dom.Node;

public interface CTNumbering
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTNumbering.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctnumberingfdf9type");

    public List<CTNumPicBullet> getNumPicBulletList();

    @Deprecated
    public CTNumPicBullet[] getNumPicBulletArray();

    public CTNumPicBullet getNumPicBulletArray(int var1);

    public int sizeOfNumPicBulletArray();

    public void setNumPicBulletArray(CTNumPicBullet[] var1);

    public void setNumPicBulletArray(int var1, CTNumPicBullet var2);

    public CTNumPicBullet insertNewNumPicBullet(int var1);

    public CTNumPicBullet addNewNumPicBullet();

    public void removeNumPicBullet(int var1);

    public List<CTAbstractNum> getAbstractNumList();

    @Deprecated
    public CTAbstractNum[] getAbstractNumArray();

    public CTAbstractNum getAbstractNumArray(int var1);

    public int sizeOfAbstractNumArray();

    public void setAbstractNumArray(CTAbstractNum[] var1);

    public void setAbstractNumArray(int var1, CTAbstractNum var2);

    public CTAbstractNum insertNewAbstractNum(int var1);

    public CTAbstractNum addNewAbstractNum();

    public void removeAbstractNum(int var1);

    public List<CTNum> getNumList();

    @Deprecated
    public CTNum[] getNumArray();

    public CTNum getNumArray(int var1);

    public int sizeOfNumArray();

    public void setNumArray(CTNum[] var1);

    public void setNumArray(int var1, CTNum var2);

    public CTNum insertNewNum(int var1);

    public CTNum addNewNum();

    public void removeNum(int var1);

    public CTDecimalNumber getNumIdMacAtCleanup();

    public boolean isSetNumIdMacAtCleanup();

    public void setNumIdMacAtCleanup(CTDecimalNumber var1);

    public CTDecimalNumber addNewNumIdMacAtCleanup();

    public void unsetNumIdMacAtCleanup();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTNumbering.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTNumbering newInstance() {
            return (CTNumbering)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTNumbering newInstance(XmlOptions xmlOptions) {
            return (CTNumbering)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTNumbering parse(String string) throws XmlException {
            return (CTNumbering)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTNumbering parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTNumbering)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTNumbering parse(File file) throws XmlException, IOException {
            return (CTNumbering)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTNumbering parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNumbering)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTNumbering parse(URL uRL) throws XmlException, IOException {
            return (CTNumbering)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTNumbering parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNumbering)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTNumbering parse(InputStream inputStream) throws XmlException, IOException {
            return (CTNumbering)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTNumbering parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNumbering)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTNumbering parse(Reader reader) throws XmlException, IOException {
            return (CTNumbering)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTNumbering parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNumbering)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTNumbering parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTNumbering)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTNumbering parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTNumbering)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTNumbering parse(Node node) throws XmlException {
            return (CTNumbering)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTNumbering parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTNumbering)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTNumbering parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTNumbering)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTNumbering parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTNumbering)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

