/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.ltgfmt;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface TestsDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(TestsDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLTOOLS").resolveHandle("tests5621doctype");

    public Tests getTests();

    public void setTests(Tests var1);

    public Tests addNewTests();

    public static final class Factory {
        public static TestsDocument newInstance() {
            return (TestsDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static TestsDocument newInstance(XmlOptions options) {
            return (TestsDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static TestsDocument parse(String xmlAsString) throws XmlException {
            return (TestsDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static TestsDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (TestsDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static TestsDocument parse(File file) throws XmlException, IOException {
            return (TestsDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static TestsDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (TestsDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static TestsDocument parse(URL u) throws XmlException, IOException {
            return (TestsDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static TestsDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (TestsDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static TestsDocument parse(InputStream is) throws XmlException, IOException {
            return (TestsDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static TestsDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (TestsDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static TestsDocument parse(Reader r) throws XmlException, IOException {
            return (TestsDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static TestsDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (TestsDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static TestsDocument parse(XMLStreamReader sr) throws XmlException {
            return (TestsDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static TestsDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (TestsDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static TestsDocument parse(Node node) throws XmlException {
            return (TestsDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static TestsDocument parse(Node node, XmlOptions options) throws XmlException {
            return (TestsDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static TestsDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (TestsDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static TestsDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (TestsDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
        }

        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xis) throws XmlException, XMLStreamException {
            return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xis, type, null);
        }

        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xis, type, options);
        }

        private Factory() {
        }
    }

    public static interface Tests
    extends XmlObject {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Tests.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLTOOLS").resolveHandle("tests9d6eelemtype");

        public TestCase[] getTestArray();

        public TestCase getTestArray(int var1);

        public int sizeOfTestArray();

        public void setTestArray(TestCase[] var1);

        public void setTestArray(int var1, TestCase var2);

        public TestCase insertNewTest(int var1);

        public TestCase addNewTest();

        public void removeTest(int var1);

        public static final class Factory {
            public static Tests newInstance() {
                return (Tests)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Tests newInstance(XmlOptions options) {
                return (Tests)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

