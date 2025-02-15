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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;
import org.w3c.dom.Node;

public interface CTLsdException
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTLsdException.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctlsdexceptiona296type");

    public String getName();

    public STString xgetName();

    public void setName(String var1);

    public void xsetName(STString var1);

    public STOnOff.Enum getLocked();

    public STOnOff xgetLocked();

    public boolean isSetLocked();

    public void setLocked(STOnOff.Enum var1);

    public void xsetLocked(STOnOff var1);

    public void unsetLocked();

    public BigInteger getUiPriority();

    public STDecimalNumber xgetUiPriority();

    public boolean isSetUiPriority();

    public void setUiPriority(BigInteger var1);

    public void xsetUiPriority(STDecimalNumber var1);

    public void unsetUiPriority();

    public STOnOff.Enum getSemiHidden();

    public STOnOff xgetSemiHidden();

    public boolean isSetSemiHidden();

    public void setSemiHidden(STOnOff.Enum var1);

    public void xsetSemiHidden(STOnOff var1);

    public void unsetSemiHidden();

    public STOnOff.Enum getUnhideWhenUsed();

    public STOnOff xgetUnhideWhenUsed();

    public boolean isSetUnhideWhenUsed();

    public void setUnhideWhenUsed(STOnOff.Enum var1);

    public void xsetUnhideWhenUsed(STOnOff var1);

    public void unsetUnhideWhenUsed();

    public STOnOff.Enum getQFormat();

    public STOnOff xgetQFormat();

    public boolean isSetQFormat();

    public void setQFormat(STOnOff.Enum var1);

    public void xsetQFormat(STOnOff var1);

    public void unsetQFormat();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTLsdException.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTLsdException newInstance() {
            return (CTLsdException)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTLsdException newInstance(XmlOptions xmlOptions) {
            return (CTLsdException)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTLsdException parse(String string) throws XmlException {
            return (CTLsdException)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTLsdException parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTLsdException)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTLsdException parse(File file) throws XmlException, IOException {
            return (CTLsdException)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTLsdException parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLsdException)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTLsdException parse(URL uRL) throws XmlException, IOException {
            return (CTLsdException)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTLsdException parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLsdException)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTLsdException parse(InputStream inputStream) throws XmlException, IOException {
            return (CTLsdException)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTLsdException parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLsdException)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTLsdException parse(Reader reader) throws XmlException, IOException {
            return (CTLsdException)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTLsdException parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLsdException)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTLsdException parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTLsdException)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTLsdException parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTLsdException)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTLsdException parse(Node node) throws XmlException {
            return (CTLsdException)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTLsdException parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTLsdException)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTLsdException parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTLsdException)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTLsdException parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTLsdException)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

