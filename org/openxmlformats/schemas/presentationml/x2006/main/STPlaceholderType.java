/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
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
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface STPlaceholderType
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STPlaceholderType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stplaceholdertypeca72type");
    public static final Enum TITLE = Enum.forString("title");
    public static final Enum BODY = Enum.forString("body");
    public static final Enum CTR_TITLE = Enum.forString("ctrTitle");
    public static final Enum SUB_TITLE = Enum.forString("subTitle");
    public static final Enum DT = Enum.forString("dt");
    public static final Enum SLD_NUM = Enum.forString("sldNum");
    public static final Enum FTR = Enum.forString("ftr");
    public static final Enum HDR = Enum.forString("hdr");
    public static final Enum OBJ = Enum.forString("obj");
    public static final Enum CHART = Enum.forString("chart");
    public static final Enum TBL = Enum.forString("tbl");
    public static final Enum CLIP_ART = Enum.forString("clipArt");
    public static final Enum DGM = Enum.forString("dgm");
    public static final Enum MEDIA = Enum.forString("media");
    public static final Enum SLD_IMG = Enum.forString("sldImg");
    public static final Enum PIC = Enum.forString("pic");
    public static final int INT_TITLE = 1;
    public static final int INT_BODY = 2;
    public static final int INT_CTR_TITLE = 3;
    public static final int INT_SUB_TITLE = 4;
    public static final int INT_DT = 5;
    public static final int INT_SLD_NUM = 6;
    public static final int INT_FTR = 7;
    public static final int INT_HDR = 8;
    public static final int INT_OBJ = 9;
    public static final int INT_CHART = 10;
    public static final int INT_TBL = 11;
    public static final int INT_CLIP_ART = 12;
    public static final int INT_DGM = 13;
    public static final int INT_MEDIA = 14;
    public static final int INT_SLD_IMG = 15;
    public static final int INT_PIC = 16;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STPlaceholderType newValue(Object object) {
            return (STPlaceholderType)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STPlaceholderType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STPlaceholderType newInstance() {
            return (STPlaceholderType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STPlaceholderType newInstance(XmlOptions xmlOptions) {
            return (STPlaceholderType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STPlaceholderType parse(String string) throws XmlException {
            return (STPlaceholderType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STPlaceholderType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STPlaceholderType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STPlaceholderType parse(File file) throws XmlException, IOException {
            return (STPlaceholderType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STPlaceholderType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPlaceholderType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STPlaceholderType parse(URL uRL) throws XmlException, IOException {
            return (STPlaceholderType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STPlaceholderType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPlaceholderType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STPlaceholderType parse(InputStream inputStream) throws XmlException, IOException {
            return (STPlaceholderType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STPlaceholderType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPlaceholderType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STPlaceholderType parse(Reader reader) throws XmlException, IOException {
            return (STPlaceholderType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STPlaceholderType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STPlaceholderType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STPlaceholderType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STPlaceholderType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STPlaceholderType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STPlaceholderType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STPlaceholderType parse(Node node) throws XmlException {
            return (STPlaceholderType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STPlaceholderType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STPlaceholderType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STPlaceholderType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STPlaceholderType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STPlaceholderType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STPlaceholderType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

    public static final class Enum
    extends StringEnumAbstractBase {
        static final int INT_TITLE = 1;
        static final int INT_BODY = 2;
        static final int INT_CTR_TITLE = 3;
        static final int INT_SUB_TITLE = 4;
        static final int INT_DT = 5;
        static final int INT_SLD_NUM = 6;
        static final int INT_FTR = 7;
        static final int INT_HDR = 8;
        static final int INT_OBJ = 9;
        static final int INT_CHART = 10;
        static final int INT_TBL = 11;
        static final int INT_CLIP_ART = 12;
        static final int INT_DGM = 13;
        static final int INT_MEDIA = 14;
        static final int INT_SLD_IMG = 15;
        static final int INT_PIC = 16;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("title", 1), new Enum("body", 2), new Enum("ctrTitle", 3), new Enum("subTitle", 4), new Enum("dt", 5), new Enum("sldNum", 6), new Enum("ftr", 7), new Enum("hdr", 8), new Enum("obj", 9), new Enum("chart", 10), new Enum("tbl", 11), new Enum("clipArt", 12), new Enum("dgm", 13), new Enum("media", 14), new Enum("sldImg", 15), new Enum("pic", 16)});
        private static final long serialVersionUID = 1L;

        public static Enum forString(String string) {
            return (Enum)table.forString(string);
        }

        public static Enum forInt(int n) {
            return (Enum)table.forInt(n);
        }

        private Enum(String string, int n) {
            super(string, n);
        }

        private Object readResolve() {
            return Enum.forInt(this.intValue());
        }
    }
}

