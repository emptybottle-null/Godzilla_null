/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
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
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface STShd
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STShd.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stshd14d3type");
    public static final Enum NIL = Enum.forString("nil");
    public static final Enum CLEAR = Enum.forString("clear");
    public static final Enum SOLID = Enum.forString("solid");
    public static final Enum HORZ_STRIPE = Enum.forString("horzStripe");
    public static final Enum VERT_STRIPE = Enum.forString("vertStripe");
    public static final Enum REVERSE_DIAG_STRIPE = Enum.forString("reverseDiagStripe");
    public static final Enum DIAG_STRIPE = Enum.forString("diagStripe");
    public static final Enum HORZ_CROSS = Enum.forString("horzCross");
    public static final Enum DIAG_CROSS = Enum.forString("diagCross");
    public static final Enum THIN_HORZ_STRIPE = Enum.forString("thinHorzStripe");
    public static final Enum THIN_VERT_STRIPE = Enum.forString("thinVertStripe");
    public static final Enum THIN_REVERSE_DIAG_STRIPE = Enum.forString("thinReverseDiagStripe");
    public static final Enum THIN_DIAG_STRIPE = Enum.forString("thinDiagStripe");
    public static final Enum THIN_HORZ_CROSS = Enum.forString("thinHorzCross");
    public static final Enum THIN_DIAG_CROSS = Enum.forString("thinDiagCross");
    public static final Enum PCT_5 = Enum.forString("pct5");
    public static final Enum PCT_10 = Enum.forString("pct10");
    public static final Enum PCT_12 = Enum.forString("pct12");
    public static final Enum PCT_15 = Enum.forString("pct15");
    public static final Enum PCT_20 = Enum.forString("pct20");
    public static final Enum PCT_25 = Enum.forString("pct25");
    public static final Enum PCT_30 = Enum.forString("pct30");
    public static final Enum PCT_35 = Enum.forString("pct35");
    public static final Enum PCT_37 = Enum.forString("pct37");
    public static final Enum PCT_40 = Enum.forString("pct40");
    public static final Enum PCT_45 = Enum.forString("pct45");
    public static final Enum PCT_50 = Enum.forString("pct50");
    public static final Enum PCT_55 = Enum.forString("pct55");
    public static final Enum PCT_60 = Enum.forString("pct60");
    public static final Enum PCT_62 = Enum.forString("pct62");
    public static final Enum PCT_65 = Enum.forString("pct65");
    public static final Enum PCT_70 = Enum.forString("pct70");
    public static final Enum PCT_75 = Enum.forString("pct75");
    public static final Enum PCT_80 = Enum.forString("pct80");
    public static final Enum PCT_85 = Enum.forString("pct85");
    public static final Enum PCT_87 = Enum.forString("pct87");
    public static final Enum PCT_90 = Enum.forString("pct90");
    public static final Enum PCT_95 = Enum.forString("pct95");
    public static final int INT_NIL = 1;
    public static final int INT_CLEAR = 2;
    public static final int INT_SOLID = 3;
    public static final int INT_HORZ_STRIPE = 4;
    public static final int INT_VERT_STRIPE = 5;
    public static final int INT_REVERSE_DIAG_STRIPE = 6;
    public static final int INT_DIAG_STRIPE = 7;
    public static final int INT_HORZ_CROSS = 8;
    public static final int INT_DIAG_CROSS = 9;
    public static final int INT_THIN_HORZ_STRIPE = 10;
    public static final int INT_THIN_VERT_STRIPE = 11;
    public static final int INT_THIN_REVERSE_DIAG_STRIPE = 12;
    public static final int INT_THIN_DIAG_STRIPE = 13;
    public static final int INT_THIN_HORZ_CROSS = 14;
    public static final int INT_THIN_DIAG_CROSS = 15;
    public static final int INT_PCT_5 = 16;
    public static final int INT_PCT_10 = 17;
    public static final int INT_PCT_12 = 18;
    public static final int INT_PCT_15 = 19;
    public static final int INT_PCT_20 = 20;
    public static final int INT_PCT_25 = 21;
    public static final int INT_PCT_30 = 22;
    public static final int INT_PCT_35 = 23;
    public static final int INT_PCT_37 = 24;
    public static final int INT_PCT_40 = 25;
    public static final int INT_PCT_45 = 26;
    public static final int INT_PCT_50 = 27;
    public static final int INT_PCT_55 = 28;
    public static final int INT_PCT_60 = 29;
    public static final int INT_PCT_62 = 30;
    public static final int INT_PCT_65 = 31;
    public static final int INT_PCT_70 = 32;
    public static final int INT_PCT_75 = 33;
    public static final int INT_PCT_80 = 34;
    public static final int INT_PCT_85 = 35;
    public static final int INT_PCT_87 = 36;
    public static final int INT_PCT_90 = 37;
    public static final int INT_PCT_95 = 38;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STShd newValue(Object object) {
            return (STShd)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STShd.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STShd newInstance() {
            return (STShd)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STShd newInstance(XmlOptions xmlOptions) {
            return (STShd)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STShd parse(String string) throws XmlException {
            return (STShd)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STShd parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STShd)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STShd parse(File file) throws XmlException, IOException {
            return (STShd)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STShd parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STShd)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STShd parse(URL uRL) throws XmlException, IOException {
            return (STShd)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STShd parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STShd)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STShd parse(InputStream inputStream) throws XmlException, IOException {
            return (STShd)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STShd parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STShd)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STShd parse(Reader reader) throws XmlException, IOException {
            return (STShd)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STShd parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STShd)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STShd parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STShd)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STShd parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STShd)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STShd parse(Node node) throws XmlException {
            return (STShd)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STShd parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STShd)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STShd parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STShd)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STShd parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STShd)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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
        static final int INT_NIL = 1;
        static final int INT_CLEAR = 2;
        static final int INT_SOLID = 3;
        static final int INT_HORZ_STRIPE = 4;
        static final int INT_VERT_STRIPE = 5;
        static final int INT_REVERSE_DIAG_STRIPE = 6;
        static final int INT_DIAG_STRIPE = 7;
        static final int INT_HORZ_CROSS = 8;
        static final int INT_DIAG_CROSS = 9;
        static final int INT_THIN_HORZ_STRIPE = 10;
        static final int INT_THIN_VERT_STRIPE = 11;
        static final int INT_THIN_REVERSE_DIAG_STRIPE = 12;
        static final int INT_THIN_DIAG_STRIPE = 13;
        static final int INT_THIN_HORZ_CROSS = 14;
        static final int INT_THIN_DIAG_CROSS = 15;
        static final int INT_PCT_5 = 16;
        static final int INT_PCT_10 = 17;
        static final int INT_PCT_12 = 18;
        static final int INT_PCT_15 = 19;
        static final int INT_PCT_20 = 20;
        static final int INT_PCT_25 = 21;
        static final int INT_PCT_30 = 22;
        static final int INT_PCT_35 = 23;
        static final int INT_PCT_37 = 24;
        static final int INT_PCT_40 = 25;
        static final int INT_PCT_45 = 26;
        static final int INT_PCT_50 = 27;
        static final int INT_PCT_55 = 28;
        static final int INT_PCT_60 = 29;
        static final int INT_PCT_62 = 30;
        static final int INT_PCT_65 = 31;
        static final int INT_PCT_70 = 32;
        static final int INT_PCT_75 = 33;
        static final int INT_PCT_80 = 34;
        static final int INT_PCT_85 = 35;
        static final int INT_PCT_87 = 36;
        static final int INT_PCT_90 = 37;
        static final int INT_PCT_95 = 38;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("nil", 1), new Enum("clear", 2), new Enum("solid", 3), new Enum("horzStripe", 4), new Enum("vertStripe", 5), new Enum("reverseDiagStripe", 6), new Enum("diagStripe", 7), new Enum("horzCross", 8), new Enum("diagCross", 9), new Enum("thinHorzStripe", 10), new Enum("thinVertStripe", 11), new Enum("thinReverseDiagStripe", 12), new Enum("thinDiagStripe", 13), new Enum("thinHorzCross", 14), new Enum("thinDiagCross", 15), new Enum("pct5", 16), new Enum("pct10", 17), new Enum("pct12", 18), new Enum("pct15", 19), new Enum("pct20", 20), new Enum("pct25", 21), new Enum("pct30", 22), new Enum("pct35", 23), new Enum("pct37", 24), new Enum("pct40", 25), new Enum("pct45", 26), new Enum("pct50", 27), new Enum("pct55", 28), new Enum("pct60", 29), new Enum("pct62", 30), new Enum("pct65", 31), new Enum("pct70", 32), new Enum("pct75", 33), new Enum("pct80", 34), new Enum("pct85", 35), new Enum("pct87", 36), new Enum("pct90", 37), new Enum("pct95", 38)});
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

