/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabAlignment
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabLeader
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabRelativeTo
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
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabAlignment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabLeader;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabRelativeTo;
import org.w3c.dom.Node;

public interface CTPTab
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPTab.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctptaba283type");

    public STPTabAlignment.Enum getAlignment();

    public STPTabAlignment xgetAlignment();

    public void setAlignment(STPTabAlignment.Enum var1);

    public void xsetAlignment(STPTabAlignment var1);

    public STPTabRelativeTo.Enum getRelativeTo();

    public STPTabRelativeTo xgetRelativeTo();

    public void setRelativeTo(STPTabRelativeTo.Enum var1);

    public void xsetRelativeTo(STPTabRelativeTo var1);

    public STPTabLeader.Enum getLeader();

    public STPTabLeader xgetLeader();

    public void setLeader(STPTabLeader.Enum var1);

    public void xsetLeader(STPTabLeader var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPTab.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPTab newInstance() {
            return (CTPTab)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPTab newInstance(XmlOptions xmlOptions) {
            return (CTPTab)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPTab parse(String string) throws XmlException {
            return (CTPTab)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPTab parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPTab)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPTab parse(File file) throws XmlException, IOException {
            return (CTPTab)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPTab parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPTab)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPTab parse(URL uRL) throws XmlException, IOException {
            return (CTPTab)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPTab parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPTab)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPTab parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPTab)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPTab parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPTab)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPTab parse(Reader reader) throws XmlException, IOException {
            return (CTPTab)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPTab parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPTab)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPTab parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPTab)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPTab parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPTab)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPTab parse(Node node) throws XmlException {
            return (CTPTab)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPTab parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPTab)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPTab parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPTab)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPTab parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPTab)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

