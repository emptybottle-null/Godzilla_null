/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.presentationml.x2006.main;

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
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.presentationml.x2006.main.STName;
import org.w3c.dom.Node;

public interface CTCommentAuthor
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCommentAuthor.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcommentauthora405type");

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public long getId();

    public XmlUnsignedInt xgetId();

    public void setId(long var1);

    public void xsetId(XmlUnsignedInt var1);

    public String getName();

    public STName xgetName();

    public void setName(String var1);

    public void xsetName(STName var1);

    public String getInitials();

    public STName xgetInitials();

    public void setInitials(String var1);

    public void xsetInitials(STName var1);

    public long getLastIdx();

    public XmlUnsignedInt xgetLastIdx();

    public void setLastIdx(long var1);

    public void xsetLastIdx(XmlUnsignedInt var1);

    public long getClrIdx();

    public XmlUnsignedInt xgetClrIdx();

    public void setClrIdx(long var1);

    public void xsetClrIdx(XmlUnsignedInt var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCommentAuthor.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCommentAuthor newInstance() {
            return (CTCommentAuthor)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCommentAuthor newInstance(XmlOptions xmlOptions) {
            return (CTCommentAuthor)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCommentAuthor parse(String string) throws XmlException {
            return (CTCommentAuthor)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCommentAuthor parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCommentAuthor)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCommentAuthor parse(File file) throws XmlException, IOException {
            return (CTCommentAuthor)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCommentAuthor parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCommentAuthor)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCommentAuthor parse(URL uRL) throws XmlException, IOException {
            return (CTCommentAuthor)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCommentAuthor parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCommentAuthor)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCommentAuthor parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCommentAuthor)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCommentAuthor parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCommentAuthor)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCommentAuthor parse(Reader reader) throws XmlException, IOException {
            return (CTCommentAuthor)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCommentAuthor parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCommentAuthor)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCommentAuthor parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCommentAuthor)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCommentAuthor parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCommentAuthor)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCommentAuthor parse(Node node) throws XmlException {
            return (CTCommentAuthor)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCommentAuthor parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCommentAuthor)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCommentAuthor parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCommentAuthor)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCommentAuthor parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCommentAuthor)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

