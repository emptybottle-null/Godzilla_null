/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.math.BigInteger;
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTwipsMeasure;
import org.w3c.dom.Node;

public interface CTSpacing
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSpacing.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctspacingff2ftype");

    public BigInteger getBefore();

    public STTwipsMeasure xgetBefore();

    public boolean isSetBefore();

    public void setBefore(BigInteger var1);

    public void xsetBefore(STTwipsMeasure var1);

    public void unsetBefore();

    public BigInteger getBeforeLines();

    public STDecimalNumber xgetBeforeLines();

    public boolean isSetBeforeLines();

    public void setBeforeLines(BigInteger var1);

    public void xsetBeforeLines(STDecimalNumber var1);

    public void unsetBeforeLines();

    public STOnOff.Enum getBeforeAutospacing();

    public STOnOff xgetBeforeAutospacing();

    public boolean isSetBeforeAutospacing();

    public void setBeforeAutospacing(STOnOff.Enum var1);

    public void xsetBeforeAutospacing(STOnOff var1);

    public void unsetBeforeAutospacing();

    public BigInteger getAfter();

    public STTwipsMeasure xgetAfter();

    public boolean isSetAfter();

    public void setAfter(BigInteger var1);

    public void xsetAfter(STTwipsMeasure var1);

    public void unsetAfter();

    public BigInteger getAfterLines();

    public STDecimalNumber xgetAfterLines();

    public boolean isSetAfterLines();

    public void setAfterLines(BigInteger var1);

    public void xsetAfterLines(STDecimalNumber var1);

    public void unsetAfterLines();

    public STOnOff.Enum getAfterAutospacing();

    public STOnOff xgetAfterAutospacing();

    public boolean isSetAfterAutospacing();

    public void setAfterAutospacing(STOnOff.Enum var1);

    public void xsetAfterAutospacing(STOnOff var1);

    public void unsetAfterAutospacing();

    public BigInteger getLine();

    public STSignedTwipsMeasure xgetLine();

    public boolean isSetLine();

    public void setLine(BigInteger var1);

    public void xsetLine(STSignedTwipsMeasure var1);

    public void unsetLine();

    public STLineSpacingRule.Enum getLineRule();

    public STLineSpacingRule xgetLineRule();

    public boolean isSetLineRule();

    public void setLineRule(STLineSpacingRule.Enum var1);

    public void xsetLineRule(STLineSpacingRule var1);

    public void unsetLineRule();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSpacing.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSpacing newInstance() {
            return (CTSpacing)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSpacing newInstance(XmlOptions xmlOptions) {
            return (CTSpacing)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSpacing parse(String string) throws XmlException {
            return (CTSpacing)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSpacing parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSpacing)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSpacing parse(File file) throws XmlException, IOException {
            return (CTSpacing)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSpacing parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSpacing)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSpacing parse(URL uRL) throws XmlException, IOException {
            return (CTSpacing)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSpacing parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSpacing)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSpacing parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSpacing)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSpacing parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSpacing)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSpacing parse(Reader reader) throws XmlException, IOException {
            return (CTSpacing)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSpacing parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSpacing)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSpacing parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSpacing)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSpacing parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSpacing)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSpacing parse(Node node) throws XmlException {
            return (CTSpacing)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSpacing parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSpacing)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSpacing parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSpacing)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSpacing parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSpacing)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

