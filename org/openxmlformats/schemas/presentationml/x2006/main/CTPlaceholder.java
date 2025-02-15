/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify
 *  org.openxmlformats.schemas.presentationml.x2006.main.STDirection
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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.STDirection;
import org.openxmlformats.schemas.presentationml.x2006.main.STPlaceholderSize;
import org.openxmlformats.schemas.presentationml.x2006.main.STPlaceholderType;
import org.w3c.dom.Node;

public interface CTPlaceholder
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPlaceholder.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctplaceholder9efctype");

    public CTExtensionListModify getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionListModify var1);

    public CTExtensionListModify addNewExtLst();

    public void unsetExtLst();

    public STPlaceholderType.Enum getType();

    public STPlaceholderType xgetType();

    public boolean isSetType();

    public void setType(STPlaceholderType.Enum var1);

    public void xsetType(STPlaceholderType var1);

    public void unsetType();

    public STDirection.Enum getOrient();

    public STDirection xgetOrient();

    public boolean isSetOrient();

    public void setOrient(STDirection.Enum var1);

    public void xsetOrient(STDirection var1);

    public void unsetOrient();

    public STPlaceholderSize.Enum getSz();

    public STPlaceholderSize xgetSz();

    public boolean isSetSz();

    public void setSz(STPlaceholderSize.Enum var1);

    public void xsetSz(STPlaceholderSize var1);

    public void unsetSz();

    public long getIdx();

    public XmlUnsignedInt xgetIdx();

    public boolean isSetIdx();

    public void setIdx(long var1);

    public void xsetIdx(XmlUnsignedInt var1);

    public void unsetIdx();

    public boolean getHasCustomPrompt();

    public XmlBoolean xgetHasCustomPrompt();

    public boolean isSetHasCustomPrompt();

    public void setHasCustomPrompt(boolean var1);

    public void xsetHasCustomPrompt(XmlBoolean var1);

    public void unsetHasCustomPrompt();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPlaceholder.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPlaceholder newInstance() {
            return (CTPlaceholder)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPlaceholder newInstance(XmlOptions xmlOptions) {
            return (CTPlaceholder)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPlaceholder parse(String string) throws XmlException {
            return (CTPlaceholder)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPlaceholder parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPlaceholder)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPlaceholder parse(File file) throws XmlException, IOException {
            return (CTPlaceholder)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPlaceholder parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPlaceholder)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPlaceholder parse(URL uRL) throws XmlException, IOException {
            return (CTPlaceholder)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPlaceholder parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPlaceholder)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPlaceholder parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPlaceholder)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPlaceholder parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPlaceholder)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPlaceholder parse(Reader reader) throws XmlException, IOException {
            return (CTPlaceholder)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPlaceholder parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPlaceholder)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPlaceholder parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPlaceholder)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPlaceholder parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPlaceholder)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPlaceholder parse(Node node) throws XmlException {
            return (CTPlaceholder)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPlaceholder parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPlaceholder)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPlaceholder parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPlaceholder)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPlaceholder parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPlaceholder)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

