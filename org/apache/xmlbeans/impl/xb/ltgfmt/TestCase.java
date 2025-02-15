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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface TestCase
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(TestCase.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLTOOLS").resolveHandle("testcase939btype");

    public String getDescription();

    public XmlString xgetDescription();

    public boolean isSetDescription();

    public void setDescription(String var1);

    public void xsetDescription(XmlString var1);

    public void unsetDescription();

    public Files getFiles();

    public void setFiles(Files var1);

    public Files addNewFiles();

    public String getId();

    public XmlID xgetId();

    public boolean isSetId();

    public void setId(String var1);

    public void xsetId(XmlID var1);

    public void unsetId();

    public String getOrigin();

    public XmlToken xgetOrigin();

    public boolean isSetOrigin();

    public void setOrigin(String var1);

    public void xsetOrigin(XmlToken var1);

    public void unsetOrigin();

    public boolean getModified();

    public XmlBoolean xgetModified();

    public boolean isSetModified();

    public void setModified(boolean var1);

    public void xsetModified(XmlBoolean var1);

    public void unsetModified();

    public static final class Factory {
        public static TestCase newInstance() {
            return (TestCase)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static TestCase newInstance(XmlOptions options) {
            return (TestCase)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static TestCase parse(String xmlAsString) throws XmlException {
            return (TestCase)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static TestCase parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (TestCase)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static TestCase parse(File file) throws XmlException, IOException {
            return (TestCase)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static TestCase parse(File file, XmlOptions options) throws XmlException, IOException {
            return (TestCase)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static TestCase parse(URL u) throws XmlException, IOException {
            return (TestCase)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static TestCase parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (TestCase)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static TestCase parse(InputStream is) throws XmlException, IOException {
            return (TestCase)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static TestCase parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (TestCase)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static TestCase parse(Reader r) throws XmlException, IOException {
            return (TestCase)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static TestCase parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (TestCase)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static TestCase parse(XMLStreamReader sr) throws XmlException {
            return (TestCase)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static TestCase parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (TestCase)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static TestCase parse(Node node) throws XmlException {
            return (TestCase)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static TestCase parse(Node node, XmlOptions options) throws XmlException {
            return (TestCase)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static TestCase parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (TestCase)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static TestCase parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (TestCase)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Files
    extends XmlObject {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Files.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLTOOLS").resolveHandle("files7c3eelemtype");

        public FileDesc[] getFileArray();

        public FileDesc getFileArray(int var1);

        public int sizeOfFileArray();

        public void setFileArray(FileDesc[] var1);

        public void setFileArray(int var1, FileDesc var2);

        public FileDesc insertNewFile(int var1);

        public FileDesc addNewFile();

        public void removeFile(int var1);

        public static final class Factory {
            public static Files newInstance() {
                return (Files)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Files newInstance(XmlOptions options) {
                return (Files)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

