/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify
 *  org.openxmlformats.schemas.presentationml.x2006.main.STIndex
 */
package org.openxmlformats.schemas.presentationml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.Calendar;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.STIndex;
import org.w3c.dom.Node;

public interface CTComment
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTComment.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcomment2d10type");

    public CTPoint2D getPos();

    public void setPos(CTPoint2D var1);

    public CTPoint2D addNewPos();

    public String getText();

    public XmlString xgetText();

    public void setText(String var1);

    public void xsetText(XmlString var1);

    public CTExtensionListModify getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionListModify var1);

    public CTExtensionListModify addNewExtLst();

    public void unsetExtLst();

    public long getAuthorId();

    public XmlUnsignedInt xgetAuthorId();

    public void setAuthorId(long var1);

    public void xsetAuthorId(XmlUnsignedInt var1);

    public Calendar getDt();

    public XmlDateTime xgetDt();

    public boolean isSetDt();

    public void setDt(Calendar var1);

    public void xsetDt(XmlDateTime var1);

    public void unsetDt();

    public long getIdx();

    public STIndex xgetIdx();

    public void setIdx(long var1);

    public void xsetIdx(STIndex var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTComment.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTComment newInstance() {
            return (CTComment)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTComment newInstance(XmlOptions xmlOptions) {
            return (CTComment)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTComment parse(String string) throws XmlException {
            return (CTComment)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTComment parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTComment)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTComment parse(File file) throws XmlException, IOException {
            return (CTComment)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTComment parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTComment)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTComment parse(URL uRL) throws XmlException, IOException {
            return (CTComment)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTComment parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTComment)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTComment parse(InputStream inputStream) throws XmlException, IOException {
            return (CTComment)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTComment parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTComment)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTComment parse(Reader reader) throws XmlException, IOException {
            return (CTComment)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTComment parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTComment)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTComment parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTComment)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTComment parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTComment)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTComment parse(Node node) throws XmlException {
            return (CTComment)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTComment parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTComment)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTComment parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTComment)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTComment parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTComment)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

